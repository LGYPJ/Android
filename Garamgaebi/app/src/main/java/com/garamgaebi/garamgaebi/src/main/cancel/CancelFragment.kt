package com.garamgaebi.garamgaebi.src.main.cancel

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.ViewTreeObserver
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.FragmentCancelBinding
import com.garamgaebi.garamgaebi.model.CancelRequest
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.ApplyViewModel
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class CancelFragment: BaseBindingFragment<FragmentCancelBinding>(R.layout.fragment_cancel),
    ConfirmDialogInterface {

    //화면전환
    var containerActivity: ContainerActivity? = null
    private val viewModel by viewModels<ApplyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //binding.activityCancelApplyBtn.isEnabled = false

        binding.activityCancelBankTv.text = "은행"

        //신청정보조회
        if(ContainerActivity().networkValid.value == true) {
            viewModel.getCancel()
        }else{

        }
        viewModel.cancelInfo.observe(viewLifecycleOwner, Observer{
            if(it.isSuccess) {
                val data = it.result
                with(binding) {
                    activityCancelNameTv.text = data.name
                    activityCancelNicknameTv.text = data.nickname
                    activityCancelPhoneTv.text = data.phone
                }
            }else{
            }
        })


        // et selected 여부에 따라 drawable 결정
        binding.activityCancelPayEt.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
            } else {
                view.setBackgroundResource(R.drawable.et_seminat_apply)
            }
        }
        //바텀다이얼로그 팝업
        binding.activityCancelBankTv.setOnClickListener {
            val orderBottomDialogFragment: CancelBankBottomDialogFragment = CancelBankBottomDialogFragment {
                binding.activityCancelBankTv.text = it
                CoroutineScope(Dispatchers.Main).launch {
                    val saveBank = async(Dispatchers.Default) { // 비동기 작업 시작
                        GaramgaebiApplication().saveStringToDataStore("bank", it)

                    }.await() // 결과 대기
                }

                binding.activityCancelBankTv.setTextColor(resources.getColor(R.color.black))
                binding.activityCancelBankTv.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
                isBank()
            }
                    activity?.let {
                        orderBottomDialogFragment.show(
                            it.supportFragmentManager, "orderBottomDialogFragment"
                        )
                    }
                    orderBottomDialogFragment.dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        }

        viewModel.cancel.observe(viewLifecycleOwner, Observer {
            Log.d("cancel", it.toString())

            if(it.isSuccess){
                val dialog = ConfirmDialog(this, getString(R.string.cancel_complete), -1) {
                    when (it) {
                        1 -> {
                            Log.d("cancel", "close")
                        }
                        2 -> {
                            (activity as ContainerActivity).onBackPressed()
                        }
                    }
                }

                            // 알림창이 띄워져있는 동안 배경 클릭 막기
                            dialog.show(
                                activity?.supportFragmentManager!!,
                                "com.example.garamgaebi.common.ConfirmDialog"
                            )
                        }else{
                (requireActivity() as ContainerActivity).networkAlertDialog()

            }

        })

        disposables
            .add(
                binding
                    .activityCancelApplyBtn
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        Log.d("canceldd", it.toString())

                        if(ContainerActivity().networkValid.value == true) {
                            //신청 완료 api
                            var idInfo = -1
                            var programInfo: Int
                            var bankInfo = ""
                            val getToken = runBlocking {
                                idInfo = GaramgaebiApplication().loadIntData("memberIdx")!!
                                programInfo = GaramgaebiApplication().loadIntData("programIdx")!!
                                bankInfo = GaramgaebiApplication().loadStringData("bank").toString()
                            }

                            var accountInfo = binding.activityCancelPayEt.toString()

                            var canelRequest =
                                CancelRequest(idInfo, programInfo, bankInfo, accountInfo)
                            viewModel.postCancel(canelRequest)
                            Log.d("cancel11", canelRequest.toString())
                        }else{
                            (requireActivity() as ContainerActivity).networkAlertDialog()
                        }

                    }, { it.printStackTrace() })
            )

        //type에 따라 상세보기 뷰모델 달라짐!
        //세미나 상세보기 뷰모델로
        var seminar = ""
        val getSeminar = runBlocking {
            seminar = GaramgaebiApplication().loadStringData("type")!!
        }
        if(seminar=="SEMINAR"){
            if(ContainerActivity().networkValid.value == true) {
                viewModel.getSeminar()
            }else{
            }
            viewModel.seminarInfo.observe(viewLifecycleOwner, Observer{
                if(it.isSuccess) {
                    val data = it.result
                    with(binding) {
                        activityCancelTitleTv.text = data.title
                        activityCancelDateDetailTv.text = data.date
                        activityCancelPlaceDetailTv.text = data.location
                    }
                    if (data.fee.toString() == "0") {
                        binding.activityCancelPayDetailTv.text = "무료"
                    } else {
                        binding.activityCancelPayDetailTv.text =
                            getString(R.string.main_fee, data.fee.toString())
                    }
                    binding.activityCancelDeadlineDetailTv.text = data.endDate

                    //무료 프로그램일때 계좌랑 은행 선택하는 거 안보이게
                    if (data.fee.toString() == "0") {
                        with(binding) {
                            activityCancelBankTv.visibility = GONE
                            activityCancelBankDownBtn.visibility = GONE
                            activityCancelPayEt.visibility = GONE

                            //무료 버튼 활성화
                            activityCancelApplyBtn.isEnabled = true
                            activityCancelApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                        }

                    } else {
                        //유료 버튼활성화
                        isCharged()
                    }
                }else{
                }

            })
        }
        //네트워킹 상세보기 뷰모델로
        var networking = ""
        val getNetworking = runBlocking {
            networking = GaramgaebiApplication().loadStringData("type")!!
        }
        if(networking=="NETWORKING"){
            if((requireActivity() as ContainerActivity).networkValid.value == true) {
                viewModel.getNetworking()
            }else{
            }
            viewModel.networkingInfo.observe(viewLifecycleOwner) {
                if (it.isSuccess) {
                    val data = it.result
                    with(binding) {
                        activityCancelTitleTv.text = data.title
                        activityCancelDateDetailTv.text = data.date
                        activityCancelPlaceDetailTv.text = data.location
                    }
                    if (data.fee.toString() == "0") {
                        binding.activityCancelPayDetailTv.text = "무료"
                    } else {
                        binding.activityCancelPayDetailTv.text =
                            getString(R.string.main_fee, data.fee.toString())
                    }
                    binding.activityCancelDeadlineDetailTv.text = data.endDate

                    //무료 프로그램일때 계좌랑 은행 선택하는 거 안보이게
                    if (data.fee == 0) {
                        with(binding) {
                            activityCancelBankTv.visibility = GONE
                            activityCancelBankDownBtn.visibility = GONE
                            activityCancelPayEt.visibility = GONE

                            //무료 버튼 활성화
                            activityCancelApplyBtn.isEnabled = true
                            activityCancelApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                        }
                    } else {
                        //유료 버튼활성화
                        isCharged()
                    }
                } else {
                }

            }
        }
        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
            onShowKeyboard = { keyboardHeight ->
                binding.svRoot.run {
                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
                }
                binding.activityCancelApplyBtn.visibility = GONE
                binding.activityCancelApplyBtn.visibility = GONE

            },
            onHideKeyboard = { ->
                //binding.fragmentCareerSaveBtn.visibility = View.VISIBLE
            }
        )
        view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val rect = Rect()
                view.getWindowVisibleDisplayFrame(rect)

                val screenHeight = view.rootView.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight < screenHeight * 0.15) {
                    // 키보드가 완전히 내려갔음을 나타내는 동작을 구현합니다.
                    binding.activityCancelApplyBtn.postDelayed({
                        binding.activityCancelApplyBtn.visibility = View.VISIBLE
                    },0)
                }
            }
        })
    }

    private fun isBank(): Boolean {
        //val bank = intent.getBooleanExtra("bank", false)
        return true;
    }
    private fun isPay(): Boolean{
        var returnValue = false
        val pay = binding.activityCancelPayEt.text.toString()
        if(pay.length > 10) {
            returnValue = true;
        }
        return returnValue;
    }


    private fun isButton():Boolean {
        var returnValue = false
        if(isBank()&&isPay()){
            returnValue = true;
        }
        return returnValue

    }


    // 유료 프로그램일때 신청취소
    private fun isCharged(){
        //은행 선택하고 계좌번호 쓰면 버튼 활성화 됨
        binding.activityCancelPayEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(isButton()){
                    binding.activityCancelApplyBtn.isEnabled = true
                    binding.activityCancelApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                }
                else {
                    binding.activityCancelApplyBtn.isEnabled = false
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

    override fun onYesButtonClick(id: Int) {
        TODO("Not yet implemented")
    }

}
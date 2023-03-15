package com.garamgaebi.garamgaebi.src.main.cancel

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
import android.view.View.VISIBLE
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
        viewModel.getCancel()
        viewModel.cancelInfo.observe(viewLifecycleOwner, Observer{
            val data = it.result
            with(binding){
                activityCancelNameTv.text = data.name
                activityCancelNicknameTv.text = data.nickname
                activityCancelPhoneTv.text = data.phone
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
                GaramgaebiApplication.sSharedPreferences
                    .edit().putString("bank", it)
                    .apply()

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


                        //신청 완료 api
                        var idInfo = GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0)
                        var programInfo = GaramgaebiApplication.sSharedPreferences.getInt("programIdx", 0)
                        var bankInfo = ""
                        bankInfo = GaramgaebiApplication.sSharedPreferences.getString("bank", "").toString()
                        var accountInfo = binding.activityCancelPayEt.toString()

                        var canelRequest = CancelRequest(idInfo,programInfo,bankInfo,accountInfo)
                        viewModel.postCancel(canelRequest)
                        Log.d("cancel11",canelRequest.toString())


                    }, { it.printStackTrace() })
            )

        //type에 따라 상세보기 뷰모델 달라짐!
        //세미나 상세보기 뷰모델로
        if(GaramgaebiApplication.sSharedPreferences.getString("type", null)=="SEMINAR"){
            viewModel.getSeminar()
            viewModel.seminarInfo.observe(viewLifecycleOwner, Observer{
                val data = it.result
                with(binding){
                    activityCancelTitleTv.text = data.title
                    activityCancelDateDetailTv.text = data.date
                    activityCancelPlaceDetailTv.text = data.location
                }
                if(data.fee.toString() == "0"){
                    binding.activityCancelPayDetailTv.text = "무료"
                }
                else{
                    binding.activityCancelPayDetailTv.text = getString(R.string.main_fee, data.fee.toString())
                }
                binding.activityCancelDeadlineDetailTv.text = data.endDate

                //무료 프로그램일때 계좌랑 은행 선택하는 거 안보이게
                if(data.fee.toString() == "0"){
                    with(binding){
                        activityCancelBankTv.visibility = GONE
                        activityCancelBankDownBtn.visibility = GONE
                        activityCancelPayEt.visibility = GONE

                        //무료 버튼 활성화
                        activityCancelApplyBtn.isEnabled = true
                        activityCancelApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                    }

                }else {
                    //유료 버튼활성화
                    isCharged()
                }

            })
        }
        //네트워킹 상세보기 뷰모델로
        if(GaramgaebiApplication.sSharedPreferences.getString("type", null)=="NETWORKING"){
            viewModel.getNetworking()
            viewModel.networkingInfo.observe(viewLifecycleOwner, Observer{
                val data = it.result
                with(binding){
                    activityCancelTitleTv.text = data.title
                    activityCancelDateDetailTv.text = data.date
                    activityCancelPlaceDetailTv.text = data.location
                }
                if(data.fee.toString() == "0"){
                    binding.activityCancelPayDetailTv.text = "무료"
                }
                else{
                    binding.activityCancelPayDetailTv.text = getString(R.string.main_fee, data.fee.toString())
                }
                binding.activityCancelDeadlineDetailTv.text = data.endDate

                //무료 프로그램일때 계좌랑 은행 선택하는 거 안보이게
                if(data.fee == 0){
                    with(binding){
                        activityCancelBankTv.visibility = GONE
                        activityCancelBankDownBtn.visibility = GONE
                        activityCancelPayEt.visibility = GONE

                        //무료 버튼 활성화
                        activityCancelApplyBtn.isEnabled = true
                        activityCancelApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                    }
                }else {
                    //유료 버튼활성화
                    isCharged()
                }

            })
        }
        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
            onShowKeyboard = { keyboardHeight ->
//                binding.svRoot.run {
//                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
//                }
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
        return returnValue;

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
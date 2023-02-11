package com.example.template.garamgaebi.src.main.cancel

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseBindingFragment
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.databinding.FragmentCancelBinding
import com.example.template.garamgaebi.model.CancelRequest
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.viewModel.ApplyViewModel
import com.example.template.garamgaebi.viewModel.SeminarViewModel

class CancelFragment: BaseBindingFragment<FragmentCancelBinding>(R.layout.fragment_cancel) {

    //화면전환
    var containerActivity: ContainerActivity? = null
    private val viewModel by viewModels<ApplyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.activityCancelApplyBtn.isEnabled = false

        binding.activityCancelBankTv.text = "은행"

        binding.activityCancelNameTv.text = GaramgaebiApplication.sSharedPreferences.getString("inputName", null)
        binding.activityCancelNicknameTv.text = GaramgaebiApplication.sSharedPreferences.getString("inputNickName", null)
        binding.activityCancelPhoneTv.text = GaramgaebiApplication.sSharedPreferences.getString("inputPhone", null)

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

        //신청취소 버튼 누르면 다이얼로그 띄우기
        /*completeDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        completeDialog?.setContentView(R.layout.dialog_cancel_complete)*/

        binding.activityCancelApplyBtn.setOnClickListener {
            //신청 완료 api
            GaramgaebiApplication.sSharedPreferences.getString("bank", null)
                ?.let { it1 ->
                    CancelRequest(GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0),GaramgaebiApplication.sSharedPreferences.getInt("programIdx", 0),
                        it1, binding.activityCancelPayEt.toString())
                }?.let { it2 -> viewModel.postCancel(it2) }

            viewModel.cancel.observe(viewLifecycleOwner, Observer {
                Log.d("cancel", it.toString())
                if(it.isSuccess){
                    //showDialog()
                    activity?.let {
                        CancelCompleteDialog().show(
                            it.supportFragmentManager, "CancelCompleteDialog"
                        )
                    }
                    CancelCompleteDialog().dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                }

            })


        }

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

        //type에 따라 상세보기 뷰모델 달라짐!
        //세미나 상세보기 뷰모델로
        if(GaramgaebiApplication.sSharedPreferences.getString("type", null)=="SEMINAR"){
            viewModel.getSeminar()
            viewModel.seminarInfo.observe(viewLifecycleOwner, Observer{
                val data = it.result
                binding.activityCancelTitleTv.text = data.title
                binding.activityCancelDateDetailTv.text = data.date
                binding.activityCancelPlaceDetailTv.text = data.location
                if(data.fee.toString() == "0"){
                    binding.activityCancelPayDetailTv.text = "무료"
                }
                else{
                    binding.activityCancelPayDetailTv.text = getString(R.string.main_fee, data.fee.toString())
                }
                binding.activityCancelDeadlineDetailTv.text = data.endDate
            })
        }
        //네트워킹 상세보기 뷰모델로
        if(GaramgaebiApplication.sSharedPreferences.getString("type", null)=="NETWORKING"){
            viewModel.getNetworking()
            viewModel.networkingInfo.observe(viewLifecycleOwner, Observer{
                val data = it.result
                binding.activityCancelTitleTv.text = data.title
                binding.activityCancelDateDetailTv.text = data.date
                binding.activityCancelPlaceDetailTv.text = data.location
                if(data.fee.toString() == "0"){
                    binding.activityCancelPayDetailTv.text = "무료"
                }
                else{
                    binding.activityCancelPayDetailTv.text = getString(R.string.main_fee, data.fee.toString())
                }
                binding.activityCancelDeadlineDetailTv.text = data.endDate
            })
        }

        //이름, 닉네임, 전화번호
        /*viewModel.postEnroll()
        viewModel.enrollReq.observe(viewLifecycleOwner, Observer{

                binding.activityCancelNameTv.text = GaramgaebiApplication.sSharedPreferences.getString("inputName", null)
                binding.activityCancelNicknameTv.text = GaramgaebiApplication.sSharedPreferences.getString("inputNickName", null)
                binding.activityCancelPhoneTv.text = GaramgaebiApplication.sSharedPreferences.getString("inputPhone", null)

                /*binding.activityCancelNameTv.text = it.name
                binding.activityCancelNicknameTv.text = it.nickname
                binding.activityCancelPhoneTv.text = it.phone*/

        })*/
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

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

}
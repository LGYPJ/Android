package com.softsquared.template.Garamgaebi.src.main.cancel

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentCancelBinding
import com.softsquared.template.Garamgaebi.databinding.FragmentSeminarBinding
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import com.softsquared.template.Garamgaebi.src.seminar.SeminarPreviewDialog

class CancelFragment: BaseFragment<FragmentCancelBinding>(FragmentCancelBinding::bind, R.layout.fragment_cancel) {

    //화면전환
    var containerActivity: ContainerActivity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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
                binding.activityCancelBankTv.setTextColor(resources.getColor(R.color.black))
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
        binding.activityCancelApplyBtn.setOnClickListener {
            activity?.let {
                CancelCompleteDialog().show(
                    it.supportFragmentManager, "CancelCompleteDialog"
                )
            }
            CancelCompleteDialog().dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
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
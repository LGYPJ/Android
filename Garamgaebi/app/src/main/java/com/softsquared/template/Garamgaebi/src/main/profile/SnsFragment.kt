package com.softsquared.template.Garamgaebi.src.main.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentProfileSnsBinding

class SnsFragment  : BaseFragment<FragmentProfileSnsBinding>(FragmentProfileSnsBinding::bind, R.layout.fragment_profile_sns) {
    private lateinit var callback: OnBackPressedCallback
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //편집 정보 저장하기 버튼 클릭이벤트
        binding.activitySnsSaveBtn.setOnClickListener {
            if (checkInfo() == true){
                //경력 저장 기능 추가
            }else{
                //저장 불가 및 이유
            }
        }

        //회사 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activitySnsEtLinkDesc)

        //직함 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activitySnsEtName)

    }
    private fun checkDpInput(view:TextView){
        if (checkInfo()){
            binding.activitySnsSaveBtn.isClickable = true
            binding.activitySnsSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
        }else{
            binding.activitySnsSaveBtn.isClickable = false
            binding.activitySnsSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
        }
    }
    private fun checkEtInput(view: EditText) {
        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.register_et_border_selected)
            } else {
                view.setBackgroundResource(R.drawable.register_et_border)
            }
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkInfo()){
                    binding.activitySnsSaveBtn.isClickable = true
                    binding.activitySnsSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
                }else{
                    binding.activitySnsSaveBtn.isClickable = false
                    binding.activitySnsSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
                }
            }
        })

    }

    fun checkInfo() : Boolean{
        var  checkResult = true
        var link = binding.activitySnsEtLinkDesc.text.toString()
        var name = binding.activitySnsEtName.text.toString()


        //회사 조건 확인 기능
        if(link.length < 8) checkResult = false
        //직함 조건 확인 기능
        if(name.isEmpty()) {
            checkResult = false
        }
        return checkResult
    }
}

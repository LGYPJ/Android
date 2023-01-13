package com.softsquared.template.Garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View.OnFocusChangeListener
import android.view.View.VISIBLE
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityAuthenticationBinding
import java.util.regex.Pattern

class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>(
    ActivityAuthenticationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 이메일 발송 버튼 drawable, 활성화 여부
        binding.activityAuthenticationEtEmail.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(checkEmail()) {
                    binding.activityAuthenticationBtnEmail.setBackgroundResource(R.drawable.register_btn_color_enable)
                    binding.activityAuthenticationBtnEmail.isEnabled = true
                } else {
                    binding.activityAuthenticationBtnEmail.setBackgroundResource(R.drawable.register_btn_color)
                    binding.activityAuthenticationBtnEmail.isEnabled = false
                }
            }
        })


        // 이메일 et selected 여부에 따라 drawable 결정
        binding.activityAuthenticationEtEmail.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
            if(hasFocus) {
                view.setBackgroundResource(R.drawable.register_et_border_selected)
                binding.activityAuthenticationDomain.setTextColor(getColor(R.color.black))
            } else {
                view.setBackgroundResource(R.drawable.register_et_border)
            }
        }

        // 이메일 발송 버튼 클릭
        binding.activityAuthenticationBtnEmail.setOnClickListener {
            binding.activityAuthenticationBtnEmail.apply{
                setBackgroundResource(R.drawable.activity_auth_btn_send)
                setTextColor(getColor(R.color.blueForBtn))
                text = "이메일 재발송하기 (3분 00초)"
                binding.activityAuthenticationEtNum.visibility = VISIBLE
                binding.activityAuthenticationBtnNum.visibility = VISIBLE
            }
        }

        // 인증번호 버튼 drawable, 활성화 여부
        binding.activityAuthenticationEtNum.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.activityAuthenticationEtNum.text.length == 6) {
                    binding.activityAuthenticationBtnNum.setBackgroundResource(R.drawable.register_btn_color_enable)
                    binding.activityAuthenticationBtnNum.isEnabled = true
                } else {
                    binding.activityAuthenticationBtnNum.setBackgroundResource(R.drawable.register_btn_color)
                    binding.activityAuthenticationBtnNum.isEnabled = false
                }
            }
        })

        // 인증번호 버튼 클릭
        binding.activityAuthenticationBtnNum.setOnClickListener {
            startActivity(Intent(this, NicknameActivity::class.java))
        }
    }

    fun checkEmail() : Boolean{
        val emailValidation = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{5,10}$"
        var email = binding.activityAuthenticationEtEmail.text.toString()
        return Pattern.matches(emailValidation, email)
    }
}
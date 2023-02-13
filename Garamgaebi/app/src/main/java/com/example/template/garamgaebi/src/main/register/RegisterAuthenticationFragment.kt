package com.example.template.garamgaebi.src.main.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.View.VISIBLE
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseBindingFragment
import com.example.template.garamgaebi.common.REGISTER_NICKNAME
import com.example.template.garamgaebi.databinding.FragmentRegisterAuthenticationBinding
import java.util.regex.Pattern

class RegisterAuthenticationFragment :
    BaseBindingFragment<FragmentRegisterAuthenticationBinding>(R.layout.fragment_register_authentication) {
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이메일 발송 버튼 drawable, 활성화 여부
        binding.fragmentAuthenticationEtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkEmail()) {
                    binding.fragmentAuthenticationBtnEmail.setBackgroundResource(R.drawable.register_btn_color_enable)
                    binding.fragmentAuthenticationBtnEmail.isEnabled = true
                } else {
                    binding.fragmentAuthenticationBtnEmail.setBackgroundResource(R.drawable.register_btn_color)
                    binding.fragmentAuthenticationBtnEmail.isEnabled = false
                }
            }
        })


        // 이메일 et selected 여부에 따라 drawable 결정
        binding.fragmentAuthenticationEtEmail.onFocusChangeListener =
            OnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    view.setBackgroundResource(R.drawable.register_et_border_selected)
                    binding.fragmentAuthenticationDomain.setTextColor(registerActivity.getColor(R.color.black80))
                } else {
                    view.setBackgroundResource(R.drawable.register_et_border)
                }
            }

        // 이메일 발송 버튼 클릭
        binding.fragmentAuthenticationBtnEmail.setOnClickListener {
            binding.fragmentAuthenticationBtnEmail.apply {
                setBackgroundResource(R.drawable.fragment_auth_btn_send)
                setTextColor(registerActivity.getColor(R.color.blueForBtn))
                text = "이메일 재발송하기 (3분 00초)"
                binding.fragmentAuthenticationEtNum.visibility = VISIBLE
                binding.fragmentAuthenticationBtnNum.visibility = VISIBLE
            }
        }

        // 인증번호 버튼 drawable, 활성화 여부
        binding.fragmentAuthenticationEtNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (binding.fragmentAuthenticationEtNum.text.length == 6) {
                    binding.fragmentAuthenticationBtnNum.setBackgroundResource(R.drawable.register_btn_color_enable)
                    binding.fragmentAuthenticationBtnNum.isEnabled = true
                } else {
                    binding.fragmentAuthenticationBtnNum.setBackgroundResource(R.drawable.register_btn_color)
                    binding.fragmentAuthenticationBtnNum.isEnabled = false
                }
            }
        })

        // 인증번호 버튼 클릭
        binding.fragmentAuthenticationBtnNum.setOnClickListener {
            registerActivity.setFragment(REGISTER_NICKNAME)
        }
    }

    fun checkEmail(): Boolean {
        val emailValidation = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{1,20}$"
        var email = binding.fragmentAuthenticationEtEmail.text.toString()
        return Pattern.matches(emailValidation, email)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}


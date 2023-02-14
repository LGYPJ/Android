package com.example.template.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseBindingFragment
import com.example.template.garamgaebi.databinding.FragmentRegisterAuthenticationBinding
import com.example.template.garamgaebi.viewModel.EditTextViewModel
import com.example.template.garamgaebi.viewModel.RegisterViewModel
import java.util.regex.Pattern

class RegisterAuthenticationFragment :
    BaseBindingFragment<FragmentRegisterAuthenticationBinding>(R.layout.fragment_register_authentication) {
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val registerViewModel by viewModels<RegisterViewModel>()
        binding.registerViewModel = registerViewModel
        val editTextViewModel by viewModels<EditTextViewModel>()
        binding.editTextViewModel = editTextViewModel
        // 이메일 발송 버튼 drawable, 활성화 여부
        binding.fragmentAuthenticationEtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkEmail()) {
                    binding.fragmentAuthenticationBtnEmail.setBackgroundResource(R.drawable.register_btn_color_enable)
                    binding.registerViewModel.isEmailValid.value = true
                    Log.d("registerEmail", "isEmailValid ${registerViewModel.isEmailValid.value}")
                } else {
                    binding.fragmentAuthenticationBtnEmail.setBackgroundResource(R.drawable.register_btn_color)
                    binding.registerViewModel.isEmailValid.value = false
                    Log.d("registerEmail", "isEmailValid ${registerViewModel.isEmailValid.value}")
                }
            }
        })


        // 이메일 발송 버튼 클릭
        binding.fragmentAuthenticationBtnEmail.setOnClickListener {
            Log.d("registerEmail", "emailBtn")
            binding.fragmentAuthenticationBtnEmail.apply {

                setBackgroundResource(R.drawable.fragment_auth_btn_send)
                setTextColor(registerActivity.getColor(R.color.blueForBtn))
                registerViewModel.isEmailValid.value = false
                registerViewModel.postEmailConfirm(registerViewModel.email.value!!)
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
                    registerViewModel.isNumValid.value = true
                } else {
                    binding.fragmentAuthenticationBtnNum.setBackgroundResource(R.drawable.register_btn_color)
                    registerViewModel.isNumValid.value = false
                }
            }
        })

        // 인증번호 버튼 클릭
        binding.fragmentAuthenticationBtnNum.setOnClickListener {
            registerViewModel.emailConfirm.observe(viewLifecycleOwner, Observer{
                if(registerViewModel.authNum.value == it.result.key) {
                    registerViewModel.isCompleteAuth.value = true
                    registerViewModel.isNumValid.value = false
                    registerViewModel.isEmailValid.value = false
                    binding.fragmentAuthenticationEtEmail.isEnabled = false
                    binding.fragmentAuthenticationEtNum.isEnabled = false
                } else {
                    binding.fragmentAuthenticationEtNum.setBackgroundResource(R.drawable.basic_red_border_layout)
                    binding.fragmentAuthenticationTvNumAnn.visibility = VISIBLE
                }
            })
        }
    }

    fun checkEmail(): Boolean {
        val emailValidation = "^(?=.*[A-Za-z])(?=.*[0-9])[A-Za-z[0-9]]{5,20}$"
        var email = binding.fragmentAuthenticationEtEmail.text.toString()
        return Pattern.matches(emailValidation, email)
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}


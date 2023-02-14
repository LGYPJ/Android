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
import com.example.template.garamgaebi.BR
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseBindingFragment
import com.example.template.garamgaebi.databinding.FragmentRegisterAuthenticationBinding
import com.example.template.garamgaebi.viewModel.RegisterViewModel
import kotlinx.coroutines.*
import java.util.regex.Pattern

class RegisterAuthenticationFragment :
    BaseBindingFragment<FragmentRegisterAuthenticationBinding>(R.layout.fragment_register_authentication) {
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<RegisterViewModel>()
        binding.lifecycleOwner = this
        binding.setVariable(BR.registerViewModel, viewModel)

        viewModel.email.observe(viewLifecycleOwner, Observer {
            binding.registerViewModel = viewModel
            viewModel.isEmailValid.value = viewModel.checkEmail()
        })
        //// 이메일 발송 버튼 drawable, 활성화 여부
        //binding.fragmentAuthenticationEtEmail.addTextChangedListener(object : TextWatcher {
        //    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        //    override fun afterTextChanged(p0: Editable?) {}
        //    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //        if (checkEmail()) {
        //            binding.fragmentAuthenticationBtnEmail.setBackgroundResource(R.drawable.register_btn_color_enable)
        //            registerViewModel.isEmailValid.value = true
        //            binding.registerViewModel = registerViewModel
        //            Log.d("registerEmail", "isEmailValid ${registerViewModel.isEmailValid.value}")
        //        } else {
        //            binding.fragmentAuthenticationBtnEmail.setBackgroundResource(R.drawable.register_btn_color)
        //            registerViewModel.isEmailValid.value = false
        //            binding.registerViewModel = registerViewModel
        //            Log.d("registerEmail", "isEmailValid ${registerViewModel.isEmailValid.value}")
        //        }
        //    }
        //})
//
//
        // 이메일 발송 버튼 클릭
        binding.fragmentAuthenticationBtnEmail.setOnClickListener {
            Log.d("registerEmail", "emailBtn")
            binding.registerViewModel = viewModel
            viewModel.timerStart()
            binding.fragmentAuthenticationEtNum.visibility = VISIBLE
            binding.fragmentAuthenticationBtnNum.visibility = VISIBLE
        }
//
        //// 인증번호 버튼 drawable, 활성화 여부
        //binding.fragmentAuthenticationEtNum.addTextChangedListener(object : TextWatcher {
        //    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        //    override fun afterTextChanged(p0: Editable?) {}
        //    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //        if (binding.fragmentAuthenticationEtNum.text.length == 6) {
        //            binding.fragmentAuthenticationBtnNum.setBackgroundResource(R.drawable.register_btn_color_enable)
        //            registerViewModel.isNumValid.value = true
        //        } else {
        //            binding.fragmentAuthenticationBtnNum.setBackgroundResource(R.drawable.register_btn_color)
        //            registerViewModel.isNumValid.value = false
        //        }
        //    }
        //})
//
        //// 인증번호 버튼 클릭
        //binding.fragmentAuthenticationBtnNum.setOnClickListener {
        //    registerViewModel.emailConfirm.observe(viewLifecycleOwner, Observer{
        //        if(registerViewModel.authNum.value == it.result.key) {
        //            registerViewModel.isCompleteAuth.value = true
        //            registerViewModel.isNumValid.value = false
        //            registerViewModel.isEmailValid.value = false
        //            binding.fragmentAuthenticationEtEmail.isEnabled = false
        //            binding.fragmentAuthenticationEtNum.isEnabled = false
        //        } else {
        //            binding.fragmentAuthenticationEtNum.setBackgroundResource(R.drawable.basic_red_border_layout)
        //            binding.fragmentAuthenticationTvNumAnn.visibility = VISIBLE
        //        }
        //    })
        //}
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}


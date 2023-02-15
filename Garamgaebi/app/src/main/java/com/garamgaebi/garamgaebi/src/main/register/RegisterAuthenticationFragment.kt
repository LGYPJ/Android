package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.REGISTER_NICKNAME
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterAuthenticationBinding
import com.garamgaebi.garamgaebi.viewModel.RegisterViewModel
import kotlinx.coroutines.*

class RegisterAuthenticationFragment :
    BaseBindingFragment<FragmentRegisterAuthenticationBinding>(R.layout.fragment_register_authentication) {
    lateinit var registerActivity: RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<RegisterViewModel>()
        binding.lifecycleOwner = this
        binding.setVariable(BR.registerViewModel, viewModel)

        viewModel.uniEmail.observe(viewLifecycleOwner, Observer {
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
        with(binding) {
            fragmentAuthenticationBtnEmail.setOnClickListener {
                Log.d("registerEmail", "emailBtn")
                registerViewModel = viewModel
                viewModel.timerStart()
                //viewModel.postEmailConfirm(viewModel.getEmail(registerActivity))
                fragmentAuthenticationEtEmail.clearFocus()
                fragmentAuthenticationEtNum.clearFocus()
                fragmentAuthenticationEtNum.visibility = VISIBLE
                fragmentAuthenticationBtnNum.visibility = VISIBLE
            }
        }

        viewModel.authNum.observe(viewLifecycleOwner, Observer {
            binding.registerViewModel = viewModel
            viewModel.isNumValid.value = viewModel.checkAuthNum()
        })

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

        // 인증번호 버튼 클릭
        binding.fragmentAuthenticationBtnNum.setOnClickListener {
            binding.registerViewModel = viewModel
            with(viewModel) {
                Log.d("이메일 인증버튼", "이메일 인증버튼")
                //emailConfirm.observe(viewLifecycleOwner, Observer {
                    if (viewModel.authNum.value == "123456") {
                        Log.d("이메일 인증버튼", "이메일 인증버튼 완")
                        isCompleteAuth.value = true
                        isAuthWrong.value = false
                        timer.cancel()
                        isTimerRunning.value = false
                        with(binding) {
                            fragmentAuthenticationEtEmail.isEnabled = false
                            fragmentAuthenticationEtNum.isEnabled = false
                            fragmentAuthenticationBtnEmail.isEnabled = false
                            fragmentAuthenticationBtnNum.isEnabled = false
                        }

                    } else {
                        Log.d("이메일 인증버튼", "이메일 인증버튼 틀림")
                        isAuthWrong.value = true
                    }
                //})
            }
        }
        binding.fragmentAuthenticationBtnNext.setOnClickListener {
            registerActivity.setFragment(REGISTER_NICKNAME)
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}


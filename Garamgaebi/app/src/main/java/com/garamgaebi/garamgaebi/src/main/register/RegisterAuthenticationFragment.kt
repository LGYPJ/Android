package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.REGISTER_NICKNAME
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterAuthenticationBinding
import com.garamgaebi.garamgaebi.model.RegisterEmailVerifyRequest
import com.garamgaebi.garamgaebi.model.RegisterSendEmailRequest
import com.garamgaebi.garamgaebi.viewModel.RegisterViewModel
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class RegisterAuthenticationFragment :
    BaseBindingFragment<FragmentRegisterAuthenticationBinding>(R.layout.fragment_register_authentication) {
    lateinit var registerActivity: RegisterActivity
    val viewModel by activityViewModels<RegisterViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)

        // 이메일 editText
        viewModel.uniEmail.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.isEmailValid.value = viewModel.checkEmail()
        })
        // 인증번호 editText
        viewModel.authNum.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.isNumValid.value = viewModel.checkAuthNum()
        })

        with(viewModel) {
            // 인증번호 검사 api
            emailVerify.observe(viewLifecycleOwner, Observer {
                Log.d("registerEmailAuthBtnResult", "${it.isSuccess}")
                if (it.isSuccess) {
                    Log.d("이메일 인증버튼", "이메일 인증버튼 완")
                    isCompleteAuth.value = true
                    isAuthWrong.value = false
                    timer.cancel()
                    isTimerRunning.value = false
                    binding.fragmentAuthenticationEtEmail.clearFocus()
                    binding.fragmentAuthenticationEtNum.clearFocus()
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
            })
        }

        binding.fragmentAuthenticationBtnNext.setOnClickListener {
            registerActivity.setFragment(REGISTER_NICKNAME)
        }

        CompositeDisposable().apply {
            // 이메일 발송
            add(
                binding.fragmentAuthenticationBtnEmail.clicks()
                    .throttleFirst(2000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        binding.viewModel = viewModel
                        with(viewModel) {
                            timerStart()
                            emailSent.value = viewModel.getEmail(registerActivity)
                            Log.d("registerEmail", emailSent.value!!)
                            postSendEmail(RegisterSendEmailRequest(emailSent.value!!))
                        }
                        with(binding) {
                            fragmentAuthenticationEtEmail.clearFocus()
                            fragmentAuthenticationEtNum.clearFocus()
                            fragmentAuthenticationEtNum.visibility = VISIBLE
                            fragmentAuthenticationBtnNum.visibility = VISIBLE
                        }
                    }, { it.printStackTrace() })
            )
            // 인증번호 검사 api call
            add(
                binding.fragmentAuthenticationBtnNum.clicks()
                    .throttleFirst(2000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        binding.viewModel = viewModel
                        with(viewModel) {
                            Log.d("이메일 인증버튼", "이메일 인증버튼")
                            authNumSent.value = authNum.value
                            Log.d("registerEmailAuthBtn", "${emailSent.value!!} ${authNumSent.value!!}")
                            postEmailVerify(RegisterEmailVerifyRequest(emailSent.value!!, authNumSent.value!!))
                        }
                    }, { it.printStackTrace() })
            )
        }

        binding.containerLayout.setOnTouchListener(View.OnTouchListener { v, event ->
            hideKeyboard()
            false
        })

    }
    private fun hideKeyboard() {
        if (activity != null && requireActivity().currentFocus != null) {
            // 프래그먼트기 때문에 getActivity() 사용
            val inputManager: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                requireActivity().currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }

    override fun onResume() {
        viewModel.nickname.value = ""
        super.onResume()
    }
}


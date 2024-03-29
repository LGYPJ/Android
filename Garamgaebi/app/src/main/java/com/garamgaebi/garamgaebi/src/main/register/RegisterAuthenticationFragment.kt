package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.networkValid
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.testEmail
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.testPW
import com.garamgaebi.garamgaebi.common.KeyboardVisibilityUtils
import com.garamgaebi.garamgaebi.common.REGISTER_NICKNAME
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterAuthenticationBinding
import com.garamgaebi.garamgaebi.model.RegisterEmailVerifyRequest
import com.garamgaebi.garamgaebi.model.RegisterSendEmailRequest
import com.garamgaebi.garamgaebi.src.main.MainActivity
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
            // 이메일 인증번호 보내기
            sendEmail.observe(viewLifecycleOwner, Observer {
                if(it.isSuccess) {
                    Log.d("registerAuth", "isSuccess")
                    with(binding) {
                        fragmentAuthenticationEtEmail.clearFocus()
                        fragmentAuthenticationEtNum.clearFocus()
                        fragmentAuthenticationEtNum.visibility = VISIBLE
                        fragmentAuthenticationBtnNum.visibility = VISIBLE
                        isEmailDuplicated.value = false
                        timerStart()
                    }
                } else {
                    isEmailDuplicated.value = true
                    uniEmailDuplicated.value = uniEmail.value
                }
            })

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
                        // 런칭 심사 테스트용
                        if(viewModel.uniEmail.value == testEmail){
                            viewModel.emailSent.value = viewModel.getEmail(registerActivity)
                            Log.d("registerAuth", "isSuccess")
                            with(binding) {
                                fragmentAuthenticationEtEmail.clearFocus()
                                fragmentAuthenticationEtNum.clearFocus()
                                fragmentAuthenticationEtNum.visibility = VISIBLE
                                fragmentAuthenticationBtnNum.visibility = VISIBLE
                                viewModel!!.isEmailDuplicated.value = false
                                viewModel!!.timerStart()
                            }
                        } else { // 테스트 아닌 코드
                            if(networkValid.value == true) {
                                binding.viewModel = viewModel
                                with(viewModel) {
                                    emailSent.value = viewModel.getEmail(registerActivity)
                                    Log.d("registerEmail", emailSent.value!!)
                                    postSendEmail(RegisterSendEmailRequest(emailSent.value!!))
                                }
                            } else {
                                (requireActivity() as RegisterActivity).networkAlertDialog()
                            }
                        }
                    }, { it.printStackTrace() })
            )
            // 인증번호 검사 api call
            add(
                binding.fragmentAuthenticationBtnNum.clicks()
                    .throttleFirst(2000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        // 런칭 심사 테스트용
                        if(viewModel.emailSent.value == "$testEmail@gachon.ac.kr") {
                            Log.d("emailTest", testEmail)
                            if(viewModel.authNum.value == testPW) {
                                Log.d("emailTest", testPW)
                                viewModel.authNumSent.value = testPW
                                Log.d("이메일 인증버튼", "이메일 인증버튼 완")
                                viewModel.isCompleteAuth.value = true
                                viewModel.isAuthWrong.value = false
                                viewModel.timer.cancel()
                                viewModel.isTimerRunning.value = false
                                binding.fragmentAuthenticationEtEmail.clearFocus()
                                binding.fragmentAuthenticationEtNum.clearFocus()
                                with(binding) {
                                    fragmentAuthenticationEtEmail.isEnabled = false
                                    fragmentAuthenticationEtNum.isEnabled = false
                                    fragmentAuthenticationBtnEmail.isEnabled = false
                                    fragmentAuthenticationBtnNum.isEnabled = false
                                }
                            }
                        } else { // 테스트 아닌 코드
                            if(networkValid.value == true) {
                                binding.viewModel = viewModel
                                with(viewModel) {
                                    Log.d("이메일 인증버튼", "이메일 인증버튼")
                                    authNumSent.value = authNum.value
                                    Log.d("registerEmailAuthBtn", "${emailSent.value!!} ${authNumSent.value!!}")
                                    postEmailVerify(RegisterEmailVerifyRequest(emailSent.value!!, authNumSent.value!!))
                                }
                            } else {
                                (requireActivity() as RegisterActivity).networkAlertDialog()
                            }
                        }


                    }, { it.printStackTrace() })
            )
        }

        binding.containerLayout.setOnTouchListener(View.OnTouchListener { v, event ->
            hideKeyboard()
            false
        })
        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
            onShowKeyboard = { keyboardHeight ->
                binding.svRoot.run {
                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
                }
                binding.fragmentAuthenticationBtnNext.visibility = View.GONE
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
                    binding.fragmentAuthenticationBtnNext.postDelayed({
                        binding.fragmentAuthenticationBtnNext.visibility = View.VISIBLE
                    },0)
                }
            }
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


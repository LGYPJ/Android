package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterCompleteBinding
import com.garamgaebi.garamgaebi.model.LoginRequest
import com.garamgaebi.garamgaebi.src.main.MainActivity
import com.garamgaebi.garamgaebi.src.main.home.RegisterTermDialog
import com.garamgaebi.garamgaebi.viewModel.CareerViewModel
import com.garamgaebi.garamgaebi.viewModel.EducationViewModel
import com.garamgaebi.garamgaebi.viewModel.HomeViewModel
import com.garamgaebi.garamgaebi.viewModel.RegisterViewModel
import com.jakewharton.rxbinding4.view.clicks
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit

class RegisterCompleteFragment : BaseFragment<FragmentRegisterCompleteBinding>
    (FragmentRegisterCompleteBinding::bind, R.layout.fragment_register_complete){
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val registerViewModel by activityViewModels<RegisterViewModel>()
        val homeViewModel by activityViewModels<HomeViewModel>()

        Log.d("registerComplete", "${registerViewModel.nickname.value}")
        if (registerViewModel.nickname.value!!.length > 4) {
            binding.fragmentCompleteTvDesc.text =
                "${registerViewModel.nickname.value},\n${getString(R.string.register_welcome_user)}"
        } else {
            binding.fragmentCompleteTvDesc.text =
                "${registerViewModel.nickname.value}, ${getString(R.string.register_welcome_user)}"
        }

        binding.fragmentCompleteCbPersonal.setOnClickListener {
            checkAgree()
        }
        binding.fragmentCompleteTvPersonal.setOnClickListener {
            checkTvAgree()
        }
        CompositeDisposable()
            .add(
                binding.fragmentCompleteBtnNext.clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        registerViewModel.postRegister(registerViewModel.getRegisterRequest())
                        registerViewModel.register.observe(viewLifecycleOwner, Observer { registerIt ->
                            if(registerIt.isSuccess) {
                                GaramgaebiApplication.myMemberIdx = registerIt.result.memberIdx
                                // 교육 or 경력
                                if(GaramgaebiApplication.sSharedPreferences.getBoolean("isCareer", false)){
                                    val viewModel by activityViewModels<CareerViewModel>()
                                    viewModel.postCareerInfo()
                                } else {
                                    val viewModel by activityViewModels<EducationViewModel>()
                                    viewModel.postEducationInfo()
                                }
                                // 로그인
                                Log.d("firebaseTokenInRegister", "${GaramgaebiApplication.sSharedPreferences.getString("pushToken", "")!!}")
                                homeViewModel.postLogin(LoginRequest(registerViewModel.socialEmail.value!!,
                                    GaramgaebiApplication.sSharedPreferences.getString("pushToken", "")!!))

                                homeViewModel.login.observe(viewLifecycleOwner, Observer { homeIt ->
                                    if(homeIt.isSuccess) {
                                        GaramgaebiApplication.sSharedPreferences.edit()
                                            .putString(GaramgaebiApplication.X_ACCESS_TOKEN, homeIt.result.accessToken)
                                            .putString(GaramgaebiApplication.X_REFRESH_TOKEN, homeIt.result.refreshToken)
                                            .putInt("memberIdx", homeIt.result.memberIdx)
                                            .putString("socialEmail", registerViewModel.socialEmail.value)
                                            .apply()
                                        GaramgaebiApplication.myMemberIdx = homeIt.result.memberIdx
                                        startActivity(Intent(registerActivity, MainActivity::class.java))
                                        ActivityCompat.finishAffinity(registerActivity)
                                    } else {
                                        Log.d("register", "login fail ${homeIt.errorMessage}")
                                    }
                                })

                            }
                        })
                    }, { it.printStackTrace() })
            )
    }
    private fun checkTvAgree() {
        if(binding.fragmentCompleteCbPersonal.isChecked){
            binding.fragmentCompleteBtnNext.isEnabled = false
            binding.fragmentCompleteCbPersonal.isChecked = false
            binding.fragmentCompleteBtnNext.setBackgroundResource(R.drawable.register_btn_color)
        } else {
            binding.fragmentCompleteBtnNext.isEnabled = true
            binding.fragmentCompleteCbPersonal.isChecked = true
            binding.fragmentCompleteBtnNext.setBackgroundResource(R.drawable.register_btn_color_enable)
            showWebViewDialog()
        }
    }
    private fun checkAgree() {
        if(binding.fragmentCompleteCbPersonal.isChecked){
            binding.fragmentCompleteBtnNext.isEnabled = true
            binding.fragmentCompleteBtnNext.setBackgroundResource(R.drawable.register_btn_color_enable)
            showWebViewDialog()
        } else {
            binding.fragmentCompleteBtnNext.isEnabled = false
            binding.fragmentCompleteBtnNext.setBackgroundResource(R.drawable.register_btn_color)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
    private fun showWebViewDialog() {
        RegisterTermDialog().show(parentFragmentManager, "RegisterTermDialog")
        /*val webView = WebView(requireContext()).apply {
            loadUrl("file:///android_asset/terms.html")
            webViewClient = WebViewClient()
        }
        AlertDialog.Builder(requireContext())
            .setTitle("가람개비 이용약관")
            .setView(webView)
            .show()*/
    }
}
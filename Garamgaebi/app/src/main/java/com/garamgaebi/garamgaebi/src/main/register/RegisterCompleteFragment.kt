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
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.networkValid
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
                "${registerViewModel.nickname.value}${getString(R.string.register_welcome_user_new_line)}"
        } else {
            binding.fragmentCompleteTvDesc.text =
                "${registerViewModel.nickname.value}${getString(R.string.register_welcome_user)}"
        }

        binding.fragmentCompleteCbPersonal.setOnClickListener {
            checkAgree()
        }
        binding.fragmentCompleteTvPersonal.setOnClickListener {
            checkTvAgree()
        }
        registerViewModel.register.observe(viewLifecycleOwner, Observer { registerIt ->
            if(registerIt.isSuccess) {
                GaramgaebiApplication.myMemberIdx = registerIt.result.memberIdx
                // 교육 or 경력
                var isCareer = false
                CoroutineScope(Dispatchers.Main).launch {
                    isCareer =
                        GaramgaebiApplication().loadBooleanData("isCareer") == true
                    Log.d("뭐묘?",isCareer.toString())
                    if (isCareer) {
                        val viewModel by activityViewModels<CareerViewModel>()
                        viewModel.postCareerInfo()
                    } else {
                        val viewModel by activityViewModels<EducationViewModel>()
                        viewModel.postEducationInfo()
                    }
                }
                // 로그인
                //  Log.d("firebaseTokenInRegister", "${GaramgaebiApplication.sSharedPreferences.getString("pushToken", "")!!}")
                var token = ""
                CoroutineScope(Dispatchers.Main).launch {
                    token = GaramgaebiApplication().loadStringData("pushToken").toString()
                    homeViewModel.postLogin(LoginRequest(registerViewModel.socialToken.value!!,
                        token))
                }


                homeViewModel.login.observe(viewLifecycleOwner, Observer { homeIt ->
                    if(homeIt.isSuccess) {
                        CoroutineScope(Dispatchers.Main).launch {
                            GaramgaebiApplication().saveStringToDataStore("kakaoToken",
                                registerViewModel.socialToken.value!!
                            )
                            GaramgaebiApplication().saveStringToDataStore(GaramgaebiApplication.X_REFRESH_TOKEN,homeIt.result.tokenInfo.accessToken)
                            GaramgaebiApplication().saveStringToDataStore(GaramgaebiApplication.X_REFRESH_TOKEN,homeIt.result.tokenInfo.refreshToken)
                            GaramgaebiApplication().saveIntToDataStore("memberIdx", homeIt.result.tokenInfo.memberIdx)
                            GaramgaebiApplication().saveBooleanToDataStore("fromLoginActivity",false)
                            GaramgaebiApplication().saveStringToDataStore("uniEmail",homeIt.result.uniEmail)
                            GaramgaebiApplication.myMemberIdx = homeIt.result.tokenInfo.memberIdx
                            startActivity(Intent(registerActivity, MainActivity::class.java))
                            ActivityCompat.finishAffinity(registerActivity)
                        }
                    } else {
                        Log.d("register", "login fail ${homeIt.errorMessage}")
                    }
                })

            }
        })
        CompositeDisposable()
            .add(
                binding.fragmentCompleteBtnNext.clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if(networkValid.value == false) {
                            (requireActivity() as RegisterActivity).networkAlertDialog()
                        } else {
                            registerViewModel.postRegister(registerViewModel.getRegisterRequest())
                        }
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
            showTermDialog()
        }
    }
    private fun checkAgree() {
        if(binding.fragmentCompleteCbPersonal.isChecked){
            binding.fragmentCompleteBtnNext.isEnabled = true
            binding.fragmentCompleteBtnNext.setBackgroundResource(R.drawable.register_btn_color_enable)
            showTermDialog()
        } else {
            binding.fragmentCompleteBtnNext.isEnabled = false
            binding.fragmentCompleteBtnNext.setBackgroundResource(R.drawable.register_btn_color)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
    private fun showTermDialog() {
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
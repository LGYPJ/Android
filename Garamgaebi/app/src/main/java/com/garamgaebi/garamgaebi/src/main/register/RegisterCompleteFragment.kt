package com.garamgaebi.garamgaebi.src.main.register

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.DialogRegisterTermBinding
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterCompleteBinding
import com.garamgaebi.garamgaebi.src.main.MainActivity
import com.garamgaebi.garamgaebi.src.main.home.RegisterTermDialog
import com.garamgaebi.garamgaebi.viewModel.CareerViewModel
import com.garamgaebi.garamgaebi.viewModel.EducationViewModel
import com.garamgaebi.garamgaebi.viewModel.RegisterViewModel

class RegisterCompleteFragment : BaseFragment<FragmentRegisterCompleteBinding>
    (FragmentRegisterCompleteBinding::bind, R.layout.fragment_register_complete){
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val registerViewModel by viewModels<RegisterViewModel>()

        if (registerViewModel.nickname.value!!.length > 4) {
            binding.fragmentCompleteTvDesc.text =
                registerViewModel.nickname.value+",\n"+getString(R.string.register_welcome_user)
        } else {
            binding.fragmentCompleteTvDesc.text =
                registerViewModel.nickname.value+","+getString(R.string.register_welcome_user)
        }

        binding.fragmentCompleteCbPersonal.setOnClickListener {
            checkAgree()
        }
        binding.fragmentCompleteTvPersonal.setOnClickListener {
            checkTvAgree()
        }
        binding.fragmentCompleteBtnNext.setOnClickListener {
            /*registerViewModel.postRegister(registerViewModel.getRegisterRequest())
            registerViewModel.register.observe(viewLifecycleOwner, Observer {
                if(it.isSuccess) {
                    GaramgaebiApplication.myMemberIdx = it.result.memberIdx
                    if(GaramgaebiApplication.sSharedPreferences.getBoolean("isCareer", false)){
                        val viewModel by viewModels<CareerViewModel>()
                        viewModel.postCareerInfo()
                    } else {
                        val viewModel by viewModels<EducationViewModel>()
                        viewModel.postEducationInfo()
                    }*/
                    startActivity(Intent(registerActivity, MainActivity::class.java))
                    ActivityCompat.finishAffinity(registerActivity)
            //    }
            //})

        }

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
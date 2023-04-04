package com.garamgaebi.garamgaebi.src.main.register

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.ActivityRegisterBinding
import com.garamgaebi.garamgaebi.viewModel.RegisterViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  Log.d("kakao", "${intent.getBooleanExtra("login", false)}")
        if(intent.getBooleanExtra("login", false)){
            val viewModel by viewModels<RegisterViewModel>()
            viewModel.socialToken.value = intent.getStringExtra("kakaoToken")
            setFragment(REGISTER_AUTH)
        }
        else {
            setFragment(REGISTER_INTRO)
            //테스트용
            //setFragment(REGISTER_AUTH)
            //setFragment(REGISTER_NICKNAME)
            //setFragment(REGISTER_EMAIL)
            //setFragment(REGISTER_ORG)
            //setFragment(REGISTER_COMPLETE)
        }
        Log.d("register","onCreate")

    }
    fun setFragment(int : Int) {
        val trans = supportFragmentManager.beginTransaction()
        when(int) {
            REGISTER_INTRO -> {
                trans.replace(R.id.activity_register_frm, RegisterIntroFragment())
            }
            /*REGISTER_LOGIN -> {
                trans.replace(R.id.activity_register_frm, RegisterLoginFragment()).addToBackStack(null)
            }*/
            REGISTER_AUTH -> {
                trans.replace(R.id.activity_register_frm, RegisterAuthenticationFragment())
            }
            REGISTER_NICKNAME -> {
                trans.replace(R.id.activity_register_frm, RegisterNicknameFragment()).addToBackStack(null)
            }
            REGISTER_EMAIL -> {
                trans.replace(R.id.activity_register_frm, RegisterEmailFragment()).addToBackStack(null)
            }
            REGISTER_ORG -> {
                trans.replace(R.id.activity_register_frm, RegisterOrganizationFragment()).addToBackStack(null)
            }
            REGISTER_EDU -> {
                trans.replace(R.id.activity_register_frm, RegisterEducationFragment()).addToBackStack(null)
            }
            REGISTER_CAREER -> {
                trans.replace(R.id.activity_register_frm, RegisterCareerFragment()).addToBackStack(null)
            }
            REGISTER_COMPLETE -> {
                trans.replace(R.id.activity_register_frm, RegisterCompleteFragment()).addToBackStack(null)
            }
        }
        trans.commitAllowingStateLoss()
    }
}
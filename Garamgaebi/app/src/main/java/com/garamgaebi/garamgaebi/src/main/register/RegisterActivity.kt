package com.garamgaebi.garamgaebi.src.main.register

import android.os.Bundle
import android.util.Log
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.ActivityRegisterBinding

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("kakao", "${intent.getBooleanExtra("login", false)}")
        if(intent.getBooleanExtra("login", false))
            setFragment(REGISTER_AUTH)
        else
            setFragment(REGISTER_INTRO)

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
                trans.replace(R.id.activity_register_frm, RegisterAuthenticationFragment()).addToBackStack(null)
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
                Log.d("eduactivity","edu")
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
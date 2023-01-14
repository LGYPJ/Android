package com.softsquared.template.Garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activityLoginKakao.setOnClickListener(){
            startActivity(Intent(this, AuthenticationActivity::class.java))
        }
    }
}
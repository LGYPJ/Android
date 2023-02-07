package com.example.template.garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import com.example.template.garamgaebi.common.BaseActivity
import com.example.template.garamgaebi.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activityLoginKakao.setOnClickListener(){
            startActivity(Intent(this, AuthenticationActivity::class.java))
        }
    }
}
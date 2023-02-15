package com.example.template.garamgaebi.src.splash

import android.content.Intent
import android.os.Bundle
import com.example.template.garamgaebi.common.BaseActivity
import com.example.template.garamgaebi.databinding.ActivitySplashBinding
import com.example.template.garamgaebi.src.main.MainActivity
import com.example.template.garamgaebi.src.main.register.RegisterActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this,  MainActivity::class.java))
            finish()
        }, 1500)*/

        CoroutineScope(Dispatchers.Default).launch {
            launch {
                delay(1500)
                startActivity(Intent(this@SplashActivity, RegisterActivity::class.java))
                finish()
            }
        }
    }
}
package com.garamgaebi.garamgaebi.src.splash

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateInterpolator
import com.garamgaebi.garamgaebi.common.BaseActivity
import com.garamgaebi.garamgaebi.databinding.ActivitySplashBinding
import com.garamgaebi.garamgaebi.src.main.MainActivity
import com.garamgaebi.garamgaebi.src.main.register.RegisterActivity
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

        var logo = binding.logo

        fun imageViewRotate(){
            val currentDegree = logo.rotation
            var anim = ObjectAnimator.ofFloat(logo,View.ROTATION, currentDegree, currentDegree+720f)
            anim.interpolator = AccelerateInterpolator()
            anim.duration = 2000

            anim.start()
        }
        imageViewRotate()


        CoroutineScope(Dispatchers.Main).launch {
            launch {
                delay(2000)
                startActivity(Intent(this@SplashActivity, RegisterActivity::class.java))
                finish()
            }
        }
    }
}
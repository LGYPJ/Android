package com.example.template.garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import androidx.core.app.ActivityCompat
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseActivity
import com.example.template.garamgaebi.databinding.ActivityCompleteBinding
import com.example.template.garamgaebi.src.main.MainActivity

class CompleteActivity : BaseActivity<ActivityCompleteBinding>(ActivityCompleteBinding::inflate){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activityCompleteCbWhole.setOnClickListener {
            if(binding.activityCompleteCbWhole.isChecked){
                binding.activityCompleteCbPersonal.isChecked = true
                binding.activityCompleteCbOld.isChecked = true
            } else {
                binding.activityCompleteCbPersonal.isChecked = false
                binding.activityCompleteCbOld.isChecked = false
            }
            checkAgree()
        }
        binding.activityCompleteCbPersonal.setOnClickListener {
            checkAgree()
        }
        binding.activityCompleteCbOld.setOnClickListener {
            checkAgree()
        }
        binding.activityCompleteBtnNext.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            ActivityCompat.finishAffinity(this)
        }

    }
    private fun checkAgree() {
        if(binding.activityCompleteCbPersonal.isChecked && binding.activityCompleteCbOld.isChecked){
            binding.activityCompleteBtnNext.isEnabled = true
            binding.activityCompleteBtnNext.setBackgroundResource(R.drawable.register_btn_color_enable)
        } else {
            binding.activityCompleteBtnNext.isEnabled = false
            binding.activityCompleteBtnNext.setBackgroundResource(R.drawable.register_btn_color)
        }
    }
}
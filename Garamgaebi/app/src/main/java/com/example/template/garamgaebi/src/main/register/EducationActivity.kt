package com.example.template.garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.config.BaseActivity
import com.example.template.garamgaebi.databinding.ActivityEducationBinding

class EducationActivity : BaseActivity<ActivityEducationBinding>(ActivityEducationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 이메일 et selected 여부에 따라 drawable 결정
        checkFocus(binding.activityEducationEtInstitution)
        checkFocus(binding.activityEducationEtMajor)

        binding.activityEducationBtnNext.setOnClickListener {
            startActivity(Intent(this, CompleteActivity::class.java))
        }
        binding.activityEducationTvCareer.setOnClickListener {
            startActivity(Intent(this, CareerActivity::class.java))
            finish()
        }
    }
    private fun checkFocus(view : View) {
        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.register_et_border_selected)
            } else {
                view.setBackgroundResource(R.drawable.register_et_border)
            }
        }
    }
}
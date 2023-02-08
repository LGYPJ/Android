package com.example.template.garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseActivity
import com.example.template.garamgaebi.databinding.ActivityCareerBinding

class CareerActivity : BaseActivity<ActivityCareerBinding>(ActivityCareerBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 이메일 et selected 여부에 따라 drawable 결정
        checkFocus(binding.activityCareerEtCompany)
        checkFocus(binding.activityCareerEtRank)
        checkFocus(binding.activityCareerEtStartDate)
        checkFocus(binding.activityCareerEtEndDate)

        binding.activityCareerBtnNext.setOnClickListener {
            startActivity(Intent(this, CompleteActivity::class.java))
        }
        binding.activityCareerTvEducation.setOnClickListener {
            startActivity(Intent(this, EducationActivity::class.java))
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
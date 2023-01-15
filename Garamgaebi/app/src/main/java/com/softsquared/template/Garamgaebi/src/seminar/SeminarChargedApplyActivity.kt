package com.softsquared.template.Garamgaebi.src.seminar

import android.content.Intent
import android.os.Bundle
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivitySeminarChargedApplyBinding

class SeminarChargedApplyActivity : BaseActivity<ActivitySeminarChargedApplyBinding>(ActivitySeminarChargedApplyBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activitySeminarChargedBackBtn.setOnClickListener {
            startActivity(Intent(this@SeminarChargedApplyActivity, SeminarFreeActivity::class.java ))
        }

    }
}
package com.softsquared.template.Garamgaebi.src.seminar


import android.os.Bundle
import android.content.Intent
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivitySeminarFreeApplyBinding


class SeminarFreeApplyActivity : BaseActivity<ActivitySeminarFreeApplyBinding>(ActivitySeminarFreeApplyBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activitySeminarFreeBackBtn.setOnClickListener {
            startActivity(Intent(this@SeminarFreeApplyActivity, SeminarFreeActivity::class.java ))
        }
    }
}
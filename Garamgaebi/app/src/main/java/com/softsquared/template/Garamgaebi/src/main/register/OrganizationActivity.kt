package com.softsquared.template.Garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityOrganizationBinding

class OrganizationActivity : BaseActivity<ActivityOrganizationBinding>(ActivityOrganizationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var flag = true
        // career 클릭
        binding.activityOrganizationBtnCareer.setOnClickListener {
            // career
            binding.activityOrganizationBtnCareer.setBackgroundResource(R.drawable.activity_organization_btn_selected)
            binding.activityOrganizationIvCareer.setImageResource(R.drawable.ic_activity_organization_career)
            binding.activityOrganizationTvCareer.setTextColor(getColor(R.color.black80))
            // edu
            binding.activityOrganizationBtnEdu.setBackgroundResource(R.drawable.register_et_border)
            binding.activityOrganizationIvEdu.setImageResource(R.drawable.ic_activity_organization_edu_unselected)
            binding.activityOrganizationTvEdu.setTextColor(getColor(R.color.grayD9))

            flag = true
            binding.activityOrganizationBtnNext.isEnabled = true
            binding.activityOrganizationBtnNext.setBackgroundResource(R.drawable.register_btn_color_enable)
        }
        // edu 클릭
        binding.activityOrganizationBtnEdu.setOnClickListener {
            // edu
            binding.activityOrganizationBtnEdu.setBackgroundResource(R.drawable.activity_organization_btn_selected)
            binding.activityOrganizationIvEdu.setImageResource(R.drawable.ic_activity_organization_edu)
            binding.activityOrganizationTvEdu.setTextColor(getColor(R.color.black80))
            // career
            binding.activityOrganizationBtnCareer.setBackgroundResource(R.drawable.register_et_border)
            binding.activityOrganizationIvCareer.setImageResource(R.drawable.ic_activity_organization_edu_unselected)
            binding.activityOrganizationTvCareer.setTextColor(getColor(R.color.grayD9))

            flag = false
            binding.activityOrganizationBtnNext.isEnabled = true
            binding.activityOrganizationBtnNext.setBackgroundResource(R.drawable.register_btn_color_enable)
        }

        binding.activityOrganizationBtnNext.setOnClickListener {
            if(flag) {
                startActivity(Intent(this,CareerActivity::class.java))
            } else {
                startActivity(Intent(this,EducationActivity::class.java))
            }

        }
    }
}
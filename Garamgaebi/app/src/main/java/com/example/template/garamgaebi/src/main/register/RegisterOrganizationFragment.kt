package com.example.template.garamgaebi.src.main.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseFragment
import com.example.template.garamgaebi.common.REGISTER_CAREER
import com.example.template.garamgaebi.common.REGISTER_EDU
import com.example.template.garamgaebi.databinding.FragmentRegisterOrganizationBinding

class RegisterOrganizationFragment : BaseFragment<FragmentRegisterOrganizationBinding>
    (FragmentRegisterOrganizationBinding::bind, R.layout.fragment_register_organization) {
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var flag = true
        // career 클릭
        binding.fragmentOrganizationBtnCareer.setOnClickListener {
            // career
            binding.fragmentOrganizationBtnCareer.setBackgroundResource(R.drawable.fragment_organization_btn_selected)
            binding.fragmentOrganizationIvCareer.setImageResource(R.drawable.ic_fragment_organization_career)
            binding.fragmentOrganizationTvCareer.setTextColor(registerActivity.getColor(R.color.black80))
            // edu
            binding.fragmentOrganizationBtnEdu.setBackgroundResource(R.drawable.register_et_border)
            binding.fragmentOrganizationIvEdu.setImageResource(R.drawable.ic_fragment_organization_edu_unselected)
            binding.fragmentOrganizationTvEdu.setTextColor(registerActivity.getColor(R.color.grayD9))

            flag = true
            binding.fragmentOrganizationBtnNext.isEnabled = true
            binding.fragmentOrganizationBtnNext.setBackgroundResource(R.drawable.register_btn_color_enable)
        }
        // edu 클릭
        binding.fragmentOrganizationBtnEdu.setOnClickListener {
            // edu
            binding.fragmentOrganizationBtnEdu.setBackgroundResource(R.drawable.fragment_organization_btn_selected)
            binding.fragmentOrganizationIvEdu.setImageResource(R.drawable.ic_fragment_organization_edu)
            binding.fragmentOrganizationTvEdu.setTextColor(registerActivity.getColor(R.color.black80))
            // career
            binding.fragmentOrganizationBtnCareer.setBackgroundResource(R.drawable.register_et_border)
            binding.fragmentOrganizationIvCareer.setImageResource(R.drawable.ic_fragment_organization_edu_unselected)
            binding.fragmentOrganizationTvCareer.setTextColor(registerActivity.getColor(R.color.grayD9))

            flag = false
            binding.fragmentOrganizationBtnNext.isEnabled = true
            binding.fragmentOrganizationBtnNext.setBackgroundResource(R.drawable.register_btn_color_enable)
        }

        binding.fragmentOrganizationBtnNext.setOnClickListener {
            if(flag) {
                registerActivity.setFragment(REGISTER_CAREER)
            } else {
                registerActivity.setFragment(REGISTER_EDU)
            }

        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}
package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.view.View
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.REGISTER_CAREER
import com.garamgaebi.garamgaebi.common.REGISTER_EDU
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterOrganizationBinding

class RegisterOrganizationFragment : BaseFragment<FragmentRegisterOrganizationBinding>
    (FragmentRegisterOrganizationBinding::bind, R.layout.fragment_register_organization) {
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var flag = true
        // career 클릭
        with(binding) {
            fragmentOrganizationBtnCareer.setOnClickListener {
                // career
                fragmentOrganizationBtnCareer.setBackgroundResource(R.drawable.fragment_organization_btn_selected)
                // edu
                fragmentOrganizationBtnEdu.setBackgroundResource(R.drawable.register_et_border)

                flag = true
                fragmentOrganizationBtnNext.isEnabled = true
                fragmentOrganizationBtnNext.setBackgroundResource(R.drawable.register_btn_color_enable)
            }
            // edu 클릭
            binding.fragmentOrganizationBtnEdu.setOnClickListener {
                // edu
                fragmentOrganizationBtnEdu.setBackgroundResource(R.drawable.fragment_organization_btn_selected)
                // career
                fragmentOrganizationBtnCareer.setBackgroundResource(R.drawable.register_et_border)

                flag = false
                fragmentOrganizationBtnNext.isEnabled = true
                fragmentOrganizationBtnNext.setBackgroundResource(R.drawable.register_btn_color_enable)
            }

            fragmentOrganizationBtnNext.setOnClickListener {
                if(flag) {
                    registerActivity.setFragment(REGISTER_CAREER)
                } else {
                    registerActivity.setFragment(REGISTER_EDU)
                }

            }
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}
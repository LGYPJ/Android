package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.REGISTER_CAREER
import com.garamgaebi.garamgaebi.common.REGISTER_EDU
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterOrganizationBinding
import com.garamgaebi.garamgaebi.viewModel.CareerViewModel
import com.garamgaebi.garamgaebi.viewModel.EducationViewModel

class RegisterOrganizationFragment : BaseFragment<FragmentRegisterOrganizationBinding>
    (FragmentRegisterOrganizationBinding::bind, R.layout.fragment_register_organization) {
    lateinit var registerActivity : RegisterActivity
    val careerViewModel by activityViewModels<CareerViewModel>()
    val eduViewModel by activityViewModels<EducationViewModel>()
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

    override fun onResume() {
        with(careerViewModel) {
            companyFirst.value = true
            positionFirst.value = true
            startFirst.value = true
            endFirst.value = true
            company.value = ""
            position.value = ""
            startDate.value = ""
            endDate.value = ""
        }
        with(eduViewModel) {
            institutionFirst.value = true
            majorFirst.value = true
            startFirst.value = true
            endFirst.value = true
            institution.value = ""
            major.value = ""
            startDate.value = ""
            endDate.value = ""
        }
        super.onResume()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}
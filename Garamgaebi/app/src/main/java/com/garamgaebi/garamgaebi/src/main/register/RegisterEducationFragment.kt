package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.common.REGISTER_CAREER
import com.garamgaebi.garamgaebi.common.REGISTER_COMPLETE
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterEducationBinding
import com.garamgaebi.garamgaebi.src.main.profile.DatePickerDialogFragment
import com.garamgaebi.garamgaebi.viewModel.RegisterViewModel


class RegisterEducationFragment : BaseBindingFragment<FragmentRegisterEducationBinding>(R.layout.fragment_register_education) {
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<RegisterViewModel>()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)

        binding.fragmentEducationSaveBtn.setOnClickListener {
            registerActivity.setFragment(REGISTER_COMPLETE)
        }
        binding.fragmentEducationTvGoCareer.setOnClickListener {
            parentFragmentManager.popBackStack()
            registerActivity.setFragment(REGISTER_CAREER)
        }
        // 유효성 확인
        viewModel.institution.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.isInstitutionValid.value = it.length < 22 && it.isNotEmpty()
            Log.d("career_company_true",viewModel.isCompanyValid.value.toString())
        })
        viewModel.major.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.isMajorValid.value = it.length < 22 && it.isNotEmpty()

            Log.d("career_position_true",viewModel.isPositionValid.value.toString())
        })
        viewModel.eduStartDate.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            viewModel.isEduStartDateValid.value = it.isNotEmpty()

            if(viewModel.eduEndDate.value?.isNotEmpty() == true) {
                if (it < viewModel.eduEndDate.value.toString()) {
                    viewModel.isEduStartDateValid.value = true
                    viewModel.isEduEndDateValid.value = true
                } else {
                    viewModel.isEduStartDateValid.value = false
                    viewModel.isEduEndDateValid.value = false
                }
            }
            Log.d("career_startDate_true",viewModel.isEduStartDateValid.value.toString())
        })

        viewModel.eduEndDate.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            viewModel.isEduEndDateValid.value = it.isNotEmpty()

            if(viewModel.eduStartDate.value?.isNotEmpty() == true) {
                if (it > viewModel.eduStartDate.value.toString()) {
                    viewModel.isEduEndDateValid.value = true
                    viewModel.isEduStartDateValid.value = true
                } else {
                    viewModel.isEduEndDateValid.value = false
                    viewModel.isEduStartDateValid.value = false
                }
            }

            Log.d("career_endDate_true",viewModel.isCareerEndDateValid.value.toString())
        })

        //재직 정보 date picker
        binding.activityEducationEtStartPeriod.setOnClickListener {
            viewModel.careerStartDateFocusing.value = true

            val orderBottomDialogFragment: DatePickerDialogFragment? =
                viewModel.careerStartDate.value?.let { it1 ->
                    DatePickerDialogFragment(it1) {
                        val arr = it.split(".")
                        binding.activityEducationEtStartPeriod.setText(arr[0]+"."+arr[1])
                    }
                }
            orderBottomDialogFragment?.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }
        binding.activityEducationEtEndPeriod.setOnClickListener {
            viewModel.careerEndDateFocusing.value = true
            val orderBottomDialogFragment: DatePickerDialogFragment? =
                viewModel.careerEndDate.value?.let { it1 ->
                    DatePickerDialogFragment(it1) {
                        val arr = it.split(".")
                        viewModel.careerEndDate.value = arr[0] + "." + arr[1]
                        if (GaramgaebiFunction().checkNow(it)) {
                            binding.activityEducationCheckbox.isChecked = true
                            binding.activityEducationEtEndPeriod.text = "현재"
                            viewModel.isWorking.value = "TRUE"
                        } else {
                            binding.activityEducationCheckbox.isChecked = false
                            binding.activityEducationEtEndPeriod.text = arr[0] + "." + arr[1]
                            viewModel.careerEndDate.value = (arr[0] + "." + arr[1])
                            viewModel.isWorking.value = "FALSE"
                        }
                    }
                }
            orderBottomDialogFragment?.show(parentFragmentManager, orderBottomDialogFragment.tag)

        }
        binding.activityEducationCheckboxCl.setOnClickListener {
            if(viewModel.eduCheckBox.value == false) {
                viewModel.eduEndDate.value = "현재"
                viewModel.isLearning.value = "TRUE"
                viewModel.eduCheckBox.value = true
            }else{
                viewModel.eduEndDate.value = ""
                viewModel.isLearning.value = "FALSE"
                viewModel.eduCheckBox.value = false
            }
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}
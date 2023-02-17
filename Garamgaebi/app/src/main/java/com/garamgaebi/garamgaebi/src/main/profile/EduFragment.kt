package com.garamgaebi.garamgaebi.src.main.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.databinding.FragmentProfileEducationBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.EducationViewModel

class EduFragment  : BaseBindingFragment<FragmentProfileEducationBinding>(R.layout.fragment_profile_education) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[EducationViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // 유효성 확인
        viewModel.institution.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.institutionIsValid.value = it.length < 22 && it.isNotEmpty()


            Log.d("edu_institution_true",viewModel.institutionIsValid.value.toString())
        })
        viewModel.major.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.majorIsValid.value = it.length < 22 && it.isNotEmpty()

            Log.d("edu_major_true",viewModel.majorIsValid.value.toString())
        })
        viewModel.startDate.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            viewModel.startDateIsValid.value = it.isNotEmpty()

            if(viewModel.endDate.value?.isNotEmpty() == true) {
                if (it < viewModel.endDate.value.toString()) {
                    viewModel.startDateIsValid.value = true
                    viewModel.endDateIsValid.value = true
                } else {
                    viewModel.startDateIsValid.value = false
                    viewModel.endDateIsValid.value = false
                }
            }
            Log.d("edu_startDate_true",viewModel.startDateIsValid.value.toString())
        })
        viewModel.endDate.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            viewModel.endDateIsValid.value = it.isNotEmpty()

            if(viewModel.startDate.value?.isNotEmpty() == true) {
                if (it > viewModel.startDate.value.toString()) {
                    viewModel.endDateIsValid.value = true
                    viewModel.startDateIsValid.value = true
                } else {
                    viewModel.endDateIsValid.value = false
                    viewModel.startDateIsValid.value = false
                }
            }

            Log.d("edu_endDate_true",viewModel.endDateIsValid.value.toString())
        })


        with(viewModel){
            institutionHint.value = getString(R.string.register_input_institution_desc)
            majorHint.value = getString(R.string.register_input_major)

            institutionState.value = getString(R.string.caution_input_22)
            majorHint.value = getString(R.string.caution_input_22)
        }

        //유효성 끝
        //유효성 끝
        binding.activityEducationCheckboxDesc.setOnClickListener {
            if(viewModel.checkBox.value == false) {
                viewModel.endDate.value = "현재"
                viewModel.isLearning.value = "TRUE"
                viewModel.checkBox.value = true
            }else{
                viewModel.endDate.value = ""
                viewModel.isLearning.value = "FALSE"
                viewModel.checkBox.value = false
            }
        }
        binding.activityEducationCheckboxRl.setOnClickListener {
            if(viewModel.checkBox.value == false) {
                viewModel.endDate.value = "현재"
                viewModel.isLearning.value = "TRUE"
                viewModel.checkBox.value = true
            }else{
                viewModel.endDate.value = ""
                viewModel.isLearning.value = "FALSE"
                viewModel.checkBox.value = false
            }
        }

        binding.activityEducationSaveBtn.setOnClickListener {
            viewModel.postEducationInfo()
            (activity as ContainerActivity).onBackPressed()
            Log.d("career_add_button","success"+viewModel.endDate.value.toString())
        }




        //재직 정보 date picker
        binding.activityEducationEtStartPeriod.setOnClickListener {
            binding.activityEducationEtInstitutionDesc.clearFocus()
            binding.activityEducationEtMajorDesc.clearFocus()

            viewModel.startFocusing.value = true
            viewModel.startFirst.value = false

            val orderBottomDialogFragment: DatePickerDialogFragment? =
                viewModel.startDate.value?.let { it1 ->
                    DatePickerDialogFragment(it1) {
                        val arr = it.split(".")
                        binding.activityEducationEtStartPeriod.setText(arr[0]+"."+arr[1])
                        viewModel.startFocusing.value = false
                    }
                }
            orderBottomDialogFragment?.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }
        binding.activityEducationEtEndPeriod.setOnClickListener {
            binding.activityEducationEtInstitutionDesc.clearFocus()
            binding.activityEducationEtMajorDesc.clearFocus()

            viewModel.endFocusing.value = true
            viewModel.endFirst.value = false

            val orderBottomDialogFragment: DatePickerDialogFragment? =
                viewModel.endDate.value?.let { it1 ->
                    DatePickerDialogFragment(it1) {
                        val arr = it.split(".")
                        viewModel.endDate.value = arr[0] + "." + arr[1]
                        if (GaramgaebiFunction().checkNow(it)) {
                            binding.activityEducationCheckbox.isChecked = true
                            binding.activityEducationEtEndPeriod.setText("현재")
                            viewModel.isLearning.value = "TRUE"
                        } else {
                            binding.activityEducationCheckbox.isChecked = false
                            binding.activityEducationEtEndPeriod.setText(arr[0] + "." + arr[1])
                            viewModel.endDate.value = (arr[0] + "." + arr[1])
                            viewModel.isLearning.value = "FALSE"
                        }
                        viewModel.endFocusing.value = false
                    }
                }
            orderBottomDialogFragment?.show(parentFragmentManager, orderBottomDialogFragment.tag)

        }
    }
}

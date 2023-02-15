package com.example.template.garamgaebi.src.main.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.garamgaebi.BR
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.*
import com.example.template.garamgaebi.databinding.FragmentProfileEducationBinding
import com.example.template.garamgaebi.databinding.FragmentProfileEducationEditBinding
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.viewModel.CareerViewModel
import com.example.template.garamgaebi.viewModel.EditTextViewModel
import com.example.template.garamgaebi.viewModel.EducationViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EduEditFragment  : BaseBindingFragment<FragmentProfileEducationEditBinding>(R.layout.fragment_profile_education_edit),ConfirmDialogInterface {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[EducationViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val educationIdx = GaramgaebiApplication.sSharedPreferences.getInt("EduIdxForEdit",-1)
        val originInstitution = GaramgaebiApplication.sSharedPreferences.getString("EduInstitutionForEdit","Error")
        val originMajor = GaramgaebiApplication.sSharedPreferences.getString("EduMajorForEdit","Error")
        val originNow = GaramgaebiApplication.sSharedPreferences.getString("EduIsLearningForEdit","Error")
        val originStart = GaramgaebiApplication.sSharedPreferences.getString("EduStartDateForEdit","Error")
        val originEnd = GaramgaebiApplication.sSharedPreferences.getString("EduEndDateForEdit","Error")
        Log.d("go_edit_edu",educationIdx.toString() + originInstitution + originMajor + originNow + originStart + originEnd)

        viewModel.educationIdx = educationIdx
        viewModel.institution.value = originInstitution
        viewModel.major.value = originMajor
        viewModel.isLearning.value = originNow
        viewModel.startDate.value = originStart
        viewModel.endDate.value = originEnd


        with(binding){
            activityEducationCheckbox.isChecked = originNow.equals("TRUE")
            activityEducationEtEndPeriod.text = originEnd
            if(originNow.equals("TRUE")){
                activityEducationEtEndPeriod.text = "현재"
            }
        }

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

        binding.activityEducationRemoveBtn.setOnClickListener {
            val dialog = ConfirmDialog(this, getString(R.string.delete_done), 1)
            // 알림창이 띄워져있는 동안 배경 클릭 막기
            dialog.isCancelable = false
            dialog.show(activity?.supportFragmentManager!!, "com.example.template.garamgaebi.common.ConfirmDialog")
            //경력 삭제
            viewModel.deleteEducationInfo()
            Log.d("edu_remove_button","success")
        }
        binding.activityEducationSaveBtn.setOnClickListener {
            //경력 편집 저장
            viewModel.patchEducationInfo()
            (activity as ContainerActivity).onBackPressed()
            Log.d("edu_add_button","success")
        }

        //기존의 정보 입력이 되어 있기에 첫 입력 예외 x
        viewModel.institutionFirst.value = false
        viewModel.majorFirst.value = false
        viewModel.startFocusing.value = true
        viewModel.startFirst.value = false
        viewModel.endFocusing.value = true
        viewModel.endFirst.value = false


        //재직 정보 date picker
        binding.activityEducationEtStartPeriod.setOnClickListener {
            viewModel.startFocusing.value = true
            viewModel.startFirst.value = false

            val orderBottomDialogFragment: DatePickerDialogFragment? =
                viewModel.startDate.value?.let { it1 ->
                    DatePickerDialogFragment(it1) {
                        val arr = it.split(".")
                        binding.activityEducationEtStartPeriod.setText(arr[0]+"."+arr[1])
                    }
                }
            orderBottomDialogFragment?.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }
        binding.activityEducationEtEndPeriod.setOnClickListener {
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
                            viewModel.isLearning.value = "FALSE"
                        }
                    }
                }
            orderBottomDialogFragment?.show(parentFragmentManager, orderBottomDialogFragment.tag)

        }
    }
    override fun onYesButtonClick(id: Int) {
        (activity as ContainerActivity).onBackPressed()
    }
}

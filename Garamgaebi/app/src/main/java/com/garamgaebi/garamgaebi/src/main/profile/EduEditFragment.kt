package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.FragmentProfileEducationEditBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.EducationViewModel

class EduEditFragment  : BaseBindingFragment<FragmentProfileEducationEditBinding>(R.layout.fragment_profile_education_edit),ConfirmDialogInterface {
    @SuppressLint("ClickableViewAccessibility")
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
            activityEducationEtEndPeriod.setText(originEnd)
            if(originNow.equals("TRUE")){
                activityEducationEtEndPeriod.setText("현재")
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

        binding.activityEducationRemoveBtn.setOnClickListener {
            val dialog: DialogFragment? = ConfirmDialog(this,getString(R.string.delete_q), 1) { it ->
                when (it) {
                    -1 -> {

                        Log.d("edu_remove_button","close")

                    }
                    1 -> {
                        //경력 삭제
                        viewModel.deleteEducationInfo()
                        val dialog = ConfirmDialog(this, getString(R.string.delete_done), -1){it2 ->
                            when(it2){
                                1 -> {

                                    Log.d("edu_remove_button","close")

                                }
                                2->{
                                    (activity as ContainerActivity).onBackPressed()
                                }
                            }
                        }
                        // 알림창이 띄워져있는 동안 배경 클릭 막기
                        dialog.show(activity?.supportFragmentManager!!, "com.example.garamgaebi.common.ConfirmDialog")
                    }
                }
            }
            // 알림창이 띄워져있는 동안 배경 클릭 막기
            dialog?.show(activity?.supportFragmentManager!!, "com.example.garamgaebi.common.ConfirmDialog")
            Log.d("edu_remove_button","success")
        }
        binding.activityEducationSaveBtn.setOnClickListener {
            //경력 편집 저장
            viewModel.patchEducationInfo()
            (activity as ContainerActivity).onBackPressed()
            Log.d("edu_add_button","success")
        }


        //시작년월 입력감지
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

        //종료년월 입력감지
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

        //시작년월 달력 show 여부 감지
        viewModel.showStart.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            if(it == true) {
                binding.activityEducationEtInstitutionDesc.clearFocus()
                binding.activityEducationEtMajorDesc.clearFocus()
                binding.activityEducationEtStartPeriod.clearFocus()
                binding.activityEducationEtEndPeriod.clearFocus()

                viewModel.startFocusing.value = true
                viewModel.startFirst.value = false
                val orderBottomDialogFragment: DatePickerDialogFragment? =
                    viewModel.startDate.value?.let { it1 ->
                        DatePickerDialogFragment(it1) {
                            val arr = it.split(".")
                            viewModel.startDate.value = (arr[0] + "." + arr[1])
                            viewModel.startFocusing.value = false
                        }
                    }
                orderBottomDialogFragment?.show(
                    parentFragmentManager,
                    orderBottomDialogFragment.tag
                )
                viewModel.showStart.value = false
            }else{
                Log.d("career_showStart_true","no")
            }
            Log.d("career_showStart_true","히히")
        })

        //종료년월 갈력 show 감지
        viewModel.showEnd.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            if(it == true) {
                binding.activityEducationEtInstitutionDesc.clearFocus()
                binding.activityEducationEtMajorDesc.clearFocus()
                binding.activityEducationEtStartPeriod.clearFocus()
                binding.activityEducationEtEndPeriod.clearFocus()

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
                viewModel.showEnd.value = false
            }else{
                Log.d("career_showEnd_true","하하")
            }
            Log.d("career_showEnd_true","히히")
        })



        //기존의 정보 입력이 되어 있기에 첫 입력 예외 x
        viewModel.institutionFirst.value = false
        viewModel.majorFirst.value = false

        viewModel.startFirst.value = false
        viewModel.endFirst.value = false

        binding.containerLayout.setOnTouchListener(View.OnTouchListener { v, event ->
            hideKeyboard()
            false
        })

    }
    private fun hideKeyboard() {
        if (activity != null && requireActivity().currentFocus != null) {
            // 프래그먼트기 때문에 getActivity() 사용
            val inputManager: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                requireActivity().currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
    override fun onYesButtonClick(id: Int) {
    }
}

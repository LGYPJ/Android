package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.FragmentProfileEducationEditBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.EducationViewModel
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class EduEditFragment  : BaseBindingFragment<FragmentProfileEducationEditBinding>(R.layout.fragment_profile_education_edit),ConfirmDialogInterface {
    private var educationIdx: Int by Delegates.notNull()
    private lateinit var originInstitution : String
    private lateinit var originMajor : String
    private lateinit var originNow : String
    private lateinit var originStart : String
    private lateinit var originEnd : String
    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[EducationViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        //원본 데이터
        setOriginData()

           Log.d("go_edit_edu",educationIdx.toString() + originInstitution + originMajor + originNow + originStart + originEnd)

        viewModel.educationIdx = educationIdx
        with(viewModel) {
            institution.value = originInstitution
            major.value = originMajor
            isLearning.value = originNow
            startDate.value = originStart
            endDate.value = originEnd

            institutionHint.value = getString(R.string.register_input_institution_desc)
            majorHint.value = getString(R.string.register_input_major)
            institutionState.value = getString(R.string.caution_input_22)
            majorHint.value = getString(R.string.caution_input_22)

            //기존의 정보 입력이 되어 있기에 첫 입력 예외 x
            institutionFirst.value = false
            majorFirst.value = false
            startFirst.value = false
            endFirst.value = false

            // 유효성 확인
            institution.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                institutionIsValid.value = it.length < 22 && it.isNotEmpty()
                Log.d("edu_institution_true", institutionIsValid.value.toString())
            }
            major.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                majorIsValid.value = it.length < 22 && it.isNotEmpty()
                Log.d("edu_major_true", majorIsValid.value.toString())
            }
            startDate.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                startDateIsValid.value = it.isNotEmpty()
                if (endDate.value?.isNotEmpty() == true) {
                    if (it < endDate.value.toString()) {
                        startDateIsValid.value = true
                        endDateIsValid.value = true
                    } else {
                        startDateIsValid.value = false
                        endDateIsValid.value = false
                    }
                }
                Log.d("edu_startDate_true", startDateIsValid.value.toString())
            }

            endDate.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                endDateIsValid.value = it.isNotEmpty()
                if (startDate.value?.isNotEmpty() == true) {
                    if (it > startDate.value.toString()) {
                        endDateIsValid.value = true
                        startDateIsValid.value = true
                    } else {
                        endDateIsValid.value = false
                        startDateIsValid.value = false
                    }
                }
                Log.d("edu_endDate_true", endDateIsValid.value.toString())
            }

            //시작년월 입력감지
            startDate.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                startDateIsValid.value = it.isNotEmpty()
                if (endDate.value?.isNotEmpty() == true) {
                    if (it < endDate.value.toString()) {
                        startDateIsValid.value = true
                        endDateIsValid.value = true
                    } else {
                        startDateIsValid.value = false
                        endDateIsValid.value = false
                    }
                }
                Log.d("edu_startDate_true", startDateIsValid.value.toString())
            }

            //종료년월 입력감지
            endDate.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                endDateIsValid.value = it.isNotEmpty()
                if (startDate.value?.isNotEmpty() == true) {
                    if (it > startDate.value.toString()) {
                        endDateIsValid.value = true
                        startDateIsValid.value = true
                    } else {
                        endDateIsValid.value = false
                        startDateIsValid.value = false
                    }
                }

                Log.d("edu_endDate_true", endDateIsValid.value.toString())
            }

            //시작년월 달력 show 여부 감지
            showStart.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                if (it == true) {
                    binding.activityEducationEtStartPeriod.clearFocus()
                    startFocusing.value = true
                    startFirst.value = false
                    val orderBottomDialogFragment: DatePickerDialogFragment? =
                        startDate.value?.let { it1 ->
                            DatePickerDialogFragment(it1) {it2 ->
                                val arr = it2.split("/")
                                startDate.value = (arr[0] + "/" + arr[1])
                                startFocusing.value = false
                            }
                        }
                    orderBottomDialogFragment?.show(
                        parentFragmentManager,
                        orderBottomDialogFragment.tag
                    )
                    viewModel.showStart.value = false
                } else {
                    Log.d("career_showStart_true", "no")
                }
                Log.d("career_showStart_true", "히히")
            }

            //종료년월 갈력 show 감지
            showEnd.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                if (it == true) {
                    binding.activityEducationEtEndPeriod.clearFocus()
                    endFocusing.value = true
                    endFirst.value = false
                    val orderBottomDialogFragment: DatePickerDialogFragment? =
                        endDate.value?.let { it1 ->
                            DatePickerDialogFragment(it1) {it2 ->
                                val arr = it2.split("/")
                                viewModel.endDate.value = arr[0] + "/" + arr[1]
                                if (GaramgaebiFunction().checkNow(it2)) {
                                    binding.activityEducationCheckbox.isChecked = true
                                    binding.activityEducationEtEndPeriod.setText("현재")
                                    isLearning.value = "TRUE"
                                } else {
                                    binding.activityEducationCheckbox.isChecked = false
                                    binding.activityEducationEtEndPeriod.setText(arr[0] + "/" + arr[1])
                                    endDate.value = (arr[0] + "/" + arr[1])
                                    isLearning.value = "FALSE"
                                }
                                endFocusing.value = false
                            }
                        }
                    orderBottomDialogFragment?.show(
                        parentFragmentManager,
                        orderBottomDialogFragment.tag
                    )
                    showEnd.value = false
                } else {
                    Log.d("career_showEnd_true", "하하")
                }
                Log.d("career_showEnd_true", "히히")
            }

            //유효성 끝
        }

        disposables
            .add(
                binding
                    .activityEducationSaveBtn
                    .clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.patchEducationInfo()
                        Log.d("edu_add_button","success"+viewModel.endDate.value.toString())
                        (activity as ContainerActivity).onBackPressed()
                    }, { it.printStackTrace() })
            )
        disposables
            .add(
                binding
                    .activityEducationCheckboxDesc
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if (viewModel.checkBox.value == false) {
                            viewModel.endDate.value = "현재"
                            viewModel.isLearning.value = "TRUE"
                            viewModel.checkBox.value = true
                        } else {
                            viewModel.endDate.value = ""
                            viewModel.isLearning.value = "FALSE"
                            viewModel.checkBox.value = false
                        }
                    }, { it.printStackTrace() })
            )
        disposables
            .add(
                binding
                    .activityEducationCheckboxRl
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if (viewModel.checkBox.value == false) {
                            viewModel.endDate.value = "현재"
                            viewModel.isLearning.value = "TRUE"
                            viewModel.checkBox.value = true
                        } else {
                            viewModel.endDate.value = ""
                            viewModel.isLearning.value = "FALSE"
                            viewModel.checkBox.value = false
                        }
                    }, { it.printStackTrace() })
            )

        disposables
            .add(
                binding
                    .activityEducationRemoveBtn
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        val dialog = ConfirmDialog(this,getString(R.string.delete_q), 1) {
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
                        dialog.show(activity?.supportFragmentManager!!, "com.example.garamgaebi.common.ConfirmDialog")
                        Log.d("edu_remove_button","success")
                    }, { it.printStackTrace() })
            )

        binding.containerLayout.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }

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
    private fun setOriginData(){
        educationIdx = GaramgaebiApplication.sSharedPreferences.getInt("EduIdxForEdit",-1)
        originInstitution =
            GaramgaebiApplication.sSharedPreferences.getString("EduInstitutionForEdit","Error").toString()
        originMajor = GaramgaebiApplication.sSharedPreferences.getString("EduMajorForEdit","Error").toString()
        originNow =
            GaramgaebiApplication.sSharedPreferences.getString("EduIsLearningForEdit","Error").toString()
        originStart =
            GaramgaebiApplication.sSharedPreferences.getString("EduStartDateForEdit","Error").toString()
        originEnd = GaramgaebiApplication.sSharedPreferences.getString("EduEndDateForEdit","Error").toString()

        with(binding) {
            activityEducationCheckbox.isChecked = originNow == "TRUE"
            viewModel!!.checkBox.value = originNow == "TRUE"
            activityEducationEtEndPeriod.setText(originEnd)
            if (originNow == "TRUE") {
                activityEducationEtEndPeriod.setText("현재")
            }
        }
    }
    override fun onYesButtonClick(id: Int) = Unit
}

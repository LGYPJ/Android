package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.databinding.FragmentProfileCareerEditBinding
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.CareerViewModel
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit
import kotlin.properties.Delegates

class CareerEditFragment  : BaseBindingFragment<FragmentProfileCareerEditBinding>(R.layout.fragment_profile_career_edit),ConfirmDialogInterface{
    private var careerIdx: Int by Delegates.notNull()
    private lateinit var originCompany : String
    private lateinit var originPosition : String
    private lateinit var originNow : String
    private lateinit var originStart : String
    private lateinit var originEnd : String
    @SuppressLint("SuspiciousIndentation", "ClickableViewAccessibility", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[CareerViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        //원본 데이터
        setOriginData()

        Log.d("go_edit_career",careerIdx.toString() + originCompany + originPosition + originNow + originStart + originEnd)

            viewModel.careerIdx = careerIdx

        with(viewModel) {
            company.value = originCompany
            position.value = originPosition
            isWorking.value = originNow
            startDate.value = originStart
            endDate.value = originEnd

            companyHint.value = getString(R.string.register_input_company_desc)
            positionHint.value = getString(R.string.register_input_position_desc)

            //기존의 정보 입력이 되어 있기에 첫 입력 예외 x
            companyFirst.value = false
            positionFirst.value = false
            startFirst.value = false
            endFirst.value = false
            // 유효성 확인

            //회사 입력 감지
            company.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                companyIsValid.value = it.length < INPUT_TEXT_LENGTH && it.isNotEmpty()
                GaramgaebiFunction().checkFirstChar(companyIsValid, it)

                Log.d("career_company_true", companyIsValid.value.toString())
            }

            //직함 입력 감지
            position.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                positionIsValid.value = it.length < INPUT_TEXT_LENGTH && it.isNotEmpty()
                GaramgaebiFunction().checkFirstChar(positionIsValid, it)

                Log.d("career_position_true", positionIsValid.value.toString())
            }

            //시작년월 입력 감지
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
                Log.d("career_startDate_true", startDateIsValid.value.toString())
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
                Log.d("career_endDate_true", endDateIsValid.value.toString())
            }


            //시작년월 달력 show 여부 감지
            showStart.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                if (it == true) {
                    binding.activityCareerEtStartPeriod.clearFocus()
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
                    showStart.value = false
                } else {
                    Log.d("career_showStart_true", "no")
                }
            }

            //종료년월 갈력 show 감지
            showEnd.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                if (it == true) {
                    binding.activityCareerEtEndPeriod.clearFocus()
                    endFocusing.value = true
                    endFirst.value = false
                    val orderBottomDialogFragment: DatePickerDialogFragment? =
                        endDate.value?.let { it1 ->
                            DatePickerDialogFragment(it1) {it2->
                                val arr = it2.split("/")
                                endDate.value = arr[0] + "/" + arr[1]
                                if (GaramgaebiFunction().checkNow(it2)) {
                                    binding.activityCareerCheckbox.isChecked = true
                                    binding.activityCareerEtEndPeriod.setText("현재")
                                    isWorking.value = "TRUE"
                                } else {
                                    binding.activityCareerCheckbox.isChecked = false
                                    binding.activityCareerEtEndPeriod.setText(arr[0] + "/" + arr[1])
                                    endDate.value = (arr[0] + "/" + arr[1])
                                    isWorking.value = "FALSE"
                                    Log.d("api_career_이상,", viewModel.isWorking.value!!)
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
            }
        }
        //유효성 끝

        //클릭 이벤트 처리
        //저장 클릭
        disposables
            .add(
                binding
                    .activityCareerSaveBtn
                    .clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.patchCareerInfo()
                        Log.d("career_add_button","success"+viewModel.endDate.value.toString())
                        (activity as ContainerActivity).onBackPressed()
                    }, { it.printStackTrace() })
            )
        //체크박스, 문구 클릭
        disposables
            .add(
                binding
                    .activityCareerCheckboxDesc
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if (viewModel.checkBox.value == false) {
                            viewModel.endDate.value = "현재"
                            viewModel.isWorking.value = "TRUE"
                            viewModel.checkBox.value = true
                        } else {
                            viewModel.endDate.value = ""
                            viewModel.isWorking.value = "FALSE"
                            viewModel.checkBox.value = false
                        }
                    }, { it.printStackTrace() })
            )
        disposables
            .add(
                binding
                    .activityCareerCheckboxRl
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if (viewModel.checkBox.value == false) {
                            viewModel.endDate.value = "현재"
                            viewModel.isWorking.value = "TRUE"
                            viewModel.checkBox.value = true
                        } else {
                            viewModel.endDate.value = ""
                            viewModel.isWorking.value = "FALSE"
                            viewModel.checkBox.value = false
                        }
                    }, { it.printStackTrace() })
            )

        //삭제 버튼 클릭
        disposables
            .add(
                binding
                    .activityCareerRemoveBtn
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                            val dialog = ConfirmDialog(this,getString(R.string.delete_q), 1) {
                                when (it) {
                                    -1 -> {
                                        Log.d("career_remove_button","close")
                                    }
                                    1 -> {
                                        //경력 삭제
                                        viewModel.deleteCareerInfo()
                                        val dialog = ConfirmDialog(this, getString(R.string.delete_done), -1){it2 ->
                                            when(it2){
                                                1 -> {
                                                    Log.d("career_remove_button","close")
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
                            Log.d("career_remove_button","success")
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
        careerIdx = GaramgaebiApplication.sSharedPreferences.getInt("CareerIdxForEdit",-1)
        originCompany =
            GaramgaebiApplication.sSharedPreferences.getString("CareerCompanyForEdit","Error").toString()
        originPosition =
            GaramgaebiApplication.sSharedPreferences.getString("CareerPositionForEdit","Error").toString()
        originNow =
            GaramgaebiApplication.sSharedPreferences.getString("CareerIsWorkingForEdit","Error").toString()
        originStart =
            GaramgaebiApplication.sSharedPreferences.getString("CareerStartDateForEdit","Error").toString()
        originEnd =
            GaramgaebiApplication.sSharedPreferences.getString("CareerEndDateForEdit","Error").toString()

        with(binding){
            activityCareerCheckbox.isChecked = originNow == "TRUE"
            viewModel!!.checkBox.value = originNow == "TRUE"
            activityCareerEtEndPeriod.setText(originEnd)
            if(originNow == "TRUE"){
                activityCareerEtEndPeriod.setText("현재")
            }
            Log.d("career_checkbox_true", originNow)
        }
    }
    override fun onYesButtonClick(id: Int) = Unit

}

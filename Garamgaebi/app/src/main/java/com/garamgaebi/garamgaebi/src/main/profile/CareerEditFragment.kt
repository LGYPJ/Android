package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.databinding.FragmentProfileCareerEditBinding
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.CareerViewModel
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

class CareerEditFragment  : BaseBindingFragment<FragmentProfileCareerEditBinding>(R.layout.fragment_profile_career_edit),ConfirmDialogInterface{
    private lateinit var callback: OnBackPressedCallback
    @SuppressLint("SuspiciousIndentation", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[CareerViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val careerIdx = GaramgaebiApplication.sSharedPreferences.getInt("CareerIdxForEdit",-1)
        val originCompany = GaramgaebiApplication.sSharedPreferences.getString("CareerCompanyForEdit","Error")
        val originPosition = GaramgaebiApplication.sSharedPreferences.getString("CareerPositionForEdit","Error")
        val originNow = GaramgaebiApplication.sSharedPreferences.getString("CareerIsWorkingForEdit","Error")
        val originStart = GaramgaebiApplication.sSharedPreferences.getString("CareerStartDateForEdit","Error")
        val originEnd = GaramgaebiApplication.sSharedPreferences.getString("CareerEndDateForEdit","Error")

        Log.d("go_edit_career",careerIdx.toString() + originCompany + originPosition + originNow + originStart + originEnd)

        viewModel.careerIdx = careerIdx
        viewModel.company.value = originCompany
        viewModel.position.value = originPosition
        viewModel.isWorking.value = originNow
        viewModel.startDate.value = originStart
        viewModel.endDate.value = originEnd


        with(binding){
            activityCareerCheckbox.isChecked = originNow.equals("TRUE")
            viewModel.checkBox.value = originNow.equals("TRUE")
            activityCareerEtEndPeriod.setText(originEnd)
            if(originNow.equals("TRUE")){
                activityCareerEtEndPeriod.setText("현재")
            }
            Log.d("career_checkbox_true",originNow.toString())

        }

        // 유효성 확인

        //회사 입력 감지
        viewModel.company.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.companyIsValid.value = it.length < 22 && it.isNotEmpty()


            Log.d("career_company_true",viewModel.companyIsValid.value.toString())
        })

        //직함 입력 감지
        viewModel.position.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.positionIsValid.value = it.length < 22 && it.isNotEmpty()

            Log.d("career_position_true",viewModel.positionIsValid.value.toString())
        })

        //시작년월 입력 감지
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
            Log.d("career_startDate_true",viewModel.startDateIsValid.value.toString())
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

            Log.d("career_endDate_true",viewModel.endDateIsValid.value.toString())
        })


        //시작년월 달력 show 여부 감지
        viewModel.showStart.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            if(it == true) {
                binding.activityCareerEtPosition.clearFocus()
                binding.activityCareerEtCompanyDesc.clearFocus()
                binding.activityCareerEtStartPeriod.clearFocus()
                binding.activityCareerEtEndPeriod.clearFocus()

                viewModel.startFocusing.value = true
                viewModel.startFirst.value = false
                val orderBottomDialogFragment: DatePickerDialogFragment? =
                    viewModel.startDate.value?.let { it1 ->
                        DatePickerDialogFragment(it1) {
                            val arr = it.split("/")
                            viewModel.startDate.value = (arr[0] + "/" + arr[1])
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
                binding.activityCareerEtPosition.clearFocus()
                binding.activityCareerEtCompanyDesc.clearFocus()
                binding.activityCareerEtStartPeriod.clearFocus()
                binding.activityCareerEtEndPeriod.clearFocus()

                viewModel.endFocusing.value = true
                viewModel.endFirst.value = false
                val orderBottomDialogFragment: DatePickerDialogFragment? =
                    viewModel.endDate.value?.let { it1 ->
                        DatePickerDialogFragment(it1) {
                            val arr = it.split("/")
                            viewModel.endDate.value = arr[0] + "/" + arr[1]
                            if (GaramgaebiFunction().checkNow(it)) {
                                binding.activityCareerCheckbox.isChecked = true
                                binding.activityCareerEtEndPeriod.setText("현재")
                                viewModel.isWorking.value = "TRUE"
                            } else {
                                binding.activityCareerCheckbox.isChecked = false
                                binding.activityCareerEtEndPeriod.setText(arr[0] + "/" + arr[1])
                                viewModel.endDate.value = (arr[0] + "/" + arr[1])
                                viewModel.isWorking.value = "FALSE"
                                Log.d("api_career_이상,", viewModel.isWorking.value!!)
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

        with(viewModel){
            companyHint.value = getString(R.string.register_input_company_desc)
            positionHint.value = getString(R.string.register_input_position_desc)
        }

        //유효성 끝

        disposables
            .add(
                binding
                    .activityCareerSaveBtn
                    .clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.postCareerInfo()
                        Log.d("career_add_button","success"+viewModel.endDate.value.toString())
                        (activity as ContainerActivity).onBackPressed()
                    }, { it.printStackTrace() })
            )
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
        disposables
            .add(
                binding
                    .activityCareerRemoveBtn
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                            val dialog: DialogFragment? = ConfirmDialog(this,getString(R.string.delete_q), 1) { it ->
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
                            dialog?.show(activity?.supportFragmentManager!!, "com.example.garamgaebi.common.ConfirmDialog")
                            Log.d("career_remove_button","success")
                    }, { it.printStackTrace() })
            )

        //기존의 정보 입력이 되어 있기에 첫 입력 예외 x
        viewModel.companyFirst.value = false
        viewModel.positionFirst.value = false
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
    //뒤로가기
    override fun onYesButtonClick(id: Int) {

    }

}

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
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.common.INPUT_TEXT_LENGTH
import com.garamgaebi.garamgaebi.common.KeyboardVisibilityUtils
import com.garamgaebi.garamgaebi.databinding.FragmentProfileCareerBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.CareerViewModel
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

class CareerFragment  : BaseBindingFragment<FragmentProfileCareerBinding>(R.layout.fragment_profile_career) {
    @SuppressLint("SuspiciousIndentation", "ClickableViewAccessibility", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[CareerViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        with(viewModel) {
            companyHint.value = getString(R.string.register_input_company_desc)
            positionHint.value = getString(R.string.register_input_position_desc)


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

            _add.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel

                if (_add.value?.result == true){
                    (activity as ContainerActivity).onBackPressed()
                }

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
                    binding.fragmentCareerEtStartPeriod.clearFocus()
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
                Log.d("career_showStart_true", "히히")
            }

            //종료년월 갈력 show 감지
            showEnd.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                if (it == true) {
                    binding.fragmentCareerEtEndPeriod.clearFocus()
                    endFocusing.value = true
                    endFirst.value = false
                    val orderBottomDialogFragment: DatePickerDialogFragment? =
                        viewModel.endDate.value?.let { it1 ->
                            DatePickerDialogFragment(it1) {it2 ->
                                val arr = it2.split("/")
                                viewModel.endDate.value = arr[0] + "/" + arr[1]
                                if (GaramgaebiFunction().checkNow(it2)) {
                                    binding.fragmentCareerCheckbox.isChecked = true
                                    binding.fragmentCareerEtEndPeriod.setText("현재")
                                    isWorking.value = "TRUE"
                                } else {
                                    binding.fragmentCareerCheckbox.isChecked = false
                                    binding.fragmentCareerEtEndPeriod.setText(arr[0] + "/" + arr[1])
                                    endDate.value = (arr[0] + "/" + arr[1])
                                    isWorking.value = "FALSE"
                                }
                                endFocusing.value = false
                            }
                        }
                    orderBottomDialogFragment?.show(
                        parentFragmentManager,
                        orderBottomDialogFragment.tag
                    )
                    viewModel.showEnd.value = false
                } else {
                    Log.d("career_showEnd_true", "하하")
                }
                Log.d("career_showEnd_true", "히히")
            }
        }

        //유효성 끝

        disposables
            .add(
                binding
                    .fragmentCareerSaveBtn
                    .clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.postCareerInfo()
                        Log.d("career_add_button","success"+viewModel.endDate.value.toString())
                        //(activity as ContainerActivity).onBackPressed()
                    }, { it.printStackTrace() })
            )
        disposables
            .add(
                binding
                    .fragmentCareerCheckboxDesc
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if (viewModel.checkBox.value == false) {
                            viewModel.endDate.value = "현재"
                            viewModel.isWorking.value = "TRUE"
                            viewModel.checkBox.value = true
                            viewModel.endFirst.value = false
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
                    .fragmentCareerCheckbox
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if (viewModel.checkBox.value == true) {
                            viewModel.endDate.value = "현재"
                            viewModel.isWorking.value = "TRUE"
                            viewModel.checkBox.value = true
                            viewModel.endFirst.value = false
                        } else {
                            viewModel.endDate.value = ""
                            viewModel.isWorking.value = "FALSE"
                           viewModel.checkBox.value = false
                        }
                    }, { it.printStackTrace() })
            )

        binding.containerLayout.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }
//        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
//            onShowKeyboard = { keyboardHeight ->
//                binding.svRoot.run {
//                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
//                }
//            })
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
}

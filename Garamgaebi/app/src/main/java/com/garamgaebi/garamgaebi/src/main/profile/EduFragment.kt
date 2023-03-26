package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.FragmentProfileEducationBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.EducationViewModel
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

class EduFragment  : BaseBindingFragment<FragmentProfileEducationBinding>(R.layout.fragment_profile_education) {
    @SuppressLint("ClickableViewAccessibility", "SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[EducationViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        with(viewModel) {
            institutionHint.value = getString(R.string.register_input_institution_desc)
            majorHint.value = getString(R.string.register_input_major)

            institutionState.value = getString(R.string.caution_input_22)
            majorHint.value = getString(R.string.caution_input_22)

            // 유효성 확인

            //교육 기관 입력 감지
            institution.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                institutionIsValid.value = it.length < INPUT_TEXT_LENGTH && it.isNotEmpty()
                GaramgaebiFunction().checkFirstChar(institutionIsValid, it)
                Log.d("edu_institution_true", institutionIsValid.value.toString())
            }

            //전공 입력 감지
            major.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                majorIsValid.value = it.length < INPUT_TEXT_LENGTH && it.isNotEmpty()
                GaramgaebiFunction().checkFirstChar(majorIsValid, it)
                Log.d("edu_major_true", majorIsValid.value.toString())
            }

            _add.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel

                if (_add.value?.result == true){
                    GaramgaebiApplication.getEdu = true
                    (activity as ContainerActivity).onBackPressed()
                }else{
                    (requireActivity() as ContainerActivity).networkAlertDialog()
                }

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
                    binding.fragmentEducationEtStartPeriod.clearFocus()
                    startFocusing.value = true
                    startFirst.value = false
                    val orderBottomDialogFragment: DatePickerDialogFragment? =
                        viewModel.startDate.value?.let { it1 ->
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
                    binding.fragmentEducationEtEndPeriod.clearFocus()
                    endFocusing.value = true
                    endFirst.value = false
                    val orderBottomDialogFragment: DatePickerDialogFragment? =
                        endDate.value?.let { it1 ->
                            DatePickerDialogFragment(it1) {it2->
                                val arr = it2.split("/")
                                endDate.value = arr[0] + "/" + arr[1]
                                if (GaramgaebiFunction().checkNow(it2)) {
                                    binding.fragmentEducationCheckbox.isChecked = true
                                    binding.fragmentEducationEtEndPeriod.setText("현재")
                                    isLearning.value = "TRUE"
                                } else {
                                    binding.fragmentEducationCheckbox.isChecked = false
                                    binding.fragmentEducationEtEndPeriod.setText(arr[0] + "/" + arr[1])
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
        }
        //유효성 끝

        disposables
            .add(
                binding
                    .fragmentEducationSaveBtn
                    .clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if((requireActivity() as ContainerActivity).networkValid.value == true) {
                            viewModel.postEducationInfo()
                        }else {
                            (requireActivity() as ContainerActivity).networkAlertDialog()
                        }
                        Log.d("edu_add_button","success"+viewModel.endDate.value.toString())
                        //(activity as ContainerActivity).onBackPressed()
                    }, { it.printStackTrace() })
            )
        disposables
            .add(
                binding
                    .fragmentEducationCheckboxDesc
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if (viewModel.checkBox.value == false) {
                            viewModel.endDate.value = "현재"
                            viewModel.isLearning.value = "TRUE"
                            viewModel.checkBox.value = true
                            viewModel.endFirst.value = false
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
                    .fragmentEducationCheckbox
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if (viewModel.checkBox.value == true) {
                            viewModel.endDate.value = "현재"
                            viewModel.isLearning.value = "TRUE"
                            viewModel.checkBox.value = true
                            viewModel.endFirst.value = false
                        } else {
                            viewModel.endDate.value = ""
                            viewModel.isLearning.value = "FALSE"
                            viewModel.checkBox.value = false
                        }
                    }, { it.printStackTrace() })
            )

        binding.containerLayout.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }
        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
            onShowKeyboard = { keyboardHeight ->
                binding.svRoot.run {
                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
                }
                binding.fragmentEducationSaveBtn.visibility = View.GONE
            },
            onHideKeyboard = { ->
                // binding.fragmentEducationSaveBtn.visibility = View.VISIBLE
            }
        )
        view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val rect = Rect()
                view.getWindowVisibleDisplayFrame(rect)

                val screenHeight = view.rootView.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight < screenHeight * 0.15) {
                    // 키보드가 완전히 내려갔음을 나타내는 동작을 구현합니다.
                    binding.fragmentEducationSaveBtn.postDelayed({
                        binding.fragmentEducationSaveBtn.visibility = View.VISIBLE
                    },0)
                }
            }
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
}

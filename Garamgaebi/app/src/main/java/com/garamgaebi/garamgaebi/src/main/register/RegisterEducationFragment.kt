package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterEducationBinding
import com.garamgaebi.garamgaebi.src.main.profile.DatePickerDialogFragment
import com.garamgaebi.garamgaebi.viewModel.CareerViewModel
import com.garamgaebi.garamgaebi.viewModel.EducationViewModel
import com.garamgaebi.garamgaebi.viewModel.RegisterViewModel
import kotlinx.coroutines.runBlocking


class RegisterEducationFragment : BaseBindingFragment<FragmentRegisterEducationBinding>(R.layout.fragment_register_education) {
    lateinit var registerActivity : RegisterActivity
    val careerViewModel by activityViewModels<CareerViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by activityViewModels<EducationViewModel>()
        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

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
                    binding.activityEducationEtEndPeriod.clearFocus()
                    endFocusing.value = true
                    endFirst.value = false
                    val orderBottomDialogFragment: DatePickerDialogFragment? =
                        endDate.value?.let { it1 ->
                            DatePickerDialogFragment(it1) {it2->
                                val arr = it2.split("/")
                                endDate.value = arr[0] + "/" + arr[1]
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
        }
        //유효성 끝

        binding.fragmentEducationSaveBtn.setOnClickListener {
//            GaramgaebiApplication.sSharedPreferences
//                .edit().putBoolean("isCareer", false)
//                .apply()
            val careerCheck = runBlocking{ // 비동기 작업 시작
                GaramgaebiApplication().saveBooleanToDataStore("isCareer",true)
            }
            registerActivity.setFragment(REGISTER_COMPLETE)
        }
        binding.fragmentEducationTvGoCareer.setOnClickListener {
            parentFragmentManager.popBackStack()
            registerActivity.setFragment(REGISTER_CAREER)
        }

        binding.activityEducationCheckbox.setOnClickListener {
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
        }
        binding.activityEducationCheckboxDesc.setOnClickListener {
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
        }
        binding.containerLayout.setOnTouchListener{ _, _ ->
            hideKeyboard()
            false
        }
        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
            onShowKeyboard = { keyboardHeight ->
                binding.svRoot.run {
                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
                }
                binding.fragmentEducationSaveBtn.visibility = View.GONE
                binding.fragmentEducationTv.visibility = View.GONE
                binding.fragmentEducationTvGoCareer.visibility = View.GONE

            },
            onHideKeyboard = { ->
                //binding.fragmentCareerSaveBtn.visibility = View.VISIBLE
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
                    binding.fragmentEducationTv.postDelayed({
                        binding.fragmentEducationTv.visibility = View.VISIBLE
                    },0)
                    binding.fragmentEducationTvGoCareer.postDelayed({
                        binding.fragmentEducationTvGoCareer.visibility = View.VISIBLE
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
            checkBox.value = false
        }
        super.onResume()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}
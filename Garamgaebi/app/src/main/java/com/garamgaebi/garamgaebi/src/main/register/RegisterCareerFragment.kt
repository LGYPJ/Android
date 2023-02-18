package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterCareerBinding
import com.garamgaebi.garamgaebi.src.main.profile.DatePickerDialogFragment
import com.garamgaebi.garamgaebi.viewModel.CareerViewModel
import com.garamgaebi.garamgaebi.viewModel.RegisterViewModel

class RegisterCareerFragment : BaseBindingFragment<FragmentRegisterCareerBinding>(R.layout.fragment_register_career) {
    lateinit var registerActivity:RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<CareerViewModel>()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)
        binding.viewModel = viewModel

        binding.fragmentCareerSaveBtn.setOnClickListener {
            GaramgaebiApplication.sSharedPreferences
                .edit().putBoolean("isCareer", true)
                .apply()
            registerActivity.setFragment(REGISTER_COMPLETE)
        }
        binding.fragmentRegisterCareerTvGoEdu.setOnClickListener {
            Log.d("edu","edu")
            parentFragmentManager.popBackStack()
            registerActivity.setFragment(REGISTER_EDU)
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
                binding.fragmentCareerEtCompany.clearFocus()
                binding.fragmentCareerEtPosition.clearFocus()
                binding.activityCareerEtStartPeriod.clearFocus()
                binding.activityCareerEtEndPeriod.clearFocus()

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
                binding.fragmentCareerEtCompany.clearFocus()
                binding.fragmentCareerEtPosition.clearFocus()
                binding.activityCareerEtStartPeriod.clearFocus()
                binding.activityCareerEtEndPeriod.clearFocus()

                viewModel.endFocusing.value = true
                viewModel.endFirst.value = false
                val orderBottomDialogFragment: DatePickerDialogFragment? =
                    viewModel.endDate.value?.let { it1 ->
                        DatePickerDialogFragment(it1) {
                            val arr = it.split(".")
                            viewModel.endDate.value = arr[0] + "." + arr[1]
                            if (GaramgaebiFunction().checkNow(it)) {
                                binding.activityCareerCheckbox.isChecked = true
                                binding.activityCareerEtEndPeriod.setText("현재")
                                viewModel.isWorking.value = "TRUE"
                            } else {
                                binding.activityCareerCheckbox.isChecked = false
                                binding.activityCareerEtEndPeriod.setText(arr[0] + "." + arr[1])
                                viewModel.endDate.value = (arr[0] + "." + arr[1])
                                viewModel.isWorking.value = "FALSE"
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

        //hint 설정
        with(viewModel){
            companyHint.value = getString(R.string.register_input_company_desc)
            positionHint.value = getString(R.string.register_input_position_desc)

        }

        binding.activityCareerCheckboxCl.setOnClickListener {
            if(viewModel.checkBox.value == false) {
                viewModel.endDate.value = "현재"
                viewModel.isWorking.value = "TRUE"
                viewModel.checkBox.value = true
            }else{
                viewModel.endDate.value = ""
                viewModel.isWorking.value = "FALSE"
                viewModel.checkBox.value = false
            }
        }

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
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}
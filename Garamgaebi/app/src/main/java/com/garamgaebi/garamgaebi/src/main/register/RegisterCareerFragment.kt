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
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.common.REGISTER_COMPLETE
import com.garamgaebi.garamgaebi.common.REGISTER_EDU
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterCareerBinding
import com.garamgaebi.garamgaebi.src.main.profile.DatePickerDialogFragment
import com.garamgaebi.garamgaebi.viewModel.RegisterViewModel

class RegisterCareerFragment : BaseBindingFragment<FragmentRegisterCareerBinding>(R.layout.fragment_register_career) {
    lateinit var registerActivity:RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<RegisterViewModel>()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)

        binding.fragmentCareerSaveBtn.setOnClickListener {
            registerActivity.setFragment(REGISTER_COMPLETE)
        }
        binding.fragmentRegisterCareerTvGoEdu.setOnClickListener {
            Log.d("edu","edu")
            parentFragmentManager.popBackStack()
            registerActivity.setFragment(REGISTER_EDU)
        }

        // 유효성 확인
        viewModel.company.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.isCompanyValid.value = it.length < 22 && it.isNotEmpty()
            Log.d("career_company_true",viewModel.isCompanyValid.value.toString())
        })
        viewModel.position.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.isPositionValid.value = it.length < 22 && it.isNotEmpty()

            Log.d("career_position_true",viewModel.isPositionValid.value.toString())
        })
        viewModel.careerStartDate.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            viewModel.isCareerStartDateValid.value = it.isNotEmpty()

            if(viewModel.careerEndDate.value?.isNotEmpty() == true) {
                if (it < viewModel.careerEndDate.value.toString()) {
                    viewModel.isCareerStartDateValid.value = true
                    viewModel.isCareerEndDateValid.value = true
                } else {
                    viewModel.isCareerStartDateValid.value = false
                    viewModel.isCareerEndDateValid.value = false
                }
            }
            Log.d("career_startDate_true",viewModel.isCareerStartDateValid.value.toString())
        })

        viewModel.careerEndDate.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            viewModel.isCareerEndDateValid.value = it.isNotEmpty()

            if(viewModel.careerStartDate.value?.isNotEmpty() == true) {
                if (it > viewModel.careerStartDate.value.toString()) {
                    viewModel.isCareerEndDateValid.value = true
                    viewModel.isCareerStartDateValid.value = true
                } else {
                    viewModel.isCareerEndDateValid.value = false
                    viewModel.isCareerStartDateValid.value = false
                }
            }

            Log.d("career_endDate_true",viewModel.isCareerEndDateValid.value.toString())
        })

        //재직 정보 date picker
        binding.activityCareerEtStartPeriod.setOnClickListener {
            viewModel.careerStartDateFocusing.value = true
            binding.fragmentCareerEtCompany.clearFocus()
            binding.fragmentCareerEtPosition.clearFocus()

            binding.fragmentCareerEtCompany
            val orderBottomDialogFragment: DatePickerDialogFragment? =
                viewModel.careerStartDate.value?.let { it1 ->
                    DatePickerDialogFragment(it1) {
                        val arr = it.split(".")
                        binding.activityCareerEtStartPeriod.setText(arr[0]+"."+arr[1])
                    }
                }
            viewModel.careerEndDateFocusing.value = false
            orderBottomDialogFragment?.show(parentFragmentManager, orderBottomDialogFragment.tag)
            viewModel.careerEndDateFocusing.value = false
        }
        binding.activityCareerEtEndPeriod.setOnClickListener {
            viewModel.careerEndDateFocusing.value = true
            binding.fragmentCareerEtCompany.clearFocus()
            binding.fragmentCareerEtPosition.clearFocus()

            val orderBottomDialogFragment: DatePickerDialogFragment? =
                viewModel.careerEndDate.value?.let { it1 ->
                    DatePickerDialogFragment(it1) {
                        val arr = it.split(".")
                        viewModel.careerEndDate.value = arr[0] + "." + arr[1]
                        if (GaramgaebiFunction().checkNow(it)) {
                            binding.activityCareerCheckbox.isChecked = true
                            binding.activityCareerEtEndPeriod.text = "현재"
                            viewModel.isWorking.value = "TRUE"
                        } else {
                            binding.activityCareerCheckbox.isChecked = false
                            binding.activityCareerEtEndPeriod.text = arr[0] + "." + arr[1]
                            viewModel.careerEndDate.value = (arr[0] + "." + arr[1])
                            viewModel.isWorking.value = "FALSE"
                        }
                    }
                }
            viewModel.careerStartDateFocusing.value = false
            orderBottomDialogFragment?.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }

        binding.activityCareerCheckboxCl.setOnClickListener {
            if(viewModel.careerCheckBox.value == false) {
                viewModel.careerEndDate.value = "현재"
                viewModel.isLearning.value = "TRUE"
                viewModel.careerCheckBox.value = true
            }else{
                viewModel.careerEndDate.value = ""
                viewModel.isLearning.value = "FALSE"
                viewModel.careerCheckBox.value = false
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
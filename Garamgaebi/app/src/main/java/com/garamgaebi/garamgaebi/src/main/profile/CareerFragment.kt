package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.databinding.FragmentProfileCareerBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.CareerViewModel

class CareerFragment  : BaseBindingFragment<FragmentProfileCareerBinding>(R.layout.fragment_profile_career) {
    private lateinit var callback: OnBackPressedCallback
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[CareerViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        // 유효성 확인
        viewModel.company.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
                viewModel.companyIsValid.value = it.length < 22 && it.isNotEmpty()


            Log.d("career_company_true",viewModel.companyIsValid.value.toString())
        })
        viewModel.position.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
                viewModel.positionIsValid.value = it.length < 22 && it.isNotEmpty()

            Log.d("career_position_true",viewModel.positionIsValid.value.toString())
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
            Log.d("career_startDate_true",viewModel.startDateIsValid.value.toString())
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

            Log.d("career_endDate_true",viewModel.endDateIsValid.value.toString())
        })


        with(viewModel){
            companyHint.value = getString(R.string.register_input_company_desc)
            positionHint.value = getString(R.string.register_input_position_desc)

            companyState.value = getString(R.string.caution_input_22)
            positionState.value = getString(R.string.caution_input_22)
        }

        //유효성 끝
        binding.activityCareerCheckboxDesc.setOnClickListener {
            viewModel.endDate.value = "현재"
            viewModel.isWorking.value = "TRUE"
            viewModel.checkBox.value = true
        }

        binding.activityCareerSaveBtn.setOnClickListener {
            viewModel.postCareerInfo()
            (activity as ContainerActivity).onBackPressed()
            Log.d("career_add_button","success"+viewModel.endDate.value.toString())
        }




        //재직 정보 date picker
        binding.activityCareerEtStartPeriod.setOnClickListener {
            viewModel.startFocusing.value = true
            viewModel.startFirst.value = false

                val orderBottomDialogFragment: DatePickerDialogFragment? =
                    viewModel.startDate.value?.let { it1 ->
                        DatePickerDialogFragment(it1) {
                            val arr = it.split(".")
                            binding.activityCareerEtStartPeriod.setText(arr[0]+"."+arr[1])
                        }
                    }
            orderBottomDialogFragment?.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }
        binding.activityCareerEtEndPeriod.setOnClickListener {
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
                    }
                }
            orderBottomDialogFragment?.show(parentFragmentManager, orderBottomDialogFragment.tag)

        }
    }
}

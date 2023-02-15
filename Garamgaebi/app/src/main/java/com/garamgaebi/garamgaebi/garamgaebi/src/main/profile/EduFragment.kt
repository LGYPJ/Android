package com.garamgaebi.garamgaebi.garamgaebi.src.main.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentProfileEducationBinding
import com.garamgaebi.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel
import com.garamgaebi.garamgaebi.garamgaebi.viewModel.EducationViewModel

class EduFragment  : BaseBindingFragment<FragmentProfileEducationBinding>(R.layout.fragment_profile_education) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[EducationViewModel::class.java]
        binding.setVariable(BR.snsViewModel,viewModel)
        binding.educationViewModel = viewModel

        val editTextViewModel = ViewModelProvider(this)[EditTextViewModel::class.java]
        binding.setVariable(BR.editTextViewModel,editTextViewModel)

        binding.editTextViewModel = editTextViewModel

        // 유효성 확인
        viewModel.institution.observe(viewLifecycleOwner, Observer {
            binding.educationViewModel = viewModel
            if(it.length < 22 && it.isNotEmpty())
                viewModel.institutionIsValid.value = true

            Log.d("education_institution_true",viewModel.institutionIsValid.value.toString())
        })
        viewModel.major.observe(viewLifecycleOwner, Observer {
            binding.educationViewModel = viewModel
            if(it.length < 22 && it.isNotEmpty())
                viewModel.majorIsValid.value = true

            Log.d("education_major_true",viewModel.majorIsValid.value.toString())
        })
        viewModel.startDate.observe(viewLifecycleOwner, Observer {
            binding.educationViewModel = viewModel

            viewModel.startDateIsValid.value = it.isNotEmpty()

            Log.d("education_startDate_true",viewModel.startDateIsValid.value.toString())
        })
        viewModel.endDate.observe(viewLifecycleOwner, Observer {
            binding.educationViewModel = viewModel

            viewModel.endDateIsValid.value = it.isNotEmpty()

            Log.d("educationendDate_true",viewModel.startDateIsValid.value.toString())
        })

        binding.activityEducationSaveBtn.setOnClickListener {
            viewModel.postEducationInfo()
            (activity as ContainerActivity).onBackPressed()
            Log.d("education_add_button","success")
        }



        //교육기관 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityEducationEtInstitutionDesc)

        //전공/과정 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityEducationEtMajorDesc)

        //교육기간_시작 입력 시 레이아웃 테두리 변경 -> 달력으로 바꿔야함
        checkDpInput(binding.activityEducationEtStartPeriod)

        //교육기간_종료 시 레이아웃 테두리 변경 -> 달력으로 바꿔야함
        checkDpInput(binding.activityEducationEtEndPeriod)

//재직 정보 date picker
        binding.activityEducationEtStartPeriod.setOnClickListener {
            val orderBottomDialogFragment: DatePickerDialogFragment = DatePickerDialogFragment {
                val arr = it.split(".")

                binding.activityEducationEtStartPeriod.setText(arr[0] + "." + arr[1])

                checkDpInput(binding.activityEducationEtEndPeriod)
            }
            orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }
        //종료년월
        binding.activityEducationEtEndPeriod.setOnClickListener {
            val orderBottomDialogFragment: DatePickerDialogFragment = DatePickerDialogFragment {
                val arr = it.split(".")
                viewModel.endDate.value = arr[0]+"."+arr[1]
                if (GaramgaebiFunction().checkNow(it)) {
                    binding.activityEducationCheckbox.isChecked = true
                    binding.activityEducationEtEndPeriod.setText("현재")
                    viewModel.isLearning.value = "TRUE"
                } else {
                    binding.activityEducationCheckbox.isChecked = false
                    binding.activityEducationEtEndPeriod.setText(arr[0] + "." + arr[1])
                    viewModel.isLearning.value = "FALSE"
                }
                checkDpInput(binding.activityEducationEtEndPeriod)
            }
            orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }
    }

     fun checkDpInput(view: TextView){
        if (checkInfo()){
            binding.activityEducationSaveBtn.isClickable = true
            binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
        }else{
            binding.activityEducationSaveBtn.isClickable = false
            binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
        }
    }
    fun checkEtInput(view: EditText) {
        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.basic_black_border_layout)
            } else {
                view.setBackgroundResource(R.drawable.basic_gray_border_layout)
            }
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkInfo()){
                    binding.activityEducationSaveBtn.isClickable = true
                    binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
                }else{
                    binding.activityEducationSaveBtn.isClickable = false
                    binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
                }
            }
        })
    }

     fun checkInfo() : Boolean{
        var  checkResult = true
        var institution = binding.activityEducationEtInstitutionDesc.text.toString()
        var major = binding.activityEducationEtMajorDesc.text.toString()
        var start = binding.activityEducationEtStartPeriod.text.toString()
        var end = binding.activityEducationEtEndPeriod.text.toString()

        //교육기관 조건 확인 기능
        if(institution.length < 8) checkResult = false

        //전공/과정 조건 확인 기능
        if(major.isEmpty()) {
            checkResult = false
        }
        //시작년월 조건 확인 기능
        if(start.isEmpty()) checkResult = false

        //종료년월 조건 확인 기능
        if(end.isEmpty()) checkResult = false

        return checkResult
    }
}

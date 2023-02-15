package com.example.template.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.garamgaebi.BR
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.*
import com.example.template.garamgaebi.databinding.FragmentProfileCareerBinding
import com.example.template.garamgaebi.databinding.FragmentProfileCareerEditBinding
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.viewModel.CareerViewModel
import com.example.template.garamgaebi.viewModel.EditTextViewModel
import com.example.template.garamgaebi.viewModel.ProfileViewModel
import com.example.template.garamgaebi.viewModel.SNSViewModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class CareerEditFragment  : BaseBindingFragment<FragmentProfileCareerEditBinding>(R.layout.fragment_profile_career_edit),ConfirmDialogInterface{
    private lateinit var callback: OnBackPressedCallback
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[CareerViewModel::class.java]
        binding.setVariable(BR.snsViewModel,viewModel)
        binding.careerViewModel = viewModel

        val editTextViewModel = ViewModelProvider(this)[EditTextViewModel::class.java]
        binding.setVariable(BR.editTextViewModel,editTextViewModel)

        binding.editTextViewModel = editTextViewModel

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
            activityCareerEtEndPeriod.text = originEnd
            if(originNow.equals("TRUE")){
                activityCareerEtEndPeriod.text = "현재"
            }
        }

        // 유효성 확인
        viewModel.company.observe(viewLifecycleOwner, Observer {
            binding.careerViewModel = viewModel
            if(it.length < 22 && it.isNotEmpty())
            viewModel.companyIsValid.value = true

            Log.d("career_company_true",viewModel.companyIsValid.value.toString())
        })
        viewModel.position.observe(viewLifecycleOwner, Observer {
            binding.careerViewModel = viewModel
            if(it.length < 22 && it.isNotEmpty())
                viewModel.positionIsValid.value = true

            Log.d("career_position_true",viewModel.positionIsValid.value.toString())
        })
        viewModel.startDate.observe(viewLifecycleOwner, Observer {
            binding.careerViewModel = viewModel

            viewModel.startDateIsValid.value = it.isNotEmpty()

            Log.d("career_startDate_true",viewModel.positionIsValid.value.toString())
        })
        viewModel.endDate.observe(viewLifecycleOwner, Observer {
            binding.careerViewModel = viewModel

            viewModel.endDateIsValid.value = it.isNotEmpty()

            Log.d("career_endDate_true",viewModel.positionIsValid.value.toString())
        })

        binding.activityCareerRemoveBtn.setOnClickListener {
            val dialog = ConfirmDialog(this, getString(R.string.delete_done), 1)
            // 알림창이 띄워져있는 동안 배경 클릭 막기
            dialog.isCancelable = false
            dialog.show(activity?.supportFragmentManager!!, "com.example.template.garamgaebi.common.ConfirmDialog")
            //경력 삭제
            viewModel.deleteCareerInfo()
            Log.d("career_remove_button","success")
        }
        binding.activityCareerSaveBtn.setOnClickListener {
            //경력 편집 저장
            viewModel.patchCareerInfo()
            (activity as ContainerActivity).onBackPressed()
            Log.d("career_add_button","success")
        }



        //회사 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityCareerEtCompanyDesc)

        //직함 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityCareerEtPosition)

        //재직기간_시작 입력 시 레이아웃 테두리 변경 -> 달력으로 바꿔야함
        checkDpInput(binding.activityCareerEtStartPeriod)

        //재직기간_종료 시 레이아웃 테두리 변경 -> 달력으로 바꿔야함
        checkDpInput(binding.activityCareerEtEndPeriod)

        //재직 정보 date picker
        binding.activityCareerEtStartPeriod.setOnClickListener {
                val orderBottomDialogFragment: DatePickerDialogFragment = DatePickerDialogFragment {
                    val arr = it.split(".")
                    binding.activityCareerEtStartPeriod.setText(arr[0]+"."+arr[1])
                    checkDpInput(binding.activityCareerEtStartPeriod)
                }
                orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }
        binding.activityCareerEtEndPeriod.setOnClickListener {
            val orderBottomDialogFragment: DatePickerDialogFragment = DatePickerDialogFragment {
                val arr = it.split(".")
                viewModel.endDate.value = arr[0]+"."+arr[1]
                if(checkNow(it)){
                    binding.activityCareerCheckbox.isChecked = true
                    //binding.activityCareerEtEndPeriod.setText("현재")
                    viewModel.isWorking.value = "TRUE"
                    Log.d("edit_Date","yes")

                }else{
                    binding.activityCareerCheckbox.isChecked = false
                    binding.activityCareerEtEndPeriod.setText(arr[0]+"."+arr[1])
                    viewModel.isWorking.value = "FALSE"
                    Log.d("edit_Date","no")

                }
                checkDpInput(binding.activityCareerEtEndPeriod)
            }
            orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }
    }
    private fun checkDpInput(view:TextView){
        if (checkInfo()){
            //binding.activityCareerSaveBtn.isClickable = true
            binding.activityCareerSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
        }else{
            //binding.activityCareerSaveBtn.isClickable = false
            binding.activityCareerSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
        }
    }
    private fun checkEtInput(view: EditText) {
        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.register_et_border_selected)
            } else {
                view.setBackgroundResource(R.drawable.register_et_border)
            }
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkInfo()){
                    //binding.activityCareerSaveBtn.isClickable = true
                    binding.activityCareerSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
                }else{
                   // binding.activityCareerSaveBtn.isClickable = false
                    binding.activityCareerSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
                }
            }
        })

    }

    private fun checkNow(inputDate :String) : Boolean {
        //현재 재직 중일 때
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        var formatted = current.format(formatter)
        Log.d("edit_Date",(inputDate >= formatted).toString())
        return inputDate >= formatted
    }
    fun checkInfo() : Boolean{
        var  checkResult = true
        var company = binding.activityCareerEtCompanyDesc.text.toString()
        var position = binding.activityCareerEtPosition.text.toString()
        var start = binding.activityCareerEtStartPeriod.text.toString()
        var end = binding.activityCareerEtEndPeriod.text.toString()

        //회사 조건 확인 기능
        if(company.length > 22) {
            checkResult = false
        }
        //직함 조건 확인 기능
        if(position.length > 22) {
            checkResult = false
        }
        //시작년월 조건 확인 기능
        if(start.isEmpty()) checkResult = false

        //종료년월 조건 확인 기능
        if(end.isEmpty()) checkResult = false

        return checkResult
    }
    override fun onYesButtonClick(id: Int) {
        (activity as ContainerActivity).onBackPressed()
    }

}

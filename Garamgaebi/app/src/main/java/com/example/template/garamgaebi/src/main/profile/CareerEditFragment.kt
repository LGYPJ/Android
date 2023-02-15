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
            activityCareerEtEndPeriod.text = originEnd
            if(originNow.equals("TRUE")){
                activityCareerEtEndPeriod.text = "현재"
            }
        }

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
            //startHint.value = getString(R.string.register_input_company_desc)
            //companyHint.value = getString(R.string.register_input_company_desc)
            companyState.value = getString(R.string.caution_input_22)
            positionState.value = getString(R.string.caution_input_22)
        }

        //유효성 끝

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

        //기존의 정보 입력이 되어 있기에 첫 입력 예외 x
        viewModel.companyFirst.value = false
        viewModel.positionFirst.value = false
        viewModel.startFocusing.value = true
        viewModel.startFirst.value = false
        viewModel.endFocusing.value = true
        viewModel.endFirst.value = false

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
                            viewModel.isWorking.value = "FALSE"
                        }
                    }
                }
            orderBottomDialogFragment?.show(parentFragmentManager, orderBottomDialogFragment.tag)

        }
    }
    //뒤로가기
    override fun onYesButtonClick(id: Int) {
        (activity as ContainerActivity).onBackPressed()
    }

}

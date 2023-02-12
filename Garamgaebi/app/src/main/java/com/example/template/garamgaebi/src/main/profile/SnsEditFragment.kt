package com.example.template.garamgaebi.src.main.profile

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
import com.example.template.garamgaebi.common.BaseBindingFragment
import com.example.template.garamgaebi.common.BaseFragment
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.common.GaramgaebiFunction
import com.example.template.garamgaebi.databinding.FragmentProfileSnsBinding
import com.example.template.garamgaebi.databinding.FragmentProfileSnsEditBinding
import com.example.template.garamgaebi.model.ProfileDataResponse
import com.example.template.garamgaebi.model.SNSData
import com.example.template.garamgaebi.viewModel.EditTextViewModel
import com.example.template.garamgaebi.viewModel.ProfileViewModel
import com.example.template.garamgaebi.viewModel.SNSViewModel

class SnsEditFragment  : BaseBindingFragment<FragmentProfileSnsEditBinding>(R.layout.fragment_profile_sns_edit) {
    private lateinit var callback: OnBackPressedCallback
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[SNSViewModel::class.java]
        binding.setVariable(BR.snsViewModel,viewModel)

        binding.snsViewModel = viewModel

        val editTextViewModel = ViewModelProvider(this)[EditTextViewModel::class.java]
        binding.setVariable(BR.editTextViewModel,editTextViewModel)

        binding.editTextViewModel = editTextViewModel

        val snsIdx = GaramgaebiApplication.sSharedPreferences.getInt("SNSIdxForEdit",-1)
        val originAddress = GaramgaebiApplication.sSharedPreferences.getString("SNSAddressForEdit","Error")
        val originType = GaramgaebiApplication.sSharedPreferences.getString("SNSTypeForEdit","Error")
        Log.d("go_edit_sns",snsIdx.toString()+ originAddress + originType)

        viewModel.snsIdx = snsIdx
        viewModel.snsType.value = originType
        viewModel.snsAddress.value = originAddress

        // 유효성 확인
        viewModel.snsType.observe(viewLifecycleOwner, Observer {
            binding.snsViewModel = viewModel
            viewModel.snsTypeIsValid.value = it.isNotEmpty()
            Log.d("sns_type_true",it.isNotEmpty().toString())
        })
        viewModel.snsAddress.observe(viewLifecycleOwner, Observer {
            binding.snsViewModel = viewModel
            viewModel.snsAddressIsValid.value = it.isNotEmpty()
            Log.d("sns_address_true",it.isNotEmpty().toString())
        })

        binding.activitySnsSaveBtn.setOnClickListener {
            //편집 저장하기
            viewModel.patchSNSInfo()
            Log.d("sns_edit_button","success")
        }
        binding.activitySnsRemoveBtn.setOnClickListener {
            //삭제하기
            viewModel.deleteSNSInfo()
            Log.d("sns_remove_button","success")
        }


    }
}

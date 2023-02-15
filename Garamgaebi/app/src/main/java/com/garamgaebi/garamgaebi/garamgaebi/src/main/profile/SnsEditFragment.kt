package com.garamgaebi.garamgaebi.garamgaebi.src.main.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentProfileSnsEditBinding
import com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel
import com.garamgaebi.garamgaebi.garamgaebi.viewModel.SNSViewModel

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

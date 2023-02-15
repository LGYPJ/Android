package com.garamgaebi.garamgaebi.garamgaebi.src.main.profile

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentProfileSnsBinding
import com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel
import com.garamgaebi.garamgaebi.garamgaebi.viewModel.SNSViewModel

class SnsAddFragment  : BaseBindingFragment<FragmentProfileSnsBinding>(R.layout.fragment_profile_sns) {
    private lateinit var callback: OnBackPressedCallback
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[SNSViewModel::class.java]
        binding.setVariable(BR.snsViewModel,viewModel)
        binding.lifecycleOwner = this

        binding.snsViewModel = viewModel

        val editTextViewModel = ViewModelProvider(this)[EditTextViewModel::class.java]
        binding.setVariable(BR.editTextViewModel,editTextViewModel)


        viewModel.typeInputDesc.value = getString(R.string.sns_add_type_desc)
        viewModel.addressInputDesc.value = getString(R.string.sns_add_link_desc)

        // 유효성 확인
        viewModel.snsType.observe(viewLifecycleOwner, Observer {
            binding.snsViewModel = viewModel
            viewModel.snsTypeIsValid.value = it.isNotEmpty()

            when(it){

                "인스타그램" -> {
                }
                "블로그" -> {
                }
                "깃허브" -> {
                }
                "" -> {
                    viewModel.typeState.value = getString(R.string.caution_input_22)
                    viewModel.snsTypeIsValid.value = it.length < 22  && it.isNotEmpty()
                }

            }
            Log.d("sns_type_true",it.isNotEmpty().toString())
        })
        viewModel.snsAddress.observe(viewLifecycleOwner, Observer {
            binding.snsViewModel = viewModel
            //유효성 확인
            viewModel.snsAddressIsValid.value = it.length < 45 && it.isNotEmpty()

            Log.d("sns_address_true",viewModel.snsAddressIsValid.value.toString())
        })



        binding.activitySnsSaveBtn.setOnClickListener {
            viewModel.postSNSInfo()
            Log.d("sns_add_button","success")
        }
        viewModel.linkState.value = getString(R.string.sns_type_dialog_etc_state)


        binding.activitySnsEtName.isFocusable = false

        binding.activitySnsEtName.setOnClickListener {
            binding.activitySnsEtName.isFocusable = false
            viewModel.typeFirst.value = false

            val orderBottomDialogFragment: SnsOrderBottomDialogFragment = SnsOrderBottomDialogFragment {
                when (it) {
                    0 -> {
                        Toast.makeText(activity, "인스타그램", Toast.LENGTH_SHORT).show()
                        viewModel.snsType.value = "인스타그램"
                        viewModel.addressInputDesc.value = getString(R.string.sns_type_dialog_insta_desc)
                        viewModel.linkState.value = getString(R.string.sns_type_dialog_insta_state)
                    }
                    1 -> {
                        Toast.makeText(activity, "블로그", Toast.LENGTH_SHORT).show()
                        viewModel.snsType.value = "블로그"
                        viewModel.addressInputDesc.value = getString(R.string.sns_type_dialog_blog_desc)
                        viewModel.linkState.value = getString(R.string.sns_type_dialog_blog_state)
                    }
                    2 -> {
                        Toast.makeText(activity, "깃허브", Toast.LENGTH_SHORT).show()
                        viewModel.snsType.value = "깃허브"
                        viewModel.addressInputDesc.value = getString(R.string.sns_type_dialog_github_desc)
                        viewModel.linkState.value = getString(R.string.sns_type_dialog_github_state)

                    }
                    3 -> {
                        Toast.makeText(activity, "직접 입력", Toast.LENGTH_SHORT).show()
                        viewModel.snsType.value = ""
                        viewModel.typeInputDesc.value = getString(R.string.sns_type_dialog_etc_desc)
                        viewModel.addressInputDesc.value = getString(R.string.sns_address_dialog_etc_desc)

                        viewModel.linkState.value = getString(R.string.sns_type_dialog_etc_state)

                        binding.activitySnsEtName.isFocusable = true
                        binding.activitySnsEtName.isClickable = false
                        binding.activitySnsEtNameLength.visibility = View.VISIBLE

                    }
                }

            }
            orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }
    }
}

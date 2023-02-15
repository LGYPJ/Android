package com.example.template.garamgaebi.src.main.profile

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.garamgaebi.BR
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseBindingFragment
import com.example.template.garamgaebi.databinding.FragmentProfileSnsBinding
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.viewModel.EditTextViewModel
import com.example.template.garamgaebi.viewModel.SNSViewModel


class SnsAddFragment  : BaseBindingFragment<FragmentProfileSnsBinding>(R.layout.fragment_profile_sns) {
    private lateinit var callback: OnBackPressedCallback
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[SNSViewModel::class.java]
        binding.setVariable(BR.snsViewModel,viewModel)
        binding.lifecycleOwner = this

        binding.snsViewModel = viewModel

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
                else -> {
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
            (activity as ContainerActivity).onBackPressed()
            Log.d("sns_add_button","success")
        }
        viewModel.linkState.value = getString(R.string.sns_type_dialog_etc_state)
        binding.activitySnsEtLinkDesc.setOnClickListener {
        }

        //dialog 띄우기
        binding.activitySnsEtName.isFocusable = false
        binding.activitySnsEtName.isFocusableInTouchMode = false

        var editType : Boolean = true
        binding.activitySnsEtName.setOnClickListener {

            if(editType) {
                val orderBottomDialogFragment: SnsOrderBottomDialogFragment =
                    SnsOrderBottomDialogFragment {
                        when (it) {
                            0 -> {
                                Toast.makeText(activity, "인스타그램", Toast.LENGTH_SHORT).show()
                                viewModel.snsType.value = "인스타그램"
                                viewModel.addressInputDesc.value =
                                    getString(R.string.sns_type_dialog_insta_desc)
                                viewModel.linkState.value =
                                    getString(R.string.sns_type_dialog_insta_state)
                            }
                            1 -> {
                                Toast.makeText(activity, "블로그", Toast.LENGTH_SHORT).show()
                                viewModel.snsType.value = "블로그"
                                viewModel.addressInputDesc.value =
                                    getString(R.string.sns_type_dialog_blog_desc)
                                viewModel.linkState.value =
                                    getString(R.string.sns_type_dialog_blog_state)
                            }
                            2 -> {
                                Toast.makeText(activity, "깃허브", Toast.LENGTH_SHORT).show()
                                viewModel.snsType.value = "깃허브"
                                viewModel.addressInputDesc.value =
                                    getString(R.string.sns_type_dialog_github_desc)
                                viewModel.linkState.value =
                                    getString(R.string.sns_type_dialog_github_state)

                            }
                            3 -> {
                                Toast.makeText(activity, "직접 입력", Toast.LENGTH_SHORT).show()
                                viewModel.snsType.value = ""
                                viewModel.typeInputDesc.value =
                                    getString(R.string.sns_type_dialog_etc_desc)
                                viewModel.addressInputDesc.value =
                                    getString(R.string.sns_address_dialog_etc_desc)

                                viewModel.linkState.value =
                                    getString(R.string.sns_type_dialog_etc_state)
                                // fragment
                                binding.activitySnsEtNameLength.visibility = View.VISIBLE
                                binding.activitySnsEtName.isFocusable = true
                                binding.activitySnsEtName.isFocusableInTouchMode = true

                                editType = false
                            }
                        }

                    }
                orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
            }else{

            }
        }
    }
}

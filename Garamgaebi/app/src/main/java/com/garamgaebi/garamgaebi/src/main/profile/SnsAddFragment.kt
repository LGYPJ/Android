package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.marginStart
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.databinding.FragmentProfileSnsBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.SNSViewModel
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit


class SnsAddFragment  : BaseBindingFragment<FragmentProfileSnsBinding>(R.layout.fragment_profile_sns) {
    private lateinit var callback: OnBackPressedCallback
    @SuppressLint("ClickableViewAccessibility")
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
            viewModel.snsAddress.value =""
            when(it){
                "인스타그램" -> {
                    binding.instaChar.text = "@"
                    binding.activitySnsEtLinkDesc.setPadding(70,0,0,0)
                    binding.instaChar.visibility = View.VISIBLE
                }
                "블로그" -> {
                    binding.instaChar.visibility = View.GONE
                    binding.activitySnsEtLinkDesc.setPadding(30,0,0,0)

                }
                "깃허브" -> {
                    binding.instaChar.visibility = View.GONE
                    binding.activitySnsEtLinkDesc.setPadding(30,0,0,0)


                }
                else -> {
                    binding.instaChar.visibility = View.GONE
                    binding.activitySnsEtLinkDesc.setPadding(30,0,0,0)


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
            Log.d("sns_address_first",viewModel.addressFirst.value.toString())
            Log.d("sns_type_true",viewModel.snsTypeIsValid.value.toString())
            Log.d("sns_type_first",viewModel.typeFirst.value.toString())

        })



        disposables
            .add(
                binding
                    .activitySnsSaveBtn
                    .clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.postSNSInfo()
                        Log.d("sns_add_button","success")
                        (activity as ContainerActivity).onBackPressed()
                    }, { it.printStackTrace() })
            )


        viewModel.linkState.value = getString(R.string.sns_type_dialog_etc_state)
        binding.activitySnsEtLinkDesc.setOnClickListener {
        }

        //dialog 띄우기
        binding.activitySnsEtName.isFocusable = false
        binding.activitySnsEtName.isFocusableInTouchMode = false

        var editType : Boolean = true
        disposables
            .add(
                binding
                    .activitySnsEtName
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({

                        if(editType) {

                            val orderBottomDialogFragment: SnsOrderBottomDialogFragment =
                                SnsOrderBottomDialogFragment {
                                    when (it) {
                                        0 -> {
                                            Toast.makeText(activity, "인스타그램", Toast.LENGTH_SHORT).show()
                                            viewModel.snsType.value = "인스타그램"
                                            viewModel.addressInputDesc.value =
                                                " " + getString(R.string.sns_add_link_desc)
                                            //viewModel.linkState.value =
                                            getString(R.string.sns_type_dialog_insta_state)
                                        }
                                        1 -> {
                                            Toast.makeText(activity, "블로그", Toast.LENGTH_SHORT).show()
                                            viewModel.snsType.value = "블로그"
                                            viewModel.addressInputDesc.value = getString(R.string.sns_add_link_desc)
//                                viewModel.addressInputDesc.value =
//                                    getString(R.string.sns_type_dialog_blog_desc)
//                                viewModel.linkState.value =
                                            getString(R.string.sns_type_dialog_blog_state)
                                        }
                                        2 -> {
                                            Toast.makeText(activity, "깃허브", Toast.LENGTH_SHORT).show()
                                            viewModel.snsType.value = "깃허브"
                                            viewModel.addressInputDesc.value = getString(R.string.sns_add_link_desc)
//                                viewModel.addressInputDesc.value =
//                                    getString(R.string.sns_type_dialog_github_desc)
//                                viewModel.linkState.value =
//                                    getString(R.string.sns_type_dialog_github_state)

                                        }
                                        3 -> {
                                            Toast.makeText(activity, "직접 입력", Toast.LENGTH_SHORT).show()
                                            viewModel.snsType.value = ""
                                            viewModel.typeInputDesc.value =
                                                getString(R.string.sns_type_dialog_etc_desc)
                                            viewModel.addressInputDesc.value =
                                                getString(R.string.sns_address_dialog_etc_desc)

                                            //viewModel.linkState.value =
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
                    }, { it.printStackTrace() })
            )

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
}

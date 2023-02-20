package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import com.garamgaebi.garamgaebi.common.ConfirmDialog
import com.garamgaebi.garamgaebi.common.ConfirmDialogInterface
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.FragmentProfileSnsEditBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.SNSViewModel
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

class SnsEditFragment  : BaseBindingFragment<FragmentProfileSnsEditBinding>(R.layout.fragment_profile_sns_edit),
    ConfirmDialogInterface
     {
    private lateinit var callback: OnBackPressedCallback

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[SNSViewModel::class.java]
        binding.setVariable(BR.snsViewModel,viewModel)
        binding.lifecycleOwner = this

        binding.snsViewModel = viewModel

        var viewFirst : Boolean = true

        // 유효성 확인
        viewModel.snsType.observe(viewLifecycleOwner, Observer {
            binding.snsViewModel = viewModel
            viewModel.snsTypeIsValid.value = it.isNotEmpty()
            if(!viewFirst) {
                viewModel.snsAddress.value = ""
                when (it) {
                    "인스타그램" -> {
                        binding.instaChar.text = "@"
                        binding.activitySnsEtLinkDesc.setPadding(65, 0, 0, 0)
                        binding.instaChar.visibility = View.VISIBLE
                    }
                    "블로그" -> {
                        binding.instaChar.visibility = View.GONE
                        binding.activitySnsEtLinkDesc.setPadding(30, 0, 0, 0)

                    }
                    "깃허브" -> {
                        binding.instaChar.visibility = View.GONE
                        binding.activitySnsEtLinkDesc.setPadding(30, 0, 0, 0)


                    }
                    else -> {
                        binding.instaChar.visibility = View.GONE
                        binding.activitySnsEtLinkDesc.setPadding(30, 0, 0, 0)


                        viewModel.typeState.value = getString(R.string.caution_input_22)
                        viewModel.snsTypeIsValid.value = it.length < INPUT_TEXT_LENGTH && it.isNotEmpty()
                    }

                }
            }else{
                viewFirst = false
            }
            Log.d("sns_type_true",it.isNotEmpty().toString())
        })
        viewModel.snsAddress.observe(viewLifecycleOwner, Observer {
            binding.snsViewModel = viewModel
            //유효성 확인
            viewModel.snsAddressIsValid.value = it.length < SNS_ADDRESS && it.isNotEmpty()

            Log.d("sns_address_true",viewModel.snsAddressIsValid.value.toString())
        })

        //내 sns 정보 view에 설정
        val snsIdx = GaramgaebiApplication.sSharedPreferences.getInt("SNSIdxForEdit",-1)
        var originAddress = GaramgaebiApplication.sSharedPreferences.getString("SNSAddressForEdit","Error")
        val originType = GaramgaebiApplication.sSharedPreferences.getString("SNSTypeForEdit","Error")
        Log.d("go_edit_sns",snsIdx.toString()+ originAddress + originType)


        if (originType != null) {
            if(originType.equals("인스타그램") || originType.equals("인스타") ||originType.lowercase().equals("instagram") || originType.lowercase().equals("insta"))
                if (originAddress != null) {
                    originAddress = originAddress.substring(1)
                    binding.instaChar.text = "@"
                    binding.activitySnsEtLinkDesc.setPadding(65, 0, 0, 0)
                    binding.instaChar.visibility = View.VISIBLE
                    viewModel.addressInputDesc.value =
                        " " + getString(R.string.sns_add_link_desc)
                }
        }

        viewModel.snsIdx = snsIdx
        viewModel.snsType.value = originType
        viewModel.snsAddress.value = originAddress


        disposables
            .add(
                binding
                    .activitySnsSaveBtn
                    .clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.patchSNSInfo()
                        Log.d("sns_add_button","success")
                        (activity as ContainerActivity).onBackPressed()
                    }, { it.printStackTrace() })
            )

        disposables
            .add(
                binding
                    .activitySnsRemoveBtn
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        val dialog: DialogFragment? = ConfirmDialog(this,getString(R.string.delete_q), 1) { it ->
                            when (it) {
                                -1 -> {

                                    Log.d("sns_edit_button","close")

                                }
                                1 -> {
                                    //경력 삭제
                                    viewModel.deleteSNSInfo()
                                    val dialog = ConfirmDialog(this, getString(R.string.delete_done), -1){it2 ->
                                        when(it2){
                                            1 -> {

                                                Log.d("sns_edit_button","close")

                                            }
                                            2->{
                                                (activity as ContainerActivity).onBackPressed()
                                            }
                                        }
                                    }
                                    // 알림창이 띄워져있는 동안 배경 클릭 막기
                                    dialog.show(activity?.supportFragmentManager!!, "com.example.garamgaebi.common.ConfirmDialog")
                                }
                            }
                        }
                        // 알림창이 띄워져있는 동안 배경 클릭 막기
                        dialog?.show(activity?.supportFragmentManager!!, "com.example.garamgaebi.common.ConfirmDialog")
                        Log.d("sns_edit_button","success")
                    }, { it.printStackTrace() })
            )
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

        binding.activitySnsEtName.isFocusable = false
        binding.activitySnsEtName.isFocusableInTouchMode = false


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
         //삭제 확인 버튼
    override fun onYesButtonClick(id: Int) {
    }
}

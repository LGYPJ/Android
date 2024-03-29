package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.getSNS
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.networkValid
import com.garamgaebi.garamgaebi.databinding.FragmentProfileSnsBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.SNSViewModel
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

/*
SNS 추가 Fragment - ContainerActivity

SMS 추가
 */
class SnsAddFragment  : BaseBindingFragment<FragmentProfileSnsBinding>(R.layout.fragment_profile_sns) {

    @SuppressLint("ClickableViewAccessibility", "ResourceAsColor")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[SNSViewModel::class.java]
        binding.setVariable(BR.snsViewModel,viewModel)
        binding.lifecycleOwner = this

        binding.snsViewModel = viewModel

        viewModel.typeInputDesc.value = getString(R.string.sns_add_type_desc)
        viewModel.addressInputDesc.value = getString(R.string.sns_add_link_desc)

        viewModel.snsType.observe(viewLifecycleOwner) {
            binding.snsViewModel = viewModel
            viewModel.snsTypeIsValid.value = it.isNotEmpty()
            GaramgaebiFunction().checkFirstChar(viewModel.snsTypeIsValid, it)
            binding.fragmentSnsEtLinkDesc.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

            when (it) {
                "인스타그램" -> {
                    binding.instaChar.visibility = View.VISIBLE
                    binding.instaChar.text = "@"
                    binding.fragmentSnsEtLinkDesc.inputType =
                        InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }
//                "블로그" -> {
//                    binding.instaChar.text = ""
//                }
//                "깃허브" -> {
//                    binding.instaChar.text = ""
//                }
                else -> {
                    binding.instaChar.text = ""
                    viewModel.typeState.value = getString(R.string.caution_input_22)
                    viewModel.snsTypeIsValid.value =
                        it.length < INPUT_TEXT_LENGTH && it.isNotEmpty()
                    GaramgaebiFunction().checkFirstChar(viewModel.snsTypeIsValid, it)
                }
            }
            Log.d("sns_type_true", it.isNotEmpty().toString())
        }
        viewModel.snsAddress.observe(viewLifecycleOwner) {
            binding.snsViewModel = viewModel
            //유효성 확인
            viewModel.snsAddressIsValid.value = it.length < SNS_ADDRESS && it.isNotEmpty()
            if (viewModel.snsType.value == "인스타그램") {
                viewModel.snsAddressIsValid.value =
                    Pattern.matches("^[0-9a-zA-Z_]([0-9-a-zA-Z._-]){0,46}$", it)
                if (it.isNotEmpty()) {
                    if (it.toCharArray()[0] == '.' || it.toCharArray()[it.length - 1] == '.')
                        viewModel.snsAddressIsValid.value = false
                }
            }
            GaramgaebiFunction().checkFirstChar(viewModel.snsAddressIsValid, it)
        }

        viewModel._add.observe(viewLifecycleOwner) {
            binding.snsViewModel = viewModel

            if (viewModel._add.value?.result == true){
                getSNS = true
                (activity as ContainerActivity).onBackPressed()
            }else{
                (requireActivity() as ContainerActivity).networkAlertDialog()
            }

        }

        disposables
            .add(
                binding
                    .fragmentSnsSaveBtn
                    .clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if(networkValid.value == true) {
                            viewModel.postSNSInfo()
                        }else {
                            (requireActivity() as ContainerActivity).networkAlertDialog()
                        }
                        Log.d("sns_add_button","success")
                        //(activity as ContainerActivity).onBackPressed()
                    }, { it.printStackTrace() })
            )


        viewModel.linkState.value = getString(R.string.sns_type_dialog_etc_state)
        binding.fragmentSnsEtLinkDesc.setOnClickListener {
        }

        //dialog 띄우기
        binding.fragmentSnsEtName.isFocusable = false
        binding.fragmentSnsEtName.isFocusableInTouchMode = false

        var editType = true
        disposables
            .add(
                binding
                    .fragmentSnsEtName
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({

                        viewModel.snsTypeFocusing.value = true
                        if(editType) {

                            val orderBottomDialogFragment =
                                OrderBottomDialogFragment(resources.getStringArray(R.array.sns_option)) {
                                    when (it) {
                                        0 -> {
                                            viewModel.snsAddress.value =""
                                            Toast.makeText(activity, "인스타그램", Toast.LENGTH_SHORT).show()
                                            viewModel.snsType.value = "인스타그램"
                                            viewModel.addressInputDesc.value =
                                                " " + getString(R.string.sns_add_link_desc)


                                        }
                                        1 -> {
                                            viewModel.snsAddress.value =""
                                            Toast.makeText(activity, "블로그", Toast.LENGTH_SHORT).show()
                                            viewModel.snsType.value = "블로그"
                                            viewModel.addressInputDesc.value = getString(R.string.sns_add_link_desc)

                                        }
                                        2 -> {
                                            viewModel.snsAddress.value =""
                                            Toast.makeText(activity, "깃허브", Toast.LENGTH_SHORT).show()
                                            viewModel.snsType.value = "깃허브"
                                            viewModel.addressInputDesc.value = getString(R.string.sns_add_link_desc)

                                        }
                                        3 -> {
                                            Toast.makeText(activity, "직접 입력", Toast.LENGTH_SHORT).show()
                                            viewModel.snsType.value = ""
                                            viewModel.typeInputDesc.value =
                                                getString(R.string.sns_type_dialog_etc_desc)
                                            viewModel.addressInputDesc.value =
                                                getString(R.string.sns_address_dialog_etc_desc)
                                            viewModel.snsAddress.value =""

                                            // fragment
                                            binding.fragmentSnsEtNameLength.visibility = View.VISIBLE
                                            binding.fragmentSnsEtName.isFocusable = true
                                            binding.fragmentSnsEtName.isFocusableInTouchMode = true

                                            editType = false
                                        }
                                    }
                                    binding.fragmentSnsEtName.clearFocus()
                                    viewModel.snsTypeFocusing.value = false
                                }
                            orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
                        }
                    }, { it.printStackTrace() })
            )

        binding.containerLayout.setOnTouchListener { _, _ ->
            hideKeyboard()
            false
        }

        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
            onShowKeyboard = { keyboardHeight ->
                binding.svRoot.run {
                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
                }
                binding.fragmentSnsSaveBtn.visibility = View.GONE
            },
            onHideKeyboard = {
               // binding.fragmentSnsSaveBtn.visibility = View.VISIBLE
            }
        )
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)

            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight < screenHeight * 0.15) {
                // 키보드가 완전히 내려갔음을 나타내는 동작을 구현합니다.
                binding.fragmentSnsSaveBtn.postDelayed({
                    binding.fragmentSnsSaveBtn.visibility = View.VISIBLE
                }, 0)
            }
        }
    }

    override fun onDestroy() {
        keyboardVisibilityUtils.detachKeyboardListeners()
        super.onDestroy()
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

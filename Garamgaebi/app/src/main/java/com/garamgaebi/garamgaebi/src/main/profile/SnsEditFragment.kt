package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import com.garamgaebi.garamgaebi.common.ConfirmDialog
import com.garamgaebi.garamgaebi.common.ConfirmDialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.FragmentProfileSnsEditBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.SNSViewModel
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

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

        viewModel.snsType.observe(viewLifecycleOwner, Observer {
            binding.snsViewModel = viewModel
            viewModel.snsTypeIsValid.value = it.isNotEmpty()
            GaramgaebiFunction().checkFirstChar(viewModel.snsTypeIsValid, it)
            binding.fragmentSnsEtLinkDesc.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS

            when(it){
                "인스타그램" -> {
                    binding.instaChar.visibility = View.VISIBLE
                    binding.instaChar.text = "@"
                    binding.fragmentSnsEtLinkDesc.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                }
                "블로그" -> {
                    binding.instaChar.text = ""
                }
                "깃허브" -> {
                    binding.instaChar.text = ""
                }
                else -> {
                    binding.instaChar.text = ""
                    viewModel.typeState.value = getString(R.string.caution_input_22)
                    viewModel.snsTypeIsValid.value = it.length < INPUT_TEXT_LENGTH && it.isNotEmpty()
                    GaramgaebiFunction().checkFirstChar(viewModel.snsTypeIsValid, it)
                }
            }
            Log.d("sns_type_true",it.isNotEmpty().toString())
        })
        viewModel.snsAddress.observe(viewLifecycleOwner, Observer {
            binding.snsViewModel = viewModel
            //유효성 확인
            viewModel.snsAddressIsValid.value = it.length < SNS_ADDRESS && it.isNotEmpty()
            if (viewModel.snsType.value == "인스타그램"){
                viewModel.snsAddressIsValid.value = Pattern.matches("^[0-9a-zA-Z_]([0-9-a-zA-Z._-]){0,46}$", it)
                if(it.isNotEmpty()){
                    if(it.toCharArray()[0] == '.' || it.toCharArray()[it.length-1] == '.')
                        viewModel.snsAddressIsValid.value = false
                }
            }
            GaramgaebiFunction().checkFirstChar(viewModel.snsAddressIsValid, it)


            Log.d("sns_address_true",viewModel.snsAddressIsValid.value.toString())
        })

        viewModel._patch.observe(viewLifecycleOwner) {
            binding.snsViewModel = viewModel

            if (viewModel._patch.value?.result == true){
                GaramgaebiApplication.getSNS = true
                (activity as ContainerActivity).onBackPressed()
            }else{
                networkAlertDialog()
            }

        }
        //삭제 관측
        viewModel._delete.observe(viewLifecycleOwner) {
            binding.snsViewModel = viewModel
            Log.d("career_delete", viewModel._patch.value?.result.toString())
            if (viewModel._delete.value?.result == true){
                GaramgaebiApplication.getSNS = true
                val dialog = ConfirmDialog(
                    this@SnsEditFragment,
                    getString(R.string.delete_done),
                    -1
                ) { it2 ->
                    when (it2) {
                        1 -> {
                            Log.d("sns_remove_button", "close")
                        }
                        2 -> {
                            (activity as ContainerActivity).onBackPressed()
                        }
                    }
                }
                // 알림창이 띄워져있는 동안 배경 클릭 막기
                dialog.show(
                    activity?.supportFragmentManager!!,
                    "com.example.garamgaebi.common.ConfirmDialog"
                )
            }else{
                networkAlertDialog()
            }

        }

        var snsIdx = -1
        var originAddress = ""
        var originType = ""

        //내 sns 정보 view에 설정
        val putdata = runBlocking {
            snsIdx = GaramgaebiApplication().loadIntData(
                "SNSIdxForEdit"
            )!!
            originAddress = GaramgaebiApplication().loadStringData(
                "SNSAddressForEdit"
            ).toString()
            originType = GaramgaebiApplication().loadStringData(
                "SNSTypeForEdit"
            ).toString()
        }

        Log.d("go_edit_sns",snsIdx.toString()+ originAddress + originType)


        if (originType != null) {
            if(originType.equals("인스타그램") || originType.equals("인스타") ||originType.lowercase().equals("instagram") || originType.lowercase().equals("insta"))
                if (originAddress != null) {
                    originAddress = originAddress.substring(1)
                    binding.instaChar.text = "@"
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
                    .fragmentSnsSaveBtn
                    .clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if(networkValid.value == true) {
                            viewModel.patchSNSInfo()
                            networkValid.postValue(true)
                        }else {
                            networkAlertDialog()
                        }
                        Log.d("sns_add_button","success")
                        //(activity as ContainerActivity).onBackPressed()
                    }, { it.printStackTrace() })
            )

        disposables
            .add(
                binding
                    .fragmentSnsRemoveBtn
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        val dialog: DialogFragment? = ConfirmDialog(this,getString(R.string.delete_q), 1) { it ->
                            when (it) {
                                -1 -> {

                                    Log.d("sns_edit_button","close")

                                }
                                1 -> {
                                    if(networkValid.value == true) {
                                        //경력 삭제
                                        viewModel.deleteSNSInfo()
                                    }else {
                                        networkAlertDialog()
                                    }
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
                    .fragmentSnsEtName
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({

                        if(editType) {

                            val orderBottomDialogFragment: OrderBottomDialogFragment =
                                OrderBottomDialogFragment(resources.getStringArray(R.array.sns_option)) {
                                    when (it) {
                                        0 -> {
                                            viewModel.snsAddress.value =""
                                            Toast.makeText(activity, "인스타그램", Toast.LENGTH_SHORT).show()
                                            viewModel.snsType.value = "인스타그램"
                                            viewModel.addressInputDesc.value =
                                                " " + getString(R.string.sns_add_link_desc)
                                            //viewModel.linkState.value =
                                            getString(R.string.sns_type_dialog_insta_state)
                                        }
                                        1 -> {
                                            viewModel.snsAddress.value =""
                                            Toast.makeText(activity, "블로그", Toast.LENGTH_SHORT).show()
                                            viewModel.snsType.value = "블로그"
                                            viewModel.addressInputDesc.value = getString(R.string.sns_add_link_desc)
//                                viewModel.addressInputDesc.value =
//                                    getString(R.string.sns_type_dialog_blog_desc)
//                                viewModel.linkState.value =
                                            getString(R.string.sns_type_dialog_blog_state)
                                        }
                                        2 -> {
                                            viewModel.snsAddress.value =""
                                            Toast.makeText(activity, "깃허브", Toast.LENGTH_SHORT).show()
                                            viewModel.snsType.value = "깃허브"
                                            viewModel.addressInputDesc.value = getString(R.string.sns_add_link_desc)
//                                viewModel.addressInputDesc.value =
//                                    getString(R.string.sns_type_dialog_github_desc)
//                                viewModel.linkState.value =
//                                    getString(R.string.sns_type_dialog_github_state)

                                        }
                                        3 -> {
                                            viewModel.snsAddress.value =""
                                            Toast.makeText(activity, "직접 입력", Toast.LENGTH_SHORT).show()
                                            viewModel.snsType.value = ""
                                            viewModel.typeInputDesc.value =
                                                getString(R.string.sns_type_dialog_etc_desc)
                                            viewModel.addressInputDesc.value =
                                                getString(R.string.sns_address_dialog_etc_desc)

                                            //viewModel.linkState.value =
                                            getString(R.string.sns_type_dialog_etc_state)
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
                        }else{

                        }
                    }, { it.printStackTrace() })
            )

        binding.fragmentSnsEtName.isFocusable = false
        binding.fragmentSnsEtName.isFocusableInTouchMode = false


        binding.containerLayout.setOnTouchListener(View.OnTouchListener { v, event ->
            hideKeyboard()
            false
        })

        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
            onShowKeyboard = { keyboardHeight ->
                binding.svRoot.run {
                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
                }
                binding.fragmentSnsSaveBtn.visibility = View.GONE
                binding.fragmentSnsRemoveBtn.visibility = View.GONE
            },
            onHideKeyboard = { ->
                //binding.fragmentSnsSaveBtn.visibility = View.VISIBLE
            }
        )
        view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val rect = Rect()
                view.getWindowVisibleDisplayFrame(rect)

                val screenHeight = view.rootView.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight < screenHeight * 0.15) {
                    // 키보드가 완전히 내려갔음을 나타내는 동작을 구현합니다.
                    binding.fragmentSnsSaveBtn.postDelayed({
                        binding.fragmentSnsSaveBtn.visibility = View.VISIBLE
                    },0)
                    binding.fragmentSnsRemoveBtn.postDelayed({
                        binding.fragmentSnsRemoveBtn.visibility = View.VISIBLE
                    },0)
                }
            }
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

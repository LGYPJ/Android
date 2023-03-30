package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.core.app.ActivityCompat
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.FragmentWithdrawalBinding
import com.garamgaebi.garamgaebi.model.InactiveSuccess
import com.garamgaebi.garamgaebi.model.WithdrawalResponse
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.src.main.register.LoginActivity
import com.garamgaebi.garamgaebi.viewModel.WithdrawalViewModel
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

/*
탈퇴 Fragment - ContainerActivity

탈퇴 조회
 */
class WithdrawalFragment :
    BaseBindingFragment<FragmentWithdrawalBinding>(R.layout.fragment_withdrawal),
    ConfirmDialogInterface {
    var containerActivity: ContainerActivity? = null
    private var callback: OnBackPressedCallback? = null

    @SuppressLint("SuspiciousIndentation", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var myEmail = "not@gachon.ac.kr"
        runBlocking {
            myEmail = GaramgaebiApplication().loadStringData("uniEmail").toString()
        }
        val viewModel = ViewModelProvider(this)[WithdrawalViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        if (myEmail != null) {
            viewModel.email.value = myEmail
        }

        viewModel.category.observe(viewLifecycleOwner) {
            if (it.isNotEmpty() && (it.toCharArray()[0] != ' '))
                viewModel.categoryIsValid.value = true

            if (it.equals("ETC")) {
                binding.activityContentEtNameLength.visibility = View.VISIBLE
                viewModel.content.value = ""
            } else {
                binding.activityContentEtNameLength.visibility = View.GONE
                viewModel.contentIsValid.value = true
                viewModel.contentFirst.value = false

            }
        }
        viewModel.content.observe(viewLifecycleOwner) {

            Log.d("content", "되냐")
            viewModel.contentIsValid.value = it.length < INPUT_TEXT_LENGTH_100 && it.isNotEmpty()
            GaramgaebiFunction().checkFirstChar(viewModel.contentIsValid, it)
        }
        viewModel.agree.observe(viewLifecycleOwner) {
            viewModel.agreeIsValid.value = binding.fragmentWithdrawalCheckbox.isChecked
        }

        viewModel._withdrawal.observe(viewLifecycleOwner) {

            Log.d("withdrawal","입장")
            if (viewModel._withdrawal.value?.isSuccess == true || viewModel._withdrawal.value?.errorCode ==200){
                runBlocking { // 비동기 작업 시작
                    GaramgaebiApplication().saveStringToDataStore(
                        "kakaoToken",
                        ""
                    )
                    GaramgaebiApplication().saveStringToDataStore(
                        GaramgaebiApplication.X_ACCESS_TOKEN,
                        ""
                    )
                    GaramgaebiApplication().saveStringToDataStore(
                        GaramgaebiApplication.X_REFRESH_TOKEN,
                        ""
                    )
                    GaramgaebiApplication().saveIntToDataStore(
                        "memberIdx",
                        -1
                    )
                    GaramgaebiApplication().clearDataStore()
                    Log.d("withdrawal","block")

                }

                GaramgaebiApplication.myMemberIdx = -1
                val dialog =
                    ConfirmDialog(this, "탈퇴가 완료되었습니다", -1) { it2 ->
                        when (it2) {
                            1 -> {

                                Log.d("withdrawal_button", "close")
                                val i = (Intent(
                                    activity,
                                    LoginActivity::class.java
                                ))
                                i.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                ActivityCompat.finishAffinity(
                                    requireActivity()
                                )
                                startActivity(i)
                                Log.d("logout_button", "main")
                            }
                            2 -> {
                                val i = (Intent(
                                    activity,
                                    LoginActivity::class.java
                                ))
                                i.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                ActivityCompat.finishAffinity(
                                    requireActivity()
                                )
                                startActivity(i)
                                Log.d("logout_button", "main")
                            }
                        }
                    }
                // 알림창이 띄워져있는 동안 배경 클릭 막기
                dialog.show(
                    activity?.supportFragmentManager!!,
                    "com.example.garamgaebi.common.ConfirmDialog"
                )
            }else{
                (requireActivity() as ContainerActivity).networkAlertDialog()
            }

        }



        //동의 체크박스 클릭 이벤트
        disposables
            .add(
                binding
                    .fragmentWithdrawalTvCheckboxDesc
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        val preCheck = binding.fragmentWithdrawalCheckbox.isChecked
                        viewModel.agree.value = !preCheck
                    }, { it.printStackTrace() })
            )
        disposables
            .add(
                binding
                    .fragmentWithdrawalEtOption
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.categoryFocusing.value = true
                        viewModel.categoryFirst.value = false

                        val orderBottomDialogFragment =
                            OrderBottomDialogFragment(resources.getStringArray(R.array.withdrawal_option)) {
                            when (it) {
                                0 -> {
                                    Toast.makeText(activity, "이용이 불편해서", Toast.LENGTH_SHORT).show()
                                    binding.fragmentWithdrawalEtOption.text = "이용이 불편해서"
                                    viewModel.categoryFocusing.value = false
                                    viewModel.category.value = "UNCOMFORTABLE"
                                }
                                1 -> {
                                    Toast.makeText(activity, "사용 빈도가 낮아서", Toast.LENGTH_SHORT).show()
                                    binding.fragmentWithdrawalEtOption.text = "사용 빈도가 낮아서"
                                    viewModel.categoryFocusing.value = false
                                    viewModel.category.value = "UNUSED"
                                }
                                2 -> {
                                    Toast.makeText(activity, "콘텐츠 내용이 부족해서", Toast.LENGTH_SHORT).show()
                                    binding.fragmentWithdrawalEtOption.text = "콘텐츠 내용이 부족해서"
                                    viewModel.categoryFocusing.value = false
                                    viewModel.category.value = "CONTENT_LACK"

                                }
                                3 -> {
                                    Toast.makeText(activity, "기타", Toast.LENGTH_SHORT).show()
                                    binding.fragmentWithdrawalEtOption.text = "기타"
                                    viewModel.categoryFocusing.value = false
                                    viewModel.category.value = "ETC"

                                }

                            }
                        }
                        orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
                    }, { it.printStackTrace() })
            )
        disposables
            .add(
                binding
                    .fragmentWithdrawalSendBtn
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if((requireActivity() as ContainerActivity).networkValid.value == true) {
                            val dialog: DialogFragment =
                                ConfirmDialog(this, "탈퇴하시겠습니까?", 1) {
                                    when (it) {
                                        -1 -> {

                                            Log.d("withdrawal_button", "close")

                                        }
                                        1 -> {
                                            //탈퇴
                                            viewModel.postWithdrawal()

                                        }
                                    }
                                }
                            // 알림창이 띄워져있는 동안 배경 클릭 막기
                            dialog.show(
                                activity?.supportFragmentManager!!,
                                "com.example.garamgaebi.common.ConfirmDialog"
                            )
                            Log.d("withdrawal_button", "success")
                        }else{
                            (requireActivity() as ContainerActivity).networkAlertDialog()
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
//                binding.svRoot.postDelayed({
//                    binding.svRoot.smoothScrollBy(0,binding.fragmentWithdrawalEtContent.bottom)
//                },0)
                binding.fragmentWithdrawalSendBtn.visibility = View.GONE
            },
            onHideKeyboard = {
              //  binding.fragmentWithdrawalSendBtn.visibility = View.VISIBLE
            }
        )
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)

            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight < screenHeight * 0.15) {
                // 키보드가 완전히 내려갔음을 나타내는 동작을 구현합니다.
                binding.fragmentWithdrawalSendBtn.postDelayed({
                    binding.fragmentWithdrawalSendBtn.visibility = VISIBLE
                }, 0)
            }
        }
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


    override fun onDestroy() {
        keyboardVisibilityUtils.detachKeyboardListeners()
        super.onDestroy()
    }
    override fun onYesButtonClick(id: Int) {
        TODO("Not yet implemented")
    }
    @SuppressLint("NotifyDataSetChanged", "ResourceType")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val fragmentManager = requireActivity().supportFragmentManager
        val backStackEntryCount = fragmentManager.backStackEntryCount
        containerActivity = context as ContainerActivity
        if (callback == null) {
            callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            requireActivity().supportFragmentManager.popBackStack()
                            (requireActivity() as ContainerActivity).backServiceCenter()
                        }
                    }
                    //(activity as ContainerActivity).supportFragmentManager.beginTransaction().remove(NetworkingGamePlaceFragment()).commit()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback!!)

    }
    override fun onDetach() {
        super.onDetach()
        //callback?.handleOnBackPressed()
        callback?.remove()

    }
}


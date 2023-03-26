package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.FragmentServicecenterBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.src.main.register.LoginActivity
import com.garamgaebi.garamgaebi.viewModel.ServiceCenterViewModel
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

/*
고객센터 Fragment - ContainerActivity

문의 사항 접수
로그아웃
탈퇴 fragment 이동
 */
class ServiceCenterFragment :
    BaseBindingFragment<FragmentServicecenterBinding>(R.layout.fragment_servicecenter),
    ConfirmDialogInterface {
    var containerActivity: ContainerActivity? = null
    @SuppressLint("SuspiciousIndentation", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //뒤로가기

        val viewModel = ViewModelProvider(this)[ServiceCenterViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.email.observe(viewLifecycleOwner) {
            binding.viewModel = viewModel

            //email 유효성 검사 부분
            viewModel.emailIsValid.value = Patterns.EMAIL_ADDRESS.matcher(it).matches()
            Log.d("qna_email_true", viewModel.emailIsValid.value.toString())
        }

        viewModel.category.observe(viewLifecycleOwner) {
            if (it.isNotEmpty() && (it.toCharArray()[0] != ' '))
                viewModel.categoryIsValid.value = true

            Log.d("qna_category_true", viewModel.categoryIsValid.value.toString())
        }
        viewModel.content.observe(viewLifecycleOwner) {

            viewModel.contentIsValid.value = it.length < INPUT_TEXT_LENGTH_100
            GaramgaebiFunction().checkFirstChar(viewModel.contentIsValid, it)

            Log.d("qna_content_true", viewModel.contentIsValid.value.toString())
        }
        viewModel.agree.observe(viewLifecycleOwner) {

            viewModel.agreeIsValid.value = binding.fragmentServicecenterCheckbox.isChecked

            Log.d("qna_agree_true", viewModel.agreeIsValid.value.toString())
        }

        viewModel._logout.observe(viewLifecycleOwner) {
            if (it.isSuccess) {
                runBlocking { // 비동기 작업 시작
                    GaramgaebiApplication().saveStringToDataStore("kakaoToken", "")
                    GaramgaebiApplication().saveStringToDataStore(
                        GaramgaebiApplication.X_ACCESS_TOKEN,
                        ""
                    )
                    GaramgaebiApplication().saveStringToDataStore(
                        GaramgaebiApplication.X_REFRESH_TOKEN,
                        ""
                    )
                    GaramgaebiApplication().saveStringToDataStore("pushToken", "")
                    GaramgaebiApplication().saveIntToDataStore("memberIdx", -1)
                    GaramgaebiApplication().clearDataStore()
                }

                val target = (Intent(activity, LoginActivity::class.java))
                target.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                finishAffinity(requireActivity())
                startActivity(target)
            } else {
                networkAlertDialog()
            }
        }
        viewModel._qna.observe(viewLifecycleOwner) {
            binding.viewModel = viewModel

            if (viewModel._qna.value?.result == true){
                (activity as ContainerActivity).onBackPressed()
            }else{
                networkAlertDialog()
            }
        }

        //동의 체크박스 클릭 이벤트
        disposables
            .add(
                binding
                    .fragmentServicecenterCheckboxDesc
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        val preCheck = binding.fragmentServicecenterCheckbox.isChecked
                        viewModel.agree.value = !preCheck
                    }, { it.printStackTrace() })
            )

        disposables
            .add(
                binding
                    .fragmentServicecenterSendBtn
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if(networkValid.value == true){
                            viewModel.postQna()
                        }else {
                            networkAlertDialog()
                        }
                    }, { it.printStackTrace() })
            )

        //회원탈퇴 이동
        disposables
            .add(
                binding
                    .fragmentServicecenterTvWithdrawal
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        containerActivity!!.openFragmentOnFrameLayout(15)
                        containerActivity!!.goWithdrawal()
                    }, { it.printStackTrace() })
            )


        //로그아웃 이동
        disposables
            .add(
                binding
                    .fragmentServicecenterTvLogout
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if(networkValid.value == true){
                            val dialog: DialogFragment = ConfirmDialog(this,"로그아웃하시겠습니까?", 3) {
                                when (it) {
                                    -1 -> {
                                        Log.d("logout_button", "close")
                                        //(activity as ContainerActivity).onBackPressed()
                                    }
                                    1 -> {
                                        //로그아웃
                                        viewModel.postLogout()
                                        Log.d("logout_button", "api")

                                    }
                                }
                            }
                            // 알림창이 띄워져있는 동안 배경 클릭 막기
                            dialog.show(activity?.supportFragmentManager!!, "com.example.garamgaebi.common.ConfirmDialog")
                            Log.d("logout_button","success")
                        }else {
                            networkAlertDialog()
                        }
                        //로그아웃으로 이동
                    }, { it.printStackTrace() })
            )

        disposables
            .add(
                binding
                    .fragmentServicecenterEtOption
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.categoryFocusing.value = true
                        viewModel.categoryFirst.value = false
                        binding.fragmentServicecenterEtContent.clearFocus()

                        val orderBottomDialogFragment =
                            OrderBottomDialogFragment(resources.getStringArray(R.array.question_option)) {
                        when (it) {
                                0 -> {
                                    Toast.makeText(activity, "이용 문의", Toast.LENGTH_SHORT).show()
                                    binding.fragmentServicecenterEtOption.text = "이용문의"
                                    viewModel.categoryFocusing.value = false

                                }
                                1 -> {
                                    Toast.makeText(activity, "오류신고", Toast.LENGTH_SHORT).show()
                                    binding.fragmentServicecenterEtOption.text = "오류신고"
                                    viewModel.categoryFocusing.value = false

                                }
                                2 -> {
                                    Toast.makeText(activity, "서비스 제안", Toast.LENGTH_SHORT).show()
                                    binding.fragmentServicecenterEtOption.text = "서비스 제안"
                                    viewModel.categoryFocusing.value = false

                                }
                                3 -> {
                                    Toast.makeText(activity, "기타", Toast.LENGTH_SHORT).show()
                                    binding.fragmentServicecenterEtOption.text = "기타"
                                    viewModel.categoryFocusing.value = false

                                }
                            }
                            if(viewModel.category.value?.isNotEmpty() == true){
                                viewModel.categoryIsValid.value = true
                            }
                        }
                        orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
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
                binding.cvBottom.visibility = View.GONE
                binding.fragmentServicecenterSendBtn.visibility = View.GONE

            },
                onHideKeyboard = {
                    //binding.cvBottom.visibility = View.VISIBLE
                }
            )
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)

            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight < screenHeight * 0.15) {
                // 키보드가 완전히 내려갔음을 나타내는 동작을 구현합니다.
                binding.cvBottom.postDelayed({
                    binding.cvBottom.visibility = View.VISIBLE
                }, 0)
                binding.fragmentServicecenterSendBtn.postDelayed({
                    binding.fragmentServicecenterSendBtn.visibility = View.VISIBLE
                }, 0)
            }
        }
    }
    private fun hideKeyboard() {
        if (activity != null && requireActivity().currentFocus != null) {
            // 프래그먼트기 때문에 getActivity() 사용
            binding.cvBottom.visibility = View.VISIBLE
            val inputManager: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                requireActivity().currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

    override fun onYesButtonClick(id: Int) {
        TODO("Not yet implemented")
    }
}


package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnTouchListener
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.databinding.FragmentServicecenterBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.src.main.register.RegisterLoginActivity
import com.garamgaebi.garamgaebi.viewModel.ServiceCenterViewModel
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit


class ServiceCenterFragment :
    BaseBindingFragment<FragmentServicecenterBinding>(R.layout.fragment_servicecenter) {
    var containerActivity: ContainerActivity? = null

    @SuppressLint("SuspiciousIndentation", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //뒤로가기

        val viewModel = ViewModelProvider(this)[ServiceCenterViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        viewModel.email.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            //email 유효성 검사 부분
            viewModel.emailIsValid.value = it.length < 22 && it.isNotEmpty() && it.contains("@")
            Log.d("qna_email_true",viewModel.emailIsValid.value.toString())
        })

        viewModel.category.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            if(it.isNotEmpty())
                viewModel.categoryIsValid.value = true

            Log.d("qna_category_true",viewModel.categoryIsValid.value.toString())
        })
        viewModel.content.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            viewModel.contentIsValid.value = it.length < 100

            Log.d("qna_content_true",viewModel.contentIsValid.value.toString())
        })
        viewModel.agree.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            viewModel.agreeIsValid.value = binding.activityServicecenterCheckbox.isChecked

            Log.d("qna_agree_true",viewModel.agreeIsValid.value.toString())
        })

        with(viewModel){
            emailHint.value = getString(R.string.response_email_desc)
            emailState.value = getString(R.string.email_wrong_state)

        }

        //동의 체크박스 클릭 이벤트
        disposables
            .add(
                binding
                    .activityServicecenterCheckboxDesc
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        var preCheck = binding.activityServicecenterCheckbox.isChecked
                        viewModel.agree.value = !preCheck
                    }, { it.printStackTrace() })
            )

        disposables
            .add(
                binding
                    .activityServicecenterSendBtn
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.postQna()
                        (activity as ContainerActivity).onBackPressed()
                    }, { it.printStackTrace() })
            )

        //회원탈퇴 이동
        disposables
            .add(
                binding
                    .activityServicecenterSendBtn
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
                    .activityServicecenterTvLogout
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.postLogout()
                        activity?.startActivity(Intent(activity,RegisterLoginActivity::class.java))
                        //로그아웃으로 이동
                    }, { it.printStackTrace() })
            )

        disposables
            .add(
                binding
                    .activityServicecenterEtOption
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.categoryFocusing.value = true
                        viewModel.categoryFirst.value = false

                        val orderBottomDialogFragment: ServiceCenterOrderBottomdialogFragment = ServiceCenterOrderBottomdialogFragment {
                            when (it) {
                                0 -> {
                                    Toast.makeText(activity, "이용 문의", Toast.LENGTH_SHORT).show()
                                    binding.activityServicecenterEtOption.setText("이용문의")
                                    viewModel.categoryFocusing.value = false

                                }
                                1 -> {
                                    Toast.makeText(activity, "오류신고", Toast.LENGTH_SHORT).show()
                                    binding.activityServicecenterEtOption.setText("오류신고")
                                    viewModel.categoryFocusing.value = false

                                }
                                2 -> {
                                    Toast.makeText(activity, "서비스 제안", Toast.LENGTH_SHORT).show()
                                    binding.activityServicecenterEtOption.setText("서비스 제안")
                                    viewModel.categoryFocusing.value = false

                                }
                                3 -> {
                                    Toast.makeText(activity, "기타", Toast.LENGTH_SHORT).show()
                                    binding.activityServicecenterEtOption.setText("기타")
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


        binding.containerLayout.setOnTouchListener(OnTouchListener { v, event ->
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
    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }
}


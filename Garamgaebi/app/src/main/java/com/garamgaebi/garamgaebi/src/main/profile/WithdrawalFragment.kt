package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.FragmentWithdrawalBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.EditTextViewModel
import com.garamgaebi.garamgaebi.viewModel.WithdrawalViewModel
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

class WithdrawalFragment :
    BaseBindingFragment<FragmentWithdrawalBinding>(R.layout.fragment_withdrawal) {
    var containerActivity: ContainerActivity? = null

    @SuppressLint("SuspiciousIndentation", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var myEmail = GaramgaebiApplication.sSharedPreferences.getString("mySchoolEmail","not@gachon.ac.kr")

        binding.activityWithdrawalSendBtn.setOnClickListener {
                //탈퇴 기능 추가
                (activity as ContainerActivity).onBackPressed()
        }
        val viewModel = ViewModelProvider(this)[WithdrawalViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        if (myEmail != null) {
            viewModel.email.value = myEmail
        }

        viewModel.category.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            if(it.isNotEmpty())
                viewModel.categoryIsValid.value = true

            if(it.equals("기타")){
                binding.activityContentEtNameLength.visibility = View.VISIBLE
                viewModel.content.value=""
            }else{
                binding.activityContentEtNameLength.visibility = View.GONE
                viewModel.contentIsValid.value=true
                viewModel.contentFirst.value=false

            }


            Log.d("qna_category_true",viewModel.categoryIsValid.value.toString())
        })
        viewModel.content.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            viewModel.contentIsValid.value = it.length < 100 && it.isNotEmpty()

            Log.d("qna_content_true",viewModel.contentIsValid.value.toString())
        })
        viewModel.agree.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            viewModel.agreeIsValid.value = binding.activityWithdrawalCheckbox.isChecked

            Log.d("qna_agree_true",viewModel.agreeIsValid.value.toString())
        })



        //동의 체크박스 클릭 이벤트
        disposables
            .add(
                binding
                    .activityWithdrawalTvCheckboxDesc
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        var preCheck = binding.activityWithdrawalCheckbox.isChecked
                        viewModel.agree.value = !preCheck
                    }, { it.printStackTrace() })
            )
        disposables
            .add(
                binding
                    .activityWithdrawalEtOption
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.categoryFocusing.value = true
                        viewModel.categoryFirst.value = false

                        val orderBottomDialogFragment: WithdrawalOrderBottomDialogFragment = WithdrawalOrderBottomDialogFragment {
                            when (it) {
                                0 -> {
                                    Toast.makeText(activity, "이용이 불편해서", Toast.LENGTH_SHORT).show()
                                    binding.activityWithdrawalEtOption.setText("이용이 불편해서")
                                    viewModel.categoryFocusing.value = false
                                }
                                1 -> {
                                    Toast.makeText(activity, "사용 빈도가 낮아서", Toast.LENGTH_SHORT).show()
                                    binding.activityWithdrawalEtOption.setText("사용 빈도가 낮아서")
                                    viewModel.categoryFocusing.value = false
                                }
                                2 -> {
                                    Toast.makeText(activity, "콘텐츠 내용이 부족해서", Toast.LENGTH_SHORT).show()
                                    binding.activityWithdrawalEtOption.setText("콘텐츠 내용이 부족해서")
                                    viewModel.categoryFocusing.value = false
                                }
                                3 -> {
                                    Toast.makeText(activity, "기타", Toast.LENGTH_SHORT).show()
                                    binding.activityWithdrawalEtOption.setText("기타")
                                    viewModel.categoryFocusing.value = false
                                }

                            }
                        }
                        orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
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


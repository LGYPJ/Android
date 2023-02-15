package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentWithdrawalBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.EditTextViewModel
import com.garamgaebi.garamgaebi.viewModel.WithdrawalViewModel

class WithdrawalFragment :
    BaseBindingFragment<FragmentWithdrawalBinding>(R.layout.fragment_withdrawal) {
    var containerActivity: ContainerActivity? = null

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //탈퇴기 버튼 클릭이벤트

        var myEmail = GaramgaebiApplication.sSharedPreferences.getString("myEmail","umc@naver.com")

        binding.activityWithdrawalSendBtn.setOnClickListener {
            if (checkInfo() == true){
                //탈퇴 기능 추가
                (activity as ContainerActivity).onBackPressed()
            }else{
                //탈퇴 불가 및 이유
            }
        }
        val viewModel = ViewModelProvider(this)[WithdrawalViewModel::class.java]
        binding.setVariable(BR.withdrawalViewModel,viewModel)
        binding.withdrawalViewModel = viewModel

        val editTextViewModel = ViewModelProvider(this)[EditTextViewModel::class.java]
        binding.setVariable(BR.editTextViewModel,editTextViewModel)


        if (myEmail != null) {
            viewModel.email.value = myEmail
        }

        viewModel.email.observe(viewLifecycleOwner, Observer {
            binding.withdrawalViewModel = viewModel

            //email 유효성 검사 부분
            if(it.length < 22 && it.isNotEmpty())
                viewModel.emailIsValid.value = true

            Log.d("wd_email_true",viewModel.emailIsValid.value.toString())
        })
        viewModel.category.observe(viewLifecycleOwner, Observer {
            binding.withdrawalViewModel = viewModel
            if(it.isNotEmpty())
                viewModel.categoryIsValid.value = true

            if(it.equals("기타")){
                viewModel.contentIsValid.value = false
            }

            Log.d("wd_category_true",viewModel.categoryIsValid.value.toString())
        })
        viewModel.content.observe(viewLifecycleOwner, Observer {
            binding.withdrawalViewModel = viewModel

            if(viewModel.category.value.equals("기타"))
            viewModel.contentIsValid.value = (it.length < 100 && it.isNotEmpty())
            else{
                viewModel.contentIsValid.value = true
            }

            Log.d("wd_content_true",viewModel.contentIsValid.value.toString())
        })
        viewModel.agree.observe(viewLifecycleOwner, Observer {
            binding.withdrawalViewModel = viewModel

            viewModel.agreeIsValid.value = binding.activityWithdrawalCheckbox.isChecked

            Log.d("wd_agree_true",viewModel.agreeIsValid.value.toString())
        })

        //동의 체크박스 클릭 이벤트
        binding.activityWithdrawalTvCheckboxDesc.setOnClickListener {
            var preCheck = binding.activityWithdrawalCheckbox.isChecked
            viewModel.agree.value = !preCheck

            //binding.activityWithdrawalCheckbox.isChecked = !preCheck
//                if (checkInfo()){
//                    binding.activityWithdrawalSendBtn.isClickable = true
//                    binding.activityWithdrawalSendBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
//                }else{
//                    binding.activityWithdrawalSendBtn.isClickable = false
//                    binding.activityWithdrawalSendBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
//                }
        }

        //탈퇴사유 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityWithdrawalEtContent)

        binding.activityWithdrawalEtOption.setOnClickListener {
            val orderBottomDialogFragment: WithdrawalOrderBottomDialogFragment = WithdrawalOrderBottomDialogFragment {
                when (it) {
                    0 -> {
                        Toast.makeText(activity, "이용이 불편해서", Toast.LENGTH_SHORT).show()
                        binding.activityWithdrawalEtOption.setText("이용이 불편해서")
                    }
                    1 -> {
                        Toast.makeText(activity, "사용 빈도가 낮아서", Toast.LENGTH_SHORT).show()
                        binding.activityWithdrawalEtOption.setText("사용 빈도가 낮아서")
                    }
                    2 -> {
                        Toast.makeText(activity, "콘텐츠 내용이 부족해서", Toast.LENGTH_SHORT).show()
                        binding.activityWithdrawalEtOption.setText("콘텐츠 내용이 부족해서")
                    }
                    3 -> {
                        Toast.makeText(activity, "기타", Toast.LENGTH_SHORT).show()
                        binding.activityWithdrawalEtOption.setText("기타")
                    }

                }
            }
            orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }

    }

    fun checkEtInput(view: EditText) {
        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.register_et_border_selected)
            } else {
                view.setBackgroundResource(R.drawable.register_et_border)
            }
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkInfo()){
                    binding.activityWithdrawalSendBtn.isClickable = true
                    binding.activityWithdrawalSendBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
                }else{
                    binding.activityWithdrawalSendBtn.isClickable = false
                    binding.activityWithdrawalSendBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
                }
            }
        })
    }

    fun checkInfo() : Boolean{
        var  checkResult = true
        var option = binding.activityWithdrawalEtOption.text.toString()
        var content = binding.activityWithdrawalEtContent.text.toString()


        //질문 선택 확인 기능
        if(option.isEmpty()) checkResult = false

        //내용 확인 기능
        if(content.isEmpty()) {
            checkResult = false
        }
        if(!binding.activityWithdrawalCheckbox.isChecked){
            checkResult = false
        }
        return checkResult
    }
    //뒤로가기 버튼 눌렀을 때
}


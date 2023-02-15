package com.garamgaebi.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
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
import com.garamgaebi.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentServicecenterBinding
import com.garamgaebi.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.garamgaebi.viewModel.EditTextViewModel
import com.garamgaebi.garamgaebi.garamgaebi.viewModel.ServiceCenterViewModel

class ServiceCenterFragment :
    BaseBindingFragment<FragmentServicecenterBinding>(R.layout.fragment_servicecenter) {
    var containerActivity: ContainerActivity? = null

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //뒤로가기

        val viewModel = ViewModelProvider(this)[ServiceCenterViewModel::class.java]
        binding.setVariable(BR.serviceCenterViewModel,viewModel)
        binding.serviceCenterViewModel = viewModel

        val editTextViewModel = ViewModelProvider(this)[EditTextViewModel::class.java]
        binding.setVariable(BR.editTextViewModel,editTextViewModel)

        viewModel.email.observe(viewLifecycleOwner, Observer {
            binding.serviceCenterViewModel = viewModel

            //email 유효성 검사 부분
            if(it.length < 22 && it.isNotEmpty())
                viewModel.emailIsValid.value = true

            Log.d("qna_email_true",viewModel.emailIsValid.value.toString())
        })
        viewModel.category.observe(viewLifecycleOwner, Observer {
            binding.serviceCenterViewModel = viewModel
            if(it.isNotEmpty())
                viewModel.categoryIsValid.value = true

            Log.d("qna_category_true",viewModel.categoryIsValid.value.toString())
        })
        viewModel.content.observe(viewLifecycleOwner, Observer {
            binding.serviceCenterViewModel = viewModel

            viewModel.contentIsValid.value = it.length < 100

            Log.d("qna_content_true",viewModel.contentIsValid.value.toString())
        })
        viewModel.agree.observe(viewLifecycleOwner, Observer {
            binding.serviceCenterViewModel = viewModel

            viewModel.agreeIsValid.value = binding.activityServicecenterCheckbox.isChecked

            Log.d("qna_agree_true",viewModel.agreeIsValid.value.toString())
        })


        //동의 체크박스 클릭 이벤트
        binding.activityServicecenterCheckboxDesc.setOnClickListener {
            var preCheck = binding.activityServicecenterCheckbox.isChecked
            viewModel.agree.value = !preCheck

           // binding.activityServicecenterCheckbox.isChecked = !preCheck

//                if (checkInfo()){
//                    binding.activityServicecenterSendBtn.isClickable = true
//                    binding.activityServicecenterSendBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
//                }else{
//                    binding.activityServicecenterSendBtn.isClickable = false
//                    binding.activityServicecenterSendBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
//                }
        }

        binding.activityServicecenterSendBtn.setOnClickListener {
            if (checkInfo() == true){
                //문의 보내기 기능 추가
                viewModel.postQna()
                (activity as ContainerActivity).onBackPressed()
                Log.d("qna_success","입니다")
            }else{
                //저장 불가 및 이유
            }
        }

        //회원탈퇴 이동
        binding.activityServicecenterTvWithdrawal.setOnClickListener {
            containerActivity!!.openFragmentOnFrameLayout(15)
            containerActivity!!.goWithdrawal()
        }

        //로그아웃 이동
        binding.activityServicecenterTvLogout.setOnClickListener {
            //startActivity(Intent(this,WithdrawalActivity::class.java))
            //로그아웃으로 이동
        }

        //이메일 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activitySevicecenterTvEmail)

        //문의내용 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityServicecenterEtContent)

        binding.activityServicecenterEtOption.setOnClickListener {
            val orderBottomDialogFragment: ServiceCenterOrderBottomdialogFragment = ServiceCenterOrderBottomdialogFragment {
                when (it) {
                    0 -> {
                        Toast.makeText(activity, "이용 문의", Toast.LENGTH_SHORT).show()
                        binding.activityServicecenterEtOption.setText("이용문의")
                    }
                    1 -> {
                        Toast.makeText(activity, "오류신고", Toast.LENGTH_SHORT).show()
                        binding.activityServicecenterEtOption.setText("오류신고")
                    }
                    2 -> {
                        Toast.makeText(activity, "서비스 제안", Toast.LENGTH_SHORT).show()
                        binding.activityServicecenterEtOption.setText("서비스 제안")
                    }
                    3 -> {
                        Toast.makeText(activity, "기타", Toast.LENGTH_SHORT).show()
                        binding.activityServicecenterEtOption.setText("기타")
                    }
                }
                if(viewModel.category.value?.isNotEmpty() == true){
                    viewModel.categoryIsValid.value = true
                }
            }
            orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }
      }
    fun checkEtInput(view: EditText) {
        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.basic_black_border_layout)
            } else {
                view.setBackgroundResource(R.drawable.basic_gray_border_layout)
            }
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkInfo()){
                    binding.activityServicecenterSendBtn.isClickable = true
                    binding.activityServicecenterSendBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
                }else{
                    binding.activityServicecenterSendBtn.isClickable = false
                    binding.activityServicecenterSendBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
                }
            }
        })
    }
    fun checkInfo() : Boolean{
        var  checkResult = true
        var option = binding.activityServicecenterEtOption.text.toString()
        var content = binding.activityServicecenterEtContent.text.toString()


        //질문 선택 확인 기능
        if(option.isEmpty()) checkResult = false

        //내용 확인 기능
        if(content.isEmpty()) {
            checkResult = false
        }

        if(!binding.activityServicecenterCheckbox.isChecked){
            checkResult = false
        }
        return checkResult
    }
    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }
}


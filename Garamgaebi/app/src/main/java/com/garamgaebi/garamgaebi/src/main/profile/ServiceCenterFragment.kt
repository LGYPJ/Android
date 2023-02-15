package com.garamgaebi.garamgaebi.src.main.profile

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
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.databinding.FragmentServicecenterBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.EditTextViewModel
import com.garamgaebi.garamgaebi.viewModel.ServiceCenterViewModel

class ServiceCenterFragment :
    BaseBindingFragment<FragmentServicecenterBinding>(R.layout.fragment_servicecenter) {
    var containerActivity: ContainerActivity? = null

    @SuppressLint("SuspiciousIndentation")
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
        binding.activityServicecenterCheckboxDesc.setOnClickListener {
            var preCheck = binding.activityServicecenterCheckbox.isChecked
            viewModel.agree.value = !preCheck
        }

        binding.activityServicecenterSendBtn.setOnClickListener {

                viewModel.postQna()
                (activity as ContainerActivity).onBackPressed()
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


        binding.activityServicecenterEtOption.setOnClickListener {
            viewModel.categoryFocusing.value = true
            viewModel.categoryFirst.value = false

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
    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }
}


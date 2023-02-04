package com.example.template.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.config.BaseFragment
import com.example.template.garamgaebi.databinding.FragmentWithdrawalBinding
import com.example.template.garamgaebi.src.main.ContainerActivity

class WithdrawalFragment :
    BaseFragment<FragmentWithdrawalBinding>(FragmentWithdrawalBinding::bind, R.layout.fragment_withdrawal) {
    var containerActivity: ContainerActivity? = null

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //탈퇴기 버튼 클릭이벤트
        binding.activityWithdrawalSendBtn.setOnClickListener {
            if (checkInfo() == true){
                //탈퇴 기능 추가
            }else{
                //탈퇴 불가 및 이유
            }
        }

        //동의 체크박스 클릭 이벤트
        binding.activityWithdrawalTvCheckboxDesc.setOnClickListener {
            var preCheck = binding.activityWithdrawalCheckbox.isChecked
            binding.activityWithdrawalCheckbox.isChecked = !preCheck
                if (checkInfo()){
                    binding.activityWithdrawalSendBtn.isClickable = true
                    binding.activityWithdrawalSendBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
                }else{
                    binding.activityWithdrawalSendBtn.isClickable = false
                    binding.activityWithdrawalSendBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
                }
        }

        //탈퇴사유 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityWithdrawalEtContent)

        binding.activityWithdrawalEtOption.setOnClickListener {
            val orderBottomDialogFragment: WithdrawalOrderBottomDialogFragment = WithdrawalOrderBottomDialogFragment {
                when (it) {
                    0 -> {
                        Toast.makeText(activity, "1선택", Toast.LENGTH_SHORT).show()
                        binding.activityWithdrawalEtOption.setText("1선택")
                    }
                    1 -> {
                        Toast.makeText(activity, "2선택", Toast.LENGTH_SHORT).show()
                        binding.activityWithdrawalEtOption.setText("2선택")
                    }
                    2 -> {
                        Toast.makeText(activity, "3선택", Toast.LENGTH_SHORT).show()
                        binding.activityWithdrawalEtOption.setText("3선택")
                    }
                    3 -> {
                        Toast.makeText(activity, "4선택", Toast.LENGTH_SHORT).show()
                        binding.activityWithdrawalEtOption.setText("4선택")
                    }

                }
            }
            orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
        }

    }
//    private fun dipToPixels(dipValue: Float): Float {
//        return TypedValue.applyDimension(
//            TypedValue.COMPLEX_UNIT_DIP,
//            dipValue,
//            resources.displayMetrics
//        )
//    }

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


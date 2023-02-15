package com.garamgaebi.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.garamgaebi.garamgaebi.garamgaebi.R

import com.garamgaebi.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.garamgaebi.common.REGISTER_EMAIL
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentRegisterNicknameBinding

class RegisterNicknameFragment : BaseBindingFragment<FragmentRegisterNicknameBinding>
    (R.layout.fragment_register_nickname) {
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // et selected 여부에 따라 drawable 결정
        binding.fragmentNicknameEtEmail.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    view.setBackgroundResource(R.drawable.register_et_border_selected)
                } else {
                    view.setBackgroundResource(R.drawable.register_et_border)
                }
        }

        // et에 따라 닉네임 조건 tv 변경
        binding.fragmentNicknameEtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.fragmentNicknameEtEmail.text.length > 8) {
                    binding.fragmentNicknameState.apply {
                        visibility = View.VISIBLE
                        text = "사용 불가능한 닉네임입니다"
                        setTextColor(registerActivity.getColor(R.color.redForText))
                    }
                    binding.fragmentNicknameBtn.apply {
                        setBackgroundResource(R.drawable.register_btn_color)
                        isEnabled = false
                    }

                } else if(binding.fragmentNicknameEtEmail.text.isNotEmpty()) {
                    binding.fragmentNicknameState.apply {
                        visibility = View.VISIBLE
                        text = "사용 가능한 닉네임입니다"
                        setTextColor(registerActivity.getColor(R.color.blueForBtn))
                    }
                    binding.fragmentNicknameBtn.apply {
                        setBackgroundResource(R.drawable.register_btn_color_enable)
                        isEnabled = true
                    }
                } else {
                    binding.fragmentNicknameState.visibility = View.GONE
                    binding.fragmentNicknameBtn.apply {
                        setBackgroundResource(R.drawable.register_btn_color)
                        isEnabled = false
                    }
                }
            }
        })

        binding.fragmentNicknameBtn.setOnClickListener {
            registerActivity.setFragment(REGISTER_EMAIL)
        }

    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}
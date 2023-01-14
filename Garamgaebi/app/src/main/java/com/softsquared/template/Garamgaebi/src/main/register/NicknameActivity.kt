package com.softsquared.template.Garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityNicknameBinding

class NicknameActivity : BaseActivity<ActivityNicknameBinding>(ActivityNicknameBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // et selected 여부에 따라 drawable 결정
        binding.activityNicknameEtEmail.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
                if (hasFocus) {
                    view.setBackgroundResource(R.drawable.register_et_border_selected)
                } else {
                    view.setBackgroundResource(R.drawable.register_et_border)
                }
        }

        // et에 따라 닉네임 조건 tv 변경
        binding.activityNicknameEtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(binding.activityNicknameEtEmail.text.length > 8) {
                    binding.activityNicknameState.apply {
                        visibility = View.VISIBLE
                        text = "사용 불가능한 닉네임입니다"
                        setTextColor(getColor(R.color.redForText))
                    }
                    binding.activityNicknameBtn.apply {
                        setBackgroundResource(R.drawable.register_btn_color)
                        isEnabled = false
                    }

                } else if(binding.activityNicknameEtEmail.text.isNotEmpty()) {
                    binding.activityNicknameState.apply {
                        visibility = View.VISIBLE
                        text = "사용 가능한 닉네임입니다"
                        setTextColor(getColor(R.color.blueForBtn))
                    }
                    binding.activityNicknameBtn.apply {
                        setBackgroundResource(R.drawable.register_btn_color_enable)
                        isEnabled = true
                    }
                } else {
                    binding.activityNicknameState.visibility = View.GONE
                    binding.activityNicknameBtn.apply {
                        setBackgroundResource(R.drawable.register_btn_color)
                        isEnabled = false
                    }
                }
            }
        })

        binding.activityNicknameBtn.setOnClickListener {
            startActivity(Intent(this, EmailActivity::class.java))
        }
    }
}
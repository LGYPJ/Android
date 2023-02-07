package com.example.template.garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseActivity
import com.example.template.garamgaebi.databinding.ActivityEmailBinding


class EmailActivity : BaseActivity<ActivityEmailBinding>(ActivityEmailBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // et selected 여부에 따라 drawable 결정
        binding.activityEmailEtEmail.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.register_et_border_selected)
            } else {
                view.setBackgroundResource(R.drawable.register_et_border)
            }
        }

        // et에 따라 닉네임 조건 tv 변경
        binding.activityEmailEtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(checkEmail()) {
                    binding.activityEmailTvState.apply {
                        visibility = View.VISIBLE
                        text = "사용 가능한 이메일입니다"
                        setTextColor(getColor(R.color.blueForBtn))
                    }
                    binding.activityEmailBtn.apply {
                        setBackgroundResource(R.drawable.register_btn_color_enable)
                        isEnabled = true
                    }
                } else {
                    binding.activityEmailTvState.apply {
                        visibility = View.VISIBLE
                        text = "이메일 형식이 올바르지 않습니다"
                        setTextColor(getColor(R.color.redForText))
                    }
                    binding.activityEmailBtn.apply {
                        setBackgroundResource(R.drawable.register_btn_color)
                        isEnabled = false
                    }
                }
            }
        })

        binding.activityEmailBtn.setOnClickListener {
            startActivity(Intent(this, OrganizationActivity::class.java))
        }
    }
    fun checkEmail() : Boolean{
        val email = binding.activityEmailEtEmail.text.toString()
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
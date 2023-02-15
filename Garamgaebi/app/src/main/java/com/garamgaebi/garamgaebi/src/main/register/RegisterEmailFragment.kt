package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.REGISTER_ORG
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentRegisterEmailBinding


class RegisterEmailFragment : BaseBindingFragment<FragmentRegisterEmailBinding>(R.layout.fragment_register_email) {
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // et selected 여부에 따라 drawable 결정
        binding.fragmentEmailEtEmail.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.register_et_border_selected)
            } else {
                view.setBackgroundResource(R.drawable.register_et_border)
            }
        }

        // et에 따라 닉네임 조건 tv 변경
        binding.fragmentEmailEtEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(checkEmail()) {
                    binding.fragmentEmailTvState.apply {
                        visibility = View.VISIBLE
                        text = "사용 가능한 이메일입니다"
                        setTextColor(registerActivity.getColor(R.color.blueForBtn))
                    }
                    binding.fragmentEmailBtn.apply {
                        setBackgroundResource(R.drawable.register_btn_color_enable)
                        isEnabled = true
                    }
                } else {
                    binding.fragmentEmailTvState.apply {
                        visibility = View.VISIBLE
                        text = "이메일 형식이 올바르지 않습니다"
                        setTextColor(registerActivity.getColor(R.color.redForText))
                    }
                    binding.fragmentEmailBtn.apply {
                        setBackgroundResource(R.drawable.register_btn_color)
                        isEnabled = false
                    }
                }
            }
        })

        binding.fragmentEmailBtn.setOnClickListener {
            registerActivity.setFragment(REGISTER_ORG)
        }
    }
    fun checkEmail() : Boolean{
        val email = binding.fragmentEmailEtEmail.text.toString()
        return Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}
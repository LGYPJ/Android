package com.example.template.garamgaebi.src.main.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseFragment
import com.example.template.garamgaebi.common.REGISTER_AUTH
import com.example.template.garamgaebi.databinding.FragmentRegisterLoginBinding

class RegisterLoginFragment : BaseFragment<FragmentRegisterLoginBinding>(
    FragmentRegisterLoginBinding::bind, R.layout.fragment_register_login) {
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentLoginKakao.setOnClickListener(){
            registerActivity.setFragment(REGISTER_AUTH)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}
package com.garamgaebi.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.view.View
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.garamgaebi.common.REGISTER_COMPLETE
import com.garamgaebi.garamgaebi.garamgaebi.common.REGISTER_EDU
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterCareerBinding

class RegisterCareerFragment : BaseBindingFragment<FragmentRegisterCareerBinding>(R.layout.fragment_register_career) {
    lateinit var registerActivity:RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이메일 et selected 여부에 따라 drawable 결정
        checkFocus(binding.fragmentCareerEtCompany)
        checkFocus(binding.fragmentCareerEtRank)
        checkFocus(binding.fragmentCareerEtStartDate)
        checkFocus(binding.fragmentCareerEtEndDate)

        binding.fragmentCareerBtnNext.setOnClickListener {
            registerActivity.setFragment(REGISTER_COMPLETE)
        }
        binding.fragmentCareerTvEducation.setOnClickListener {
            registerActivity.setFragment(REGISTER_EDU)
            parentFragmentManager.popBackStack()
        }
    }
    private fun checkFocus(view : View) {
        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.register_et_border_selected)
            } else {
                view.setBackgroundResource(R.drawable.register_et_border)
            }
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}
package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.view.View
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.REGISTER_CAREER
import com.garamgaebi.garamgaebi.common.REGISTER_COMPLETE
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterEducationBinding


class RegisterEducationFragment : BaseBindingFragment<FragmentRegisterEducationBinding>(R.layout.fragment_register_education) {
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 이메일 et selected 여부에 따라 drawable 결정
        checkFocus(binding.fragmentEducationEtInstitution)
        checkFocus(binding.fragmentEducationEtMajor)

        binding.fragmentEducationBtnNext.setOnClickListener {
            registerActivity.setFragment(REGISTER_COMPLETE)
        }
        binding.fragmentEducationTvCareer.setOnClickListener {
            registerActivity.setFragment(REGISTER_CAREER)
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
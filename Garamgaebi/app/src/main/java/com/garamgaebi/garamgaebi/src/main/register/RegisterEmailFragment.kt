package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.REGISTER_EMAIL
import com.garamgaebi.garamgaebi.common.REGISTER_ORG
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterEmailBinding
import com.garamgaebi.garamgaebi.viewModel.RegisterViewModel


class RegisterEmailFragment : BaseBindingFragment<FragmentRegisterEmailBinding>(R.layout.fragment_register_email) {
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val viewModel by viewModels<RegisterViewModel>()
        binding.lifecycleOwner = this
        binding.setVariable(BR.viewModel, viewModel)

        viewModel.profileEmail.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.isProfileEmailValid.value = viewModel.checkProfileEmail()
        })
        binding.fragmentEmailBtn.setOnClickListener {
            registerActivity.setFragment(REGISTER_ORG)
        }

        /*// et에 따라 닉네임 조건 tv 변경
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
        })*/

        binding.fragmentEmailBtn.setOnClickListener {
            registerActivity.setFragment(REGISTER_ORG)
        }
        binding.containerLayout.setOnTouchListener(View.OnTouchListener { v, event ->
            hideKeyboard()
            false
        })

    }
    private fun hideKeyboard() {
        if (activity != null && requireActivity().currentFocus != null) {
            // 프래그먼트기 때문에 getActivity() 사용
            val inputManager: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                requireActivity().currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}
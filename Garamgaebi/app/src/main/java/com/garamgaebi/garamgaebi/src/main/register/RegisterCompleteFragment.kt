package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentRegisterCompleteBinding
import com.garamgaebi.garamgaebi.src.main.MainActivity

class RegisterCompleteFragment : BaseFragment<FragmentRegisterCompleteBinding>
    (FragmentRegisterCompleteBinding::bind, R.layout.fragment_register_complete){
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentCompleteCbWhole.setOnClickListener {
            if(binding.fragmentCompleteCbWhole.isChecked){
                binding.fragmentCompleteCbPersonal.isChecked = true
                binding.fragmentCompleteCbOld.isChecked = true
            } else {
                binding.fragmentCompleteCbPersonal.isChecked = false
                binding.fragmentCompleteCbOld.isChecked = false
            }
            checkAgree()
        }
        binding.fragmentCompleteCbPersonal.setOnClickListener {
            checkAgree()
        }
        binding.fragmentCompleteCbOld.setOnClickListener {
            checkAgree()
        }
        binding.fragmentCompleteBtnNext.setOnClickListener {
            startActivity(Intent(registerActivity, MainActivity::class.java))
            ActivityCompat.finishAffinity(registerActivity)
        }

    }
    private fun checkAgree() {
        if(binding.fragmentCompleteCbPersonal.isChecked && binding.fragmentCompleteCbOld.isChecked){
            binding.fragmentCompleteBtnNext.isEnabled = true
            binding.fragmentCompleteBtnNext.setBackgroundResource(R.drawable.register_btn_color_enable)
        } else {
            binding.fragmentCompleteBtnNext.isEnabled = false
            binding.fragmentCompleteBtnNext.setBackgroundResource(R.drawable.register_btn_color)
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }
}
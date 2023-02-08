package com.example.template.garamgaebi.src.main.myPage

import android.os.Bundle
import android.view.View
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseFragment
import com.example.template.garamgaebi.databinding.FragmentMyPageBinding

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {
    private var mCount = 0
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonChangeCounterText.setOnClickListener {
            binding.textViewCounter.text =
                resources.getString(R.string.my_page_tv_counter, ++mCount)
        }
    }
}
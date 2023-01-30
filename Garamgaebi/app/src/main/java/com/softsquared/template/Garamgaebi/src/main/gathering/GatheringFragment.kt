package com.softsquared.template.Garamgaebi.src.main.gathering

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentGatheringBinding


class GatheringFragment : BaseFragment<FragmentGatheringBinding>(FragmentGatheringBinding::bind, R.layout.fragment_gathering) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val gatheringVPAdapter = GatheringVPAdapter(this)
        binding.fragmentGatheringVp.adapter =  gatheringVPAdapter
        val tabArray = arrayOf("세미나", "네트워킹", "내 모임")
        TabLayoutMediator(binding.fragmentGatheringTl, binding.fragmentGatheringVp) { tab, position ->
            tab.text = tabArray[position]

        }.attach()


    }
    fun setVPSeminar() {
        binding.fragmentGatheringVp.currentItem = 0
    }
    fun setVPNetworking() {
        binding.fragmentGatheringVp.currentItem = 1
    }

}
package com.garamgaebi.garamgaebi.src.main.gathering

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.GatheringVPAdapter
import com.garamgaebi.garamgaebi.common.BaseFragment

import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentGatheringBinding


class GatheringFragment : BaseFragment<FragmentGatheringBinding>(FragmentGatheringBinding::bind, R.layout.fragment_gathering) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gatheringVPAdapter = GatheringVPAdapter(this)
        binding.fragmentGatheringVp.adapter =  gatheringVPAdapter
        val tabArray = arrayOf("세미나", "네트워킹", "내 모임")
        TabLayoutMediator(binding.fragmentGatheringTl, binding.fragmentGatheringVp, false, false) { tab, position ->
            tab.text = tabArray[position]
            tab.position.let{binding.fragmentGatheringVp.setCurrentItem(position, false)}
        }.attach()

    }
    fun setVPSeminar() {
        binding.fragmentGatheringVp.currentItem = 0
    }
    fun setVPNetworking() {
        binding.fragmentGatheringVp.currentItem = 1
    }
    fun setVPmy() {
        binding.fragmentGatheringVp.currentItem = 2
    }


}
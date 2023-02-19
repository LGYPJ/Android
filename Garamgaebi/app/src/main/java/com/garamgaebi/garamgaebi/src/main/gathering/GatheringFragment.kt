package com.garamgaebi.garamgaebi.src.main.gathering

import android.os.Bundle
import android.util.Log
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.GatheringVPAdapter
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GATHERING_MY
import com.garamgaebi.garamgaebi.common.GATHERING_NETWORKING
import com.garamgaebi.garamgaebi.common.GATHERING_SEMINAR

import com.garamgaebi.garamgaebi.databinding.FragmentGatheringBinding


class GatheringFragment : BaseFragment<FragmentGatheringBinding>(FragmentGatheringBinding::bind, R.layout.fragment_gathering) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gatheringVPAdapter = GatheringVPAdapter(parentFragmentManager, requireActivity().lifecycle)
        binding.fragmentGatheringVp.adapter =  gatheringVPAdapter
        val tabArray = arrayOf("세미나", "네트워킹", "내 모임")
        TabLayoutMediator(binding.fragmentGatheringTl, binding.fragmentGatheringVp) { tab, position ->
            tab.text = tabArray[position]
        }.attach()

    }
    fun setVPSeminar() {
        binding.fragmentGatheringVp.currentItem = GATHERING_SEMINAR
        Log.d("goGatheringSeminar", "${binding.fragmentGatheringVp.currentItem}")
    }
    fun setVPNetworking() {
        binding.fragmentGatheringVp.currentItem = GATHERING_NETWORKING
        Log.d("goGatheringNetworking", "${binding.fragmentGatheringVp.currentItem}")
    }
    fun setVPmy() {
        binding.fragmentGatheringVp.currentItem = GATHERING_MY
        Log.d("goGatheringNetworking", "${binding.fragmentGatheringVp.currentItem}")
    }
}
package com.garamgaebi.garamgaebi.src.main.gathering

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*

import com.garamgaebi.garamgaebi.databinding.FragmentGatheringBinding


class GatheringFragment : BaseFragment<FragmentGatheringBinding>(FragmentGatheringBinding::bind, R.layout.fragment_gathering) {
    private val gatheringSeminarFragment = GatheringSeminarFragment()
    private val gatheringNetworkingFragment = GatheringNetworkingFragment()
    private val gatheringMyMeetingFragment = GatheringMyMeetingFragment()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("GatheringFragment", "GatheringFragment onViewCreated")
        val gatheringVPAdapter = GatheringVPAdapter(this)
        gatheringVPAdapter.createFragment(GATHERING_SEMINAR)
        binding.fragmentGatheringVp.adapter = gatheringVPAdapter
        val tabArray = arrayOf("세미나", "네트워킹", "내 모임")
        TabLayoutMediator(binding.fragmentGatheringTl, binding.fragmentGatheringVp) { tab, position ->
            tab.text = tabArray[position]
        }.attach()
    }
    fun setVPSeminar() {
        binding.fragmentGatheringVp.post {
            binding.fragmentGatheringVp.currentItem = GATHERING_SEMINAR
        }
        Log.d("goGatheringSeminar", "${binding.fragmentGatheringVp.currentItem}")
    }
    fun setVPNetworking() {
        binding.fragmentGatheringVp.post {
            binding.fragmentGatheringVp.currentItem = GATHERING_NETWORKING
        }
        Log.d("goGatheringNetworking", "${binding.fragmentGatheringVp.currentItem}")
    }
    fun setVPmy() {
        binding.fragmentGatheringVp.currentItem = GATHERING_MY
        Log.d("goGatheringNetworking", "${binding.fragmentGatheringVp.currentItem}")
    }
    inner class GatheringVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = NUM_TABS
        override fun createFragment(position: Int): Fragment {
            Log.d("GatheringFragment", "GatheringFragment createFragment")
            return when(position) {
                GATHERING_SEMINAR -> gatheringSeminarFragment
                GATHERING_NETWORKING -> gatheringNetworkingFragment
                GATHERING_MY -> gatheringMyMeetingFragment
                else -> gatheringSeminarFragment
            }
        }
    }
}
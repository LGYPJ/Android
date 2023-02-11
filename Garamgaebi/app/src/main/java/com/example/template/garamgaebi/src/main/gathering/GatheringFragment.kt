package com.example.template.garamgaebi.src.main.gathering

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.adapter.GatheringVPAdapter
import com.example.template.garamgaebi.common.BaseFragment

import com.example.template.garamgaebi.databinding.FragmentGatheringBinding
import com.example.template.garamgaebi.src.main.seminar.SeminarFragment
import com.google.android.material.tabs.TabLayout


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

        //스와이프 불가
        /*binding.fragmentGatheringVp.run {
            isUserInputEnabled = false
        }*/

        /*binding.fragmentGatheringTl.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let{binding.fragmentGatheringVp.setCurrentItem(it, false)}
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })*/

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

    /*fun swipe(){
        binding.fragmentGatheringVp.run {
            isUserInputEnabled = false
        }
    }*/


    /*fun setVPSeminar() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_frm, GatheringSeminarFragment()).commit()
    }
    fun setVPNetworking() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_frm, GatheringNetworkingFragment()).commit()
    }
    fun setVPmy() {
        val transaction = requireActivity().supportFragmentManager.beginTransaction()
        transaction.replace(R.id.activity_main_frm, GatheringMyMeetingFragment()).commit()
    }*/

}
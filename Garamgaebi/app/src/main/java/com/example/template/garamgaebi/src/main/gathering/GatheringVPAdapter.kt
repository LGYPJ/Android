package com.example.template.garamgaebi.src.main.gathering

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.template.garamgaebi.common.HOME_TAG

class GatheringVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            HOME_TAG -> GatheringSeminarFragment()
            1 -> GatheringNetworkingFragment()
            2 -> GatheringMyMeetingFragment()
            else -> GatheringSeminarFragment()
        }
    }
}
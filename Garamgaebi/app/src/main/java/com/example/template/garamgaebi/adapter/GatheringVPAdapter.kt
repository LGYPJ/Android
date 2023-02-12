package com.example.template.garamgaebi.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.template.garamgaebi.common.HOME_TAG
import com.example.template.garamgaebi.src.main.gathering.GatheringMyMeetingFragment
import com.example.template.garamgaebi.src.main.gathering.GatheringNetworkingFragment
import com.example.template.garamgaebi.src.main.gathering.GatheringSeminarFragment

class GatheringVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> GatheringSeminarFragment()
            1 -> GatheringNetworkingFragment()
            2 -> GatheringMyMeetingFragment()
            else -> GatheringSeminarFragment()
        }
    }
}
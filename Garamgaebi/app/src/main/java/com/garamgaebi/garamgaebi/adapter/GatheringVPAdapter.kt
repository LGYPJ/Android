package com.garamgaebi.garamgaebi.adapter

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.garamgaebi.garamgaebi.common.GATHERING_MY
import com.garamgaebi.garamgaebi.common.GATHERING_NETWORKING
import com.garamgaebi.garamgaebi.common.GATHERING_SEMINAR
import com.garamgaebi.garamgaebi.common.NUM_TABS
import com.garamgaebi.garamgaebi.src.main.gathering.GatheringMyMeetingFragment
import com.garamgaebi.garamgaebi.src.main.gathering.GatheringNetworkingFragment
import com.garamgaebi.garamgaebi.src.main.gathering.GatheringSeminarFragment

class GatheringVPAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = NUM_TABS

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            GATHERING_SEMINAR -> GatheringSeminarFragment()
            GATHERING_NETWORKING -> GatheringNetworkingFragment()
            GATHERING_MY -> GatheringMyMeetingFragment()
            else -> GatheringSeminarFragment()
        }
    }
}
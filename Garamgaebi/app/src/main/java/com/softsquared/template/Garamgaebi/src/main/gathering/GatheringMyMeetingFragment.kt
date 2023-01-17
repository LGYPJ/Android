package com.softsquared.template.Garamgaebi.src.main.gathering

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentGatheringMyMeetingBinding
import com.softsquared.template.Garamgaebi.databinding.FragmentGatheringSeminarBinding
import com.softsquared.template.Garamgaebi.src.main.home.GatheringItemDecoration

class GatheringMyMeetingFragment : BaseFragment<FragmentGatheringMyMeetingBinding>(FragmentGatheringMyMeetingBinding::bind, R.layout.fragment_gathering_my_meeting){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var myMeetingDataList : ArrayList<GatheringMyMeetingItemData> = arrayListOf(
            GatheringMyMeetingItemData("xxxx-xx-xx", "로건 세미나", "pp"),
            GatheringMyMeetingItemData("xxxx-xx-xx","신디 세미나", "pp"),
        )

        //TODO : 날짜에 따라 data 나누기
        val myMeetingScheduledAdapter = GatheringMyMeetingScheduledRVAdapter(myMeetingDataList)
        val myMeetingLastAdapter = GatheringMyMeetingLastRVAdapter(myMeetingDataList)

        binding.fragmentGatheringMyMeetingRvScheduled.adapter = myMeetingScheduledAdapter
        binding.fragmentGatheringMyMeetingRvLast.adapter = myMeetingLastAdapter

        binding.fragmentGatheringMyMeetingRvScheduled.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.fragmentGatheringMyMeetingRvLast.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        binding.fragmentGatheringMyMeetingRvScheduled.addItemDecoration(GatheringItemDecoration())
        binding.fragmentGatheringMyMeetingRvLast.addItemDecoration(GatheringItemDecoration())

        myMeetingScheduledAdapter.setOnItemClickListener(object : GatheringMyMeetingScheduledRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                //TODO("Not yet implemented")
            }
        })

        myMeetingLastAdapter.setOnItemClickListener(object : GatheringMyMeetingLastRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                //TODO("Not yet implemented")
            }
        })
    }
}
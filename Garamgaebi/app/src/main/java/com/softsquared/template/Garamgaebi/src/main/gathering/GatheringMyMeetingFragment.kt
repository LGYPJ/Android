package com.softsquared.template.Garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentGatheringMyMeetingBinding
import com.softsquared.template.Garamgaebi.databinding.FragmentGatheringSeminarBinding
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import com.softsquared.template.Garamgaebi.src.main.home.GatheringItemDecoration

class GatheringMyMeetingFragment : BaseFragment<FragmentGatheringMyMeetingBinding>(FragmentGatheringMyMeetingBinding::bind, R.layout.fragment_gathering_my_meeting), PopupMenu.OnMenuItemClickListener{
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
            override fun onMoreClick(position: Int, v: View) {
                showPopup(v)
            }
        })

        myMeetingLastAdapter.setOnItemClickListener(object : GatheringMyMeetingLastRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                //TODO("Not yet implemented")
            }
        })
    }
    private fun showPopup(v : View) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(R.menu.fragment_home_my_meeting_popup, popup.menu)
        popup.setOnMenuItemClickListener(this)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.details -> {
                // TODO
            }
            R.id.cancel -> {
                //신청 취소 프래그먼트로!
                val intent = Intent(context, ContainerActivity::class.java)
                intent.putExtra("cancel", true)
                startActivity(intent)
            }
        }
        return item != null
    }
}
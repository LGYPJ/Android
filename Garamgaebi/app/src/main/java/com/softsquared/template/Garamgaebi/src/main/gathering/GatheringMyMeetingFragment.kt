package com.softsquared.template.Garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentGatheringMyMeetingBinding
import com.softsquared.template.Garamgaebi.model.GatheringProgramResult
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import com.softsquared.template.Garamgaebi.src.main.home.GatheringItemDecoration
import com.softsquared.template.Garamgaebi.src.main.home.HomeMyMeetingItemDecoration
import com.softsquared.template.Garamgaebi.viewModel.GatheringViewModel

class GatheringMyMeetingFragment : BaseFragment<FragmentGatheringMyMeetingBinding>(FragmentGatheringMyMeetingBinding::bind, R.layout.fragment_gathering_my_meeting), PopupMenu.OnMenuItemClickListener{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[GatheringViewModel::class.java]
        viewModel.getGatheringProgramReady(1)
        viewModel.getGatheringProgramClosed(1)

        viewModel.programReady.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<GatheringProgramResult>
            val myMeetingScheduledAdapter = GatheringMyMeetingScheduledRVAdapter(result)
            if(result.isEmpty()) {
                binding.fragmentGatheringMyMeetingClScheduledBlank.visibility = View.VISIBLE
            } else {
                binding.fragmentGatheringMyMeetingRvScheduled.apply {
                    adapter = myMeetingScheduledAdapter
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    addItemDecoration(GatheringItemDecoration())
                }
                binding.fragmentGatheringMyMeetingClScheduledBlank.visibility = View.GONE
            }
            myMeetingScheduledAdapter.setOnItemClickListener(object : GatheringMyMeetingScheduledRVAdapter.OnItemClickListener{
                override fun onMoreClick(position: Int, v: View) {
                    showPopup(v)
                }
            })
        })
        viewModel.programClosed.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<GatheringProgramResult>
            val myMeetingLastAdapter = GatheringMyMeetingLastRVAdapter(result)
            if(result.isEmpty()) {
                binding.fragmentGatheringMyMeetingClLastBlank.visibility = View.VISIBLE
            }
            else {
                binding.fragmentGatheringMyMeetingRvLast.apply {
                    adapter = myMeetingLastAdapter
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    addItemDecoration(GatheringItemDecoration())
                }
                binding.fragmentGatheringMyMeetingClLastBlank.visibility = View.GONE
            }
            myMeetingLastAdapter.setOnItemClickListener(object : GatheringMyMeetingLastRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    //TODO("Not yet implemented")
                }
            })
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
package com.example.template.garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.adapter.GatheringMyMeetingLastRVAdapter
import com.example.template.garamgaebi.adapter.GatheringMyMeetingScheduledRVAdapter
import com.example.template.garamgaebi.common.BaseFragment
import com.example.template.garamgaebi.databinding.FragmentGatheringMyMeetingBinding
import com.example.template.garamgaebi.model.GatheringProgramResult
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.src.main.home.GatheringItemDecoration
import com.example.template.garamgaebi.viewModel.GatheringViewModel

class GatheringMyMeetingFragment : BaseFragment<FragmentGatheringMyMeetingBinding>(FragmentGatheringMyMeetingBinding::bind, R.layout.fragment_gathering_my_meeting), PopupMenu.OnMenuItemClickListener{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentGatheringMyMeetingClScheduledBlank.visibility = View.VISIBLE
        binding.fragmentGatheringMyMeetingRvScheduled.visibility = View.GONE
        binding.fragmentGatheringMyMeetingClLastBlank.visibility = View.VISIBLE
        binding.fragmentGatheringMyMeetingRvLast.visibility = View.GONE

        val viewModel = ViewModelProvider(this)[GatheringViewModel::class.java]
        viewModel.getGatheringProgramReady(1)
        viewModel.getGatheringProgramClosed(1)

        viewModel.programReady.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<GatheringProgramResult>
            val myMeetingScheduledAdapter : GatheringMyMeetingScheduledRVAdapter
            if(result.isEmpty() || !it.isSuccess || it == null) {
                binding.fragmentGatheringMyMeetingClScheduledBlank.visibility = View.VISIBLE
                binding.fragmentGatheringMyMeetingRvScheduled.visibility = View.GONE
            } else {
                binding.fragmentGatheringMyMeetingClScheduledBlank.visibility = View.GONE
                binding.fragmentGatheringMyMeetingRvScheduled.visibility = View.VISIBLE

                myMeetingScheduledAdapter = GatheringMyMeetingScheduledRVAdapter(result)
                binding.fragmentGatheringMyMeetingRvScheduled.apply {
                    adapter = myMeetingScheduledAdapter
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    addItemDecoration(GatheringItemDecoration())
                }
                myMeetingScheduledAdapter.setOnItemClickListener(object : GatheringMyMeetingScheduledRVAdapter.OnItemClickListener{
                    override fun onMoreClick(position: Int, v: View) {
                        showPopupScheduled(v)
                    }
                })
            }
        })
        viewModel.programClosed.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<GatheringProgramResult>
            val myMeetingLastAdapter : GatheringMyMeetingLastRVAdapter
            if(result.isEmpty() || !it.isSuccess || it == null) {
                binding.fragmentGatheringMyMeetingClLastBlank.visibility = View.VISIBLE
                binding.fragmentGatheringMyMeetingRvLast.visibility = View.GONE
            }
            else {
                myMeetingLastAdapter = GatheringMyMeetingLastRVAdapter(result)
                binding.fragmentGatheringMyMeetingClLastBlank.visibility = View.GONE
                binding.fragmentGatheringMyMeetingRvLast.visibility = View.VISIBLE
                binding.fragmentGatheringMyMeetingRvLast.apply {
                    adapter = myMeetingLastAdapter
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    addItemDecoration(GatheringItemDecoration())
                }
                myMeetingLastAdapter.setOnItemClickListener(object : GatheringMyMeetingLastRVAdapter.OnItemClickListener{
                    override fun onMoreClick(position: Int, v: View) {
                        showPopupLast(v)
                    }
                })
            }
        })


    }
    private fun showPopupScheduled(v : View) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(R.menu.fragment_gathering_my_meeting_popup_scheduled, popup.menu)
        popup.setOnMenuItemClickListener(this)
        popup.show()
    }
    private fun showPopupLast(v : View) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(R.menu.fragment_gathering_my_meeting_popup_last, popup.menu)
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
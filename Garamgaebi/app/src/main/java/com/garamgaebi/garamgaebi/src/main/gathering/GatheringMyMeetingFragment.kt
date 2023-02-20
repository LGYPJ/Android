package com.garamgaebi.garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.GatheringMyMeetingLastRVAdapter
import com.garamgaebi.garamgaebi.adapter.GatheringMyMeetingScheduledRVAdapter
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.databinding.FragmentGatheringMyMeetingBinding
import com.garamgaebi.garamgaebi.model.GatheringProgramResult
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.GatheringViewModel

class GatheringMyMeetingFragment : BaseFragment<FragmentGatheringMyMeetingBinding>(FragmentGatheringMyMeetingBinding::bind, R.layout.fragment_gathering_my_meeting), PopupMenu.OnMenuItemClickListener {
    //리사이클러뷰 갱신
    var data = MutableLiveData<GatheringProgramResult>()
    var myMeetingScheduledAdapter : GatheringMyMeetingScheduledRVAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentGatheringMyMeetingClScheduledBlank.visibility = View.VISIBLE
        binding.fragmentGatheringMyMeetingRvScheduled.visibility = View.GONE
        binding.fragmentGatheringMyMeetingClLastBlank.visibility = View.VISIBLE
        binding.fragmentGatheringMyMeetingRvLast.visibility = View.GONE

        val viewModel by viewModels<GatheringViewModel>()

        viewModel.getGatheringProgramReady(myMemberIdx)
        viewModel.getGatheringProgramClosed(myMemberIdx)



        viewModel.programReady.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<GatheringProgramResult>
            val myMeetingScheduledAdapter: GatheringMyMeetingScheduledRVAdapter

            if (result == null || result.isEmpty()) {
                binding.fragmentGatheringMyMeetingClScheduledBlank.visibility = View.VISIBLE
                binding.fragmentGatheringMyMeetingRvScheduled.visibility = View.GONE
            } else {
                binding.fragmentGatheringMyMeetingClScheduledBlank.visibility = View.GONE
                binding.fragmentGatheringMyMeetingRvScheduled.visibility = View.VISIBLE

                myMeetingScheduledAdapter = GatheringMyMeetingScheduledRVAdapter(result)
                binding.fragmentGatheringMyMeetingRvScheduled.apply {
                    adapter = myMeetingScheduledAdapter
                    layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    addItemDecoration(GatheringItemDecoration())
                }
                myMeetingScheduledAdapter!!.setOnItemClickListener(object :
                    GatheringMyMeetingScheduledRVAdapter.OnItemClickListener {
                    override fun onMoreClick(position: Int, v: View) {
                        val program = it.result[position].programIdx
                        val type = it.result[position].type
                        GaramgaebiApplication.sSharedPreferences.edit()
                            .putInt("programIdx", program)
                            .putString("type", type)
                            .apply()
                        showPopupScheduled(v)
                    }
                })
            }
        })


        viewModel.programClosed.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<GatheringProgramResult>
            val myMeetingLastAdapter: GatheringMyMeetingLastRVAdapter
            if (result == null || result.isEmpty()) {
                binding.fragmentGatheringMyMeetingClLastBlank.visibility = View.VISIBLE
                binding.fragmentGatheringMyMeetingRvLast.visibility = View.GONE
            } else {
                myMeetingLastAdapter = GatheringMyMeetingLastRVAdapter(result)
                binding.fragmentGatheringMyMeetingClLastBlank.visibility = View.GONE
                binding.fragmentGatheringMyMeetingRvLast.visibility = View.VISIBLE
                binding.fragmentGatheringMyMeetingRvLast.apply {
                    adapter = myMeetingLastAdapter
                    layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    addItemDecoration(GatheringItemDecoration())
                }
                myMeetingLastAdapter.setOnItemClickListener(object :
                    GatheringMyMeetingLastRVAdapter.OnItemClickListener {
                    override fun onMoreClick(position: Int, v: View) {
                        val program = it.result[position].programIdx
                        val type = it.result[position].type
                        GaramgaebiApplication.sSharedPreferences.edit()
                            .putInt("programIdx", program)
                            .putString("type", type)
                            .apply()
                        showPopupLast(v)
                    }
                })
            }
        })


    }

    private fun showPopupScheduled(v: View) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(R.menu.fragment_gathering_my_meeting_popup_scheduled, popup.menu)
        popup.setOnMenuItemClickListener(this)
        popup.show()
    }

    private fun showPopupLast(v: View) {
        val popup = PopupMenu(requireContext(), v)
        popup.menuInflater.inflate(R.menu.fragment_gathering_my_meeting_popup_last, popup.menu)
        popup.setOnMenuItemClickListener(this)
        popup.show()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.details -> {
                //세미나로
                if (GaramgaebiApplication.sSharedPreferences.getString("type", null) == "SEMINAR") {
                    val intent = Intent(context, ContainerActivity::class.java)
                    intent.putExtra("seminar", true)
                    intent.putExtra("gathering-seminar", "gathering-seminar")
                    startActivity(intent)
                }
                //네트워킹으로
                if (GaramgaebiApplication.sSharedPreferences.getString("type", null) == "NETWORKING") {
                    val intent = Intent(context, ContainerActivity::class.java)
                    intent.putExtra("networking", true)
                    intent.putExtra("gathering-networking", "gathering-networking")
                    startActivity(intent)
                }
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
package com.garamgaebi.garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.*
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.common.NetworkErrorDialog
import com.garamgaebi.garamgaebi.databinding.FragmentGatheringMyMeetingBinding
import com.garamgaebi.garamgaebi.model.GatheringProgramResult
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.GatheringViewModel
import com.kakao.sdk.common.KakaoSdk.type
import kotlinx.coroutines.*

class GatheringMyMeetingFragment : BaseFragment<FragmentGatheringMyMeetingBinding>(
    FragmentGatheringMyMeetingBinding::bind,
    R.layout.fragment_gathering_my_meeting
), PopupMenu.OnMenuItemClickListener {
    //리사이클러뷰 갱신
    var data = MutableLiveData<GatheringProgramResult>()
    private val viewModel by viewModels<GatheringViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /*binding.fragmentGatheringMyMeetingClScheduledBlank.visibility = View.VISIBLE
        binding.fragmentGatheringMyMeetingRvScheduled.visibility = View.GONE
        binding.fragmentGatheringMyMeetingClLastBlank.visibility = View.VISIBLE
        binding.fragmentGatheringMyMeetingRvLast.visibility = View.GONE*/

        binding.fragmentGatheringMyMeetingRvScheduled.addItemDecoration(GatheringItemDecoration())
        binding.fragmentGatheringMyMeetingRvLast.addItemDecoration(GatheringItemDecoration())
        CoroutineScope(Dispatchers.IO).launch {
            setView()
        }
    }

    private fun showPopupScheduled(v: View) {
        val popup = PopupMenu(requireContext(), v)
        with(popup) {
            menuInflater.inflate(R.menu.fragment_gathering_my_meeting_popup_scheduled, popup.menu)
            setOnMenuItemClickListener(this@GatheringMyMeetingFragment)
            show()
        }
    }

    private fun showPopupLast(v: View) {
        val popup = PopupMenu(requireContext(), v)
        with(popup) {
            menuInflater.inflate(R.menu.fragment_gathering_my_meeting_popup_last, popup.menu)
            setOnMenuItemClickListener(this@GatheringMyMeetingFragment)
            show()
        }
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.details -> {
                //세미나로
                var type: String = ""
                val getType = runBlocking {
                    type = GaramgaebiApplication().loadStringData("type"
                    ).toString()
                }

                if (type == "SEMINAR") {
                    startActivity(
                        Intent(context, ContainerActivity::class.java)
                            .putExtra("seminar", true)
                            .putExtra("gathering-seminar", "gathering-seminar")
                    )
                }
                //네트워킹으로
                if (type == "NETWORKING"
                ) {
                    startActivity(
                        Intent(context, ContainerActivity::class.java)
                            .putExtra("networking", true)
                            .putExtra("gathering-networking", "gathering-networking")
                    )
                }
            }
            R.id.cancel -> {
                //신청 취소 프래그먼트로!
                startActivity(
                    Intent(context, ContainerActivity::class.java)
                        .putExtra("cancel", true)
                )
            }
        }
        return item != null
    }
    private suspend fun setView() {
        withContext(Dispatchers.Main) {
            with(viewModel) {
                //예정된 모임
                getGatheringProgramReady(myMemberIdx)

                programReady.observe(viewLifecycleOwner, Observer {
                    val result = it.result as ArrayList<GatheringProgramResult>
                    val myMeetingScheduledAdapter: GatheringMyMeetingScheduledRVAdapter

                    if (result == null||result.isEmpty()) {
                        binding.fragmentGatheringMyMeetingClScheduledBlank.visibility =
                            View.VISIBLE
                        binding.fragmentGatheringMyMeetingRvScheduled.visibility = View.GONE
                    } else {
                        binding.fragmentGatheringMyMeetingClScheduledBlank.visibility =
                            View.GONE
                        binding.fragmentGatheringMyMeetingRvScheduled.visibility = View.VISIBLE

                        myMeetingScheduledAdapter = GatheringMyMeetingScheduledRVAdapter(result)
                        binding.fragmentGatheringMyMeetingRvScheduled.apply {
                            removeItemDecoration(GatheringItemDecoration())
                            adapter = myMeetingScheduledAdapter
                            layoutManager =
                                LinearLayoutManager(
                                    activity,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                        }
                        myMeetingScheduledAdapter!!.setOnItemClickListener(object :
                            GatheringMyMeetingScheduledRVAdapter.OnItemClickListener {
                            override fun onMoreClick(position: Int, v: View) {
                                val program = it.result[position].programIdx
                                val type = it.result[position].type
                                val putType = runBlocking {
                                    GaramgaebiApplication().saveStringToDataStore("type",type)
                                    GaramgaebiApplication().saveIntToDataStore("programIdx",program)

                                }
//                                GaramgaebiApplication.sSharedPreferences.edit()
//                                    .putInt("programIdx", program)
//                                    .putString("type", type)
//                                    .apply()
                                showPopupScheduled(v)
                            }
                        })
                    }
                })

                // 지난 모임
                getGatheringProgramClosed(myMemberIdx)
                programClosed.observe(viewLifecycleOwner, Observer {
                    val result = it.result as ArrayList<GatheringProgramResult>
                    val myMeetingLastAdapter: GatheringMyMeetingLastRVAdapter
                    if (result.isEmpty()) {
                        binding.fragmentGatheringMyMeetingClLastBlank.visibility = View.VISIBLE
                        binding.fragmentGatheringMyMeetingRvLast.visibility = View.GONE
                    } else {
                        myMeetingLastAdapter = GatheringMyMeetingLastRVAdapter(result)
                        binding.fragmentGatheringMyMeetingClLastBlank.visibility = View.GONE
                        binding.fragmentGatheringMyMeetingRvLast.visibility = View.VISIBLE
                        binding.fragmentGatheringMyMeetingRvLast.apply {
                            adapter = myMeetingLastAdapter
                            layoutManager =
                                LinearLayoutManager(
                                    activity,
                                    LinearLayoutManager.VERTICAL,
                                    false
                                )
                        }
                        myMeetingLastAdapter.setOnItemClickListener(object :
                            GatheringMyMeetingLastRVAdapter.OnItemClickListener {
                            override fun onMoreClick(position: Int, v: View) {
                                val program = it.result[position].programIdx
                                val type = it.result[position].type
                                val putType = runBlocking {
                                    GaramgaebiApplication().saveStringToDataStore("type",type)
                                    GaramgaebiApplication().saveIntToDataStore("programIdx",program)

                                }
//                                GaramgaebiApplication.sSharedPreferences.edit()
//                                    .putInt("programIdx", program)
//                                    .putString("type", type)
//                                    .apply()
                                showPopupLast(v)
                            }
                        })
                    }
                })

            }
        }
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            updateData()
        }
    }

    private suspend fun updateData() {
        withContext(Dispatchers.IO) {
            viewModel.getGatheringProgramReady(myMemberIdx)
        }
    }
}
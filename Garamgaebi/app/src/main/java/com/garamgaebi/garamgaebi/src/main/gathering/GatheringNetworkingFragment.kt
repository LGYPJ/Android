package com.garamgaebi.garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.GatheringNetworkingDeadlineRVAdapter
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.FragmentGatheringNetworkingBinding
import com.garamgaebi.garamgaebi.model.GatheringNetworkingClosedResult
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.GatheringViewModel
import kotlinx.coroutines.*

class GatheringNetworkingFragment : BaseFragment<FragmentGatheringNetworkingBinding>(FragmentGatheringNetworkingBinding::bind, R.layout.fragment_gathering_networking){
    val viewModel by viewModels<GatheringViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 서버 꺼졌을 때 예외처리 하기 위해 시작할 때 뷰
        /*binding.fragmentGatheringNetworkingThisMonthClBlank.visibility = View.VISIBLE
        binding.fragmentGatheringNetworkingClThisMonth.visibility = View.GONE
        binding.fragmentGatheringNetworkingScheduledClBlank.visibility = View.VISIBLE
        binding.fragmentGatheringNetworkingClScheduled.visibility = View.GONE
        binding.fragmentGatheringNetworkingClosedClBlank.visibility = View.VISIBLE
        binding.fragmentGatheringNetworkingRvDeadline.visibility = View.GONE*/

        CoroutineScope(Dispatchers.IO).launch {
            setView()
        }

    }
    private suspend fun setView() {
        viewModel.getGatheringNetworkingThisMonth()
        viewModel.getGatheringNetworkingNextMonth()
        viewModel.getGatheringNetworkingClosed()
        withContext(Dispatchers.Main) {
            // 이번 달
            viewModel.networkingThisMonth.observe(viewLifecycleOwner, Observer{
                if (it.result == null) {
                    binding.fragmentGatheringNetworkingThisMonthClBlank.visibility = View.VISIBLE
                    binding.fragmentGatheringNetworkingClThisMonth.visibility = View.GONE
                } else {
                    binding.fragmentGatheringNetworkingThisMonthClBlank.visibility = View.GONE
                    binding.fragmentGatheringNetworkingClThisMonth.visibility = View.VISIBLE

                    binding.fragmentGatheringNetworkingThisMonthTvName.text = it.result.title
                    binding.fragmentGatheringNetworkingThisMonthTvDateData.text = GaramgaebiFunction().getDateYMD(it.result.date)
                    binding.fragmentGatheringNetworkingThisMonthTvPlaceData.text = it.result.location
                    binding.fragmentGatheringNetworkingThisMonthTvDDay.text = GaramgaebiFunction().getDDay(it.result.date)
                    if(it.result.isOpen == "BEFORE_OPEN"){
                        binding.fragmentGatheringNetworkingClThisMonth.isEnabled = false
                    }
                    val program = it.result.programIdx
                    binding.fragmentGatheringNetworkingClThisMonth.setOnClickListener {
                        val putData = runBlocking {
                            GaramgaebiApplication().saveIntToDataStore("programIdx",program)

                        }
//                        GaramgaebiApplication.sSharedPreferences
//                            .edit().putInt("programIdx", program)
//                            .apply()
                        //네트워킹 메인 프래그먼트로!
                        startActivity(Intent(context, ContainerActivity::class.java)
                            .putExtra("networking", true)
                            .putExtra("gonetworking", "gonetworking"))
                    }
                }

            })
            // 예정된
            viewModel.networkingNextMonth.observe(viewLifecycleOwner, Observer{
                val result = it.result
                if (result == null) {
                    binding.fragmentGatheringNetworkingScheduledClBlank.visibility = View.VISIBLE
                    binding.fragmentGatheringNetworkingClScheduled.visibility = View.GONE
                } else {
                    binding.fragmentGatheringNetworkingScheduledClBlank.visibility = View.GONE
                    binding.fragmentGatheringNetworkingClScheduled.visibility = View.VISIBLE

                    binding.fragmentGatheringNetworkingScheduledTvName.text = result.title
                    binding.fragmentGatheringNetworkingScheduledTvDateData.text = GaramgaebiFunction().getDateYMD(result.date)
                    binding.fragmentGatheringNetworkingScheduledTvPlaceData.text = result.location
                }
                binding.fragmentGatheringNetworkingClScheduled.isEnabled = false
            })
            // 마감된
            viewModel.networkingClosed.observe(viewLifecycleOwner, Observer{
                val result = it.result as ArrayList<GatheringNetworkingClosedResult>
                val networkingDeadlineAdapter : GatheringNetworkingDeadlineRVAdapter
                if (result == null || result.isEmpty()) {
                    binding.fragmentGatheringNetworkingClosedClBlank.visibility = View.VISIBLE
                    binding.fragmentGatheringNetworkingRvDeadline.visibility = View.GONE
                }
                else {
                    binding.fragmentGatheringNetworkingClosedClBlank.visibility = View.GONE
                    binding.fragmentGatheringNetworkingRvDeadline.visibility = View.VISIBLE
                    networkingDeadlineAdapter = GatheringNetworkingDeadlineRVAdapter(result)
                    binding.fragmentGatheringNetworkingRvDeadline.apply {
                        adapter = networkingDeadlineAdapter
                        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        addItemDecoration(GatheringItemDecoration())
                        networkingDeadlineAdapter.setOnItemClickListener(object :
                            GatheringNetworkingDeadlineRVAdapter.OnItemClickListener{
                            override fun onClick(position: Int) {
                                val program = it.result[position].programIdx
//                                GaramgaebiApplication.sSharedPreferences
//                                    .edit().putInt("programIdx", program)
//                                    .apply()
                                val putData = runBlocking {
                                    GaramgaebiApplication().saveIntToDataStore("programIdx",program)
                                }
                                //네트워킹 메인 프래그먼트로!
                                startActivity(Intent(context, ContainerActivity::class.java)
                                    .putExtra("networking", true)
                                    .putExtra("gonetworking", "gonetworking"))
                            }
                        })
                    }
                }
            })
        }
    }
}
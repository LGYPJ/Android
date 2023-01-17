package com.softsquared.template.Garamgaebi.src.main.gathering

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentGatheringNetworkingBinding
import com.softsquared.template.Garamgaebi.src.main.home.GatheringItemDecoration

class GatheringNetworkingFragment : BaseFragment<FragmentGatheringNetworkingBinding>(FragmentGatheringNetworkingBinding::bind, R.layout.fragment_gathering_networking){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var networkingDataList : ArrayList<GatheringNetworkingItemData> = arrayListOf(
            GatheringNetworkingItemData("로건 세미나","xxxx-xx-xx", "pp", 1),
            GatheringNetworkingItemData("신디 세미나", "xxxx-xx-xx", "pp", 2),
        )
        val networkingThisMonthAdapter = GatheringNetworkingThisMonthRVAdapter(networkingDataList)
        val networkingScheduledAdapter = GatheringNetworkingScheduledRVAdapter(networkingDataList)
        val networkingDeadlineAdapter = GatheringNetworkingDeadlineRVAdapter(networkingDataList)

        binding.fragmentGatheringNetworkingRvThisMonth.adapter = networkingThisMonthAdapter
        binding.fragmentGatheringNetworkingRvScheduled.adapter = networkingScheduledAdapter
        binding.fragmentGatheringNetworkingRvDeadline.adapter = networkingDeadlineAdapter

        binding.fragmentGatheringNetworkingRvThisMonth.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.fragmentGatheringNetworkingRvScheduled.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.fragmentGatheringNetworkingRvDeadline.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        binding.fragmentGatheringNetworkingRvThisMonth.addItemDecoration(GatheringItemDecoration())
        binding.fragmentGatheringNetworkingRvScheduled.addItemDecoration(GatheringItemDecoration())
        binding.fragmentGatheringNetworkingRvDeadline.addItemDecoration(GatheringItemDecoration())

        networkingThisMonthAdapter.setOnItemClickListener(object : GatheringNetworkingThisMonthRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                //TODO("Not yet implemented")
            }
        })
        networkingScheduledAdapter.setOnItemClickListener(object : GatheringNetworkingScheduledRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                //TODO("Not yet implemented")
            }
        })
        networkingDeadlineAdapter.setOnItemClickListener(object :GatheringNetworkingDeadlineRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                //TODO("Not yet implemented")
            }

        })
    }
}
package com.softsquared.template.Garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentGatheringNetworkingBinding
import com.softsquared.template.Garamgaebi.src.main.home.GatheringItemDecoration
import com.softsquared.template.Garamgaebi.src.main.networking.NetworkingActivity
import com.softsquared.template.Garamgaebi.src.seminar.SeminarFreeActivity
import com.softsquared.template.Garamgaebi.src.seminar.SeminarFreeApplyActivity

class GatheringNetworkingFragment : BaseFragment<FragmentGatheringNetworkingBinding>(FragmentGatheringNetworkingBinding::bind, R.layout.fragment_gathering_networking){
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val networkingThisMonthData = GatheringSeminarItemData("로건 네트워킹","xxxx-xx-xx", "pp", 1)
        val networkingScheduledData = GatheringSeminarItemData("신디 네트워킹","xxxx-xx-xx", "pp", 2)
        var networkingDataList : ArrayList<GatheringNetworkingItemData> = arrayListOf(
            GatheringNetworkingItemData("로건 세미나","xxxx-xx-xx", "pp", 1),
            GatheringNetworkingItemData("신디 세미나", "xxxx-xx-xx", "pp", 2),
        )
        // 이번 달
        if (networkingThisMonthData == null) {
            binding.fragmentGatheringNetworkingClBlank.visibility = View.VISIBLE
            binding.fragmentGatheringNetworkingClThisMonth.visibility = View.GONE
        } else {
            binding.fragmentGatheringNetworkingClBlank.visibility = View.GONE
            binding.fragmentGatheringNetworkingClThisMonth.visibility = View.VISIBLE
            binding.fragmentGatheringNetworkingThisMonthTvName.text = networkingThisMonthData.name
            binding.fragmentGatheringNetworkingThisMonthTvDateData.text = networkingThisMonthData.date
            binding.fragmentGatheringNetworkingThisMonthTvPlaceData.text = networkingThisMonthData.place
            if(networkingThisMonthData.dDay == 0) {
                binding.fragmentGatheringNetworkingThisMonthTvDDay.text = "D-day"
            } else {
                binding.fragmentGatheringNetworkingThisMonthTvDDay.text = "D-"+networkingThisMonthData.dDay
            }
        }

        // 예정된
        binding.fragmentGatheringNetworkingScheduledTvName.text = networkingScheduledData.name
        binding.fragmentGatheringNetworkingScheduledTvDate.text = networkingScheduledData.date
        binding.fragmentGatheringNetworkingScheduledTvPlaceData.text = networkingScheduledData.place
        if(networkingScheduledData.dDay == 0) {
            binding.fragmentGatheringNetworkingScheduledTvDDay.text = "D-day"
        } else {
            binding.fragmentGatheringNetworkingScheduledTvDDay.text = "D-"+networkingScheduledData.dDay
        }

        // 마감된
        val networkingDeadlineAdapter = GatheringNetworkingDeadlineRVAdapter(networkingDataList)
        binding.fragmentGatheringNetworkingRvDeadline.adapter = networkingDeadlineAdapter
        binding.fragmentGatheringNetworkingRvDeadline.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.fragmentGatheringNetworkingRvDeadline.addItemDecoration(GatheringItemDecoration())

        binding.fragmentGatheringNetworkingTvThisMonth.setOnClickListener {
            startActivity(Intent(activity, NetworkingActivity::class.java))
        }

        networkingDeadlineAdapter.setOnItemClickListener(object :GatheringNetworkingDeadlineRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                //TODO("Not yet implemented")
            }

        })
    }
}
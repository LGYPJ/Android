package com.softsquared.template.Garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentGatheringNetworkingBinding
import com.softsquared.template.Garamgaebi.model.GatheringNetworkingClosedResponse
import com.softsquared.template.Garamgaebi.model.GatheringNetworkingClosedResult
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import com.softsquared.template.Garamgaebi.src.main.home.GatheringItemDecoration
import com.softsquared.template.Garamgaebi.viewModel.GatheringViewModel

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
        val viewModel = ViewModelProvider(this)[GatheringViewModel::class.java]
        viewModel.getGatheringNetworkingThisMonth()
        viewModel.getGatheringNetworkingNextMonth()
        viewModel.getGatheringNetworkingClosed()

        viewModel.networkingThisMonth.observe(viewLifecycleOwner, Observer{
            val result = it.result
            if (result == null) {
                binding.fragmentGatheringNetworkingClBlank.visibility = View.VISIBLE
                binding.fragmentGatheringNetworkingClThisMonth.visibility = View.GONE
            } else {
                binding.fragmentGatheringNetworkingClBlank.visibility = View.GONE
                binding.fragmentGatheringNetworkingClThisMonth.visibility = View.VISIBLE
                binding.fragmentGatheringNetworkingThisMonthTvName.text = result.title
                binding.fragmentGatheringNetworkingThisMonthTvDateData.text = result.date
                binding.fragmentGatheringNetworkingThisMonthTvPlaceData.text = result.location
                //TODO 날짜
                binding.fragmentGatheringNetworkingThisMonthTvDDay.text = "D-day"
            }
        })
        // 예정된
        viewModel.networkingNextMonth.observe(viewLifecycleOwner, Observer{
            val result = it.result
            if (result == null) {

            } else {
                binding.fragmentGatheringNetworkingScheduledTvName.text = networkingScheduledData.name
                binding.fragmentGatheringNetworkingScheduledTvDate.text = networkingScheduledData.date
                binding.fragmentGatheringNetworkingScheduledTvPlaceData.text = networkingScheduledData.place
                // TODO 날짜
                binding.fragmentGatheringNetworkingScheduledTvDDay.text = "오픈예정"
            }
        })
        // 마감된
        viewModel.networkingClosed.observe(viewLifecycleOwner, Observer{
            val result = it.result as ArrayList<GatheringNetworkingClosedResult>
            val networkingDeadlineAdapter = GatheringNetworkingDeadlineRVAdapter(result)
            if (result.isEmpty()) {

            }
            else {
                binding.fragmentGatheringNetworkingRvDeadline.adapter = networkingDeadlineAdapter
                binding.fragmentGatheringNetworkingRvDeadline.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                binding.fragmentGatheringNetworkingRvDeadline.addItemDecoration(GatheringItemDecoration())

                binding.fragmentGatheringNetworkingClThisMonth.setOnClickListener {
                    //네트워킹 프래그먼트로!
                    val intent = Intent(context, ContainerActivity::class.java)
                    intent.putExtra("networking", true)
                    startActivity(intent)
                }
            }
            networkingDeadlineAdapter.setOnItemClickListener(object :GatheringNetworkingDeadlineRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    //TODO
                }
            })
        })



    }
}
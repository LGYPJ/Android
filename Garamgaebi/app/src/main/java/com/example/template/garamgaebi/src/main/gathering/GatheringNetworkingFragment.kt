package com.example.template.garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.adapter.GatheringNetworkingDeadlineRVAdapter
import com.example.template.garamgaebi.common.BaseFragment
import com.example.template.garamgaebi.common.GaramgaebiFunction
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.databinding.FragmentGatheringNetworkingBinding
import com.example.template.garamgaebi.model.GatheringNetworkingClosedResult
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.src.main.home.GatheringItemDecoration
import com.example.template.garamgaebi.viewModel.GatheringViewModel

class GatheringNetworkingFragment : BaseFragment<FragmentGatheringNetworkingBinding>(FragmentGatheringNetworkingBinding::bind, R.layout.fragment_gathering_networking){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 서버 꺼졌을 때 예외처리 하기 위해 시작할 때 뷰
        binding.fragmentGatheringNetworkingThisMonthClBlank.visibility = View.VISIBLE
        binding.fragmentGatheringNetworkingClThisMonth.visibility = View.GONE
        binding.fragmentGatheringNetworkingScheduledClBlank.visibility = View.VISIBLE
        binding.fragmentGatheringNetworkingClScheduled.visibility = View.GONE
        binding.fragmentGatheringNetworkingClosedClBlank.visibility = View.VISIBLE
        binding.fragmentGatheringNetworkingRvDeadline.visibility = View.GONE
        // 이번 달
        val viewModel by viewModels<GatheringViewModel>()
        viewModel.getGatheringNetworkingThisMonth()
        viewModel.getGatheringNetworkingNextMonth()
        viewModel.getGatheringNetworkingClosed()

        viewModel.networkingThisMonth.observe(viewLifecycleOwner, Observer{
            val result = it.result
            if (result == null) {
                binding.fragmentGatheringNetworkingThisMonthClBlank.visibility = View.VISIBLE
                binding.fragmentGatheringNetworkingClThisMonth.visibility = View.GONE
            } else {
                binding.fragmentGatheringNetworkingThisMonthClBlank.visibility = View.GONE
                binding.fragmentGatheringNetworkingClThisMonth.visibility = View.VISIBLE

                binding.fragmentGatheringNetworkingThisMonthTvName.text = result.title
                binding.fragmentGatheringNetworkingThisMonthTvDateData.text = GaramgaebiFunction().getDateYMD(result.date)
                binding.fragmentGatheringNetworkingThisMonthTvPlaceData.text = result.location
                binding.fragmentGatheringNetworkingThisMonthTvDDay.text = GaramgaebiFunction().getDDay(result.date)

            }
            val program = it.result.programIdx
            binding.fragmentGatheringNetworkingClThisMonth.setOnClickListener {
                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("programIdx", program)
                    .apply()
                //네트워킹 메인 프래그먼트로!
                val intent = Intent(context, ContainerActivity::class.java)
                intent.putExtra("networking", true)
                intent.putExtra("gonetworking", "gonetworking")
                startActivity(intent)
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
            if (result.isEmpty()) {
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
                            GaramgaebiApplication.sSharedPreferences
                                .edit().putInt("programIdx", program)
                                .apply()
                            //네트워킹 메인 프래그먼트로!
                            val intent = Intent(context, ContainerActivity::class.java)
                            intent.putExtra("networking", true)
                            intent.putExtra("gonetworking", "gonetworking")
                            startActivity(intent)
                        }
                    })
                }
            }
        })
    }
}
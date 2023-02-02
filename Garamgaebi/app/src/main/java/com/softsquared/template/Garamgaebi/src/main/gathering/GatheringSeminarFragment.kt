package com.softsquared.template.Garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentGatheringSeminarBinding
import com.softsquared.template.Garamgaebi.model.GatheringSeminarClosedResponse
import com.softsquared.template.Garamgaebi.model.GatheringSeminarClosedResult
import com.softsquared.template.Garamgaebi.model.GatheringSeminarResponse
import com.softsquared.template.Garamgaebi.model.GatheringSeminarResult
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import com.softsquared.template.Garamgaebi.src.main.home.GatheringItemDecoration
import com.softsquared.template.Garamgaebi.viewModel.GatheringViewModel

class GatheringSeminarFragment : BaseFragment<FragmentGatheringSeminarBinding>(FragmentGatheringSeminarBinding::bind, R.layout.fragment_gathering_seminar){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 이번 달
        val viewModel = ViewModelProvider(this)[GatheringViewModel::class.java]
        viewModel.getGatheringSeminarThisMonth()
        viewModel.getGatheringSeminarNextMonth()
        viewModel.getGatheringSeminarClosed()
        // 이번달
        viewModel.seminarThisMonth.observe(viewLifecycleOwner, Observer {
            val result = it.result
            if (result == null) {
                binding.fragmentGatheringSeminarClBlank.visibility = View.VISIBLE
                binding.fragmentGatheringSeminarClThisMonth.visibility = View.GONE
            } else {
                binding.fragmentGatheringSeminarClBlank.visibility = View.GONE
                binding.fragmentGatheringSeminarClThisMonth.visibility = View.VISIBLE
                binding.fragmentGatheringSeminarThisMonthTvName.text = result.title
                binding.fragmentGatheringSeminarThisMonthTvDateData.text = result.date
                binding.fragmentGatheringSeminarThisMonthTvPlaceData.text = result.location
                //TODO 날짜에 따라 D-day 바뀌게
                binding.fragmentGatheringSeminarThisMonthTvDDay.text = "D-day"
            }
        })

        // 예정된
        viewModel.seminarNextMonth.observe(viewLifecycleOwner, Observer {
            val result = it.result
            binding.fragmentGatheringSeminarScheduledTvName.text = result.title
            binding.fragmentGatheringSeminarScheduledTvDateData.text = result.date
            binding.fragmentGatheringSeminarScheduledTvPlaceData.text = result.location
            binding.fragmentGatheringSeminarScheduledTvDDay.text = "오픈예정"
        })

        // 마감된
        viewModel.seminarClosed.observe(viewLifecycleOwner, Observer {
            val seminarDeadlineAdapter = GatheringSeminarDeadlineRVAdapter(it.result as ArrayList<GatheringSeminarClosedResult>)
            binding.fragmentGatheringSeminarRvClosed.apply {
                adapter = seminarDeadlineAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(GatheringItemDecoration())
            }
            seminarDeadlineAdapter.setOnItemClickListener(object :GatheringSeminarDeadlineRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    //TODO("Not yet implemented")
                }
            })
        })

        binding.fragmentGatheringSeminarClScheduled.setOnClickListener {
            //세미나 메인 프래그먼트로!
            val intent = Intent(context, ContainerActivity::class.java)
            intent.putExtra("seminar", true)
            startActivity(intent)
        }

        binding.fragmentGatheringSeminarClThisMonth.setOnClickListener {
            //세미나 메인 프래그먼트로!
            val intent = Intent(context, ContainerActivity::class.java)
            intent.putExtra("seminar", true)
            startActivity(intent)
        }
    }



}
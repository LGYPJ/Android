package com.example.template.garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.config.BaseFragment
import com.example.template.garamgaebi.databinding.FragmentGatheringSeminarBinding
import com.example.template.garamgaebi.model.GatheringSeminarClosedResult
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.src.main.home.GatheringItemDecoration
import com.example.template.garamgaebi.viewModel.GatheringViewModel

class GatheringSeminarFragment : BaseFragment<FragmentGatheringSeminarBinding>(FragmentGatheringSeminarBinding::bind, R.layout.fragment_gathering_seminar){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 서버 꺼졌을 때 예외처리 하기 위해 시작할 때 뷰
        binding.fragmentGatheringSeminarThisMonthClBlank.visibility = View.VISIBLE
        binding.fragmentGatheringSeminarClThisMonth.visibility = View.GONE
        binding.fragmentGatheringSeminarScheduledClBlank.visibility = View.VISIBLE
        binding.fragmentGatheringSeminarClScheduled.visibility = View.GONE
        binding.fragmentGatheringSeminarClosedClBlank.visibility = View.VISIBLE
        binding.fragmentGatheringSeminarRvClosed.visibility = View.GONE


        // 이번 달
        val viewModel = ViewModelProvider(this)[GatheringViewModel::class.java]
        binding.viewModel = viewModel
        viewModel.getGatheringSeminarThisMonth()
        viewModel.getGatheringSeminarNextMonth()
        viewModel.getGatheringSeminarClosed()
        // 이번달
        viewModel.seminarThisMonth.observe(viewLifecycleOwner, Observer {
            val result = it.result
            if(result == null) {
                binding.fragmentGatheringSeminarThisMonthClBlank.visibility = View.VISIBLE
                binding.fragmentGatheringSeminarClThisMonth.visibility = View.GONE
            } else {
                binding.fragmentGatheringSeminarThisMonthClBlank.visibility = View.GONE
                binding.fragmentGatheringSeminarClThisMonth.visibility = View.VISIBLE

                binding.thisMonthModel = result
            }
        })

        // 예정된
        viewModel.seminarNextMonth.observe(viewLifecycleOwner, Observer {
            val result = it.result
            if(result == null || !it.isSuccess || it == null) {
                binding.fragmentGatheringSeminarScheduledClBlank.visibility = View.VISIBLE
                binding.fragmentGatheringSeminarClScheduled.visibility = View.GONE
            } else {
                binding.fragmentGatheringSeminarScheduledClBlank.visibility = View.GONE
                binding.fragmentGatheringSeminarClScheduled.visibility = View.VISIBLE
                binding.fragmentGatheringSeminarScheduledTvName.text = result.title
                binding.fragmentGatheringSeminarScheduledTvDateData.text = result.date
                binding.fragmentGatheringSeminarScheduledTvPlaceData.text = result.location
                binding.fragmentGatheringSeminarScheduledTvDDay.text = "오픈예정"
            }

        })

        // 마감된
        viewModel.seminarClosed.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<GatheringSeminarClosedResult>
            val seminarDeadlineAdapter = GatheringSeminarDeadlineRVAdapter(result)
            if(result.isEmpty() || !it.isSuccess || it == null) {
                binding.fragmentGatheringSeminarClosedClBlank.visibility = View.VISIBLE
                binding.fragmentGatheringSeminarRvClosed.visibility = View.GONE
            } else {
                binding.fragmentGatheringSeminarClosedClBlank.visibility = View.GONE
                binding.fragmentGatheringSeminarRvClosed.visibility = View.VISIBLE
                binding.fragmentGatheringSeminarRvClosed.apply {
                    adapter = seminarDeadlineAdapter
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    addItemDecoration(GatheringItemDecoration())
                }
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
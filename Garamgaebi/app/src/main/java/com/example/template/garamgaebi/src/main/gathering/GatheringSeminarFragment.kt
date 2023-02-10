package com.example.template.garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.adapter.GatheringSeminarDeadlineRVAdapter
import com.example.template.garamgaebi.common.BaseBindingFragment
import com.example.template.garamgaebi.common.BaseFragment
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.databinding.FragmentGatheringSeminarBinding
import com.example.template.garamgaebi.model.GatheringSeminarClosedResult
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.src.main.home.GatheringItemDecoration
import com.example.template.garamgaebi.viewModel.GatheringViewModel

/**
 * 세미나 모아보기 프래그먼트
 *
 */
class GatheringSeminarFragment : BaseFragment<FragmentGatheringSeminarBinding>(FragmentGatheringSeminarBinding::bind,R.layout.fragment_gathering_seminar){


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

                binding.fragmentGatheringSeminarThisMonthTvName.text = result.title
                binding.fragmentGatheringSeminarThisMonthTvDateData.text = result.date
                binding.fragmentGatheringSeminarThisMonthTvPlaceData.text = result.location
                binding.fragmentGatheringSeminarThisMonthTvDDay.text = "D-day"
            }
            val program = it.result.programIdx
            binding.fragmentGatheringSeminarClThisMonth.setOnClickListener {
                //세미나 메인 화면으로
                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("programIdx", program)
                    .apply()
                //세미나 메인 프래그먼트로!
                val intent = Intent(context, ContainerActivity::class.java)
                intent.putExtra("seminar", true)
                startActivity(intent)
            }
        })

        // 예정된
        viewModel.seminarNextMonth.observe(viewLifecycleOwner, Observer {
            val result = it.result
            if(result == null) {
                binding.fragmentGatheringSeminarScheduledClBlank.visibility = View.VISIBLE
                binding.fragmentGatheringSeminarClScheduled.visibility = View.GONE
            } else {
                binding.fragmentGatheringSeminarScheduledClBlank.visibility = View.GONE
                binding.fragmentGatheringSeminarClScheduled.visibility = View.VISIBLE

                binding.fragmentGatheringSeminarScheduledTvName.text = result.title
                binding.fragmentGatheringSeminarScheduledTvDateData.text = result.date
                binding.fragmentGatheringSeminarScheduledTvPlaceData.text = result.location
            }
            binding.fragmentGatheringSeminarClScheduled.isEnabled = false

        })

        // 마감된
        viewModel.seminarClosed.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<GatheringSeminarClosedResult>
            val seminarDeadlineAdapter : GatheringSeminarDeadlineRVAdapter
            if(result.isEmpty()) {
                binding.fragmentGatheringSeminarClosedClBlank.visibility = View.VISIBLE
                binding.fragmentGatheringSeminarRvClosed.visibility = View.GONE
            } else {
                seminarDeadlineAdapter = GatheringSeminarDeadlineRVAdapter(result)
                binding.fragmentGatheringSeminarClosedClBlank.visibility = View.GONE
                binding.fragmentGatheringSeminarRvClosed.visibility = View.VISIBLE
                binding.fragmentGatheringSeminarRvClosed.apply {
                    adapter = seminarDeadlineAdapter
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    addItemDecoration(GatheringItemDecoration())
                }
                seminarDeadlineAdapter.setOnItemClickListener(object :
                    GatheringSeminarDeadlineRVAdapter.OnItemClickListener{
                    override fun onClick(position: Int) {
                        val program = it.result[position].programIdx
                        GaramgaebiApplication.sSharedPreferences
                            .edit().putInt("programIdx", program)
                            .apply()
                        //세미나 메인 프래그먼트로!
                        val intent = Intent(context, ContainerActivity::class.java)
                        intent.putExtra("seminar", true)
                        startActivity(intent)
                    }
                })
            }
        })
    }



}
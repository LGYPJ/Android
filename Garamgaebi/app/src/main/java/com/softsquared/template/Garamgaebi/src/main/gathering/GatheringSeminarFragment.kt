package com.softsquared.template.Garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentGatheringSeminarBinding
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import com.softsquared.template.Garamgaebi.src.main.home.GatheringItemDecoration

class GatheringSeminarFragment : BaseFragment<FragmentGatheringSeminarBinding>(FragmentGatheringSeminarBinding::bind, R.layout.fragment_gathering_seminar){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 데이터
        val seminarThisMonthData = GatheringSeminarItemData("로건 세미나","xxxx-xx-xx", "pp", 1)
        val seminarScheduledData = GatheringSeminarItemData("신디 세미나","xxxx-xx-xx", "pp", 2)
        val seminarDataList : ArrayList<GatheringSeminarItemData> = arrayListOf(
            GatheringSeminarItemData("로건 세미나","xxxx-xx-xx", "pp", 1),
            GatheringSeminarItemData("신디 세미나","xxxx-xx-xx", "pp", 2)
        )
        // 이번 달
        if (seminarThisMonthData == null) {
            binding.fragmentGatheringSeminarClBlank.visibility = View.VISIBLE
            binding.fragmentGatheringSeminarClThisMonth.visibility = View.GONE
        } else {
            binding.fragmentGatheringSeminarClBlank.visibility = View.GONE
            binding.fragmentGatheringSeminarClThisMonth.visibility = View.VISIBLE
            binding.fragmentGatheringSeminarThisMonthTvName.text = seminarThisMonthData.name
            binding.fragmentGatheringSeminarThisMonthTvDate.text = seminarThisMonthData.date
            binding.fragmentGatheringSeminarThisMonthTvPlaceData.text = seminarThisMonthData.place
            if(seminarThisMonthData.dDay == 0)
                binding.fragmentGatheringSeminarThisMonthTvDDay.text = "D-day"
            else
                binding.fragmentGatheringSeminarThisMonthTvDDay.text = "D-"+seminarThisMonthData.dDay
        }


        // 예정된
        binding.fragmentGatheringSeminarScheduledTvName.text = seminarScheduledData.name
        binding.fragmentGatheringSeminarScheduledTvDate.text = seminarScheduledData.date
        binding.fragmentGatheringSeminarScheduledTvPlaceData.text = seminarScheduledData.place
        if(seminarThisMonthData.dDay == 0) {
            binding.fragmentGatheringSeminarScheduledTvDDay.text = "D-day"
        } else {
            binding.fragmentGatheringSeminarScheduledTvDDay.text = "D-"+seminarScheduledData.dDay
        }

        // 마감된
        val seminarDeadlineAdapter = GatheringSeminarDeadlineRVAdapter(seminarDataList)
        binding.fragmentGatheringSeminarRvClosed.adapter = seminarDeadlineAdapter
        binding.fragmentGatheringSeminarRvClosed.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.fragmentGatheringSeminarRvClosed.addItemDecoration(GatheringItemDecoration())
        binding.fragmentGatheringSeminarTvThisMonth.setOnClickListener {

        }


        binding.fragmentGatheringSeminarClThisMonth.setOnClickListener {
            //세미나 메인 프래그먼트로!

            val intent = Intent(context, ContainerActivity::class.java)
            intent.putExtra("seminar", true)
            startActivity(intent)

        }
        seminarDeadlineAdapter.setOnItemClickListener(object :GatheringSeminarDeadlineRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                //TODO("Not yet implemented")
            }

        })
    }



}
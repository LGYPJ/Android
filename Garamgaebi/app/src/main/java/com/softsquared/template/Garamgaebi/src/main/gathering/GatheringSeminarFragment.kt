package com.softsquared.template.Garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentGatheringSeminarBinding
import com.softsquared.template.Garamgaebi.databinding.FragmentSeminarBinding
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import com.softsquared.template.Garamgaebi.src.main.home.GatheringItemDecoration
import com.softsquared.template.Garamgaebi.src.main.home.HomeSeminarItemData
import com.softsquared.template.Garamgaebi.src.main.networking.NetworkingActivity
import com.softsquared.template.Garamgaebi.src.seminar.SeminarFragment
import com.softsquared.template.Garamgaebi.src.seminar.SeminarFreeActivity
import com.softsquared.template.Garamgaebi.src.seminar.SeminarFreeApplyActivity

class GatheringSeminarFragment : BaseFragment<FragmentGatheringSeminarBinding>(FragmentGatheringSeminarBinding::bind, R.layout.fragment_gathering_seminar){


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var seminarDataList : ArrayList<GatheringSeminarItemData> = arrayListOf(
            GatheringSeminarItemData("로건 세미나","xxxx-xx-xx", "pp", 1),
            GatheringSeminarItemData("신디 세미나", "xxxx-xx-xx", "pp", 2),
        )

        //TODO : 날짜에 따라 data 나누기
        val seminarThisMonthAdapter = GatheringSeminarThisMonthRVAdapter(seminarDataList)
        val seminarScheduledAdapter = GatheringSeminarScheduledRVAdapter(seminarDataList)
        val seminarDeadlineAdapter = GatheringSeminarDeadlineRVAdapter(seminarDataList)

        binding.fragmentGatheringSeminarRvThisMonth.adapter = seminarThisMonthAdapter
        binding.fragmentGatheringSeminarRvScheduled.adapter = seminarScheduledAdapter
        binding.fragmentGatheringSeminarRvDeadline.adapter = seminarDeadlineAdapter

        binding.fragmentGatheringSeminarRvThisMonth.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.fragmentGatheringSeminarRvScheduled.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.fragmentGatheringSeminarRvDeadline.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        binding.fragmentGatheringSeminarRvThisMonth.addItemDecoration(GatheringItemDecoration())
        binding.fragmentGatheringSeminarRvScheduled.addItemDecoration(GatheringItemDecoration())
        binding.fragmentGatheringSeminarRvDeadline.addItemDecoration(GatheringItemDecoration())

        binding.fragmentGatheringSeminarTvThisMonth.setOnClickListener {
            startActivity(Intent(activity, SeminarFreeActivity::class.java))
        }
        seminarThisMonthAdapter.setOnItemClickListener(object : GatheringSeminarThisMonthRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                //세미나 메인 프래그먼트로!
                /*val intent = Intent(context, SeminarFragment::class.java)
                val tmp = "gathering_seminar"
                intent.putExtra("gathering_seminar",tmp )
                startActivity(intent)*/


            }
        })
        seminarScheduledAdapter.setOnItemClickListener(object : GatheringSeminarScheduledRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                //TODO("Not yet implemented")
            }
        })
        seminarDeadlineAdapter.setOnItemClickListener(object :GatheringSeminarDeadlineRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                //TODO("Not yet implemented")
            }

        })
    }
}
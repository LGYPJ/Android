package com.softsquared.template.Garamgaebi.src.main.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var seminarDataList: ArrayList<HomeSeminarItemData> = arrayListOf(
            HomeSeminarItemData(1, true,"로건 세미나","xxxx-xx-xx", "pp", 1),
            HomeSeminarItemData(2, false,"신디 세미나", "xxxx-xx-xx", "pp", 2)
        )
        var networkingDataList: ArrayList<HomeNetworkingItemData> = arrayListOf(
            HomeNetworkingItemData(1, true,"로건 네트워킹","xxxx-xx-xx", "pp", 1),
            HomeNetworkingItemData(2, false,"신디 네트워킹", "xxxx-xx-xx", "pp", 2)
        )
        var userDataList: ArrayList<HomeUserItemData> = arrayListOf()
        for(i : Int in 1..10) {
            userDataList.add(HomeUserItemData(R.drawable.ic_network_profile,"name$i", "소속", "전공/직무"))
        }
        var myMeetingDataList : ArrayList<HomeMyMeetingItemData> = arrayListOf(
            HomeMyMeetingItemData("xx월 xx일\nxx:xx","로건 세미나","pp"),
            HomeMyMeetingItemData("xx월 xx일\nxx:xx","신디 네트워킹","pp"),
            HomeMyMeetingItemData("xx월 xx일\nxx:xx","짱구 세미나","pp"),
            HomeMyMeetingItemData("xx월 xx일\nxx:xx","찹도 네트워킹","pp")
        )

        val seminarRVAdapter = HomeSeminarRVAdapter(seminarDataList)
        val networkingRVAdapter = HomeNetworkingRVAdapter(networkingDataList)
        val userRVAdapter = HomeUserItemRVAdapter(userDataList)
        val myMeetingRVAdapter = HomeMyMeetingRVAdapter(myMeetingDataList)

        binding.fragmentHomeRvSeminar.adapter = seminarRVAdapter
        binding.fragmentHomeRvNetworking.adapter = networkingRVAdapter
        binding.fragmentHomeRvUser.adapter = userRVAdapter
        binding.fragmentHomeRvMyMeeting.adapter = myMeetingRVAdapter

        binding.fragmentHomeRvSeminar.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvNetworking.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.fragmentHomeRvUser.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)

        binding.fragmentHomeRvSeminar.addItemDecoration(HomeSeminarItemDecoration())
        binding.fragmentHomeRvNetworking.addItemDecoration(HomeNetworkingItemDecoration())
        binding.fragmentHomeRvUser.addItemDecoration(HomeUserItemDecoration())
        binding.fragmentHomeRvMyMeeting.addItemDecoration(HomeMyMeetingItemDecoration())

        seminarRVAdapter.setOnItemClickListener(object : HomeSeminarRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                // TODO("Not yet implemented")
            }
        })
    }
}
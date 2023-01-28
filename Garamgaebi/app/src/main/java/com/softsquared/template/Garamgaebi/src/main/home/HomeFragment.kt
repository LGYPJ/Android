package com.softsquared.template.Garamgaebi.src.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentHomeBinding
import com.softsquared.template.Garamgaebi.src.main.MainActivity
import com.softsquared.template.Garamgaebi.src.main.gathering.GatheringFragment
import com.softsquared.template.Garamgaebi.src.main.gathering.GatheringSeminarFragment
import com.softsquared.template.Garamgaebi.src.main.notification.NotificationActivity


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 뷰페이저 간격 조절을 위한 변수
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.homeItemPageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.homeItemPagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        // 데이터 리스트
        var seminarDataList: ArrayList<HomeSeminarItemData> = arrayListOf(
            HomeSeminarItemData(1, true,"로건 세미나","xxxx-xx-xx", "pp", 1),
            HomeSeminarItemData(2, false,"신디 세미나", "xxxx-xx-xx", "pp", 2),
            HomeSeminarItemData(3, false,"짱구 세미나","xxxx-xx-xx", "pp", 3)
        )
        var networkingDataList: ArrayList<HomeNetworkingItemData> = arrayListOf(
            HomeNetworkingItemData(1, true,"로건 네트워킹","xxxx-xx-xx", "pp", 1),
            HomeNetworkingItemData(2, false,"신디 네트워킹", "xxxx-xx-xx", "pp", 2),
            HomeNetworkingItemData(3, false,"짱구 네트워킹","xxxx-xx-xx", "pp", 3)
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

        // 리사이클러뷰 어댑터
        val seminarRVAdapter = HomeSeminarRVAdapter(seminarDataList)
        val networkingRVAdapter = HomeNetworkingRVAdapter(networkingDataList)
        val userRVAdapter = HomeUserItemRVAdapter(userDataList)
        val myMeetingRVAdapter = HomeMyMeetingRVAdapter(myMeetingDataList)

        // 어댑터 적용
        binding.fragmentHomeVpSeminar.apply {
            adapter = seminarRVAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
            setPageTransformer { page, position ->
                page.translationX = position * -offsetPx
            }
            addItemDecoration(HomeVPItemDecoration(requireContext(), seminarDataList.size))
        }
        binding.fragmentHomeVpNetworking.apply {
            adapter = networkingRVAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                }
            })
            setPageTransformer { page, position ->
                page.translationX = position * -offsetPx
            }
            addItemDecoration(HomeVPItemDecoration(requireContext(), networkingDataList.size))
        }

        binding.fragmentHomeRvUser.apply {
            adapter = userRVAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(HomeUserItemDecoration())
        }
        if(myMeetingDataList.isEmpty()) {
            binding.fragmentHomeClMyMeetingsBlank.visibility = View.VISIBLE
        } else {
            binding.fragmentHomeRvMyMeeting.apply {
                adapter = myMeetingRVAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(HomeMyMeetingItemDecoration())
            }
            binding.fragmentHomeClMyMeetingsBlank.visibility = View.GONE
        }

        binding.fragmentHomeRvUser.addItemDecoration(HomeUserItemDecoration())
        binding.fragmentHomeRvMyMeeting.addItemDecoration(HomeMyMeetingItemDecoration())


        seminarRVAdapter.setOnItemClickListener(object : HomeSeminarRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                // TODO("Not yet implemented")
            }
        })
        networkingRVAdapter.setOnItemClickListener(object : HomeNetworkingRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                // TODO("Not yet implemented")
            }
        })
        userRVAdapter.setOnItemClickListener(object : HomeUserItemRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                // TODO("Not yet implemented")
            }
        })
        myMeetingRVAdapter.setOnItemClickListener(object : HomeMyMeetingRVAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                // TODO("Not yet implemented")
            }
        })

        binding.fragmentHomeIvNotification.setOnClickListener {
            startActivity(Intent(activity, NotificationActivity::class.java))
        }

        binding.fragmentHomeClGatheringSeminar.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.activity_main_frm, GatheringFragment()).commit()
            (activity as MainActivity).goGathering()

        }
        binding.fragmentHomeClGatheringNetworking.setOnClickListener {
            parentFragmentManager.beginTransaction().replace(R.id.activity_main_frm, GatheringFragment()).commit()
            (activity as MainActivity).goGathering()
        }


    }
}
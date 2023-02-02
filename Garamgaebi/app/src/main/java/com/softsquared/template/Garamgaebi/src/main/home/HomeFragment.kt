package com.softsquared.template.Garamgaebi.src.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentHomeBinding
import com.softsquared.template.Garamgaebi.model.HomeNetworkingResult
import com.softsquared.template.Garamgaebi.model.HomeProgramResult
import com.softsquared.template.Garamgaebi.model.HomeSeminarResult
import com.softsquared.template.Garamgaebi.model.HomeUserResult
import com.softsquared.template.Garamgaebi.src.main.MainActivity
import com.softsquared.template.Garamgaebi.src.main.notification.NotificationActivity
import com.softsquared.template.Garamgaebi.src.seminar.HomeNetworkingHelpDialog
import com.softsquared.template.Garamgaebi.src.seminar.HomeSeminarHelpDialog
import com.softsquared.template.Garamgaebi.viewModel.HomeViewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 뷰페이저 간격 조절을 위한 변수
        val pageMarginPx = resources.getDimensionPixelOffset(R.dimen.homeItemPageMargin)
        val pagerWidth = resources.getDimensionPixelOffset(R.dimen.homeItemPagerWidth)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pageMarginPx - pagerWidth

        // 뷰모델
        val viewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        viewModel.getHomeSeminar()
        viewModel.getHomeNetworking()
        viewModel.getHomeUser()
        viewModel.getHomeProgram(1)

        // 세미나
        viewModel.seminar.observe(viewLifecycleOwner, Observer {
            val seminarRVAdapter = HomeSeminarRVAdapter(it.result as ArrayList<HomeSeminarResult>)
            binding.fragmentHomeVpSeminar.apply {
                adapter = seminarRVAdapter
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                    }
                })
                offscreenPageLimit = 1
                // 간격 조절
                setPageTransformer { page, position ->
                    page.translationX = position * -offsetPx
                }
                addItemDecoration(HomeVPItemDecoration(requireContext()))
            }
            // 리사이클러뷰 클릭 리스너
            seminarRVAdapter.setOnItemClickListener(object : HomeSeminarRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    // TODO("Not yet implemented")
                }
            })
        })
        // 네트워킹
        viewModel.networking.observe(viewLifecycleOwner, Observer {
            val networkingRVAdapter = HomeNetworkingRVAdapter(it.result as ArrayList<HomeNetworkingResult>)
            binding.fragmentHomeVpNetworking.apply {
                adapter = networkingRVAdapter
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                    override fun onPageSelected(position: Int) {
                        super.onPageSelected(position)
                    }
                })
                offscreenPageLimit = 1
                setPageTransformer { page, position ->
                    page.translationX = position * -offsetPx
                }
                addItemDecoration(HomeVPItemDecoration(requireContext()))
            }
            // 리사이클러뷰 클릭 리스너
            networkingRVAdapter.setOnItemClickListener(object : HomeNetworkingRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    // TODO("Not yet implemented")
                }
            })
        })
        // 유저 프로필 10명
        viewModel.user.observe(viewLifecycleOwner, Observer {
            val userRVAdapter = HomeUserItemRVAdapter(it.result as ArrayList<HomeUserResult>)
            binding.fragmentHomeRvUser.apply {
                adapter = userRVAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(HomeUserItemDecoration())
            }
            // 리사이클러뷰 클릭 리스너
            userRVAdapter.setOnItemClickListener(object : HomeUserItemRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    // TODO("Not yet implemented")
                }
            })
        })
        // 내 모임
        viewModel.program.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<HomeProgramResult>
            val myMeetingRVAdapter = HomeMyMeetingRVAdapter(result)
            if(result.isEmpty()) {
                binding.fragmentHomeClMyMeetingsBlank.visibility = View.VISIBLE
            } else {
                binding.fragmentHomeRvMyMeeting.apply {
                    adapter = myMeetingRVAdapter
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    addItemDecoration(HomeMyMeetingItemDecoration())
                }
                binding.fragmentHomeClMyMeetingsBlank.visibility = View.GONE
            }
            // 리사이클러뷰 클릭 리스너
            myMeetingRVAdapter.setOnItemClickListener(object : HomeMyMeetingRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    // TODO("Not yet implemented")
                }
            })
        })

        // 리사이클러뷰 어댑터
        //val seminarRVAdapter = HomeSeminarRVAdapter(seminarDataList)
        //val networkingRVAdapter = HomeNetworkingRVAdapter(networkingDataList)
        //val userRVAdapter = HomeUserItemRVAdapter(userDataList)
        //val myMeetingRVAdapter = HomeMyMeetingRVAdapter(myMeetingDataList)

        binding.fragmentHomeIvNotification.setOnClickListener {
            startActivity(Intent(activity, NotificationActivity::class.java))
        }
        // 모아보기 세미나 이동
        binding.fragmentHomeClGatheringSeminar.setOnClickListener {
            (activity as MainActivity).goGatheringSeminar()
            goGathering()
        }
        // 모아보기 네트워킹 이동
        binding.fragmentHomeClGatheringNetworking.setOnClickListener {
            (activity as MainActivity).goGatheringNetworking()
            goGathering()
        }

        // 세미나 도움말
        binding.fragmentHomeIvSeminarHelpBtn.setOnClickListener { v ->
            Log.d("test", "${v.x.toInt()}, ${v.y.toInt()}")
            activity?.let {
                HomeSeminarHelpDialog(v.x.toInt(), v.y.toInt()).show(
                    it.supportFragmentManager, "HomeSeminarHelpDialog"
                )
            }
            true
        }
        // 네트워크 도움말
        binding.fragmentHomeIvNetworkingHelpBtn.setOnClickListener { v ->
            Log.d("test", "${v.x.toInt()}, ${v.y.toInt()}")
            activity?.let {
                HomeNetworkingHelpDialog(v.x.toInt(), v.y.toInt()).show(
                    it.supportFragmentManager, "HomeSeminarHelpDialog"
                )
            }
            true
        }
    }
    private fun goGathering() {
        val findFragment = parentFragmentManager.findFragmentByTag("gathering")
        parentFragmentManager.fragments.forEach { fm ->
            parentFragmentManager.beginTransaction().hide(fm).commitAllowingStateLoss()
        }
        findFragment?.let {
            // 프래그먼트 상태 정보가 있는 경우, 보여주기만
            parentFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        }
    }
}
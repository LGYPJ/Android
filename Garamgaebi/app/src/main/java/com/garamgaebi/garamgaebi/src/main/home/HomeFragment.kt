package com.garamgaebi.garamgaebi.src.main.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.HomeMyMeetingRVAdapter
import com.garamgaebi.garamgaebi.adapter.HomeNetworkingRVAdapter
import com.garamgaebi.garamgaebi.adapter.HomeSeminarRVAdapter
import com.garamgaebi.garamgaebi.adapter.HomeUserItemRVAdapter
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentHomeBinding
import com.garamgaebi.garamgaebi.model.HomeNetworkingResult
import com.garamgaebi.garamgaebi.model.HomeProgramResult
import com.garamgaebi.garamgaebi.model.HomeSeminarResult
import com.garamgaebi.garamgaebi.model.HomeUserResult
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.src.main.MainActivity
import com.garamgaebi.garamgaebi.viewModel.HomeViewModel


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 서버 꺼졌을 때 예외처리 하기 위해 시작할 때 뷰
        constraintsConnect(binding.fragmentHomeTvNetworking, binding.fragmentHomeClSeminarBlank)
        with(binding) {
            fragmentHomeClSeminarBlank.visibility = View.VISIBLE
            fragmentHomeClNetworkingBlank.visibility = View.VISIBLE
            fragmentHomeClUserBlank.visibility = View.VISIBLE
            fragmentHomeClMyMeetingsBlank.visibility = View.VISIBLE
        }

        // 뷰페이저 간격 조절을 위한 변수
        val pagerWidth = resources.displayMetrics.widthPixels
            .minus(resources.getDimensionPixelOffset(R.dimen.exceptionHomeItemWidth))
        val pageMargin = resources.getDimensionPixelOffset(R.dimen.homeItemMargin)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pagerWidth - pageMargin

        // 뷰모델
        val viewModel by viewModels<HomeViewModel>()

        // 세미나
        viewModel.getHomeSeminar()
        viewModel.seminar.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<HomeSeminarResult>
            val seminarRVAdapter : HomeSeminarRVAdapter
            if(result.isEmpty()) {
                binding.fragmentHomeClSeminarBlank.visibility = View.VISIBLE
                constraintsConnect(binding.fragmentHomeTvNetworking, binding.fragmentHomeClSeminarBlank)
            } else {
                seminarRVAdapter = HomeSeminarRVAdapter(result)
                binding.fragmentHomeVpSeminar.apply {
                    adapter = seminarRVAdapter
                    orientation = ViewPager2.ORIENTATION_HORIZONTAL
                    offscreenPageLimit = 1
                    // 간격 조절
                    setPageTransformer { page, position ->
                        page.translationX = position * -offsetPx
                    }
                    addItemDecoration(HomeVPItemDecoration(requireContext()))
                }
                constraintsConnect(binding.fragmentHomeTvNetworking, binding.fragmentHomeVpSeminar)
                binding.fragmentHomeClSeminarBlank.visibility = View.GONE
                // 리사이클러뷰 클릭 리스너
                seminarRVAdapter.setOnItemClickListener(object : HomeSeminarRVAdapter.OnItemClickListener{
                    override fun onClick(position: Int) {
                        if(it.result[position].isOpen == "OPEN"){
                            val program = it.result[position].programIdx
                            GaramgaebiApplication.sSharedPreferences
                                .edit().putInt("programIdx", program)
                                .apply()
                            //세미나 메인 프래그먼트로!
                            val intent = Intent(context, ContainerActivity::class.java)
                            intent.putExtra("seminar", true)
                            //intent.putExtra("HomeSeminarIdx", program)
                            startActivity(intent)
                        }
                    }
                })
            }
        })

        // 네트워킹
        viewModel.getHomeNetworking()
        viewModel.networking.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<HomeNetworkingResult>
            val networkingRVAdapter : HomeNetworkingRVAdapter
            if(result.isEmpty()) {
                binding.fragmentHomeClNetworkingBlank.visibility = View.VISIBLE
            } else {
                networkingRVAdapter = HomeNetworkingRVAdapter(result)
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
                binding.fragmentHomeClNetworkingBlank.visibility = View.GONE
                // 리사이클러뷰 클릭 리스너
                networkingRVAdapter.setOnItemClickListener(object : HomeNetworkingRVAdapter.OnItemClickListener{
                    override fun onClick(position: Int) {
                        val program = it.result[position].programIdx
                        GaramgaebiApplication.sSharedPreferences
                            .edit().putInt("programIdx", program)
                            .apply()
                        //네트워킹 메인 프래그먼트로!
                        val intent = Intent(context, ContainerActivity::class.java)
                        intent.putExtra("networking", true)
                        startActivity(intent)
                    }
                })
            }
        })

        // 유저 프로필 11명
        viewModel.getHomeUser()
        viewModel.user.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<HomeUserResult>
            val userRVAdapter : HomeUserItemRVAdapter
            if(result.isEmpty()) {
                binding.fragmentHomeClUserBlank.visibility = View.VISIBLE
            } else {
                userRVAdapter = HomeUserItemRVAdapter(result)
                binding.fragmentHomeRvUser.apply {
                    adapter = userRVAdapter
                    layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
                    addItemDecoration(HomeUserItemDecoration(requireContext()))
                }
                binding.fragmentHomeClUserBlank.visibility = View.GONE
                // 리사이클러뷰 클릭 리스너
                userRVAdapter.setOnItemClickListener(object : HomeUserItemRVAdapter.OnItemClickListener{
                    override fun onClick(position: Int) {
                        GaramgaebiApplication.sSharedPreferences.edit().putInt("userMemberIdx",result[position].memberIdx).apply()

                        val intent = Intent(context, ContainerActivity::class.java)
                        intent.putExtra("someoneProfile", true)
                        startActivity(intent)
                    }
                })
            }
        })

        // 내 모임
        viewModel.getHomeProgram(22)
        viewModel.program.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<HomeProgramResult>
            val myMeetingRVAdapter: HomeMyMeetingRVAdapter
            if (result.isEmpty()) {
                binding.fragmentHomeClMyMeetingsBlank.visibility = View.VISIBLE
            } else {
                myMeetingRVAdapter = HomeMyMeetingRVAdapter(result)
                binding.fragmentHomeRvMyMeeting.apply {
                    adapter = myMeetingRVAdapter
                    layoutManager =
                        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                    addItemDecoration(HomeMyMeetingItemDecoration())
                }
                binding.fragmentHomeClMyMeetingsBlank.visibility = View.GONE
                // 리사이클러뷰 클릭 리스너
                myMeetingRVAdapter.setOnItemClickListener(object :
                    HomeMyMeetingRVAdapter.OnItemClickListener {
                    override fun onClick(position: Int) {
                        val program = it.result[position].programIdx
                        GaramgaebiApplication.sSharedPreferences
                            .edit().putInt("programIdx", program)
                            .apply()

                        //세미나 메인 프래그먼트로!
                        if(it.result[position].type == "SEMINAR"){
                            val intent = Intent(context, ContainerActivity::class.java)
                            intent.putExtra("seminar", true)
                            startActivity(intent)
                        }
                        //네트워킹 메인 프래그먼트로
                        if(it.result[position].type == "NETWORKING"){
                            val intent = Intent(context, ContainerActivity::class.java)
                            intent.putExtra("networking", true)
                            startActivity(intent)
                        }
                    }
                })
            }
        })
        // 알림 이동
        binding.fragmentHomeIvNotification.setOnClickListener {
            val target = Intent(context, ContainerActivity::class.java)
            target.putExtra("notification", true)
            startActivity(target)
        }

        // 읽지 않은 알림 존재 여부
        viewModel.getNotificationUnread(22)
        viewModel.notificationUnread.observe(viewLifecycleOwner, Observer {
            if(it.result.isUnreadExist)
                binding.fragmentHomeIvNotificationPoint.visibility = View.VISIBLE
            else
                binding.fragmentHomeIvNotificationPoint.visibility = View.GONE
        })

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
        binding.fragmentHomeIvSeminarHelpBtn.setOnClickListener {
            (activity as MainActivity).getHelpFrame()
            binding.fragmentHomeTvSeminarHelp.visibility = View.VISIBLE
        }

        // 네트워크 도움말
        binding.fragmentHomeIvNetworkingHelpBtn.setOnClickListener {
            (activity as MainActivity).getHelpFrame()
            binding.fragmentHomeTvNetworkingHelp.visibility = View.VISIBLE
        }
    }
    private fun goGathering() {
        val findFragment = parentFragmentManager.findFragmentByTag("gathering")
        parentFragmentManager.fragments.forEach { fm ->
            parentFragmentManager.beginTransaction().hide(fm).commitAllowingStateLoss()
        }
        findFragment?.let {
            parentFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        }
    }

    private fun constraintsConnect(mainView: View, targetView : View) {
        val constraints = ConstraintSet()
        constraints.clone(binding.fragmentHomeClMeeting)
        constraints.connect(
            mainView.id,
            ConstraintSet.TOP,
            targetView.id,
            ConstraintSet.BOTTOM,
        )
        constraints.applyTo(binding.fragmentHomeClMeeting)
    }
    fun goneSeminarHelp() {
        binding.fragmentHomeTvSeminarHelp.visibility = View.GONE
    }
    fun goneNetworkingHelp() {
        binding.fragmentHomeTvNetworkingHelp.visibility = View.GONE
    }

}
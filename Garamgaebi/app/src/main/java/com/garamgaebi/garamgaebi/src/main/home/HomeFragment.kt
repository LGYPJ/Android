package com.garamgaebi.garamgaebi.src.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AlphaAnimation
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.*
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.networkValid
import com.garamgaebi.garamgaebi.databinding.FragmentHomeBinding
import com.garamgaebi.garamgaebi.model.*
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.src.main.MainActivity
import com.garamgaebi.garamgaebi.viewModel.HomeViewModel
import kotlinx.coroutines.*

/**
 * 홈 프래그먼트 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home) {
    val viewModel by viewModels<HomeViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 뷰페이저 간격 조절을 위한 변수
        val pagerWidth = resources.displayMetrics.widthPixels
            .minus(resources.getDimensionPixelOffset(R.dimen.exceptionHomeItemWidth))
        val pageMargin = resources.getDimensionPixelOffset(R.dimen.homeItemMargin)
        val screenWidth = resources.displayMetrics.widthPixels
        val offsetPx = screenWidth - pagerWidth - pageMargin

        with(binding) {
            // 서버 꺼졌을 때 예외처리 하기 위해 시작할 때 뷰
            /*constraintsConnect(binding.fragmentHomeTvNetworking, binding.fragmentHomeClSeminarBlank)
            fragmentHomeClSeminarBlank.visibility = View.VISIBLE
            fragmentHomeClNetworkingBlank.visibility = View.VISIBLE
            fragmentHomeClUserBlank.visibility = View.VISIBLE
            fragmentHomeClMyMeetingsBlank.visibility = View.VISIBLE*/

            fragmentHomeVpSeminar.apply {
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                offscreenPageLimit = 1
                //간격 조절
                setPageTransformer { page, position ->
                    page.translationX = position * -offsetPx
                }
                addItemDecoration(HomeVPItemDecoration(requireContext()))
            }
            fragmentHomeVpNetworking.apply {
                orientation = ViewPager2.ORIENTATION_HORIZONTAL
                offscreenPageLimit = 1
                setPageTransformer { page, position ->
                    page.translationX = position * -offsetPx
                }
                addItemDecoration(HomeVPItemDecoration(requireContext()))
            }
            fragmentHomeRvUser.addItemDecoration(HomeUserItemDecoration(requireContext()))
            fragmentHomeRvMyMeeting.addItemDecoration(HomeMyMeetingItemDecoration())

        }
        setView()
        viewModel.getHomeUser()

        // 알림 이동
        binding.fragmentHomeIvNotification.setOnClickListener {
            startActivity(
                Intent(context, ContainerActivity::class.java).putExtra(
                    "openFragment",
                    NOTIFICATION
                )
            )
        }

        // 모아보기 세미나 이동
        binding.fragmentHomeClGatheringSeminar.setOnClickListener {
            Log.d("goGatheringSeminarBtn", "goGatheringSeminarBtn")
            goGathering()
            (activity as MainActivity).goGatheringSeminar()
        }
        // 모아보기 네트워킹 이동
        binding.fragmentHomeClGatheringNetworking.setOnClickListener {
            Log.d("goGatheringNetworkingBtn", "goGatheringNetworkingBtn")
            goGathering()
            (activity as MainActivity).goGatheringNetworking()
        }

        // 세미나 도움말
        binding.fragmentHomeIvSeminarHelpBtn.setOnClickListener {
            (activity as MainActivity).getSeminarHelpFrame()
            binding.fragmentHomeIvSeminarHelp.visibility = View.VISIBLE
            binding.fragmentHomeIvSeminarHelp.startAnimation(
                AlphaAnimation(0f, 1f).apply {
                duration = 500 // 애니메이션 지속 시간 (1초)
            })
        }

        // 네트워크 도움말
        binding.fragmentHomeIvNetworkingHelpBtn.setOnClickListener {
            (activity as MainActivity).getNetworkingHelpFrame()
            binding.fragmentHomeIvNetworkingHelp.visibility = View.VISIBLE
            binding.fragmentHomeIvNetworkingHelp.startAnimation(
                AlphaAnimation(0f, 1f).apply {
                    duration = 500 // 애니메이션 지속 시간 (1초)
            })
        }
    }

    override fun onResume() {
        super.onResume()
        if(networkValid.value == true) {
            Log.d("network","onResumeHomeTrue")
            updateData()
        }


    }
    private fun setView() {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.Main) {
                // 세미나
                viewModel.seminar.observe(viewLifecycleOwner, Observer {
                    val result = it.result as ArrayList<HomeSeminarResult>
                    val seminarRVAdapter: HomeSeminarRVAdapter
                    if (result == null || result.isEmpty()) {
                        binding.fragmentHomeVpSeminar.visibility = View.GONE
                        binding.fragmentHomeClSeminarBlank.visibility = View.VISIBLE
                        constraintsConnect(
                            binding.fragmentHomeTvNetworking,
                            binding.fragmentHomeClSeminarBlank
                        )
                    } else {
                        seminarRVAdapter = HomeSeminarRVAdapter(result)
                        binding.fragmentHomeVpSeminar.apply {
                            adapter = seminarRVAdapter
                        }
                        constraintsConnect(
                            binding.fragmentHomeTvNetworking,
                            binding.fragmentHomeVpSeminar
                        )
                        binding.fragmentHomeVpSeminar.visibility = View.VISIBLE
                        binding.fragmentHomeClSeminarBlank.visibility = View.GONE
                        // 리사이클러뷰 클릭 리스너
                        seminarRVAdapter.setOnItemClickListener(object :
                            HomeSeminarRVAdapter.OnItemClickListener {
                            override fun onClick(position: Int) {
                                if (it.result[position].isOpen == "OPEN") {
//                                    GaramgaebiApplication.sSharedPreferences
//                                        .edit().putInt("programIdx", it.result[position].programIdx)
//                                        .apply()
                                    CoroutineScope(Dispatchers.Default).launch {
                                        GaramgaebiApplication().saveIntToDataStore(
                                            "programIdx",
                                            it.result[position].programIdx
                                        )
                                        //세미나 메인 프래그먼트로!
                                        startActivity(
                                            Intent(requireContext(), ContainerActivity::class.java)
                                                .putExtra("openFragment", SEMINAR)
                                        )
                                    }
                                }
                            }
                        })
                        Log.d("homeComplete", "homeSeminarComplete")
                    }
                })
                // 네트워킹
                viewModel.networking.observe(viewLifecycleOwner, Observer {
                    val result = it.result as ArrayList<HomeNetworkingResult>
                    val networkingRVAdapter: HomeNetworkingRVAdapter
                    if (result == null || result.isEmpty()) {
                        binding.fragmentHomeVpNetworking.visibility = View.GONE
                        binding.fragmentHomeClNetworkingBlank.visibility = View.VISIBLE
                    } else {
                        networkingRVAdapter = HomeNetworkingRVAdapter(result)
                        binding.fragmentHomeVpNetworking.apply {
                            adapter = networkingRVAdapter
                        }
                        binding.fragmentHomeVpNetworking.visibility = View.VISIBLE
                        binding.fragmentHomeClNetworkingBlank.visibility = View.GONE
                        // 리사이클러뷰 클릭 리스너
                        networkingRVAdapter.setOnItemClickListener(object :
                            HomeNetworkingRVAdapter.OnItemClickListener {
                            override fun onClick(position: Int) {
                                val program = it.result[position].programIdx
//                                GaramgaebiApplication.sSharedPreferences
//                                    .edit().putInt("programIdx", program)
//                                    .apply()
                                CoroutineScope(Dispatchers.Default).launch {
                                    GaramgaebiApplication().saveIntToDataStore(
                                        "programIdx",
                                        it.result[position].programIdx
                                    )
                                    //네트워킹 메인 프래그먼트로!
                                    startActivity(
                                        Intent(context, ContainerActivity::class.java)
                                            .putExtra("openFragment", NETWORKING)
                                    )
                                }
                            }
                        })
                        Log.d("homeComplete", "homeNetworkingComplete")
                        (requireActivity() as MainActivity).dismissLoadingDialog()
                        Log.d("home", "dialog dismiss")
                    }
                })
                // 유저 프로필 11명
                viewModel.user.observe(viewLifecycleOwner, Observer {
                    val result = it.result as ArrayList<HomeUserResult>
                    val userRVAdapter: HomeUserItemRVAdapter
                    if (result == null || result.isEmpty()) {
                        binding.fragmentHomeRvUser.visibility = View.GONE
                        binding.fragmentHomeClUserBlank.visibility = View.VISIBLE
                    } else {
                        userRVAdapter = HomeUserItemRVAdapter(result, requireContext())
                        binding.fragmentHomeRvUser.apply {
                            adapter = userRVAdapter
                            layoutManager =
                                LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false).apply {
                                    initialPrefetchItemCount = 10
                                }
                        }
                        binding.fragmentHomeRvUser.visibility = View.VISIBLE
                        binding.fragmentHomeClUserBlank.visibility = View.GONE
                        // 리사이클러뷰 클릭 리스너
                        userRVAdapter.setOnItemClickListener(object :
                            HomeUserItemRVAdapter.OnItemClickListener {
                            override fun onClick(position: Int) {
                                CoroutineScope(Dispatchers.Default).launch {
                                    GaramgaebiApplication().saveIntToDataStore(
                                        "userMemberIdx",
                                        result[position].memberIdx
                                    )

//
//                                GaramgaebiApplication.sSharedPreferences.edit()
//                                    .putInt("userMemberIdx", result[position].memberIdx).apply()

                                    val target = Intent(context, ContainerActivity::class.java)
                                    target.putExtra("openFragment", USER_PROFILE)
                                    startActivity(target)
                                }
                            }
                        })
                        Log.d("homeComplete", "homeUserComplete")
                    }
                })
                // 내 모임
                viewModel.program.observe(viewLifecycleOwner, Observer {
                    val result = it.result as ArrayList<HomeProgramResult>
                    val myMeetingRVAdapter: HomeMyMeetingRVAdapter
                    if (result == null || result.isEmpty()) {
                        binding.fragmentHomeClMyMeetingsBlank.visibility = View.VISIBLE
                        binding.fragmentHomeRvMyMeeting.visibility = View.GONE

                    } else {
                        myMeetingRVAdapter = HomeMyMeetingRVAdapter(result)
                        binding.fragmentHomeRvMyMeeting.apply {
                            adapter = myMeetingRVAdapter
                            layoutManager =
                                LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
                        }
                        binding.fragmentHomeRvMyMeeting.visibility = View.VISIBLE
                        binding.fragmentHomeClMyMeetingsBlank.visibility = View.GONE
                        // 리사이클러뷰 클릭 리스너
                        myMeetingRVAdapter.setOnItemClickListener(object :
                            HomeMyMeetingRVAdapter.OnItemClickListener {
                            override fun onClick(position: Int) {
                                val program = it.result[position].programIdx
//                                val putData = runBlocking {
//                                    GaramgaebiApplication().saveIntToDataStore("programIdx",program)
//                                }
                                CoroutineScope(Dispatchers.Default).launch {
                                    GaramgaebiApplication().saveIntToDataStore(
                                        "programIdx",
                                        program
                                    )


//                                GaramgaebiApplication.sSharedPreferences
//                                    .edit().putInt("programIdx", program)
//                                    .apply()
                                    //세미나 메인 프래그먼트로!
                                    if (it.result[position].type == "SEMINAR") {
                                        val intent = Intent(context, ContainerActivity::class.java)
                                        intent.putExtra("openFragment", SEMINAR)
                                        startActivity(intent)
                                    }
                                    //네트워킹 메인 프래그먼트로
                                    if (it.result[position].type == "NETWORKING") {
                                        val intent = Intent(context, ContainerActivity::class.java)
                                        intent.putExtra("openFragment", NETWORKING)
                                        startActivity(intent)
                                    }
                                }
                            }
                        })
                        Log.d("homeComplete", "homeMyMeetingComplete")
                    }
                })
                // 읽지 않은 알림 존재 여부
                viewModel.notificationUnread.observe(viewLifecycleOwner, Observer {
                    if (it.result.isUnreadExist)
                        binding.fragmentHomeIvNotificationPoint.visibility = View.VISIBLE
                    else
                        binding.fragmentHomeIvNotificationPoint.visibility = View.GONE
                })
            }
        }
    }


    private fun updateData() {
        Log.d("network", "updateData")
        with(viewModel) {
            // 세미나
            getHomeSeminar()
            // 네트워킹
            getHomeNetworking()
            // 내 모임
            getHomeProgram(myMemberIdx)
            // 읽지 않은 알림 존재 여부
            getNotificationUnread(myMemberIdx)
            Log.d("home", "updateData")
        }
    }

    fun updateNotificationUnread() {
        Log.d("firebaseUpdateNotifiUnread", "firebaseUpdateNotifiUnread")
        lifecycleScope.launch {
            viewModel.getNotificationUnread(myMemberIdx)
        }
    }

    private fun goGathering() {
        Log.d("goGathering", "goGathering")
        with(parentFragmentManager) {
            beginTransaction().hide(parentFragmentManager.findFragmentByTag("home")!!)
                .hide(parentFragmentManager.findFragmentByTag("myProfile")!!)
                .show(parentFragmentManager.findFragmentByTag("gathering")!!)
                .commitAllowingStateLoss()
        }
    }

    private fun constraintsConnect(mainView: View, targetView: View) {
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
        binding.fragmentHomeIvSeminarHelp.visibility = View.GONE
        binding.fragmentHomeIvSeminarHelp.startAnimation(
            AlphaAnimation(1f, 0f).apply {
            duration = 500 // 애니메이션 지속 시간 (1초)
        })
    }

    fun goneNetworkingHelp() {
        binding.fragmentHomeIvNetworkingHelp.visibility = View.GONE
        binding.fragmentHomeIvNetworkingHelp.startAnimation(
            AlphaAnimation(1f, 0f).apply {
                duration = 500 // 애니메이션 지속 시간 (1초)
            })
    }
}

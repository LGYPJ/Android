package com.garamgaebi.garamgaebi.src.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.NotificationItemRVAdapter
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.databinding.FragmentNotificationBinding
import com.garamgaebi.garamgaebi.model.NotificationList
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.HomeViewModel
import kotlinx.coroutines.*

class NotificationFragment : BaseFragment<FragmentNotificationBinding>(
    FragmentNotificationBinding::bind,
    R.layout.fragment_notification
) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<HomeViewModel>()
        viewModel.getNotification(myMemberIdx)
        var notificationRVAdapter = NotificationItemRVAdapter(arrayListOf())
        //hasNext 저장 용도

        // 최초 리사이클러뷰
        viewModel.notification.observe(viewLifecycleOwner, Observer {
            val result = it.result.result as ArrayList<NotificationList>
            val putData = runBlocking {
                GaramgaebiApplication().saveBooleanToDataStore("hasNext",it.result.hasNext)
            }

            notificationRVAdapter.setList(result)
            binding.activityNotificationRv.apply {
                adapter = notificationRVAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            }
        })
        with(binding.swipeRefreshLayout) {
            setOnRefreshListener {
                notificationRVAdapter.refresh()
                viewModel.getNotification(myMemberIdx)
                isRefreshing = false
            }
        }
        // 최하단 스크롤 시 다음 알림 조회 API call
        binding.activityNotificationRv.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)
//                Log.d(
//                    "scrollHasNext",
//                    "${GaramgaebiApplication.sSharedPreferences.getBoolean("hasNext", false)}"
//                )
                var hasNext = false
                val putData = runBlocking {
                    hasNext = GaramgaebiApplication().loadBooleanData("hasNext") == true
                }
                if (hasNext) {
                    val rvPosition =
                        (rv.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    val totalCount = rv.adapter?.itemCount?.minus(1)
                    Log.d("scrollEndTotalCount", "${rv.adapter?.itemCount?.minus(1)}")
                    if (rvPosition == totalCount) {
                        viewModel.getNotificationScroll(
                            myMemberIdx,
                            (rv.adapter!! as NotificationItemRVAdapter).getLastItemId(totalCount - 1)
                        )
                        rv.smoothScrollToPosition(totalCount+2)
                    }
                }
            }
        })
        // 다음 알림 조회 API
        viewModel.notificationScroll.observe(viewLifecycleOwner, Observer {
            val putData = runBlocking {
                GaramgaebiApplication().saveBooleanToDataStore("hasNext",it.result.hasNext)
            }
            notificationRVAdapter.apply {
                setList(it.result.result as ArrayList<NotificationList>)
                Log.d(
                    "scrollEndGetNext",
                    "${notificationRVAdapter.itemCount - it.result.result.size} ${it.result.result.size}"
                )
                notifyItemRangeInserted(
                    notificationRVAdapter.itemCount - it.result.result.size,
                    it.result.result.size
                )
            }
        })

        notificationRVAdapter.setOnItemClickListener(object :
            NotificationItemRVAdapter.OnItemClickListener {
            override fun onClick(dataList: ArrayList<NotificationList>, position: Int) {
                dataList[position].isRead = true
                val program = dataList[position].programIdx
//                GaramgaebiApplication.sSharedPreferences
//                    .edit().putInt("programIdx", program)
//                    .apply()
                val putData = runBlocking {
                    GaramgaebiApplication().saveIntToDataStore("programIdx",program)
                }
                //세미나 메인 프래그먼트로!
                if (dataList[position].resourceType == "SEMINAR") {
                    (requireActivity() as ContainerActivity).openFragmentOnFrameLayout(1)
                    (requireActivity() as ContainerActivity).isSeminar()

                }
                //네트워킹 메인 프래그먼트로
                if (dataList[position].resourceType == "NETWORKING") {
                    (requireActivity() as ContainerActivity).openFragmentOnFrameLayout(5)
                    (requireActivity() as ContainerActivity).isNetworking()
                }
            }
        })
    }
}
package com.garamgaebi.garamgaebi.garamgaebi.src.main.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.garamgaebi.adapter.NotificationItemRVAdapter
import com.garamgaebi.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.FragmentNotificationBinding
import com.garamgaebi.garamgaebi.garamgaebi.model.NotificationList
import com.garamgaebi.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.garamgaebi.viewModel.HomeViewModel
import kotlinx.coroutines.*

class NotificationFragment : BaseFragment<FragmentNotificationBinding>(FragmentNotificationBinding::bind,R.layout.fragment_notification) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<HomeViewModel>()
        viewModel.getNotification(22)
        var notificationRVAdapter = NotificationItemRVAdapter(arrayListOf())
        //hasNext 저장 용도
        val editor = GaramgaebiApplication.sSharedPreferences.edit()

        // 최초 리사이클러뷰
        viewModel.notification.observe(viewLifecycleOwner, Observer {
            val result = it.result.result as ArrayList<NotificationList>
            editor.putBoolean("hasNext", it.result.hasNext).apply()
            notificationRVAdapter.setList(result)
            if(!GaramgaebiApplication.sSharedPreferences.getBoolean("hasNext", false))
                notificationRVAdapter.deleteLoading()
            binding.activityNotificationRv.apply {
                adapter = notificationRVAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            }
        })
        // 최하단 스크롤 시 다음 알림 조회 API call
        binding.activityNotificationRv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrolled(rv: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(rv, dx, dy)
                Log.d("scrollHasNext", "${GaramgaebiApplication.sSharedPreferences.getBoolean("hasNext",false)}")
                if(GaramgaebiApplication.sSharedPreferences.getBoolean("hasNext",false)){
                    val rvPosition =
                        (rv.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    val totalCount = rv.adapter?.itemCount?.minus(1)
                    Log.d("scrollEndTotalCount", "${rv.adapter?.itemCount?.minus(1)}")
                    if(rvPosition == totalCount) {
                        (rv.adapter!! as NotificationItemRVAdapter).deleteLoading()
                        CoroutineScope(Dispatchers.Default).launch {
                            launch {
                                delay(1500)
                                Log.d("scrollEndLastId", "scrollEnd " +
                                        "${(rv.adapter!! as NotificationItemRVAdapter).getLastItemId(totalCount-1)}")
                                viewModel.getNotificationScroll(22,
                                    (rv.adapter!! as NotificationItemRVAdapter).getLastItemId(totalCount-1))
                            }
                        }
                    }
                }
            }
        })
        // 다음 알림 조회 API
        viewModel.notificationScroll.observe(viewLifecycleOwner, Observer{
            editor.putBoolean("hasNext", it.result.hasNext).apply()
            notificationRVAdapter.apply{
                setList(it.result.result as ArrayList<NotificationList>)
                Log.d("scrollEndGetNext", "${notificationRVAdapter.itemCount-it.result.result.size} ${it.result.result.size}")
                notifyItemRangeInserted(notificationRVAdapter.itemCount-it.result.result.size, it.result.result.size)
                if(!GaramgaebiApplication.sSharedPreferences.getBoolean("hasNext", false))
                    deleteLoading()
        }
        })

        notificationRVAdapter.setOnItemClickListener(object : NotificationItemRVAdapter.OnItemClickListener{
            override fun onClick(dataList: ArrayList<NotificationList>, position: Int) {
                dataList[position].isRead = true
                val program = dataList[position].programIdx
                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("programIdx", program)
                    .apply()
                //세미나 메인 프래그먼트로!
                if(dataList[position].resourceType == "SEMINAR"){
                    val intent = Intent(context, ContainerActivity::class.java)
                    intent.putExtra("seminar", true)
                    startActivity(intent)
                }
                //네트워킹 메인 프래그먼트로
                if(dataList[position].resourceType == "NETWORKING"){
                    val intent = Intent(context, ContainerActivity::class.java)
                    intent.putExtra("networking", true)
                    startActivity(intent)
                }
            }
        })
    }
}
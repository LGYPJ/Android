package com.example.template.garamgaebi.src.main.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.adapter.NotificationItemRVAdapter
import com.example.template.garamgaebi.common.BaseFragment
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.databinding.FragmentNotificationBinding
import com.example.template.garamgaebi.model.NotificationList
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.viewModel.HomeViewModel

class NotificationFragment : BaseFragment<FragmentNotificationBinding>(FragmentNotificationBinding::bind,R.layout.fragment_notification) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel by viewModels<HomeViewModel>()
        viewModel.getNotification(22)


        viewModel.notification.observe(viewLifecycleOwner, Observer {
            val result = it.result.result as ArrayList<NotificationList>
            val notificationRVAdapter = NotificationItemRVAdapter(result)
            binding.activityNotificationRv.apply {
                adapter = notificationRVAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            }
            binding.activityNotificationRv.addOnScrollListener(object : RecyclerView.OnScrollListener(){
                override fun onScrollStateChanged(rv: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(rv, newState)
                    val lastVisibleItemPosition =
                        (rv.layoutManager as LinearLayoutManager).findLastCompletelyVisibleItemPosition()
                    val totalItemViewCount = rv.adapter!!.itemCount-1
                    if(newState == 2 && !rv.canScrollVertically(1)
                        &&lastVisibleItemPosition == totalItemViewCount-1){
                        var lastItemId =
                            (binding.activityNotificationRv.adapter as NotificationItemRVAdapter)
                                .getLastItemId(totalItemViewCount)
                        viewModel.getNotification(22, lastItemId)
                    }
                }
            })

            notificationRVAdapter.setOnItemClickListener(object : NotificationItemRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    it.result.result[position].isRead = true
                    val program = it.result.result[position].programIdx
                    GaramgaebiApplication.sSharedPreferences
                        .edit().putInt("programIdx", program)
                        .apply()

                    //세미나 메인 프래그먼트로!
                    if(it.result.result[position].resourceType == "SEMINAR"){
                        val intent = Intent(context, ContainerActivity::class.java)
                        intent.putExtra("seminar", true)
                        startActivity(intent)
                    }
                    //네트워킹 메인 프래그먼트로
                    if(it.result.result[position].resourceType == "NETWORKING"){
                        val intent = Intent(context, ContainerActivity::class.java)
                        intent.putExtra("networking", true)
                        startActivity(intent)
                    }
                }
            })
        })

    }
}
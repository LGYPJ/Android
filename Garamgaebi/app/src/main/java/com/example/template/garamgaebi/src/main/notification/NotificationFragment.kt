package com.example.template.garamgaebi.src.main.notification

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.adapter.NotificationItemRVAdapter
import com.example.template.garamgaebi.common.BaseBindingFragment
import com.example.template.garamgaebi.databinding.FragmentNotificationBinding
import com.example.template.garamgaebi.model.NotificationResult
import com.example.template.garamgaebi.viewModel.NotificationViewModel

class NotificationFragment : BaseBindingFragment<FragmentNotificationBinding>(R.layout.fragment_notification) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = ViewModelProvider(this)[NotificationViewModel::class.java]
        viewModel.getNotification(1)

        viewModel.notification.observe(viewLifecycleOwner, Observer {
            val result = it.result as ArrayList<NotificationResult>
            val notificationRVAdapter = NotificationItemRVAdapter(result)
            binding.activityNotificationRv.apply {
                adapter = notificationRVAdapter
                layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            }
            notificationRVAdapter.setOnItemClickListener(object : NotificationItemRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    //TODO
                }

            })
        })

    }
}
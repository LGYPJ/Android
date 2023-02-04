package com.example.template.garamgaebi.src.main.notification

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.config.BaseActivity
import com.example.template.garamgaebi.databinding.ActivityNotificationBinding

class NotificationActivity : BaseActivity<ActivityNotificationBinding>(ActivityNotificationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val itemDataList : ArrayList<NotificationItemData> = arrayListOf()
        for( i : Int in 1..3) {
            itemDataList.add(NotificationItemData(R.drawable.ic_item_activity_notification_gathering, "모아보기","새로운 네트워킹이 오픈되었어요"))
            itemDataList.add(NotificationItemData(R.drawable.ic_item_activity_notification_complete, "신청완료","2차 네트워킹 신청이 완료되었어요"))
            itemDataList.add(NotificationItemData(R.drawable.ic_item_activity_notification_deadline, "마감임박","2차 세미나 마감이 임박했어요"))
        }

        val notificationRVAdapter = NotificationItemRVAdapter(itemDataList)
        binding.activityNotificationRv.adapter = notificationRVAdapter
        binding.activityNotificationRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.activityNotificationBtnBack.setOnClickListener {
            onBackPressed()
        }
    }
}
package com.example.template.garamgaebi.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.PROGRESSBAR
import com.example.template.garamgaebi.common.RV_ITEM
import com.example.template.garamgaebi.databinding.ItemNotificationBinding
import com.example.template.garamgaebi.databinding.ItemNotificationProgressBarBinding
import com.example.template.garamgaebi.model.NotificationList

class NotificationItemRVAdapter(private val dataList: ArrayList<NotificationList>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class NotificationViewHolder( val binding: ItemNotificationBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NotificationList){
            with(binding) {
                itemFragmentNotificationIv.setImageResource(setImage(data.notificationType))
                itemFragmentNotificationTvTitle.text = setTitle(data.notificationType)
                itemFragmentNotificationTvDesc.text = data.content
                if(data.isRead) {
                    root.setBackgroundColor(root.context.getColor(R.color.grayF9))
                } else {
                    root.setBackgroundColor(root.context.getColor(R.color.white))
                }
            }

        }
    }
    inner class ProgressBarViewHolder(val binding: ItemNotificationProgressBarBinding)
        : RecyclerView.ViewHolder(binding.root) {
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            RV_ITEM -> {
                val binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                NotificationViewHolder(binding)
            }
            else -> {
                val binding = ItemNotificationProgressBarBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ProgressBarViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is NotificationViewHolder) {
                holder.bind(dataList[position])
                holder.binding.root.setOnClickListener {
                    itemClickListener.onClick(dataList ,position)
                }
        } else {}
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickListener {
        fun onClick(dataList: ArrayList<NotificationList>, position: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }
    private fun setImage(type : String) : Int{
        return when(type) {
            "COLLECTIONS" -> R.drawable.ic_item_fragment_notification_gathering
            "SOON_CLOSE" -> R.drawable.ic_item_fragment_notification_deadline
            "APPLY_COMPLETE" -> R.drawable.ic_item_fragment_notification_complete
            "APPLY_CANCEL_COMPLETE"  -> R.drawable.ic_item_fragment_notification_complete
            else -> R.drawable.ic_item_fragment_notification_gathering
        }
    }
    private fun setTitle(type : String) : String {
        return when(type) {
            "COLLECTIONS" -> "모아보기"
            "SOON_CLOSE" -> "마감임박"
            "APPLY_COMPLETE" -> "신청완료"
            "APPLY_CANCEL_COMPLETE" -> "신청취소완료"
            else -> "모아보기"
        }
    }

    fun getLastItemId(count : Int) : Int{
        return dataList[count].notificationIdx
    }
    override fun getItemViewType(position: Int): Int {
        // 알림과 프로그레스바 아이템뷰를 구분
        return when (dataList[position].notificationIdx) {
            -2 -> PROGRESSBAR
            else -> RV_ITEM
        }
    }
    fun setList(list: ArrayList<NotificationList>) {
        dataList.addAll(list)
        dataList.add(NotificationList("",false,-2,"",-1,""))
    }
    fun deleteLoading(){
        Log.d("scrollEndDeleteIndex", "${dataList.lastIndex}")
        dataList.removeAt(dataList.lastIndex) // 로딩이 완료되면 프로그레스바를 지움
    }
}
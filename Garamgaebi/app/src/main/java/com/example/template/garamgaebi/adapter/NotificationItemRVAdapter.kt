package com.example.template.garamgaebi.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.databinding.ItemNotificationBinding
import com.example.template.garamgaebi.model.NotificationList

class NotificationItemRVAdapter(private val dataList: ArrayList<NotificationList>) : RecyclerView.Adapter<NotificationItemRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class ViewHolder( val binding: ItemNotificationBinding):
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.binding.root.setOnClickListener {
            itemClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickListener {
        fun onClick(position: Int)
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
}
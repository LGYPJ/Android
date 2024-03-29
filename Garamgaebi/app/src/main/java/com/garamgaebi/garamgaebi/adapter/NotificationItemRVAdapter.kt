package com.garamgaebi.garamgaebi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.PROGRESSBAR
import com.garamgaebi.garamgaebi.common.RV_ITEM
import com.garamgaebi.garamgaebi.databinding.ItemNotificationBinding
import com.garamgaebi.garamgaebi.databinding.ItemNotificationProgressBarBinding
import com.garamgaebi.garamgaebi.model.NotificationList

class NotificationItemRVAdapter(private val dataList: ArrayList<NotificationList>) : RecyclerView.Adapter<NotificationItemRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class ViewHolder(val binding: ItemNotificationBinding):
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
        return ViewHolder(ItemNotificationBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.binding.root.setOnClickListener {
            itemClickListener.onClick(dataList ,position)
        }
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
            "APPLY_CANCEL_COMPLETE" -> R.drawable.ic_item_fragment_notification_cancel_complete
            "NON_DEPOSIT_CANCEL" -> R.drawable.ic_item_fragment_notification_cancel_complete
            "REFUND_COMPLETE" -> R.drawable.ic_item_fragment_notification_refund_complete
            "APPLY_COMFIRM" -> R.drawable.ic_item_fragment_notification_complete
            else -> R.drawable.ic_item_fragment_notification_complete
        }
    }
    private fun setTitle(type : String) : String {
        return when(type) {
            "COLLECTIONS" -> "모아보기"
            "SOON_CLOSE" -> "마감임박"
            "APPLY_COMPLETE" -> "신청완료"
            "APPLY_CANCEL_COMPLETE" -> "신청취소완료"
            "NON_DEPOSIT_CANCEL" -> "미입금취소"
            "REFUND_COMPLETE" -> "환불완료"
            "APPLY_COMFIRM" -> "신청확정"
            else -> "모아보기"
        }
    }

    fun getLastItemId(count : Int) : Int{
        return dataList[count].notificationIdx
    }
    fun setList(list: ArrayList<NotificationList>) {
        dataList.addAll(list)
    }
    fun refresh() {
        dataList.clear()
    }
}
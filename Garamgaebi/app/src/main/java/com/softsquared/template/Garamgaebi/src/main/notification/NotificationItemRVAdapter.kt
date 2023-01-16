package com.softsquared.template.Garamgaebi.src.main.notification

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemNotificationBinding

class NotificationItemRVAdapter(private val dataList: ArrayList<NotificationItemData>) : RecyclerView.Adapter<NotificationItemRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: NotificationItemRVAdapter.OnItemClickListener
    inner class ViewHolder( val binding: ItemNotificationBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NotificationItemData){
            binding.itemActivityNotificationIv.setImageResource(data.img)
            binding.itemActivityNotificationTvTitle.text = data.title
            binding.itemActivityNotificationTvDesc.text = data.desc
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

}
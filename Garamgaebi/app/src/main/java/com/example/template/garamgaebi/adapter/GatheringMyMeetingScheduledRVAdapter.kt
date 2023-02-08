package com.example.template.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.databinding.ItemGatheringMyMeetingScheduledBinding
import com.example.template.garamgaebi.model.GatheringProgramResult

class GatheringMyMeetingScheduledRVAdapter(private val dataList: ArrayList<GatheringProgramResult>): RecyclerView.Adapter<GatheringMyMeetingScheduledRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class ViewHolder( val binding: ItemGatheringMyMeetingScheduledBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GatheringProgramResult){
            binding.itemGatheringMyMeetingScheduledTvName.text = data.title
            binding.itemGatheringMyMeetingScheduledTvDate.text = data.date
            binding.itemGatheringMyMeetingScheduledTvPlace.text = data.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGatheringMyMeetingScheduledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.binding.itemGatheringMyMeetingScheduledIvMore.setOnClickListener {
            itemClickListener.onMoreClick(position, v = holder.binding.itemGatheringMyMeetingScheduledIvMore)
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickListener {
        fun onMoreClick(position: Int, v : View)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

}
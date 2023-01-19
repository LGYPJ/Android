package com.softsquared.template.Garamgaebi.src.main.gathering

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemGatheringMyMeetingLastBinding

class GatheringMyMeetingLastRVAdapter(private val dataList: ArrayList<GatheringMyMeetingItemData>): RecyclerView.Adapter<GatheringMyMeetingLastRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: GatheringMyMeetingLastRVAdapter.OnItemClickListener
    inner class ViewHolder( val binding: ItemGatheringMyMeetingLastBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GatheringMyMeetingItemData){
            binding.itemGatheringMyMeetingLastTvName.text = data.name
            binding.itemGatheringMyMeetingLastTvDate.text = data.date
            binding.itemGatheringMyMeetingLastTvPlace.text = data.place
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGatheringMyMeetingLastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
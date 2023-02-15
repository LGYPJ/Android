package com.example.template.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.databinding.ItemGatheringMyMeetingLastBinding
import com.garamgaebi.garamgaebi.model.GatheringProgramResult

class GatheringMyMeetingLastRVAdapter(private val dataList: ArrayList<GatheringProgramResult>): RecyclerView.Adapter<GatheringMyMeetingLastRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class ViewHolder( val binding: ItemGatheringMyMeetingLastBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GatheringProgramResult){
            binding.itemGatheringMyMeetingLastTvName.text = data.title
            binding.itemGatheringMyMeetingLastTvDate.text = GaramgaebiFunction().getDateMyMeeting(data.date)
            binding.itemGatheringMyMeetingLastTvPlace.text = data.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGatheringMyMeetingLastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.binding.itemGatheringMyMeetingLastIvMore.setOnClickListener {
            if(dataList[position].isOpen == "OPEN"){
                itemClickListener.onMoreClick(position, v = holder.binding.itemGatheringMyMeetingLastIvMore)
            }
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
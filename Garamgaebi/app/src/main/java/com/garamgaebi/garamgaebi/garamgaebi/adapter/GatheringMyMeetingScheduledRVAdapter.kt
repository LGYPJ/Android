package com.garamgaebi.garamgaebi.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.databinding.ItemGatheringMyMeetingScheduledBinding
import com.garamgaebi.garamgaebi.garamgaebi.model.GatheringProgramResult

class GatheringMyMeetingScheduledRVAdapter(
    private var dataList: ArrayList<GatheringProgramResult>) : ListAdapter<GatheringProgramResult, GatheringMyMeetingScheduledRVAdapter.ViewHolder>(diffUtil){
    private lateinit var itemClickListener: OnItemClickListener
    inner class ViewHolder( val binding: ItemGatheringMyMeetingScheduledBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GatheringProgramResult){
            binding.itemGatheringMyMeetingScheduledTvName.text = data.title
            binding.itemGatheringMyMeetingScheduledTvDate.text = GaramgaebiFunction().getDateMyMeeting(data.date)
            binding.itemGatheringMyMeetingScheduledTvPlace.text = data.location
        }
    }
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<GatheringProgramResult>() {

            // 두 아이템이 동일한 아이템인지 체크. 보통 고유한 id를 기준으로 비교
            override fun areItemsTheSame(oldItem: GatheringProgramResult, newItem: GatheringProgramResult): Boolean {
                return oldItem.programIdx == newItem.programIdx
            }

            // 두 아이템이 동일한 내용을 가지고 있는지 체크. areItemsTheSame()이 true일때 호출됨
            override fun areContentsTheSame(oldItem: GatheringProgramResult, newItem: GatheringProgramResult): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }


        }
    }

    override fun submitList(list: List<GatheringProgramResult>?) {
        super.submitList(list)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGatheringMyMeetingScheduledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.binding.itemGatheringMyMeetingScheduledIvMore.setOnClickListener {
            if(dataList[position].isOpen == "OPEN"){
                itemClickListener.onMoreClick(position, v = holder.binding.itemGatheringMyMeetingScheduledIvMore)
            }
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickListener {
        fun onMoreClick(position: Int, v: View)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }


}
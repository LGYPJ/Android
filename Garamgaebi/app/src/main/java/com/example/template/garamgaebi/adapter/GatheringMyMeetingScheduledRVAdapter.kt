package com.example.template.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.databinding.ItemGatheringMyMeetingScheduledBinding
import com.example.template.garamgaebi.model.GatheringProgramResult
import com.example.template.garamgaebi.viewModel.GatheringViewModel

class GatheringMyMeetingScheduledRVAdapter(
    viewLifecycleOwner: LifecycleOwner,
    private var dataList: ArrayList<GatheringProgramResult>, private val gatheringViewModel: GatheringViewModel
) : ListAdapter<GatheringProgramResult, GatheringMyMeetingScheduledRVAdapter.ViewHolder>(diffUtil){
    private lateinit var itemClickListener: OnItemClickListener
    inner class ViewHolder( val binding: ItemGatheringMyMeetingScheduledBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GatheringProgramResult, gatheringViewModel: GatheringViewModel){
            binding.itemGatheringMyMeetingScheduledTvName.text = data.title
            binding.itemGatheringMyMeetingScheduledTvDate.text = data.date
            binding.itemGatheringMyMeetingScheduledTvPlace.text = data.location

            //삭제
            gatheringViewModel.addGetGatheringProgramReady()
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
        holder.bind(dataList[position], gatheringViewModel)
        //val temp = differ.currentList[position]
        //holder.bind(currentList[position])
        holder.binding.itemGatheringMyMeetingScheduledIvMore.setOnClickListener {
            itemClickListener.onMoreClick(position, v = holder.binding.itemGatheringMyMeetingScheduledIvMore)
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickListener {
        fun onMoreClick(position: Int, v: View)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

    fun setData(memo : List<GatheringProgramResult>){
        dataList = memo as ArrayList<GatheringProgramResult>
        notifyDataSetChanged()
    }

    // 아이템에 아이디를 설정해줌 (깜빡이는 현상방지)
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }



}
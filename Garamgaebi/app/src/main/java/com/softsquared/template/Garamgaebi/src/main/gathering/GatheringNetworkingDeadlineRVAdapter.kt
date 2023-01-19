package com.softsquared.template.Garamgaebi.src.main.gathering

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemGatheringSeminarDeadlineBinding

class GatheringNetworkingDeadlineRVAdapter(private val dataList: ArrayList<GatheringNetworkingItemData>): RecyclerView.Adapter<GatheringNetworkingDeadlineRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: GatheringNetworkingDeadlineRVAdapter.OnItemClickListener
    inner class ViewHolder( val binding: ItemGatheringSeminarDeadlineBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GatheringNetworkingItemData){
            binding.itemGatheringSeminarScheduledTvName.text = data.name
            binding.itemGatheringSeminarScheduledTvDateData.text = data.date
            binding.itemGatheringSeminarScheduledTvPlaceData.text = data.place
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGatheringSeminarDeadlineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
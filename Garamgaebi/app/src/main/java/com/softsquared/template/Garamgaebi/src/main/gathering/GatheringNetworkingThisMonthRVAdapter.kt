package com.softsquared.template.Garamgaebi.src.main.gathering

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemGatheringNetworkingThisMonthBinding

class GatheringNetworkingThisMonthRVAdapter(private val dataList: ArrayList<GatheringNetworkingItemData>): RecyclerView.Adapter<GatheringNetworkingThisMonthRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: GatheringNetworkingThisMonthRVAdapter.OnItemClickListener
    inner class ViewHolder( val binding: ItemGatheringNetworkingThisMonthBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GatheringNetworkingItemData){
            binding.itemGatheringNetworkingThisMonthTvName.text = data.name
            binding.itemGatheringNetworkingThisMonthTvDateData.text = data.date
            binding.itemGatheringNetworkingThisMonthTvPlaceData.text = data.place
            if(data.dDay == 0) {
                binding.itemGatheringNetworkingThisMonthTvDDay.text = "D-day"
            } else {
                binding.itemGatheringNetworkingThisMonthTvDDay.text = "D-"+data.dDay
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGatheringNetworkingThisMonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
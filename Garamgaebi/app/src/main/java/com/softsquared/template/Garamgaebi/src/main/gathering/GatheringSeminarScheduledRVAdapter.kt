package com.softsquared.template.Garamgaebi.src.main.gathering

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemGatheringNetworkingScheduledBinding
import com.softsquared.template.Garamgaebi.databinding.ItemGatheringNetworkingThisMonthBinding
import com.softsquared.template.Garamgaebi.databinding.ItemGatheringSeminarScheduledBinding

class GatheringSeminarScheduledRVAdapter(private val dataList: ArrayList<GatheringSeminarItemData>): RecyclerView.Adapter<GatheringSeminarScheduledRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: GatheringSeminarScheduledRVAdapter.OnItemClickListener
    inner class ViewHolder( val binding: ItemGatheringSeminarScheduledBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GatheringSeminarItemData){
            binding.itemGatheringSeminarScheduledTvName.text = data.name
            binding.itemGatheringSeminarScheduledTvDateData.text = data.date
            binding.itemGatheringSeminarScheduledTvPlaceData.text = data.place
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGatheringSeminarScheduledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
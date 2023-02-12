package com.example.template.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.common.GaramgaebiFunction
import com.example.template.garamgaebi.databinding.ItemGatheringNetworkingDeadlineBinding
import com.example.template.garamgaebi.model.GatheringNetworkingClosedResult

class GatheringNetworkingDeadlineRVAdapter(private val dataList: ArrayList<GatheringNetworkingClosedResult>): RecyclerView.Adapter<GatheringNetworkingDeadlineRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class ViewHolder( val binding: ItemGatheringNetworkingDeadlineBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GatheringNetworkingClosedResult){
            binding.itemGatheringNetworkingDeadlineTvName.text = data.title
            binding.itemGatheringNetworkingDeadlineTvDateData.text = GaramgaebiFunction().getDateYMD(data.date)
            binding.itemGatheringNetworkingDeadlineTvPlaceData.text = data.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGatheringNetworkingDeadlineBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        if(dataList[position].isOpen == "OPEN"){
            holder.binding.root.setOnClickListener {
                itemClickListener.onClick(position)
            }
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
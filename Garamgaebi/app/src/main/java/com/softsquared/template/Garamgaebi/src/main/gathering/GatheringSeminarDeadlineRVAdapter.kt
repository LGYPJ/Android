package com.softsquared.template.Garamgaebi.src.main.gathering

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemGatheringSeminarDeadlineBinding
import com.softsquared.template.Garamgaebi.model.GatheringSeminarClosedResult
import com.softsquared.template.Garamgaebi.model.GatheringSeminarResult

class GatheringSeminarDeadlineRVAdapter(private val dataList: ArrayList<GatheringSeminarClosedResult>): RecyclerView.Adapter<GatheringSeminarDeadlineRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: GatheringSeminarDeadlineRVAdapter.OnItemClickListener
    inner class ViewHolder( val binding: ItemGatheringSeminarDeadlineBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: GatheringSeminarClosedResult){
            binding.itemGatheringSeminarDeadlineTvName.text = data.title
            binding.itemGatheringSeminarDeadlineTvDateData.text = data.date
            binding.itemGatheringSeminarDeadlineTvPlaceData.text = data.location
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

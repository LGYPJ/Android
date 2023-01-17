package com.softsquared.template.Garamgaebi.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemHomeSeminarScheduledBinding
import com.softsquared.template.Garamgaebi.databinding.ItemHomeSeminarThismonthBinding

class HomeSeminarRVAdapter (private val dataList: ArrayList<HomeSeminarItemData>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener:  OnItemClickListener
    inner class ThisMonthViewHolder(val binding: ItemHomeSeminarThismonthBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSeminarItemData) {
            binding.itemHomeSeminarTvName.text = data.name
            binding.itemHomeSeminarTvDateData.text = data.date
            binding.itemHomeSeminarTvPlaceData.text = data.place
            if(data.dDay == 0) {
                binding.itemHomeSeminarTvDDay.text = "D-day"
            } else {
                binding.itemHomeSeminarTvDDay.text = "D-"+data.dDay
            }
        }
    }
    inner class ScheduledViewHolder(val binding: ItemHomeSeminarScheduledBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSeminarItemData) {
            binding.itemHomeSeminarTvName.text = data.name
            binding.itemHomeSeminarTvDateData.text = data.date
            binding.itemHomeSeminarTvPlaceData.text = data.place
            if(data.dDay == 0) {
                binding.itemHomeSeminarTvDDay.text = "D-day"
            } else {
                binding.itemHomeSeminarTvDDay.text = "D-"+data.dDay
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            1 -> {
                val binding = ItemHomeSeminarThismonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ThisMonthViewHolder(binding)
            }
            2 -> {
                val binding = ItemHomeSeminarScheduledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ScheduledViewHolder(binding)
            }
            else -> {
                val binding = ItemHomeSeminarThismonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ThisMonthViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ThisMonthViewHolder) {
            holder.bind(dataList[position])
            holder.binding.root.setOnClickListener {
                itemClickListener.onClick(position)
            }
        } else if(holder is ScheduledViewHolder) {
            holder.bind(dataList[position])
            holder.binding.root.setOnClickListener {
                itemClickListener.onClick(position)
            }
        }
    }
    override fun getItemCount(): Int = dataList.size
    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }
    interface OnItemClickListener {
        fun onClick(position: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }
}
package com.softsquared.template.Garamgaebi.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.databinding.ItemHomeNetworkingClosedBinding
import com.softsquared.template.Garamgaebi.databinding.ItemHomeNetworkingScheduledBinding
import com.softsquared.template.Garamgaebi.databinding.ItemHomeNetworkingThismonthBinding
import com.softsquared.template.Garamgaebi.model.HomeNetworkingResult

class HomeNetworkingRVAdapter (private val dataList: ArrayList<HomeNetworkingResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: HomeNetworkingRVAdapter.OnItemClickListener
    inner class ThisMonthViewHolder(val binding: ItemHomeNetworkingThismonthBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeNetworkingResult) {
            if(data.payment == "PREMIUM"){
                binding.icPay.setImageResource(R.drawable.ic_item_home_charged)
            } else {
                binding.icPay.setImageResource(R.drawable.ic_item_home_for_free)
            }
            binding.itemHomeNetworkingTvName.text = data.title
            binding.itemHomeNetworkingTvDateData.text = data.date
            binding.itemHomeNetworkingTvPlaceData.text = data.location

            //TODO 날짜에 따라 D-day 바뀌게
            binding.itemHomeNetworkingTvDDay.text = "D-day"
        }
    }
    inner class ScheduledViewHolder(val binding: ItemHomeNetworkingScheduledBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeNetworkingResult) {
            if(data.payment == "PREMIUM"){
                binding.icPay.setImageResource(R.drawable.ic_item_home_charged)
            } else {
                binding.icPay.setImageResource(R.drawable.ic_item_home_for_free)
            }
            binding.itemHomeNetworkingTvName.text = data.title
            binding.itemHomeNetworkingTvDateData.text = data.date
            binding.itemHomeNetworkingTvPlaceData.text = data.location

            //TODO 날짜에 따라 D-day 바뀌게
            binding.itemHomeNetworkingTvDDay.text = "D-day"
        }
    }
    inner class ClosedViewHolder(val binding: ItemHomeNetworkingClosedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeNetworkingResult) {
            if(data.payment == "PREMIUM"){
                binding.icPay.setImageResource(R.drawable.ic_item_home_charged)
            } else {
                binding.icPay.setImageResource(R.drawable.ic_item_home_for_free)
            }
            binding.itemHomeNetworkingTvName.text = data.title
            binding.itemHomeNetworkingTvDateData.text = data.date
            binding.itemHomeNetworkingTvPlaceData.text = data.location
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            1 -> {
                val binding = ItemHomeNetworkingThismonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ThisMonthViewHolder(binding)
            }
            2 -> {
                val binding = ItemHomeNetworkingScheduledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ScheduledViewHolder(binding)
            }
            3-> {
                val binding = ItemHomeNetworkingClosedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                ClosedViewHolder(binding)
            }
            else -> {
                val binding = ItemHomeNetworkingThismonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ThisMonthViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is ThisMonthViewHolder -> {
                holder.bind(dataList[position])
                holder.binding.root.setOnClickListener {
                    itemClickListener.onClick(position)
                }
            }
            is ScheduledViewHolder -> {
                holder.bind(dataList[position])
                holder.binding.root.setOnClickListener {
                    itemClickListener.onClick(position)
                }
            }
            is ClosedViewHolder -> {
                holder.bind(dataList[position])
                holder.binding.root.setOnClickListener {
                    itemClickListener.onClick(position)
                }
            }
        }
    }
    override fun getItemCount(): Int = dataList.size
        override fun getItemViewType(position: Int): Int {
            return when(dataList[position].status) {
                "THIS_MONTH" -> 1
                "READY" -> 2
                "CLOSED" -> 3
                else -> 1
            }
        }
    interface OnItemClickListener {
        fun onClick(position: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }
}
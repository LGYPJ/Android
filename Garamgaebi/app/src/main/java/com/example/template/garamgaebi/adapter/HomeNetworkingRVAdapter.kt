package com.example.template.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.databinding.ItemHomeNetworkingClosedBinding
import com.example.template.garamgaebi.databinding.ItemHomeNetworkingScheduledBinding
import com.example.template.garamgaebi.databinding.ItemHomeNetworkingThismonthBinding
import com.example.template.garamgaebi.model.HomeNetworkingResult

class HomeNetworkingRVAdapter (private val dataList: ArrayList<HomeNetworkingResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class ThisMonthViewHolder(val binding: ItemHomeNetworkingThismonthBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeNetworkingResult) {
            if(data.payment == "PREMIUM")
                binding.icPay.setImageResource(R.drawable.ic_item_home_charged)
            else
                binding.icPay.setImageResource(R.drawable.ic_item_home_for_free)
            with(binding) {
                itemHomeNetworkingTvName.text = data.title
                itemHomeNetworkingTvDateData.text = data.date
                itemHomeNetworkingTvPlaceData.text = data.location
                itemHomeNetworkingTvDDay.text = "D-day"
            }
        }
    }
    inner class ScheduledViewHolder(val binding: ItemHomeNetworkingScheduledBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeNetworkingResult) {
            if(data.payment == "PREMIUM")
                binding.icPay.setImageResource(R.drawable.ic_item_home_charged)
            else
                binding.icPay.setImageResource(R.drawable.ic_item_home_for_free)
            with(binding) {
                itemHomeNetworkingTvName.text = data.title
                itemHomeNetworkingTvDateData.text = data.date
                itemHomeNetworkingTvPlaceData.text = data.location
                itemHomeNetworkingTvDDay.text = "D-day"
            }
        }
    }
    inner class ClosedViewHolder(val binding: ItemHomeNetworkingClosedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeNetworkingResult) {
            if(data.payment == "PREMIUM")
                binding.icPay.setImageResource(R.drawable.ic_item_home_charged)
            else
                binding.icPay.setImageResource(R.drawable.ic_item_home_for_free)
            with(binding) {
                itemHomeNetworkingTvName.text = data.title
                itemHomeNetworkingTvDateData.text = data.date
                itemHomeNetworkingTvPlaceData.text = data.location
            }
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
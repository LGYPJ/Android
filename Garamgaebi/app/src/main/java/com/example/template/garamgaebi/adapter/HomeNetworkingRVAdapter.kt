package com.example.template.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.CLOSED
import com.example.template.garamgaebi.common.GaramgaebiFunction
import com.example.template.garamgaebi.common.READY
import com.example.template.garamgaebi.common.THIS_MONTH
import com.example.template.garamgaebi.databinding.ItemHomeNetworkingClosedBinding
import com.example.template.garamgaebi.databinding.ItemHomeNetworkingScheduledBinding
import com.example.template.garamgaebi.databinding.ItemHomeNetworkingThismonthBinding
import com.example.template.garamgaebi.model.HomeNetworkingResult

class HomeNetworkingRVAdapter (private val dataList: ArrayList<HomeNetworkingResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class ThisMonthViewHolder(val binding: ItemHomeNetworkingThismonthBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeNetworkingResult) {
            with(binding) {
                if(data.payment == "PREMIUM")
                    icPay.setImageResource(R.drawable.ic_item_home_charged)
                else
                    icPay.setImageResource(R.drawable.ic_item_home_for_free)
                itemHomeNetworkingTvName.text = data.title
                itemHomeNetworkingTvDateData.text = GaramgaebiFunction().getDateYMD(data.date)
                itemHomeNetworkingTvPlaceData.text = data.location
                itemHomeNetworkingTvDDay.text = GaramgaebiFunction().getDDay(data.date)
            }
        }
    }
    inner class ScheduledViewHolder(val binding: ItemHomeNetworkingScheduledBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeNetworkingResult) {
            with(binding) {
                if(data.payment == "PREMIUM")
                    icPay.setImageResource(R.drawable.ic_item_home_charged)
                else
                    icPay.setImageResource(R.drawable.ic_item_home_for_free)
                itemHomeNetworkingTvName.text = data.title
                itemHomeNetworkingTvDateData.text = GaramgaebiFunction().getDateYMD(data.date)
                itemHomeNetworkingTvPlaceData.text = data.location
                itemHomeNetworkingTvDDay.text = GaramgaebiFunction().getDDay(data.date)
            }
        }
    }
    inner class ClosedViewHolder(val binding: ItemHomeNetworkingClosedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeNetworkingResult) {
            with(binding) {
                if(data.payment == "PREMIUM")
                    icPay.setImageResource(R.drawable.ic_item_home_charged)
                else
                    icPay.setImageResource(R.drawable.ic_item_home_for_free)
                itemHomeNetworkingTvName.text = data.title
                itemHomeNetworkingTvDateData.text = GaramgaebiFunction().getDateYMD(data.date)
                itemHomeNetworkingTvPlaceData.text = data.location
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            THIS_MONTH -> {
                val binding = ItemHomeNetworkingThismonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ThisMonthViewHolder(binding)
            }
            READY -> {
                val binding = ItemHomeNetworkingScheduledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ScheduledViewHolder(binding)
            }
            CLOSED-> {
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
                if(dataList[position].isOpen == "OPEN"){
                    holder.binding.root.setOnClickListener {
                        itemClickListener.onClick(position)
                    }
                }
            }
            is ScheduledViewHolder -> {
                holder.bind(dataList[position])
                if(dataList[position].isOpen == "OPEN"){
                    holder.binding.root.setOnClickListener {
                        itemClickListener.onClick(position)
                    }
                }
            }
            is ClosedViewHolder -> {
                holder.bind(dataList[position])
                if(dataList[position].isOpen == "OPEN"){
                    holder.binding.root.setOnClickListener {
                        itemClickListener.onClick(position)
                    }
                }
            }
        }
    }
    override fun getItemCount(): Int = dataList.size
        override fun getItemViewType(position: Int): Int {
            return when(dataList[position].status) {
                "THIS_MONTH" -> THIS_MONTH
                "READY" -> READY
                "CLOSED" -> CLOSED
                else -> THIS_MONTH
            }
        }
    interface OnItemClickListener {
        fun onClick(position: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }
}
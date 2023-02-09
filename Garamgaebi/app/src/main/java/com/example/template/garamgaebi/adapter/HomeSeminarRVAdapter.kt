package com.example.template.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.databinding.ItemHomeSeminarClosedBinding
import com.example.template.garamgaebi.databinding.ItemHomeSeminarScheduledBinding
import com.example.template.garamgaebi.databinding.ItemHomeSeminarThismonthBinding
import com.example.template.garamgaebi.model.HomeSeminarResult

class HomeSeminarRVAdapter (private val dataList: ArrayList<HomeSeminarResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    inner class ThisMonthViewHolder(val binding: ItemHomeSeminarThismonthBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSeminarResult) {
            if(data.payment == "PREMIUM")
                binding.icPay.setImageResource(R.drawable.ic_item_home_charged)
            else
                binding.icPay.setImageResource(R.drawable.ic_item_home_for_free)
            with(binding) {
                itemHomeSeminarTvName.text = data.title
                itemHomeSeminarTvDateData.text = data.date
                itemHomeSeminarTvPlaceData.text = data.location
                itemHomeSeminarTvDDay.text = "D-day"
            }
        }
    }
    inner class ScheduledViewHolder(val binding: ItemHomeSeminarScheduledBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSeminarResult) {
            if(data.payment == "PREMIUM")
                binding.icPay.setImageResource(R.drawable.ic_item_home_charged)
            else
                binding.icPay.setImageResource(R.drawable.ic_item_home_for_free)
            with(binding) {
                itemHomeSeminarTvName.text = data.title
                itemHomeSeminarTvDateData.text = data.date
                itemHomeSeminarTvPlaceData.text = data.location
                itemHomeSeminarTvDDay.text = "D-day"
            }
        }
    }
    inner class ClosedViewHolder(val binding: ItemHomeSeminarClosedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSeminarResult) {
            if(data.payment == "PREMIUM")
                binding.icPay.setImageResource(R.drawable.ic_item_home_charged)
            else
                binding.icPay.setImageResource(R.drawable.ic_item_home_for_free)
            with(binding) {
                itemHomeSeminarTvName.text = data.title
                itemHomeSeminarTvDateData.text = data.date
                itemHomeSeminarTvPlaceData.text = data.location
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
            3-> {
                val binding = ItemHomeSeminarClosedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
                ClosedViewHolder(binding)
            }
            else -> {
                val binding = ItemHomeSeminarThismonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
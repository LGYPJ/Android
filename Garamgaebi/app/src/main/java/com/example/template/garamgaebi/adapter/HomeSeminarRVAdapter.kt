package com.example.template.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.CLOSED
import com.example.template.garamgaebi.common.GaramgaebiFunction
import com.example.template.garamgaebi.common.READY
import com.example.template.garamgaebi.common.THIS_MONTH
import com.example.template.garamgaebi.databinding.ItemHomeSeminarClosedBinding
import com.example.template.garamgaebi.databinding.ItemHomeSeminarScheduledBinding
import com.example.template.garamgaebi.databinding.ItemHomeSeminarThismonthBinding
import com.example.template.garamgaebi.model.HomeSeminarResult

class HomeSeminarRVAdapter (private val dataList: ArrayList<HomeSeminarResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    inner class ThisMonthViewHolder(val binding: ItemHomeSeminarThismonthBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSeminarResult) {
            with(binding) {
                if(data.payment == "PREMIUM")
                    icPay.setImageResource(R.drawable.ic_item_home_charged)
                else
                    icPay.setImageResource(R.drawable.ic_item_home_for_free)
                itemHomeSeminarTvName.text = data.title
                itemHomeSeminarTvDateData.text = GaramgaebiFunction().getDateYMD(data.date)
                itemHomeSeminarTvPlaceData.text = data.location
                itemHomeSeminarTvDDay.text = GaramgaebiFunction().getDDay(data.date)
            }
        }
    }
    inner class ScheduledViewHolder(val binding: ItemHomeSeminarScheduledBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSeminarResult) {
            with(binding) {
                if(data.payment == "PREMIUM")
                    icPay.setImageResource(R.drawable.ic_item_home_charged)
                else
                    icPay.setImageResource(R.drawable.ic_item_home_for_free)
                itemHomeSeminarTvName.text = data.title
                itemHomeSeminarTvDateData.text = GaramgaebiFunction().getDateYMD(data.date)
                itemHomeSeminarTvPlaceData.text = data.location
                itemHomeSeminarTvDDay.text = GaramgaebiFunction().getDDay(data.date)
            }
        }
    }
    inner class ClosedViewHolder(val binding: ItemHomeSeminarClosedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSeminarResult) {
            with(binding) {
                if(data.payment == "PREMIUM")
                    icPay.setImageResource(R.drawable.ic_item_home_charged)
                else
                    icPay.setImageResource(R.drawable.ic_item_home_for_free)
                itemHomeSeminarTvName.text = data.title
                itemHomeSeminarTvDateData.text = GaramgaebiFunction().getDateYMD(data.date)
                itemHomeSeminarTvPlaceData.text = data.location
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            THIS_MONTH -> {
                val binding = ItemHomeSeminarThismonthBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ThisMonthViewHolder(binding)
            }
            READY -> {
                val binding = ItemHomeSeminarScheduledBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ScheduledViewHolder(binding)
            }
            CLOSED -> {
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
                /*holder.binding.root.setOnClickListener {
                    itemClickListener.onClick(position)
                }*/
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
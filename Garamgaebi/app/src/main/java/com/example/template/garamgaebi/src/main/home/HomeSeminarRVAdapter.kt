package com.example.template.garamgaebi.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.databinding.ItemHomeSeminarClosedBinding
import com.example.template.garamgaebi.databinding.ItemHomeSeminarScheduledBinding
import com.example.template.garamgaebi.databinding.ItemHomeSeminarThismonthBinding
import com.example.template.garamgaebi.model.HomeSeminarResult

class HomeSeminarRVAdapter (private val dataList: ArrayList<HomeSeminarResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener:  HomeSeminarRVAdapter.OnItemClickListener

    inner class ThisMonthViewHolder(val binding: ItemHomeSeminarThismonthBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSeminarResult) {
            binding.model = data
        }
    }
    inner class ScheduledViewHolder(val binding: ItemHomeSeminarScheduledBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSeminarResult) {
            binding.model = data
        }
    }
    inner class ClosedViewHolder(val binding: ItemHomeSeminarClosedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeSeminarResult) {
            binding.model = data
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
package com.example.template.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.common.GaramgaebiFunction
import com.example.template.garamgaebi.databinding.ItemHomeMyMeetingBinding

import com.example.template.garamgaebi.model.HomeProgramResult

class HomeMyMeetingRVAdapter(private val dataList: ArrayList<HomeProgramResult>): RecyclerView.Adapter<HomeMyMeetingRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class ViewHolder( val binding: ItemHomeMyMeetingBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeProgramResult){
            with(binding){
                itemHomeMyMeetingTvName.text = data.title
                itemHomeMyMeetingTvDate.text = GaramgaebiFunction().getDateMyMeeting(data.date)
                itemHomeMyMeetingTvPlace.text = data.location
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeMyMeetingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
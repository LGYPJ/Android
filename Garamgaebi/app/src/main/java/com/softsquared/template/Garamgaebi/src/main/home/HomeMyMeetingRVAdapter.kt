package com.softsquared.template.Garamgaebi.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemHomeMyMeetingBinding
import com.softsquared.template.Garamgaebi.databinding.ItemHomeUserBinding

class HomeMyMeetingRVAdapter(private val dataList: ArrayList<HomeMyMeetingItemData>): RecyclerView.Adapter<HomeMyMeetingRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: HomeMyMeetingRVAdapter.OnItemClickListener
    inner class ViewHolder( val binding: ItemHomeMyMeetingBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeMyMeetingItemData){
            binding.itemHomeMyMeetingTvName.text = data.name
            binding.itemHomeMyMeetingTvDate.text = data.date
            binding.itemHomeMyMeetingTvPlace.text = data.place
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
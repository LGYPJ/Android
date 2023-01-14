package com.softsquared.template.Garamgaebi.src.main.networking_game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemNetworkGameSelectBinding

class NetworkingGameSelectAdapter(private val dataList: ArrayList<NetworkingGameSelect>): RecyclerView.Adapter<NetworkingGameSelectAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemNetworkGameSelectBinding):
            RecyclerView.ViewHolder(binding.root) {
                fun bind(data: NetworkingGameSelect){
                    binding.activityItemNetworkGameTv.text = data.place
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNetworkGameSelectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
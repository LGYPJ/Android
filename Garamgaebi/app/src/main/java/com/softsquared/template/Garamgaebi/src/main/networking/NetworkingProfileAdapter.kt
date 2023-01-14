package com.softsquared.template.Garamgaebi.src.main.networking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemNetworkProfileBinding

class NetworkingProfileAdapter(private val dataList: ArrayList<NetworkingProfile>): RecyclerView.Adapter<NetworkingProfileAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemNetworkProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                fun bind(data: NetworkingProfile){
                    binding.itemProfileImg.setImageResource(data.img)
                    binding.itemProfileNameTv.text = data.name
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNetworkProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }




}
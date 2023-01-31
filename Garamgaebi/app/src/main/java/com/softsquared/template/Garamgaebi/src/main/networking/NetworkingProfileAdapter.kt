package com.softsquared.template.Garamgaebi.src.main.networking

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.*
import com.softsquared.template.Garamgaebi.src.main.seminar.*

class NetworkingProfileAdapter(private val dataList: ArrayList<NetworkingProfile>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class OriginViewHolder(private val binding: ItemNetworkProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                fun bind(data: NetworkingProfile){
                    binding.itemProfileImg.setImageResource(data.img)
                    binding.itemProfileNameTv.text = data.name
                }
            }

    inner class BlueViewHolder(private val binding: ItemNetworkProfileBlueBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NetworkingProfile){
            binding.itemProfileImg.setImageResource(data.img)
            binding.itemProfileNameTv.text = data.name
        }
    }

    inner class GrayViewHolder(private val binding: ItemNetworkProfileGrayBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: NetworkingProfile){
            binding.itemProfileImg.setImageResource(data.img)
            binding.itemProfileNameTv.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            multi_type1 -> {
                val binding = ItemNetworkProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                OriginViewHolder(binding)
            }
            multi_type2 -> {
                val binding = ItemNetworkProfileBlueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BlueViewHolder(binding)
            }
            multi_type3 -> {
                val binding = ItemNetworkProfileGrayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GrayViewHolder(binding)
            }
            else -> {
                val binding = ItemNetworkProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                OriginViewHolder(binding)
            }
        }

    }


    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(dataList[position].type){
            multi_type1 -> {
                (holder as OriginViewHolder).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
            multi_type2 -> {
                (holder as BlueViewHolder).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
            multi_type3 -> {
                (holder as GrayViewHolder).bind(dataList[position])
                holder.setIsRecyclable(false)
            }
        }
    }
    override fun getItemViewType(position: Int): Int {
        return dataList[position].type
    }
}
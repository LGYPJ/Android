package com.garamgaebi.garamgaebi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.garamgaebi.garamgaebi.common.BLUE
import com.garamgaebi.garamgaebi.common.ORIGIN
import com.garamgaebi.garamgaebi.databinding.ItemNetworkGamePlaceProfileBinding
import com.garamgaebi.garamgaebi.databinding.ItemNetworkProfileBlueBinding
import com.garamgaebi.garamgaebi.databinding.ItemNetworkingGameProfileBlueBinding
import com.garamgaebi.garamgaebi.model.GameMemberGetResult

class NetworkingGameProfileAdapter(private val dataList: ArrayList<GameMemberGetResult>, private val number : Int): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class ViewHolder(val binding: ItemNetworkGamePlaceProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                @SuppressLint("SuspiciousIndentation")
                fun bind(data: GameMemberGetResult, position: Int){
                    binding.itemNetworkGameProfileNameTv.text = data.nickname
                    Glide.with(binding.itemNetworkGameProfileFrame.context)
                        .load(data.profileUrl)
                        .into(binding.itemNetworkGameProfileFrame)
                }
    }

    inner class BlueViewHolder(private val binding: ItemNetworkingGameProfileBlueBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: GameMemberGetResult, position: Int){
            binding.itemProfileNameTv.text = data.nickname
            Glide.with(binding.itemNetworkGameProfileFrame.context)
                .load(data.profileUrl)
                .into(binding.itemNetworkGameProfileFrame)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            BLUE -> {
                val binding = ItemNetworkingGameProfileBlueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BlueViewHolder(binding)
            }
            ORIGIN -> {
                val binding = ItemNetworkGamePlaceProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }

            else -> {
                val binding = ItemNetworkGamePlaceProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }


    }

    override fun onBindViewHolder(holder:  RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder -> {
                holder.bind(dataList[position], position)
            }
            is BlueViewHolder -> {
                holder.bind(dataList[position], position)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when(position){
            number -> BLUE
            else -> ORIGIN
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
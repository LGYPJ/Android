package com.example.template.garamgaebi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.garamgaebi.common.BLUE
import com.example.template.garamgaebi.common.GRAY
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.common.ORIGIN
import com.example.template.garamgaebi.databinding.ItemNetworkGamePlaceProfileBinding
import com.example.template.garamgaebi.databinding.ItemNetworkProfileBlueBinding
import com.example.template.garamgaebi.model.GameMemberGetResult
import com.example.template.garamgaebi.model.GameMemberGetResultRe
import com.example.template.garamgaebi.model.NetworkingResult
import com.example.template.garamgaebi.src.main.networking_game.NetworkingGameProfile

class NetworkingGameProfileAdapter(private val dataList: ArrayList<GameMemberGetResult>, private val number : Int): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    inner class ViewHolder(val binding: ItemNetworkGamePlaceProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                @SuppressLint("SuspiciousIndentation")
                fun bind(data: GameMemberGetResult, position: Int){
                    binding.itemNetworkGameProfileNameTv.text = data.nickname
                    Glide.with(binding.itemNetworkGameProfileImg.context)
                        .load(data.profileUrl)
                        .into(binding.itemNetworkGameProfileImg)
                }
    }

    inner class BlueViewHolder(private val binding: ItemNetworkProfileBlueBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: GameMemberGetResult, position: Int){
            binding.itemProfileNameTv.text = data.nickname
            Glide.with(binding.itemProfileImg.context)
                .load(data.profileUrl)
                .into(binding.itemProfileImg)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            BLUE -> {
                val binding = ItemNetworkProfileBlueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
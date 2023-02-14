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
import com.example.template.garamgaebi.model.GameMemberGetResult
import com.example.template.garamgaebi.src.main.networking_game.NetworkingGameProfile

class NetworkingGameProfileAdapter(private val dataList: ArrayList<GameMemberGetResult>, private val blue : Boolean): RecyclerView.Adapter<NetworkingGameProfileAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemNetworkGamePlaceProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                @SuppressLint("SuspiciousIndentation")
                fun bind(data: GameMemberGetResult, position: Int){
                    binding.itemNetworkGameProfileNameTv.text = data.nickname
                    Glide.with(binding.itemNetworkGameProfileImg.context)
                        .load(data.profileUrl)
                        .into(binding.itemNetworkGameProfileImg)
                    if(blue){
                        binding.itemNetworkGameProfileBorder.visibility = VISIBLE
                    }
                    else {
                        binding.itemNetworkGameProfileBorder.visibility = INVISIBLE
                    }
                }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNetworkGamePlaceProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], position)
    }

    /*override fun getItemViewType(position: Int): Int {
        return when(position){
             0 ->
            -1 -> GRAY
            else -> ORIGIN
        }
    }*/

    override fun getItemCount(): Int {
        return dataList.size
    }
}
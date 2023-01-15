package com.softsquared.template.Garamgaebi.src.main.networking_game

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemNetworkGamePlaceProfileBinding

class NetworkingGameProfileAdapter(private val dataList: ArrayList<NetworkingGameProfile>): RecyclerView.Adapter<NetworkingGameProfileAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemNetworkGamePlaceProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                @SuppressLint("SuspiciousIndentation")
                fun bind(data: NetworkingGameProfile, position: Int){
                    binding.itemNetworkGameProfileImg.setImageResource(data.img)
                    binding.itemNetworkGameProfileNameTv.text = data.name


                    if(data.next) {
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

    override fun getItemCount(): Int {
        return dataList.size
    }
}
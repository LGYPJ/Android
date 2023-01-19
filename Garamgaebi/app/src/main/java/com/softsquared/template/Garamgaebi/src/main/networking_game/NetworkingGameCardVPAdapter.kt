package com.softsquared.template.Garamgaebi.src.main.networking_game

import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemGameCardVpBinding
import com.softsquared.template.Garamgaebi.databinding.ItemNetworkGamePlaceProfileBinding

class NetworkingGameCardVPAdapter(private val dataList: ArrayList<NetworkingGameCard>):  RecyclerView.Adapter<NetworkingGameCardVPAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemGameCardVpBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(data :NetworkingGameCard, position: Int){
            binding.activityItemGameCardTv.text = data.content

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemGameCardVpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], position)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}
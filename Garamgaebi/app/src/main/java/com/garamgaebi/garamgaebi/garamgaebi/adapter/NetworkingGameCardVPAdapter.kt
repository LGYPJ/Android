package com.garamgaebi.garamgaebi.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.garamgaebi.garamgaebi.garamgaebi.databinding.ItemGameCardVpBinding

class NetworkingGameCardVPAdapter(private val dataList: List<String>):  RecyclerView.Adapter<NetworkingGameCardVPAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemGameCardVpBinding) :RecyclerView.ViewHolder(binding.root){
        fun bind(data: String, position: Int){
            Glide.with(binding.activityItemGameCardBlankImg.context)
                .load(data)
                .into(binding.activityItemGameCardBlankImg)
            //binding.activityItemGameCardTv.text = data.result

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
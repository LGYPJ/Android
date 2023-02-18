package com.garamgaebi.garamgaebi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.garamgaebi.garamgaebi.common.ORIGIN
import com.garamgaebi.garamgaebi.databinding.ItemGameCardVpBinding

class NetworkingGameCardVPAdapter(private val dataList: List<String>, private val number: Int):  RecyclerView.Adapter<NetworkingGameCardVPAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemGameCardVpBinding)
        :RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SuspiciousIndentation")
        fun bind(data : String, position: Int){
            Glide.with(binding.activityItemGameCardBlankImg.context)
                .load(data)
                .into(binding.activityItemGameCardBlankImg)


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when(viewType){
            ORIGIN -> {
                val binding = ItemGameCardVpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }

            else -> {
                val binding = ItemGameCardVpBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder){
            else -> {
                holder.bind(dataList[number], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    /*override fun getItemViewType(position: Int): Int {
        return when(position){
            number-> ORIGIN
            else -> ORIGIN
        }
    }*/


}
package com.example.template.garamgaebi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.garamgaebi.common.BLUE
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.common.ORIGIN
import com.example.template.garamgaebi.databinding.ItemNetworkProfileBinding
import com.example.template.garamgaebi.databinding.ItemNetworkProfileBlueBinding
import com.example.template.garamgaebi.databinding.ItemSeminarProfileBinding
import com.example.template.garamgaebi.databinding.ItemSeminarProfileBlueBinding
import com.example.template.garamgaebi.model.NetworkingParticipantsResult
import com.example.template.garamgaebi.model.NetworkingResult

class NetworkingProfileAdapter(private val dataList: ArrayList<NetworkingResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener

    inner class OriginViewHolder(private val binding: ItemNetworkProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                @SuppressLint("SetTextI18n")
                fun bind(data: NetworkingResult){
                    binding.itemProfileNameTv.text = data.nickname
                    Glide.with(binding.itemProfileImg.context)
                        .load(data.profileImg)
                        .into(binding.itemProfileImg)

                }
            }

    inner class BlueViewHolder(private val binding: ItemNetworkProfileBlueBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: NetworkingResult){
            binding.itemProfileNameTv.text = data.nickname
            Glide.with(binding.itemProfileImg.context)
                .load(data.profileImg)
                .into(binding.itemProfileImg)

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            BLUE -> {
                val binding = ItemNetworkProfileBlueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BlueViewHolder(binding)
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
        when(holder){
            is OriginViewHolder -> {
                holder.bind(dataList[position])
                holder.itemView.setOnClickListener {
                    itemClickListener.onClick(position)
                }
            }
            is BlueViewHolder -> {
                holder.bind(dataList[position])
                holder.itemView.setOnClickListener {
                    itemClickListener.onClick(position)
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(dataList[position].memberIdx){
            GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0) -> BLUE
            else -> ORIGIN
        }
    }

    interface OnItemClickListener {
        fun onClick(position: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

}
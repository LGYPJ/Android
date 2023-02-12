package com.example.template.garamgaebi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.garamgaebi.common.BLUE
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.common.ORIGIN
import com.example.template.garamgaebi.databinding.ItemSeminarProfileBinding
import com.example.template.garamgaebi.databinding.ItemSeminarProfileBlueBinding
import com.example.template.garamgaebi.src.main.seminar.data.SeminarParticipantsResult
import com.example.template.garamgaebi.src.main.seminar.data.SeminarResult


class SeminarProfileAdapter(private val dataList: ArrayList<SeminarResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    inner class OriginViewHolder(val binding: ItemSeminarProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                @SuppressLint("SetTextI18n")
                fun bind(data: SeminarResult){
                    binding.itemProfileNameTv.text = data.nickname
                    Glide.with(binding.itemProfileImg.context)
                        .load(data.profileImg)
                        .into(binding.itemProfileImg)
                }
            }

    inner class BlueViewHolder(private val binding: ItemSeminarProfileBlueBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: SeminarResult){
            binding.itemProfileNameTv.text = data.nickname
            Glide.with(binding.itemProfileImg.context)
                .load(data.profileImg)
                .into(binding.itemProfileImg)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            BLUE -> {
                val binding = ItemSeminarProfileBlueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BlueViewHolder(binding)
            }
            else -> {
                val binding = ItemSeminarProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                OriginViewHolder(binding)
            }
        }

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

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        //return dataList[position].type
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
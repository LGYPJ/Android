package com.example.template.garamgaebi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.garamgaebi.databinding.ItemSeminarProfileBinding
import com.example.template.garamgaebi.src.main.seminar.data.SeminarParticipantsResult


class SeminarProfileAdapter(private val dataList: ArrayList<SeminarParticipantsResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class OriginViewHolder(private val binding: ItemSeminarProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                @SuppressLint("SetTextI18n")
                fun bind(data: SeminarParticipantsResult){
                    binding.itemProfileNameTv.text = data.nickname
                    Glide.with(binding.itemProfileImg.context)
                        .load(data.profileImg)
                        .into(binding.itemProfileImg)

                }
            }

    /*inner class BlueViewHolder(private val binding: ItemSeminarProfileBlueBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SeminarProfile){
            binding.itemProfileImg.setImageResource(data.img)
            binding.itemProfileNameTv.text = data.name
        }
    }

    inner class GrayViewHolder(private val binding: ItemSeminarProfileGrayBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SeminarProfile){
            binding.itemProfileImg.setImageResource(data.img)
            binding.itemProfileNameTv.text = data.name
        }
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        /*return when(viewType){
            multi_type1 -> {
                val binding = ItemSeminarProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                OriginViewHolder(binding)
            }
            multi_type2 -> {
                val binding = ItemSeminarProfileBlueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BlueViewHolder(binding)
            }
            multi_type3 -> {
                val binding = ItemSeminarProfileGrayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GrayViewHolder(binding)
            }
            else -> {
                val binding = ItemSeminarProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                OriginViewHolder(binding)
            }
        }*/
        val binding = ItemSeminarProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OriginViewHolder(binding)


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        /*when(dataList[position].type){
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
        }*/
        (holder as OriginViewHolder).bind(dataList[position])

    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    /*override fun getItemViewType(position: Int): Int {
        //return dataList[position].type
        return dataList.size
    }*/



}
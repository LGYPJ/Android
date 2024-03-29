package com.garamgaebi.garamgaebi.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BLUE
import com.garamgaebi.garamgaebi.common.GRAY
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.ORIGIN
import com.garamgaebi.garamgaebi.databinding.ItemSeminarProfileBinding
import com.garamgaebi.garamgaebi.databinding.ItemSeminarProfileBlueBinding
import com.garamgaebi.garamgaebi.databinding.ItemSeminarProfileGrayBinding
import com.garamgaebi.garamgaebi.model.SeminarResult
import kotlinx.coroutines.runBlocking


class SeminarProfileAdapter(private val dataList: ArrayList<SeminarResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener

    inner class OriginViewHolder(val binding: ItemSeminarProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                @SuppressLint("SetTextI18n")
                fun bind(data: SeminarResult){
                    binding.itemProfileNameTv.text = data.nickname
                    if(data.profileImg == null){
                        Log.d("participantsImg", "why")
                        binding.activityItemSeminarProfileImg.visibility = VISIBLE

                        /*Glide.with(binding.activityItemSeminarProfile.context)
                            .load(R.drawable.ic_transparent)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.activityItemSeminarProfile)*/
                    }
                    else{
                        binding.activityItemSeminarProfileImg.visibility = GONE
                        Glide.with(binding.activityItemSeminarProfile.context)
                            .load(data.profileImg)
                            .placeholder(R.drawable.default_profile)
                            .error(R.drawable.default_profile)
                            .fallback(R.drawable.default_profile)
                            .skipMemoryCache(true)
                            .into(binding.activityItemSeminarProfile)
                    }
                }
            }

    inner class BlueViewHolder(private val binding: ItemSeminarProfileBlueBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: SeminarResult){
            binding.itemProfileNameTv.text = data.nickname
            if(data.profileImg == null){
                binding.activityItemSeminarProfileImg.visibility = VISIBLE
//                Glide.with(binding.itemNetworkGameProfileFrame.context)
//                    .load(R.drawable.ic_transparent)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
//                    .skipMemoryCache(true)
//                    .into(binding.itemNetworkGameProfileFrame)
            }
            else{
                binding.activityItemSeminarProfileImg.visibility = GONE
                Glide.with(binding.itemNetworkGameProfileFrame.context)
                    .load(data.profileImg)
                    .placeholder(R.drawable.default_profile)
                    .error(R.drawable.default_profile)
                    .fallback(R.drawable.default_profile)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(binding.itemNetworkGameProfileFrame)
            }
        }
    }

    inner class GrayViewHolder(binding : ItemSeminarProfileGrayBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(){

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            BLUE -> {
                val binding = ItemSeminarProfileBlueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BlueViewHolder(binding)
            }
            GRAY -> {
                val binding = ItemSeminarProfileGrayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                GrayViewHolder(binding)
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
            is GrayViewHolder -> {
                holder.bind()

            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        val id = runBlocking { // 코루틴의 결과를 대기하고 반환
            GaramgaebiApplication().loadIntData("memberIdx") ?: 0
        }
        Log.d("why_you",dataList[position].memberIdx.toString() + id.toString())

        return when(dataList[position].memberIdx){
            id -> BLUE
            -1 -> GRAY
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
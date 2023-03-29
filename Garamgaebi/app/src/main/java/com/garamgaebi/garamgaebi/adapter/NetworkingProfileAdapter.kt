package com.garamgaebi.garamgaebi.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
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
import com.garamgaebi.garamgaebi.databinding.ItemNetworkProfileBinding
import com.garamgaebi.garamgaebi.databinding.ItemNetworkProfileBlueBinding
import com.garamgaebi.garamgaebi.databinding.ItemNetworkProfileGrayBinding
import com.garamgaebi.garamgaebi.model.NetworkingResult
import kotlinx.coroutines.runBlocking

class NetworkingProfileAdapter(private val dataList: ArrayList<NetworkingResult>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener

    inner class OriginViewHolder(private val binding: ItemNetworkProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                @SuppressLint("SetTextI18n")
                fun bind(data: NetworkingResult){
                    binding.itemProfileNameTv.text = data.nickname
                    if(data.profileImg == null){
                        binding.activityItemSeminarProfileImg.visibility = VISIBLE
//                        Glide.with(binding.activityItemSeminarProfile.context)
//                            .load(R.drawable.ic_transparent)
//                            .diskCacheStrategy(DiskCacheStrategy.NONE)
//                            .skipMemoryCache(true)
//                            .into(binding.activityItemSeminarProfile)
                    }
                    else{
                        binding.activityItemSeminarProfileImg.visibility = GONE
                        Glide.with(binding.activityItemSeminarProfile.context)
                            .load(data.profileImg)
                            .placeholder(R.drawable.default_profile)
                            .error(R.drawable.default_profile)
                            .fallback(R.drawable.default_profile)
                            .diskCacheStrategy(DiskCacheStrategy.NONE)
                            .skipMemoryCache(true)
                            .into(binding.activityItemSeminarProfile)
                    }


                }
            }

    inner class BlueViewHolder(private val binding: ItemNetworkProfileBlueBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: NetworkingResult){
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

    inner class GrayViewHolder(binding: ItemNetworkProfileGrayBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(){

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            BLUE -> {
                val binding = ItemNetworkProfileBlueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BlueViewHolder(binding)
            }
            GRAY -> {
                val binding = ItemNetworkProfileGrayBinding.inflate(LayoutInflater.from(parent.context),parent, false)
                GrayViewHolder(binding)

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
            is GrayViewHolder -> {
                holder.bind()
            }
        }
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
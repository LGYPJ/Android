package com.garamgaebi.garamgaebi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.databinding.ItemHomeUserBinding
import com.garamgaebi.garamgaebi.model.HomeUserResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeUserItemRVAdapter(private val dataList: ArrayList<HomeUserResult>): RecyclerView.Adapter<HomeUserItemRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class ViewHolder( val binding: ItemHomeUserBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeUserResult){
            with(binding) {
                if(data.profileUrl == null) {
                    itemHomeUserIvProfileDefault.visibility = View.VISIBLE
                    Glide.with(itemView)
                        .load(R.drawable.ic_transparent)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(itemHomeUserIvProfile)
                }
                else {
                    itemHomeUserIvProfileDefault.visibility = View.GONE

                    Glide.with(itemView)
                        .load(data.profileUrl)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .centerCrop()
                        .into(itemHomeUserIvProfile)

//                    CoroutineScope(Dispatchers.Main).launch {
//                        val bitmap = withContext(Dispatchers.IO) {
//                            GaramgaebiFunction.ImageLoader.loadImage(data.profileUrl)
//                        }
//                        binding.itemHomeUserIvProfile.setImageBitmap(bitmap)
//                    }
                }
                itemHomeUserIvProfile.clipToOutline = true
                itemHomeUserTvNickname.text = data.nickName
                itemHomeUserTvBelong.text = data.belong
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
        holder.binding.root.setOnClickListener {
            itemClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int = dataList.size

    interface OnItemClickListener {
        fun onClick(position: Int)
    }
    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }

}
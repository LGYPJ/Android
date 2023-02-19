package com.garamgaebi.garamgaebi.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.databinding.ItemHomeUserBinding
import com.garamgaebi.garamgaebi.model.HomeUserResult

class HomeUserItemRVAdapter(private val dataList: ArrayList<HomeUserResult>): RecyclerView.Adapter<HomeUserItemRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class ViewHolder( val binding: ItemHomeUserBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeUserResult){
            with(binding) {
                Log.d("homeuser", "${data.profileUrl}")
                if(data.profileUrl == null)
                    itemHomeUserIvProfileDefault.visibility = View.VISIBLE
                else {
                    itemHomeUserIvProfileDefault.visibility = View.GONE
                    Glide.with(itemView)
                    .load(data.profileUrl)
                    .centerCrop()
                    .into(itemHomeUserIvProfile)
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
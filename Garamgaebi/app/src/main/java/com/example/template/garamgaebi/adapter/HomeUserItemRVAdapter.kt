package com.example.template.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.databinding.ItemHomeUserBinding
import com.example.template.garamgaebi.model.HomeUserResult

class HomeUserItemRVAdapter(private val dataList: ArrayList<HomeUserResult>): RecyclerView.Adapter<HomeUserItemRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class ViewHolder( val binding: ItemHomeUserBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeUserResult){
            with(binding) {
                itemHomeUserIvProfile.clipToOutline = true
                itemHomeUserTvNickname.text = data.nickName
                itemHomeUserTvOrg.text = data.group
                itemHomeUserTvMajor.text = data.detail
            }
            Glide.with(itemView).load(R.drawable.ic_launcher_background)
                .into(binding.itemHomeUserIvProfile)
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
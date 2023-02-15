package com.garamgaebi.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.garamgaebi.garamgaebi.garamgaebi.databinding.ItemHomeUserBinding
import com.garamgaebi.garamgaebi.model.HomeUserResult

class HomeUserItemRVAdapter(private val dataList: ArrayList<HomeUserResult>): RecyclerView.Adapter<HomeUserItemRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: OnItemClickListener
    inner class ViewHolder( val binding: ItemHomeUserBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeUserResult){
            with(binding) {
                Glide.with(itemView).load(data.profileUrl)
                    .into(itemHomeUserIvProfile)
                itemHomeUserIvProfile.clipToOutline = true
                itemHomeUserTvNickname.text = data.nickName
                if(data.belong == null) {
                    itemHomeUserTvBelong.visibility = View.GONE
                    itemHomeUserTvOrg.visibility = View.VISIBLE
                    itemHomeUserTvMajor.visibility = View.VISIBLE
                    itemHomeUserTvOrg.text = data.group
                    itemHomeUserTvMajor.text = data.detail
                } else {
                    itemHomeUserTvBelong.visibility = View.VISIBLE
                    itemHomeUserTvOrg.visibility = View.GONE
                    itemHomeUserTvMajor.visibility = View.GONE
                    itemHomeUserTvBelong.text = data.belong
                    itemHomeUserTvOrg.text = ""
                    itemHomeUserTvMajor.text = ""
                }
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
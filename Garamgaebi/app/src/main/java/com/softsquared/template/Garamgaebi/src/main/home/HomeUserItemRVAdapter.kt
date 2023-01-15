package com.softsquared.template.Garamgaebi.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemHomeUserBinding

class HomeUserItemRVAdapter(private val dataList: ArrayList<HomeUserItemData>): RecyclerView.Adapter<HomeUserItemRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: HomeUserItemRVAdapter.OnItemClickListener
    inner class ViewHolder( val binding: ItemHomeUserBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeUserItemData){
            binding.itemHomeUserProfileImg.setImageResource(data.profileImg)
            binding.itemHomeUserTvNickname.text = data.nickname
            binding.itemHomeUserTvOrg.text = data.org
            binding.itemHomeUserTvMajor.text = data.major
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
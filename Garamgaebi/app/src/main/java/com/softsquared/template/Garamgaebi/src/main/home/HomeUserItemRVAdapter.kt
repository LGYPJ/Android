package com.softsquared.template.Garamgaebi.src.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.databinding.ItemHomeUserBinding
import com.softsquared.template.Garamgaebi.model.HomeUserResult

class HomeUserItemRVAdapter(private val dataList: ArrayList<HomeUserResult>): RecyclerView.Adapter<HomeUserItemRVAdapter.ViewHolder>() {
    private lateinit var itemClickListener: HomeUserItemRVAdapter.OnItemClickListener
    inner class ViewHolder( val binding: ItemHomeUserBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: HomeUserResult){
            //binding.itemHomeUserProfileImg.setImageResource()
            binding.itemHomeUserTvNickname.text = data.nickName
            binding.itemHomeUserTvOrg.text = data.belong
            //binding.itemHomeUserTvMajor.text = data.major
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
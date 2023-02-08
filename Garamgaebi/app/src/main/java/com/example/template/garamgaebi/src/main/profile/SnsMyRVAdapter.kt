package com.example.template.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.garamgaebi.databinding.ItemMyprofileSnsBinding
import com.example.template.garamgaebi.databinding.ItemSeminarPresentBinding
import com.example.template.garamgaebi.model.PresentationResult
import com.example.template.garamgaebi.model.SNSData
import com.example.template.garamgaebi.src.main.seminar.SeminarPresentAdapter

class SnsMyRVAdapter(private val dataList: ArrayList<SNSData>): RecyclerView.Adapter<SnsMyRVAdapter.ViewHolder>(){

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ItemMyprofileSnsBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: SNSData) {
            binding.item = data

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMyprofileSnsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }


    interface OnItemClickListener {
        fun onClick(position: Int)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])

        holder.itemView.setOnClickListener {
            itemClickListener.onClick(position)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        itemClickListener = onItemClickListener
    }
}
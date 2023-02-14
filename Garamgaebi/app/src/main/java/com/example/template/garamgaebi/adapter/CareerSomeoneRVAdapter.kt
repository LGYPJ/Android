package com.example.template.garamgaebi.adapter

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.databinding.ItemMyprofileCareerBinding
import com.example.template.garamgaebi.databinding.ItemSomeoneprofileCareerBinding
import com.example.template.garamgaebi.model.CareerData
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.src.main.profile.CareerRVItemData

class CareerSomeoneRVAdapter(private val dataList: ArrayList<CareerData>, val mContext : Context): RecyclerView.Adapter<CareerSomeoneRVAdapter.ViewHolder>(){

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ItemSomeoneprofileCareerBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: CareerData) {
            binding.item = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSomeoneprofileCareerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

package com.example.template.garamgaebi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.databinding.ItemSomeoneprofileCareerBinding
import com.example.template.garamgaebi.databinding.ItemSomeoneprofileEduBinding
import com.example.template.garamgaebi.model.CareerData
import com.example.template.garamgaebi.model.EducationData
import com.example.template.garamgaebi.src.main.profile.EduRVItemData

class EduSomeoneRVAdapter(private val dataList: ArrayList<EducationData>, val mContext : Context): RecyclerView.Adapter<EduSomeoneRVAdapter.ViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ItemSomeoneprofileEduBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: EducationData) {
            binding.item = data
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemSomeoneprofileEduBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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


    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        itemClickListener = onItemClickListener
    }
}
package com.example.template.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.databinding.ItemMyprofileEduBinding
import com.example.template.garamgaebi.databinding.ItemMyprofileSnsBinding
import com.example.template.garamgaebi.model.EducationData
import com.example.template.garamgaebi.model.SNSData

class EduMyRVAdapter(private val dataList: ArrayList<EducationData>): RecyclerView.Adapter<EduMyRVAdapter.ViewHolder>(){

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ItemMyprofileEduBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: EducationData) {
            binding.item = data

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMyprofileEduBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

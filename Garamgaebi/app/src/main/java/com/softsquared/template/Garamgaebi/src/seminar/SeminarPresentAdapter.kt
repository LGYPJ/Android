package com.softsquared.template.Garamgaebi.src.seminar

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemSeminarPresentBinding

class SeminarPresentAdapter(private val dataList: ArrayList<SeminarPresent>): RecyclerView.Adapter<SeminarPresentAdapter.ViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ItemSeminarPresentBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: SeminarPresent){
            binding.activitySeminarFreePresentTitleTv.text = data.title
            binding.activitySeminarFreePresentNameTv.text = data.name
            binding.activitySeminarFreePresentJobTv.text = data.job
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSeminarPresentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
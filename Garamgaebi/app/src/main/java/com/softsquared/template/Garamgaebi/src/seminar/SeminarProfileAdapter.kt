package com.softsquared.template.Garamgaebi.src.seminar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemSeminarProfileBinding
import com.softsquared.template.Garamgaebi.src.main.MainActivity

class SeminarProfileAdapter(private val dataList: ArrayList<SeminarProfile>): RecyclerView.Adapter<SeminarProfileAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemSeminarProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                fun bind(data: SeminarProfile){
                    binding.itemProfileImg.setImageResource(data.img)
                    binding.itemProfileNameTv.text = data.name
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSeminarProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}
package com.garamgaebi.garamgaebi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garamgaebi.garamgaebi.garamgaebi.databinding.ItemCancelBankBinding
import com.garamgaebi.garamgaebi.src.main.cancel.Cancel

class CancelAdapter(private val dataList: ArrayList<Cancel>): RecyclerView.Adapter<CancelAdapter.ViewHolder>() {

    private lateinit var itemClickListener: SeminarPresentAdapter.OnItemClickListener

    inner class ViewHolder(private val binding: ItemCancelBankBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(data: Cancel){
                    binding.itemCancelImg.setImageResource(data.img)
                    binding.itemCancelTv.text = data.bank
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCancelBankBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
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

    interface OnItemClickListener: SeminarPresentAdapter.OnItemClickListener{
        override fun onClick(position: Int)
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener){
        itemClickListener = onItemClickListener
    }
}
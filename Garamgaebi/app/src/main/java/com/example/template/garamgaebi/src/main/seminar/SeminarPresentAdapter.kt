package com.example.template.garamgaebi.src.main.seminar

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.template.garamgaebi.databinding.ItemSeminarPresentBinding
import com.example.template.garamgaebi.model.PresentationResult

class SeminarPresentAdapter(private val dataList: ArrayList<PresentationResult>): RecyclerView.Adapter<SeminarPresentAdapter.ViewHolder>() {

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ItemSeminarPresentBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: PresentationResult) {
            /*binding.activitySeminarFreePresentTitleTv.text = data.title
            binding.activitySeminarFreePresentNameTv.text = data.nickname
            binding.activitySeminarFreePresentJobTv.text = data.organization
            Glide.with(binding.activitySeminarPresentPreviewProfileImg.context)
                .load(data.profileImgUrl)
                .into(binding.activitySeminarPresentPreviewProfileImg)*/
            binding.item = data

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

    companion object {
        @JvmStatic
        @BindingAdapter("profileImgUrl")
        fun loadImage(imageView: ImageView, imageURL:String){
            Glide.with(imageView.context)
                .load(imageURL)
                .into(imageView)
        }
    }

}
package com.garamgaebi.garamgaebi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.ItemMyprofileCareerBinding
import com.garamgaebi.garamgaebi.model.CareerData
import com.garamgaebi.garamgaebi.src.main.ContainerActivity

class CareerMyRVAdapter(private val dataList: ArrayList<CareerData>,val mContext : Context): RecyclerView.Adapter<CareerMyRVAdapter.ViewHolder>(){

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ItemMyprofileCareerBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: CareerData) {
            binding.item = data
            binding.activityMyprofileCareerListItemIvEdit.setOnClickListener {
                // 경력 편집
                val careerIdx = data.careerIdx
                val editCareerCompany = data.company
                val editCareerPosition = data.position
                val editCareerIsWorking = data.isWorking
                val editCareerStartDate = data.startDate
                val editCareerEndDate = data.endDate
                Log.d("career_edit_button", "success$editCareerEndDate")

                GaramgaebiApplication.sSharedPreferences
                    .edit().putString("CareerCompanyForEdit", editCareerCompany)
                    .putString("CareerPositionForEdit", editCareerPosition)
                    .putString("CareerIsWorkingForEdit", editCareerIsWorking)
                    .putString("CareerStartDateForEdit", editCareerStartDate)
                    .putString("CareerEndDateForEdit", editCareerEndDate)
                    .putInt("CareerIdxForEdit", careerIdx)
                    .apply()

                //SNS 편집 프래그먼트로!
                val intent = Intent(it.context, ContainerActivity::class.java)
                intent.putExtra("careerEdit", true)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMyprofileCareerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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

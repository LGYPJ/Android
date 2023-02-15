package com.garamgaebi.garamgaebi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.ItemMyprofileEduBinding
import com.garamgaebi.garamgaebi.model.EducationData
import com.garamgaebi.garamgaebi.src.main.ContainerActivity


class EduMyRVAdapter(private val dataList: ArrayList<EducationData>,val mContext : Context): RecyclerView.Adapter<EduMyRVAdapter.ViewHolder>(){

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ItemMyprofileEduBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: EducationData) {
            binding.item = data
            binding.activityMyprofileEduListItemIvEdit.setOnClickListener{
                    // 교육 편집
                    val educationIdx = data.educationIdx
                    val editEduInstitution = data.institution
                    val editEduMajor = data.major
                    val editEduIsLearning = data.isLearning
                    val editEduStartDate = data.startDate
                    val editEduEndDate = data.endDate

                    GaramgaebiApplication.sSharedPreferences
                        .edit().putString("EduInstitutionForEdit", editEduInstitution)
                        .putString("EduMajorForEdit", editEduMajor)
                        .putString("EduIsLearningForEdit", editEduIsLearning)
                        .putString("EduStartDateForEdit", editEduStartDate)
                        .putString("EduEndDateForEdit", editEduEndDate)
                        .putInt("EduIdxForEdit", educationIdx)
                        .apply()

                    //교육 편집 프래그먼트로!
                    val intent = Intent(it.context, ContainerActivity::class.java)
                    intent.putExtra("eduEdit", true)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent)
                }

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




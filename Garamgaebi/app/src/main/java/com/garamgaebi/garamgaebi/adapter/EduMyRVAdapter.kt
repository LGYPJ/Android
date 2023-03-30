package com.garamgaebi.garamgaebi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garamgaebi.garamgaebi.common.EDU_EDIT
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.ItemMyprofileEduBinding
import com.garamgaebi.garamgaebi.model.EducationData
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class EduMyRVAdapter(private val dataList: ArrayList<EducationData>,val mContext : Context): RecyclerView.Adapter<EduMyRVAdapter.ViewHolder>(){

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ItemMyprofileEduBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: EducationData) {
            binding.item = data
            if(data.endDate.isNullOrBlank() || data.isLearning.equals("TRUE")){
                binding.activityMyprofileEduListItemTvEndPeriod.text = "현재"
            }
            binding.activityMyprofileEduListItemIvEdit.setOnClickListener {
                // 교육 편집
                val educationIdx = data.educationIdx
                val editEduInstitution = data.institution
                val editEduMajor = data.major
                val editEduIsLearning = data.isLearning
                val editEduStartDate = data.startDate
                var editEduEndDate = data.endDate
                if (data.endDate.isNullOrBlank() || data.isLearning.equals("TRUE")) {
                    editEduEndDate = "현재"
                }
                CoroutineScope(Dispatchers.Main).launch {
                    GaramgaebiApplication().saveStringToDataStore(
                        "EduInstitutionForEdit", editEduInstitution
                    )
                    GaramgaebiApplication().saveStringToDataStore(
                        "EduMajorForEdit", editEduMajor
                    )
                    GaramgaebiApplication().saveStringToDataStore(
                        "EduIsLearningForEdit", editEduIsLearning
                    )
                    GaramgaebiApplication().saveStringToDataStore(
                        "EduStartDateForEdit", editEduStartDate
                    )
                    GaramgaebiApplication().saveStringToDataStore(
                        "EduEndDateForEdit", editEduEndDate
                    )
                    GaramgaebiApplication().saveIntToDataStore(
                        "EduIdxForEdit", educationIdx
                    )
                    //경력 편집 프래그먼트로!
                    val target = Intent(it.context, ContainerActivity::class.java)
                    target.putExtra("openFragment", EDU_EDIT)
                    target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(target)
                }
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




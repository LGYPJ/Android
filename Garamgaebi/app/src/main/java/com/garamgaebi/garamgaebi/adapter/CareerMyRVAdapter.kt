package com.garamgaebi.garamgaebi.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garamgaebi.garamgaebi.common.CAREER_EDIT
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.ItemMyprofileCareerBinding
import com.garamgaebi.garamgaebi.model.CareerData
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CareerMyRVAdapter(private val dataList: ArrayList<CareerData>,val mContext : Context): RecyclerView.Adapter<CareerMyRVAdapter.ViewHolder>(){

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ItemMyprofileCareerBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: CareerData) {
            binding.item = data
            if(data.endDate.isNullOrBlank() || data.isWorking == "TRUE"){
                binding.activityMyprofileCareerListItemTvEndPeriod.text = "현재"
            }
            binding.activityMyprofileCareerListItemIvEdit.setOnClickListener {
                // 경력 편집
                val careerIdx = data.careerIdx
                val editCareerCompany = data.company
                val editCareerPosition = data.position
                val editCareerIsWorking = data.isWorking
                val editCareerStartDate = data.startDate
                var editCareerEndDate = data.endDate
                if(data.endDate.isNullOrBlank() || data.isWorking == "TRUE"){
                    editCareerEndDate = "현재"
                }

                CoroutineScope(Dispatchers.Main).launch {
                    GaramgaebiApplication().saveStringToDataStore(
                        "CareerCompanyForEdit",
                        editCareerCompany
                    )
                    GaramgaebiApplication().saveStringToDataStore(
                        "CareerPositionForEdit",
                        editCareerPosition
                    )
                    GaramgaebiApplication().saveStringToDataStore(
                        "CareerIsWorkingForEdit",
                        editCareerIsWorking
                    )
                    GaramgaebiApplication().saveStringToDataStore(
                        "CareerStartDateForEdit",
                        editCareerStartDate
                    )
                    GaramgaebiApplication().saveStringToDataStore(
                        "CareerEndDateForEdit",
                        editCareerEndDate
                    )
                    GaramgaebiApplication().saveIntToDataStore(
                        "CareerIdxForEdit",
                        careerIdx
                    )
                    //경력 편집 프래그먼트로!
                    val target = Intent(it.context, ContainerActivity::class.java)
                    target.putExtra("openFragment", CAREER_EDIT)
                    target.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(target)
                }
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

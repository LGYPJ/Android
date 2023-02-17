package com.garamgaebi.garamgaebi.adapter

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.ItemMyprofileSnsBinding
import com.garamgaebi.garamgaebi.model.SNSData
import com.garamgaebi.garamgaebi.src.main.ContainerActivity

class SnsMyRVAdapter(private val dataList: ArrayList<SNSData>, val mContext: Context): RecyclerView.Adapter<SnsMyRVAdapter.ViewHolder>(){

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ItemMyprofileSnsBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(data: SNSData) {
            binding.item = data

            binding.activityMyprofileSnsListItemTvLink.setOnClickListener {
                val clipboard = mContext?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // 새로운 ClipData 객체로 데이터 복사하기
                val clip: ClipData =
                    ClipData.newPlainText("sns_address", data.address)

                // 새로운 클립 객체를 클립보드에 배치합니다.
                clipboard.setPrimaryClip(clip)
                Toast.makeText(binding.root.context, "복사 완료", Toast.LENGTH_SHORT).show()

            }

            binding.activityMyprofileSnsListItemIvEdit.setOnClickListener {
                // sns 편집
                val editSNSAddress = data.address
                val editSNSType = data.type
                val editSNSIdx = data.snsIdx

                GaramgaebiApplication.sSharedPreferences
                    .edit().putString("SNSAddressForEdit", editSNSAddress)
                    .putString("SNSTypeForEdit", editSNSType)
                    .putInt("SNSIdxForEdit", editSNSIdx)
                    .apply()


                //SNS 편집 프래그먼트로!
                val intent = Intent(it.context, ContainerActivity::class.java)
                intent.putExtra("snsEdit", true)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemMyprofileSnsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
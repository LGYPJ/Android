package com.garamgaebi.garamgaebi.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.content.*
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.SNS_EDIT
import com.garamgaebi.garamgaebi.databinding.ItemMyprofileSnsBinding
import com.garamgaebi.garamgaebi.model.SNSData
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import kotlinx.coroutines.runBlocking

class SnsMyRVAdapter(private val dataList: ArrayList<SNSData>, val mContext: Context): RecyclerView.Adapter<SnsMyRVAdapter.ViewHolder>(){

    private lateinit var itemClickListener: OnItemClickListener

    inner class ViewHolder(private val binding: ItemMyprofileSnsBinding):
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n", "SuspiciousIndentation")
        fun bind(data: SNSData) {
            binding.item = data

            binding.activityMyprofileSnsListItemTvLink.setOnClickListener {
                val clipboard = mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // 새로운 ClipData 객체로 데이터 복사하기
                val clip: ClipData =
                    ClipData.newPlainText("sns_address", data.address)

                // 새로운 클립 객체를 클립보드에 배치합니다.
                clipboard.setPrimaryClip(clip)

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)
                Toast.makeText(binding.root.context, "복사 완료", Toast.LENGTH_SHORT).show()

                if(data.type == "인스타그램") {
                    var instaLink = ""
                    if(data.address[0] =='@'){
                        instaLink = data.address.substring(1)
                    }else{
                        instaLink = data.address
                    }
                    val uri = Uri.parse("https://www.instagram.com/${instaLink}/")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    intent.setPackage("com.instagram.android")
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                    try {
                        val activityContext = mContext as Activity
                        activityContext.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        mContext.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("https://www.instagram.com/${data.address}/")
                            )
                        )
                    }
                }else{
                    val uri = Uri.parse("${data.address}")
                    val intent = Intent(Intent.ACTION_VIEW, uri)
                    intent.setPackage("com.chrome.android")
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

                    try {
                        val activityContext = mContext as Activity
                        activityContext.startActivity(intent)
                    } catch (e: ActivityNotFoundException) {
                        val activityContext = mContext as Activity
                        activityContext.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                Uri.parse("${data.address}")
                            )
                        )
                    }
                }

            }

            binding.activityMyprofileSnsListItemIvEdit.setOnClickListener {
                // sns 편집
                val editSNSAddress = data.address
                val editSNSType = data.type
                val editSNSIdx = data.snsIdx

                runBlocking {
                    GaramgaebiApplication().saveStringToDataStore("SNSAddressForEdit",editSNSAddress)
                    GaramgaebiApplication().saveStringToDataStore("SNSTypeForEdit",editSNSType)
                    GaramgaebiApplication().saveIntToDataStore("SNSIdxForEdit",editSNSIdx)
                }

                //SNS 편집 프래그먼트로!
                val intent = Intent(it.context, ContainerActivity::class.java)
                intent.putExtra("openFragment", SNS_EDIT)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
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
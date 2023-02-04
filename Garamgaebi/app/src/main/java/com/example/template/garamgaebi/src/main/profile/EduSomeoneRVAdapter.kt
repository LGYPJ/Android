package com.example.template.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.util.SparseBooleanArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.template.garamgaebi.databinding.ItemSomeoneprofileEduBinding

class EduSomeoneRVAdapter(private val dataList: ArrayList<EduRVItemData>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private val checkRead = SparseBooleanArray()
    var canRemove : Boolean = true

    interface OnItemClickListener {
        fun onItemClick(position: Int)
        fun onLongClick(position: Int)
    }
    fun setItemClickListener(param: Any) {}

    private lateinit var mItemClickListener: OnItemClickListener
    fun setMyItemClickListener(itemClickListener: OnItemClickListener){
        mItemClickListener = itemClickListener
    }

    //viewHolder 객체
    inner class DataViewHolder(private val viewBinding: ItemSomeoneprofileEduBinding): RecyclerView.ViewHolder(viewBinding.root){

        init {
            itemView.setOnClickListener {
                mItemClickListener.onItemClick(adapterPosition)
            }
            itemView.setOnLongClickListener{
                mItemClickListener.onLongClick(adapterPosition)
                return@setOnLongClickListener true
            }
        }
        @SuppressLint("ResourceAsColor", "SuspiciousIndentation")
        fun bind(data: EduRVItemData) {
            viewBinding.activityMyprofileEduListItemTvName.text = data.title
            viewBinding.activityMyprofileEduListItemTvContent.text = data.position
            viewBinding.activityMyprofileEduListItemTvStartPeriod.text = data.start
            viewBinding.activityMyprofileEduListItemTvEndPeriod.text = data.end
        }
    }

//    override fun getItemViewType(position: Int): Int {
//        return dataList[position].type
//    }

    //viewHolder 만들어질때 실행할 동작들
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val Binding = ItemSomeoneprofileEduBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)

        return   DataViewHolder(Binding)

    }

    //viewHolder가 실제로 데이터를 표시해야할 때 호출되는 함수
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as DataViewHolder).bind(dataList[position])
        //holder.setIsRecyclable(false)


    }
    //표현할 item의 총 개수
    override fun getItemCount(): Int = dataList.size

    fun addItem(data : EduRVItemData) {
        dataList.add(data);
    }

    fun getItem(position : Int): EduRVItemData {
        return dataList.get(position);
    }

    fun addUserItems(data: EduRVItemData){
        dataList.add(data)
        notifyItemInserted(getItemCount()-1)
    }

}

//package com.softsquared.template.Garamgaebi.src.myProfile
//
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.BaseAdapter
//import com.softsquared.template.Garamgaebi.R
//
//class CareerListViewAdapter(private val items: MutableList<CareerListViewItem>): BaseAdapter() {
//
//    override fun getCount(): Int = items.size
//
//    override fun getItem(position: Int): CareerListViewItem = items[position]
//
//    override fun getItemId(position: Int): Long = position.toLong()
//
//    override fun getView(position: Int, view: View?, parent: ViewGroup?): View {
//        var convertView = view
//        if (convertView == null) convertView = LayoutInflater.from(parent?.context).inflate(R.layout.activity_myprofile_career_list_item, parent, false)
//
//        val item: CareerListViewItem = items[position]
//        convertView!!.image_title.setImageDrawable(item.icon)
//        convertView.text_title.text = item.title
//        convertView.text_sub_title.text = item.subTitle
//
//        return convertView
//    }
//}
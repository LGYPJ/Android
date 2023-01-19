package com.softsquared.template.Garamgaebi.src.main.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.softsquared.template.Garamgaebi.databinding.ActivitySomeoneprofileEduListItemBinding

class EduSomeoneListViewAdapter(private val context: Context, private val items: MutableList<EduListViewItem>): BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): EduListViewItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ActivitySomeoneprofileEduListItemBinding.inflate(LayoutInflater.from(context))

        val show = items[position]
        binding.activityMyprofileEduListItemTvName.text = show.title
        binding.activityMyprofileEduListItemTvContent.text = show.position
        binding.activityMyprofileEduListItemTvStartPeriod.text = show.start
        binding.activityMyprofileEduListItemTvEndPeriod.text = show.end

        return binding.root
    }
}
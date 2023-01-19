package com.softsquared.template.Garamgaebi.src.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.databinding.ActivitySomeoneprofileCareerListItemBinding
import com.softsquared.template.Garamgaebi.databinding.FragmentMyprofileCareerListItemBinding

class CareerSomeoneListViewAdapter(private val context: Context, private val items: MutableList<CareerListViewItem>): BaseAdapter() {
    override fun getCount(): Int = items.size

    override fun getItem(position: Int): CareerListViewItem = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val binding = ActivitySomeoneprofileCareerListItemBinding.inflate(LayoutInflater.from(context))

        val show = items[position]
        binding.activityMyprofileCareerListItemTvName.text = show.title
        binding.activityMyprofileCareerListItemTvContent.text = show.position
        binding.activityMyprofileCareerListItemTvStartPeriod.text = show.start
        binding.activityMyprofileCareerListItemTvEndPeriod.text = show.end

        return binding.root
    }
}
package com.softsquared.template.Garamgaebi.src.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.databinding.ActivityMyprofileSnsListItemBinding
import com.softsquared.template.Garamgaebi.databinding.ActivitySomeoneprofileSnsListItemBinding

class SnsSomeoneListViewAdapter(private val context: Context, private val items: MutableList<SnsListViewItem>): BaseAdapter() {
        override fun getCount(): Int = items.size

        override fun getItem(position: Int): SnsListViewItem = items[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val binding = ActivitySomeoneprofileSnsListItemBinding.inflate(LayoutInflater.from(context))

            val show = items[position]
            binding.activitySomeoneprofileSnsListItemTvName.text = show.snsAddress
            return binding.root
        }
}
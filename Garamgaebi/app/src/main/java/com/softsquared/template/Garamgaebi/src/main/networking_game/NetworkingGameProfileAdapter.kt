package com.softsquared.template.Garamgaebi.src.main.networking_game

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.Garamgaebi.databinding.ItemNetworkGamePlaceProfileBinding

class NetworkingGameProfileAdapter(private val dataList: ArrayList<NetworkingGameProfile>, private val isBordered: Boolean): RecyclerView.Adapter<NetworkingGameProfileAdapter.ViewHolder>() {


    inner class ViewHolder(val binding: ItemNetworkGamePlaceProfileBinding):
            RecyclerView.ViewHolder(binding.root) {
                @SuppressLint("SuspiciousIndentation")
                fun bind(data: NetworkingGameProfile, isBordered: Boolean = false){
                    binding.itemNetworkGameProfileImg.setImageResource(data.img)
                    binding.itemNetworkGameProfileNameTv.text = data.name

                    for(i in 0..dataList.size) {
                        if(adapterPosition == i) {
                            if (!isBordered) {
                                binding.itemNetworkGameProfileBorder.visibility = INVISIBLE
                            } else {
                                binding.itemNetworkGameProfileBorder.visibility = VISIBLE
                            }
                        }
                        else {
                            if (!isBordered) {
                                binding.itemNetworkGameProfileBorder.visibility = INVISIBLE
                            } else {
                                binding.itemNetworkGameProfileBorder.visibility = VISIBLE
                            }
                        }
                    }


                }

            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemNetworkGamePlaceProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], isBordered)

        /*if(position == 0) run {
            isBordered = false
        }*/

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}
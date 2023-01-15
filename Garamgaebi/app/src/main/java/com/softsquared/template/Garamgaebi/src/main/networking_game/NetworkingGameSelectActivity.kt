package com.softsquared.template.Garamgaebi.src.main.networking_game


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityNetworkingGameSelectBinding

class NetworkingGameSelectActivity : BaseActivity<ActivityNetworkingGameSelectBinding>(ActivityNetworkingGameSelectBinding::inflate) {


    private var networkGameSelectList: ArrayList<NetworkingGameSelect> = arrayListOf(
        NetworkingGameSelect("가천관"),
        NetworkingGameSelect("AI 공학관"),
        NetworkingGameSelect("비전타워"),
        NetworkingGameSelect("가천관"),
        NetworkingGameSelect("제3기숙사"),
        NetworkingGameSelect("가천관"),
        NetworkingGameSelect("글로벌 센터"),
        NetworkingGameSelect("가천관")

    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkingGameSelectAdapter = NetworkingGameSelectAdapter(networkGameSelectList)
        binding.activityNetworkGameRv.apply {
            adapter = networkingGameSelectAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(NetworkingGameSelectVerticalItemDecoration())

        }

    }
}
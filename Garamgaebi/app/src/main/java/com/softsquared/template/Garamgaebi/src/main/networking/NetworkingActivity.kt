package com.softsquared.template.Garamgaebi.src.main.networking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityNetworkingBinding

class NetworkingActivity : BaseActivity<ActivityNetworkingBinding>(ActivityNetworkingBinding::inflate) {

    private var networkProfileList: ArrayList<NetworkingProfile> = arrayListOf(
        NetworkingProfile(R.drawable.ic_network_profile1, "신디"),
        NetworkingProfile(R.drawable.ic_network_profile2, "짱구"),
        NetworkingProfile(R.drawable.ic_network_profile3, "로건"),
        NetworkingProfile(R.drawable.ic_network_profile1, "찹도"),
        NetworkingProfile(R.drawable.ic_network_profile2, "네온"),
        NetworkingProfile(R.drawable.ic_network_profile3, "코코아"),
        NetworkingProfile(R.drawable.ic_network_profile1, "연현"),
        NetworkingProfile(R.drawable.ic_network_profile2, "승콩")
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkingProfile = NetworkingProfileAdapter(networkProfileList)
        binding.activityNetworkProfileRv.apply {
            adapter = networkingProfile
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(NetworkingHorizontalItemDecoration())
        }
    }
}
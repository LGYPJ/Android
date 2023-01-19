package com.softsquared.template.Garamgaebi.src.main.networking

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityNetworkingBinding
import com.softsquared.template.Garamgaebi.src.main.networking_game.NetworkingGameSelectActivity
import com.softsquared.template.Garamgaebi.src.seminar.multi_type1
import com.softsquared.template.Garamgaebi.src.seminar.multi_type2
import com.softsquared.template.Garamgaebi.src.seminar.multi_type3

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
        NetworkingProfile(R.drawable.activity_seminar_profile_img_blue, "신디", multi_type2),
        NetworkingProfile(R.drawable.ic_network_profile2, "짱구", multi_type1),
        NetworkingProfile(R.drawable.ic_network_profile3, "로건", multi_type1),
        NetworkingProfile(R.drawable.activity_seminar_profile_gray, "알수없음", multi_type3),
        NetworkingProfile(R.drawable.ic_network_profile2, "네온", multi_type1),
        NetworkingProfile(R.drawable.ic_network_profile3, "코코아", multi_type1),
        NetworkingProfile(R.drawable.ic_network_profile1, "연현", multi_type1),
        NetworkingProfile(R.drawable.ic_network_profile2, "승콩", multi_type1)
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val networkingProfile = NetworkingProfileAdapter(networkProfileList)
        binding.activityNetworkProfileRv.apply {
            adapter = networkingProfile
            layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(NetworkingHorizontalItemDecoration())
        }

        //신청하기 버튼 누르면 네트워킹 신청 화면으로
        binding.activityNetworkApplyBtn.setOnClickListener {
            val intent = Intent(this@NetworkingActivity, NetworkingFreeApplyActivity::class.java)
            startActivity(intent)
        }

        //참가하기 버튼 누르면 네트워킹 게임 화면으로
        binding.activityNetworkParticipateBtn.setOnClickListener {
            val intent = Intent(this@NetworkingActivity, NetworkingGameSelectActivity::class.java)
            startActivity(intent)
        }
    }

    //신청하기 누르면 버튼 바꾸는!!
    override fun onStart() {
        super.onStart()
        var apply = intent.getBooleanExtra("apply", false)
        if(apply){
            val apply_tv = "신청완료"
            binding.activityNetworkApplyBtn.setText(apply_tv)
            binding.activityNetworkApplyBtn.setTextColor(getColor(R.color.seminar_blue))
            binding.activityNetworkApplyBtn.setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
        }
    }

}
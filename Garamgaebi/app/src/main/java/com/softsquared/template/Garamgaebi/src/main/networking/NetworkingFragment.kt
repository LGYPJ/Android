package com.softsquared.template.Garamgaebi.src.main.networking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentNetworkingBinding
import com.softsquared.template.Garamgaebi.databinding.FragmentSeminarBinding
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import com.softsquared.template.Garamgaebi.src.main.networking_game.NetworkingGameSelectActivity
import com.softsquared.template.Garamgaebi.src.seminar.multi_type1
import com.softsquared.template.Garamgaebi.src.seminar.multi_type2
import com.softsquared.template.Garamgaebi.src.seminar.multi_type3

class NetworkingFragment: BaseFragment<FragmentNetworkingBinding>(FragmentNetworkingBinding::bind, R.layout.fragment_networking) {

    private var networkProfileList: ArrayList<NetworkingProfile> = arrayListOf(
        NetworkingProfile(R.drawable.activity_seminar_profile_img_blue, "신디", multi_type2),
        NetworkingProfile(R.drawable.ic_network_profile2, "짱구", multi_type1),
        NetworkingProfile(R.drawable.ic_network_profile3, "로건", multi_type1),
        NetworkingProfile(R.drawable.activity_seminar_profile_gray, "알수없음", multi_type3),
        NetworkingProfile(R.drawable.ic_network_profile2, "네온", multi_type1),
        NetworkingProfile(R.drawable.ic_network_profile3, "코코아", multi_type1),
        NetworkingProfile(R.drawable.ic_network_profile1, "연현", multi_type1),
        NetworkingProfile(R.drawable.ic_network_profile2, "승콩", multi_type1)
    )

    //화면전환
    var containerActivity: ContainerActivity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val networkingProfile = NetworkingProfileAdapter(networkProfileList)
        binding.activityNetworkProfileRv.apply {
            adapter = networkingProfile
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(NetworkingHorizontalItemDecoration())
        }

        //신청하기 버튼 누르면 네트워킹 신청 화면으로
        binding.activityNetworkApplyBtn.setOnClickListener {
            containerActivity!!.openFragmentOnFrameLayout(6)
        }

        //참가하기 버튼 누르면 네트워킹 게임 화면으로
        binding.activityNetworkParticipateBtn.setOnClickListener {
            containerActivity!!.openFragmentOnFrameLayout(7)
        }

    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

    //신청하기 누르면 버튼 바꾸는!!
    override fun onStart() {
        super.onStart()
        val apply = arguments?.getBoolean("networking", false)
        Log.d("networking", apply.toString())
        if(apply !== null) {
            val apply_tv = "신청완료"
            binding.activityNetworkApplyBtn.text = apply_tv
            binding.activityNetworkApplyBtn.setTextColor(resources.getColor(R.color.seminar_blue))
            binding.activityNetworkApplyBtn.setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
        }

    }
}
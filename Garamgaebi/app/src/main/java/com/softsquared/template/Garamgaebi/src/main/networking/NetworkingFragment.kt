package com.softsquared.template.Garamgaebi.src.main.networking

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentNetworkingBinding
import com.softsquared.template.Garamgaebi.model.NetworkingParticipantsResult
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import com.softsquared.template.Garamgaebi.src.main.seminar.multi_type1
import com.softsquared.template.Garamgaebi.src.main.seminar.multi_type2
import com.softsquared.template.Garamgaebi.src.main.seminar.multi_type3
import com.softsquared.template.Garamgaebi.viewModel.NetworkingViewModel

class NetworkingFragment: BaseFragment<FragmentNetworkingBinding>(FragmentNetworkingBinding::bind, R.layout.fragment_networking) {

    //private lateinit var viewModel: ItemViewModel

    /*private var networkProfileList: ArrayList<NetworkingProfile> = arrayListOf(
        NetworkingProfile(R.drawable.activity_seminar_profile_img_blue, "신디", multi_type2),
        NetworkingProfile(R.drawable.ic_network_profile2, "짱구", multi_type1),
        NetworkingProfile(R.drawable.ic_network_profile3, "로건", multi_type1),
        NetworkingProfile(R.drawable.activity_seminar_profile_gray, "알수없음", multi_type3),
        NetworkingProfile(R.drawable.ic_network_profile2, "네온", multi_type1),
        NetworkingProfile(R.drawable.ic_network_profile3, "코코아", multi_type1),
        NetworkingProfile(R.drawable.ic_network_profile1, "연현", multi_type1),
        NetworkingProfile(R.drawable.ic_network_profile2, "승콩", multi_type1)
    )*/

    //화면전환
    var containerActivity: ContainerActivity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뷰모델
        val viewModel = ViewModelProvider(this)[NetworkingViewModel::class.java]

        //프로필 어댑터 연결
        viewModel.getNetworkingParticipants(1)
        viewModel.networkingParticipants.observe(viewLifecycleOwner, Observer {
            val networkingProfile = NetworkingProfileAdapter(it.result as ArrayList<NetworkingParticipantsResult>)
            binding.activityNetworkProfileRv.apply {
                adapter = networkingProfile
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(NetworkingHorizontalItemDecoration())
            }
        })

        //네트워킹 상세정보
        /*viewModel.getNetworkingInfo(0,0)
        viewModel.networkingInfo.observe(viewLifecycleOwner, Observer {
            val item = it.result
            binding.activityNetworkTitleTv.text = item.title
            binding.activityNetworkDateDetailTv.text = item.date
            binding.activityNetworkPlaceDetailTv.text = item.location
            binding.activityNetworkPayDetailTv.text = item.fee.toString()
            binding.activityNetworkDeadlineDetailTv.text = item.endDate
            //버튼 상태 추가
            if (item.userButtonStatus == "ApplyComplete") {
                binding.activityNetworkApplyBtn.text = "신청완료"
                binding.activityNetworkApplyBtn.setTextColor(resources.getColor(R.color.seminar_blue))
                binding.activityNetworkApplyBtn.setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
            }
        })*/

        //신청하기 버튼 누르면 네트워킹 신청 화면으로
        binding.activityNetworkApplyBtn.setOnClickListener {
            containerActivity!!.openFragmentOnFrameLayout(6)
        }

        //viewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        //참가하기 버튼 누르면 네트워킹 게임 화면으로
        binding.activityNetworkParticipateBtn.setOnClickListener {
            //viewModel.selectItem("아이스브레이킹")
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
        //버튼 눌렀을때 바뀌는 거
            /*val apply_tv = "신청완료"
            binding.activityNetworkApplyBtn.text = apply_tv
            binding.activityNetworkApplyBtn.setTextColor(resources.getColor(R.color.seminar_blue))
            binding.activityNetworkApplyBtn.setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)*/

    }
}
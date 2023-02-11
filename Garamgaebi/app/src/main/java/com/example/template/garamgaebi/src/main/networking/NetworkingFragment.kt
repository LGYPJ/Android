package com.example.template.garamgaebi.src.main.networking

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.adapter.NetworkingProfileAdapter

import com.example.template.garamgaebi.common.BaseFragment

import com.example.template.garamgaebi.databinding.FragmentNetworkingBinding
import com.example.template.garamgaebi.model.HomeSeminarResult
import com.example.template.garamgaebi.model.NetworkingParticipantsResult
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.viewModel.NetworkingViewModel
import com.example.template.garamgaebi.viewModel.SeminarViewModel

class NetworkingFragment: BaseFragment<FragmentNetworkingBinding>(FragmentNetworkingBinding::bind ,R.layout.fragment_networking) {

    //화면전환
    var containerActivity: ContainerActivity? = null
    private val viewModel by viewModels<NetworkingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //프로필 어댑터 연결
        viewModel.getNetworkingParticipants()
        viewModel.networkingParticipants.observe(viewLifecycleOwner, Observer {
            val networkingProfile = NetworkingProfileAdapter(it.result as ArrayList<NetworkingParticipantsResult>)
            //참석자가 없을 경우 다른 뷰 노출
            if(it.result.isEmpty()){
                binding.activityNetworkingNoParticipants.visibility = VISIBLE
                binding.activityNetworkProfileRv.visibility = GONE

            } else{
                binding.activityNetworkingNoParticipants.visibility = GONE
                binding.activityNetworkProfileRv.visibility = VISIBLE
                binding.activityNetworkProfileRv.apply {
                    adapter = networkingProfile
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    //addItemDecoration(NetworkingHorizontalItemDecoration())
                }
            }
        })

        //네트워킹 상세정보
        viewModel.getNetworkingInfo()
        viewModel.networkingInfo.observe(viewLifecycleOwner, Observer {
            val item = it.result
            binding.activityNetworkTitleTv.text = item.title
            binding.activityNetworkDateDetailTv.text = item.date
            binding.activityNetworkPlaceDetailTv.text = item.location
            if(item.fee.toString() == "0"){
                binding.activityNetworkPayDetailTv.text = "무료"
            }
            binding.activityNetworkDeadlineDetailTv.text = item.endDate
            //버튼 상태 추가
            /*if (item.userButtonStatus == "ApplyComplete") {
                binding.activityNetworkApplyBtn.text = "신청완료"
                binding.activityNetworkApplyBtn.setTextColor(resources.getColor(R.color.seminar_blue))
                binding.activityNetworkApplyBtn.setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
            }*/
        })

        //신청하기 버튼 누르면 네트워킹 신청 화면으로
        binding.activityNetworkApplyBtn.setOnClickListener {
            containerActivity!!.openFragmentOnFrameLayout(6)
        }

        //참가하기 버튼 누르면 네트워킹 게임 화면으로
        binding.activityNetworkParticipateBtn.setOnClickListener {
            //viewModel.selectItem("아이스브레이킹")
            containerActivity!!.openFragmentOnFrameLayout(7)
            val temp = "아이스브레이킹"
            containerActivity!!.iceBreaking(temp)
        }

    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

}
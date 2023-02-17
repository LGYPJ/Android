package com.garamgaebi.garamgaebi.src.main.networking

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.NetworkingProfileAdapter

import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction

import com.garamgaebi.garamgaebi.databinding.FragmentNetworkingBinding
import com.garamgaebi.garamgaebi.model.NetworkingResult
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.NetworkingViewModel

class NetworkingFragment: BaseFragment<FragmentNetworkingBinding>(FragmentNetworkingBinding::bind ,R.layout.fragment_networking) {

    //화면전환
    var containerActivity: ContainerActivity? = null
    private val viewModel by viewModels<NetworkingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //프로필 어댑터 연결
        viewModel.getNetworkingParticipants()
        viewModel.networkingParticipants.observe(viewLifecycleOwner, Observer {
            val networkingProfile = NetworkingProfileAdapter(it as ArrayList<NetworkingResult>)
            //참석자가 없을 경우 다른 뷰 노출
            if(it.isEmpty()){
                binding.activityNetworkingNoParticipants.visibility = VISIBLE
                binding.activityNetworkProfileRv.visibility = GONE
                //참석자 수 표시
                binding.activityNetworkParticipantNumberTv.text = getString(R.string.main_participants, "0")
            } else{
                binding.activityNetworkingNoParticipants.visibility = GONE
                binding.activityNetworkProfileRv.visibility = VISIBLE
                //참석자 수 표시
                binding.activityNetworkParticipantNumberTv.text = getString(R.string.main_participants, it.size.toString())
                binding.activityNetworkProfileRv.apply {
                    adapter = networkingProfile
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    //addItemDecoration(NetworkingHorizontalItemDecoration())
                }
                //리사이클러뷰 클릭 이벤트
                networkingProfile.setOnItemClickListener(object :
                NetworkingProfileAdapter.OnItemClickListener{
                    override fun onClick(position: Int) {
                        //상대방 프로필 프래그먼트로
                        if(position ==0 && it[0].memberIdx != GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0)){
                            containerActivity!!.openFragmentOnFrameLayout(13)
                        }
                        if(position != 0){
                            containerActivity!!.openFragmentOnFrameLayout(13)
                        }
                    }
                })
            }
        })

        // 아이스브레이킹 버튼 참여 활성화
        viewModel.networkingActive.observe(viewLifecycleOwner, Observer{
            val startDate = GaramgaebiApplication.sSharedPreferences.getString("startDate", null)
                              //현재 시간과 stratDate 비교 --> 같다면 true로 반환
            if(it.isApply && startDate?.let { it1 -> GaramgaebiFunction().checkIceBreaking(it1) } == true){
                //버튼 활성화 & 멘트 바꾸기
                with(binding){
                    activityNetworkIcebreakingContent1Tv.text = getString(R.string.networking_icebreaking_active1)
                    activityNetworkIcebreakingContent2Tv.text = getString(R.string.networking_icebreaking_active2)
                    activityNetworkParticipateBtn.isEnabled = true
                    activityNetworkParticipateTv.setTextColor(resources.getColor(R.color.white))
                    activityNetworkParticipateImg.setBackgroundResource(R.drawable.activity_network_participate_btn_white)
                    activityNetworkParticipateBtn.setBackgroundResource(R.drawable.networking_blue_join_btn_background)
                }
            }else{
                binding.activityNetworkParticipateBtn.isEnabled = true
            }
        })

        //네트워킹 상세정보
        viewModel.getNetworkingInfo()
        viewModel.networkingInfo.observe(viewLifecycleOwner, Observer {
            // 시작 date변환 -> 저장
            val item = it.result
            with(binding) {
                activityNetworkTitleTv.text = item.title
                activityNetworkDateDetailTv.text = item.date
                activityNetworkPlaceDetailTv.text = item.location
            }
            if(item.fee.toString() == "0"){
                binding.activityNetworkPayDetailTv.text = "무료"
            }
            else{
                binding.activityNetworkPayDetailTv.text = getString(R.string.main_fee, item.fee.toString())
            }
            binding.activityNetworkDeadlineDetailTv.text = item.endDate

            //무료
            if(it.result.fee == 0) {
                // 버튼 상태
                if(it.result.userButtonStatus == "APPLY_COMPLETE"){
                    //신청완료, 비활성화
                    with(binding) {
                        activityNetworkApplyBtn.text = "신청완료"
                        activityNetworkApplyBtn.setTextColor(resources.getColor(R.color.seminar_blue))
                        activityNetworkApplyBtn.setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
                        activityNetworkApplyBtn.isEnabled = false
                    }
                }
                if(it.result.userButtonStatus == "CLOSED"){
                    //마감, 비활성화
                    with(binding) {
                        activityNetworkApplyBtn.text = "마감"
                        activityNetworkApplyBtn.setTextColor(resources.getColor(R.color.gray8a))
                        activityNetworkApplyBtn.setBackgroundResource(R.drawable.activity_userbutton_closed_gray)
                        activityNetworkApplyBtn.isEnabled = false
                    }
                }
                if(it.result.userButtonStatus == "APPLY"){
                    // 신청하기 활성화
                    with(binding) {
                        activityNetworkApplyBtn.text = "신청하기"
                        activityNetworkApplyBtn.setTextColor(resources.getColor(R.color.white))
                        activityNetworkApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                        activityNetworkApplyBtn.isEnabled = true
                    }
                }
            }
            //유료
            else {
                // 버튼 상태
                if(it.result.userButtonStatus == "BEFORE_APPLY_CONFIRM"){
                    //신청확인중, 비활성화
                    with(binding){
                        activityNetworkApplyBtn.text = "신청확인중"
                        activityNetworkApplyBtn.setTextColor(resources.getColor(R.color.seminar_blue))
                        activityNetworkApplyBtn.setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
                        activityNetworkApplyBtn.isEnabled = false
                    }
                }
                if(it.result.userButtonStatus == "APPLY_COMPLETE"){
                    //신청완료, 비활성화
                    with(binding){
                        activityNetworkApplyBtn.text = "신청완료"
                        activityNetworkApplyBtn.setTextColor(resources.getColor(R.color.seminar_blue))
                        activityNetworkApplyBtn.setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
                        activityNetworkApplyBtn.isEnabled = false
                    }
                }
                if(it.result.userButtonStatus == "CLOSED"){
                    //마감, 비활성화
                    with(binding){
                        activityNetworkApplyBtn.text = "마감"
                        activityNetworkApplyBtn.setTextColor(resources.getColor(R.color.gray8a))
                        activityNetworkApplyBtn.setBackgroundResource(R.drawable.activity_userbutton_closed_gray)
                        activityNetworkApplyBtn.isEnabled = false
                    }
                }
                if(it.result.userButtonStatus == "APPLY"){
                    // 신청하기 활성화
                    with(binding){
                        activityNetworkApplyBtn.text = "신청하기"
                        activityNetworkApplyBtn.setTextColor(resources.getColor(R.color.white))
                        activityNetworkApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                        activityNetworkApplyBtn.isEnabled = true
                    }
                }

            }
        })

        //신청하기 버튼 누르면 네트워킹 신청 화면으로
        binding.activityNetworkApplyBtn.setOnClickListener {
            val pay = binding.activityNetworkPayDetailTv.text
            if(pay == "무료") {
                containerActivity!!.openFragmentOnFrameLayout(6)
            }
            else {
                containerActivity!!.openFragmentOnFrameLayout(20)
            }
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
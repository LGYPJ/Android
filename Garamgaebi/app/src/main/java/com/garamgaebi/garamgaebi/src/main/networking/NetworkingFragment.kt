package com.garamgaebi.garamgaebi.src.main.networking

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.networkValid
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction

import com.garamgaebi.garamgaebi.databinding.FragmentNetworkingBinding
import com.garamgaebi.garamgaebi.model.NetworkingResult
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.NetworkingViewModel
import kotlinx.coroutines.*

/**
 < NetworkingFragment >
  화면 기능 : 네트웨킹 일시, 장소, 참가비, 신청마감 , 참석자 상세 정보 보여줌

  신청하기 버튼 클릭 -> 네트워킹 신청 무료 or 유료 화면으로 이동 ( NetworkingChargedApplyFragment or NetworkingFreeApplyFragment)
  참가하기 버튼 클릭 -> 아이스브레이킹 장소 선택 화면으로 이동 (NetworkingGameSelectFragment)
                  -> NetworkingViewModel getNetworkingParticipants()의 networkingActive에서 아이스브레이킹 시작 시간을 가져와
                     현재 시간 startDate와 비교하여 두 값이 같아질때 참가하기 버튼 활성화 & 아이스브레이킹 뷰 달라지게


  참석자 프로틸 사진 클릭 -> 다른 사람 프로필 상세보기 화면으로 이동 ( UserProfileFragment )
  자신 프로필 사진 클릭 -> 다른 화면으로 이동 X (참석자 리스트 index 0의 memberIdx와 dataStore 저장 되어 있는 memberIdx 가 같을때)


   신청 & 마감 여부에 따른 신청하기 버튼 & 참석자 뷰
   -> NetworkingViewModel getNetworkingParticipants() networkingParticipants의 List<NetworkingParticipantsResult>가 비었을때 (it.isEmpty) 참석자 뷰 다르게
   -> NetworkingViewModel getNetworkingInfo() networkingInfo의 userButtonStatus에 따라 신청하기 버튼 뷰 다르게
      무료 -> 신청완료(APPLY_COMPLETE), 비활성화 / 마감(CLOSED), 비활성화 / 신청하기(APPLY) 활성화
      유료 -> 신청확인중(BEFORE_APPLY_CONFIRM), 비활성화 / 신청완료(APPLY_COMPLETE), 비활성화 / 마감(CLOSED), 비활성화 / 신청하기(APPLY), 활성화
 */
class NetworkingFragment: BaseFragment<FragmentNetworkingBinding>(FragmentNetworkingBinding::bind ,R.layout.fragment_networking) {

    //화면전환
    var containerActivity: ContainerActivity? = null
    private val viewModel by viewModels<NetworkingViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(networkValid.value == true) {
            setDataView()
        }

        with(binding) {
            activityNetworkProfileRv.addItemDecoration(NetworkingHorizontalItemDecoration())
        }
        //신청하기 버튼 누르면 네트워킹 신청 화면으로
        binding.activityNetworkApplyBtn.setOnClickListener {
            val pay = binding.activityNetworkPayDetailTv.text
            val free = getString(R.string.origin_pay)
            if(pay == free) {
                containerActivity!!.openFragmentOnFrameLayout(6)
            }
            else {
                containerActivity!!.openFragmentOnFrameLayout(20)
            }
        }

        //참가하기 버튼 누르면 네트워킹 게임 화면으로
        binding.activityNetworkParticipateBtn.setOnClickListener {
            containerActivity!!.openFragmentOnFrameLayout(7)
        }

    }
    @OptIn(DelicateCoroutinesApi::class)
    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            if (networkValid.value == true)
                updateData()
            else {
                networkValid.postValue(false)
            }
        }
    }

    private fun setDataView() {
        with(viewModel) {
            if (networkValid.value == true) {
                getNetworkingParticipants()
            } else {
            }

            networkingParticipants.observe(viewLifecycleOwner, Observer {

                val networkingProfile =
                    NetworkingProfileAdapter(it as ArrayList<NetworkingResult>)
                //참석자가 없을 경우 다른 뷰 노출
                if (it.isEmpty()) {
                    binding.activityNetworkingNoParticipants.visibility = VISIBLE
                    binding.activityNetworkProfileRv.visibility = GONE
                    //참석자 수 표시
                    binding.activityNetworkParticipantNumberTv.text =
                        getString(R.string.main_participants, "0")
                } else {
                    binding.activityNetworkingNoParticipants.visibility = GONE
                    binding.activityNetworkProfileRv.visibility = VISIBLE
                    //참석자 수 표시
                    binding.activityNetworkParticipantNumberTv.text =
                        getString(R.string.main_participants, it.size.toString())
                    binding.activityNetworkProfileRv.apply {
                        adapter = networkingProfile
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }
                    //리사이클러뷰 클릭 이벤트
                    networkingProfile.setOnItemClickListener(object :
                        NetworkingProfileAdapter.OnItemClickListener {
                        override fun onClick(position: Int) {
                            var memberIdxCheck = 0
                            val putData = runBlocking {
                                memberIdxCheck =
                                    GaramgaebiApplication().loadIntData("memberIdx")!!
                            }
                            //상대방 프로필 프래그먼트로
                            if (position == 0 && it[0].memberIdx == memberIdxCheck) {
                                //이동 x

                            } else {
                                val putData = runBlocking {
                                    GaramgaebiApplication().saveIntToDataStore(
                                        "userMemberIdx",
                                        it[position].memberIdx
                                    )!!
                                    containerActivity!!.openFragmentOnFrameLayout(13)
                                    containerActivity!!.goUser()
                                }
                            }
                        }
                    })
                }
            })

            networkingActive.observe(viewLifecycleOwner, Observer {
                var startDate = ""
                val putData = runBlocking {
                    startDate = GaramgaebiApplication().loadStringData("startDate").toString()
                }
                //현재 시간과 stratDate 비교 --> 같다면 true로 반환
                if (it.isApply && startDate.let { it1 ->
                        GaramgaebiFunction().checkIceBreaking(
                            it1
                        )
                    }) {
                    //버튼 활성화 & 멘트 바꾸기
                    with(binding) {
                        activityNetworkIcebreakingContent1Tv.text =
                            getString(R.string.networking_icebreaking_active1)
                        activityNetworkIcebreakingContent2Tv.text =
                            getString(R.string.networking_icebreaking_active2)
                        activityNetworkParticipateBtn.isEnabled = true
                        activityNetworkParticipateTv.setTextColor(resources.getColor(R.color.white))
                        activityNetworkParticipateImg.setBackgroundResource(R.drawable.activity_network_participate_btn_white)
                        activityNetworkParticipateBtn.setBackgroundResource(R.drawable.networking_blue_join_btn_background)
                    }
                } else {
                    binding.activityNetworkParticipateBtn.isEnabled = false
                }
            })

            //네트워킹 상세정보

            networkingInfo.observe(viewLifecycleOwner, Observer {
                // 시작 date변환 -> 저장
                val item = it.result
                with(binding) {
                    activityNetworkTitleTv.text = item.title
                    activityNetworkDateDetailTv.text = item.date
                    activityNetworkPlaceDetailTv.text = item.location
                }
                if (item.fee.toString() == "0") {
                    binding.activityNetworkPayDetailTv.text = getString(R.string.origin_pay)
                } else {
                    binding.activityNetworkPayDetailTv.text =
                        getString(R.string.main_fee, item.fee.toString())
                }
                binding.activityNetworkDeadlineDetailTv.text = item.endDate

                //무료
                if (it.result.fee == 0) {
                    // 버튼 상태

                    if (it.result.userButtonStatus == getString(R.string.origin_apply_complete)) {
                        //신청완료, 비활성화
                        with(binding.activityNetworkApplyBtn) {
                            text = getString(R.string.apply_complete_btn)
                            setTextColor(resources.getColor(R.color.seminar_blue))
                            setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
                            isEnabled = false
                        }
                    }
                    if (it.result.userButtonStatus == getString(R.string.origin_apply_closed)) {
                        //마감, 비활성화
                        with(binding.activityNetworkApplyBtn) {
                            text = getString(R.string.apply_end_btn)
                            setTextColor(resources.getColor(R.color.gray8a))
                            setBackgroundResource(R.drawable.activity_userbutton_closed_gray)
                            isEnabled = false
                        }
                    }
                    if (it.result.userButtonStatus == getString(R.string.origin_apply_apply)) {
                        // 신청하기 활성화
                        with(binding.activityNetworkApplyBtn) {
                            text = getString(R.string.origin_apply)
                            setTextColor(resources.getColor(R.color.white))
                            setBackgroundResource(R.drawable.btn_seminar_apply)
                            isEnabled = true
                        }
                    }
                    binding.activityNetworkApplyBtn.visibility = VISIBLE
                }
                //유료
                else {
                    // 버튼 상태
                    Log.d("applybtn2", it.result.fee.toString())
                    if (it.result.userButtonStatus == getString(R.string.origin_before_apply_confirm)) {
                        //신청확인중, 비활성화
                        with(binding.activityNetworkApplyBtn) {
                            text = getString(R.string.apply_check_btn)
                            setTextColor(resources.getColor(R.color.seminar_blue))
                            setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
                        }
                    }
                    if (it.result.userButtonStatus == getString(R.string.origin_apply_complete)) {
                        //신청완료, 비활성화
                        with(binding.activityNetworkApplyBtn) {
                            text = getString(R.string.apply_complete_btn)
                            setTextColor(resources.getColor(R.color.seminar_blue))
                            setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
                            isEnabled = false
                        }
                    }
                    if (it.result.userButtonStatus == getString(R.string.origin_apply_closed)) {
                        //마감, 비활성화
                        with(binding.activityNetworkApplyBtn) {
                            text = getString(R.string.apply_end_btn)
                            setTextColor(resources.getColor(R.color.gray8a))
                            setBackgroundResource(R.drawable.activity_userbutton_closed_gray)
                            isEnabled = false
                        }
                    }
                    if (it.result.userButtonStatus == getString(R.string.origin_apply_apply)) {
                        // 신청하기 활성화
                        with(binding.activityNetworkApplyBtn) {
                            text = getString(R.string.origin_apply)
                            setTextColor(resources.getColor(R.color.white))
                            setBackgroundResource(R.drawable.btn_seminar_apply)
                            isEnabled = true
                        }
                    }
                    binding.activityNetworkApplyBtn.visibility = VISIBLE
                }
            })
        }
    }

    private suspend fun updateData():Int {
        val value: Int = withContext(Dispatchers.IO) {
            val total = 1
            with(viewModel) {
                getNetworkingParticipants()
                getNetworkingInfo()
            }
            total
        }
        return value
    }



    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

}
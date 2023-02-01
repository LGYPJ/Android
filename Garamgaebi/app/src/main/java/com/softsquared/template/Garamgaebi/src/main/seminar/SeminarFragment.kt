package com.softsquared.template.Garamgaebi.src.main.seminar

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentSeminarBinding
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import com.softsquared.template.Garamgaebi.src.main.seminar.data.PresentationResult
import com.softsquared.template.Garamgaebi.src.main.seminar.data.SeminarParticipantsResult
import com.softsquared.template.Garamgaebi.viewModel.SeminarViewModel

class SeminarFragment: BaseFragment<FragmentSeminarBinding>(FragmentSeminarBinding::bind, R.layout.fragment_seminar) {

    private var profileList: ArrayList<SeminarParticipantsResult> = arrayListOf(
        SeminarParticipantsResult(1, "cindy", "https://post-phinf.pstatic.net/MjAxOTA2MjRfMTcg/MDAxNTYxMzUzMjkyNjIx.oP-m6lCS0OfZtmZr3EggV6SXr8lZclr0NamrgZx1AIEg.RhB9HljEXJLXfDTBC23pXcEhKDrcSyS0p9GLAEeXWosg.JPEG/IMG_3231.jpg?type=w1200"),
        SeminarParticipantsResult(1, "cindy", "https://post-phinf.pstatic.net/MjAxOTA2MjRfMTcg/MDAxNTYxMzUzMjkyNjIx.oP-m6lCS0OfZtmZr3EggV6SXr8lZclr0NamrgZx1AIEg.RhB9HljEXJLXfDTBC23pXcEhKDrcSyS0p9GLAEeXWosg.JPEG/IMG_3231.jpg?type=w1200"),
        SeminarParticipantsResult(1, "cindy", "https://post-phinf.pstatic.net/MjAxOTA2MjRfMTcg/MDAxNTYxMzUzMjkyNjIx.oP-m6lCS0OfZtmZr3EggV6SXr8lZclr0NamrgZx1AIEg.RhB9HljEXJLXfDTBC23pXcEhKDrcSyS0p9GLAEeXWosg.JPEG/IMG_3231.jpg?type=w1200"),
        SeminarParticipantsResult(1, "cindy", "https://post-phinf.pstatic.net/MjAxOTA2MjRfMTcg/MDAxNTYxMzUzMjkyNjIx.oP-m6lCS0OfZtmZr3EggV6SXr8lZclr0NamrgZx1AIEg.RhB9HljEXJLXfDTBC23pXcEhKDrcSyS0p9GLAEeXWosg.JPEG/IMG_3231.jpg?type=w1200"),
        SeminarParticipantsResult(1, "cindy", "https://post-phinf.pstatic.net/MjAxOTA2MjRfMTcg/MDAxNTYxMzUzMjkyNjIx.oP-m6lCS0OfZtmZr3EggV6SXr8lZclr0NamrgZx1AIEg.RhB9HljEXJLXfDTBC23pXcEhKDrcSyS0p9GLAEeXWosg.JPEG/IMG_3231.jpg?type=w1200"),
        SeminarParticipantsResult(1, "cindy", "https://post-phinf.pstatic.net/MjAxOTA2MjRfMTcg/MDAxNTYxMzUzMjkyNjIx.oP-m6lCS0OfZtmZr3EggV6SXr8lZclr0NamrgZx1AIEg.RhB9HljEXJLXfDTBC23pXcEhKDrcSyS0p9GLAEeXWosg.JPEG/IMG_3231.jpg?type=w1200"),
        SeminarParticipantsResult(1, "cindy", "https://post-phinf.pstatic.net/MjAxOTA2MjRfMTcg/MDAxNTYxMzUzMjkyNjIx.oP-m6lCS0OfZtmZr3EggV6SXr8lZclr0NamrgZx1AIEg.RhB9HljEXJLXfDTBC23pXcEhKDrcSyS0p9GLAEeXWosg.JPEG/IMG_3231.jpg?type=w1200"),
        SeminarParticipantsResult(1, "cindy", "https://post-phinf.pstatic.net/MjAxOTA2MjRfMTcg/MDAxNTYxMzUzMjkyNjIx.oP-m6lCS0OfZtmZr3EggV6SXr8lZclr0NamrgZx1AIEg.RhB9HljEXJLXfDTBC23pXcEhKDrcSyS0p9GLAEeXWosg.JPEG/IMG_3231.jpg?type=w1200"),
        SeminarParticipantsResult(1, "cindy", "https://post-phinf.pstatic.net/MjAxOTA2MjRfMTcg/MDAxNTYxMzUzMjkyNjIx.oP-m6lCS0OfZtmZr3EggV6SXr8lZclr0NamrgZx1AIEg.RhB9HljEXJLXfDTBC23pXcEhKDrcSyS0p9GLAEeXWosg.JPEG/IMG_3231.jpg?type=w1200"),

    )
    private var presentList: ArrayList<PresentationResult> = arrayListOf(
        PresentationResult(1,"docker에 대해 알아보자", "네온","http://news.samsungdisplay.com/wp-content/uploads/2018/08/2.png","재학생", "ㄴㄴㄴㄴㄴㄴㄴㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ", "https://news.samsungdisplay.com/15580"),
        PresentationResult(1,"docker에 대해 알아보자", "네온","http://news.samsungdisplay.com/wp-content/uploads/2018/08/2.png","재학생", "ㄴㄴㄴㄴㄴㄴㄴㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ", "https://news.samsungdisplay.com/15580"),
        PresentationResult(1,"docker에 대해 알아보자", "네온","http://news.samsungdisplay.com/wp-content/uploads/2018/08/2.png","재학생", "ㄴㄴㄴㄴㄴㄴㄴㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ", "https://news.samsungdisplay.com/15580")
    )
    //화면전환
    var containerActivity: ContainerActivity? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //뷰모델
        val viewModel = ViewModelProvider(this)[SeminarViewModel::class.java]

        //프로필 어댑터 연결
        viewModel.getSeminarParticipants(1)
        viewModel.seminarParticipants.observe(viewLifecycleOwner, Observer {
            val seminarProfile = SeminarProfileAdapter(it.result as ArrayList<SeminarParticipantsResult>)
            binding.activitySeminarFreeProfileRv.apply {
                adapter = seminarProfile
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(SeminarHorizontalItemDecoration())
            }
            /*val seminarProfile = SeminarProfileAdapter(profileList)
            binding.activitySeminarFreeProfileRv.apply {
                adapter = seminarProfile
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(SeminarHorizontalItemDecoration())
        }*/
        })

        //발표 어댑터 연결
        viewModel.getSeminarsInfo(1)
        viewModel.presentation.observe(viewLifecycleOwner, Observer {
            /*val presentAdapter = SeminarPresentAdapter(presentList)
            binding.activitySeminarFreePresentRv.apply {
                adapter = presentAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(SeminarVerticalItemDecoration())
            }*/
            //서버 데이터 채워지면 이걸로 바꾸기
            val presentAdapter = SeminarPresentAdapter(it.result as ArrayList<PresentationResult>)
            binding.activitySeminarFreePresentRv.apply {
                adapter = presentAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(SeminarVerticalItemDecoration())
            }
            //발표 리사이클러뷰 클릭하면 팝업다이얼로그 나타남!
            presentAdapter.setOnItemClickListener(object : SeminarPresentAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    val bundle = Bundle()
                    bundle.putInt("presentationDialog", position)
                    val seminarPreviewDialog = SeminarPreviewDialog()
                    seminarPreviewDialog.arguments = bundle
                    activity?.let {
                        seminarPreviewDialog.show(
                            it.supportFragmentManager, "SeminarPreviewDialog"
                        )
                    }
                    SeminarPreviewDialog().dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
            } )
        })


        //무료이면 무료신청 페이지로 유료이면 유료 신청 페이지로 ==> 프래그먼트 전환으로 바꾸기

        binding.activitySeminarFreeApplyBtn.setOnClickListener {
            val pay = binding.activitySeminarFreePayDetailTv.text
            if(pay == "무료") {
                containerActivity!!.openFragmentOnFrameLayout(2)
            }
            else {
                containerActivity!!.openFragmentOnFrameLayout(3)
            }
        }

    }
    //신청하기 누르면 버튼 바꾸는!!
    override fun onStart() {
        super.onStart()
        //버튼 누르면 변하는 거
            /*val apply_tv = "신청완료"
            binding.activitySeminarFreeApplyBtn.text = apply_tv
            binding.activitySeminarFreeApplyBtn.setTextColor(resources.getColor(R.color.seminar_blue))
            binding.activitySeminarFreeApplyBtn.setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)*/


    }
    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }


}
package com.example.template.garamgaebi.src.main.seminar

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.config.BaseBindingFragment
import com.example.template.garamgaebi.databinding.FragmentSeminarBinding
import com.example.template.garamgaebi.model.PresentationResult
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.src.main.seminar.data.SeminarParticipantsResult
import com.example.template.garamgaebi.viewModel.SeminarViewModel

class SeminarFragment: BaseBindingFragment<FragmentSeminarBinding>(R.layout.fragment_seminar) {

    /*private var profileList: ArrayList<SeminarParticipantsResult> = arrayListOf(
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
    )*/
    //화면전환
    var containerActivity: ContainerActivity? = null
    //private val items = MutableLiveData<ArrayList<PresentationResult>>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //뷰모델
        val viewModel = ViewModelProvider(this)[SeminarViewModel::class.java]
        //val recyclerViewItems : ArrayList<PresentationResult> = ArrayList()

        //프로필 어댑터 연결
        viewModel.getSeminarParticipants(6)
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
        viewModel.getSeminarsInfo(6)
        //val presentAdapter = SeminarPresentAdapter(viewModel.present)
        viewModel.present.observe(viewLifecycleOwner, Observer { it ->
            val presentAdapter = SeminarPresentAdapter(it)
            binding.activitySeminarFreePresentRv.apply {
                adapter = presentAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(SeminarVerticalItemDecoration())
            }
            /*val presentAdapter = SeminarPresentAdapter(viewModel.present)
            binding.activitySeminarFreePresentRv.apply {
                items.value = it
                val presentAdapter = SeminarPresentAdapter(items)
               // adapter = presentAdapter
                adapter = presentAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(SeminarVerticalItemDecoration())
            }*/
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

        //세미나 상세 정보
        /*viewModel.getSeminarDetail(0,0)
        viewModel.info.observe(viewLifecycleOwner, Observer {
                val item = it.result
                binding.activitySeminarFreeTitleTv.text = item.title
                binding.activitySeminarFreeDateDetailTv.text = item.date
                binding.activitySeminarFreePlaceDetailTv.text = item.location
                binding.activitySeminarFreePayDetailTv.text = item.fee.toString()
                binding.activitySeminarFreeDeadlineDetailTv.text = item.endDate

                //버튼 상태 추가하기
                if (item.userButtonStatus == "ApplyComplete") {
                    binding.activitySeminarFreeApplyBtn.text = "신청완료"
                    binding.activitySeminarFreeApplyBtn.setTextColor(resources.getColor(R.color.seminar_blue))
                    binding.activitySeminarFreeApplyBtn.setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
            }
        })*/


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

    /*private fun setUpSeminarDetailParameter() : HashMap<String, Int> {
        return hashMapOf(
            "memberIdx" to 0,
            "programIdx" to 0
        )
    }*/


}
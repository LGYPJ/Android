package com.example.template.garamgaebi.src.main.seminar

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.adapter.SeminarPresentAdapter
import com.example.template.garamgaebi.adapter.SeminarProfileAdapter

import com.example.template.garamgaebi.common.BaseFragment

import com.example.template.garamgaebi.databinding.FragmentSeminarBinding
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.src.main.seminar.data.PresentationResult
import com.example.template.garamgaebi.src.main.seminar.data.SeminarParticipantsResult
import com.example.template.garamgaebi.viewModel.SeminarViewModel

class SeminarFragment: BaseFragment<FragmentSeminarBinding>(FragmentSeminarBinding::bind,R.layout.fragment_seminar) {

    //화면전환
    var containerActivity: ContainerActivity? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뷰모델
        val viewModel = ViewModelProvider(this)[SeminarViewModel::class.java]

        //프로필 어댑터 연결
        viewModel.getSeminarParticipants(8,1)
        viewModel.seminarParticipants.observe(viewLifecycleOwner, Observer {
            val seminarProfile = SeminarProfileAdapter(it.result as ArrayList<SeminarParticipantsResult>)
            binding.activitySeminarFreeProfileRv.apply {
                adapter = seminarProfile
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(SeminarHorizontalItemDecoration())
            }
        })

        //발표 어댑터 연결
        viewModel.getSeminarsInfo(8)
        viewModel.presentation.observe(viewLifecycleOwner, Observer { it ->
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
                   // val temp = it.result[position].presentationIdx
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
        viewModel.getSeminarDetail(6,1)
        viewModel.info.observe(viewLifecycleOwner, Observer {
            val item = it.result
            binding.activitySeminarFreeTitleTv.text = item.title
            binding.activitySeminarFreeDateDetailTv.text = item.date
            binding.activitySeminarFreePlaceDetailTv.text = item.location
            if(item.fee.toString() == "0"){
                binding.activitySeminarFreePayDetailTv.text = "무료"
            }
            else{
                binding.activitySeminarFreePayDetailTv.text = getString(R.string.main_fee, item.fee.toString())
            }
            binding.activitySeminarFreeDeadlineDetailTv.text = item.endDate
                //버튼 상태 추가하기
                /*if (item.userButtonStatus == "ApplyComplete") {
                    binding.activitySeminarFreeApplyBtn.text = "신청완료"
                    binding.activitySeminarFreeApplyBtn.setTextColor(resources.getColor(R.color.seminar_blue))
                    binding.activitySeminarFreeApplyBtn.setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
            }*/

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
    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

}
package com.softsquared.template.Garamgaebi.src.seminar

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentSeminarBinding

class SeminarFragment: BaseFragment<FragmentSeminarBinding>(FragmentSeminarBinding::bind, R.layout.fragment_seminar) {

    private var profileList: ArrayList<SeminarProfile> = arrayListOf(
        SeminarProfile(R.drawable.activity_seminar_profile_img_blue, "신디", multi_type2),
        SeminarProfile(R.drawable.ic_seminar_profile2, "짱구", multi_type1),
        SeminarProfile(R.drawable.ic_seminar_profile1, "로건", multi_type1),
        SeminarProfile(R.drawable.activity_seminar_profile_gray, "알수없음", multi_type3),
        SeminarProfile(R.drawable.ic_seminar_profile1, "네온", multi_type1),
        SeminarProfile(R.drawable.ic_seminar_profile3, "코코아", multi_type1),
        SeminarProfile(R.drawable.ic_seminar_profile2, "연현", multi_type1),
        SeminarProfile(R.drawable.ic_seminar_profile1, "승콩", multi_type1)
    )

    private var presentList: ArrayList<SeminarPresent> = arrayListOf(
        SeminarPresent("docker에 대해 알아보자", "네온", "재학생",R.drawable.activity_seminar_present_profile1_img),
        SeminarPresent("docker에 대해 알아보자", "네온", "재학생",R.drawable.activity_seminar_present_profile2_img),
        SeminarPresent("docker에 대해 알아보자docker에 대해 알아보자", "네온", "재학생",R.drawable.activity_seminar_present_profile3_img)
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val seminarProfile = SeminarProfileAdapter(profileList)
        binding.activitySeminarFreeProfileRv.apply {
            adapter = seminarProfile
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(SeminarHorizontalItemDecoration())
        }

        val presentAdapter = SeminarPresentAdapter(presentList)
        binding.activitySeminarFreePresentRv.apply {
            adapter = presentAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(SeminarVerticalItemDecoration())
        }

         //발표 리사이클러뷰 클릭하면 팝업다이얼로그 나타남!
        presentAdapter.setOnItemClickListener(object : SeminarPresentAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                activity?.let {
                    SeminarPreviewDialog().show(
                        it.supportFragmentManager, "SeminarPreviewDialog"
                    )
                }
                SeminarPreviewDialog().dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
        } )

        //무료이면 무료신청 페이지로 유료이면 유료 신청 페이지로

        binding.activitySeminarFreeApplyBtn.setOnClickListener {
            val pay = binding.activitySeminarFreePayDetailTv.text
            if(pay == "무료") {
                startActivity(Intent(activity,SeminarFreeApplyActivity::class.java ))
            }
            else {
                startActivity(Intent(activity,SeminarChargedApplyActivity::class.java ))
            }
        }



    }
    //신청하기 누르면 버튼 바꾸는!!
    /*override fun onStart() {
        super.onStart()
        var apply = intent.getBooleanExtra("apply", false)
        if(apply){
            val apply_tv = "신청완료"
            binding.activitySeminarFreeApplyBtn.setText(apply_tv)
            binding.activitySeminarFreeApplyBtn.setTextColor(getColor(R.color.seminar_blue))
            binding.activitySeminarFreeApplyBtn.setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
        }
    }*/

}
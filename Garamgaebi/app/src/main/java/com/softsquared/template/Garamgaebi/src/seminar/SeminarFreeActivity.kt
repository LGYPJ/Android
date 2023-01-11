package com.softsquared.template.Garamgaebi.src.seminar

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivitySeminarFreeBinding


class SeminarFreeActivity : BaseActivity<ActivitySeminarFreeBinding>(ActivitySeminarFreeBinding::inflate) {

    private var profileList: ArrayList<SeminarProfile> = arrayListOf(
        SeminarProfile(R.drawable.ic_seminar_profile1, "신디"),
        SeminarProfile(R.drawable.ic_seminar_profile2, "짱구"),
        SeminarProfile(R.drawable.ic_seminar_profile1, "로건"),
        SeminarProfile(R.drawable.ic_seminar_profile3, "찹도"),
        SeminarProfile(R.drawable.ic_seminar_profile1, "네온"),
        SeminarProfile(R.drawable.ic_seminar_profile3, "코코아"),
        SeminarProfile(R.drawable.ic_seminar_profile2, "연현"),
        SeminarProfile(R.drawable.ic_seminar_profile1, "승콩")
    )

    private var presentList: ArrayList<SeminarPresent> = arrayListOf(
        SeminarPresent("docker에 대해 알아보자", "네온", "재학생"),
        SeminarPresent("docker에 대해 알아보자", "네온", "재학생"),
        SeminarPresent("docker에 대해 알아보자", "네온", "재학생"),
        SeminarPresent("docker에 대해 알아보자", "네온", "재학생"),
        SeminarPresent("docker에 대해 알아보자", "네온", "재학생"),
        SeminarPresent("docker에 대해 알아보자", "네온", "재학생")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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


        presentAdapter.setOnItemClickListener(object : SeminarPresentAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                SeminarPreviewDialog().show(
                    supportFragmentManager, "SeminarPreviewDialog"
                )
            }
        } )


    }
}
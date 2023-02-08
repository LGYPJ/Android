package com.example.template.garamgaebi.src.main.profile

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.*
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseFragment
import com.example.template.garamgaebi.databinding.FragmentSomeoneprofileBinding


class SomeoneProfileFragment :
BaseFragment<FragmentSomeoneprofileBinding>(FragmentSomeoneprofileBinding::bind, R.layout.fragment_someoneprofile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.activitySomeoneProfileTvUsername.text = "찹도"
        binding.activitySomeoneProfileTvIntro.text = "내가 여기 짱 찹도다"
        binding.activitySomeoneProfileTvSchool.text = "가천대학교 소프트웨어학과"
        binding.activitySomeoneProfileTvEmail.text = "chapdo@naver.com"


        if(binding.activitySomeoneProfileTvIntro.text.length > 0){
            binding.activitySomeoneProfileTvIntro.visibility = VISIBLE
        }



        //sns 리스트뷰 연결
        var snsItems: ArrayList<SnsRVItemData> = arrayListOf()
        snsItems.apply {
            add(SnsRVItemData("chapdoinstagram.com"))
            add(SnsRVItemData("chapdoinstagram.com"))
            add(SnsRVItemData("chapdoinstagram.com"))
        }
        val snsAdapter = SnsSomeoneRVAdapter(snsItems)
        binding.activitySomeoneProfileRVSns.adapter = snsAdapter

        var dividerItemDecoration =
            context?.let {
                ContextCompat.getDrawable(it, R.drawable.divider)
                    ?.let { DividerItemDecoratorForLastItem(it) }
            };
        if (dividerItemDecoration != null) {
            binding.activitySomeoneProfileRVSns.addItemDecoration(dividerItemDecoration)
        }
        snsAdapter?.notifyDataSetChanged()
        Log.d("리스트1",snsItems.size.toString())


        //career 리스트뷰 연결
        var careerItems: ArrayList<CareerRVItemData> = arrayListOf()
        val careerAdapter = CareerSomeoneRVAdapter(careerItems)
        binding.activitySomeoneProfileRVCareer.adapter = careerAdapter

        careerItems.add(CareerRVItemData("가천대학교 입학","신입생 새내기","2020.01","2020.12"))
        careerItems.add(CareerRVItemData("UMC 2기","챌린저","2022.03","2022.09"))
        careerItems.add(CareerRVItemData("UMC 3기","챌린저/PM","2022.09","2023.02"))
         dividerItemDecoration =
            context?.let {
                ContextCompat.getDrawable(it, R.drawable.divider)
                    ?.let { DividerItemDecoratorForLastItem(it) }
            };
        if (dividerItemDecoration != null) {
            binding.activitySomeoneProfileRVCareer.addItemDecoration(dividerItemDecoration)
        }
        //edu 리스트뷰 연결
        var eduItems: ArrayList<EduRVItemData> = arrayListOf()
        val eduAdapter = EduSomeoneRVAdapter(eduItems)
        binding.activitySomeoneProfileRVEdu.adapter = eduAdapter
        //setListViewHeightBasedOnChildren(binding.activitySomeoneProfileLvEdu)

        eduItems.add(EduRVItemData("우아한 형제들","프론트엔드 개발 교육","2020.04","2021.09"))
        //setListViewHeightBasedOnChildren(binding.activitySomeoneProfileLvEdu)
        eduItems.add(EduRVItemData("가천대학교 4학년","누나 복학 화이팅","2023.03","현재"))
        //setListViewHeightBasedOnChildren(binding.activitySomeoneProfileLvEdu)
        dividerItemDecoration =
            context?.let {
                ContextCompat.getDrawable(it, R.drawable.divider)
                    ?.let { DividerItemDecoratorForLastItem(it) }
            };
        if (dividerItemDecoration != null) {
            binding.activitySomeoneProfileRVEdu.addItemDecoration(dividerItemDecoration)
        }
        careerAdapter?.notifyDataSetChanged()
        eduAdapter?.notifyDataSetChanged()

        snsAdapter.setMyItemClickListener(object : SnsSomeoneRVAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val clipboard = activity?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // 새로운 ClipData 객체로 데이터 복사하기
                val clip: ClipData =
                    ClipData.newPlainText("sns_address", snsItems[position].snsAddress)

                // 새로운 클립 객체를 클립보드에 배치합니다.
                clipboard.setPrimaryClip(clip)
                Toast.makeText(binding.root.context, "복사 완료", Toast.LENGTH_SHORT).show()

            }override fun onLongClick(position: Int) {


            }
        })
    }

}

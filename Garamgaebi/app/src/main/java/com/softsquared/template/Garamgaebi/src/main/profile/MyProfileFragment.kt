package com.softsquared.template.Garamgaebi.src.main.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentMyprofileBinding
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity

class MyProfileFragment :
    BaseFragment<FragmentMyprofileBinding>(FragmentMyprofileBinding::bind, R.layout.fragment_myprofile) {
    private lateinit var callback: OnBackPressedCallback
    var containerActivity: ContainerActivity? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.activityMyProfileTvUsername.text = "로건"
        binding.activityMyProfileTvIntro.text = "자기소개"
        binding.activityMyProfileTvSchool.text = "가천대학교 소프트웨어학과"
        binding.activityMyProfileTvEmail.text = "umc@naver.com"


        if(binding.activityMyProfileTvIntro.text.isNotEmpty()){
            binding.activityMyProfileTvIntro.visibility = View.VISIBLE
        }

        //내 프로필 편집 화면으로 이동
        binding.activityMyProfileBtnEditProfile.setOnClickListener {
            val intent = Intent(activity,ContainerActivity::class.java)
            intent.putExtra("edit",true) //데이터 넣기
            startActivity(intent)
        }
        //고객센터 화면으로 이동
        binding.activityMyProfileIvCs.setOnClickListener {
            val intent = Intent(activity,ContainerActivity::class.java)
            intent.putExtra("servicecenter",true) //데이터 넣기
            startActivity(intent)
        }
        //탈퇴 화면으로 이동
        binding.activityMyProfileIvWd.setOnClickListener {
            val intent = Intent(activity,ContainerActivity::class.java)
            intent.putExtra("withdrawal",true) //데이터 넣기
            startActivity(intent)
        }

        //sns 리스트뷰 연결
        var snsItems: ArrayList<SnsRVItemData> = arrayListOf()
        val snsAdapter = SnsMYRVAdapter(snsItems)
        var dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVSns.context, LinearLayoutManager(requireContext()).orientation)
        binding.activityMyProfileRVSns.addItemDecoration(dividerItemDecoration)
        binding.activityMyProfileRVSns.adapter = snsAdapter

        //sns 추가 버튼
        binding.activityMyProfileBtnSnsAdd.setOnClickListener {
            binding.activityMyProfileRVSns.visibility = View.VISIBLE
            //binding.activityMyprofileSnsVListEndline.visibility = View.VISIBLE
            binding.activityMyProfileTvSnsDesc.visibility = View.GONE
            snsItems.add(SnsRVItemData("neoninstagram.com"))
            snsAdapter?.notifyDataSetChanged()
            Log.d("plus_sns",snsItems.size.toString())
            val intent = Intent(activity,ContainerActivity::class.java)
            intent.putExtra("sns",true) //데이터 넣기
            startActivity(intent)
        }

        //career 리스트뷰 연결
        var careerItems: ArrayList<CareerRVItemData> = arrayListOf()
        val careerAdapter = CareerMyRVAdapter(careerItems)
        dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVCareer.context, LinearLayoutManager(requireContext()).orientation)
        binding.activityMyProfileRVCareer.addItemDecoration(dividerItemDecoration)
        binding.activityMyProfileRVCareer.adapter = careerAdapter

        //career 추가 버튼
        binding.activityMyProfileBtnCareerAdd.setOnClickListener {
            binding.activityMyProfileRVCareer.visibility = View.VISIBLE
            binding.activityMyProfileTvCareerDesc.visibility = View.GONE
            careerItems.add(CareerRVItemData("우아한 형제들","프론트엔드 개발자","2020.04","2021.09"))
            careerAdapter?.notifyDataSetChanged()
            Log.d("plus_career",careerItems.size.toString())
            val intent = Intent(activity,ContainerActivity::class.java)
            intent.putExtra("career",true) //데이터 넣기
            startActivity(intent)
        }

        //edu 리스트뷰 연결
        var eduItems: ArrayList<EduRVItemData> = arrayListOf()
        val eduAdapter = EduMyRVAdapter(eduItems)
        dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVEdu.context, LinearLayoutManager(requireContext()).orientation)
        binding.activityMyProfileRVEdu.addItemDecoration(dividerItemDecoration)
        binding.activityMyProfileRVEdu.adapter = eduAdapter

        //edu 추가 버튼
        binding.activityMyProfileBtnEduAdd.setOnClickListener {
            binding.activityMyProfileRVEdu.visibility = View.VISIBLE
            binding.activityMyProfileTvEduDesc.visibility = View.GONE
            eduItems.add(EduRVItemData("우아한 형제들","프론트엔드 개발 교육","2020.04","2021.09"))
            eduAdapter?.notifyDataSetChanged()
            val intent = Intent(activity,ContainerActivity::class.java)
            intent.putExtra("edu",true) //데이터 넣기
            startActivity(intent)
        }

        //test 상대 프로필
        binding.activityMyProfileIvProfile.setOnClickListener {
            val intent = Intent(activity,ContainerActivity::class.java)
            intent.putExtra("someoneprofile",true) //데이터 넣기
            startActivity(intent)
        }
    }

    // 자동 높이 조절
     fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter = listView.adapter
            ?: // pre-condition
            return
        var totalHeight = 0
        val desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.AT_MOST)
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            //listItem.measure(0, 0);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
            totalHeight += listItem.measuredHeight
        }
        var totaldividers = listView.dividerHeight * (listAdapter.count-1)

        val params = listView.layoutParams
        params.height = totalHeight +totaldividers
        listView.layoutParams = params
        listView.requestLayout()
    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
       // containerActivity = context as ContainerActivity
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
    }



package com.softsquared.template.Garamgaebi.src.main.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import androidx.activity.OnBackPressedCallback
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.ActivityMainBinding
import com.softsquared.template.Garamgaebi.databinding.FragmentMyprofileBinding
import com.softsquared.template.Garamgaebi.src.main.MainActivity
import com.softsquared.template.Garamgaebi.src.main.home.HomeFragment

class MyProfileFragment :
    BaseFragment<FragmentMyprofileBinding>(FragmentMyprofileBinding::bind, R.layout.fragment_myprofile) {
    private lateinit var callback: OnBackPressedCallback
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
            startActivity(Intent(activity, ProfileEditActivity::class.java))
        }

        //고객센터 화면으로 이동
        binding.activityMyProfileIvCs.setOnClickListener {
            startActivity(Intent(activity, ServiceCenterActivity::class.java))
        }
        //탈퇴 화면으로 이동
        binding.activityMyProfileIvWd.setOnClickListener {
            startActivity(Intent(activity, WithdrawalActivity::class.java))
        }

        //sns 리스트뷰 연결
        var snsItems: ArrayList<SnsListViewItem> = arrayListOf()
        val snsAdapter = context?.let { SnsListViewAdapter(it, snsItems) }
        binding.activityMyProfileLvSns.adapter = snsAdapter
        setListViewHeightBasedOnChildren(binding.activityMyProfileLvSns)

        //sns 추가 버튼
        binding.activityMyProfileBtnSnsAdd.setOnClickListener {
            binding.activityMyProfileLvSns.visibility = View.VISIBLE
            binding.activityMyprofileSnsVListEndline.visibility = View.VISIBLE
            binding.activityMyProfileTvSnsDesc.visibility = View.GONE
            snsItems.add(SnsListViewItem("neoninstagram.com"))
            snsAdapter?.notifyDataSetChanged()
            setListViewHeightBasedOnChildren(binding.activityMyProfileLvSns)
            Log.d("plus_sns",snsItems.size.toString())
            startActivity(Intent(activity, SnsProfileActivity::class.java))
        }

        //career 리스트뷰 연결
        var careerItems: ArrayList<CareerListViewItem> = arrayListOf()
        val careerAdapter = context?.let { CareerListViewAdapter(it, careerItems) }
        binding.activityMyProfileLvCareer.adapter = careerAdapter
        setListViewHeightBasedOnChildren(binding.activityMyProfileLvCareer)

        //career 추가 버튼
        binding.activityMyProfileBtnCareerAdd.setOnClickListener {
            binding.activityMyProfileLvCareer.visibility = View.VISIBLE
            binding.activityMyprofileCareerVListEndline.visibility = View.VISIBLE
            binding.activityMyProfileTvCareerDesc.visibility = View.GONE
            careerItems.add(CareerListViewItem("우아한 형제들","프론트엔드 개발자","2020.04","2021.09"))
            careerAdapter?.notifyDataSetChanged()
            setListViewHeightBasedOnChildren(binding.activityMyProfileLvCareer)
            Log.d("plus_career",careerItems.size.toString())
            startActivity(Intent(activity, CareerActivity::class.java))
        }

        //edu 리스트뷰 연결
        var eduItems: ArrayList<EduListViewItem> = arrayListOf()
        val eduAdapter = context?.let { EduListViewAdapter(it, eduItems) }
        binding.activityMyProfileLvEdu.adapter = eduAdapter
        setListViewHeightBasedOnChildren(binding.activityMyProfileLvEdu)

        //edu 추가 버튼
        binding.activityMyProfileBtnEduAdd.setOnClickListener {
            binding.activityMyProfileLvEdu.visibility = View.VISIBLE
            binding.activityMyprofileEduVListEndline.visibility = View.VISIBLE
            binding.activityMyProfileTvEduDesc.visibility = View.GONE
            eduItems.add(EduListViewItem("우아한 형제들","프론트엔드 개발 교육","2020.04","2021.09"))
            eduAdapter?.notifyDataSetChanged()
            setListViewHeightBasedOnChildren(binding.activityMyProfileLvEdu)
            startActivity(Intent(activity, EduActivity::class.java))
        }

        //test
        binding.activityMyProfileIvProfile.setOnClickListener {
            startActivity(Intent(activity, SomeoneProfileActivity::class.java))
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
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                    (activity as MainActivity?)
                        ?.supportFragmentManager
                        ?.beginTransaction()
                        ?.replace(ActivityMainBinding.inflate(layoutInflater).activityMainFrm.id,
                            HomeFragment()
                        )
                        ?.commitAllowingStateLoss()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }
    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
    }



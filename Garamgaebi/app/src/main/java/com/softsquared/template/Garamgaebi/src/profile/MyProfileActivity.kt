package com.softsquared.template.Garamgaebi.src.profile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat
import com.Garamgaebi.src.profile.ServiceCenterActivity
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityMyprofileBinding
import com.softsquared.template.Garamgaebi.src.main.register.LoginActivity

class MyProfileActivity : BaseActivity<ActivityMyprofileBinding>(ActivityMyprofileBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activityMyProfileTvUsername.text = "로건"
        binding.activityMyProfileTvIntro.text = "자기소개"
        binding.activityMyProfileTvSchool.text = "가천대학교 소프트웨어학과"
        binding.activityMyProfileTvEmail.text = "umc@naver.com"


        if(binding.activityMyProfileTvIntro.text.length > 0){
            binding.activityMyProfileTvIntro.visibility = VISIBLE
        }

        //내 프로필 편집 화면으로 이동
        binding.activityMyProfileBtnEditProfile.setOnClickListener {
            startActivity(Intent(this, ProfileEditActivity::class.java))
        }

        //고객센터 화면으로 이동
        binding.activityMyProfileIvCs.setOnClickListener {
            startActivity(Intent(this, ServiceCenterActivity::class.java))
        }
        //탈퇴 화면으로 이동
        binding.activityMyProfileIvWd.setOnClickListener {
            startActivity(Intent(this, WithdrawalActivity::class.java))
        }

        //sns 리스트뷰 연결
        val snsItems = mutableListOf<SnsListViewItem>()
        val snsAdapter = SnsListViewAdapter(this, snsItems)
        binding.activityMyProfileLvSns.adapter = snsAdapter

        //sns 추가 버튼
        binding.activityMyProfileBtnSnsAdd.setOnClickListener {
            binding.activityMyProfileLvSns.visibility = VISIBLE
            binding.activityMyProfileTvSnsDesc.visibility = GONE
            snsItems.add(SnsListViewItem("neoninstagram.com"))
            snsAdapter.notifyDataSetChanged()
        }

        //career 리스트뷰 연결
        val careerItems = mutableListOf<CareerListViewItem>()
        val careerAdapter = CareerListViewAdapter(this, careerItems)
        binding.activityMyProfileLvCareer.adapter = careerAdapter

        //career 추가 버튼
        binding.activityMyProfileBtnCareerAdd.setOnClickListener {
            binding.activityMyProfileLvCareer.visibility = VISIBLE
            binding.activityMyProfileTvCareerDesc.visibility = GONE
            careerItems.add(CareerListViewItem("우아한 형제들","프론트엔드 개발자","2020.04","2021.09"))
            careerAdapter.notifyDataSetChanged()
            startActivity(Intent(this, CareerActivity::class.java))
        }

        //edu 리스트뷰 연결
        val eduItems = mutableListOf<EduListViewItem>()
        val eduAdapter = EduListViewAdapter(this, eduItems)
        binding.activityMyProfileLvEdu.adapter = eduAdapter

        //edu 추가 버튼
        binding.activityMyProfileBtnEduAdd.setOnClickListener {
            binding.activityMyProfileLvEdu.visibility = VISIBLE
            binding.activityMyProfileTvEduDesc.visibility = GONE
            eduItems.add(EduListViewItem("우아한 형제들","프론트엔드 개발 교육","2020.04","2021.09"))
            eduAdapter.notifyDataSetChanged()
            startActivity(Intent(this, EduActivity::class.java))
        }
    }
    //뒤로가기 버튼 눌렀을 때
    override fun onBackPressed() {
        super.onBackPressed()
//        stopPlay() //이 액티비티에서 종료되어야 하는 활동 종료시켜주는 함수
//        Toast.makeText(this@WebViewPlayer, "방송 시청이 종료되었습니다.", Toast.LENGTH_SHORT).show() //토스트 메시지
//        val intent =
//            Intent(this@WebViewPlayer, MainActivity::class.java) //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP //인텐트 플래그 설정
//        startActivity(intent) //인텐트 이동
        finish() //현재 액티비티 종료
    }
}


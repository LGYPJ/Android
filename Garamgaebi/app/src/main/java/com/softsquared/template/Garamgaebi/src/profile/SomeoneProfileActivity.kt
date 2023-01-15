package com.softsquared.template.Garamgaebi.src.profile

import android.content.Intent
import android.os.Bundle
import android.view.View.VISIBLE
import android.widget.Toast
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivitySomeoneprofileBinding
import com.softsquared.template.Garamgaebi.src.main.MainActivity


class SomeoneProfileActivity : BaseActivity<ActivitySomeoneprofileBinding>(ActivitySomeoneprofileBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activitySomeoneProfileTvUsername.text = "로건"
        binding.activitySomeoneProfileTvIntro.text = "자기소개"
        binding.activitySomeoneProfileTvSchool.text = "가천대학교 소프트웨어학과"
        binding.activitySomeoneProfileTvEmail.text = "umc@naver.com"


        if(binding.activitySomeoneProfileTvIntro.text.length > 0){
            binding.activitySomeoneProfileTvIntro.visibility = VISIBLE
        }

        //뒤로가기
        binding.activitySomeoneProfileBackBtn.setOnClickListener {
            onBackPressed()
        }

        //sns 리스트뷰 연결
        val snsItems = mutableListOf<SnsListViewItem>()
        val snsAdapter = SnsSomeoneListViewAdapter(this, snsItems)
        binding.activitySomeoneProfileLvSns.adapter = snsAdapter

        //career 리스트뷰 연결
        val careerItems = mutableListOf<CareerListViewItem>()
        val careerAdapter = CareerListViewAdapter(this, careerItems)
        binding.activitySomeoneProfileLvCareer.adapter = careerAdapter

        //edu 리스트뷰 연결
        val eduItems = mutableListOf<EduListViewItem>()
        val eduAdapter = EduListViewAdapter(this, eduItems)
        binding.activitySomeoneProfileLvEdu.adapter = eduAdapter

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

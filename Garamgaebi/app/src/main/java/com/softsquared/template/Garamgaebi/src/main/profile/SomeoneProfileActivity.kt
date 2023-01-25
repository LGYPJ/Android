package com.softsquared.template.Garamgaebi.src.main.profile

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.*
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.widget.AdapterView
import android.widget.ListView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivitySomeoneprofileBinding


class SomeoneProfileActivity : BaseActivity<ActivitySomeoneprofileBinding>(ActivitySomeoneprofileBinding::inflate) {
    val handler: Handler = object : Handler(Looper.getMainLooper()) {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun handleMessage(msg: Message) {
            if (msg.what == 1) {

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.activitySomeoneProfileTvUsername.text = "찹도"
        binding.activitySomeoneProfileTvIntro.text = "내가 여기 짱 찹도다"
        binding.activitySomeoneProfileTvSchool.text = "가천대학교 소프트웨어학과"
        binding.activitySomeoneProfileTvEmail.text = "chapdo@naver.com"


        if(binding.activitySomeoneProfileTvIntro.text.length > 0){
            binding.activitySomeoneProfileTvIntro.visibility = VISIBLE
        }

        //뒤로가기
        binding.activitySomeoneProfileBackBtn.setOnClickListener {
            onBackPressed()
        }




        //sns 리스트뷰 연결
        var snsItems: ArrayList<SnsListViewItem> = arrayListOf()
        snsItems.apply {
            add(SnsListViewItem("chapdoinstagram.com"))
            add(SnsListViewItem("chapdoinstagram.com"))
            add(SnsListViewItem("chapdoinstagram.com"))
        }
        val snsAdapter = SnsSomeoneListViewAdapter(this, snsItems)
        binding.activitySomeoneProfileLvSns.adapter = snsAdapter
        snsAdapter?.notifyDataSetChanged()
        Log.d("리스트1",snsItems.size.toString())

        setListViewHeightBasedOnChildren(binding.activitySomeoneProfileLvSns)

        binding.activityMyProfileBtnSnsAdd.setOnClickListener {
            snsItems.add(SnsListViewItem("chapdoinstagram.com"))
            snsAdapter?.notifyDataSetChanged()
            setListViewHeightBasedOnChildren(binding.activitySomeoneProfileLvSns)
        }


        //career 리스트뷰 연결
        var careerItems: ArrayList<CareerListViewItem> = arrayListOf()
        val careerAdapter = CareerSomeoneListViewAdapter(this, careerItems)
        binding.activitySomeoneProfileLvCareer.adapter = careerAdapter

        careerItems.add(CareerListViewItem("가천대학교 입학","신입생 새내기","2020.01","2020.12"))
        careerItems.add(CareerListViewItem("UMC 2기","챌린저","2022.03","2022.09"))
        careerItems.add(CareerListViewItem("UMC 3기","챌린저/PM","2022.09","2023.02"))
        setListViewHeightBasedOnChildren(binding.activitySomeoneProfileLvCareer)

        //edu 리스트뷰 연결
        var eduItems: ArrayList<EduListViewItem> = arrayListOf()
        val eduAdapter = EduSomeoneListViewAdapter(this, eduItems)
        binding.activitySomeoneProfileLvEdu.adapter = eduAdapter
        //setListViewHeightBasedOnChildren(binding.activitySomeoneProfileLvEdu)

        //test
        binding.activitySomeoneProfileLvSns.visibility = View.VISIBLE
        binding.activityMyprofileSnsVListEndline.visibility = View.VISIBLE
        binding.activityMyProfileTvSnsDesc.visibility = View.GONE




        eduItems.add(EduListViewItem("우아한 형제들","프론트엔드 개발 교육","2020.04","2021.09"))
        //setListViewHeightBasedOnChildren(binding.activitySomeoneProfileLvEdu)
        eduItems.add(EduListViewItem("가천대학교 4학년","누나 복학 화이팅","2023.03","현재"))
        //setListViewHeightBasedOnChildren(binding.activitySomeoneProfileLvEdu)

        careerAdapter?.notifyDataSetChanged()
        eduAdapter?.notifyDataSetChanged()


        binding.activitySomeoneProfileLvSns.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            snsItems[i]
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

            // 새로운 ClipData 객체로 데이터 복사하기
            val clip: ClipData =
                ClipData.newPlainText("sns_address", snsItems[i].snsAddress)

            // 새로운 클립 객체를 클립보드에 배치합니다.
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "복사 완료", Toast.LENGTH_SHORT).show()
            false
        })
    }

    // 자동 높이 조절
    private fun setListViewHeightBasedOnChildren(listView: ListView) {
        val listAdapter = listView.adapter
            ?: // pre-condition
            return
        var totalHeight = 0
        val desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.width, View.MeasureSpec.AT_MOST)
        for (i in 0 until listAdapter.count) {
            val listItem = listAdapter.getView(i, null, listView)
            listItem.measure(0, 0);
            listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED)
            totalHeight += listItem.measuredHeight
            Log.d("리스트",totalHeight.toString())
        }
        var totaldividers = listView.dividerHeight * (listAdapter.count-1)

        val params = listView.layoutParams
        params.height = totalHeight +totaldividers
        listView.layoutParams = params
        listView.requestLayout()
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

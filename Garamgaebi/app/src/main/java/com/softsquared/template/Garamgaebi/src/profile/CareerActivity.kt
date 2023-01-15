package com.softsquared.template.Garamgaebi.src.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityProfileCareerBinding

class CareerActivity  : BaseActivity<ActivityProfileCareerBinding>(ActivityProfileCareerBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //편집 정보 저장하기 버튼 클릭이벤트
        binding.activityCareerSaveBtn.setOnClickListener {
            if (checkInfos() == true){
                //경력 저장 기능 추가
            }else{
                //저장 불가 및 이유
            }
        }
        //뒤로가기
        binding.activityCareerBackBtn.setOnClickListener {
            onBackPressed()
        }

        //회사 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityCareerEtCompanyDesc)

        //직함 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityCareerEtPosition)

        //재직기간_시작 입력 시 레이아웃 테두리 변경 -> 달력으로 바꿔야함
        checkEtInput(binding.activityCareerEtStartPeriod)

        //재직기간_종료 시 레이아웃 테두리 변경 -> 달력으로 바꿔야함
        checkEtInput(binding.activityCareerEtEndPeriod)
        checkNow(binding.activityCareerEtEndPeriod)


    }
    fun checkEtInput(view: EditText) {
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (p0.toString().equals("")){
                    view.setBackgroundResource(R.drawable.basic_gray_border_layout)
                }else{
                    view.setBackgroundResource(R.drawable.basic_black_border_layout)
                }
            }
        })
    }
    fun checkNow(view: EditText) {
        var end = binding.activityCareerEtEndPeriod.text.toString()
        //현재 재직 중일 때
        /*
        if(end == 현재){
            binding.activityCareerCheckbox.isChecked = true
        }else{
            binding.activityCareerCheckbox.isChecked = false
        }
         */
    }
    fun checkInfos() : Boolean{
        var  checkResult = true
        var company = binding.activityCareerEtCompanyDesc.text.toString()
        var position = binding.activityCareerEtPosition.text.toString()
        var start = binding.activityCareerEtStartPeriod.text.toString()
        var end = binding.activityCareerEtEndPeriod.text.toString()

        //회사 조건 확인 기능

        //직함 조건 확인 기능

        //시작년월 조건 확인 기능

        //종료년월 조건 확인 기능


        return checkResult
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

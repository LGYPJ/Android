package com.softsquared.template.Garamgaebi.src.main.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityProfileEditBinding

class ProfileEditActivity: BaseActivity<ActivityProfileEditBinding>(ActivityProfileEditBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //편집 정보 저장하기 버튼 클릭이벤트
        binding.activityEducationSaveBtn.setOnClickListener {
            if (checkInfo() == true){
                //회원정보 편집 저장 기능 추가
            }else{
                //저장 불가 및 이유
            }
        }
        //뒤로가기
        binding.activityEditProfileBackBtn.setOnClickListener {
            onBackPressed()
        }

        //닉네임 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityEditProfileEtNick)

        //소속 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityEditProfileEtTeam)

        //이메일 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityEditProfileEtEmail)

        //자기소개 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityEditProfileEtIntro)


    }
    fun checkEtInput(view: EditText) {
        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.basic_black_border_layout)
            } else {
                view.setBackgroundResource(R.drawable.basic_gray_border_layout)
            }
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (checkInfo()){
                    binding.activityEducationSaveBtn.isClickable = true
                    binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
                }else{
                    binding.activityEducationSaveBtn.isClickable = false
                    binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
                }
            }
        })
    }
    fun checkInfo() : Boolean{
       var  checkResult = true
        var nick = binding.activityEditProfileEtNick.text.toString()
        var team = binding.activityEditProfileEtTeam.text.toString()
        var email = binding.activityEditProfileEtEmail.text.toString()
        var intro = binding.activityEditProfileEtIntro.text.toString()

        //닉네임 조건 확인 기능
        if(nick.length < 8) checkResult = false

        //소속 조건 확인 기능
        if(team.isEmpty()) {
            checkResult = false
        }
        //이메일 조건 확인 기능
        if(email.isEmpty()) checkResult = false

        //자기소개 조건 확인 기능
        //if(intro.isEmpty()) checkResult = false


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


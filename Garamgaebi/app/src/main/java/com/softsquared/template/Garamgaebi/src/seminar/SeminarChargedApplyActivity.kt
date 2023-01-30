package com.softsquared.template.Garamgaebi.src.seminar

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivitySeminarChargedApplyBinding
import java.util.regex.Pattern

class SeminarChargedApplyActivity : BaseActivity<ActivitySeminarChargedApplyBinding>(ActivitySeminarChargedApplyBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //뒤로가기 버튼 누르면 세미나 메인 페이지로
        binding.activitySeminarChargedBackBtn.setOnClickListener {
            startActivity(Intent(this@SeminarChargedApplyActivity, SeminarFreeActivity::class.java ))
        }

        // et selected 여부에 따라 drawable 결정
        binding.activitySeminarChargedApplyNameTv.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
            } else {
                view.setBackgroundResource(R.drawable.et_seminat_apply)
            }
        }
        binding.activitySeminarChargedApplyNicknameTv.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
            } else {
                view.setBackgroundResource(R.drawable.et_seminat_apply)
            }
        }
        binding.activitySeminarChargedApplyPhoneTv.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
            } else {
                view.setBackgroundResource(R.drawable.et_seminat_apply)
            }
        }

        // et에 따라 오류메세지 생성 & drawable 변경 & 신청하기버튼 활성화
        binding.activitySeminarChargedApplyNicknameTv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(!isNickName()){
                    binding.activitySeminarChargedApplyNotCorrectNicknameTv.visibility = View.VISIBLE
                    binding.activitySeminarChargedApplyNicknameTv.setBackgroundResource(R.drawable.activity_seminar_apply_red_border)
                }
                else{
                    binding.activitySeminarChargedApplyNotCorrectNicknameTv.visibility = View.GONE
                    binding.activitySeminarChargedApplyNicknameTv.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
                }
                if(isButton()){

                    binding.activitySeminarChargedApplyBtn.isEnabled = true
                    binding.activitySeminarChargedApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                }
                else {

                    binding.activitySeminarChargedApplyBtn.isEnabled = false
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.activitySeminarChargedApplyPhoneTv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!isPhoneNumberCheck()){
                    binding.activitySeminarChargedApplyNotCorrectPhoneTv.visibility = View.VISIBLE
                    binding.activitySeminarChargedApplyPhoneTv.setBackgroundResource(R.drawable.activity_seminar_apply_red_border)
                }
                else{
                    binding.activitySeminarChargedApplyNotCorrectPhoneTv.visibility = View.GONE
                    binding.activitySeminarChargedApplyPhoneTv.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
                }
                if(isButton()){

                    binding.activitySeminarChargedApplyBtn.isEnabled = true
                    binding.activitySeminarChargedApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                }
                else {

                    binding.activitySeminarChargedApplyBtn.isEnabled = false
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })

        //신청하기 버튼 누르면 버튼 바뀌는 값 전달
        binding.activitySeminarChargedApplyBtn.setOnClickListener {
            val intent = Intent(this@SeminarChargedApplyActivity, SeminarFreeActivity::class.java)
            intent.putExtra("apply_charged", true)
            startActivity(intent)
        }

    }

    //전화번호 형식 맞나
    fun isPhoneNumberCheck() : Boolean {
        var returnValue = false
        val phone = binding.activitySeminarChargedApplyPhoneTv.text.toString()
        val regex = "^\\s*(010|011|012|013|014|015|016|017|018|019)(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$";
        val p = Pattern.compile(regex);
        val m = p.matcher(phone);
        if (m.matches()) {
            returnValue = true;
        }
        return returnValue;
    }
    //닉네임 맞나
    fun isNickName(): Boolean{
        var returnValue = false
        val nickname = binding.activitySeminarChargedApplyNicknameTv.text.toString()
        val regex = "cindy"
        val p = regex.matches(nickname.toRegex())
        if(p){
            returnValue = true;
        }
        return returnValue;
    }
    //닉네임이 맞고 전화번호 형식이 맞으면 버튼이 활성화 되는 함수
    fun isButton():Boolean {
        var returnValue = false
        if(isNickName()&&isPhoneNumberCheck()){
            returnValue = true;
        }
        return returnValue;

    }
}
package com.softsquared.template.Garamgaebi.src.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityProfileEducationBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EduActivity  : BaseActivity<ActivityProfileEducationBinding>(ActivityProfileEducationBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        //편집 정보 저장하기 버튼 클릭이벤트
        binding.activityEducationSaveBtn.setOnClickListener {
            if (checkInfo() == true){
                //교육 저장 기능 추가
            }else{
                //저장 불가 및 이유
            }
        }
        //뒤로가기
        binding.activityEducationBackBtn.setOnClickListener {
            onBackPressed()
        }

        //교육기관 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityEducationEtInstitutionDesc)

        //전공/과정 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityEducationEtMajorDesc)

        //교육기간_시작 입력 시 레이아웃 테두리 변경 -> 달력으로 바꿔야함
        checkDpInput(binding.activityEducationEtStartPeriod)

        //교육기간_종료 시 레이아웃 테두리 변경 -> 달력으로 바꿔야함
        checkDpInput(binding.activityEducationEtEndPeriod)

//재직 정보 date picker
        binding.activityEducationEtStartPeriod .setOnClickListener {
            val orderBottomDialogFragment: DatePickerDialogFragment = DatePickerDialogFragment {
                val arr = it.split(".")

                binding.activityEducationEtStartPeriod.setText(arr[0]+"."+arr[1])

                checkDpInput(binding.activityEducationEtEndPeriod)
            }
            orderBottomDialogFragment.show(supportFragmentManager, orderBottomDialogFragment.tag)
        }
        //종료년월
        binding.activityEducationEtEndPeriod.setOnClickListener {
            val orderBottomDialogFragment: DatePickerDialogFragment = DatePickerDialogFragment {
                val arr = it.split(".")
                if(checkNow(it)){
                    binding.activityEducationCheckbox.isChecked = true
                    binding.activityEducationEtEndPeriod.setText("현재")
                }else{
                    binding.activityEducationCheckbox.isChecked = false
                    binding.activityEducationEtEndPeriod.setText(arr[0]+"."+arr[1])
                }
                checkDpInput(binding.activityEducationEtEndPeriod)
            }
            orderBottomDialogFragment.show(supportFragmentManager, orderBottomDialogFragment.tag)
        }
    }
    private fun checkDpInput(view: TextView){
        if (checkInfo()){
            binding.activityEducationSaveBtn.isClickable = true
            binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
        }else{
            binding.activityEducationSaveBtn.isClickable = false
            binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
        }
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
    private fun checkNow(inputDate :String) : Boolean {
        //현재 교육 중일 때
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        var formatted = current.format(formatter)
        return inputDate >= formatted
    }
    private fun checkInfo() : Boolean{
        var  checkResult = true
        var institution = binding.activityEducationEtInstitutionDesc.text.toString()
        var major = binding.activityEducationEtMajorDesc.text.toString()
        var start = binding.activityEducationEtStartPeriod.text.toString()
        var end = binding.activityEducationEtEndPeriod.text.toString()

        //교육기관 조건 확인 기능
        if(institution.length < 8) checkResult = false

        //전공/과정 조건 확인 기능
        if(major.isEmpty()) {
            checkResult = false
        }
        //시작년월 조건 확인 기능
        if(start.isEmpty()) checkResult = false

        //종료년월 조건 확인 기능
        if(end.isEmpty()) checkResult = false


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

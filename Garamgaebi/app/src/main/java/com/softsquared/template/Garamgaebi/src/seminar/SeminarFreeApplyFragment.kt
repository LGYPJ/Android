package com.softsquared.template.Garamgaebi.src.seminar

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentSeminarFreeApplyBinding
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import java.util.regex.Pattern

class SeminarFreeApplyFragment: BaseFragment<FragmentSeminarFreeApplyBinding>(FragmentSeminarFreeApplyBinding::bind, R.layout.fragment_seminar_free_apply) {

    //화면전환
    var containerActivity: ContainerActivity? = null
    //뷰모델
    //lateinit var seminarButtonViewModel: SeminarButtonViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // et selected 여부에 따라 drawable 결정
        binding.activitySeminarFreeApplyNameTv.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
            } else {
                view.setBackgroundResource(R.drawable.et_seminat_apply)
            }
        }
        binding.activitySeminarFreeApplyNicknameTv.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
            } else {
                view.setBackgroundResource(R.drawable.et_seminat_apply)
            }
        }
        binding.activitySeminarFreeApplyPhoneTv.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
            } else {
                view.setBackgroundResource(R.drawable.et_seminat_apply)
            }
        }

        // et에 따라 오류메세지 생성 & drawable 변경 & 신청하기버튼 활성화
        binding.activitySeminarFreeApplyNicknameTv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(!isNickName()){
                    binding.activitySeminarFreeApplyNotCorrectNicknameTv.visibility = View.VISIBLE
                    binding.activitySeminarFreeApplyNicknameTv.setBackgroundResource(R.drawable.activity_seminar_apply_red_border)
                }
                else{
                    binding.activitySeminarFreeApplyNotCorrectNicknameTv.visibility = View.GONE
                    binding.activitySeminarFreeApplyNicknameTv.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
                }
                if(isButton()){

                    binding.activitySeminarFreeApplyBtn.isEnabled = true
                    binding.activitySeminarFreeApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                }
                else {

                    binding.activitySeminarFreeApplyBtn.isEnabled = false
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.activitySeminarFreeApplyPhoneTv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!isPhoneNumberCheck()){
                    binding.activitySeminarFreeApplyNotCorrectPhoneTv.visibility = View.VISIBLE
                    binding.activitySeminarFreeApplyPhoneTv.setBackgroundResource(R.drawable.activity_seminar_apply_red_border)
                }
                else{
                    binding.activitySeminarFreeApplyNotCorrectPhoneTv.visibility = View.GONE
                    binding.activitySeminarFreeApplyPhoneTv.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
                }
                if(isButton()){

                    binding.activitySeminarFreeApplyBtn.isEnabled = true
                    binding.activitySeminarFreeApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                }
                else {

                    binding.activitySeminarFreeApplyBtn.isEnabled = false
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })

        //신청하기 버튼 누르면 버튼 바뀌는 값 전달 bundle로 전달
        binding.activitySeminarFreeApplyBtn.setOnClickListener {
            val bundle = Bundle()
            bundle.putBoolean("apply",true)
            val seminarFragment = SeminarFragment()
            seminarFragment.arguments = bundle
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_seminar_frame, seminarFragment).commit()
            /*requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
            requireActivity().supportFragmentManager.popBackStack()*/
            /*seminarButtonViewModel = ViewModelProvider(this)[SeminarButtonViewModel::class.java]
            seminarButtonViewModel.button2.value*/
            //seminarButtonViewModel.updateValue(Button(true, "신청완료", R.color.seminar_blue, R.drawable.activity_seminar_apply_done_btn_border))
            //containerActivity!!.openFragmentOnFrameLayout(1)
            /*val sharedPreferences = this.activity?.getSharedPreferences("pref", Context.MODE_PRIVATE)
            sharedPreferences?.edit()?.run {
                putBoolean("true", true)
                containerActivity!!.openFragmentOnFrameLayout(1)
                apply()
            }*/
        }



        //뒤로가기 버튼 누르면 세미나 메인 페이지로 ==> 툴바 적용
        /*binding.activitySeminarFreeBackBtn.setOnClickListener {

        }*/
    }

    //전화번호 형식 맞나
    fun isPhoneNumberCheck() : Boolean {
        var returnValue = false
        val phone = binding.activitySeminarFreeApplyPhoneTv.text.toString()
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
        val nickname = binding.activitySeminarFreeApplyNicknameTv.text.toString()
        val regex = "cindy"
        val p = regex.matches(nickname.toRegex())
        if(p){
            returnValue = true;
        }
        return returnValue;
    }

    fun isButton():Boolean {
        var returnValue = false
        if(isNickName()&&isPhoneNumberCheck()){
            returnValue = true;
        }
        return returnValue;

    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

}
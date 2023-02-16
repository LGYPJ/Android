package com.garamgaebi.garamgaebi.src.main.seminar

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.FragmentSeminarChargedApplyBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.ApplyViewModel
import java.util.regex.Pattern

class SeminarChargedApplyFragment: BaseBindingFragment<FragmentSeminarChargedApplyBinding>(R.layout.fragment_seminar_charged_apply) {

    //화면전환
    var containerActivity: ContainerActivity? = null
    private val viewModel by viewModels<ApplyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //데이터바인딩
        binding.setVariable(BR.item, viewModel)


        //처음에 버튼 비활성화
        binding.activitySeminarChargedApplyBtn.isEnabled = false

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

        // 계좌 복사
        binding.activitySeminarChargedCopyBtn.setOnClickListener {
            val copy = binding.activitySeminarChargedInfoAccount.text
            createClipData(copy as String)
        }

        //신청하기 버튼 누르면 버튼 바뀌는 값 전달 bundle로 전달
        binding.activitySeminarChargedApplyBtn.setOnClickListener {
            //신청 등록 api
            viewModel.postEnroll()
            viewModel.enroll.observe(viewLifecycleOwner, Observer {
                binding.item = viewModel
                if(it.isSuccess){
                    //세미나 메인 화면으로
                    requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
                    requireActivity().supportFragmentManager.popBackStack()
                }
            })
        }

        viewModel.getSeminar()
        viewModel.seminarInfo.observe(viewLifecycleOwner, Observer{
            binding.item = viewModel

        })

    }

    // 이름 형식 맞나
    fun isName() : Boolean {
        var returnValue = false
        val name = binding.activitySeminarChargedApplyNameTv.text.toString()
        val regex = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z]{1,20}$"
        val p = Pattern.compile(regex)
        val m = p.matcher(name)
        if (m.matches()) {
            returnValue = true
        }
        return returnValue;
    }

    //전화번호 형식 맞나
    fun isPhoneNumberCheck() : Boolean {
        var returnValue = false
        val phone = binding.activitySeminarChargedApplyPhoneTv.text.toString()
        val regex = "^[0-9]{11}$"
        val p = Pattern.compile(regex)
        val m = p.matcher(phone)
        if (m.matches()) {
            returnValue = true
        }
        return returnValue
    }
    //닉네임 맞나
    fun isNickName(): Boolean{
        var returnValue = false
        val nickname = binding.activitySeminarChargedApplyNicknameTv.text.toString()
        //나중에 회원가입할 때 닉네임 로컬에 저장해서 regax에 선언하기
        val regex = GaramgaebiApplication.sSharedPreferences.getString("nickname", null)
        val p = regex?.matches(nickname.toRegex())
        if(p == true){
            returnValue = true
        }
        return returnValue
    }

    //버튼 활성화
    fun isButton():Boolean {
        var returnValue = false
        if(isNickName()&&isPhoneNumberCheck()&&isName()){
            returnValue = true
        }
        return returnValue

    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

    // 계좌 복사
    private fun createClipData(copy : String){
        val clipboardManager: ClipboardManager =
        requireActivity().getSystemService(CLIPBOARD_SERVICE) as ClipboardManager
        val clipData: ClipData = ClipData.newPlainText("copy", copy)
        //클립보드에 배치
        clipboardManager.setPrimaryClip(clipData)
    }

}
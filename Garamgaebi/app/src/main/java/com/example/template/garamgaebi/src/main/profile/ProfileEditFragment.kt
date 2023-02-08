package com.example.template.garamgaebi.src.main.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat.getColor
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseFragment
import com.example.template.garamgaebi.databinding.FragmentProfileEditBinding

class ProfileEditFragment :
    BaseFragment<FragmentProfileEditBinding>(FragmentProfileEditBinding::bind, R.layout.fragment_profile_edit) {
    private lateinit var callback: OnBackPressedCallback

    var nickState:Int = 0
    var emailState:Int = 0
    var teamState:Int = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        //편집 정보 저장하기 버튼 클릭이벤트
        binding.activityEducationSaveBtn.setOnClickListener {
            if (checkInfo() == true){
                //회원정보 편집 저장 기능 추가
            }else{
                //저장 불가 및 이유
            }
        }

        //닉네임 입력 시 레이아웃 테두리 변경
        checkNickname(binding.activityEditProfileEtNick)

        //소속 입력 시 레이아웃 테두리 변경
        checkTeam(binding.activityEditProfileEtTeam)

        //이메일 입력 시 레이아웃 테두리 변경
        checkEmail(binding.activityEditProfileEtEmail)

        //자기소개 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityEditProfileEtIntro)


    }
    fun checkEtInput(view: EditText){
        var focusing : Boolean = false

        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            focusing = hasFocus
            if (hasFocus) {
                binding.activityEditProfileEtIntro.setHint("")
                view.setBackgroundResource(R.drawable.basic_black_border_layout)
            } else {
                binding.activityEditProfileEtIntro.setHint("100자 이내로 작성해주세요")
                view.setBackgroundResource(R.drawable.basic_gray_border_layout)
            }
        }
    }

    fun checkNickname(view: EditText){
        var focusing : Boolean = false

        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            focusing = hasFocus
            if (hasFocus) {
                binding.activityEditProfileEtNick.setHint("")
                view.setBackgroundResource(R.drawable.basic_black_border_layout)
            } else {
                binding.activityEditProfileEtNick.setHint("닉네임을 입력해주세요 (최대 8글자")
                view.setBackgroundResource(R.drawable.basic_gray_border_layout)
            }
            if(nickState == -1){
                view.setBackgroundResource(R.drawable.basic_red_border_layout)
            }
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var nick = binding.activityEditProfileEtNick.text.toString()

                //닉네임이 너무 길 때
                if(nick.length > 8) {
                    binding.activityNicknameState.apply {
                        visibility = View.VISIBLE
                        text = "사용 불가능한 닉네임입니다"
                        setTextColor(getColor(requireActivity().applicationContext,R.color.redForText))
                    }
                    binding.activityEditProfileEtNick.setBackgroundResource(R.drawable.basic_red_border_layout)
                    nickState = -1

                    //나머지 경우
                } else {
                    if (focusing){
                        binding.activityEditProfileEtNick.setBackgroundResource(R.drawable.basic_black_border_layout)
                        binding.activityNicknameState.visibility = View.VISIBLE
                    }else {
                        binding.activityEditProfileEtNick.setBackgroundResource(R.drawable.basic_gray_border_layout)
                        binding.activityNicknameState.visibility = View.GONE
                    }
                    //유효한 경우
                    if(nick.isNotEmpty()) {
                        binding.activityNicknameState.apply {
                            visibility = View.VISIBLE
                            text = "사용 가능한 닉네임입니다"
                            setTextColor(getColor(requireActivity().applicationContext,R.color.blueForBtn))
                        }
                        nickState = 1
                    } else{
                        binding.activityNicknameState.visibility = View.GONE
                        nickState = 0
                    }
                }
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

    fun checkTeam(view: EditText){
        var focusing : Boolean = false

        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            focusing = hasFocus
            if (hasFocus) {
                binding.activityEditProfileEtTeam.setHint("")
                view.setBackgroundResource(R.drawable.basic_black_border_layout)
            } else {
                binding.activityEditProfileEtTeam.setHint("소속을 입력해주세요")
                view.setBackgroundResource(R.drawable.basic_gray_border_layout)
            }
            if(teamState == -1){
                view.setBackgroundResource(R.drawable.basic_red_border_layout)
            }
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var team = binding.activityEditProfileEtTeam.text.toString()

                //소속이 너무 길 때
                if(team.length > 8) {
                    binding.activityTeamState.apply {
                        visibility = View.VISIBLE
                        text = "사용 불가능한 소속입니다"
                        setTextColor(getColor(requireActivity().applicationContext,R.color.redForText))
                    }
                    binding.activityEditProfileEtTeam.setBackgroundResource(R.drawable.basic_red_border_layout)
                    teamState = -1

                    //나머지 경우
                } else {

                    if (focusing){
                        binding.activityEditProfileEtTeam.setBackgroundResource(R.drawable.basic_black_border_layout)
                        binding.activityTeamState.visibility = View.VISIBLE
                    }else {
                        binding.activityEditProfileEtTeam.setBackgroundResource(R.drawable.basic_gray_border_layout)
                        binding.activityTeamState.visibility = View.GONE
                    }
                    //유효한 경우
                    if(team.isNotEmpty()) {
                        binding.activityTeamState.apply {
                            visibility = View.VISIBLE
                            text = "사용 가능한 소속입니다"
                            setTextColor(getColor(requireActivity().applicationContext,R.color.blueForBtn))
                        }
                        teamState = 1
                    } else{
                        binding.activityTeamState.visibility = View.GONE
                        teamState = 0
                    }
                }
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
    fun checkEmail(view: EditText){
        var focusing : Boolean = false

        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            focusing = hasFocus
            if (hasFocus) {
                binding.activityEditProfileEtEmail.setHint("")
                view.setBackgroundResource(R.drawable.basic_black_border_layout)
            } else {
                binding.activityEditProfileEtEmail.setHint("이메일을 입력해주세요")
                view.setBackgroundResource(R.drawable.basic_gray_border_layout)
            }
            if(emailState == -1){
                view.setBackgroundResource(R.drawable.basic_red_border_layout)
            }
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val email = binding.activityEditProfileEtEmail.text.toString()

                //유효할 때
                    if(Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()){
                        binding.activityEmailState.apply {
                        visibility = View.VISIBLE
                        text = "사용 가능한 이메일입니다"
                        setTextColor(getColor(requireActivity().applicationContext,R.color.blueForBtn))
                    }
                    emailState = 1
                        if (focusing){
                            binding.activityEditProfileEtEmail.setBackgroundResource(R.drawable.basic_black_border_layout)
                            binding.activityEmailState.visibility = View.VISIBLE
                        }else {
                            binding.activityEditProfileEtEmail.setBackgroundResource(R.drawable.basic_gray_border_layout)
                            binding.activityEmailState.visibility = View.GONE
                        }

                    //유효하지 않을 경우
                } else {
                    if(email.isNotEmpty()) {
                        binding.activityEmailState.apply {
                            visibility = View.VISIBLE
                            text = "이메일 형식이 올바르지 않습니다"
                            setTextColor(getColor(requireActivity().applicationContext,R.color.redForText))
                        }
                        binding.activityEditProfileEtEmail.setBackgroundResource(R.drawable.basic_red_border_layout)
                        emailState = -1
                    } else{
                        binding.activityEmailState.visibility = View.GONE
                        emailState = 0
                    }
                }
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
        return nickState == 1 && teamState == 1 && emailState == 1
    }

}




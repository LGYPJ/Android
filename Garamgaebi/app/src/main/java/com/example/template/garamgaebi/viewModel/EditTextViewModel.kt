package com.example.template.garamgaebi.viewModel

import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.repository.ProfileRepository

class EditTextViewModel : ViewModel(){
//    fun checkNickname(view: EditText){
//        var focusing : Boolean = false
//
//        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
//            focusing = hasFocus
//            if (hasFocus) {
//                binding.activityEditProfileEtNick.setHint("")
//                view.setBackgroundResource(R.drawable.basic_black_border_layout)
//            } else {
//                binding.activityEditProfileEtNick.setHint("닉네임을 입력해주세요 (최대 8글자")
//                view.setBackgroundResource(R.drawable.basic_gray_border_layout)
//            }
//            if(nickState == -1){
//                view.setBackgroundResource(R.drawable.basic_red_border_layout)
//            }
//        }
//        view.addTextChangedListener(object : TextWatcher {
//            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//            override fun afterTextChanged(p0: Editable?) {}
//            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                var nick = binding.activityEditProfileEtNick.text.toString()
//
//                //닉네임이 너무 길 때
//                if(nick.length > 8) {
//                    binding.activityNicknameState.apply {
//                        visibility = View.VISIBLE
//                        text = "사용 불가능한 닉네임입니다"
//                        setTextColor(getColor(requireActivity().applicationContext,R.color.redForText))
//                    }
//                    binding.activityEditProfileEtNick.setBackgroundResource(R.drawable.basic_red_border_layout)
//                    nickState = -1
//
//                    //나머지 경우
//                } else {
//                    if (focusing){
//                        binding.activityEditProfileEtNick.setBackgroundResource(R.drawable.basic_black_border_layout)
//                        binding.activityNicknameState.visibility = View.VISIBLE
//                    }else {
//                        binding.activityEditProfileEtNick.setBackgroundResource(R.drawable.basic_gray_border_layout)
//                        binding.activityNicknameState.visibility = View.GONE
//                    }
//                    //유효한 경우
//                    if(nick.isNotEmpty()) {
//                        binding.activityNicknameState.apply {
//                            visibility = View.VISIBLE
//                            text = "사용 가능한 닉네임입니다"
//                            setTextColor(getColor(requireActivity().applicationContext,R.color.blueForBtn))
//                        }
//                        nickState = 1
//                    } else{
//                        binding.activityNicknameState.visibility = View.GONE
//                        nickState = 0
//                    }
//                }
//                if (checkInfo()){
//                    binding.activityEducationSaveBtn.isClickable = true
//                    binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
//                }else{
//                    binding.activityEducationSaveBtn.isClickable = false
//                    binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
//                }
//
//            }
//        })
//    }


    fun setBackgroundEditTextOnFocus(view: EditText, onFocus : Boolean, check : Boolean){
        Log.d("focus_check","true12")
        with(view) {
            if (onFocus) {
                if (!check) {
                    Log.d("focus_check","red")
                    setBackgroundResource(R.drawable.basic_red_border_layout)
                }else{
                    Log.d("focus_check","true")
                    setBackgroundResource(R.drawable.basic_black_border_layout)
                }
            } else {
                if (!check) {
                    Log.d("focus_check","red")
                    setBackgroundResource(R.drawable.basic_red_border_layout)
                }else{
                    Log.d("focus_check","false")
                    setBackgroundResource(R.drawable.basic_gray_border_layout)
                }
            }

        }
    }
}

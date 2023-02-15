package com.garamgaebi.garamgaebi.common

import android.util.Log
import android.widget.EditText
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:onFocusLost")
    fun EditText.onFocusLost(callback: GaramgaebiFunction.OnFocusLostListener) {
        setOnFocusChangeListener { _, hasFocus ->
            Log.d("focus_check","adapter")
            if (!hasFocus) {
                callback.onFocusLost( this,false)
                Log.d("focus_check","adapter2")
            } else {
                callback.onFocusLost(this,true)
                Log.d("focus_check","adapter3")
            }
        }
    }

    @JvmStatic
    @BindingAdapter("app:onFocusing")
    fun EditText.onFocusing(callback: GaramgaebiFunction.OnFocusingListener) {
        setOnFocusChangeListener { _, hasFocus ->
            Log.d("focus_check","adapter")
            if (!hasFocus) {
                callback.onFocusing(false)
                Log.d("focus_check","adapter2")
            } else {
                callback.onFocusing(true)
                Log.d("focus_check","adapter3")
            }
        }
    }

//    @JvmStatic
//    @BindingAdapter("app:onCheckInput")
//    fun EditText.baseCheckInput(view:EditText,condition:Int) {
//        var focusing : Boolean = false
//        var state : Int = 0
//
//
//        @SuppressLint("ResourceAsColor")
//        fun checkInput(view: EditText){
//            with(view) {
//                onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
//                    focusing = hasFocus
//                    if (hasFocus) {
//                        setHintTextColor(R.color.white)
//                        setBackgroundResource(R.drawable.basic_black_border_layout)
//                    } else {
//                        setHintTextColor(R.color.grayD9)
//                        setBackgroundResource(R.drawable.basic_gray_border_layout)
//                    }
//                    if (state == -1) {
//                        setBackgroundResource(R.drawable.basic_red_border_layout)
//                    }
//                }
//                view.addTextChangedListener(object : TextWatcher {
//                    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
//                    override fun afterTextChanged(p0: Editable?) {}
//                    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
//                        var input = view.text.toString()
//
//                        //닉네임이 너무 길 때
//                        if (input.length > 8) {
//                            state = -1
////                        binding.activityNicknameState.apply {
////                            visibility = View.VISIBLE
////                            text = "사용 불가능한 닉네임입니다"
////                            setTextColor(
////                                ContextCompat.getColor(
////                                    requireActivity().applicationContext,
////                                    R.color.redForText
////                                )
////                            )
////                        }
//                            setBackgroundResource(R.drawable.basic_red_border_layout)
//                            //나머지 경우
//                        } else {
//                            if (focusing) {
//                                setBackgroundResource(R.drawable.basic_black_border_layout)
//                                //binding.activityNicknameState.visibility = View.VISIBLE
//                            } else {
//                                setBackgroundResource(R.drawable.basic_gray_border_layout)
//                                //binding.activityNicknameState.visibility = View.GONE
//                            }
//                            //유효한 경우
//                            if (input.isNotEmpty()) {
////                            binding.activityNicknameState.apply {
////                                visibility = View.VISIBLE
////                                text = "사용 가능한 닉네임입니다"
////                                setTextColor(
////                                    ContextCompat.getColor(
////                                        requireActivity().applicationContext,
////                                        R.color.blueForBtn
////                                    )
////                                )
////                            }
//                                state = 1
//                            } else {
//                                //binding.activityNicknameState.visibility = View.GONE
//                                state = 0
//                            }
//                        }
////                    if (checkInfo()) {
////                        binding.activityEducationSaveBtn.isClickable = true
////                        binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
////                    } else {
////                        binding.activityEducationSaveBtn.isClickable = false
////                        binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
////                    }
//
//                    }
//                })
//            }
//        }
//    }
}
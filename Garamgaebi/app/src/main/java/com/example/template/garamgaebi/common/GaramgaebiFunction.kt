package com.example.template.garamgaebi.common

import android.util.Log
import android.view.View.OnFocusChangeListener
import android.widget.EditText
import androidx.databinding.BindingAdapter
import com.example.template.garamgaebi.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class GaramgaebiFunction {
    fun getDay(date : String): String{
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-mm-dd")).toString()
    }


    //editText focus Listener
//    var onFocusChangeListener =
//        OnFocusChangeListener { view, isFocused ->
//            val origin = view.background
//            if(isFocused){
//                view.setBackgroundResource(R.drawable.basic_black_border_layout)
//            }else{
//                view.background = origin
//            }
//        }
    interface OnFocusLostListener {
        fun onFocusLost(view: EditText,boolean: Boolean)
    }
    fun logTest(view:EditText, check : Boolean,isValid : Boolean){
        Log.d("focus_check_log",view.toString() + check.toString() + isValid.toString())
    }






}
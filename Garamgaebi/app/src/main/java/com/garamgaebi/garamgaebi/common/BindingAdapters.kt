package com.garamgaebi.garamgaebi.common

import android.util.Log
import android.widget.EditText
import androidx.databinding.BindingAdapter

object BindingAdapters {
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
}
package com.garamgaebi.garamgaebi.common

import android.util.Log
import android.widget.EditText
import androidx.databinding.BindingAdapter

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("onFocusing")
    fun EditText.onFocusing(callback: GaramgaebiFunction.OnFocusingListener) {
        setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus) {
                callback.onFocusing(false)
            } else {
                callback.onFocusing(true)
            }
        }
    }
}
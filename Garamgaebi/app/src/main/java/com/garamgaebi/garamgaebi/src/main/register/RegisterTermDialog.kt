package com.garamgaebi.garamgaebi.src.main.home

import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.DialogFragment
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseDialogFragment
import com.garamgaebi.garamgaebi.databinding.DialogRegisterTermBinding

class RegisterTermDialog(): BaseDialogFragment<DialogRegisterTermBinding>(DialogRegisterTermBinding::bind, R.layout.dialog_register_term) {
    override fun init() {
        super.init()
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setGravity(Gravity.CENTER)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dialogRegisterTermWvDesc.apply {
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("file:///android_asset/terms.html")
        }
    }

    override fun onResume() {
        super.onResume()
        requireContext().dialogFragmentResize(this,0.9f, 0.6f)
    }
}
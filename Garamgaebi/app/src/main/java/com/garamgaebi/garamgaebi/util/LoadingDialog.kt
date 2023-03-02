package com.garamgaebi.garamgaebi.util

import android.animation.ObjectAnimator
import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.view.animation.AnimationUtils
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.databinding.DialogLoadingBinding

class LoadingDialog(context: Context) : Dialog(context) {
    private lateinit var binding: DialogLoadingBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        binding = DialogLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setCancelable(false)
        window!!.setBackgroundDrawable(ColorDrawable())
        window!!.setDimAmount(0.2f)
        binding.progressBar.startAnimation(AnimationUtils.loadAnimation(context, R.anim.dialog_loading_rotate))

    }

    override fun show() {
        if(!this.isShowing) super.show()
    }
}
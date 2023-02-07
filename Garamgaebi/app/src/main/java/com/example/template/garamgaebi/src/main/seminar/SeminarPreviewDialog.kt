package com.example.template.garamgaebi.src.main.seminar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseDialog
import com.example.template.garamgaebi.databinding.DialogSeminarPreviewBinding
import com.example.template.garamgaebi.viewModel.SeminarViewModel

class SeminarPreviewDialog: BaseDialog<DialogSeminarPreviewBinding>(R.layout.dialog_seminar_preview){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val viewModel = ViewModelProvider(this)[SeminarViewModel::class.java]
        viewModel.getSeminarsInfo(8)
        viewModel.present.observe(viewLifecycleOwner, Observer {
            val position = arguments?.getInt("presentationDialog", 0)
            val data = it[position!!]
            binding.item = data

        })
        binding.dialogFragmentSeminarCloseBtn.setOnClickListener {
           dismiss()
        }
    }
    companion object {
        @JvmStatic
        @BindingAdapter("profileImg")
        fun loadImage(imageView: ImageView, imageURL:String){
            Glide.with(imageView.context)
                .load(imageURL)
                .into(imageView)
        }
    }


}
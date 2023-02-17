package com.garamgaebi.garamgaebi.src.main.seminar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.DialogFragment

import com.bumptech.glide.Glide
import com.garamgaebi.garamgaebi.databinding.DialogSeminarPreviewBinding
import com.garamgaebi.garamgaebi.model.PresentationResult

class SeminarPreviewDialog(private val present : List<PresentationResult>):DialogFragment() {
     private lateinit var binding : DialogSeminarPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogSeminarPreviewBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        val position = bundle!!.getInt("presentationDialog", 0)
        val data = present[position]
        Glide.with(binding.dialogSeminarProfileImg.context)
            .load(data.profileImgUrl)
            .into(binding.dialogSeminarProfileImg)
        with(binding){
            dialogFragmentSeminarNameTv.text = data.nickname
            dialogFragmentSeminarJobTv.text =data.organization
            dialogFragmentSeminarTitleTv.text = data.title
            dialogFragmentSeminarContentTv.text = data.content
            dialogFragmentSeminarPresentReferenceDetailTv.text = data.presentationUrl
        }


        binding.dialogFragmentSeminarCloseBtn.setOnClickListener {
           dismiss()
        }
    }


}
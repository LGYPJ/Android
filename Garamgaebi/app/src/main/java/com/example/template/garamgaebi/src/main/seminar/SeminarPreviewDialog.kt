package com.example.template.garamgaebi.src.main.seminar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.template.garamgaebi.databinding.DialogSeminarPreviewBinding
import com.example.template.garamgaebi.viewModel.SeminarViewModel

class SeminarPreviewDialog: DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }
    private lateinit var binding: DialogSeminarPreviewBinding

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

        val viewModel = ViewModelProvider(this)[SeminarViewModel::class.java]
        viewModel.getSeminarsInfo(1)
        viewModel.presentation.observe(viewLifecycleOwner, Observer {
            val position = arguments?.getInt("presentationDialog", 0)
            val data = it.result[position!!]
            binding.dialogFragmentSeminarNameTv.text = data.nickname
            binding.dialogFragmentSeminarJobTv.text = data.organization
            binding.dialogFragmentSeminarTitleTv.text = data.title
            binding.dialogFragmentSeminarContentTv.text = data.content
            binding.dialogFragmentSeminarPresentReferenceDetailTv.text = data.presentationUrl
            Glide.with(binding.dialogSeminarProfileImg.context)
                .load(data.profileImgUrl)
                .into(binding.dialogSeminarProfileImg)

        })

        binding.dialogFragmentSeminarCloseBtn.setOnClickListener {
           dismiss()
        }

    }


}
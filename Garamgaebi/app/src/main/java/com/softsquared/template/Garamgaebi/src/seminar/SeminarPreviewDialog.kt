package com.softsquared.template.Garamgaebi.src.seminar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.databinding.ActivityMainBinding.inflate
import com.softsquared.template.Garamgaebi.databinding.DialogSeminarPreviewBinding

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

        binding.dialogFragmentSeminarCloseBtn.setOnClickListener {
           dismiss()
        }

    }

    override fun onResume() {
        super.onResume()
        //val width = resources.getDimensionPixelSize(R.dimen.seminar_dialog_width)
        //val height = resources.getDimensionPixelSize(R.dimen.seminar_dialog_height)
        //dialog?.window?.setLayout(width, height)

    }


}
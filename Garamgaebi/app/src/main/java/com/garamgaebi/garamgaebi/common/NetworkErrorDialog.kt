package com.garamgaebi.garamgaebi.common

import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.databinding.DeleteDialogBinding
import com.garamgaebi.garamgaebi.databinding.NetworkErrorDialogBinding

class NetworkErrorDialog(val itemClick: (Int) -> Unit)
 : DialogFragment() {

    // 뷰 바인딩 정의
    private var _binding: NetworkErrorDialogBinding? = null
    private val binding get() = _binding!!

    private var confirmDialogInterface: ConfirmDialogInterface? = null

    private var text: String? = null
    private var id: Int? = null

    init {
        this.text = text
        this.id = id
        this.confirmDialogInterface = confirmDialogInterface
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = NetworkErrorDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //확인버튼
            // 확인 버튼 클릭 -> 확인
            binding.check.setOnClickListener {
                // 삭제 , 삭제 완료 다이얼로그 띄우기
                itemClick(1)
                dismiss()
            }


        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCancel(dialog: DialogInterface) {
        dismiss()
        super.onCancel(dialog)
    }

    override fun dismiss() {
        super.dismiss()
    }
}

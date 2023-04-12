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

class ConfirmDialog(
    confirmDialogInterface: ConfirmDialogInterface,
    text: String, id: Int,val itemClick: (Int) -> Unit)
 : DialogFragment() {

    // 뷰 바인딩 정의
    private var _binding: DeleteDialogBinding? = null
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
        _binding = DeleteDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //삭제 물음 / 삭제 완료
        binding.deleteDone.text = text

        when (id) {
            1 -> {

                // 예 버튼 클릭 -> 삭제, 다이얼로그 show
                binding.close.setOnClickListener {
                    // 삭제 , 삭제 완료 다이얼로그 띄우기
                    itemClick(1)
                    dismiss()
                }

                // 아니요 버튼 클릭 -> dismiss
                binding.no.setOnClickListener {
                    itemClick(-1)
                    dismiss()
                }
            }
            -1 -> {
                binding.no.visibility = View.GONE
                binding.close.text = getString(R.string.close)

                // 확인 버튼 클릭
                binding.close.setOnClickListener {
                    //this.confirmDialogInterface?.onYesButtonClick(id!!)
                    itemClick(2)
                    dismiss()
                }

            }
            3 -> {
                // 예 버튼 클릭 -> 삭제, 다이얼로그 show
                binding.close.setOnClickListener {
                    // 삭제 , 삭제 완료 다이얼로그 띄우기
                    itemClick(1)
                    dismiss()
                }

                // 아니요 버튼 클릭 -> dismiss
                binding.no.setOnClickListener {
                    itemClick(-1)
                    dismiss()
                }
            }
            4 -> {
                binding.deleteDone.textSize = 16F
                // 예 버튼 클릭 -> 마켓 이동
                binding.close.text = getString(R.string.check)
                binding.close.setOnClickListener {
                    // 삭제 , 삭제 완료 다이얼로그 띄우기
                    itemClick(1)
                    dismiss()
                }

                // 아니요 버튼 클릭 -> dismiss
                binding.no.text = getString(R.string.close)
                binding.no.setOnClickListener {
                    itemClick(-1)
                    dismiss()
                }
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCancel(dialog: DialogInterface) {
        dismiss()
        if(id == -1){
            itemClick(2)
        }
        super.onCancel(dialog)
    }

    override fun dismiss() {
        itemClick(-1)
        super.dismiss()
    }
}

interface ConfirmDialogInterface {
    fun onYesButtonClick(id: Int)
}
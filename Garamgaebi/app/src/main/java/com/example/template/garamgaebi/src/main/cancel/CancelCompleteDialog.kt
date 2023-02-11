package com.example.template.garamgaebi.src.main.cancel

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.template.garamgaebi.adapter.GatheringMyMeetingScheduledRVAdapter
import com.example.template.garamgaebi.databinding.DialogCancelCompleteBinding
import com.example.template.garamgaebi.model.GatheringProgramResult
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.src.main.MainActivity
import com.example.template.garamgaebi.src.main.gathering.GatheringMyMeetingFragment
import com.example.template.garamgaebi.viewModel.ApplyViewModel
import com.example.template.garamgaebi.viewModel.GatheringViewModel

class CancelCompleteDialog: DialogFragment() {

    //화면전환
    var containerActivity: ContainerActivity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }
    private lateinit var binding: DialogCancelCompleteBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogCancelCompleteBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //신청취소 완료 다이얼로그에서 닫기 버튼 누르면 다이얼로그 없어짐
        // 내모임으로 화면전환 추가하기
        binding.dialogCancelCompleteBtn.setOnClickListener {
            //containerActivity!!.openFragmentOnFrameLayout(0)
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra("meeting", true)
            startActivity(intent)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity

    }

}
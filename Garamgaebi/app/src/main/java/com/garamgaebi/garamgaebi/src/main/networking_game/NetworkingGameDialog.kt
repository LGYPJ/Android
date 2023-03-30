package com.garamgaebi.garamgaebi.src.main.networking_game

import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.ConfirmDialogInterface
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.DeleteDialogBinding
import com.garamgaebi.garamgaebi.databinding.DialogIcebreakingBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity

class NetworkingGameDialog :DialogFragment() {
    // 뷰 바인딩 정의
    private var _binding: DialogIcebreakingBinding? =null
    private val binding get() = _binding
    //private var confirmDialogInterface: ConfirmDialogInterface? = null
    var containerActivity: ContainerActivity? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = DialogIcebreakingBinding.inflate(inflater, container, false)
       val view = binding?.root
        // 레이아웃 배경을 투명하게 해줌, 필수 아님
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //취소
        binding?.no?.setOnClickListener {
            dismiss()
        }

        //참가
        binding?.yes?.setOnClickListener {
            containerActivity!!.openFragmentOnFrameLayout(8)
            val temp = GaramgaebiApplication.sSharedPreferences.getString("placeName", null)
            if (temp != null) {
                Log.d("placeName", temp)
                containerActivity!!.networkingPlace(temp)
            }

            //roomId 부여
            val why = GaramgaebiApplication.sSharedPreferences.getString("roomId", null)
            GaramgaebiApplication.sSharedPreferences
                .edit().putString("roomId", why)
                .apply()
            if (why != null) {
                Log.d("roomId", why)
            }
            dismiss()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
//        callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                (activity as ContainerActivity).openFragmentOnFrameLayout(5)
//                (activity as ContainerActivity).supportFragmentManager.beginTransaction().remove(NetworkingGameSelectFragment()).commit()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(this,callback)
    }

}
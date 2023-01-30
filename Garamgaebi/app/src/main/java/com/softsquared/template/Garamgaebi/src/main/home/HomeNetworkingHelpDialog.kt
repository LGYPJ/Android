package com.softsquared.template.Garamgaebi.src.seminar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.softsquared.template.Garamgaebi.databinding.DialogHomeNetworkingHelpBinding
import com.softsquared.template.Garamgaebi.databinding.DialogHomeSeminarHelpBinding

class HomeNetworkingHelpDialog(x : Int,  y : Int): DialogFragment() {
    private val coX = x
    private val coY = y
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }
    private lateinit var binding: DialogHomeNetworkingHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogHomeNetworkingHelpBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setDimAmount(0f)
        dialog?.window?.setGravity(Gravity.LEFT or Gravity.TOP)

        Log.d("test", "$coX $coY")
        dialog?.window?.attributes?.apply {
            x = (coX+ConvertDPtoPX(-23)).toInt()
            y = (coY+ConvertDPtoPX(87)).toInt()
        }
        return binding.root
    }
    fun ConvertDPtoPX(dp: Int): Float {
        val density: Float = requireContext().resources.displayMetrics.density
        return (dp*density+0.5).toFloat()
    }
}
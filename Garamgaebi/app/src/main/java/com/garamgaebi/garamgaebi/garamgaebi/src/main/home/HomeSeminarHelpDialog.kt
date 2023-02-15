package com.garamgaebi.garamgaebi.garamgaebi.src.main.seminar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.garamgaebi.garamgaebi.garamgaebi.databinding.DialogHomeSeminarHelpBinding

class HomeSeminarHelpDialog(x : Int,  y : Int): DialogFragment() {
    private val coX = x
    private val coY = y
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setStyle(STYLE_NO_TITLE, R.style.dialog_fullscreen)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = true
    }
    private lateinit var binding: DialogHomeSeminarHelpBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogHomeSeminarHelpBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.setDimAmount(0f)
        dialog?.window?.setGravity(Gravity.LEFT or Gravity.TOP)

        Log.d("test", "$coX $coY")
        dialog?.window?.attributes?.apply {
            x = (coX+convertDPtoPX(-26)).toInt()
            //y = (coY+convertDPtoPX(-55)).toInt()
            //x = coX
            y = coY
            Log.d("help", "$x $y")
        }
        return binding.root
    }
    private fun convertDPtoPX(dp: Int): Float {
        val density: Float = requireContext().resources.displayMetrics.density
        return (dp*density+0.5).toFloat()
    }
}
package com.softsquared.template.Garamgaebi.src.main.cancel

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityCancelBinding
import com.softsquared.template.Garamgaebi.databinding.ActivityNetworkingBinding
import com.softsquared.template.Garamgaebi.src.seminar.SeminarPreviewDialog

class CancelActivity : BaseActivity<ActivityCancelBinding>(ActivityCancelBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // et selected 여부에 따라 drawable 결정
        binding.activityCancelPay.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
            } else {
                view.setBackgroundResource(R.drawable.et_seminat_apply)
            }
        }

        binding.activityCancelBankTv.setOnClickListener {
            val orderBottomDialogFragment: CancelBankBottomDialogFragment = CancelBankBottomDialogFragment {
                binding.activityCancelBankTv.text = it
                binding.activityCancelBankTv.setTextColor(getColor(R.color.black))
            }
            orderBottomDialogFragment.show(supportFragmentManager, orderBottomDialogFragment.tag)
        }

    }

}
package com.softsquared.template.Garamgaebi.src.main.cancel

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityCancelBinding
import com.softsquared.template.Garamgaebi.databinding.ActivityNetworkingBinding
import com.softsquared.template.Garamgaebi.src.seminar.SeminarPreviewDialog

class CancelActivity : BaseActivity<ActivityCancelBinding>(ActivityCancelBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val bankName = intent.getStringExtra("bank")
        binding.activityCancelBankTv.text = "은행"

        binding.activityCancelBankTv.setOnClickListener {
            CancelBankBottomDialogFragment().show(
                supportFragmentManager, "CancelBankBottomDialog"
            )
            CancelBankBottomDialogFragment().dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            /*val bankName = intent.getStringExtra("bank")
            binding.activityCancelBankTv.text = bankName
            binding.activityCancelBankTv.setTextColor(getColor(R.color.black))*/
        }

        }

    override fun onRestart() {
        super.onRestart()
        val bankName = intent.getStringExtra("bank")
        binding.activityCancelBankTv.text = bankName
        binding.activityCancelBankTv.setTextColor(getColor(R.color.black))
    }




}
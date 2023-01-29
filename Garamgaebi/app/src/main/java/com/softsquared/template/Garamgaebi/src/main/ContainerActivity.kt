package com.softsquared.template.Garamgaebi.src.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityContainerBinding
import com.softsquared.template.Garamgaebi.databinding.ActivityMainBinding
import com.softsquared.template.Garamgaebi.src.main.cancel.CancelCompleteDialog
import com.softsquared.template.Garamgaebi.src.main.cancel.CancelFragment
import com.softsquared.template.Garamgaebi.src.main.home.HomeFragment
import com.softsquared.template.Garamgaebi.src.seminar.SeminarChargedApplyFragment
import com.softsquared.template.Garamgaebi.src.seminar.SeminarFragment
import com.softsquared.template.Garamgaebi.src.seminar.SeminarFreeApplyFragment

class ContainerActivity : BaseActivity<ActivityContainerBinding>(ActivityContainerBinding::inflate), CancelCompleteDialog.CompleteDialogInterface {

    /*private var seminarFragment : SeminarFragment? = null
    private var seminarFreeApplyFragment : SeminarFreeApplyFragment? = null
    private var seminarChargedApplyFragment: SeminarChargedApplyFragment? =null
    private var cancelFragment: CancelFragment? =null*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //툴바
        val toolbar = binding.activityContainerToolbar
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        // 툴바 백버튼
        /*ab.setDisplayHomeAsUpEnabled(false)
        ab.setDisplayShowCustomEnabled(true)
        ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back_35dp)*/


        binding.activitySeminarFreeBackBtn.setOnClickListener {
            onBackPressed()
        }

        /*seminarFragment = SeminarFragment()
        seminarFreeApplyFragment = SeminarFreeApplyFragment()
        seminarChargedApplyFragment = SeminarChargedApplyFragment()
        cancelFragment = CancelFragment()*/


        //컨테이너 액티비티 시작할때 처음 보이는게 세미나 프래그먼트 고정
        //supportFragmentManager.beginTransaction().replace(R.id.activity_seminar_frame, CancelFragment()).commitAllowingStateLoss()



    }

    fun openFragmentOnFrameLayout(int: Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            0 -> finish()
            1 -> transaction.replace(R.id.activity_seminar_frame, SeminarFragment())
            2 -> transaction.replace(R.id.activity_seminar_frame, SeminarFreeApplyFragment()).addToBackStack(null)
            3 -> transaction.replace(R.id.activity_seminar_frame, SeminarChargedApplyFragment()).addToBackStack(null)
            4 -> transaction.replace(R.id.activity_seminar_frame, CancelFragment()).addToBackStack(null)
        }
        transaction.commit()
    }
    
    override fun onStart() {
        super.onStart()

        if(intent.getBooleanExtra("seminar", false)){
            openFragmentOnFrameLayout(1)
        }
        if(intent.getBooleanExtra("cancel", false)){
            openFragmentOnFrameLayout(4)
        }
        val fragmentList = supportFragmentManager.fragments

        for (fragment in fragmentList) {
            if(fragment is SeminarFragment){
                binding.activityContainerToolbarTv.text = "세미나"
            }
            if(fragment is SeminarFreeApplyFragment){
                binding.activityContainerToolbarTv.text = "세미나"
            }
            if(fragment is SeminarChargedApplyFragment){
                binding.activityContainerToolbarTv.text = "세미나"
            }
            if(fragment is CancelFragment){
                binding.activityContainerToolbarTv.text = "신청 취소"
            }

        }

    }

    override fun onYesButtonClick() {
        finish()
    }


}
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
import com.softsquared.template.Garamgaebi.src.seminar.SeminarChargedApplyFragment
import com.softsquared.template.Garamgaebi.src.seminar.SeminarFragment
import com.softsquared.template.Garamgaebi.src.seminar.SeminarFreeApplyFragment

class ContainerActivity : BaseActivity<ActivityContainerBinding>(ActivityContainerBinding::inflate) {

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

        //홈 액티비티에서
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        //컨테이너 액티비티에서 세미나 프래그먼트로
        if(intent.getBooleanExtra("gathering_seminar", false)){
            val fragment = SeminarFragment()
            fragmentTransaction.replace(R.id.activity_seminar_frame, fragment)
        }
        fragmentTransaction.commit()

        binding.activitySeminarFreeBackBtn.setOnClickListener {
            onBackPressed()
        }



    }

    fun openFragmentOnFrameLayout(int: Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            1 -> transaction.replace(R.id.activity_seminar_frame, SeminarFragment())
            2 -> transaction.replace(R.id.activity_seminar_frame, SeminarFreeApplyFragment()).addToBackStack(null)
            3 -> transaction.replace(R.id.activity_seminar_frame, SeminarChargedApplyFragment()).addToBackStack(null)
        }
        transaction.commit()
    }


}
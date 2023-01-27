package com.softsquared.template.Garamgaebi.src.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityContainerBinding
import com.softsquared.template.Garamgaebi.databinding.ActivityMainBinding
import com.softsquared.template.Garamgaebi.src.seminar.SeminarFragment

class ContainerActivity : BaseActivity<ActivityContainerBinding>(ActivityContainerBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //툴바
        val toolbar = binding.activityContainerToolbar
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        ab.setDisplayHomeAsUpEnabled(false)

        //홈 액티비티에서
        /*val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        //gatheringseminarfragment에서 리사이클러뷰 클릭해서 putExtra로 글자 보내고 그 글자면 프래그먼트가 무엇이다 라는 if문으로 해결하려고 했는데 굳이라는 의문이 든다..
        //코드가 너무 복잡해지고,,,,,,,,,,,,,,,,
        if(intent.getStringExtra("gathering_seminar")){

        }
        val fragment = SeminarFragment()
        fragmentTransaction.add(R.id.activity_seminar_frame, fragment)
        fragmentTransaction.commit()*/


    }

}
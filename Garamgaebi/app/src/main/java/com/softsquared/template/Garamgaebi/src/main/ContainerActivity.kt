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
import com.softsquared.template.Garamgaebi.src.main.home.HomeFragment
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
        //val fragmentManager = supportFragmentManager
        //val fragmentTransaction = fragmentManager.beginTransaction()

        //컨테이너 액티비티에서 세미나 프래그먼트로
        /*if(intent.getBooleanExtra("gathering_seminar", false)){
            //val fragment = SeminarFragment()
            //fragmentTransaction.add(R.id.activity_seminar_frame, fragment)
            openFragmentOnFrameLayout(1)
        }*/
        //fragmentTransaction.commit()

        binding.activitySeminarFreeBackBtn.setOnClickListener {
            //supportFragmentManager.beginTransaction().remove(SeminarFragment()).commit()
            //supportFragmentManager.popBackStack()
            onBackPressed()
        }
        //컨테이너 액티비티 시작할때 처음 보이는게 세미나 프래그먼트 고정
        supportFragmentManager.beginTransaction().replace(R.id.activity_seminar_frame, SeminarFragment()).commitAllowingStateLoss()


    }

    fun openFragmentOnFrameLayout(int: Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            1 -> {transaction.replace(R.id.activity_seminar_frame, SeminarFragment()) }
            2 -> transaction.replace(R.id.activity_seminar_frame, SeminarFreeApplyFragment())
            3 -> transaction.replace(R.id.activity_seminar_frame, SeminarChargedApplyFragment())
        }
        transaction.commit()
    }
    
    /*override fun onStart(){
        super.onStart()
        //컨테이너 액티비티 시작할때 처음 보이는게 세미나 프래그먼트 고정
        //supportFragmentManager.beginTransaction().replace(R.id.activity_seminar_frame, SeminarFragment()).commitAllowingStateLoss()
        if(intent.getBooleanExtra("gathering_seminar", false)){
            //val fragment = SeminarFragment()
            //fragmentTransaction.add(R.id.activity_seminar_frame, fragment)
            openFragmentOnFrameLayout(1)
        }
    }*/

    //Listener역할을 할 Interface 생성
    /*interface onBackPressedListener {
        fun onBackPressed()
    }


    override fun onBackPressed(){
        //아래와 같은 코드를 추가하도록 한다
        //해당 엑티비티에서 띄운 프래그먼트에서 뒤로가기를 누르게 되면 프래그먼트에서 구현한 onBackPressed 함수가 실행되게 된다.
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is onBackPressedListener) {
                if(fragment is SeminarFragment){
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                else{
                (fragment as onBackPressedListener).onBackPressed()
                return}
            }
        }

    }*/


}
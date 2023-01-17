package com.softsquared.template.Garamgaebi.src.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityMainBinding
import com.softsquared.template.Garamgaebi.src.main.home.HomeFragment
import com.softsquared.template.Garamgaebi.src.main.myPage.MyPageFragment
import com.softsquared.template.Garamgaebi.src.profile.MyProfileActivity

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.activity_main_frm, HomeFragment()).commitAllowingStateLoss()
        setBottomNavi()
    }
    //이벤트 리스너 역할. 하단 네비게이션 이벤트에 따라 화면을 리턴한다.
    private fun setBottomNavi() {

        binding.activityMainBottomNavi.itemIconTintList = null
        binding.activityMainBottomNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    setFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                //R.id.gathering -> {
                //    setFragment(GatheringFragment())
                //    return@setOnItemSelectedListener true
                //}
                //R.id.profile -> {
                //    setFragment(ProfileFragment())

        binding.activityMainBottomNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.activity_main_btm_nav_home -> {
                    setFragment(HomeFragment())
                    return@setOnItemSelectedListener true
                }
                //R.id.activity_main_btm_nav_gathering -> {
                //    setFragment(GatheringFragment())
                //    return@setOnItemSelectedListener true
                //}
                //R.id.activity_main_btm_nav_profile -> {
                //    setFragment(MyProfileFragment())

                //    return@setOnItemSelectedListener true
                //}
                else -> false
            }
        }

    }
    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.activity_main_frm, fragment).commit()
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.activity_main_frm, fragment).commit()
    }
}
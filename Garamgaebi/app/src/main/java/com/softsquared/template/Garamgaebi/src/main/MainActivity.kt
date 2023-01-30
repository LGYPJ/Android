package com.softsquared.template.Garamgaebi.src.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityMainBinding
import com.softsquared.template.Garamgaebi.src.main.gathering.GatheringFragment
import com.softsquared.template.Garamgaebi.src.main.home.HomeFragment
import com.softsquared.template.Garamgaebi.src.main.profile.MyProfileFragment

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private var homeFragment : HomeFragment? = null
    private var gatheringFragment : GatheringFragment? = null
    private var myProfileFragment : MyProfileFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBottomNavi()
    }
    //이벤트 리스너 역할. 하단 네비게이션 이벤트에 따라 화면을 리턴한다.
    private fun setBottomNavi() {
        homeFragment = HomeFragment()
        gatheringFragment = GatheringFragment()
        myProfileFragment = MyProfileFragment()

        supportFragmentManager.beginTransaction().add(R.id.activity_main_frm, homeFragment!!, "home").commitAllowingStateLoss()
        supportFragmentManager.beginTransaction().add(R.id.activity_main_frm, gatheringFragment!!, "gathering").commitAllowingStateLoss()
        supportFragmentManager.beginTransaction().add(R.id.activity_main_frm, myProfileFragment!!, "myProfile").commitAllowingStateLoss()

        supportFragmentManager.beginTransaction().show(homeFragment!!).commitAllowingStateLoss()
        supportFragmentManager.beginTransaction().hide(gatheringFragment!!).commitAllowingStateLoss()
        supportFragmentManager.beginTransaction().hide(myProfileFragment!!).commitAllowingStateLoss()
        binding.activityMainBottomNavi.selectedItemId = R.id.home

        binding.activityMainBottomNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.activity_main_btm_nav_home -> {
                    supportFragmentManager.beginTransaction().show(homeFragment!!).commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().hide(gatheringFragment!!).commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().hide(myProfileFragment!!).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.activity_main_btm_nav_gathering -> {
                    supportFragmentManager.beginTransaction().hide(homeFragment!!).commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().show(gatheringFragment!!).commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().hide(myProfileFragment!!).commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.activity_main_btm_nav_profile -> {
                    supportFragmentManager.beginTransaction().hide(homeFragment!!).commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().hide(gatheringFragment!!).commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().show(myProfileFragment!!).commitAllowingStateLoss()

                    return@setOnItemSelectedListener true
                }
                else -> false
            }
        }


    }
    fun goGatheringSeminar() {
        binding.activityMainBottomNavi.selectedItemId = R.id.activity_main_btm_nav_gathering
        gatheringFragment!!.setVPSeminar()
    }
    fun goGatheringNetworking() {
        binding.activityMainBottomNavi.selectedItemId = R.id.activity_main_btm_nav_gathering
        gatheringFragment!!.setVPNetworking()
    }


}
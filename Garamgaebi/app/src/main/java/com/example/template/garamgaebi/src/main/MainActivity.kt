package com.example.template.garamgaebi.src.main

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.config.BaseActivity
import com.example.template.garamgaebi.config.GaramgaebiApplication
import com.example.template.garamgaebi.databinding.ActivityMainBinding
import com.example.template.garamgaebi.model.ApiInterface
import com.example.template.garamgaebi.model.LoginRequest
import com.example.template.garamgaebi.model.LoginResponse
import com.example.template.garamgaebi.src.main.gathering.GatheringFragment
import com.example.template.garamgaebi.src.main.home.HomeFragment
import com.example.template.garamgaebi.src.main.profile.MyProfileFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private var homeFragment : HomeFragment? = null
    private var gatheringFragment : GatheringFragment? = null
    private var myProfileFragment : MyProfileFragment? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lateinit var sSharedPreferences: SharedPreferences

        val client = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)
        client.postlogin(LoginRequest("zzangu@gachon.ac.kr","1234"))
            .enqueue(object : Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if(response.isSuccessful){
                        response.body()?.result?.accessToken
                        GaramgaebiApplication.sSharedPreferences =
                            applicationContext.getSharedPreferences("SOFTSQUARED_TEMPLATE_APP", MODE_PRIVATE)
                        val editor = GaramgaebiApplication.sSharedPreferences.edit() //sharedPreferences를 제어할 editor를 선언
                        editor.putString(
                            "login",
                            response.body()?.result?.accessToken
                        ) // key,value 형식으로 저장
                        editor.commit()

                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Log.e("login", "Error", t)
                }
            })

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
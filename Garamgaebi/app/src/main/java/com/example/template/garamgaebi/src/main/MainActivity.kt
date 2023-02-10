package com.example.template.garamgaebi.src.main


import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseActivity
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.common.GaramgaebiApplication.Companion.X_ACCESS_TOKEN
import com.example.template.garamgaebi.common.GaramgaebiApplication.Companion.X_REFRESH_TOKEN
import com.example.template.garamgaebi.databinding.ActivityMainBinding
import com.example.template.garamgaebi.model.ApiInterface
import com.example.template.garamgaebi.model.LoginRequest
import com.example.template.garamgaebi.model.LoginResponse
import com.example.template.garamgaebi.src.main.gathering.GatheringFragment
import com.example.template.garamgaebi.src.main.gathering.GatheringMyMeetingFragment
import com.example.template.garamgaebi.src.main.home.HomeFragment
import com.example.template.garamgaebi.src.main.profile.MyProfileFragment
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ua.naiksoftware.stomp.StompClient


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private var homeFragment: HomeFragment? = null
    private var gatheringFragment: GatheringFragment? = null
    private var myProfileFragment: MyProfileFragment? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        // 임시 로그인
        val client = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)
        client.postLogin(LoginRequest("zzangu@gachon.ac.kr", "1234"))
            .enqueue(object : Callback<LoginResponse> {
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    if (response.isSuccessful) {
                        with(GaramgaebiApplication.sSharedPreferences.edit()) {
                            putString(X_ACCESS_TOKEN, response.body()?.result?.accessToken)
                            putString(X_REFRESH_TOKEN, response.body()?.result?.refreshToken)
                            response.body()?.result?.let { putInt("memberIdx", it.memberIdx) }
                            apply()
                        }
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

        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_frm, homeFragment!!, "home").commitAllowingStateLoss()
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_frm, gatheringFragment!!, "gathering").commitAllowingStateLoss()
        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_frm, myProfileFragment!!, "myProfile").commitAllowingStateLoss()

        supportFragmentManager.beginTransaction().show(homeFragment!!).commitAllowingStateLoss()
        supportFragmentManager.beginTransaction().hide(gatheringFragment!!)
            .commitAllowingStateLoss()
        supportFragmentManager.beginTransaction().hide(myProfileFragment!!)
            .commitAllowingStateLoss()
        binding.activityMainBottomNavi.selectedItemId = R.id.home

        binding.activityMainBottomNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.activity_main_btm_nav_home -> {
                    supportFragmentManager.beginTransaction().show(homeFragment!!)
                        .commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().hide(gatheringFragment!!)
                        .commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().hide(myProfileFragment!!)
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.activity_main_btm_nav_gathering -> {
                    supportFragmentManager.beginTransaction().hide(homeFragment!!)
                        .commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().show(gatheringFragment!!)
                        .commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().hide(myProfileFragment!!)
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.activity_main_btm_nav_profile -> {
                    supportFragmentManager.beginTransaction().hide(homeFragment!!)
                        .hide((homeFragment!!))
                        .commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().hide(gatheringFragment!!)
                        .commitAllowingStateLoss()
                    supportFragmentManager.beginTransaction().show(myProfileFragment!!)
                        .commitAllowingStateLoss()

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

    /*override fun onRestart() {
        super.onRestart()
        GatheringMyMeetingFragment().refreshAdapter()
    }*/


}

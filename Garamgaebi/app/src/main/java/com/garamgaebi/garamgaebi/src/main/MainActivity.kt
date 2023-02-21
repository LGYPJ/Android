package com.garamgaebi.garamgaebi.src.main


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.src.main.gathering.GatheringFragment
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseActivity
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.X_ACCESS_TOKEN
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.X_REFRESH_TOKEN
import com.garamgaebi.garamgaebi.common.MyFirebaseMessagingService
import com.garamgaebi.garamgaebi.databinding.ActivityMainBinding
import com.garamgaebi.garamgaebi.model.ApiInterface
import com.garamgaebi.garamgaebi.model.GatheringProgramResult
import com.garamgaebi.garamgaebi.model.LoginRequest
import com.garamgaebi.garamgaebi.model.LoginResponse

import com.garamgaebi.garamgaebi.src.main.home.HomeFragment
import com.garamgaebi.garamgaebi.src.main.profile.MyProfileFragment
import com.garamgaebi.garamgaebi.src.main.register.RegisterActivity
import com.garamgaebi.garamgaebi.viewModel.HomeViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private var homeFragment: HomeFragment? = null
    private var gatheringFragment: GatheringFragment? = null
    private var myProfileFragment: MyProfileFragment? = null

    //private var gatheringProgramResponse = MutableLiveData<GatheringProgramResponse>()
    var data = mutableListOf<GatheringProgramResult>()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        //Log.d("fireBase", getFcmToken())
        val viewModel by viewModels<HomeViewModel>()
        // login false일때 테스트용
        GaramgaebiApplication.sSharedPreferences.edit().putBoolean("login", true).apply()
        // 자동 로그인
        if(GaramgaebiApplication.sSharedPreferences.getBoolean("login", false)) {
            Log.d("fireBaseTokenInLogin", GaramgaebiApplication.sSharedPreferences.getString("pushToken", "")!!)
            viewModel.postLogin(LoginRequest("cindy1769@gachon.ac.kr",
                GaramgaebiApplication.sSharedPreferences.getString("pushToken", "")!!))

            viewModel.login.observe(this, Observer {
                if(it.isSuccess) {
                    GaramgaebiApplication.sSharedPreferences.edit()
                        .putString(X_ACCESS_TOKEN, it.result.accessToken)
                        .putString(X_REFRESH_TOKEN, it.result.refreshToken)
                        .putInt("memberIdx", it.result.memberIdx)
                        .apply()
                    GaramgaebiApplication.myMemberIdx = it.result.memberIdx
                } else {
                    Log.d("register", "login fail ${it.errorMessage}")
                }
            })
        } else {
            startActivity(Intent(this, RegisterActivity::class.java))
            finish()
        }

        setBottomNavi()
        getFcmToken()
        initDynamicLink()
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

        supportFragmentManager.beginTransaction()
            .show(homeFragment!!)
            .hide(gatheringFragment!!)
            .hide(myProfileFragment!!)
            .commitAllowingStateLoss()
        binding.activityMainBottomNavi.selectedItemId = R.id.home

        binding.activityMainBottomNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.activity_main_btm_nav_home -> {
                    supportFragmentManager.beginTransaction()
                        .show(homeFragment!!)
                        .hide(gatheringFragment!!)
                        .hide(myProfileFragment!!)
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.activity_main_btm_nav_gathering -> {
                    supportFragmentManager.beginTransaction()
                        .hide(homeFragment!!)
                        .show(gatheringFragment!!)
                        .hide(myProfileFragment!!)
                        .commitAllowingStateLoss()
                    return@setOnItemSelectedListener true
                }
                R.id.activity_main_btm_nav_profile -> {
                    supportFragmentManager.beginTransaction()
                        .hide(homeFragment!!)
                        .hide(gatheringFragment!!)
                        .show(myProfileFragment!!)
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

    fun getHelpFrame() {
        Log.d("getHelpFrame", "getHelpFrame")
        binding.activityMainHelpFrm.visibility = View.VISIBLE
        binding.activityMainHelpFrm.setOnClickListener {
            binding.activityMainHelpFrm.visibility = View.GONE
            homeFragment!!.goneSeminarHelp()
            homeFragment!!.goneNetworkingHelp()

        }
    }

    /** DynamicLink */
    fun initDynamicLink() {
        val dynamicLinkData = intent.extras
        if (dynamicLinkData != null) {
            var dataStr = "DynamicLink 수신받은 값\n"
            for (key in dynamicLinkData.keySet()) {
                dataStr += "key: $key / value: ${dynamicLinkData.getString(key)}\n"
            }
            Log.d("firebaseService", dataStr)
            // notificationType을 받아서 세미나/네트워킹으로 이동
            if(dynamicLinkData.getString("programType", "") == getString(R.string.seminarUpCase)) {
                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("programIdx", dynamicLinkData.getString("programIdx")!!.toInt())
                    .apply()
                startActivity(Intent(this, ContainerActivity::class.java)
                    .putExtra("seminar", true))
            } else {
                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("programIdx", dynamicLinkData.getString("programIdx")!!.toInt())
                    .apply()
                startActivity(Intent(this, ContainerActivity::class.java)
                    .putExtra("networking", true))
            }
        }

    }

    private fun getFcmToken(){
        /** FCM설정, Token값 가져오기 */
        MyFirebaseMessagingService().getFirebaseToken()
    }
}






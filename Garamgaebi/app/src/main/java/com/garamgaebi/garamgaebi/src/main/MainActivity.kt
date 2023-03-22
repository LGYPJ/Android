package com.garamgaebi.garamgaebi.src.main


import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseActivity
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.X_ACCESS_TOKEN
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.X_REFRESH_TOKEN
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.common.MyFirebaseMessagingService
import com.garamgaebi.garamgaebi.databinding.ActivityMainBinding
import com.garamgaebi.garamgaebi.model.AutoLoginRequest
import com.garamgaebi.garamgaebi.src.main.gathering.GatheringFragment
import com.garamgaebi.garamgaebi.src.main.home.HomeFragment
import com.garamgaebi.garamgaebi.src.main.profile.MyProfileFragment
import com.garamgaebi.garamgaebi.src.main.register.LoginActivity
import com.garamgaebi.garamgaebi.src.main.register.RegisterActivity
import com.garamgaebi.garamgaebi.viewModel.HomeViewModel
import kotlinx.coroutines.*


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private var homeFragment: HomeFragment? = null
    private var gatheringFragment: GatheringFragment? = null
    private var myProfileFragment: MyProfileFragment? = null
    private val myFirebaseMessagingService = MyFirebaseMessagingService()
    val viewModel by viewModels<HomeViewModel>()
    private val mFcmPushBroadcastReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d("firebasePushBroadcast", "firebasePushBroadcast")
            homeFragment!!.updateNotificationUnread()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        getFcmToken()
        showLoadingDialog(this)
        //  Log.d("fireBaseTokenInLogin", sSharedPreferences.getString("pushToken", "")!!)


        // 로그인 액티비티에서 넘어왔는지
        var fromLogin = false
        CoroutineScope(Dispatchers.Main).launch {
            fromLogin = withContext(Dispatchers.IO) { // 비동기 작업 시작
                GaramgaebiApplication().loadBooleanData("fromLoginActivity")
            } == true // 결과 대기
            if(fromLogin) {
                setBottomNavi()

                LocalBroadcastManager.getInstance(this@MainActivity).registerReceiver(mFcmPushBroadcastReceiver, IntentFilter("fcmPushListener"))
                initDynamicLink()
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) { // 비동기 작업 시작
                        GaramgaebiApplication().saveBooleanToDataStore("fromLoginActivity", false)
                    } // 결과 대기
                }
            } else {
                autoLogin()
            }
        }
    }
    private fun autoLogin() {
        // 로그인 테스트용
        /*sSharedPreferences.edit()
            .putString(X_REFRESH_TOKEN, "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyNjY3NjI5OTIxIiwiYXV0aCI6IlJPTEVfVVNFUiIsIm1lbWJlcklkeCI6MiwiZXhwIjoxNjc4NTUwNDQyfQ.Y_trlVjHkq0kP04hwu6rMfUjxkPMilItQao7kzsrnNM")
            .putString(X_ACCESS_TOKEN, "eyJhbGciOiJIUzI1NiJ9.eyJtZW1iZXJJZHgiOjIsImV4cCI6MTY4MTE0MDY0Mn0.0HYiClU4TzWoBk8A71qRY4NxHsTeg_rkp_SBunh1rQk")
            .apply()*/
        //Log.d("login", "${sSharedPreferences.getString(X_REFRESH_TOKEN, "")}")
        // 자동 로그인
        var refreshToken = ""
        CoroutineScope(Dispatchers.Main).launch {
            refreshToken = withContext(Dispatchers.IO) { // 비동기 작업 시작
                GaramgaebiApplication().loadStringData(X_REFRESH_TOKEN)
            }.toString() // 결과 대기
            Log.d("대참사,",refreshToken)
            if(refreshToken == "") {
                dismissLoadingDialog()
                startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
                finish()
            } else {
                viewModel.postAutoLogin(AutoLoginRequest(refreshToken))
                viewModel.autoLogin.observe(this@MainActivity, Observer {
                    if(it.isSuccess) {
                        CoroutineScope(Dispatchers.Main).launch {
                            withContext(Dispatchers.IO) { // 비동기 작업 시작
                                GaramgaebiApplication().saveStringToDataStore(
                                    X_ACCESS_TOKEN,
                                    it.result.tokenInfo.accessToken
                                )
                                GaramgaebiApplication().saveStringToDataStore(
                                    X_REFRESH_TOKEN,
                                    it.result.tokenInfo.refreshToken
                                )
                                GaramgaebiApplication().saveIntToDataStore(
                                    "memberIdx",
                                    it.result.tokenInfo.memberIdx
                                )
                                GaramgaebiApplication().saveStringToDataStore("uniEmail",it.result.uniEmail)

                            } // 결과 대기
                            myMemberIdx = it.result.tokenInfo.memberIdx
                            setBottomNavi()
                            LocalBroadcastManager.getInstance(this@MainActivity).registerReceiver(mFcmPushBroadcastReceiver, IntentFilter("fcmPushListener"))
                            initDynamicLink()
                        }

                    } else {
                        Log.d("login", "login fail ${it.errorMessage}")
                        dismissLoadingDialog()
                        CoroutineScope(Dispatchers.Main).launch {
                            withContext(Dispatchers.IO) { // 비동기 작업 시작
                                GaramgaebiApplication().saveStringToDataStore(
                                    X_ACCESS_TOKEN,
                                    ""
                                )
                                GaramgaebiApplication().saveStringToDataStore(
                                    X_REFRESH_TOKEN,
                                    ""
                                )
                                GaramgaebiApplication().saveIntToDataStore("memberIdx", -1)
                            } // 결과 대기
                            myMemberIdx = -1
                            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                            finish()
                        }
                    }
                })
            }
        }

    }

    //이벤트 리스너 역할. 하단 네비게이션 이벤트에 따라 화면을 리턴한다.
    @SuppressLint("ResourceType")
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

    fun getSeminarHelpFrame() {
        Log.d("getHelpFrame", "getHelpFrame")
        binding.activityMainHelpFrm.visibility = View.VISIBLE
        binding.activityMainHelpFrm.setOnClickListener {
            binding.activityMainHelpFrm.visibility = View.GONE
            homeFragment!!.goneSeminarHelp()
        }
    }
    fun getNetworkingHelpFrame() {
        Log.d("getHelpFrame", "getHelpFrame")
        binding.activityMainHelpFrm.visibility = View.VISIBLE
        binding.activityMainHelpFrm.setOnClickListener {
            binding.activityMainHelpFrm.visibility = View.GONE
            homeFragment!!.goneNetworkingHelp()
        }
    }

    private fun getFcmToken(){
        /** FCM설정, Token값 가져오기 */
        myFirebaseMessagingService.getFirebaseToken()
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
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) { // 비동기 작업 시작
                        GaramgaebiApplication().saveIntToDataStore(
                            "programIdx",
                            dynamicLinkData.getString("programIdx")!!.toInt()
                        )
                    } // 결과 대기
                    startActivity(Intent(this@MainActivity, ContainerActivity::class.java)
                        .putExtra("seminar", true))
                }
            } else {
                CoroutineScope(Dispatchers.Main).launch {
                    withContext(Dispatchers.IO) { // 비동기 작업 시작
                        GaramgaebiApplication().saveIntToDataStore(
                            "programIdx",
                            dynamicLinkData.getString("programIdx")!!.toInt()
                        )
                    } // 결과 대기
                    startActivity(Intent(this@MainActivity, ContainerActivity::class.java)
                        .putExtra("networking", true))
                }
            }
        }

    }

    override fun onDestroy() {
        keyboardVisibilityUtils.detachKeyboardListeners()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mFcmPushBroadcastReceiver)
        super.onDestroy()
    }

}
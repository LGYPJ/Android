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
import androidx.fragment.app.Fragment
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
import com.garamgaebi.garamgaebi.util.NetworkDisconnectedFragment
import com.garamgaebi.garamgaebi.viewModel.HomeViewModel
import kotlinx.coroutines.*


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {
    private var homeFragment: HomeFragment? = null
    private var gatheringFragment: GatheringFragment? = null
    private var myProfileFragment: MyProfileFragment? = null
    private val networkDisconnectedFragment = NetworkDisconnectedFragment()
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
        Log.d("network", "isConnectedTrue")
        getFcmToken()

        CoroutineScope(Dispatchers.Main).launch {
            showLoadingDialog(this@MainActivity)
            //  Log.d("fireBaseTokenInLogin", sSharedPreferences.getString("pushToken", "")!!)
            // 로그인 액티비티에서 넘어왔는지
            var fromLogin = false
            fromLogin = withContext(Dispatchers.IO) { // 비동기 작업 시작
                GaramgaebiApplication().loadBooleanData("fromLoginActivity")
            } == true // 결과 대기
            if (fromLogin) {
                setBottomNav()
                LocalBroadcastManager.getInstance(this@MainActivity)
                    .registerReceiver(mFcmPushBroadcastReceiver, IntentFilter("fcmPushListener"))
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
        // 자동 로그인
        var refreshToken = ""
        CoroutineScope(Dispatchers.Main).launch {
            refreshToken = withContext(Dispatchers.IO) { // 비동기 작업 시작
                GaramgaebiApplication().loadStringData(X_REFRESH_TOKEN)
            }.toString() // 결과 대기
            if(refreshToken == "") {
                dismissLoadingDialog()
                startActivity(Intent(this@MainActivity, RegisterActivity::class.java))
                finish()
            } else {
                val networkObserver = object : Observer<Boolean> {
                    override fun onChanged(isConnected: Boolean) {
                        if (isConnected) {
                            networkValid.removeObserver(this)
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
                                        Log.d("memberIdx", "$myMemberIdx")
                                        setBottomNav()
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
                networkValid.observe(this@MainActivity, networkObserver)
            }
        }

    }

    //이벤트 리스너 역할. 하단 네비게이션 이벤트에 따라 화면을 리턴한다.
    @SuppressLint("ResourceType")
    private fun setBottomNav() {
        homeFragment = HomeFragment()
        gatheringFragment = GatheringFragment()
        myProfileFragment = MyProfileFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.activity_main_frm, homeFragment!!, "home")
            .add(R.id.activity_main_frm, gatheringFragment!!, "gathering")
            .add(R.id.activity_main_frm, myProfileFragment!!, "myProfile")
            .add(R.id.activity_main_frm, networkDisconnectedFragment, "networkDisconnected")
            .commitAllowingStateLoss()
        binding.activityMainBottomNavi.selectedItemId = R.id.home

        networkValid.observe(this, Observer { isConnected ->
            val selectedFragment = when (binding.activityMainBottomNavi.selectedItemId) {
                R.id.activity_main_btm_nav_home -> homeFragment
                R.id.activity_main_btm_nav_gathering -> gatheringFragment
                R.id.activity_main_btm_nav_profile -> myProfileFragment
                else -> null
            }
            updateFragmentsVisibility(isConnected, selectedFragment)
        })

        binding.activityMainBottomNavi.setOnItemSelectedListener { item ->
            val isConnected = networkValid.value == true
            when (item.itemId) {
                R.id.activity_main_btm_nav_home -> {
                    updateFragmentsVisibility(isConnected, homeFragment)
                    true
                }
                R.id.activity_main_btm_nav_gathering -> {
                    updateFragmentsVisibility(isConnected, gatheringFragment)
                    true
                }
                R.id.activity_main_btm_nav_profile -> {
                    updateFragmentsVisibility(isConnected, myProfileFragment)
                    true
                }
                else -> false
            }
        }


    }

    private fun updateFragmentsVisibility(isConnected: Boolean, targetFragment: Fragment?) {
        supportFragmentManager.beginTransaction().apply {
            if (isConnected) {
                hide(networkDisconnectedFragment)
                hide(homeFragment!!)
                hide(gatheringFragment!!)
                hide(myProfileFragment!!)
                show(targetFragment!!)
            } else {
                hide(homeFragment!!)
                hide(gatheringFragment!!)
                hide(myProfileFragment!!)
                show(networkDisconnectedFragment)
            }
        }.commitAllowingStateLoss()
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
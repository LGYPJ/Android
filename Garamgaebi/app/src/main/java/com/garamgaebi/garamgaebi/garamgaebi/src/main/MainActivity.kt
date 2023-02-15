package com.garamgaebi.garamgaebi.garamgaebi.src.main


import android.os.Bundle
import android.util.Log
import android.view.View
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.garamgaebi.common.BaseActivity
import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.X_ACCESS_TOKEN
import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.X_REFRESH_TOKEN
import com.garamgaebi.garamgaebi.databinding.ActivityMainBinding
import com.garamgaebi.garamgaebi.garamgaebi.model.ApiInterface
import com.garamgaebi.garamgaebi.garamgaebi.model.GatheringProgramResult
import com.garamgaebi.garamgaebi.garamgaebi.model.LoginRequest
import com.garamgaebi.garamgaebi.garamgaebi.model.LoginResponse
import com.garamgaebi.garamgaebi.garamgaebi.src.main.gathering.GatheringFragment
import com.garamgaebi.garamgaebi.garamgaebi.src.main.home.HomeFragment
import com.garamgaebi.garamgaebi.garamgaebi.src.main.profile.MyProfileFragment
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
        super.onCreate(savedInstanceState)

        // 임시 로그인
        val client = GaramgaebiApplication.sRetrofit.create(ApiInterface::class.java)
        client.postLogin(LoginRequest("chaewon1769@daum.net"))
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
        gatheringFragment!!.setVPSeminar()
        binding.activityMainBottomNavi.selectedItemId = R.id.activity_main_btm_nav_gathering
    }

    fun goGatheringNetworking() {
        gatheringFragment!!.setVPNetworking()
        binding.activityMainBottomNavi.selectedItemId = R.id.activity_main_btm_nav_gathering
    }

    private fun goGathering() {
        val findFragment = supportFragmentManager.findFragmentByTag("gathering")
        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction().hide(fm).commitAllowingStateLoss()
        }
        findFragment?.let {
            // 프래그먼트 상태 정보가 있는 경우, 보여주기만
            supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        }
    }

    //뒤로가기 이슈 해결 코드....
    fun onMove(int: Int) {
        super.onStart()
        when(int){
            1 -> {
                goGathering()
                binding.activityMainBottomNavi.selectedItemId = R.id.activity_main_btm_nav_gathering
                gatheringFragment!!.setVPmy()
                intent.removeExtra("meeting")
                intent.removeExtra("networking1")
            }
            2 -> {
                if(isHome()){
                    gatheringFragment!!.setVPSeminar()

                }
                if(isProfile()){
                    gatheringFragment!!.setVPSeminar()
                }
            }
            3 -> {
                goGathering()
                binding.activityMainBottomNavi.selectedItemId = R.id.activity_main_btm_nav_gathering
                gatheringFragment!!.setVPSeminar()
                intent.removeExtra("goseminar1")
                intent.removeExtra("gathering-seminar1")
            }
            4 -> {
                goGathering()
                binding.activityMainBottomNavi.selectedItemId = R.id.activity_main_btm_nav_gathering
                gatheringFragment!!.setVPNetworking()
                intent.removeExtra("gonetworking1")
            }
            else -> {

            }
        }
    }
   // 뒤로가기 이슈 해결 코드,,,
    override fun onStart() {
        super.onStart()
        if(isHome()){
            onMove(2)
        }
        if(isProfile()){
            onMove(2)
        }
        if(isGathering()){
            if(intent.getStringExtra("networking1") == "networking1"){
                onMove(1)
            }
            if (intent.getStringExtra("meeting") == "meeting") {
                onMove(1)
            }
            if(intent.getStringExtra("goseminar1")=="goseminar1"){
                onMove(3)
            }
            if(intent.getStringExtra("gonetworking1")== "gonetworking1"){
                onMove(4)
            }
            if(intent.getStringExtra("gathering-seminar1") == "gathering-seminar1"){
                onMove(3)
            }

        }


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

    fun isGathering ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is GatheringFragment) {
                returnValue = true
            }
        }
        return returnValue
    }

    fun isHome ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is HomeFragment) {
                returnValue = true
            }
        }
        return returnValue
    }

    fun isProfile ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is MyProfileFragment) {
                returnValue = true
            }
        }
        return returnValue
    }
}






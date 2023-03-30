package com.garamgaebi.garamgaebi.src.main


import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.MutableLiveData
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.fragmentHashMap
import com.garamgaebi.garamgaebi.databinding.ActivityContainerBinding
import com.garamgaebi.garamgaebi.src.main.cancel.CancelFragment
import com.garamgaebi.garamgaebi.src.main.home.NotificationFragment
import com.garamgaebi.garamgaebi.src.main.networking.NetworkingChargedApplyFragment
import com.garamgaebi.garamgaebi.src.main.networking.NetworkingFragment
import com.garamgaebi.garamgaebi.src.main.networking.NetworkingFreeApplyFragment
import com.garamgaebi.garamgaebi.src.main.networking_game.NetworkingGamePlaceFragment
import com.garamgaebi.garamgaebi.src.main.networking_game.NetworkingGameSelectFragment
import com.garamgaebi.garamgaebi.src.main.profile.*
import com.garamgaebi.garamgaebi.src.main.seminar.SeminarChargedApplyFragment
import com.garamgaebi.garamgaebi.src.main.seminar.SeminarFragment
import com.garamgaebi.garamgaebi.src.main.seminar.SeminarFreeApplyFragment
import com.garamgaebi.garamgaebi.util.NetworkDisconnectedFragment
import kotlinx.coroutines.*


class ContainerActivity : BaseActivity<ActivityContainerBinding>(ActivityContainerBinding::inflate) {
    var fragmentTag: Int = -1
    private var currentFragment: Fragment? = null
    var game =""
    val ignoreFirst : MutableLiveData<Int> = MutableLiveData<Int>(0)

    override fun onResume() {
        super.onResume()
        currentFragment()
        Log.d("돼라","ㅇㅇ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkValid.observe(this) {
            ignoreFirst.value = ignoreFirst.value!! + 1
            Log.d("network", "containerActivity networkObserver it : $it, isConnected : ${networkValid.value}")
            if(ignoreFirst.value!! > 2) {
                openFragmentOnFrameLayout(fragmentTag)
            }
        }

        //툴바
        val toolbar = binding.activityContainerToolbar
        setSupportActionBar(toolbar)
        val ab = supportActionBar!!
        ab.setDisplayShowTitleEnabled(false)
        // 툴바 백버튼
        /*ab.setDisplayHomeAsUpEnabled(false)
        ab.setDisplayShowCustomEnabled(true)
        ab.setHomeAsUpIndicator(R.drawable.ic_arrow_back_35dp)*/
        binding.activitySeminarFreeBackBtn.setOnClickListener {
        onBackPressed()
        }

    }
    fun currentFragment(){
        Log.d("currentFragment","개수개수"+supportFragmentManager.backStackEntryCount)
        if(supportFragmentManager.backStackEntryCount > 0) {
            Log.d("currentFragment", "0이상 $fragmentTag")
            var index = supportFragmentManager.backStackEntryCount - 1
            var backEntry = supportFragmentManager.getBackStackEntryAt(index)
            var tag = backEntry.name
            //backStack 맨 위의 값과 현재 프래그먼트의 값이 같아야함
            if(fragmentTag != tag?.toInt() && tag?.toInt() != GAME) {
                supportFragmentManager.popBackStack()
                tag?.toInt()?.let { openFragmentOnFrameLayout(it) }
            }
        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val backStackEntryCount = fragmentManager.backStackEntryCount

        Log.d("network", "onBackPressed backStackCount : ${supportFragmentManager.backStackEntryCount}")
        if(isWithdrawal()){
            binding.activityContainerToolbarTv.text = "고객 센터"
            super.onBackPressed()

        }else{
            super.onBackPressed()
        }
        if (isProfileEdit()) {
            CoroutineScope(Dispatchers.Main).launch {
                async(Dispatchers.IO) { // 비동기 작업 시작
                    GaramgaebiApplication().saveBooleanToDataStore("EditImage",false)
                }.await() // 결과 대기
            }
        }
        if(isWithdrawal()) {
            binding.activityContainerToolbarTv.text = "고객 센터"
        }
        if(isNetworking()){
            binding.activityContainerToolbarTv.text ="네트워킹"
        }
        if(isSeminar()){
            binding.activityContainerToolbarTv.text = "세미나"
        }
        if(isInGame()){
            binding.activityContainerToolbarTv.text = "아이스브레이킹"
        }

        var currentFragmentCheck =
            supportFragmentManager.findFragmentById(R.id.activity_container_frame)

        binding.activityContainerToolbarTv.text = fragmentHashMap[currentFragmentCheck?.tag?.toInt()]
        Log.d("fragment 가자","?"+fragmentHashMap[currentFragmentCheck?.tag?.toInt()] + currentFragmentCheck?.tag.toString() + currentFragmentCheck)
        Log.d("fragment 가자","개수개수"+supportFragmentManager.backStackEntryCount)
    }


    fun openFragmentOnFrameLayout(tag: Int){
        val transaction = supportFragmentManager.beginTransaction()
        Log.d("network", "containerActivity openFragmentOnFrameLayout backStack : ${supportFragmentManager.backStackEntryCount}")
        fragmentTag = tag
        if((networkValid.value == false) && tag in listOf(1, 5, 7, 13, 16, 20)) {
            transaction.replace(R.id.activity_container_frame, NetworkDisconnectedFragment())
        } else {
            binding.activityContainerToolbarTv.text = fragmentHashMap[tag]
            when(tag){
                SEMINAR -> {
                    transaction.replace(R.id.activity_container_frame, SeminarFragment(),SEMINAR.toString()).apply {
                        if(isNotifi()){
                            addToBackStack(SEMINAR.toString())
                        }
                    }
                }
                SEMINAR_APPLY_FREE -> {transaction.replace(R.id.activity_container_frame, SeminarFreeApplyFragment(),
                    SEMINAR_APPLY_FREE.toString()).addToBackStack(
                    SEMINAR_APPLY_FREE.toString())
                }
                SEMINAR_APPLY_CHARGED -> {transaction.replace(R.id.activity_container_frame, SeminarChargedApplyFragment(),
                    SEMINAR_APPLY_CHARGED.toString()).addToBackStack(SEMINAR_APPLY_CHARGED.toString())
                }

                CANCEL -> {transaction.replace(R.id.activity_container_frame, CancelFragment(),
                    CANCEL.toString())
                }
                NETWORKING -> {transaction.replace(R.id.activity_container_frame, NetworkingFragment(),
                    NETWORKING.toString()).apply {
                    if(isNotifi()){
                        addToBackStack(NETWORKING.toString())
                    }
                }
                }
                NETWORKING_APPLY_FREE -> {transaction.replace(R.id.activity_container_frame, NetworkingFreeApplyFragment(),
                    NETWORKING_APPLY_FREE.toString()).addToBackStack(NETWORKING_APPLY_FREE.toString())
                }
                ICEBREAKING -> {transaction.replace(R.id.activity_container_frame, NetworkingGameSelectFragment(),
                    ICEBREAKING.toString()).addToBackStack(ICEBREAKING.toString())
                }
                GAME -> {transaction.replace(R.id.activity_container_frame, NetworkingGamePlaceFragment(),GAME.toString()).addToBackStack("게임")
                    binding.activityContainerToolbarTv.text = game
                }
                //승민 부분
                SNS_ADD -> {
                    transaction.replace(R.id.activity_container_frame, SnsAddFragment(), SNS_ADD.toString())
                }
                CAREER_ADD -> {
                    transaction.replace(R.id.activity_container_frame, CareerAddFragment(),
                        CAREER_ADD.toString())
                }
                EDU_ADD -> {
                    transaction.replace(R.id.activity_container_frame, EduAddFragment(), EDU_ADD.toString())
                }
                PROFILE_EDIT -> {
                    transaction.replace(R.id.activity_container_frame, ProfileEditFragment(),
                        PROFILE_EDIT.toString())
                }
                USER_PROFILE -> {
                    if(isSeminar()){
                        transaction.replace(R.id.activity_container_frame, UserProfileFragment(),
                            USER_PROFILE.toString()).addToBackStack(USER_PROFILE.toString())
                    }
                    if(isNetworking()){
                        transaction.replace(R.id.activity_container_frame, UserProfileFragment(),USER_PROFILE.toString()).addToBackStack(USER_PROFILE.toString())
                    }
                    if(isNotifi()){
                        transaction.replace(R.id.activity_container_frame, UserProfileFragment(),USER_PROFILE.toString()).addToBackStack(USER_PROFILE.toString())
                    }
                    else{
                        transaction.replace(R.id.activity_container_frame, UserProfileFragment(),USER_PROFILE.toString())
                    }
                }
                SERVICE_CENTER -> {
                    transaction.replace(R.id.activity_container_frame, ServiceCenterFragment(),SERVICE_CENTER.toString())
                }
                WITHDRAWAL -> {
                    transaction.replace(R.id.activity_container_frame, WithdrawalFragment(),WITHDRAWAL.toString()).addToBackStack(WITHDRAWAL.toString())
                    //binding.activityContainerToolbarTv.text = "회원탈퇴"
                }
                //동원 부분
                NOTIFICATION -> {
                    transaction.replace(R.id.activity_container_frame, NotificationFragment(),NOTIFICATION.toString())
                }
                //승민 추가
                SNS_EDIT -> {
                    transaction.replace(R.id.activity_container_frame, SnsEditFragment(),SNS_EDIT.toString())
                }
                CAREER_EDIT -> {
                    transaction.replace(R.id.activity_container_frame, CareerEditFragment(),CAREER_EDIT.toString())
                }
                EDU_EDIT -> {
                    transaction.replace(R.id.activity_container_frame, EduEditFragment(),EDU_EDIT.toString())
                }

                //신디 추가 네트워킹 유료 신청
                NETWORKING_APPLY_CHARGED -> {
                    transaction.replace(R.id.activity_container_frame, NetworkingChargedApplyFragment(),NETWORKING_APPLY_CHARGED.toString()).addToBackStack(NETWORKING_APPLY_CHARGED.toString())
                    binding.activityContainerToolbarTv.text = "네트워킹"
                }
            }
            Log.d("currentFragment", "태그 end open $fragmentTag")
        }
        transaction.commit()
        Log.d("container", "onBackPressed backStackCount : ${supportFragmentManager.backStackEntryCount}")
    }


    fun networkingPlace(place: String){
        binding.activityContainerToolbarTv.text = place
        game = place
    }
    fun checkNetwork(): Boolean{
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        val isConnected = activeNetwork?.isConnectedOrConnecting == true

        return isConnected
    }

    override fun onStart() {
        super.onStart()
        networkValid.value = checkNetwork()
        if(checkNetwork()) {
            if (isSeminarCharged()) {
                binding.activityContainerToolbarTv.text = "세미나"
            }
            var tag = intent.getIntExtra("openFragment", 0)
            openFragmentOnFrameLayout(tag)
            fragmentTag = tag
            binding.activityContainerToolbarTv.text = fragmentHashMap[tag]
        }else{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_container_frame, NetworkDisconnectedFragment())
        }
    }
   //안드로이드 뒤로가기 버튼 눌렀을때
    fun isProfileEdit ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if(fragment is ProfileEditFragment){
                returnValue = true
            }
        }
        return returnValue
    }

    fun isWithdrawal ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if(fragment is WithdrawalFragment){
                returnValue = true
            }
        }
        return returnValue
    }

    fun isIceBreaking ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if(fragment is NetworkingGameSelectFragment){
                returnValue = true
            }
        }
        return returnValue
    }
    fun isNetworking ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if(fragment is NetworkingFragment){
                returnValue = true
            }
        }
        return returnValue
    }
    fun goWithdrawal(){
        binding.activityContainerToolbarTv.text = "회원 탈퇴"
    }
    fun goUser(){
        binding.activityContainerToolbarTv.text = "프로필"
    }
    fun backIceBreaking(){
        binding.activityContainerToolbarTv.text = "아이스브레이킹"
    }

    fun isInGame ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if(fragment is NetworkingGamePlaceFragment){
                returnValue = true
            }
        }
        return returnValue
    }


    fun isSeminar ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is SeminarFragment) {
                returnValue = true
            }
        }
        return returnValue
    }

    fun isSeminarCharged ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is SeminarChargedApplyFragment) {
                returnValue = true
            }
        }
        return returnValue
    }

    fun isNotifi ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if(fragment is NotificationFragment){
                returnValue = true
            }
        }
        return returnValue
    }


}
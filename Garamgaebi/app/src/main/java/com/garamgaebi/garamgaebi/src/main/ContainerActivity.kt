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
//        val fragmentManager: FragmentManager = supportFragmentManager
//        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
//        val myFragment = currentFragment
//        if (myFragment != null) {
//            // 프래그먼트 매니저를 사용하여 백스택에서 최상위 프래그먼트 인스턴스를 가져옴
//            val fragmentManager = supportFragmentManager
//            val currentFragment = fragmentManager.findFragmentById(R.id.activity_container_frame)
//            // 백스택에서 최상위 프래그먼트 제거
//            fragmentManager.popBackStack()
//            fragmentTransaction.replace(R.id.activity_container_frame, myFragment)
//
//        }
//        fragmentTransaction.commit()
        Log.d("돼라","ㅇㅇ")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
//            val fragment = SomeFragment()
//            currentFragment = fragment
//            supportFragmentManager.beginTransaction()
//                .add(R.id.container, fragment)
//                .commit()
            Log.d("태그","ㅎㅇ 액")

        }
//        start.observe(this) {
//            if(!it) {
//                networkValid.observe(this) {
//                    Log.d("network", "containerActivity networkObserver it : $it, isConnected : ${networkValid.value}")
//                    openFragmentOnFrameLayout(fragmentTag)
//                    Log.d("태그",it.toString())
//                }
//            }
//        }

        networkValid.observe(this) {
            ignoreFirst.value = ignoreFirst.value!! + 1
            Log.d("network", "containerActivity networkObserver it : $it, isConnected : ${networkValid.value}")
            if(ignoreFirst.value!! > 3) {
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

            var currentFragmentCheck =
                supportFragmentManager.findFragmentById(R.id.activity_container_frame)
            //backStack 맨 위의 값과 현재 프래그먼트의 값이 같아야함
            if(fragmentTag != tag?.toInt() && tag?.toInt() != GAME) {
                supportFragmentManager.popBackStack()
                tag?.toInt()?.let { openFragmentOnFrameLayout(it) }

            }

//                5 -> { //인게임 전 선택창
//                    if (tag == "게임") {
////                        supportFragmentManager.popBackStack()
////                        Log.d("currentFragment", "게임이 아니었음")
////                        openFragmentOnFrameLayout(8)
////                        binding.activityContainerToolbarTv.text = game
//                    }else if(tag =="아이스브레이킹"){
//                        supportFragmentManager.popBackStack()
//                        Log.d("currentFragment", "아이스브레이킹이 아니었음")
//                        openFragmentOnFrameLayout(7)
//                        binding.activityContainerToolbarTv.text = "아이스브레이킹"
//                    }else if(tag =="유저프로필"){
//                        supportFragmentManager.popBackStack()
//                        Log.d("currentFragment", "프로필이 아니었음")
//                        openFragmentOnFrameLayout(13)
//                        binding.activityContainerToolbarTv.text = "프로필"
//                    }
////                    }
////                    if (currentFragmentCheck is NetworkingGamePlaceFragment) {
////
////                    } else {
////                        supportFragmentManager.popBackStack()
////                        Log.d("currentFragment", "게임이 아니었음")
////                        openFragmentOnFrameLayout(fragmentTag)
////                        binding.activityContainerToolbarTv.text = "아이스브레ㅇ"
////                    }
//                }
//
//                1 -> { //user profile 전의 코드
//                    if (tag != "유저프로필") {
//                        //openFragmentOnFrameLayout(fragmentTag)
//                        Log.d("currentFragment", "프로필이 아니었음")
//                    }
//                    if (currentFragmentCheck is UserProfileFragment) {
//
//                    } else {
//                        supportFragmentManager.popBackStack()
//                        Log.d("currentFragment", "프로필이 아니었음")
//                        openFragmentOnFrameLayout(13)
//                        binding.activityContainerToolbarTv.text = "프로필"
//                    }
//                }
////                    5 -> { //user profile 전의 코드
////                        if (tag != "유저프로필") {
////                            //openFragmentOnFrameLayout(fragmentTag)
////                            Log.d("currentFragment", "프로필이 아니었음")
////                        }
////                        if (currentFragmentCheck is UserProfileFragment) {
////
////                        } else {
////                            supportFragmentManager.popBackStack()
////                            Log.d("currentFragment", "프로필이 아니었음")
////                            openFragmentOnFrameLayout(13)
////                            binding.activityContainerToolbarTv.text = "프로필"
////                        }
////                }
//                14 -> { //withdrawal 전의 serviceFragment
//                    if (tag != "탈퇴") {
//                        // openFragmentOnFrameLayout(fragmentTag)
//                        Log.d("currentFragment", "탈퇴가 아니었음")
//                    }
//                    if (currentFragmentCheck is WithdrawalFragment) {
//
//                    } else {
//                        supportFragmentManager.popBackStack()
//                        Log.d("currentFragment", "탈퇴가 아니었음")
//                        openFragmentOnFrameLayout(15)
//                        binding.activityContainerToolbarTv.text = "회원 탈퇴"
//                    }
//                }
//                16 -> {
//                    if (tag == "게임") {
////                        supportFragmentManager.popBackStack()
////                        Log.d("currentFragment", "게임이 아니었음")
////                        openFragmentOnFrameLayout(8)
////                        binding.activityContainerToolbarTv.text = game
//                    }else if(tag =="아이스브레이킹"){
//                        supportFragmentManager.popBackStack()
//                        Log.d("currentFragment", "아이스브레이킹이 아니었음")
//                        openFragmentOnFrameLayout(7)
//                        binding.activityContainerToolbarTv.text = "아이스브레이킹"
//                    }else if(tag =="유저프로필"){
//                        supportFragmentManager.popBackStack()
//                        Log.d("currentFragment", "프로필이 아니었음")
//                        openFragmentOnFrameLayout(13)
//                        binding.activityContainerToolbarTv.text = "프로필"
//                    }
//                }
//
//                else -> {}
//            }
        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val backStackEntryCount = fragmentManager.backStackEntryCount

//        if(backStackEntryCount < 2){
//            supportFragmentManager.popBackStack()
//            //finish()
//        }

        Log.d("안녕",backStackEntryCount.toString())
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

//        if(supportFragmentManager.backStackEntryCount > 0) {
//            Log.d("currentFragment", "0이상 $fragmentTag")
//            var index = supportFragmentManager.backStackEntryCount - 1
//            var backEntry = supportFragmentManager.getBackStackEntryAt(index)
//            var tag = backEntry.name
//            binding.activityContainerToolbarTv.text = fragmentHashMap[tag?.toInt()]
//
//
//            Log.d("currentFragment", "태그검색 $currentFragmentCheck")
//
////            if(supportFragmentManager.backStackEntryCount == 1) {
////                supportFragmentManager.popBackStack()
////                openFragmentOnFrameLayout(it)
////            }
//        }
    }


    fun openFragmentOnFrameLayout(tag: Int){
        val transaction = supportFragmentManager.beginTransaction()
        Log.d("network", "containerActivity openFragmentOnFrameLayout backStack : ${supportFragmentManager.backStackEntryCount}")
        fragmentTag = tag
        if((networkValid.value == false) && tag in listOf(1, 5, 7, 16, 20)) {
            transaction.replace(R.id.activity_container_frame, NetworkDisconnectedFragment())
        } else {
            when(tag){
                SEMINAR -> {
                    transaction.replace(R.id.activity_container_frame, SeminarFragment(),SEMINAR.toString()).apply {
                        if(isNotifi()){
                            addToBackStack(SEMINAR.toString())
                        }
                    }
                    binding.activityContainerToolbarTv.text = "세미나"
                }
                SEMINAR_APPLY_FREE -> {transaction.replace(R.id.activity_container_frame, SeminarFreeApplyFragment(),
                    SEMINAR_APPLY_FREE.toString()).addToBackStack(
                    SEMINAR_APPLY_FREE.toString())
                    binding.activityContainerToolbarTv.text = "세미나"
                }
                SEMINAR_APPLY_CHARGED -> {transaction.replace(R.id.activity_container_frame, SeminarChargedApplyFragment(),
                    SEMINAR_APPLY_CHARGED.toString()).addToBackStack(SEMINAR_APPLY_CHARGED.toString())
                    binding.activityContainerToolbarTv.text = "세미나"
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
                    binding.activityContainerToolbarTv.text = "네트워킹"
                }
                NETWORKING_APPLY_FREE -> {transaction.replace(R.id.activity_container_frame, NetworkingFreeApplyFragment(),
                    NETWORKING_APPLY_FREE.toString()).addToBackStack("네트워킹 무료 신청")
                    binding.activityContainerToolbarTv.text = "네트워킹"
                }
                ICEBREAKING -> {transaction.replace(R.id.activity_container_frame, NetworkingGameSelectFragment(),
                    ICEBREAKING.toString()).addToBackStack(ICEBREAKING.toString())
                    binding.activityContainerToolbarTv.text = "아이스브레이킹"
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
//        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        Log.d("title_onstart","됨")

        networkValid.value = checkNetwork()

        if(checkNetwork()) {

            if (isSeminarCharged()) {
                binding.activityContainerToolbarTv.text = "세미나"
            }
            var tag = intent.getIntExtra("openFragment", 0)

            openFragmentOnFrameLayout(tag)
            fragmentTag = tag
            binding.activityContainerToolbarTv.text = fragmentHashMap[tag]

//            if (intent.getBooleanExtra("seminar", false)) {
//                binding.activityContainerToolbarTv.text = "세미나"
//                fragmentTag = 1
//                openFragmentOnFrameLayout(fragmentTag)
//
//            }
//            if (intent.getBooleanExtra("cancel", false)) {
//                fragmentTag = 4
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "신청 취소"
//            }
//            if (intent.getBooleanExtra("networking", false)) {
//                fragmentTag = 5
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "네트워킹"
//            }
//
//            if (intent.getBooleanExtra("sns", false)) {
//                fragmentTag = 9
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "SNS 추가하기"
//            }
//            if (intent.getBooleanExtra("career", false)) {
//                fragmentTag = 10
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "경력 추가하기"
//            }
//            if (intent.getBooleanExtra("edu", false)) {
//                fragmentTag = 11
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "교육 추가하기"
//            }
//            if (intent.getBooleanExtra("edit", false)) {
//                fragmentTag = 12
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "프로필 편집"
//            }
//            if (intent.getBooleanExtra("someoneProfile", false)) {
//                fragmentTag = 13
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "프로필"
//            }
//            if (intent.getBooleanExtra("servicecenter", false)) {
//                fragmentTag = 14
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "고객 센터"
//            }
//            if (intent.getBooleanExtra("withdrawal", false)) {
//                fragmentTag = 15
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "회원 탈퇴"
//                window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
//            }
//            if (intent.getBooleanExtra("notification", false)) {
//                Log.d("titleOnStart", "알림")
//                fragmentTag = 16
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "알림"
//            }
//            //승민 추가
//            if (intent.getBooleanExtra("snsEdit", false)) {
//                fragmentTag = 17
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "SNS 편집하기"
//            }
//            if (intent.getBooleanExtra("careerEdit", false)) {
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "경력 편집하기"
//            }
//            if (intent.getBooleanExtra("eduEdit", false)) {
//                fragmentTag = 19
//                openFragmentOnFrameLayout(fragmentTag)
//                binding.activityContainerToolbarTv.text = "교육 편집하기"
//            }
        }else{
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_container_frame, NetworkDisconnectedFragment())
        }



        /*val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if(fragment is SeminarFragment){
                binding.activityContainerToolbarTv.text = "세미나"
            }
            if(fragment is SeminarFreeApplyFragment){
                binding.activityContainerToolbarTv.text = "세미나"
            }
            if(fragment is SeminarChargedApplyFragment){
                binding.activityContainerToolbarTv.text = "세미나"
            }
            if(fragment is CancelFragment){
                binding.activityContainerToolbarTv.text = "신청 취소"
            }
        }*/
    }
    fun tagWhy(){
        Log.d("태그","메소드")
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
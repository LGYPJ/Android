package com.garamgaebi.garamgaebi.src.main


import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseActivity
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


class ContainerActivity : BaseActivity<ActivityContainerBinding>(ActivityContainerBinding::inflate) {
    var fragmentTag: Int = -1
    private var currentFragment: Fragment? = null
    var game =""
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
        }
        networkValid.observe(this) {
            Log.d("network", "containerActivity networkObserver it : $it, isConnected : ${networkValid.value}")
            openFragmentOnFrameLayout(fragmentTag)
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
        Log.d("currentFragment","시작")
        if(supportFragmentManager.backStackEntryCount > 0) {
            Log.d("currentFragment", "0이상 $fragmentTag")
            var index = supportFragmentManager.backStackEntryCount - 1
            var backEntry = supportFragmentManager.getBackStackEntryAt(index)
            var tag = backEntry.name

            var currentFragmentCheck =
                supportFragmentManager.findFragmentById(R.id.activity_container_frame)
            //backStack 맨 위의 값과 현재 프래그먼트의 값이 같아야함
            when (fragmentTag) {
                6 -> { //게임선택 전 아이스브레이킹
                    if (tag != "아이스브레이킹") {
                        //openFragmentOnFrameLayout(fragmentTag)
                        Log.d("currentFragment", "아이스브레이킹이 아니었음")
                    }
                    if (currentFragmentCheck is NetworkingGameSelectFragment) {

                    } else {
                        supportFragmentManager.popBackStack()
                        Log.d("currentFragment", "아이스브레이킹이 아니었음")
                        openFragmentOnFrameLayout(fragmentTag)
                        binding.activityContainerToolbarTv.text = "아이스브레이킹"
                    }
                }

                5 -> { //인게임 전 선택창
                    if (tag == "게임") {
                        supportFragmentManager.popBackStack()
                        Log.d("currentFragment", "게임이 아니었음")
                        openFragmentOnFrameLayout(8)
                        binding.activityContainerToolbarTv.text = game
                    }else if(tag =="아이스브레이킹"){
                        supportFragmentManager.popBackStack()
                        Log.d("currentFragment", "아이스브레이킹이 아니었음")
                        openFragmentOnFrameLayout(7)
                        binding.activityContainerToolbarTv.text = "아이스브레이킹"
                    }else if(tag =="유저프로필"){
                        supportFragmentManager.popBackStack()
                        Log.d("currentFragment", "프로필이 아니었음")
                        openFragmentOnFrameLayout(13)
                        binding.activityContainerToolbarTv.text = "프로필"
                    }
//                    }
//                    if (currentFragmentCheck is NetworkingGamePlaceFragment) {
//
//                    } else {
//                        supportFragmentManager.popBackStack()
//                        Log.d("currentFragment", "게임이 아니었음")
//                        openFragmentOnFrameLayout(fragmentTag)
//                        binding.activityContainerToolbarTv.text = "아이스브레ㅇ"
//                    }
                }

                1 -> { //user profile 전의 코드
                    if (tag != "유저프로필") {
                        //openFragmentOnFrameLayout(fragmentTag)
                        Log.d("currentFragment", "프로필이 아니었음")
                    }
                    if (currentFragmentCheck is UserProfileFragment) {

                    } else {
                        supportFragmentManager.popBackStack()
                        Log.d("currentFragment", "프로필이 아니었음")
                        openFragmentOnFrameLayout(13)
                        binding.activityContainerToolbarTv.text = "프로필"
                    }
                }
//                    5 -> { //user profile 전의 코드
//                        if (tag != "유저프로필") {
//                            //openFragmentOnFrameLayout(fragmentTag)
//                            Log.d("currentFragment", "프로필이 아니었음")
//                        }
//                        if (currentFragmentCheck is UserProfileFragment) {
//
//                        } else {
//                            supportFragmentManager.popBackStack()
//                            Log.d("currentFragment", "프로필이 아니었음")
//                            openFragmentOnFrameLayout(13)
//                            binding.activityContainerToolbarTv.text = "프로필"
//                        }
//                }
                14 -> { //withdrawal 전의 serviceFragment
                    if (tag != "탈퇴") {
                        // openFragmentOnFrameLayout(fragmentTag)
                        Log.d("currentFragment", "탈퇴가 아니었음")
                    }
                    if (currentFragmentCheck is WithdrawalFragment) {

                    } else {
                        supportFragmentManager.popBackStack()
                        Log.d("currentFragment", "탈퇴가 아니었음")
                        openFragmentOnFrameLayout(15)
                        binding.activityContainerToolbarTv.text = "회원 탈퇴"
                    }
                }

                else -> {}
            }
        }
    }

    override fun onBackPressed() {
        val fragmentManager = supportFragmentManager
        val backStackEntryCount = fragmentManager.backStackEntryCount
        Log.d("안녕",backStackEntryCount.toString())
        Log.d("network", "onBackPressed backStackCount : ${supportFragmentManager.backStackEntryCount}")
        if(isWithdrawal()){
            binding.activityContainerToolbarTv.text = "고객 센터"
            Log.d("뭐냐고","어?")
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
            Log.d("뭐냐고", "어?")
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
    }


    fun openFragmentOnFrameLayout(tag: Int){
        val transaction = supportFragmentManager.beginTransaction()
        Log.d("network", "containerActivity openFragmentOnFrameLayout backStack : ${supportFragmentManager.backStackEntryCount}")
        fragmentTag = tag
        if(networkValid.value == false && tag in listOf(1, 5, 7, 16, 20)) {
            transaction.replace(R.id.activity_container_frame, NetworkDisconnectedFragment())
        } else {
            when(tag){
                1 -> {
                    transaction.replace(R.id.activity_container_frame, SeminarFragment())
                    binding.activityContainerToolbarTv.text = "세미나"
                }
                2 -> {transaction.replace(R.id.activity_container_frame, SeminarFreeApplyFragment()).addToBackStack("세미나 무료 신청")
                    binding.activityContainerToolbarTv.text = "세미나"
                }
                3 -> {transaction.replace(R.id.activity_container_frame, SeminarChargedApplyFragment()).addToBackStack("세미나 유료 신청")
                    binding.activityContainerToolbarTv.text = "세미나"
                }

                4 -> {transaction.replace(R.id.activity_container_frame, CancelFragment())
                }
                5 -> {transaction.replace(R.id.activity_container_frame, NetworkingFragment())
                    binding.activityContainerToolbarTv.text = "네트워킹"
                }
                6 -> {transaction.replace(R.id.activity_container_frame, NetworkingFreeApplyFragment()).addToBackStack("네트워킹 무료 신청")
                    binding.activityContainerToolbarTv.text = "네트워킹"
                }
                7 -> {transaction.replace(R.id.activity_container_frame, NetworkingGameSelectFragment()).addToBackStack("아이스브레이킹")
                    binding.activityContainerToolbarTv.text = "아이스브레이킹"
                }
                8 -> {transaction.replace(R.id.activity_container_frame, NetworkingGamePlaceFragment()).addToBackStack("게임")
                }

                //승민 부분
                9 -> {
                    transaction.replace(R.id.activity_container_frame, SnsAddFragment())
                }
                10 -> {
                    transaction.replace(R.id.activity_container_frame, CareerAddFragment())
                }
                11 -> {
                    transaction.replace(R.id.activity_container_frame, EduAddFragment())
                }
                12 -> {
                    transaction.replace(R.id.activity_container_frame, ProfileEditFragment())
                }
                13 -> {
                    if(isSeminar()){
                        transaction.replace(R.id.activity_container_frame, UserProfileFragment()).addToBackStack("유저프로필")
                    }
                    if(isNetworking()){
                        transaction.replace(R.id.activity_container_frame, UserProfileFragment()).addToBackStack("유저프로필")
                    }
                    else{
                        transaction.replace(R.id.activity_container_frame, UserProfileFragment())
                    }
                }
                14 -> {
                    transaction.replace(R.id.activity_container_frame, ServiceCenterFragment())
                }
                15 -> {
                    transaction.replace(R.id.activity_container_frame, WithdrawalFragment()).addToBackStack("탈퇴")
                    //binding.activityContainerToolbarTv.text = "회원탈퇴"
                    Log.d("회원탈퇴",binding.activityContainerToolbarTv.text.toString())
                }
                //동원 부분
                16 -> {
                    Log.d("title", "openFragmentOnFrameLayout")
                    transaction.replace(R.id.activity_container_frame, NotificationFragment())
                }
                //승민 추가
                17 -> {
                    transaction.replace(R.id.activity_container_frame, SnsEditFragment())
                }
                18 -> {
                    transaction.replace(R.id.activity_container_frame, CareerEditFragment())
                }
                19 -> {
                    transaction.replace(R.id.activity_container_frame, EduEditFragment())
                }

                //신디 추가 네트워킹 유료 신청
                20 -> {
                    transaction.replace(R.id.activity_container_frame, NetworkingChargedApplyFragment()).addToBackStack("네트워킹 유료 신청")
                    binding.activityContainerToolbarTv.text = "네트워킹"
                }


            }
            Log.d("currentFragment", "태그 $fragmentTag")

        }
        transaction.commit()
        Log.d("container", "onBackPressed backStackCount : ${supportFragmentManager.backStackEntryCount}")
    }


    fun networkingPlace(place: String){
        binding.activityContainerToolbarTv.text = place
        game = place
    }

    override fun onStart() {
        super.onStart()
//        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        Log.d("title_onstart","됨")
        if(isSeminarCharged()){
            binding.activityContainerToolbarTv.text = "세미나"
        }
        if(intent.getBooleanExtra("seminar", false)){
            binding.activityContainerToolbarTv.text = "세미나"
            fragmentTag = 1
            openFragmentOnFrameLayout(fragmentTag)
        }
        if(intent.getBooleanExtra("cancel", false)){
            fragmentTag = 4
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "신청 취소"
        }
        if(intent.getBooleanExtra("networking", false)){
            fragmentTag = 5
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "네트워킹"
        }

        if(intent.getBooleanExtra("sns", false)){
            fragmentTag = 9
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "SNS 추가하기"
        }
        if(intent.getBooleanExtra("career", false)){
            fragmentTag = 10
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "경력 추가하기"
        }
        if(intent.getBooleanExtra("edu", false)){
            fragmentTag = 11
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "교육 추가하기"
        }
        if(intent.getBooleanExtra("edit", false)){
            fragmentTag = 12
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "프로필 편집"
        }
        if(intent.getBooleanExtra("someoneProfile", false)){
            fragmentTag = 13
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "프로필"
        }
        if(intent.getBooleanExtra("servicecenter", false)){
            fragmentTag = 14
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "고객 센터"
        }
        if(intent.getBooleanExtra("withdrawal", false)){
            fragmentTag = 15
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "회원 탈퇴"
            window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        }
        if(intent.getBooleanExtra("notification", false)) {
            Log.d("titleOnStart", "알림")
            fragmentTag = 16
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "알림"
        }
        //승민 추가
        if(intent.getBooleanExtra("snsEdit", false)) {
            fragmentTag = 17
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "SNS 편집하기"
       }
        if(intent.getBooleanExtra("careerEdit", false)) {
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "경력 편집하기"
        }
        if(intent.getBooleanExtra("eduEdit", false)) {
            fragmentTag = 19
            openFragmentOnFrameLayout(fragmentTag)
            binding.activityContainerToolbarTv.text = "교육 편집하기"
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
    override fun onSaveInstanceState(outState: Bundle) {
        Log.d("currentFragment1",outState.toString())
        super.onSaveInstanceState(outState)
        // 이전에 사용 중이던 프래그먼트의 상태 정보를 저장합니다.
        currentFragment?.let { supportFragmentManager.putFragment(outState, "currentFragment", it) }

    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d("currentFragment2",savedInstanceState.toString())
        super.onRestoreInstanceState(savedInstanceState)
        // 저장된 상태 정보에서 이전에 사용 중이던 프래그먼트를 가져옵니다.
        currentFragment = supportFragmentManager.getFragment(savedInstanceState, "currentFragment")
    }


}
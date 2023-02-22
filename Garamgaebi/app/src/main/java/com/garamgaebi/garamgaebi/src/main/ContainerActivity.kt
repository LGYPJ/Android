package com.garamgaebi.garamgaebi.src.main

import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.util.Log
import android.view.WindowManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseActivity
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.ActivityContainerBinding
import com.garamgaebi.garamgaebi.src.main.cancel.CancelFragment
import com.garamgaebi.garamgaebi.src.main.networking.NetworkingFragment
import com.garamgaebi.garamgaebi.src.main.networking.NetworkingFreeApplyFragment
import com.garamgaebi.garamgaebi.src.main.networking_game.NetworkingGamePlaceFragment
import com.garamgaebi.garamgaebi.src.main.networking_game.NetworkingGameSelectFragment
import com.garamgaebi.garamgaebi.src.main.home.NotificationFragment
import com.garamgaebi.garamgaebi.src.main.networking.NetworkingChargedApplyFragment
import com.garamgaebi.garamgaebi.src.main.profile.*
import com.garamgaebi.garamgaebi.src.main.seminar.SeminarChargedApplyFragment
import com.garamgaebi.garamgaebi.src.main.seminar.SeminarFragment
import com.garamgaebi.garamgaebi.src.main.seminar.SeminarFreeApplyFragment

class ContainerActivity : BaseActivity<ActivityContainerBinding>(ActivityContainerBinding::inflate) {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
            //알림
            if(isNotifi()){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            else{
                onBackPressed()
            }
        }

    }

    override fun onBackPressed() {
        if(isWithdrawal()){
            openFragmentOnFrameLayout(14)
            binding.activityContainerToolbarTv.text = "고객 센터"
        }else {
            super.onBackPressed()
            //프래그먼트에서 back
            /*val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is onBackPressedListener) {
                (fragment as onBackPressedListener).onBackPressed()
                return
            }
        }*/
            if (isProfileEdit()) {
                GaramgaebiApplication.sSharedPreferences.edit().putBoolean("EditImage", false)
                    .apply()
            }

        if(isIceBreaking()){
            binding.activityContainerToolbarTv.text = "아이스브레이킹"
        }
        if(isNetworking()){
            binding.activityContainerToolbarTv.text ="네트워킹"
        }
        if(isSeminar()){
            binding.activityContainerToolbarTv.text = "세미나"
        }
        //알림
        if(isNotifi()){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


    fun openFragmentOnFrameLayout(int: Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            0 -> finish()
            1 -> {transaction.replace(R.id.activity_seminar_frame, SeminarFragment(), "seminar")
                binding.activityContainerToolbarTv.text = "세미나"
            }
            2 -> {transaction.replace(R.id.activity_seminar_frame, SeminarFreeApplyFragment() ,"seminarFree").addToBackStack(null)
                binding.activityContainerToolbarTv.text = "세미나"
            }
            3 -> {transaction.replace(R.id.activity_seminar_frame, SeminarChargedApplyFragment(),"seminarCharged").addToBackStack(null)
                binding.activityContainerToolbarTv.text = "세미나"
            }

            4 -> {transaction.replace(R.id.activity_seminar_frame, CancelFragment(),"cancel")
            }
            5 -> {transaction.replace(R.id.activity_seminar_frame, NetworkingFragment(),"networking")
                binding.activityContainerToolbarTv.text = "네트워킹"
            }
            6 -> {transaction.replace(R.id.activity_seminar_frame, NetworkingFreeApplyFragment(),"networkingFree").addToBackStack(null)
                binding.activityContainerToolbarTv.text = "네트워킹"
            }
            7 -> {transaction.replace(R.id.activity_seminar_frame, NetworkingGameSelectFragment(),"networkingGameSelect").addToBackStack(null)
                binding.activityContainerToolbarTv.text = "아이스브레이킹"
            }
            8 -> {transaction.replace(R.id.activity_seminar_frame, NetworkingGamePlaceFragment(),"networkingGamePlace").addToBackStack(null)
            }


            //승민 부분
            9 -> {
                transaction.replace(R.id.activity_seminar_frame, SnsAddFragment(),"sns")
            }
            10 -> {
                transaction.replace(R.id.activity_seminar_frame, CareerFragment(),"career")
            }
            11 -> {
                transaction.replace(R.id.activity_seminar_frame, EduFragment(),"edu")
            }
            12 -> transaction.replace(R.id.activity_seminar_frame, ProfileEditFragment(),"profileEdit")

            13 -> {
                transaction.replace(R.id.activity_seminar_frame, SomeoneProfileFragment(),"someoneProfile")
            }
            14 -> {
                transaction.replace(R.id.activity_seminar_frame, ServiceCenterFragment(),"serviceCenter")
            }
            15 -> {
                transaction.replace(R.id.activity_seminar_frame, WithdrawalFragment(),"withdrawal")
                //binding.activityContainerToolbarTv.text = "회원탈퇴"
                Log.d("회워탈퇴",binding.activityContainerToolbarTv.text.toString())
            }

            //동원 부분
            16 -> {
                transaction.replace(R.id.activity_seminar_frame, NotificationFragment(), "notification")
            }

            //승민 추가
            17 -> {
                transaction.replace(R.id.activity_seminar_frame, SnsEditFragment(), "snsEdit")
            }
            18 -> {
                transaction.replace(R.id.activity_seminar_frame, CareerEditFragment(), "careerEdit")
            }
            19 -> {
                transaction.replace(R.id.activity_seminar_frame, EduEditFragment(), "eduEdit")
            }


            //신디 추가 네트워킹 유료 신청
            20 -> {
                transaction.replace(R.id.activity_seminar_frame, NetworkingChargedApplyFragment(), "networkingCharged").addToBackStack(null)
                binding.activityContainerToolbarTv.text = "네트워킹"
            }


        }
        transaction.commit()
        for(fragment: Fragment in supportFragmentManager.fragments) {
            if (fragment.isVisible) {
                val tag = fragment.tag
                lateinit var frag: Fragment
                var fragmentTitle: String = ""
                when (tag) {
                    "seminar" -> fragmentTitle = "세미나"
                    "seminarFree" -> fragmentTitle = "세미나"
                    "seminarCharged" -> fragmentTitle = "세미나"
                    "cancel" -> fragmentTitle = "신청 취소"
                    "networking" -> fragmentTitle = "네트워킹"
                    "networkingFree" -> fragmentTitle = "네트워킹"
                    "networkingGameSelect" -> fragmentTitle = "아이스브레이킹"
                    "networkingGamePlace" -> fragmentTitle = binding.activityContainerToolbarTv.text.toString()
                    "sns" -> fragmentTitle = "SNS 추가하기"
                    "snsEdit" -> fragmentTitle = "SNS 편집하기"
                    //"networkingGameSelect" -> fragmentTitle = "아이스브레이킹"
                    //"networkingGamePlace" -> fragmentTitle = ""
                    "career" -> fragmentTitle = "경력"
                    "careerEdit" -> fragmentTitle = "경력 편집하기"

                    "edu" -> fragmentTitle = "교육"
                    "eduEdit" -> fragmentTitle = "교육 편집하기"

                    "profileEdit" -> fragmentTitle = "프로필 편집"
                    "someoneProfile" -> fragmentTitle = "프로필"
                    "servicecenter" -> fragmentTitle = "고객 센터"
                    "withdrawal" -> fragmentTitle = "회원 탈퇴"
                    "notification" -> fragmentTitle = "알림"
                    "networkingCharged" -> fragmentTitle = "네트워킹"
                }

                binding.activityContainerToolbarTv.text = fragmentTitle
                Log.d("title",fragmentTitle)
            }
        }
    }


    fun networkingPlace(place: String){
            binding.activityContainerToolbarTv.text = place
    }

    fun iceBreaking(ice: String){
        binding.activityContainerToolbarTv.text = ice
    }

    override fun onStart() {
        super.onStart()
        window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN)
        Log.d("title_onstart","됨")
        if(isSeminarCharged()){
            binding.activityContainerToolbarTv.text = "세미나"
        }

        if(intent.getBooleanExtra("seminar", false)){
            binding.activityContainerToolbarTv.text = "세미나"
            openFragmentOnFrameLayout(1)
        }
        if(intent.getBooleanExtra("cancel", false)){
            openFragmentOnFrameLayout(4)
            binding.activityContainerToolbarTv.text = "신청 취소"
        }
        if(intent.getBooleanExtra("networking", false)){
            openFragmentOnFrameLayout(5)
            binding.activityContainerToolbarTv.text = "네트워킹"
        }

        if(intent.getBooleanExtra("sns", false)){
            openFragmentOnFrameLayout(9)
            binding.activityContainerToolbarTv.text = "SNS 추가하기"
        }
        if(intent.getBooleanExtra("career", false)){
            openFragmentOnFrameLayout(10)
            binding.activityContainerToolbarTv.text = "경력 추가하기"
        }
        if(intent.getBooleanExtra("edu", false)){
            openFragmentOnFrameLayout(11)
            binding.activityContainerToolbarTv.text = "교육 추가하기"
        }
        if(intent.getBooleanExtra("edit", false)){
            openFragmentOnFrameLayout(12)
            binding.activityContainerToolbarTv.text = "프로필 편집"
        }
        if(intent.getBooleanExtra("someoneProfile", false)){
            openFragmentOnFrameLayout(13)
            binding.activityContainerToolbarTv.text = "프로필"
        }
        if(intent.getBooleanExtra("servicecenter", false)){
            openFragmentOnFrameLayout(14)
            binding.activityContainerToolbarTv.text = "고객 센터"
        }
        if(intent.getBooleanExtra("withdrawal", false)){
            openFragmentOnFrameLayout(15)
            binding.activityContainerToolbarTv.text = "회원 탈퇴"
        }
        if(intent.getBooleanExtra("notification", false)) {
            openFragmentOnFrameLayout(16)
            binding.activityContainerToolbarTv.text = "알림"
        }

        //승민 추가
        if(intent.getBooleanExtra("snsEdit", false)) {
            openFragmentOnFrameLayout(17)
            binding.activityContainerToolbarTv.text = "SNS 편집하기"
       }
        if(intent.getBooleanExtra("careerEdit", false)) {
            openFragmentOnFrameLayout(18)
            binding.activityContainerToolbarTv.text = "경력 편집하기"
        }
        if(intent.getBooleanExtra("eduEdit", false)) {
            openFragmentOnFrameLayout(19)
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

    fun isCancel ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is CancelFragment) {
                returnValue = true
            }
        }
        return returnValue
    }

    fun isNotifi ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if (fragment is NotificationFragment) {
                returnValue = true
            }
        }
        return returnValue
    }

}
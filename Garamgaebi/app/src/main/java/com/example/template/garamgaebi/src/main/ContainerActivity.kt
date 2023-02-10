package com.example.template.garamgaebi.src.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseActivity
import com.example.template.garamgaebi.databinding.ActivityContainerBinding
import com.example.template.garamgaebi.src.main.cancel.CancelFragment
import com.example.template.garamgaebi.src.main.networking.NetworkingFragment
import com.example.template.garamgaebi.src.main.networking.NetworkingFreeApplyFragment
import com.example.template.garamgaebi.src.main.networking_game.NetworkingGamePlaceFragment
import com.example.template.garamgaebi.src.main.networking_game.NetworkingGameSelectFragment
import com.example.template.garamgaebi.src.main.notification.NotificationFragment
import com.example.template.garamgaebi.src.main.profile.*
import com.example.template.garamgaebi.src.main.seminar.SeminarChargedApplyFragment
import com.example.template.garamgaebi.src.main.seminar.SeminarFragment
import com.example.template.garamgaebi.src.main.seminar.SeminarFreeApplyFragment

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
            if(isSeminar()){
                finish()
            }
            if(isNetworking()){
                finish()
            }else{
                onBackPressed()
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(isIceBreaking()){
            binding.activityContainerToolbarTv.text = "아이스브레이킹"
        }
        if(isNetworking()){
            binding.activityContainerToolbarTv.text ="네트워킹"
        }
        if(isSeminar()){
            binding.activityContainerToolbarTv.text = "세미나"
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
                transaction.replace(R.id.activity_seminar_frame, SnsFragment(),"sns")
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
                binding.activityContainerToolbarTv.text = "회원탈퇴"
            }

            //동원 부분
            16 -> {
                transaction.replace(R.id.activity_seminar_frame, NotificationFragment(), "notification")
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
                    //"networkingGameSelect" -> fragmentTitle = "아이스브레이킹"
                    //"networkingGamePlace" -> fragmentTitle = ""
                    "sns" -> fragmentTitle = "SNS"
                    "career" -> fragmentTitle = "경력"
                    "edu" -> fragmentTitle = "교육"
                    "profileEdit" -> fragmentTitle = "프로필 편집"
                    "someoneProfile" -> fragmentTitle = "프로필"
                    "servicecenter" -> fragmentTitle = "고객 센터"
                    "withdrawal" -> fragmentTitle = "회원 탈퇴"
                    "notification" -> fragmentTitle = "알림"
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
        Log.d("title_onstart","됨")
        if(isSeminarCharged()){
            binding.activityContainerToolbarTv.text = "세미나"
        }

        if(intent.getBooleanExtra("seminar", false)){
            binding.activityContainerToolbarTv.text = "세미나"
            /*val seminar = intent.getIntExtra("HomeSeminarIdx", 0)
            val bundle = Bundle()
            bundle.putInt("HomeSeminarIdx", seminar)
            val seminarFragment = SeminarFragment()
            seminarFragment.arguments = bundle
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_seminar_frame, seminarFragment).commit()*/
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
            binding.activityContainerToolbarTv.text = "SNS"
        }
        if(intent.getBooleanExtra("career", false)){
            openFragmentOnFrameLayout(10)
            binding.activityContainerToolbarTv.text = "경력"
        }
        if(intent.getBooleanExtra("edu", false)){
            openFragmentOnFrameLayout(11)
            binding.activityContainerToolbarTv.text = "교육"
        }
        if(intent.getBooleanExtra("edit", false)){
            openFragmentOnFrameLayout(12)
            binding.activityContainerToolbarTv.text = "프로필 편집"
        }
        if(intent.getBooleanExtra("someoneprofile", false)){
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





    }
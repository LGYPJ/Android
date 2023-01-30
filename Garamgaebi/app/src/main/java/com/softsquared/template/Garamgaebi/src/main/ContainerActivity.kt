package com.softsquared.template.Garamgaebi.src.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityContainerBinding
import com.softsquared.template.Garamgaebi.databinding.ActivityMainBinding
import com.softsquared.template.Garamgaebi.src.main.cancel.CancelCompleteDialog
import com.softsquared.template.Garamgaebi.src.main.cancel.CancelFragment
import com.softsquared.template.Garamgaebi.src.main.home.HomeFragment
import com.softsquared.template.Garamgaebi.src.main.networking.NetworkingFragment
import com.softsquared.template.Garamgaebi.src.main.networking.NetworkingFreeApplyFragment
import com.softsquared.template.Garamgaebi.src.main.networking_game.ItemViewModel
import com.softsquared.template.Garamgaebi.src.main.networking_game.NetworkingGamePlaceFragment
import com.softsquared.template.Garamgaebi.src.main.networking_game.NetworkingGameSelectFragment
import com.softsquared.template.Garamgaebi.src.main.profile.*
import com.softsquared.template.Garamgaebi.src.seminar.SeminarChargedApplyFragment
import com.softsquared.template.Garamgaebi.src.seminar.SeminarFragment
import com.softsquared.template.Garamgaebi.src.seminar.SeminarFreeApplyFragment

class ContainerActivity : BaseActivity<ActivityContainerBinding>(ActivityContainerBinding::inflate) {

    /*private var networkingGameSelectFragment : NetworkingGameSelectFragment? = null
    private var networkingFragment : NetworkingFragment? = null
    private var networkingFreeApplyFragment : NetworkingFreeApplyFragment? = null
    private var seminarFragment : SeminarFragment? = null
    private var seminarFreeApplyFragment : SeminarFreeApplyFragment? = null
    private var seminarChargedApplyFragment: SeminarChargedApplyFragment? =null
    private var cancelFragment: CancelFragment? =null
    private var networkingGamePlaceFragment: NetworkingGamePlaceFragment? =null*/
    //private lateinit var viewModel: ItemViewModel

    lateinit var previousFramgent : Fragment
    lateinit var currentFramgent : Fragment

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
            //메인 세미나 프래그먼트일때 백버튼 누르면 컨테이너 액티비티 종료되게
            if(isIceBreaking()){
                onBackPressed()
                binding.activityContainerToolbarTv.text ="아이스브레이킹"
            }
            else {
                onBackPressed()
                if(isNetworking()){
                    binding.activityContainerToolbarTv.text = "네트워킹"
                }
            }
        }


    }

    fun openFragmentOnFrameLayout(int: Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            0 -> finish()
            1 -> {transaction.replace(R.id.activity_seminar_frame, SeminarFragment(), "seminar")
                }
            2 -> {transaction.replace(R.id.activity_seminar_frame, SeminarFreeApplyFragment() ,"seminarFree").addToBackStack(null)
            }
            3 -> {transaction.replace(R.id.activity_seminar_frame, SeminarChargedApplyFragment(),"seminarCharged").addToBackStack(null)
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
                    "sns" -> fragmentTitle = "SNS"
                    "career" -> fragmentTitle = "경력"
                    "edu" -> fragmentTitle = "교육"
                    "profileEdit" -> fragmentTitle = "프로필 편집"
                    "someoneProfile" -> fragmentTitle = "프로필"
                    "servicecenter" -> fragmentTitle = "고객 센터"
                    "withdrawal" -> fragmentTitle = "회원 탈퇴"
                }

                binding.activityContainerToolbarTv.text = fragmentTitle
                Log.d("title",fragmentTitle)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if(isIceBreaking()){
            onBackPressed()
            binding.activityContainerToolbarTv.text ="아이스브레이킹"
        }
        else {
            onBackPressed()
            if(isNetworking()){
                binding.activityContainerToolbarTv.text = "네트워킹"
            }
        }
    }

    fun networkingPlace(place: String){
            binding.activityContainerToolbarTv.text = place
    }
    
    override fun onStart() {
        super.onStart()
        Log.d("title_onstart","됨")

        if(intent.getBooleanExtra("seminar", false)){
            openFragmentOnFrameLayout(1)
            binding.activityContainerToolbarTv.text = "세미나"
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
    //메인 세미나 프래그먼트일때 백버튼 누르면 컨테이너 액티비티 종료되게
    fun isBackSeminar ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if(fragment is SeminarFragment){
                returnValue = true
            }
        }
        return returnValue
    }

    fun isBackNetwork ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if(fragment is NetworkingFragment){
                returnValue = true
            }
        }
        return returnValue
    }

    fun isIceBreaking ():Boolean {
        var returnValue = false
        val fragmentList = supportFragmentManager.fragments
        for (fragment in fragmentList) {
            if(fragment is NetworkingGamePlaceFragment){
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



}
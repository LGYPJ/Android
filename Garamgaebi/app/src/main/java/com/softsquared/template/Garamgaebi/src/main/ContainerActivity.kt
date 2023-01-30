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

    /*private var seminarFragment : SeminarFragment? = null
    private var seminarFreeApplyFragment : SeminarFreeApplyFragment? = null
    private var seminarChargedApplyFragment: SeminarChargedApplyFragment? =null
    private var cancelFragment: CancelFragment? =null*/
    private var networkingGamePlaceFragment: NetworkingGamePlaceFragment? =null
    private lateinit var viewModel: ItemViewModel


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
            networkingGamePlaceFragment = NetworkingGamePlaceFragment()
            val transaction = supportFragmentManager.beginTransaction()
            if(isBackSeminar()) {
                finish()
            }
            if(isBackNetwork()) {
                finish()
            }
            if(isIceBreaking()){
                transaction.remove(networkingGamePlaceFragment!!).commit()
                supportFragmentManager.popBackStack()
            }
            else {
                onBackPressed()
            }
        }
        //아이스 브레이킹과 게임관 툴바 이름 바뀌는 거,,,,,,, 라이브데이터 적용이 안됨...
        viewModel = ViewModelProvider(this)[ItemViewModel::class.java]
        viewModel._ice.observe(this, Observer {
            binding.activityContainerToolbarTv.text = it.toString()
            Log.d("bbb", it)
        })

        /*seminarFragment = SeminarFragment()
        seminarFreeApplyFragment = SeminarFreeApplyFragment()
        seminarChargedApplyFragment = SeminarChargedApplyFragment()
        cancelFragment = CancelFragment()*/

    }

    fun openFragmentOnFrameLayout(int: Int){
        val transaction = supportFragmentManager.beginTransaction()
        when(int){
            0 -> finish()
            1 -> transaction.replace(R.id.activity_seminar_frame, SeminarFragment())
            2 -> transaction.replace(R.id.activity_seminar_frame, SeminarFreeApplyFragment()).addToBackStack(null)
            3 -> transaction.replace(R.id.activity_seminar_frame, SeminarChargedApplyFragment()).addToBackStack(null)
            4 -> transaction.replace(R.id.activity_seminar_frame, CancelFragment())
            5 -> transaction.replace(R.id.activity_seminar_frame, NetworkingFragment())
            6 -> transaction.replace(R.id.activity_seminar_frame, NetworkingFreeApplyFragment()).addToBackStack(null)
            7 -> {transaction.replace(R.id.activity_seminar_frame, NetworkingGameSelectFragment()).addToBackStack(null)
                   binding.activityContainerToolbarTv.text = "아이스브레이킹"
            }
            8 -> transaction.replace(R.id.activity_seminar_frame, NetworkingGamePlaceFragment()).addToBackStack(null)


            //승민 부분
            9 -> {
                transaction.replace(R.id.activity_seminar_frame, SnsFragment()).addToBackStack(null)
            }
            10 -> {
                transaction.replace(R.id.activity_seminar_frame, CareerFragment()).addToBackStack(null)
            }
            11 -> {
                transaction.replace(R.id.activity_seminar_frame, EduFragment()).addToBackStack(null)
            }
            12 -> transaction.replace(R.id.activity_seminar_frame, ProfileEditFragment()).addToBackStack(null)

            13 -> {
                transaction.replace(R.id.activity_seminar_frame, SomeoneProfileFragment()).addToBackStack(null)
            }
            14 -> {
                transaction.replace(R.id.activity_seminar_frame, ServiceCenterFragment()).addToBackStack(null)
            }
            15 -> {
                transaction.replace(R.id.activity_seminar_frame, WithdrawalFragment()).addToBackStack(null)
                binding.activityContainerToolbarTv.text = "회원탈퇴"
            }
        }
        transaction.commit()
    }

    //if문 넣어줏기
    fun networkingPlace(place: String){
        if(isIceBreaking()){
            binding.activityContainerToolbarTv.text = place
        }
    }
    
    override fun onStart() {
        super.onStart()

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
            if(fragment is NetworkingGameSelectFragment){
                returnValue = true
            }
        }
        return returnValue
    }



}
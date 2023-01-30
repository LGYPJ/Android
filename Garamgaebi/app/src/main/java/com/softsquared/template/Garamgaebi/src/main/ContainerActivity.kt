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
            if(isBackSeminar()) {
                finish()
            }
            if(isBackNetwork()) {
                finish()
            }
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
            1 -> {transaction.replace(R.id.activity_seminar_frame, SeminarFragment())
                }
            2 -> {transaction.replace(R.id.activity_seminar_frame, SeminarFreeApplyFragment()).addToBackStack(null)
            }
            3 -> {transaction.replace(R.id.activity_seminar_frame, SeminarChargedApplyFragment()).addToBackStack(null)
            }

            4 -> {transaction.replace(R.id.activity_seminar_frame, CancelFragment())
            }
            5 -> {transaction.replace(R.id.activity_seminar_frame, NetworkingFragment())
                binding.activityContainerToolbarTv.text = "네트워킹"
            }
            6 -> {transaction.replace(R.id.activity_seminar_frame, NetworkingFreeApplyFragment()).addToBackStack(null)
                 binding.activityContainerToolbarTv.text = "네트워킹"
            }
            7 -> {transaction.replace(R.id.activity_seminar_frame, NetworkingGameSelectFragment()).addToBackStack(null)
                   binding.activityContainerToolbarTv.text = "아이스브레이킹"
            }
            8 -> {transaction.replace(R.id.activity_seminar_frame, NetworkingGamePlaceFragment()).addToBackStack(null)
            }



        }
        transaction.commit()
    }

    fun networkingPlace(place: String){
            binding.activityContainerToolbarTv.text = place
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
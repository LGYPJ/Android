package com.garamgaebi.garamgaebi.src.main.register

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.FIRST
import com.garamgaebi.garamgaebi.common.REGISTER_LOGIN
import com.garamgaebi.garamgaebi.common.SECOND
import com.garamgaebi.garamgaebi.databinding.FragmentRegisterIntroBinding

class RegisterIntroFragment : BaseFragment<FragmentRegisterIntroBinding>(FragmentRegisterIntroBinding::bind, R.layout.fragment_register_intro) {
    lateinit var registerActivity : RegisterActivity
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vpAdapter = IntroViewPagerAdapter(registerActivity)
        binding.fragmentIntroVp.adapter = vpAdapter

        binding.fragmentIntroIndicator.apply {
            setViewPager(binding.fragmentIntroVp)
            createIndicators(2, 0)
        }

        // 뷰페이저 리스너
        binding.fragmentIntroVp.registerOnPageChangeCallback( object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when(position) {
                    FIRST -> binding.fragmentRegisterIntroBtn.text = getString(R.string.next)
                    SECOND -> binding.fragmentRegisterIntroBtn.text = getString(R.string.start)
                }
            }
        })
        // 버튼 클릭 리스너
        binding.fragmentRegisterIntroBtn.setOnClickListener {
            if(binding.fragmentRegisterIntroBtn.text == getString(R.string.next)) {
                binding.fragmentIntroVp.currentItem = 1
            } else {
                startActivity(Intent(registerActivity, RegisterLoginActivity::class.java))
            }
        }
    }
    inner class IntroViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            Log.d("vpAdapter","$position")
            return when(position) {
                FIRST -> {
                    binding.fragmentRegisterIntroBtn.text = getString(R.string.next)
                    RegisterIntroFirstFragment()
                }
                else -> {
                    binding.fragmentRegisterIntroBtn.text = getString(R.string.start)
                    RegisterIntroSecondFragment()
                }
            }
        }
    }
    override fun onAttach(context: Context) {
        super.onAttach(context)
        registerActivity = context as RegisterActivity
    }

}
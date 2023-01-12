package com.softsquared.template.Garamgaebi.src.main.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity<ActivityIntroBinding>(ActivityIntroBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vpAdapter = IntroViewPagerAdapter(this)
        binding.activityIntroVp.adapter = vpAdapter

        binding.activityIntroIndicator.apply {
            setViewPager(binding.activityIntroVp)
            createIndicators(2, 0)
        }
    }
    inner class IntroViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when(position) {
                0 -> {
                    binding.activityIntroBtn.text = "다음"
                    IntroFirstFragment()
                }
                1 -> {
                    binding.activityIntroBtn.text = "시작하기"
                    IntroSecondFragment()
                }
                else -> IntroFirstFragment()
            }
        }
    }
}
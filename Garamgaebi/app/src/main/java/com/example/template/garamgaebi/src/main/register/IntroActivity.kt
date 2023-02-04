package com.example.template.garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.template.garamgaebi.config.BaseActivity
import com.example.template.garamgaebi.databinding.ActivityIntroBinding
import com.example.template.garamgaebi.src.main.MainActivity

class IntroActivity : BaseActivity<ActivityIntroBinding>(ActivityIntroBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val vpAdapter = IntroViewPagerAdapter(this)
        binding.activityIntroVp.adapter = vpAdapter

        binding.activityIntroIndicator.apply {
            setViewPager(binding.activityIntroVp)
            createIndicators(2, 0)
        }

        // 뷰페이저 리스너
        binding.activityIntroVp.registerOnPageChangeCallback( object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when(position) {
                    0 -> binding.activityIntroBtn.text = "다음"
                    1 -> binding.activityIntroBtn.text = "시작하기"
                }
            }
        })
        // 버튼 클릭 리스너
        binding.activityIntroBtn.setOnClickListener {
            if(binding.activityIntroBtn.text == "다음") {
                binding.activityIntroVp.currentItem = 1
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
    }
    inner class IntroViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            Log.d("vpAdapter","$position")
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
package com.softsquared.template.Garamgaebi.src.main.networking_game

import android.animation.*
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseFragment
import com.softsquared.template.Garamgaebi.databinding.FragmentNetworkingBinding
import com.softsquared.template.Garamgaebi.databinding.FragmentNetworkingGamePlaceBinding
import com.softsquared.template.Garamgaebi.src.main.ContainerActivity
import kotlin.concurrent.thread

class NetworkingGamePlaceFragment: BaseFragment<FragmentNetworkingGamePlaceBinding>(FragmentNetworkingGamePlaceBinding::bind, R.layout.fragment_networking_game_place) {

    //화면전환
    var containerActivity: ContainerActivity? = null

    lateinit var front_anim: AnimatorSet
    lateinit var back_anim: AnimatorSet
    //시작하기 누르고 2초뒤에 0.7초간 뒤에 뷰페이저 fadein 효과
    lateinit var fadeInAnim : Animation
    lateinit var shadow_fade_in : Animation
    //var isFront = true

    private var networkingGameProfileList: ArrayList<NetworkingGameProfile> = arrayListOf(
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "cindy", false),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "neon", false),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "이채원", false),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "신디", false),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "유진아", false),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "로건", false),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "짱구", false),
        NetworkingGameProfile(R.drawable.ic_network_game_profile, "찹도", false)

    )

    private var networkingGameCardList: ArrayList<NetworkingGameCard> = arrayListOf(
        NetworkingGameCard("나에게 가천대는"),
        NetworkingGameCard("나에게 UMC는"),
        NetworkingGameCard("나에게 신디는"),
        NetworkingGameCard("나에게 짱구는"),
        NetworkingGameCard("나에게 로건은"),
        NetworkingGameCard("나에게 찹도는"),
        NetworkingGameCard("나에게 코코아는"),
        NetworkingGameCard("나에게 줄리아는"),
    )

    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("NotifyDataSetChanged", "ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //화면전환 & 게임장소 데이터 받아오기
        //binding.activityGamePlaceTopTv.text = intent.getStringExtra("game_place")


        //프로필 관련
        var index:Int = 0;

        //뷰페이저 클릭해서 넘어가게
        var v = 0

        //카드 뷰페이저2
        val networkingGameCardVPAdapter = NetworkingGameCardVPAdapter(networkingGameCardList)
        binding.activityGameCardBackVp.adapter = NetworkingGameCardVPAdapter(networkingGameCardList)
        binding.activityGameCardBackVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        //뷰페이저 가운데 카드 그림자 애니메이션
        shadow_fade_in = AnimationUtils.loadAnimation(context, R.anim.activity_game_card_shadow_fade_in)


        //카드 뷰페이저 양 옆 overlap
        val MIN_SCALE = 0.9f
        binding.activityGameCardBackVp.setPageTransformer { page, position ->
            val pageWidth = page.width
            if(position < -1){
                page.setBackgroundResource(R.color.game_card_white)
                page.alpha =1f
                page.translationX = pageWidth * 0.9f * position
                ViewCompat.setTranslationZ(page, position)
                val  scaleFactor = (MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - kotlin.math.abs(position)))
                page.scaleY = scaleFactor
                page.scaleX = scaleFactor
            }
            else if (position < 0){
                page.setBackgroundResource(R.color.game_card_white)
                page.alpha = 1f
                page.translationX = -pageWidth * 0.9f * position
                ViewCompat.setTranslationZ(page, position)
                val  scaleFactor = (MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - kotlin.math.abs(position)))
                page.scaleY = scaleFactor
                page.scaleX = scaleFactor

            }
            else if(position == 0f) {
                page.animation = shadow_fade_in
                page.alpha = 1f
                page.translationX = 0f
                page.translationY = 0f
                page.scaleY = 1f
                ViewCompat.setTranslationZ(page, 0f)
            }
            else if(position <= 1){
                page.setBackgroundResource(R.color.game_card_white)
                //시작하기 누르고 2초뒤에 0.7초간 뒤에 뷰페이저 fadein 효과
                page.animation = fadeInAnim
                page.alpha = 1f
                page.translationX = pageWidth * 0.9f * -position
                ViewCompat.setTranslationZ(page, -position)
                val scaleFactor = (MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - kotlin.math.abs(position)))
                page.scaleY = scaleFactor
                page.scaleX = scaleFactor
            }
            else{
                page.alpha =1f
                page.translationX = pageWidth * 0.9f * position
                ViewCompat.setTranslationZ(page, position)
                val  scaleFactor = (MIN_SCALE
                        + (1 - MIN_SCALE) * (1 - kotlin.math.abs(position)))
                page.scaleY = scaleFactor
                page.scaleX = scaleFactor
            }

        }
        //뷰페이저에 elevation 효과 넣기



        //뷰페이저 넘어갈때 시간 조정
        fun ViewPager2.setCurrentItemWithDuration(item: Int, duration: Long, interpolator: TimeInterpolator = AccelerateDecelerateInterpolator(), pagePxWidth: Int = width){
            val pxToDrag: Int = pagePxWidth * (item - currentItem)
            val animator = ValueAnimator.ofInt(0, pxToDrag)
            var previousValue = 0
            animator.addUpdateListener { valueAnimator ->
                val currentValue = valueAnimator.animatedValue as Int
                val currentPxToDrag = (currentValue - previousValue).toFloat()
                fakeDragBy(-currentPxToDrag)
                previousValue = currentValue
            }
            animator.addListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(animation: Animator?) { beginFakeDrag() }
                override fun onAnimationEnd(animation: Animator?) { endFakeDrag() }
                override fun onAnimationCancel(animation: Animator?) { /* Ignored */ }
                override fun onAnimationRepeat(animation: Animator?) { /* Ignored */ }
            })
            animator.interpolator = interpolator
            animator.duration = duration
            animator.start()
        }




        binding.activityGameCardBackVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        //뷰페이저 스와이프 불가능하게
        binding.activityGameCardBackVp.run { isUserInputEnabled = false }


        //프로필 리사이클러뷰
        val networkingGameProfile = NetworkingGameProfileAdapter(networkingGameProfileList)
        binding.activityGameProfileRv.apply {
            adapter = networkingGameProfile
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(NetworkingGameProfileHorizontalItemDecoration())


            // 파란색 카드다음 버튼 누르면 프로필 가장자리 하이라이트되게 구현
            binding.activityGamePlaceCardNextBtn.setOnClickListener {
                index++
                networkingGameProfileList[index].next = true
                var i = 0
                i++
                networkingGameProfileList[index-i].next = false

                if(index >size){
                    binding.activityGamePlaceCardNextBtn.isEnabled = false
                }
                if(i >size-1){
                    binding.activityGamePlaceCardNextBtn.isEnabled = false
                }
                //리사이클러뷰 아이템 포지션으로 포커스되게 스크롤
                (layoutManager as LinearLayoutManager).scrollToPosition(index)
                networkingGameProfile.notifyDataSetChanged()

                //파란색 카드다음 버튼 누르면 뷰페이저 넘어가게 구현
                v++
                //binding.activityGameCardBackVp.setCurrentItem(v, true)
                binding.activityGameCardBackVp.setCurrentItemWithDuration(v,400)
                networkingGameCardVPAdapter.notifyDataSetChanged()

            }

        }

        //카드 뒤집기 애니메이션
        var scale = context?.resources?.displayMetrics?.density
        val front = binding.activityGameCardFrontImg
        val back = binding.activityGameCardBackImg
        front.cameraDistance = 8000 * scale!!
        back.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(context, R.anim.activity_game_front_animator)  as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(context, R.anim.activity_game_back_animator) as AnimatorSet


        binding.activityGameCardStartBtn.setOnClickListener {
            front_anim.setTarget(front)
            back_anim.setTarget(back)
            front_anim.start()
            back_anim.start()
            binding.activityGameCardStartBtn.isEnabled = false


            //시작하기 버튼 누르면 처음 사람 프로필 강조 효과
            thread(start=true){
                Thread.sleep(700)
                activity?.runOnUiThread {
                    networkingGameProfileList[index].next = true
                    networkingGameProfile.notifyDataSetChanged()

                }
            }

            //시작하기 버튼 누르면 뷰페이저 보이게
            binding.activityGameCardBackImg.visibility = View.VISIBLE


            //시작하기 버튼 누르고 2초 뒤에 뒤에 카드 생성
            thread(start=true){
                Thread.sleep(1400)
                activity?.runOnUiThread {
                    //시작하기 누르고 2초뒤에 0.5초간 뒤에 뷰페이저 fadein 효과
                    fadeInAnim = AnimationUtils.loadAnimation(context, R.anim.activity_game_fade_in)
                    binding.activityGameCardBackVp.offscreenPageLimit = 1

                }

            }

        }

    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }
}
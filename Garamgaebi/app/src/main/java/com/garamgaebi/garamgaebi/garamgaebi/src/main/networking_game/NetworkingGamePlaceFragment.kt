package com.garamgaebi.garamgaebi.garamgaebi.src.main.networking_game

import android.animation.*
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.core.view.ViewCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.garamgaebi.adapter.NetworkingGameCardVPAdapter
import com.garamgaebi.garamgaebi.garamgaebi.adapter.NetworkingGameProfileAdapter
import com.garamgaebi.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.databinding.FragmentNetworkingGamePlaceBinding
import com.garamgaebi.garamgaebi.garamgaebi.model.GameMemberGetResult
import com.garamgaebi.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.garamgaebi.viewModel.NetworkingGameViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NetworkingGamePlaceFragment: BaseFragment<FragmentNetworkingGamePlaceBinding>(FragmentNetworkingGamePlaceBinding::bind, R.layout.fragment_networking_game_place) {

    //화면전환
    var containerActivity: ContainerActivity? = null

    private lateinit var callback: OnBackPressedCallback

    lateinit var front_anim: AnimatorSet
    lateinit var back_anim: AnimatorSet
    //시작하기 누르고 2초뒤에 0.7초간 뒤에 뷰페이저 fadein 효과
    lateinit var fadeInAnim : Animation
    lateinit var shadow_fade_in : Animation

    /*private var networkingGameCardList: ArrayList<NetworkingGameCard> = arrayListOf(
        NetworkingGameCard("나에게 가천대는"),
        NetworkingGameCard("나에게 UMC는"),
        NetworkingGameCard("나에게 신디는"),
        NetworkingGameCard("나에게 짱구는"),
        NetworkingGameCard("나에게 로건은"),
        NetworkingGameCard("나에게 찹도는"),
        NetworkingGameCard("나에게 코코아는"),
        NetworkingGameCard("나에게 줄리아는"),
    )*/

    private var index = -1

    //뷰모델
    private val viewModel by viewModels<NetworkingGameViewModel>()

    @RequiresApi(Build.VERSION_CODES.P)
    @SuppressLint("NotifyDataSetChanged", "ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뷰페이저 가운데 카드 그림자 애니메이션
        shadow_fade_in =
            AnimationUtils.loadAnimation(context, R.anim.activity_game_card_shadow_fade_in)

        //뷰페이저 넘어갈때 시간 조정
        fun ViewPager2.setCurrentItemWithDuration(
            item: Int,
            duration: Long,
            interpolator: TimeInterpolator = AccelerateDecelerateInterpolator(),
            pagePxWidth: Int = width
        ) {
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
                override fun onAnimationStart(animation: Animator?) {
                    beginFakeDrag()
                }

                override fun onAnimationEnd(animation: Animator?) {
                    endFakeDrag()
                }

                override fun onAnimationCancel(animation: Animator?) { /* Ignored */
                }

                override fun onAnimationRepeat(animation: Animator?) { /* Ignored */
                }
            })
            animator.interpolator = interpolator
            animator.duration = duration
            animator.start()
        }

        //카드 뒤집기 애니메이션
        val scale = context?.resources?.displayMetrics?.density
        val front = binding.activityGameCardFrontImg
        val back = binding.activityGameCardBackImg
        front.cameraDistance = 8000 * scale!!
        back.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(
            context,
            R.anim.activity_game_front_animator
        ) as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(
            context,
            R.anim.activity_game_back_animator
        ) as AnimatorSet


        // 유저 입장
        CoroutineScope(Dispatchers.Main).launch {
            launch {
                viewModel.connectStomp()
            }
            launch {
                viewModel.postGameMember()

            }
            launch {
                viewModel.getGameMember()
            }
            launch {
                viewModel.sendMessage()
            }
        }
        viewModel.getMember.observe(viewLifecycleOwner, Observer { data ->

            val networkingGameProfile2 =
                NetworkingGameProfileAdapter(data as ArrayList<GameMemberGetResult>, index)
            binding.activityGameProfileRv.apply {
                adapter = networkingGameProfile2
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                networkingGameProfile2.notifyDataSetChanged()
            }
            //시작하기 버튼
            binding.activityGameCardStartBtn.setOnClickListener {
                front_anim.setTarget(front)
                back_anim.setTarget(back)
                front_anim.start()
                back_anim.start()
                binding.activityGameCardStartBtn.isEnabled = false

                //시작하기 버튼 누르면 뷰페이저 보이게
                binding.activityGameCardBackImg.visibility = View.VISIBLE
                index ++
                //시작하기 버튼 누르고 2초 뒤에 뒤에 카드 생성
                CoroutineScope(Dispatchers.Main).launch {
                    launch {
                        delay(1400)
                        //시작하기 누르고 2초뒤에 0.5초간 뒤에 뷰페이저 fadein 효과
                        fadeInAnim =
                            AnimationUtils.loadAnimation(context, R.anim.activity_game_fade_in)
                        binding.activityGameCardBackVp.offscreenPageLimit = 1

                    }
                }
                val networkingGameProfile1 =
                    NetworkingGameProfileAdapter(data as ArrayList<GameMemberGetResult>, index)
                binding.activityGameProfileRv.apply {
                    adapter = networkingGameProfile1
                    layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    networkingGameProfile1.notifyDataSetChanged()
                }

            }

                binding.activityGamePlaceCardNextBtn.setOnClickListener {
                    index++
                    val networkingGameProfile =
                        NetworkingGameProfileAdapter(data as ArrayList<GameMemberGetResult>, index)
                    binding.activityGameProfileRv.apply {
                        adapter = networkingGameProfile
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                    Log.d("index", index.toString())


                    (layoutManager as LinearLayoutManager).scrollToPosition(index)
                    networkingGameProfile.notifyDataSetChanged()
                        //카드 뷰페이저2 get images api 연결
                        viewModel.getImage()
                        viewModel.getImg.observe(viewLifecycleOwner, Observer {
                            val networkingGameCardVPAdapter = NetworkingGameCardVPAdapter(it)
                            binding.activityGameCardBackVp.adapter = NetworkingGameCardVPAdapter(it)
                            binding.activityGameCardBackVp.orientation = ViewPager2.ORIENTATION_HORIZONTAL

                            binding.activityGameCardBackVp.setCurrentItemWithDuration(index, 400)
                            networkingGameCardVPAdapter.notifyDataSetChanged()

                        })

                }
            }
        })

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

        binding.activityGameCardBackVp.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e("ViewPagerFragment", "Page ${position+1}")
            }
        })

        //뷰페이저 스와이프 불가능하게
        binding.activityGameCardBackVp.run { isUserInputEnabled = false }

        // 뒤로가기 눌렀을때 delete


    }

    /*fun onBackPressed() {
       Log.d("disconnect", "wow")

    }*/


    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as ContainerActivity).openFragmentOnFrameLayout(7)
                CoroutineScope(Dispatchers.Main).launch {
                    launch {
                        viewModel.postDeleteMember()
                    }
                    launch {
                        viewModel.sendDeleteMessage()

                    }
                    launch {
                        viewModel.disconnectStomp()
                    }
                }
                viewModel.getMember.observe(viewLifecycleOwner, Observer { data ->

                    val networkingGameProfile =
                        NetworkingGameProfileAdapter(data as ArrayList<GameMemberGetResult>, index)
                    binding.activityGameProfileRv.apply {
                        adapter = networkingGameProfile
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }
                })
                (activity as ContainerActivity).supportFragmentManager.beginTransaction().remove(NetworkingGamePlaceFragment()).commit()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)

    }

    override fun onDetach() {
        super.onDetach()
        callback.handleOnBackPressed()
        callback.remove()
    }

}
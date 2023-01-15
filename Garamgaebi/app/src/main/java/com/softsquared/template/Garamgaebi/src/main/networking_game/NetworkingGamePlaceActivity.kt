package com.softsquared.template.Garamgaebi.src.main.networking_game


import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.core.view.size
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityNetworkingGamePlaceBinding

class NetworkingGamePlaceActivity : BaseActivity<ActivityNetworkingGamePlaceBinding>(ActivityNetworkingGamePlaceBinding::inflate) {

    lateinit var front_anim: AnimatorSet
    lateinit var back_anim: AnimatorSet
    var isFront = true

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
    @SuppressLint("NotifyDataSetChanged", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var index:Int = 0;


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

            }

        }

        //카드 뒤집기 애니메이션
        var scale = applicationContext.resources.displayMetrics.density
        val front = binding.activityGameCardFrontImg
        val back = binding.activityGameCardBackImg
        front.cameraDistance = 8000 * scale
        back.cameraDistance = 8000 * scale

        front_anim = AnimatorInflater.loadAnimator(applicationContext, R.anim.activity_game_front_animator)  as AnimatorSet
        back_anim = AnimatorInflater.loadAnimator(applicationContext, R.anim.activity_game_back_animator) as AnimatorSet

        binding.activityGameStartBtn.setOnClickListener {
            front_anim.setTarget(front)
            back_anim.setTarget(back)
            front_anim.start()
            back_anim.start()
            binding.activityGameStartBtn.isEnabled = false

        }




    }
}
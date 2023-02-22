package com.garamgaebi.garamgaebi.src.main.networking_game

import android.animation.*
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.NetworkingGameCardVPAdapter
import com.garamgaebi.garamgaebi.adapter.NetworkingGameProfileAdapter
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.FragmentNetworkingGamePlaceBinding
import com.garamgaebi.garamgaebi.model.GameCurrentIdxRequest
import com.garamgaebi.garamgaebi.model.GameMemberDeleteRequest
import com.garamgaebi.garamgaebi.model.GameMemberGetResult
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.NetworkingGameViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NetworkingGamePlaceFragment: BaseFragment<FragmentNetworkingGamePlaceBinding>(FragmentNetworkingGamePlaceBinding::bind, R.layout.fragment_networking_game_place) {

    //화면전환
    var containerActivity: ContainerActivity? = null

    private lateinit var callback: OnBackPressedCallback

    private val memberIdx = GaramgaebiApplication.sSharedPreferences.getInt("memberIdx", 0)
    private val roomId = GaramgaebiApplication.sSharedPreferences.getString("roomId", null)

    lateinit var front_anim: AnimatorSet
    lateinit var back_anim: AnimatorSet
    //시작하기 누르고 2초뒤에 0.7초간 뒤에 뷰페이저 fadein 효과
    lateinit var fadeInAnim : Animation
    lateinit var shadow_fade_in : Animation


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
                override fun onAnimationStart(p0: Animator) {
                    beginFakeDrag()
                }

                override fun onAnimationEnd(p0: Animator) {
                    endFakeDrag()
                }

                override fun onAnimationCancel(p0: Animator) { /* Ignored */
                }

                override fun onAnimationRepeat(p0: Animator) { /* Ignored */
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
            launch {
                viewModel.getGameMember()
            }
        }
        viewModel.getImage()

        viewModel.postMember.observe(viewLifecycleOwner, Observer { it ->
                //입장하면 currentMemberIdx받기
                var index = it.result.currentImgIdx
                val currentIndex = it.result.currentMemberIdx

                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("FirstCurrentUserId", currentIndex)
                    .apply()

                Log.d("first", GaramgaebiApplication.sSharedPreferences.getInt("FirstCurrentUserId", 0).toString())

                //currentIndex를 최초 입장시 서버에서 memberIdx로 변환해줄 예정 -> data에서 자신의 currentMemberIdx로 자신의 index(currentIndex)를 받음
                Log.d("current", index.toString())
                Log.d("currentIndex", currentIndex.toString())
                viewModel.getMember.observe(viewLifecycleOwner, Observer { data ->
                    // index 찾기 성공,,,,,,,,
                    var currentId = data.indexOf(data.find { gameMemberGetResult ->
                        gameMemberGetResult.memberIdx == currentIndex
                    })
                    Log.d("currentId", currentId.toString())
                    // 프로필
                    val networkingGameProfile2 =
                        NetworkingGameProfileAdapter(data as ArrayList<GameMemberGetResult>, -1)
                    binding.activityGameProfileRv.apply {
                        adapter = networkingGameProfile2
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    }

                    //memberList data 저장
                    setPref("data", data)

                    // 룸에 들어갔을때 프로필, 뷰페이저2 보이는 부분
                    viewModel.getImg.observe(viewLifecycleOwner, Observer { it ->
                        val networkingGameCardVPAdapter = NetworkingGameCardVPAdapter(it, index)
                        binding.activityGameCardBackVp.adapter =
                            NetworkingGameCardVPAdapter(it, index)
                        binding.activityGameCardBackVp.orientation =
                            ViewPager2.ORIENTATION_HORIZONTAL


                    //시작하기 버튼
                    binding.activityGameCardStartBtn.setOnClickListener {
                        front_anim.setTarget(front)
                        back_anim.setTarget(back)
                        front_anim.start()
                        back_anim.start()
                        binding.activityGameCardStartBtn.isEnabled = false

                        //시작하기 버튼 누르면 뷰페이저 보이게
                        binding.activityGameCardBackImg.visibility = View.VISIBLE

                        // 프로필
                        val networkingGameProfile3 =
                            NetworkingGameProfileAdapter(data as ArrayList<GameMemberGetResult>, currentId)
                        binding.activityGameProfileRv.apply {
                            adapter = networkingGameProfile3
                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                            // currentMemberIdx로 스크롤
                            (layoutManager as LinearLayoutManager).scrollToPosition(currentId)
                            networkingGameProfile2.notifyDataSetChanged()
                        }
                        // 다음 버튼 순서인 사람 뷰에서만 보이게 처리
                        if(memberIdx == data[currentId].memberIdx){
                            binding.activityGamePlaceCardNextBtn.visibility = VISIBLE
                        }

                        //시작하기 버튼 누르고 2초 뒤에 뒤에 카드 생성
                        //index + 1
                        CoroutineScope(Dispatchers.Main).launch {
                            launch {
                                delay(1400)
                                //시작하기 누르고 2초뒤에 0.5초간 뒤에 뷰페이저 fadein 효과
                                fadeInAnim =
                                    AnimationUtils.loadAnimation(
                                        context,
                                        R.anim.activity_game_fade_in
                                    )
                                binding.activityGameCardBackVp.offscreenPageLimit = 1

                            }
                        }

                    }

                    // 다음 버튼 눌렀을때
                    binding.activityGamePlaceCardNextBtn.setOnClickListener {

                        val last = data.lastIndex
                        Log.d("last", last.toString())
                        if(data[currentId].memberIdx == data[last].memberIdx){
                            currentId = 0
                            val nextId = data[currentId].memberIdx
                            Log.d("nextId", nextId.toString())
                            Log.d("currentId", currentId.toString())
                            // 전체: 해당 memberId를 userList에서 찾고 그 위치로 스크롤, currentUserId에 저장
                            GaramgaebiApplication.sSharedPreferences
                                .edit().putInt("currentUserId", nextId)
                                .apply()

                            CoroutineScope(Dispatchers.Main).launch{
                                launch {
                                    // PATCH(NEXT때 보냈던 memberIdx를 보냄)
                                    roomId?.let { it1 ->
                                        GameCurrentIdxRequest(
                                            it1, nextId)
                                    }?.let { it2 -> viewModel.patchGameCurrentIdx(it2) }
                                }
                                launch {
                                    viewModel.sendCurrentIdxMessage(nextId)
                                }
                            }
                        }
                        else{
                            val nextId = data[currentId++].memberIdx
                            Log.d("nextId", nextId.toString())
                            Log.d("currentIdelse", currentId.toString())
                            // 전체: 해당 memberId를 userList에서 찾고 그 위치로 스크롤, currentUserId에 저장
                            GaramgaebiApplication.sSharedPreferences
                                .edit().putInt("currentUserId", nextId)
                                .apply()

                            CoroutineScope(Dispatchers.Main).launch{
                                launch {
                                    // PATCH(NEXT때 보냈던 memberIdx를 보냄)
                                    roomId?.let { it1 ->
                                        GameCurrentIdxRequest(
                                            it1, nextId)
                                    }?.let { it2 -> viewModel.patchGameCurrentIdx(it2) }
                                }
                                launch {
                                    viewModel.sendCurrentIdxMessage(nextId)
                                }
                            }
                        }
                        //
                        //

                        index++
                        Log.d("index", index.toString())
                        //뷰페이저 하나만 넘어가는 거 해결
                        var tab =  binding.activityGameCardBackVp.currentItem
                        tab++
                        Log.d("tab", tab.toString())
                        binding.activityGameCardBackVp.setCurrentItemWithDuration(tab, 400)
                        networkingGameCardVPAdapter.notifyDataSetChanged()


                    }
                        viewModel.patchMessage.observe(viewLifecycleOwner, Observer {
                            val currentId2 = data.indexOf(data.find { gameMemberGetResult ->
                                gameMemberGetResult.memberIdx.toString() == it.message
                            })
                            //val currentId3 = currentId2 + 1
                            Log.d("message", it.message)

                            // 다음 버튼 순서인 사람 뷰에서만 보이게 처리
                            if(currentId2 != -1){
                                if(memberIdx == data[currentId2].memberIdx){
                                   binding.activityGamePlaceCardNextBtn.visibility = VISIBLE
                               }
                            }
                            //Log.d("member", data[currentId2].memberIdx.toString())
                            Log.d("currentId2", currentId2.toString())
                            val networkingGameProfile =
                                NetworkingGameProfileAdapter(
                                    data as ArrayList<GameMemberGetResult>,
                                    currentId2
                                )
                            binding.activityGameProfileRv.apply {
                                adapter = networkingGameProfile
                                layoutManager =
                                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                                (layoutManager as LinearLayoutManager).scrollToPosition(currentId2)
                                networkingGameProfile.notifyDataSetChanged()
                            }
                        })

                    })
                })

        })


        //카드 뷰페이저 양 옆 overlap
        val MIN_SCALE = 0.9f
        binding.activityGameCardBackVp.setPageTransformer { page, position ->
            val pageWidth = page.width
            if(position < -1){
                page.setBackgroundResource(R.color.game_card_white)
                page.alpha =1f
                //page.animation = fadeInAnim
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
                //page.animation = fadeInAnim
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



    }

    //화면전환 뒤로가기할때 delete & disconnect
    @SuppressLint("NotifyDataSetChanged", "ResourceType")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                (activity as ContainerActivity).openFragmentOnFrameLayout(7)
                CoroutineScope(Dispatchers.Main).launch {
                    launch {
                        // 퇴장유저가 자신 차례가 아닌경우(self.memberID != currentUserID)
                        if(memberIdx != GaramgaebiApplication.sSharedPreferences.getInt("currentUserId", 0)){
                            roomId?.let { GameMemberDeleteRequest(it, -1) }
                                ?.let { viewModel.postDeleteMember(it) }
                        }
                        // 퇴장유저가 자신 차례인 경우(self.memberID == currentUserID)
                        val memberList = getPref("data")

                        // nextMemberIdx에 userList에서 자신의 memberId를 찾고 그 다음 사람을 보냄
                        if(memberIdx == GaramgaebiApplication.sSharedPreferences.getInt("currentUserId", 0)){

                            //혼자 참여라면 -1주고 퇴장
                            if(memberList.size == 1){
                                roomId?.let { GameMemberDeleteRequest(it, -1) }
                                    ?.let { viewModel.postDeleteMember(it) }

                            }

                            var currentId = memberList.indexOf(memberList.find { gameMemberGetResult ->
                                gameMemberGetResult.memberIdx == GaramgaebiApplication.sSharedPreferences.getInt("currentUserId", 0)
                            })
                            val lastIndex = memberList.lastIndex
                            if(memberList[currentId].memberIdx == memberList[lastIndex].memberIdx){
                                currentId = 0
                                val nextId = memberList[currentId].memberIdx
                                GaramgaebiApplication.sSharedPreferences
                                    .edit().putInt("deleteNext", currentId)
                                    .apply()

                                roomId?.let {
                                    GameMemberDeleteRequest(
                                        it, nextId)
                                }?.let { viewModel.postDeleteMember(it) }
                            }
                            else{
                                val nextId = memberList[currentId + 1].memberIdx
                                GaramgaebiApplication.sSharedPreferences
                                    .edit().putInt("deleteNext", currentId + 1)
                                    .apply()

                                roomId?.let {
                                    GameMemberDeleteRequest(
                                        it, nextId)
                                }?.let { viewModel.postDeleteMember(it) }
                            }
                        }
                    }
                    launch {
                        viewModel.sendDeleteMessage()
                    }
                    launch {
                        viewModel.getGameMember()
                    }
                    launch {
                        viewModel.disconnectStomp()
                    }
                }
                viewModel.message.observe(viewLifecycleOwner, Observer {
                val deleteCurrent = it.message
                viewModel.deleteMember.observe(viewLifecycleOwner, Observer {
                    viewModel.getMember.observe(viewLifecycleOwner, Observer { data ->
                        // 퇴장멤버ID == currentID -> 스크롤
                        val memberList = getPref("data")
                        Log.d("memberList", memberList.toString())
                        if(deleteCurrent == GaramgaebiApplication.sSharedPreferences.getInt("deleteNext", 0).toString()){
                            var currentId = memberList.indexOf(memberList.find { gameMemberGetResult ->
                                gameMemberGetResult.memberIdx == GaramgaebiApplication.sSharedPreferences.getInt("deleteNext", 0)
                            })
                            //
                            val lastIndex = memberList.size - 1
                            if(memberList[currentId].memberIdx == memberList[lastIndex].memberIdx){
                                currentId = 0
                                val networkingGameProfile =
                                    NetworkingGameProfileAdapter(data as ArrayList<GameMemberGetResult>, currentId)
                                binding.activityGameProfileRv.apply {
                                    adapter = networkingGameProfile
                                    layoutManager =
                                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                                    (layoutManager as LinearLayoutManager).scrollToPosition(currentId)
                                    networkingGameProfile.notifyDataSetChanged()
                                }
                            }
                            else{
                                val networkingGameProfile =
                                    NetworkingGameProfileAdapter(data as ArrayList<GameMemberGetResult>, currentId + 1)
                                binding.activityGameProfileRv.apply {
                                    adapter = networkingGameProfile
                                    layoutManager =
                                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                                    (layoutManager as LinearLayoutManager).scrollToPosition(currentId + 1)
                                    networkingGameProfile.notifyDataSetChanged()
                                }
                            }
                        }
                    })
                })
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

    //memberList data 저장
    fun setPref(key:String, values: List<GameMemberGetResult>){
        val gson = Gson()
        val json = gson.toJson(values)
        GaramgaebiApplication.sSharedPreferences
            .edit().putString(key, json)
            .apply()
    }

    //memberList data 저장 값 불러옴
    fun getPref(key: String): List<GameMemberGetResult> {
        val json = GaramgaebiApplication.sSharedPreferences.getString(key, null)
        val gson = Gson()

        return gson.fromJson(
            json,
            object : TypeToken<List<GameMemberGetResult?>>() {}.type
        )
    }

}
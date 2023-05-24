package com.garamgaebi.garamgaebi.src.main.networking_game

import android.animation.*
import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.*
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
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.databinding.FragmentNetworkingGamePlaceBinding
import com.garamgaebi.garamgaebi.model.GameCurrentIdxRequest
import com.garamgaebi.garamgaebi.model.GameIsStartedRequest
import com.garamgaebi.garamgaebi.model.GameMemberDeleteRequest
import com.garamgaebi.garamgaebi.model.GameMemberGetResult
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.NetworkingGameViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

/**
 <NetworkingGamePlaceFragment>
 화면기능 : 1. 유저 입장
          2. 현재 차례인 유저 파란색 테두리로 프로필 강조
          3. 시작하기 누르면 방에 들어온 참가자들의 카드가 동시에 뒤집어짐
          4. 현재 차례인 유저가 카드 다음버튼 누르면 모든 참여자의 카드가 다음카드로 넘어가짐
          5. 헤더의 뒤로가기 버튼 누르면 퇴장.
          6. 방에 입장한 후 모든 기능들은 실시간 양방향 소통으로 이루어짐 ( Stomp 사용 NetworkingGameViewModel에 코드 있음)


NEXT 버튼 누르면
-> 누른 사람: NEXT(message: userList에서 자신의 memberId를 찾고 그 다음 사람)
-> PATCH(NEXT때 보냈던 memberIdx를 보냄)
-> 전체: 해당 memberId를 userList에서 찾고 그 위치로 스크롤, currentUserId에 저장

퇴장 하면
-> 퇴장유저가 자신 차례가 아닌경우(self.memberID != currentUserID)
-> nextMemberIdx에 -1, delete
-> 퇴장유저가 자신 차례인 경우(self.memberID == currentUserID)
-> nextMemberIdx에 userList에서 자신의 memberId를 찾고 그 다음 사람을 보냄

입장 하면
-> 서버에서 현재 memberId를 받고, userList에서 그 위치로 스크롤


- 유저 입장
-> connect, subscribe
-> 게임 방 유저 등록 POST (game/member)
-> 현재 유저 조회 POST (game/members)
-> ENTER() (websocket)
-> 기존 유저 ENTER 수신 (websocket)
-> 기존 유저: 현재 유저 조회 POST (game/members)

- 다음 이미지 버튼을 누를 때 websocket에 send(type: NEXT)를 보내고 난 후,
현재 게임방 이미지 인덱스 증가 API를 호출
-> patch
-> send

- 유저 퇴장
-> 게임 방 유저 제거 DELETE (game/member)
-> EXIT(sender: 닉네임)
-> 기존 유저 EXIT 수신
-> 기존 유저: 현재 유저 조회 POST (game/members)
-> disconnect


 */

class NetworkingGamePlaceFragment: BaseFragment<FragmentNetworkingGamePlaceBinding>(FragmentNetworkingGamePlaceBinding::bind, R.layout.fragment_networking_game_place) {

    //화면전환
    var containerActivity: ContainerActivity? = null
    private val memberIdx = myMemberIdx
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
        binding.activityGamePlaceCardNextBtn.visibility = VISIBLE
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
        viewModel.getImage()
        CoroutineScope(Dispatchers.Main).launch {
            withContext(Dispatchers.IO) {
                viewModel.connectStomp()
            }
            withContext(Dispatchers.IO){
                viewModel.postGameMember()
            }
            withContext(Dispatchers.IO){
                viewModel.getGameMember()
            }
            withContext(Dispatchers.IO){
                viewModel.sendMessage()
            }
            withContext(Dispatchers.IO){
                viewModel.getGameMember()
            }
        }

        var index : Int = 0

        viewModel.postMember.observe(viewLifecycleOwner, Observer { it ->
            //입장하면 currentMemberIdx받기
            index = it.result.currentImgIdx

            GaramgaebiApplication.sSharedPreferences
                .edit().putInt("index", index)
                .apply()

            val currentIndex = it.result.currentMemberIdx

            GaramgaebiApplication.sSharedPreferences
                .edit().putInt("FirstCurrentUserId", currentIndex)
                .apply()

            //currentIndex를 최초 입장시 서버에서 memberIdx로 변환해줄 예정 -> data에서 자신의 currentMemberIdx로 자신의 index(currentIndex)를 받음
        })

        viewModel.getMember.observe(viewLifecycleOwner, Observer { data ->
            // index 찾기
            val currentId = data.indexOf(data.find { gameMemberGetResult ->
                gameMemberGetResult.memberIdx == data[0].currentMemberIdx
            })
            GaramgaebiApplication.sSharedPreferences
                .edit().putInt("currentId", currentId)
                .apply()
            //memberList data 저장
            setPref("data", data)

        })

        //var index = GaramgaebiApplication.sSharedPreferences.getInt("index", 0)
        viewModel.message.observe(viewLifecycleOwner, Observer { enter ->
            viewModel.getMember.observe(viewLifecycleOwner, Observer { data->

                val currentId = GaramgaebiApplication.sSharedPreferences.getInt("currentId", 0)

                // 방에 들어갔을때 파란색 테두리 없이 나타나는 프로필 리사이클러뷰,,
                val networkingGameProfile2 =
                    NetworkingGameProfileAdapter(
                        data as ArrayList<GameMemberGetResult>,
                        currentId
                    )

                binding.activityGameProfileRv.apply {
                    adapter = networkingGameProfile2
                    layoutManager =
                        LinearLayoutManager(
                            context,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    (layoutManager as LinearLayoutManager).scrollToPosition(currentId)
                    networkingGameProfile2.notifyDataSetChanged()
                }

                if (currentId != -1) {
                    if (memberIdx == data[currentId].memberIdx) {
                        binding.activityGamePlaceCardNextBtn.visibility =
                            VISIBLE
                    } else {
                        binding.activityGamePlaceCardNextBtn.visibility = GONE
                    }
                }
            })

        })

        // 룸에 들어갔을때 프로필, 뷰페이저2 보이는 부분
        viewModel.getImg.observe(viewLifecycleOwner, Observer { img ->

                index = GaramgaebiApplication.sSharedPreferences.getInt("index", 0)

                val networkingGameCardVPAdapter =
                    NetworkingGameCardVPAdapter(img, index)
                binding.activityGameCardBackVp.adapter =
                    NetworkingGameCardVPAdapter(img, index)
                binding.activityGameCardBackVp.orientation =
                    ViewPager2.ORIENTATION_HORIZONTAL
                networkingGameCardVPAdapter.notifyDataSetChanged()

            setImg("img", img)


        })

        //시작 한 게임인지 아닌지
        roomId?.let { GameIsStartedRequest(it) }?.let { viewModel.postGameIsStarted(it) }
        viewModel.postGameIsStarted.observe(viewLifecycleOwner, Observer { start ->
            val currentId = GaramgaebiApplication.sSharedPreferences.getInt("currentId", 0)
            if(start.result){
                binding.activityGameCardStartBtn.isEnabled = false
                binding.activityGameCardFrontImg.visibility = INVISIBLE
                binding.activityGameCardBackImg.visibility = VISIBLE

                // 프로필
                viewModel.getMember.observe(viewLifecycleOwner, Observer { data->

                    val networkingGameProfile2 =
                        NetworkingGameProfileAdapter(
                            data as ArrayList<GameMemberGetResult>,
                            currentId
                        )
                    binding.activityGameProfileRv.apply {
                        adapter = networkingGameProfile2
                        layoutManager =
                            LinearLayoutManager(
                                context,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                        // currentMemberIdx로 스크롤
                        (layoutManager as LinearLayoutManager).scrollToPosition(
                            currentId
                        )
                        networkingGameProfile2.notifyDataSetChanged()
                    }

                })

                binding.activityGameCardBackVp.offscreenPageLimit = 1
            }
            else {
                binding.activityGameCardStartBtn.isEnabled = true
            }

        })

        //시작하기 버튼
        binding.activityGameCardStartBtn.setOnClickListener {

            CoroutineScope(Dispatchers.Main).launch {
                withContext(Dispatchers.IO) {
                    viewModel.patchGameStart()
                }
                withContext(Dispatchers.IO) {
                    viewModel.sendStartMessage()
                }
            }

        }

        //시작하기 눌렀을때 동시에 넘어가기
        viewModel.startMessage.observe(viewLifecycleOwner, Observer {

            val currentId = GaramgaebiApplication.sSharedPreferences.getInt("currentId", 0)

            front_anim.setTarget(front)
            back_anim.setTarget(back)
            front_anim.start()
            back_anim.start()
            binding.activityGameCardStartBtn.isEnabled = false

            //시작하기 버튼 누르면 뷰페이저 보이게
            binding.activityGameCardBackImg.visibility = View.VISIBLE

            // 프로필
            viewModel.getMember.observe(viewLifecycleOwner, Observer { data->

                val networkingGameProfile2 =
                    NetworkingGameProfileAdapter(
                        data as ArrayList<GameMemberGetResult>,
                        currentId
                    )
                binding.activityGameProfileRv.apply {
                    adapter = networkingGameProfile2
                    layoutManager =
                        LinearLayoutManager(
                            context,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    // currentMemberIdx로 스크롤
                    (layoutManager as LinearLayoutManager).scrollToPosition(
                        currentId
                    )
                    networkingGameProfile2.notifyDataSetChanged()
                }

                // 다음 버튼 순서인 사람 뷰에서만 보이게 처리
                if (currentId != -1) {
                    if (memberIdx == data[currentId].memberIdx) {
                        binding.activityGamePlaceCardNextBtn.visibility =
                            VISIBLE
                    } else {
                        binding.activityGamePlaceCardNextBtn.visibility = GONE
                    }
                }
            })


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
        })

        // 다음 버튼 눌렀을때
        binding.activityGamePlaceCardNextBtn.setOnClickListener {
            val data = getPref("data")
            val last = data.lastIndex

            var currentId = GaramgaebiApplication.sSharedPreferences.getInt("currentId2", 0)

            // 마지막 index의 memberIdx가 현재 index의 memberIdx와 같을때 현재 index를 0으로 이동 시킴
            if (currentId == -1) {
                currentId = 0
                if (data[currentId].memberIdx == data[last].memberIdx) {
                    currentId = 0
                    val nextId = data[currentId].memberIdx
                    val nextId4 = data[last].memberIdx

                    // 전체: 해당 memberId를 userList에서 찾고 그 위치로 스크롤, currentUserId에 저장
                    GaramgaebiApplication.sSharedPreferences
                        .edit().putInt("currentUserId", nextId)
                        .apply()

                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            viewModel.sendCurrentIdxMessage(nextId)
                        }
                        withContext(Dispatchers.IO) {
                            // PATCH(NEXT때 보냈던 memberIdx를 보냄)
                            roomId?.let { it1 ->
                                GameCurrentIdxRequest(
                                    it1, nextId
                                )
                            }?.let { it2 -> viewModel.patchGameCurrentIdx(it2) }

                        }
                    }
                } else {
                    val nextId = data[currentId + 1].memberIdx
                    val nextId3 = data[currentId].memberIdx

                    // 전체: 해당 memberId를 userList에서 찾고 그 위치로 스크롤, currentUserId에 저장
                    GaramgaebiApplication.sSharedPreferences
                        .edit().putInt("currentUserId", nextId)
                        .apply()

                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            viewModel.sendCurrentIdxMessage(nextId)
                        }
                        withContext(Dispatchers.IO) {
                            // PATCH(NEXT때 보냈던 memberIdx를 보냄)
                            roomId?.let { it1 ->
                                GameCurrentIdxRequest(
                                    it1, nextId
                                )
                            }?.let { it2 -> viewModel.patchGameCurrentIdx(it2) }
                        }
                    }

                }
            } else {
                if (data[currentId].memberIdx == data[last].memberIdx) {
                    currentId = 0
                    val nextId = data[currentId].memberIdx
                    val nextId4 = data[last].memberIdx

                    // 전체: 해당 memberId를 userList에서 찾고 그 위치로 스크롤, currentUserId에 저장
                    GaramgaebiApplication.sSharedPreferences
                        .edit().putInt("currentUserId", nextId)
                        .apply()

                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            viewModel.sendCurrentIdxMessage(nextId)
                        }
                        withContext(Dispatchers.IO) {
                            // PATCH(NEXT때 보냈던 memberIdx를 보냄)
                            roomId?.let { it1 ->
                                GameCurrentIdxRequest(
                                    it1, nextId
                                )
                            }?.let { it2 -> viewModel.patchGameCurrentIdx(it2) }
                        }
                    }
                } else {
                    val nextId = data[currentId + 1].memberIdx
                    val nextId3 = data[currentId].memberIdx

                    // 전체: 해당 memberId를 userList에서 찾고 그 위치로 스크롤, currentUserId에 저장
                    GaramgaebiApplication.sSharedPreferences
                        .edit().putInt("currentUserId", nextId)
                        .apply()

                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            viewModel.sendCurrentIdxMessage(nextId)
                        }
                        withContext(Dispatchers.IO) {
                            // PATCH(NEXT때 보냈던 memberIdx를 보냄)
                            roomId?.let { it1 ->
                                GameCurrentIdxRequest(
                                    it1, nextId
                                )
                            }?.let { it2 -> viewModel.patchGameCurrentIdx(it2) }
                        }
                    }

                }
           }
        }
        //

        viewModel.patchMessage.observe(viewLifecycleOwner, Observer { it ->

                   //var index1 = GaramgaebiApplication.sSharedPreferences.getInt("index", 0)
                    if (index == 29) {
                        index = 0
                    } else {
                        index++
                    }

                    viewModel.getImg.observe(viewLifecycleOwner, Observer { img ->
                        val networkingGameCardVPAdapter =
                            NetworkingGameCardVPAdapter(img, index)
                        binding.activityGameCardBackVp.adapter =
                            NetworkingGameCardVPAdapter(img, index)
                        binding.activityGameCardBackVp.orientation =
                            ViewPager2.ORIENTATION_HORIZONTAL
                        var tab = binding.activityGameCardBackVp.currentItem
                        tab++

                        binding.activityGameCardBackVp.setCurrentItem(tab, true)

                        networkingGameCardVPAdapter.notifyDataSetChanged()
                    })
                val data = getPref("data")

                val currentId2 = data.indexOf(data.find { gameMemberGetResult ->
                    gameMemberGetResult.memberIdx.toString() == it.message
                })

                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("currentId2", currentId2)
                    .apply()

            GaramgaebiApplication.sSharedPreferences
                .edit().putInt("currentId2memberIdx", it.message.toInt())
                .apply()

                // 다음 버튼 순서인 사람 뷰에서만 보이게 처리
                if (currentId2 != -1) {
                    if (memberIdx == data[currentId2].memberIdx) {
                        binding.activityGamePlaceCardNextBtn.visibility =
                            VISIBLE
                    } else {
                        binding.activityGamePlaceCardNextBtn.visibility = GONE
                    }

                }

                val networkingGameProfile =
                    NetworkingGameProfileAdapter(
                        data as ArrayList<GameMemberGetResult>,
                        currentId2
                    )
                binding.activityGameProfileRv.apply {
                    adapter = networkingGameProfile
                    layoutManager =
                        LinearLayoutManager(
                            context,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    (layoutManager as LinearLayoutManager).scrollToPosition(
                        currentId2
                    )
                    networkingGameProfile.notifyDataSetChanged()
                }

        })

        //퇴장
            viewModel.deleteMessage.observe(viewLifecycleOwner, Observer {
                val memberList = getPref("data")
                viewModel.getMember.observe(viewLifecycleOwner, Observer { data ->
                // 퇴장멤버ID == currentID -> 스크롤
                val deleteCurrent = it.message

                if (deleteCurrent == GaramgaebiApplication.sSharedPreferences.getInt("currentUserId", 0).toString()
                ) {
                    //
                    var currentId4 =
                        memberList.indexOf(memberList.find { gameMemberGetResult ->
                            gameMemberGetResult.memberIdx == deleteCurrent.toInt()
                        })
                    val lastIndex = memberList.lastIndex

                    if (memberList[currentId4].memberIdx == memberList[lastIndex].memberIdx) {
                        currentId4 = 0
                        val networkingGameProfile =
                            NetworkingGameProfileAdapter(
                                data as ArrayList<GameMemberGetResult>,
                                currentId4
                            )
                        binding.activityGameProfileRv.apply {
                            adapter = networkingGameProfile
                            layoutManager =
                                LinearLayoutManager(
                                    context,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                            (layoutManager as LinearLayoutManager).scrollToPosition(
                                currentId4
                            )
                            networkingGameProfile.notifyDataSetChanged()
                        }

                        if (memberIdx == data[currentId4].memberIdx) {
                            binding.activityGamePlaceCardNextBtn.visibility =
                                VISIBLE
                        } else {
                            binding.activityGamePlaceCardNextBtn.visibility = GONE
                        }

                    } else {
                        val networkingGameProfile =
                            NetworkingGameProfileAdapter(
                                data as ArrayList<GameMemberGetResult>,
                                currentId4
                            )
                        binding.activityGameProfileRv.apply {
                            adapter = networkingGameProfile
                            layoutManager =
                                LinearLayoutManager(
                                    context,
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )
                            (layoutManager as LinearLayoutManager).scrollToPosition(
                                currentId4
                            )
                            networkingGameProfile.notifyDataSetChanged()
                        }

                        if (memberIdx == data[currentId4].memberIdx) {
                            binding.activityGamePlaceCardNextBtn.visibility =
                                VISIBLE
                        } else {
                            binding.activityGamePlaceCardNextBtn.visibility = GONE
                        }

                    }
                } else {
                    val currentId5 =
                        data.indexOf(memberList.find { gameMemberGetResult ->
                            gameMemberGetResult.memberIdx == GaramgaebiApplication.sSharedPreferences.getInt(
                                "currentUserId",
                                0
                            )
                        })
                    val networkingGameProfile =
                        NetworkingGameProfileAdapter(
                            data as ArrayList<GameMemberGetResult>,
                            currentId5
                        )
                    binding.activityGameProfileRv.apply {
                        adapter = networkingGameProfile
                        layoutManager =
                            LinearLayoutManager(
                                context,
                                LinearLayoutManager.HORIZONTAL,
                                false
                            )
                        (layoutManager as LinearLayoutManager).scrollToPosition(
                            currentId5
                        )
                        networkingGameProfile.notifyDataSetChanged()
                    }
                }
            })
        })
        //퇴장 마지막

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
    private var callback: OnBackPressedCallback? = null

    //화면전환 뒤로가기할때 delete & disconnect 유저퇴장
    @SuppressLint("NotifyDataSetChanged", "ResourceType")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
        if (callback == null) {
            callback = object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    CoroutineScope(Dispatchers.Main).launch {
                        runBlocking {
                            withContext(Dispatchers.IO) {
                                deleteMember()
                            }
                        }
                        runBlocking {
                            withContext(Dispatchers.IO) {
                                viewModel.sendDeleteMessage()
                            }
                        }
                        runBlocking {
                            withContext(Dispatchers.IO) {
                                viewModel.getGameMember()
                            }
                        }
                        runBlocking {
                            withContext(Dispatchers.IO) {
                                viewModel.disconnectStomp()
                                requireActivity().supportFragmentManager.popBackStack()
                                (requireActivity() as ContainerActivity).backIceBreaking()

                            }
                        }

                    }
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback!!)

    }

    override fun onDetach() {
        super.onDetach()
        callback?.remove()

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
        var json = GaramgaebiApplication.sSharedPreferences.getString(key, null)
        if(GaramgaebiApplication.sSharedPreferences.getString(key, null).toString() != null){
            json = GaramgaebiApplication.sSharedPreferences.getString(key, null)
        }
        val gson = Gson()

        return gson.fromJson(
            json,
            object : TypeToken<List<GameMemberGetResult?>>() {}.type
        )
    }

    fun setImg(key:String, values: List<String>){
        val gson = Gson()
        val json = gson.toJson(values)
        GaramgaebiApplication.sSharedPreferences
            .edit().putString(key, json)
            .apply()
    }

    //memberList data 저장 값 불러옴
    fun getImg(key: String): List<String> {
        var json = GaramgaebiApplication.sSharedPreferences.getString(key, null)
        if(GaramgaebiApplication.sSharedPreferences.getString(key, null).toString() != null){
            json = GaramgaebiApplication.sSharedPreferences.getString(key, null)
        }
        val gson = Gson()

        return gson.fromJson(
            json,
            object : TypeToken<List<String?>>() {}.type
        )

    }


    fun deleteMember(){
        // 퇴장유저가 자신 차례가 아닌경우(self.memberID != currentUserID)
        val memberList = getPref("data")
        if(memberIdx != GaramgaebiApplication.sSharedPreferences.getInt("currentId2memberIdx", 0)){

            var currentId = memberList.indexOf(memberList.find { gameMemberGetResult ->
                gameMemberGetResult.memberIdx == memberIdx
            })

            val lastIndex = memberList.lastIndex

            if(memberList[currentId].memberIdx == memberList[lastIndex].memberIdx){
                roomId?.let { GameMemberDeleteRequest(it, -1) }
                    ?.let { viewModel.postDeleteMember(it) }
                currentId = 0
                val nextId = memberList[currentId].memberIdx
                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("currentId2", currentId)
                    .apply()
            }
            else{
                roomId?.let { GameMemberDeleteRequest(it, -1) }
                    ?.let { viewModel.postDeleteMember(it) }
                val nextId = memberList[currentId + 1].memberIdx
                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("currentId2", currentId)
                    .apply()
            }
        }
        // 퇴장유저가 자신 차례인 경우(self.memberID == currentUserID)

        // nextMemberIdx에 userList에서 자신의 memberId를 찾고 그 다음 사람을 보냄
        if(memberIdx == GaramgaebiApplication.sSharedPreferences.getInt("currentId2memberIdx", 0)){
            //혼자 참여라면 -1주고 퇴장
            if(memberList.size == 1){
                roomId?.let { GameMemberDeleteRequest(it, -1) }
                    ?.let { viewModel.postDeleteMember(it) }

            }
            var currentId = memberList.indexOf(memberList.find { gameMemberGetResult ->
                gameMemberGetResult.memberIdx == memberIdx
            })

            val lastIndex = memberList.lastIndex

            if(memberList[currentId].memberIdx == memberList[lastIndex].memberIdx){
                currentId = 0
                val nextId = memberList[currentId].memberIdx
                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("deleteNext", nextId)
                    .apply()

                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("currentId2", currentId)
                    .apply()

                roomId?.let {
                    GameMemberDeleteRequest(
                        it, nextId)
                }?.let { viewModel.postDeleteMember(it) }
            }
            else{
                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("currentId2", currentId)
                    .apply()
                val nextId = memberList[currentId + 1].memberIdx
                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("deleteNext", nextId)
                    .apply()

                roomId?.let {
                    GameMemberDeleteRequest(
                        it, nextId)
                }?.let { viewModel.postDeleteMember(it) }
            }
        }
    }
}
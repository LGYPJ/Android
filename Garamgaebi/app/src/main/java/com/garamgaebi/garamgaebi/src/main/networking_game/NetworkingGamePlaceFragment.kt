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
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class NetworkingGamePlaceFragment: BaseFragment<FragmentNetworkingGamePlaceBinding>(FragmentNetworkingGamePlaceBinding::bind, R.layout.fragment_networking_game_place) {

    //화면전환
    var containerActivity: ContainerActivity? = null
    private val memberIdx = GaramgaebiApplication.myMemberIdx
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

        Log.d("why1", "why1")

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

        viewModel.postMember.observe(viewLifecycleOwner, Observer { it ->
            //입장하면 currentMemberIdx받기
            val index: Int = if (it.result.currentImgIdx == 30) {
                0
            } else {
                it.result.currentImgIdx
            }
            GaramgaebiApplication.sSharedPreferences
                .edit().putInt("index", index)
                .apply()

            Log.d("exitindex", index.toString())
            val currentIndex = it.result.currentMemberIdx

            GaramgaebiApplication.sSharedPreferences
                .edit().putInt("FirstCurrentUserId", currentIndex)
                .apply()

            Log.d(
                "first",
                GaramgaebiApplication.sSharedPreferences.getInt("FirstCurrentUserId", 0)
                    .toString()
            )


            //currentIndex를 최초 입장시 서버에서 memberIdx로 변환해줄 예정 -> data에서 자신의 currentMemberIdx로 자신의 index(currentIndex)를 받음
            Log.d("postMember", it.result.toString())
            Log.d("indeximg", index.toString())
            Log.d("currentIndex", currentIndex.toString())
        })

        viewModel.getMember.observe(viewLifecycleOwner, Observer { data ->
            // index 찾기
            val currentId = data.indexOf(data.find { gameMemberGetResult ->
                gameMemberGetResult.memberIdx == GaramgaebiApplication.sSharedPreferences.getInt("FirstCurrentUserId",0)
            })
            GaramgaebiApplication.sSharedPreferences
                .edit().putInt("currentId", currentId)
                .apply()

            //memberList data 저장
            setPref("data", data)
            Log.d("data", data.toString())
        })

        var currentId = GaramgaebiApplication.sSharedPreferences.getInt("currentId", 0)
        var index = GaramgaebiApplication.sSharedPreferences.getInt("index", 0)

        viewModel.message.observe(viewLifecycleOwner, Observer { enter ->
            viewModel.getMember.observe(viewLifecycleOwner, Observer { data ->
                Log.d("currentId", currentId.toString())
                // 방에 들어갔을때 파란색 테두리 없이 나타나는 프로필 리사이클러뷰,,
                val networkingGameProfile2 =
                    NetworkingGameProfileAdapter(
                        data as ArrayList<GameMemberGetResult>,
                        -1
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
            })
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
            Log.d("deletestart", "deletestart")
            Log.d("startcurrentId", currentId.toString())
            front_anim.setTarget(front)
            back_anim.setTarget(back)
            front_anim.start()
            back_anim.start()
            binding.activityGameCardStartBtn.isEnabled = false

            //시작하기 버튼 누르면 뷰페이저 보이게
            binding.activityGameCardBackImg.visibility = View.VISIBLE

            // 룸에 들어갔을때 프로필, 뷰페이저2 보이는 부분
            viewModel.getImg.observe(viewLifecycleOwner, Observer { img ->
                setImg("img", img)
                val networkingGameCardVPAdapter =
                    NetworkingGameCardVPAdapter(img, index)
                binding.activityGameCardBackVp.adapter =
                    NetworkingGameCardVPAdapter(img, index)
                binding.activityGameCardBackVp.orientation =
                    ViewPager2.ORIENTATION_HORIZONTAL

            })

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
            Log.d("nextlast", last.toString())
            currentId =
                GaramgaebiApplication.sSharedPreferences.getInt("currentId2", 0)

            Log.d("nextcurrentId", currentId.toString())
            // 마지막 index의 memberIdx가 현재 index의 memberIdx와 같을때 현재 index를 0으로 이동 시킴
            if (currentId == -1) {
                currentId = 0
                if (data[currentId].memberIdx == data[last].memberIdx) {
                    currentId = 0
                    val nextId = data[currentId].memberIdx
                    val nextId4 = data[last].memberIdx
                    Log.d("nextId", nextId.toString())
                    Log.d("nextcurrentId", currentId.toString())
                    Log.d("nextId4", nextId4.toString())
                    // 전체: 해당 memberId를 userList에서 찾고 그 위치로 스크롤, currentUserId에 저장
                    GaramgaebiApplication.sSharedPreferences
                        .edit().putInt("currentUserId", nextId)
                        .apply()

                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            viewModel.sendCurrentIdxMessage(nextId)
                            Log.d("indexwhy1", "indexwhy1")
                        }
                        withContext(Dispatchers.IO) {
                            // PATCH(NEXT때 보냈던 memberIdx를 보냄)
                            roomId?.let { it1 ->
                                GameCurrentIdxRequest(
                                    it1, nextId
                                )
                            }?.let { it2 -> viewModel.patchGameCurrentIdx(it2) }
                            Log.d("indexwhy1-1", "indexwhy1-1")
                        }
                    }
                } else {
                    val nextId = data[currentId + 1].memberIdx
                    val nextId3 = data[currentId].memberIdx
                    Log.d("nextId2", nextId.toString())
                    Log.d("nextcurrentIdelse", currentId.toString())
                    Log.d("nextId3", nextId3.toString())
                    // 전체: 해당 memberId를 userList에서 찾고 그 위치로 스크롤, currentUserId에 저장
                    GaramgaebiApplication.sSharedPreferences
                        .edit().putInt("currentUserId", nextId)
                        .apply()

                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            viewModel.sendCurrentIdxMessage(nextId)
                            Log.d("indexwhy2", "indexwhy2")
                        }
                        withContext(Dispatchers.IO) {
                            // PATCH(NEXT때 보냈던 memberIdx를 보냄)
                            roomId?.let { it1 ->
                                GameCurrentIdxRequest(
                                    it1, nextId
                                )
                            }?.let { it2 -> viewModel.patchGameCurrentIdx(it2) }
                            Log.d("indexwhy2-1", "indexwhy2-1")
                        }
                    }

                }
            } else {
                if (data[currentId].memberIdx == data[last].memberIdx) {
                    currentId = 0
                    val nextId = data[currentId].memberIdx
                    val nextId4 = data[last].memberIdx
                    Log.d("nextId", nextId.toString())
                    Log.d("nextcurrentId", currentId.toString())
                    Log.d("nextId4", nextId4.toString())
                    // 전체: 해당 memberId를 userList에서 찾고 그 위치로 스크롤, currentUserId에 저장
                    GaramgaebiApplication.sSharedPreferences
                        .edit().putInt("currentUserId", nextId)
                        .apply()

                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            viewModel.sendCurrentIdxMessage(nextId)
                            Log.d("indexwhy1", "indexwhy1")
                        }
                        withContext(Dispatchers.IO) {
                            // PATCH(NEXT때 보냈던 memberIdx를 보냄)
                            roomId?.let { it1 ->
                                GameCurrentIdxRequest(
                                    it1, nextId
                                )
                            }?.let { it2 -> viewModel.patchGameCurrentIdx(it2) }
                            Log.d("indexwhy1-1", "indexwhy1-1")
                        }
                    }
                } else {
                    val nextId = data[currentId + 1].memberIdx
                    val nextId3 = data[currentId].memberIdx
                    Log.d("nextId2", nextId.toString())
                    Log.d("nextcurrentIdelse", currentId.toString())
                    Log.d("nextId3", nextId3.toString())
                    // 전체: 해당 memberId를 userList에서 찾고 그 위치로 스크롤, currentUserId에 저장
                    GaramgaebiApplication.sSharedPreferences
                        .edit().putInt("currentUserId", nextId)
                        .apply()

                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            viewModel.sendCurrentIdxMessage(nextId)
                            Log.d("indexwhy2", "indexwhy2")
                        }
                        withContext(Dispatchers.IO) {
                            // PATCH(NEXT때 보냈던 memberIdx를 보냄)
                            roomId?.let { it1 ->
                                GameCurrentIdxRequest(
                                    it1, nextId
                                )
                            }?.let { it2 -> viewModel.patchGameCurrentIdx(it2) }
                            Log.d("indexwhy2-1", "indexwhy2-1")
                        }
                    }

                }
            }
        }
        //
        viewModel.patchMessage.observe(viewLifecycleOwner, Observer { it ->
            if (it.type != "EXIT") {
                val data = getPref("data")
                Log.d("deletenext", "deletenext")
                Log.d("index", index.toString())
                //뷰페이저 하나만 넘어가는 거 해결

                val currentId2 = data.indexOf(data.find { gameMemberGetResult ->
                    gameMemberGetResult.memberIdx.toString() == it.message
                })
                Log.d("currentId2", currentId2.toString())
                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("currentId2", currentId2)
                    .apply()
                //val currentId3 = currentId2 + 1
                //Log.d("currentId3", currentId3.toString())
                Log.d("message", it.message)
                Log.d("whtwht", "tt")

                //index++
                //val index1 = index++
                if (index == 30) {
                    index = 0
                    index++
                } else {
                    index++
                }
                val img = getImg("img")
                val networkingGameCardVPAdapter =
                    NetworkingGameCardVPAdapter(img, index)
                binding.activityGameCardBackVp.adapter =
                    NetworkingGameCardVPAdapter(img, index)
                binding.activityGameCardBackVp.orientation =
                    ViewPager2.ORIENTATION_HORIZONTAL
                var tab = binding.activityGameCardBackVp.currentItem
                tab++
                Log.d("tab", tab.toString())
                binding.activityGameCardBackVp.setCurrentItem(tab, true)
                //binding.activityGameCardBackVp.setCurrentItemWithDuration(tab, 400)
                networkingGameCardVPAdapter.notifyDataSetChanged()
                //Log.d("memberIdx3", data[currentId3].memberIdx.toString())

                // 다음 버튼 순서인 사람 뷰에서만 보이게 처리
                if (currentId2 != -1) {
                    Log.d("currentrealmemberId", memberIdx.toString())
                    Log.d(
                        "currentId2memberId",
                        data[currentId2].memberIdx.toString()
                    )
                    if (memberIdx == data[currentId2].memberIdx) {
                        Log.d("currentIdwhy", "why")
                        binding.activityGamePlaceCardNextBtn.visibility =
                            VISIBLE
                    } else {
                        binding.activityGamePlaceCardNextBtn.visibility = GONE
                    }

                }

                // Log.d("member", data[currentId2 + 1].memberIdx.toString())
                Log.d("currentId2-1", currentId2.toString())
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
            }
        })



        //퇴장
        //viewModel.getMember.observe(viewLifecycleOwner, Observer { data ->
            viewModel.deleteMessage.observe(viewLifecycleOwner, Observer {
                val data = getPref("data")
                Log.d("deleteconnect", "deleteconnect")
                //viewModel.deleteMember.observe(viewLifecycleOwner, Observer {game->
                // viewModel.getMember.observe(viewLifecycleOwner, Observer { data ->
                Log.d("deletedata", data.toString())
                // 퇴장멤버ID == currentID -> 스크롤
                val deleteCurrent = it.message
                Log.d("deleteDeleteCurrent", deleteCurrent)
                val memberList = getPref("data")
                Log.d("deletememberList", memberList.toString())
                Log.d(
                    "deletecurrentId5",
                    GaramgaebiApplication.sSharedPreferences.getInt("currentUserId", 0)
                        .toString()
                )

                //val deleteCurrentId = GaramgaebiApplication.sSharedPreferences.getInt("deleteNext", 0)
                //Log.d("deleteDeleteCurrent2", GaramgaebiApplication.sSharedPreferences.getInt("deleteNext", 0).toString())
                //Log.d("deleteDeleteCurrent3", memberList[deleteCurrentId].memberIdx.toString())
                if (deleteCurrent == GaramgaebiApplication.sSharedPreferences.getInt("currentUserId", 0).toString()
                ) {
                    //
                    var currentId4 =
                        data.indexOf(memberList.find { gameMemberGetResult ->
                            gameMemberGetResult.memberIdx == GaramgaebiApplication.sSharedPreferences.getInt(
                                "deleteNext",
                                0
                            )
                        })
                    Log.d("deletecurrentId4", currentId4.toString())
                    val lastIndex = data.size - 1
                    Log.d("deleteLastIndex", lastIndex.toString())
                    if (data[currentId4].memberIdx == data[lastIndex].memberIdx) {
                        Log.d("delete1", "delete1")
                        currentId4 = 0
                        Log.d("delete1currentId", currentId4.toString())
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
                            Log.d("currentIdwhy", "why")
                            binding.activityGamePlaceCardNextBtn.visibility =
                                VISIBLE
                        } else {
                            binding.activityGamePlaceCardNextBtn.visibility = GONE
                        }

                    } else {
                        Log.d("delete1currentId", currentId4.toString())
                        Log.d("delete2", "delete2")
                        val networkingGameProfile =
                            NetworkingGameProfileAdapter(
                                data as ArrayList<GameMemberGetResult>,
                                currentId4 + 1
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
                                currentId4 + 1
                            )
                            networkingGameProfile.notifyDataSetChanged()
                        }

                        if (memberIdx == data[currentId4].memberIdx) {
                            Log.d("currentIdwhy", "why")
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
                    Log.d("deletecurrentId5", currentId5.toString())
                    Log.d("delete3", "delete3")
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
        //})
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
                    //(activity as ContainerActivity).openFragmentOnFrameLayout(7)
                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) {
                            deleteMember()
                        }
                        withContext(Dispatchers.IO) {
                            Log.d("deletesend", "deletesend")
                            viewModel.sendDeleteMessage()
                        }
                        withContext(Dispatchers.IO){
                            Log.d("deleteget", "deleteget")
                            viewModel.getGameMember()
                        }
                        withContext(Dispatchers.IO) {
                            Log.d("deletedisconnect", "deletedisconnect")
                            viewModel.disconnectStomp()
                            requireActivity().supportFragmentManager.popBackStack()
                        }

                    }
                    //(activity as ContainerActivity).supportFragmentManager.beginTransaction().remove(NetworkingGamePlaceFragment()).commit()
                }


            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(requireActivity(), callback!!)

    }

    override fun onDetach() {
        super.onDetach()
        //callback?.handleOnBackPressed()
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
        Log.d("deletememberlist", memberList.toString())
        Log.d("deletememberIdx", memberIdx.toString())
        Log.d("deleteCurrentUserId", GaramgaebiApplication.sSharedPreferences.getInt("currentUserId", 0).toString())
        if(memberIdx != GaramgaebiApplication.sSharedPreferences.getInt("currentUserId", 0)){
            Log.d("deletewhy1", "deletewhy1")

            var currentId = memberList.indexOf(memberList.find { gameMemberGetResult ->
                gameMemberGetResult.memberIdx == memberIdx
            })
            Log.d("deletewhy1CurrentId", currentId.toString())
            val lastIndex = memberList.lastIndex
            Log.d("deleteLastIndex", lastIndex.toString())
            if(memberList[currentId].memberIdx == memberList[lastIndex].memberIdx){
                Log.d("deletewhy4", "deletewhy4")
                roomId?.let { GameMemberDeleteRequest(it, -1) }
                    ?.let { viewModel.postDeleteMember(it) }
                currentId = 0
                val nextId = memberList[currentId].memberIdx
                Log.d("deletenextId1", nextId.toString())
                /*GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("deleteNext", nextId)
                    .apply()*/
            }
            else{
                Log.d("deletewhy5", "deletewhy5")
                roomId?.let { GameMemberDeleteRequest(it, -1) }
                    ?.let { viewModel.postDeleteMember(it) }
                val nextId = memberList[currentId + 1].memberIdx
                Log.d("deletenextId2", nextId.toString())
                /*GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("deleteNext", nextId)
                    .apply()*/
            }
        }
        // 퇴장유저가 자신 차례인 경우(self.memberID == currentUserID)

        // nextMemberIdx에 userList에서 자신의 memberId를 찾고 그 다음 사람을 보냄
        if(memberIdx == GaramgaebiApplication.sSharedPreferences.getInt("currentUserId", 0)){
            Log.d("deletewhy2", "deletewhy2")
            //혼자 참여라면 -1주고 퇴장
            if(memberList.size == 1){
                Log.d("deletewhy3", "deletewhy3")
                roomId?.let { GameMemberDeleteRequest(it, -1) }
                    ?.let { viewModel.postDeleteMember(it) }

            }
            var currentId = memberList.indexOf(memberList.find { gameMemberGetResult ->
                gameMemberGetResult.memberIdx == memberIdx
            })
            Log.d("deleteCurrentId", currentId.toString())
            val lastIndex = memberList.lastIndex
            Log.d("deleteLastIndex", lastIndex.toString())
            if(memberList[currentId].memberIdx == memberList[lastIndex].memberIdx){
                Log.d("deletewhy4", "deletewhy4")
                currentId = 0
                val nextId = memberList[currentId].memberIdx
                Log.d("deletenextId3", nextId.toString())
                GaramgaebiApplication.sSharedPreferences
                    .edit().putInt("deleteNext", nextId)
                    .apply()

                roomId?.let {
                    GameMemberDeleteRequest(
                        it, nextId)
                }?.let { viewModel.postDeleteMember(it) }
            }
            else{
                Log.d("deletewhy5", "deletewhy5")
                val nextId = memberList[currentId + 1].memberIdx
                Log.d("deletenextId4", nextId.toString())
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
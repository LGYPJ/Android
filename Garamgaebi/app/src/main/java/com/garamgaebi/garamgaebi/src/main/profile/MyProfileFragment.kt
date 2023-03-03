package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.CareerMyRVAdapter
import com.garamgaebi.garamgaebi.adapter.EduMyRVAdapter
import com.garamgaebi.garamgaebi.adapter.SnsMyRVAdapter
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.loganVer
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.databinding.FragmentMyprofileBinding
import com.garamgaebi.garamgaebi.model.ProfileDataResponse
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.ProfileViewModel
import kotlinx.coroutines.*


@Suppress("UNREACHABLE_CODE")
class MyProfileFragment :
    BaseBindingFragment<FragmentMyprofileBinding>(R.layout.fragment_myprofile) {
    var containerActivity: ContainerActivity? = null

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    fun loganVersion(){
        var anim_small: Animation? = null
        var anim_center: Animation? = null
        anim_small = AnimationUtils.loadAnimation(requireActivity(), R.anim.scale_big)
        anim_center = AnimationUtils.loadAnimation(requireActivity(), R.anim.translate_center)

        var anim_up = AnimationUtils.loadAnimation(requireActivity(), R.anim.move_bottom_up)
        var anim_down = AnimationUtils.loadAnimation(requireActivity(), R.anim.move_bottom_down)
        var anim_down2 = AnimationUtils.loadAnimation(requireActivity(), R.anim.move_bottom_down2)
        var anim_down3 = AnimationUtils.loadAnimation(requireActivity(), R.anim.move_bottom_down3)

        var anim_in = AnimationUtils.loadAnimation(requireActivity(),R.anim.fab_open)
        var anim_out = AnimationUtils.loadAnimation(requireActivity(),R.anim.fab_close)

        CoroutineScope(Dispatchers.Main).launch {
//            binding.conSky.startAnimation(anim_down2)
//            binding.profileInfo.startAnimation(anim_down)
//            binding.activityMyProfileIvProfile.startAnimation(anim_down3)
//
//            binding.activityMyProfileTvUsername.startAnimation(anim_in)
//            binding.activityMyProfileTvEmail.startAnimation(anim_in)
//            binding.activityMyProfileTvIntro.startAnimation(anim_in)
//            binding.activityMyProfileTvSchool.startAnimation(anim_in)
//            binding.activityMyProfileBtnEditProfile.startAnimation(anim_in)

        }

    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.lifecycleOwner = this
        binding.setVariable(BR.profileViewModel,viewModel)
        viewModel.getProfileInfo(myMemberIdx)


        val dividerItemDecoration = DividerItemDecoration(
            binding.activityMyProfileRVSns.context,
            LinearLayoutManager(requireContext()).orientation
        )
        with(binding) {
            activityMyProfileRVSns.addItemDecoration(dividerItemDecoration)
            activityMyProfileRVCareer.addItemDecoration(dividerItemDecoration)
            activityMyProfileRVEdu.addItemDecoration(dividerItemDecoration)
            CoroutineScope(Dispatchers.IO).launch {
                setDataView()
            }
            activityMyProfileBtnSnsAdd.setOnClickListener {
                goAddSNSFragment()
            }

            activityMyProfileBtnCareerAdd.setOnClickListener {
                goAddCareerFragment()
            }

            activityMyProfileBtnEduAdd.setOnClickListener {
                goAddEduFragment()
            }

            activityMyProfileIvCs.setOnClickListener {
                goServiceCenterFragment()
            }

            activityMyProfileBtnEditProfile.setOnClickListener {
                goEditFragment()
            }

            activityMyProfileTvEmail.setOnClickListener {
                val clipboard = requireActivity()?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // 새로운 ClipData 객체로 데이터 복사하기
                val clip: ClipData =
                    ClipData.newPlainText("email_address", activityMyProfileTvEmail.text)

                // 새로운 클립 객체를 클립보드에 배치합니다.
                clipboard.setPrimaryClip(clip)

                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)
                Toast.makeText(binding.root.context, "복사 완료", Toast.LENGTH_SHORT).show()
            }

            refreshLayout.setOnRefreshListener {
                    viewModel.getProfileInfo(myMemberIdx)
                    binding.refreshLayout.isRefreshing = false
            }
        }




    }

    //내 프로필 편집 화면으로 이동
    private fun goEditFragment() {
        val intent = Intent(activity, ContainerActivity::class.java)
        intent.putExtra("edit", true) //데이터 넣기
        startActivity(intent)
    }

    //고객센터 화면으로 이동
    private fun goServiceCenterFragment() {
        val intent = Intent(activity, ContainerActivity::class.java)
        intent.putExtra("servicecenter", true) //데이터 넣기
        startActivity(intent)
    }

    //sns 추가 버튼
    private fun goAddSNSFragment() {
        val intent = Intent(activity, ContainerActivity::class.java)
        intent.putExtra("sns", true) //데이터 넣기
        startActivity(intent)
    }

    //career 추가 버튼
    private fun goAddCareerFragment() {
        val intent = Intent(activity, ContainerActivity::class.java)
        intent.putExtra("career", true) //데이터 넣기
        startActivity(intent)
    }

    //edu 추가 버튼
    private fun goAddEduFragment() {
        val intent = Intent(activity, ContainerActivity::class.java)
        intent.putExtra("edu", true) //데이터 넣기
        startActivity(intent)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onResume() {
        super.onResume()

        CoroutineScope(Dispatchers.IO).launch {
            updateData()
            if(loganVer)
                loganVersion()
        }
    }

    private suspend fun setDataView() {
        withContext(Dispatchers.Main) {
            with(viewModel) {
                var dividerItemDecoration = DividerItemDecoration(
                    binding.activityMyProfileRVSns.context,
                    LinearLayoutManager(requireContext()).orientation
                )

                //getProfileInfo(myMemberIdx)
                profileInfo.observe(viewLifecycleOwner) {
                    binding.profileViewModel = viewModel
                    val result = it as ProfileDataResponse
                    if (result == null) {

                    } else {

                        with(binding) {

                            GaramgaebiApplication.sSharedPreferences
                                .edit().putString("myNickName", result.result.nickName)
                                .putString("myBelong", result.result.belong)
                                .putString("myEmail", result.result.profileEmail)
                                .putString("myIntro", result.result.content)
                                .putString("myImage", result.result.profileUrl)
                                .apply()

                            activityMyProfileTvUsername.text = result.result.nickName
                            activityMyProfileTvEmail.text = result.result.profileEmail
                            activityMyProfileTvSchool.text = result.result.belong
                            activityMyProfileTvIntro.text = result.result.content

                            if (result.result.content == "" || result.result.content == null) {
                                activityMyProfileTvIntro.visibility = View.GONE
                            } else {
                                activityMyProfileTvIntro.visibility = View.VISIBLE
                            }
                            if (result.result.belong == "" || result.result.belong == null) {
                                activityMyProfileTvSchool.visibility = View.GONE
                            } else {
                                activityMyProfileTvSchool.visibility = View.VISIBLE
                            }

                            if (result.result.profileUrl != null) {
                                CoroutineScope(Dispatchers.Main).launch {
                                    val bitmap = withContext(Dispatchers.IO) {
                                        GaramgaebiFunction.ImageLoader.loadImage(result.result.profileUrl)
                                    }
                                    binding.activityMyProfileIvProfile.setImageBitmap(bitmap)
                                    Log.d("image_url", result.result.profileUrl)
                                }
                                activityMyProfileIvProfile.clipToOutline = true
                            }

                        }
                    }
                }
                //SNS 정보 어댑터 연결
                //getSNSInfo(myMemberIdx)
                snsInfoArray.observe(viewLifecycleOwner, Observer { it ->
                    if (it == null) {

                    } else {
                        val snsAdapter =
                            activity?.let { it1 -> SnsMyRVAdapter(it, it1.applicationContext) }
                        binding.activityMyProfileRVSns.apply {
                            adapter = snsAdapter

                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        }
                        snsAdapter?.setOnItemClickListener(object :
                            SnsMyRVAdapter.OnItemClickListener {
                            override fun onClick(position: Int) {
                            }
                        })
                    }
                })

                //경력 정보 어댑터 연결
               //getCareerInfo(myMemberIdx)
                careerInfoArray.observe(viewLifecycleOwner, Observer { it ->
                    if (it == null) {

                    } else {
                        val careerAdapter = activity?.let { it1 ->
                            CareerMyRVAdapter(
                                it,
                                it1.applicationContext
                            )
                        }
                        dividerItemDecoration = DividerItemDecoration(
                            binding.activityMyProfileRVCareer.context,
                            LinearLayoutManager(requireContext()).orientation
                        )
                        binding.activityMyProfileRVCareer.apply {
                            adapter = careerAdapter
                            Log.d("career_adapter_list_size", it.size.toString())
                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        }
                        careerAdapter?.setOnItemClickListener(object :
                            CareerMyRVAdapter.OnItemClickListener {
                            override fun onClick(position: Int) {
                            }
                        })
                    }
                })
                //교육 정보 어댑터 연결
                //getEducationInfo(myMemberIdx)
                educationInfoArray.observe(viewLifecycleOwner) {
                    if (it == null) {

                    } else {
                        val eduAdapter =
                            activity?.let { it1 -> EduMyRVAdapter(it, it1.applicationContext) }
                        dividerItemDecoration = DividerItemDecoration(
                            binding.activityMyProfileRVEdu.context,
                            LinearLayoutManager(requireContext()).orientation
                        )
                        binding.activityMyProfileRVEdu.apply {
                            adapter = eduAdapter
                            layoutManager =
                                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                        }
                        eduAdapter?.setOnItemClickListener(object :
                            EduMyRVAdapter.OnItemClickListener {
                            override fun onClick(position: Int) {
                            }
                        })
                    }
                }
            }
        }
    }


    private fun updateData() {
        with(viewModel) {
            getProfileInfo(myMemberIdx)
            getSNSInfo(myMemberIdx)
            getCareerInfo(myMemberIdx)
            getEducationInfo(myMemberIdx)
        }
    }
}



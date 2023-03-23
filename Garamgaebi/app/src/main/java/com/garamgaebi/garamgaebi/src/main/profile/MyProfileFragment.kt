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
import androidx.appcompat.app.AlertDialog
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
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.getCareer
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.getEdu
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.getProfile
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.getSNS
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.common.NetworkErrorDialog
import com.garamgaebi.garamgaebi.databinding.FragmentMyprofileBinding
import com.garamgaebi.garamgaebi.model.ProfileDataResponse
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.ProfileViewModel
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


@Suppress("UNREACHABLE_CODE")
class MyProfileFragment :
    BaseBindingFragment<FragmentMyprofileBinding>(R.layout.fragment_myprofile) {
    var containerActivity: ContainerActivity? = null

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }



    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
        binding.setVariable(BR.profileViewModel, viewModel)


            val dividerItemDecoration = DividerItemDecoration(
                binding.fragmentMyProfileRVSns.context,
                LinearLayoutManager(requireContext()).orientation
            )
            with(binding) {
                fragmentMyProfileRVSns.addItemDecoration(dividerItemDecoration)
                fragmentMyProfileRVCareer.addItemDecoration(dividerItemDecoration)
                fragmentMyProfileRVEdu.addItemDecoration(dividerItemDecoration)

                fragmentMyProfileBtnSnsAdd.setOnClickListener {
                    goAddSNSFragment()
                }

                fragmentMyProfileBtnCareerAdd.setOnClickListener {
                    goAddCareerFragment()
                }

                fragmentMyProfileBtnEduAdd.setOnClickListener {
                    goAddEduFragment()
                }

                fragmentMyProfileIvCs.setOnClickListener {
                    goServiceCenterFragment()
                }

                fragmentMyProfileBtnEditProfile.setOnClickListener {
                    goEditFragment()
                }

                fragmentMyProfileTvEmail.setOnClickListener {
                    val clipboard =
                        requireActivity()?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                    // 새로운 ClipData 객체로 데이터 복사하기
                    val clip: ClipData =
                        ClipData.newPlainText("email_address", fragmentMyProfileTvEmail.text)

                    // 새로운 클립 객체를 클립보드에 배치합니다.
                    clipboard.setPrimaryClip(clip)

                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)
                        Toast.makeText(binding.root.context, "복사 완료", Toast.LENGTH_SHORT).show()
                }

                disposables
                    .add(
                        binding
                            .networkErrorIv
                            .clicks()
                            .throttleFirst(1000, TimeUnit.MILLISECONDS)
                            .subscribe({
                                if(checkNetwork(requireContext())) {
                                    viewModel.getProfileInfo(myMemberIdx)

                                    fragmentMyProfileClContainer.visibility = View.VISIBLE
                                    networkErrorContainer.visibility = View.GONE
                                }else {
                                    fragmentMyProfileClContainer.visibility = View.GONE
                                    networkErrorContainer.visibility = View.VISIBLE
                                }
                                //(activity as ContainerActivity).onBackPressed()
                            }, { it.printStackTrace() })
                    )
            }
        //네트워크 부분
        if(checkNetwork(requireContext())) {
            CoroutineScope(Dispatchers.IO).launch {
                setDataView()
            }
            with(binding){
                fragmentMyProfileClContainer.visibility = View.VISIBLE
                networkErrorContainer.visibility = View.GONE
            }
        }else{
                //네트워크 실패시 실패 레이아웃을 작동
                with(binding){
                    fragmentMyProfileClContainer.visibility = View.GONE
                    networkErrorContainer.visibility = View.VISIBLE
                }
        }

        binding.refreshLayout.setOnRefreshListener {
            if(checkNetwork(requireContext())) {
                viewModel.getProfileInfo(myMemberIdx)
                with(binding){
                    fragmentMyProfileClContainer.visibility = View.VISIBLE
                    networkErrorContainer.visibility = View.GONE
                }
            }else{
                with(binding){
                    fragmentMyProfileClContainer.visibility = View.GONE
                    networkErrorContainer.visibility = View.VISIBLE
                }
            }
            binding.refreshLayout.isRefreshing = false
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

        }
    }

    private suspend fun setDataView() {
        withContext(Dispatchers.Main) {
            with(viewModel) {
                var dividerItemDecoration = DividerItemDecoration(
                    binding.fragmentMyProfileRVSns.context,
                    LinearLayoutManager(requireContext()).orientation
                )

                //getProfileInfo(myMemberIdx)
                profileInfo.observe(viewLifecycleOwner) {
                    binding.profileViewModel = viewModel
                    val result = it as ProfileDataResponse
                    if (result == null || result.result == null) {
                        with(binding){
                            fragmentMyProfileClContainer.visibility = View.GONE
                            networkErrorContainer.visibility = View.VISIBLE
                        }
                    } else {

                        with(binding) {
                            val putData = runBlocking {
                                with(result.result){
                                    fragmentMyProfileClContainer.visibility = View.VISIBLE
                                    networkErrorContainer.visibility = View.GONE
                                    GaramgaebiApplication().saveStringToDataStore("myNickName",nickName)
                                    GaramgaebiApplication().saveStringToDataStore("myBelong",belong)
                                    GaramgaebiApplication().saveStringToDataStore("myEmail",profileEmail)
                                    GaramgaebiApplication().saveStringToDataStore("myIntro",content)
                                    GaramgaebiApplication().saveStringToDataStore("myImage",profileUrl)
                                    Log.d("profile_info", result.result.toString())
                                }
                            }

                            fragmentMyProfileTvUsername.text = result.result.nickName
                            fragmentMyProfileTvEmail.text = result.result.profileEmail
                            fragmentMyProfileTvSchool.text = result.result.belong
                            fragmentMyProfileTvIntro.text = result.result.content

                            if (result.result.content == "" || result.result.content == null) {
                                fragmentMyProfileTvIntro.visibility = View.GONE
                            } else {
                                fragmentMyProfileTvIntro.visibility = View.VISIBLE
                            }
                            if (result.result.belong == "" || result.result.belong == null) {
                                fragmentMyProfileTvSchool.visibility = View.GONE
                            } else {
                                fragmentMyProfileTvSchool.visibility = View.VISIBLE
                            }

                            if (result.result.profileUrl != null) {
                                CoroutineScope(Dispatchers.Main).launch {
                                    val bitmap = withContext(Dispatchers.IO) {
                                        GaramgaebiFunction.ImageLoader.loadImage(result.result.profileUrl)
                                    }
                                    binding.fragmentMyProfileIvProfile.setImageBitmap(bitmap)
                                    Log.d("image_url", result.result.profileUrl)
                                }
                                fragmentMyProfileIvProfile.clipToOutline = true
                            }
                        }
                    }
                }
                //SNS 정보 어댑터 연결
                //getSNSInfo(myMemberIdx)
                snsInfoArray.observe(viewLifecycleOwner, Observer { it ->
                    if (it == null) {
                        binding.fragmentMyProfileClContainer.visibility = View.GONE
                        binding.networkErrorContainer.visibility = View.VISIBLE
                    } else {
                        binding.fragmentMyProfileClContainer.visibility = View.VISIBLE
                        binding.networkErrorContainer.visibility = View.GONE
                        val snsAdapter =
                            activity?.let { it1 -> SnsMyRVAdapter(it, it1.applicationContext) }
                        binding.fragmentMyProfileRVSns.apply {
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
                        binding.fragmentMyProfileClContainer.visibility = View.GONE
                        binding.networkErrorContainer.visibility = View.VISIBLE
                    } else {
                        binding.fragmentMyProfileClContainer.visibility = View.VISIBLE
                        binding.networkErrorContainer.visibility = View.GONE
                        val careerAdapter = activity?.let { it1 ->
                            CareerMyRVAdapter(
                                it,
                                it1.applicationContext
                            )
                        }
                        dividerItemDecoration = DividerItemDecoration(
                            binding.fragmentMyProfileRVCareer.context,
                            LinearLayoutManager(requireContext()).orientation
                        )
                        binding.fragmentMyProfileRVCareer.apply {
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
                        binding.fragmentMyProfileClContainer.visibility = View.GONE
                        binding.networkErrorContainer.visibility = View.VISIBLE
                    } else {
                        binding.fragmentMyProfileClContainer.visibility = View.VISIBLE
                        binding.networkErrorContainer.visibility = View.GONE
                        val eduAdapter =
                            activity?.let { it1 -> EduMyRVAdapter(it, it1.applicationContext) }
                        dividerItemDecoration = DividerItemDecoration(
                            binding.fragmentMyProfileRVEdu.context,
                            LinearLayoutManager(requireContext()).orientation
                        )
                        binding.fragmentMyProfileRVEdu.apply {
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
        if(checkNetwork(requireContext())) {
            with(viewModel) {
                if (getProfile) {
                    getProfileInfo(myMemberIdx)
                    getProfile = false
                }
                if (getSNS) {
                    getSNSInfo(myMemberIdx)
                    getSNS = false
                }
                if (getCareer) {
                    getCareerInfo(myMemberIdx)
                    getCareer = false
                }
                if (getEdu) {
                    getEducationInfo(myMemberIdx)
                    getEdu = false
                } else {

                }
                binding.fragmentMyProfileClContainer.visibility = View.VISIBLE
                binding.networkErrorContainer.visibility = View.GONE
                Log.d("network", "profile")
            }
        }else {
            //check
            binding.fragmentMyProfileClContainer.visibility = View.GONE
            binding.networkErrorContainer.visibility = View.VISIBLE
        }
    }
}



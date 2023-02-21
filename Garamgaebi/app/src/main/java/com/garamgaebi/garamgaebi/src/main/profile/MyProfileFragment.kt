package com.garamgaebi.garamgaebi.src.main.profile

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
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

    lateinit var viewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        binding.lifecycleOwner = this

        binding.setVariable(BR.profileViewModel,viewModel)
        val dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVSns.context, LinearLayoutManager(requireContext()).orientation)

//        with(viewModel) {
//            getProfileInfo(myMemberIdx)
//            profileInfo.observe(viewLifecycleOwner) {
//                binding.profileViewModel = viewModel
//                val result = it as ProfileDataResponse
//                GaramgaebiApplication.sSharedPreferences
//                    .edit().putString("nickname", result.result.nickName)
//                    .apply()
//                if (result == null) {
//
//                } else {
//
//                    with(binding) {
//
//                        Log.d("image_before", result.result.profileUrl)
//
//                        if(result.result.profileUrl != null) {
//                            CoroutineScope(Dispatchers.Main).launch {
//                                val bitmap = withContext(Dispatchers.IO) {
//                                    GaramgaebiFunction.ImageLoader.loadImage(result.result.profileUrl)
//                                }
//                                binding.activityMyProfileIvProfile.setImageBitmap(bitmap)
//                            }
//                            activityMyProfileIvProfile.clipToOutline = true
//                        }
//
//                        GaramgaebiApplication.sSharedPreferences
//                            .edit().putString("myNickName", result.result.nickName)
//                            .putString("myBelong", result.result.belong)
//                            .putString("myEmail", result.result.profileEmail)
//                            .putString("myIntro", result.result.content)
//                            .putString("myImage", result.result.profileUrl)
//                            .apply()
//                        //Log.d("myImage", result.result.profileUrl + "h")
//                        activityMyProfileTvUsername.text = result.result.nickName
//                        activityMyProfileTvEmail.text = result.result.profileEmail
//                        activityMyProfileTvSchool.text = result.result.belong
//                        activityMyProfileTvIntro.text = result.result.content
//
//                        if (result.result.content == "" || result.result.content == null) {
//                            activityMyProfileTvIntro.visibility = View.GONE
//                        } else {
//                            activityMyProfileTvIntro.visibility = View.VISIBLE
//                        }
//                        if (result.result.belong == "" || result.result.belong == null) {
//                            activityMyProfileTvSchool.visibility = View.GONE
//                        } else {
//                            activityMyProfileTvSchool.visibility = View.VISIBLE
//                        }
//
//                    }
//
//                }
//            }
       // }

        with(binding){
            activityMyProfileRVSns.addItemDecoration(dividerItemDecoration)
            activityMyProfileRVCareer.addItemDecoration(dividerItemDecoration)
            activityMyProfileRVEdu.addItemDecoration(dividerItemDecoration)
            activityMyProfileBtnSnsAdd.setOnClickListener{
                goAddSNSFragment()
            }

            activityMyProfileBtnCareerAdd.setOnClickListener{
                goAddCareerFragment()
            }

            activityMyProfileBtnEduAdd.setOnClickListener{
                goAddEduFragment()
            }

            activityMyProfileIvCs.setOnClickListener{
                goServiceCenterFragment()
            }

            activityMyProfileBtnEditProfile.setOnClickListener{
                goEditFragment()
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
    private fun goServiceCenterFragment(){
        val intent = Intent(activity,ContainerActivity::class.java)
        intent.putExtra("servicecenter",true) //데이터 넣기
        startActivity(intent)
    }

    //sns 추가 버튼
    private fun goAddSNSFragment(){
        val intent = Intent(activity,ContainerActivity::class.java)
        intent.putExtra("sns",true) //데이터 넣기
        startActivity(intent)
    }

    //career 추가 버튼
    private fun goAddCareerFragment(){
        val intent = Intent(activity,ContainerActivity::class.java)
        intent.putExtra("career",true) //데이터 넣기
        startActivity(intent)
    }

    //edu 추가 버튼
    private fun goAddEduFragment(){
        val intent = Intent(activity,ContainerActivity::class.java)
        intent.putExtra("edu",true) //데이터 넣기
        startActivity(intent)
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onResume() {
        super.onResume()
        GlobalScope.launch {
            val x = setDataView()
            val y = updateData()
        }
    }
    private suspend fun setDataView():Int {
        val value: Int = withContext(Dispatchers.Main) {
            val total = 1
            with(viewModel) {
                var dividerItemDecoration = DividerItemDecoration(
                    binding.activityMyProfileRVSns.context,
                    LinearLayoutManager(requireContext()).orientation
                )
                getProfileInfo(myMemberIdx)
                profileInfo.observe(viewLifecycleOwner) {
                    binding.profileViewModel = viewModel
                    val result = it as ProfileDataResponse
                    GaramgaebiApplication.sSharedPreferences
                        .edit().putString("nickname", result.result.nickName)
                        .apply()
                    if (result == null) {

                    } else {

                        with(binding) {

                            Log.d("image_beforeㅍㅍㅍ", result.result.profileUrl)

                            GaramgaebiApplication.sSharedPreferences
                                .edit().putString("myNickName", result.result.nickName)
                                .putString("myBelong", result.result.belong)
                                .putString("myEmail", result.result.profileEmail)
                                .putString("myIntro", result.result.content)
                                .putString("myImage", result.result.profileUrl)
                                .apply()
                            //Log.d("myImage", result.result.profileUrl + "h")
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
                            var loop = false
                            if (result.result.profileUrl != null) {
                                CoroutineScope(Dispatchers.Main).launch {
                                    val bitmap = withContext(Dispatchers.IO) {
                                        GaramgaebiFunction.ImageLoader.loadImage(result.result.profileUrl)
                                    }
                                    binding.activityMyProfileIvProfile.setImageBitmap(bitmap)
                                    Log.d("image_url", result.result.profileUrl)
                                    loop = true
                                }
                                activityMyProfileIvProfile.clipToOutline = true
                            }

                        }
                    }
                    }
                //SNS 정보 어댑터 연결
                getSNSInfo(myMemberIdx)
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
                getCareerInfo(myMemberIdx)
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
                getEducationInfo(myMemberIdx)
                educationInfoArray.observe(viewLifecycleOwner) {
                    if(it == null) {

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
            total
        }
        return value
    }

    private suspend fun updateData():Int {
        val value: Int = withContext(Dispatchers.IO) {
            val total = 1
            with(viewModel) {
                getProfileInfo(myMemberIdx)
                getSNSInfo(myMemberIdx)
                getCareerInfo(myMemberIdx)
                getEducationInfo(myMemberIdx)
            }
            total
        }
        return value
    }
}



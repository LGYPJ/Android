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
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
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
import com.garamgaebi.garamgaebi.databinding.FragmentMyprofileBinding
import com.garamgaebi.garamgaebi.model.ProfileDataResponse
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.src.main.MainActivity
import com.garamgaebi.garamgaebi.viewModel.ProfileViewModel
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

/*
내 프로필 Fragment - MainActivity
프로필 정보 화면 출력

프로필 편집
SNS 추가, 편집
경력 추가, 편집
교육 축, 편집
고객센터(로그아웃, 탈퇴)
 */
class MyProfileFragment :
    BaseBindingFragment<FragmentMyprofileBinding>(R.layout.fragment_myprofile) {
    var containerActivity: ContainerActivity? = null

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뷰모델 연결
        binding.lifecycleOwner = this
        binding.setVariable(BR.profileViewModel, viewModel)

        //RecyclerView 간격 조절 (중복방지위한 최초실행)
            val dividerItemDecoration = DividerItemDecoration(
                binding.fragmentMyProfileRVSns.context,
                LinearLayoutManager(requireContext()).orientation
            )

            with(binding) {
                fragmentMyProfileRVSns.addItemDecoration(dividerItemDecoration)
                fragmentMyProfileRVCareer.addItemDecoration(dividerItemDecoration)
                fragmentMyProfileRVEdu.addItemDecoration(dividerItemDecoration)

                //SNS 추가 fragment 이동
                fragmentMyProfileBtnSnsAdd.setOnClickListener {
                    goAddSNSFragment()
                }
                //경력 추가 fragment 이동
                fragmentMyProfileBtnCareerAdd.setOnClickListener {
                    goAddCareerFragment()
                }
                //교육 추가 fragment 이동
                fragmentMyProfileBtnEduAdd.setOnClickListener {
                    goAddEduFragment()
                }
                //고객센터 fragment 이동
                fragmentMyProfileIvCs.setOnClickListener {
                    goServiceCenterFragment()
                }
                //프로필 편집 이동
                fragmentMyProfileBtnEditProfile.setOnClickListener {
                    goEditFragment()
                }
                //email 주소 복사
                fragmentMyProfileTvEmail.setOnClickListener {
                    val clipboard =
                        requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    // 새로운 ClipData 객체로 데이터 복사하기
                    val clip: ClipData =
                        ClipData.newPlainText("email_address", fragmentMyProfileTvEmail.text)
                    // 새로운 클립 객체를 클립보드에 배치합니다.
                    clipboard.setPrimaryClip(clip)
                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)
                        Toast.makeText(binding.root.context, "복사 완료", Toast.LENGTH_SHORT).show()
                }

                //네트워크 대응 예시
                disposables
                    .add(
                        binding
                            .networkErrorIv
                            .clicks()
                            .throttleFirst(1000, TimeUnit.MILLISECONDS)
                            .subscribe({
                                if((requireActivity() as MainActivity).networkValid.value == true) {
                                    viewModel.getProfileInfo(myMemberIdx)

                                    fragmentMyProfileClContainer.visibility = View.VISIBLE
                                    networkErrorContainer.visibility = View.GONE
                                }else {
                                    fragmentMyProfileClContainer.visibility = View.GONE
                                    networkErrorContainer.visibility = View.VISIBLE
                                }
                            }, { it.printStackTrace() })
                    )
            }
        //네트워크 부분
        if((requireActivity() as MainActivity).networkValid.value == true) {
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

        //새로고침
        binding.refreshLayout.setOnRefreshListener {
            if((requireActivity() as MainActivity).networkValid.value == true) {
                viewModel.getProfileInfo(myMemberIdx)
                viewModel.getSNSInfo(myMemberIdx)
                viewModel.getCareerInfo(myMemberIdx)
                viewModel.getEducationInfo(myMemberIdx)

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

    override fun onResume() {
        super.onResume()

        CoroutineScope(Dispatchers.Main).launch {
            //네트워크 부분
            if((requireActivity() as MainActivity).networkValid.value == true) {
                with(binding){
                    fragmentMyProfileClContainer.visibility = View.VISIBLE
                    networkErrorContainer.visibility = View.GONE
                    getProfile = true
                    getEdu = true
                    getSNS = true
                    getCareer = true
                    updateData()
                }
            }else{
                //네트워크 실패시 실패 레이아웃을 작동
                with(binding){
                    fragmentMyProfileClContainer.visibility = View.GONE
                    networkErrorContainer.visibility = View.VISIBLE
                }
            }

        }
    }

    //data UI 할당
    private suspend fun setDataView() {
        withContext(Dispatchers.Main) {
            with(viewModel) {
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
                            CoroutineScope(Dispatchers.Main).launch {
                                with(result.result) {
                                    fragmentMyProfileClContainer.visibility = View.VISIBLE
                                    networkErrorContainer.visibility = View.GONE
                                    GaramgaebiApplication().saveBooleanToDataStore(
                                        "EditImage",
                                        false
                                    )
                                    GaramgaebiApplication().saveStringToDataStore(
                                        "myNickName",
                                        nickName
                                    )
                                    GaramgaebiApplication().saveStringToDataStore(
                                        "myEmail",
                                        profileEmail
                                    )
                                    GaramgaebiApplication().saveStringToDataStore(
                                        "myBelong",
                                        belong
                                    )
                                    GaramgaebiApplication().saveStringToDataStore(
                                        "myIntro",
                                        content
                                    )
                                    GaramgaebiApplication().saveStringToDataStore(
                                        "myImage",
                                        profileUrl
                                    ).toString()

                                    fragmentMyProfileTvUsername.text = result.result.nickName
                                    fragmentMyProfileTvEmail.text = result.result.profileEmail

                                    //null 값일 때
                                    if(belong == null || belong == ""){
                                        GaramgaebiApplication().saveBooleanToDataStore("myBelongNull",true)
                                        GaramgaebiApplication().saveStringToDataStore("myBelong","")
                                        fragmentMyProfileTvSchool.visibility = View.GONE
                                    }else{
                                        fragmentMyProfileTvSchool.visibility = View.VISIBLE
                                        fragmentMyProfileTvSchool.text = result.result.belong
                                        GaramgaebiApplication().saveBooleanToDataStore("myBelongNull",false)
                                    }
                                    if(content == null ||content == "") {
                                        GaramgaebiApplication().saveStringToDataStore("myIntro","")
                                        GaramgaebiApplication().saveBooleanToDataStore("myIntroNull", true)
                                        fragmentMyProfileTvIntro.visibility = View.GONE

                                        Log.d("profile_response_content","true")
                                    }else{
                                        fragmentMyProfileTvIntro.visibility = View.VISIBLE
                                        fragmentMyProfileTvIntro.text = result.result.content
                                        GaramgaebiApplication().saveBooleanToDataStore("myIntroNull",false)
                                    }
                                    if(profileUrl == null){
                                        GaramgaebiApplication().saveBooleanToDataStore("myImageNull",true)
                                        GaramgaebiApplication().saveStringToDataStore("myImage","")
                                    }else{
                                        GaramgaebiApplication().saveBooleanToDataStore("myImageNull",false)
                                        CoroutineScope(Dispatchers.Main).launch {
                                            val bitmap = withContext(Dispatchers.IO) {
                                                GaramgaebiFunction.ImageLoader.loadImage(result.result.profileUrl)
                                            }
                                            binding.fragmentMyProfileIvProfile.setImageBitmap(bitmap)
                                            Log.d("image_url", result.result.profileUrl)
                                        }
                                        fragmentMyProfileIvProfile.clipToOutline = true                                    }
                                    Log.d("profile_info", result.result.toString())
                                }
                            }
                        }
                    }
                }

                //SNS 정보 어댑터 연결
                snsInfoArray.observe(viewLifecycleOwner) {
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
                }

                //경력 정보 어댑터 연결
                careerInfoArray.observe(viewLifecycleOwner) {
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
                }
                //교육 정보 어댑터 연결
                educationInfoArray.observe(viewLifecycleOwner) {
                    if (it == null) {
                        binding.fragmentMyProfileClContainer.visibility = View.GONE
                        binding.networkErrorContainer.visibility = View.VISIBLE
                    } else {
                        binding.fragmentMyProfileClContainer.visibility = View.VISIBLE
                        binding.networkErrorContainer.visibility = View.GONE
                        val eduAdapter =
                            activity?.let { it1 -> EduMyRVAdapter(it, it1.applicationContext) }
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
        if((requireActivity() as MainActivity).networkValid.value == true) {
            with(viewModel) {
                Log.d("update data","0")
                if (getProfile) {
                    getProfileInfo(myMemberIdx)
                    Log.d("update data","1")
                    getProfile = false
                }
                if (getSNS) {
                    getSNSInfo(myMemberIdx)
                    Log.d("update data","2")

                    getSNS = false
                }
                if (getCareer) {
                    getCareerInfo(myMemberIdx)
                    Log.d("update data","3")

                    getCareer = false
                }
                if (getEdu) {
                    getEducationInfo(myMemberIdx)
                    Log.d("update data","4")

                    getEdu = false
                }
                binding.fragmentMyProfileClContainer.visibility = View.VISIBLE
                binding.networkErrorContainer.visibility = View.GONE
            }
        }else {
            //check
            binding.fragmentMyProfileClContainer.visibility = View.GONE
            binding.networkErrorContainer.visibility = View.VISIBLE
        }
    }
}



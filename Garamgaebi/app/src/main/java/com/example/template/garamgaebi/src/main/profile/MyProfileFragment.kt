package com.example.template.garamgaebi.src.main.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.BR
import com.example.template.garamgaebi.common.BaseBindingFragment
import com.example.template.garamgaebi.databinding.FragmentMyprofileBinding
import com.example.template.garamgaebi.model.ProfileDataResponse
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.viewModel.ProfileViewModel

class MyProfileFragment :
    BaseBindingFragment<FragmentMyprofileBinding>(R.layout.fragment_myprofile) {
    private lateinit var callback: OnBackPressedCallback
    var containerActivity: ContainerActivity? = null

    override fun initViewModel() {
        super.initViewModel()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        binding.setVariable(BR.profileViewModel,viewModel)
        viewModel.getProfileInfo(1)


        viewModel.profileInfo.observe(viewLifecycleOwner, Observer {
            val result = it as ProfileDataResponse
            if(result == null) {

            } else {
                with(binding) {
                    activityMyProfileTvUsername.text = result.result.nickName
                    activityMyProfileTvEmail.text = result.result.profileEmail
                    activityMyProfileTvSchool.text = result.result.belong
                    activityMyProfileTvIntro.text = result.result.content
                }
                    if (result.result.content == null) {
                        binding.activityMyProfileTvIntro.visibility = View.VISIBLE
                    }
            }
        })

        var dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVSns.context, LinearLayoutManager(requireContext()).orientation)


        //SNS 정보 어댑터 연결
        viewModel.getSNSInfo(1)
        viewModel.snsInfoArray.observe(viewLifecycleOwner, Observer { it ->
            val snsAdapter = SnsMyRVAdapter(it)
            binding.activityMyProfileRVSns.apply {
                adapter = snsAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(dividerItemDecoration)
            }
            //발표 리사이클러뷰 클릭하면 팝업다이얼로그 나타남!
            snsAdapter.setOnItemClickListener(object : SnsMyRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    // sns 편집
                }
            } )
        })

        //경력 정보 어댑터 연결
        viewModel.getCareerInfo(1)
        viewModel.careerInfoArray.observe(viewLifecycleOwner, Observer { it ->
            val careerAdapter = CareerMyRVAdapter(it)
            dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVCareer.context, LinearLayoutManager(requireContext()).orientation)
            binding.activityMyProfileRVCareer.apply {
                adapter = careerAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(dividerItemDecoration)
            }
            //발표 리사이클러뷰 클릭하면 팝업다이얼로그 나타남!
            careerAdapter.setOnItemClickListener(object : CareerMyRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    // sns 편집
                }
            } )
        })

        //교육 정보 어댑터 연결
        viewModel.getEducationInfo(1)
        viewModel.educationInfoArray.observe(viewLifecycleOwner, Observer { it ->
            val eduAdapter = EduMyRVAdapter(it)
            dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVEdu.context, LinearLayoutManager(requireContext()).orientation)
            binding.activityMyProfileRVEdu.apply {
                adapter = eduAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(dividerItemDecoration)
            }
            //발표 리사이클러뷰 클릭하면 팝업다이얼로그 나타남!
            eduAdapter.setOnItemClickListener(object : EduMyRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    // sns 편집
                }
            } )
        })

//        //career 리스트뷰 연결
//        var careerItems: ArrayList<CareerRVItemData> = arrayListOf()
//        val careerAdapter = CareerMyRVAdapter(careerItems)
//        dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVCareer.context, LinearLayoutManager(requireContext()).orientation)
//        binding.activityMyProfileRVCareer.addItemDecoration(dividerItemDecoration)
//        binding.activityMyProfileRVCareer.adapter = careerAdapter
//
//
//        //edu 리스트뷰 연결
//        var eduItems: ArrayList<EduRVItemData> = arrayListOf()
//        val eduAdapter = EduMyRVAdapter(eduItems)
//        dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVEdu.context, LinearLayoutManager(requireContext()).orientation)
//        binding.activityMyProfileRVEdu.addItemDecoration(dividerItemDecoration)
//        binding.activityMyProfileRVEdu.adapter = eduAdapter

        binding.activityMyProfileBtnSnsAdd.setOnClickListener{
            goAddSNSFragment()
        }

        binding.activityMyProfileBtnCareerAdd.setOnClickListener{
            goAddCareerFragment()
        }

        binding.activityMyProfileBtnEduAdd.setOnClickListener{
            goAddEduFragment()
        }

        binding.activityMyProfileIvCs.setOnClickListener{
            goServiceCenterFragment()
        }

        binding.activityMyProfileIvWd.setOnClickListener{
            goWithdrawalFragment()
        }

        binding.activityMyProfileBtnEditProfile.setOnClickListener{
            goEditFragment()
        }
        //test 상대 프로필
        binding.activityMyProfileIvProfile.setOnClickListener {
            val intent = Intent(activity,ContainerActivity::class.java)
            intent.putExtra("someoneprofile",true) //데이터 넣기
            startActivity(intent)
        }
    }
    //내 프로필 편집 화면으로 이동
    fun goEditFragment() {
        val intent = Intent(activity, ContainerActivity::class.java)
        intent.putExtra("edit", true) //데이터 넣기
        startActivity(intent)
    }
    //고객센터 화면으로 이동
    fun goServiceCenterFragment(){
        val intent = Intent(activity,ContainerActivity::class.java)
        intent.putExtra("servicecenter",true) //데이터 넣기
        startActivity(intent)
    }
    //탈퇴 화면으로 이동
    fun goWithdrawalFragment(){
        val intent = Intent(activity,ContainerActivity::class.java)
        intent.putExtra("withdrawal",true) //데이터 넣기
        startActivity(intent)
    }

    //sns 추가 버튼
    fun goAddSNSFragment(){
        binding.activityMyProfileRVCareer.visibility = View.VISIBLE
        binding.activityMyProfileTvCareerDesc.visibility = View.GONE
        val intent = Intent(activity,ContainerActivity::class.java)
        intent.putExtra("sns",true) //데이터 넣기
        startActivity(intent)
    }

    //career 추가 버튼
    fun goAddCareerFragment(){
        Log.d("ff","gogo")
        val intent = Intent(activity,ContainerActivity::class.java)
        intent.putExtra("career",true) //데이터 넣기
        startActivity(intent)
    }

    //edu 추가 버튼
    fun goAddEduFragment(){
        val intent = Intent(activity,ContainerActivity::class.java)
        intent.putExtra("edu",true) //데이터 넣기
        startActivity(intent)
    }




    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        // containerActivity = context as ContainerActivity
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}



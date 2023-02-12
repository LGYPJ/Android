package com.example.template.garamgaebi.src.main.profile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.BR
import com.example.template.garamgaebi.adapter.CareerMyRVAdapter
import com.example.template.garamgaebi.adapter.EduMyRVAdapter
import com.example.template.garamgaebi.adapter.SnsMyRVAdapter
import com.example.template.garamgaebi.common.BaseBindingFragment
import com.example.template.garamgaebi.common.GaramgaebiApplication
import com.example.template.garamgaebi.databinding.FragmentMyprofileBinding
import com.example.template.garamgaebi.model.ProfileDataResponse
import com.example.template.garamgaebi.model.SNSData
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.viewModel.ProfileViewModel

class MyProfileFragment :
    BaseBindingFragment<FragmentMyprofileBinding>(R.layout.fragment_myprofile) {
    private lateinit var callback: OnBackPressedCallback
    var containerActivity: ContainerActivity? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }
    override fun initViewModel() {
        super.initViewModel()

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    lateinit var viewModel: ProfileViewModel


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        binding.setVariable(BR.profileViewModel,viewModel)
        viewModel.getProfileInfo(22)

        viewModel.profileInfo.observe(viewLifecycleOwner, Observer {
            binding.profileViewModel = viewModel

            val result = it as ProfileDataResponse
            GaramgaebiApplication.sSharedPreferences
                .edit().putString("nickname", result.result.nickName)
                .apply()

            if(result == null) {

            } else {
                with(binding) {
                    //binding.profileViewModel = viewModel
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
            }
            snsAdapter.setOnItemClickListener(object : SnsMyRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    // sns 편집
                    val editSNSAddress = it[position].address
                    val editSNSType = it[position].type
                    val editSNSIdx = it[position].snsIdx

                    GaramgaebiApplication.sSharedPreferences
                        .edit().putString("SNSAddressForEdit", editSNSAddress)
                        .putString("SNSTypeForEdit", editSNSType)
                        .putInt("SNSIdxForEdit", editSNSIdx)
                        .apply()

                    //SNS 편집 프래그먼트로!
                    val intent = Intent(context, ContainerActivity::class.java)
                    intent.putExtra("snsEdit", true)
                    startActivity(intent)

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
                Log.d("career_adapter_list_size",it.size.toString())
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }

            careerAdapter.setOnItemClickListener(object : CareerMyRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    // 경력 편집
                    val careerIdx = it[position].careerIdx
                    val editCareerCompany = it[position].company
                    val editCareerPosition = it[position].position
                    val editCareerIsWorking = it[position].isWorking
                    val editCareerStartDate = it[position].startDate
                    val editCareerEndDate = it[position].endDate
                    Log.d("career_edit_button", "success$editCareerEndDate")

                    GaramgaebiApplication.sSharedPreferences
                        .edit().putString("CareerCompanyForEdit", editCareerCompany)
                        .putString("CareerPositionForEdit", editCareerPosition)
                        .putString("CareerIsWorkingForEdit", editCareerIsWorking)
                        .putString("CareerStartDateForEdit", editCareerStartDate)
                        .putString("CareerEndDateForEdit", editCareerEndDate)
                        .putInt("CareerIdxForEdit",careerIdx)
                        .apply()

                    //SNS 편집 프래그먼트로!
                    val intent = Intent(context, ContainerActivity::class.java)
                    intent.putExtra("careerEdit", true)
                    startActivity(intent)
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
            }
            //발표 리사이클러뷰 클릭하면 팝업다이얼로그 나타남!
            eduAdapter.setOnItemClickListener(object : EduMyRVAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    // 교육 편집
                    val educationIdx = it[position].educationIdx
                    val editEduInstitution= it[position].institution
                    val editEduMajor = it[position].major
                    val editEduIsLearning = it[position].isLearning
                    val editEduStartDate = it[position].startDate
                    val editEduEndDate = it[position].endDate

                    GaramgaebiApplication.sSharedPreferences
                        .edit().putString("EduInstitutionForEdit", editEduInstitution)
                        .putString("EduMajorForEdit", editEduMajor)
                        .putString("EduIsLearningForEdit", editEduIsLearning)
                        .putString("EduStartDateForEdit", editEduStartDate)
                        .putString("EduEndDateForEdit", editEduEndDate)
                        .putInt("EduIdxForEdit", educationIdx)
                        .apply()

                    //SNS 편집 프래그먼트로!
                    val intent = Intent(context, ContainerActivity::class.java)
                    intent.putExtra("eduEdit", true)
                    startActivity(intent)
                }
            } )
        })

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

            activityMyProfileIvWd.setOnClickListener{
                goWithdrawalFragment()
            }

            activityMyProfileBtnEditProfile.setOnClickListener{
                goEditFragment()
            }
            //test 상대 프로필
            activityMyProfileIvProfile.setOnClickListener {
                val intent = Intent(activity,ContainerActivity::class.java)
                intent.putExtra("someoneProfile",true) //데이터 넣기
                startActivity(intent)
            }
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

    fun goEditSNSFragment(data:SNSData){
        val intent = Intent(activity,ContainerActivity::class.java)
        intent.putExtra("snsEdit",true) //데이터 넣기
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

    override fun onResume() {
        Log.d("onResume","yes__")

        viewModel.getProfileInfo(1)
        viewModel.getSNSInfo(1)
        viewModel.getCareerInfo(1)
        viewModel.getEducationInfo(1)

        super.onResume()
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}



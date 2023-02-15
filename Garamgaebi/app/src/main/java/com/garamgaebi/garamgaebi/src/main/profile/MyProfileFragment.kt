package com.garamgaebi.garamgaebi.src.main.profile

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.adapter.CareerMyRVAdapter
import com.garamgaebi.garamgaebi.adapter.EduMyRVAdapter
import com.garamgaebi.garamgaebi.adapter.SnsMyRVAdapter
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.databinding.FragmentMyprofileBinding
import com.garamgaebi.garamgaebi.model.ProfileDataResponse
import com.garamgaebi.garamgaebi.model.SNSData
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.ProfileViewModel

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

        binding.lifecycleOwner = this

        binding.setVariable(BR.profileViewModel,viewModel)
        var dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVSns.context, LinearLayoutManager(requireContext()).orientation)

        with(viewModel) {
            getProfileInfo(myMemberIdx)

            profileInfo.observe(viewLifecycleOwner, Observer {
                binding.profileViewModel = viewModel

                val result = it as ProfileDataResponse
                GaramgaebiApplication.sSharedPreferences
                    .edit().putString("nickname", result.result.nickName)
                    .apply()

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
                        Log.d("myImage",result.result.profileUrl+"h")
                        activityMyProfileTvUsername.text = result.result.nickName
                        activityMyProfileTvEmail.text = result.result.profileEmail
                        activityMyProfileTvSchool.text = result.result.belong
                        activityMyProfileTvIntro.text = result.result.content

                        if(result.result.profileUrl != null){
                            activity?.let { it1 ->
                                Glide.with(it1).load(result.result.profileUrl)
                                    .into(activityMyProfileIvProfile)
                            }
                        }

                        activityMyProfileIvProfile.clipToOutline = true

                        if (result.result.content == null) {
                            activityMyProfileTvIntro.visibility = View.GONE
                        }else{
                            activityMyProfileTvIntro.visibility = View.VISIBLE
                        }
                    }

                }
            })
            binding.activityMyProfileTvEmail.setOnClickListener {
                val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // 새로운 ClipData 객체로 데이터 복사하기
                val clip: ClipData =
                    ClipData.newPlainText("sns_address", this.email.value)

                // 새로운 클립 객체를 클립보드에 배치합니다.
                clipboard.setPrimaryClip(clip)
                Toast.makeText(binding.root.context, "복사 완료", Toast.LENGTH_SHORT).show()

            }
            //SNS 정보 어댑터 연결
            getSNSInfo(myMemberIdx)
            snsInfoArray.observe(viewLifecycleOwner, Observer { it ->
                val snsAdapter = activity?.let { it1 -> SnsMyRVAdapter(it, it1.applicationContext) }
                binding.activityMyProfileRVSns.apply {
                    adapter = snsAdapter

                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
                snsAdapter?.setOnItemClickListener(object : SnsMyRVAdapter.OnItemClickListener {
                    override fun onClick(position: Int) {
                    }
                })
            })
            //경력 정보 어댑터 연결
            getCareerInfo(myMemberIdx)
            careerInfoArray.observe(viewLifecycleOwner, Observer { it ->
                val careerAdapter = activity?.let { it1 ->
                    CareerMyRVAdapter(it,
                        it1.applicationContext)
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
                careerAdapter?.setOnItemClickListener(object : CareerMyRVAdapter.OnItemClickListener {
                    override fun onClick(position: Int) {
                    }
                })
                })
            //교육 정보 어댑터 연결
            getEducationInfo(myMemberIdx)
            educationInfoArray.observe(viewLifecycleOwner, Observer { it ->
                val eduAdapter = activity?.let { it1 -> EduMyRVAdapter(it, it1.applicationContext) }
                dividerItemDecoration = DividerItemDecoration(
                    binding.activityMyProfileRVEdu.context,
                    LinearLayoutManager(requireContext()).orientation
                )
                binding.activityMyProfileRVEdu.apply {
                    adapter = eduAdapter
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
                eduAdapter?.setOnItemClickListener(object : EduMyRVAdapter.OnItemClickListener {
                    override fun onClick(position: Int) {
                    }
                })
            })
        }

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
            //test 상대 프로필
//            activityMyProfileIvProfile.setOnClickListener {
//                val intent = Intent(activity,ContainerActivity::class.java)
//                intent.putExtra("someoneProfile",true) //데이터 넣기
//                startActivity(intent)
//            }
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
        viewModel.getProfileInfo(myMemberIdx)
        viewModel.getSNSInfo(myMemberIdx)
        viewModel.getCareerInfo(myMemberIdx)
        viewModel.getEducationInfo(myMemberIdx)

        super.onResume()
    }


    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}



package com.example.template.garamgaebi.src.main.profile

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.BR
import com.example.template.garamgaebi.config.BaseBindingFragment
import com.example.template.garamgaebi.config.GaramgaebiApplication
import com.example.template.garamgaebi.databinding.FragmentMyprofileBinding
import com.example.template.garamgaebi.model.HomeSeminarResult
import com.example.template.garamgaebi.model.ProfileData
import com.example.template.garamgaebi.model.ProfileDataResponse
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.src.main.home.HomeSeminarRVAdapter
import com.example.template.garamgaebi.src.main.home.HomeVPItemDecoration
import com.example.template.garamgaebi.src.main.seminar.SeminarPresentAdapter
import com.example.template.garamgaebi.src.main.seminar.SeminarPreviewDialog
import com.example.template.garamgaebi.src.main.seminar.SeminarVerticalItemDecoration
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


//        //내 프로필 편집 화면으로 이동
//        binding.activityMyProfileBtnEditProfile.setOnClickListener {
//            val intent = Intent(activity,ContainerActivity::class.java)
//            intent.putExtra("edit",true) //데이터 넣기
//            startActivity(intent)
//        }
//        //고객센터 화면으로 이동
//        binding.activityMyProfileIvCs.setOnClickListener {
//            val intent = Intent(activity,ContainerActivity::class.java)
//            intent.putExtra("servicecenter",true) //데이터 넣기
//            startActivity(intent)
//        }
//        //탈퇴 화면으로 이동
//        binding.activityMyProfileIvWd.setOnClickListener {
//            val intent = Intent(activity,ContainerActivity::class.java)
//            intent.putExtra("withdrawal",true) //데이터 넣기
//            startActivity(intent)
//        }

        //발표 어댑터 연결
        viewModel.getSeminarsInfo(6)
        val presentAdapter = SeminarPresentAdapter(viewModel.present)
        viewModel.present.observe(viewLifecycleOwner, Observer {
            /*val presentAdapter = SeminarPresentAdapter(presentList)
            binding.activitySeminarFreePresentRv.apply {
                adapter = presentAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(SeminarVerticalItemDecoration())
            }*/
            //val presentAdapter = SeminarPresentAdapter(viewModel.present)
            binding.activitySeminarFreePresentRv.apply {
                items.value = it
                val presentAdapter = SeminarPresentAdapter(items)
                // adapter = presentAdapter
                adapter = presentAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                addItemDecoration(SeminarVerticalItemDecoration())
            }
            //발표 리사이클러뷰 클릭하면 팝업다이얼로그 나타남!
            presentAdapter.setOnItemClickListener(object : SeminarPresentAdapter.OnItemClickListener{
                override fun onClick(position: Int) {
                    val bundle = Bundle()
                    bundle.putInt("presentationDialog", position)
                    val seminarPreviewDialog = SeminarPreviewDialog()
                    seminarPreviewDialog.arguments = bundle
                    activity?.let {
                        seminarPreviewDialog.show(
                            it.supportFragmentManager, "SeminarPreviewDialog"
                        )
                    }
                    SeminarPreviewDialog().dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }
            } )
        })


        //sns 리스트뷰 연결
        var snsItems: ArrayList<SnsRVItemData> = arrayListOf()
        val snsAdapter = SnsMYRVAdapter(snsItems)
        var dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVSns.context, LinearLayoutManager(requireContext()).orientation)
        binding.activityMyProfileRVSns.addItemDecoration(dividerItemDecoration)
        binding.activityMyProfileRVSns.adapter = snsAdapter

        //sns 추가 버튼
        binding.activityMyProfileBtnSnsAdd.setOnClickListener {
            binding.activityMyProfileRVSns.visibility = View.VISIBLE
            //binding.activityMyprofileSnsVListEndline.visibility = View.VISIBLE
            binding.activityMyProfileTvSnsDesc.visibility = View.GONE
            snsItems.add(SnsRVItemData("neoninstagram.com"))
            snsAdapter?.notifyDataSetChanged()
            Log.d("plus_sns",snsItems.size.toString())
            val intent = Intent(activity,ContainerActivity::class.java)
            intent.putExtra("sns",true) //데이터 넣기
            startActivity(intent)
        }

        //career 리스트뷰 연결
        var careerItems: ArrayList<CareerRVItemData> = arrayListOf()
        val careerAdapter = CareerMyRVAdapter(careerItems)
        dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVCareer.context, LinearLayoutManager(requireContext()).orientation)
        binding.activityMyProfileRVCareer.addItemDecoration(dividerItemDecoration)
        binding.activityMyProfileRVCareer.adapter = careerAdapter

//        //career 추가 버튼
//        binding.activityMyProfileBtnCareerAdd.setOnClickListener {
//            binding.activityMyProfileRVCareer.visibility = View.VISIBLE
//            binding.activityMyProfileTvCareerDesc.visibility = View.GONE
//            careerItems.add(CareerRVItemData("우아한 형제들","프론트엔드 개발자","2020.04","2021.09"))
//            careerAdapter?.notifyDataSetChanged()
//            Log.d("plus_career",careerItems.size.toString())
//            val intent = Intent(activity,ContainerActivity::class.java)
//            intent.putExtra("career",true) //데이터 넣기
//            startActivity(intent)
//        }

        //edu 리스트뷰 연결
        var eduItems: ArrayList<EduRVItemData> = arrayListOf()
        val eduAdapter = EduMyRVAdapter(eduItems)
        dividerItemDecoration = DividerItemDecoration(binding.activityMyProfileRVEdu.context, LinearLayoutManager(requireContext()).orientation)
        binding.activityMyProfileRVEdu.addItemDecoration(dividerItemDecoration)
        binding.activityMyProfileRVEdu.adapter = eduAdapter

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


    //career 추가 버튼
    fun goAddCareerFragment(){
        binding.activityMyProfileRVCareer.visibility = View.VISIBLE
        binding.activityMyProfileTvCareerDesc.visibility = View.GONE
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



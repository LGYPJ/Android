package com.garamgaebi.garamgaebi.garamgaebi.src.main.profile

import android.os.*
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.garamgaebi.adapter.*
import com.garamgaebi.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentSomeoneprofileBinding
import com.garamgaebi.garamgaebi.garamgaebi.model.ProfileDataResponse
import com.garamgaebi.garamgaebi.garamgaebi.viewModel.ProfileViewModel


class SomeoneProfileFragment :
BaseFragment<FragmentSomeoneprofileBinding>(FragmentSomeoneprofileBinding::bind, R.layout.fragment_someoneprofile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var memberIdx = -1

        memberIdx = GaramgaebiApplication.sSharedPreferences.getInt("userMemberIdx",-1)

        var viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]

        viewModel.getProfileInfo(memberIdx)

        with(viewModel) {
            getProfileInfo(GaramgaebiApplication.myMemberIdx)

            profileInfo.observe(viewLifecycleOwner, Observer {
                val result = it as ProfileDataResponse
                GaramgaebiApplication.sSharedPreferences
                    .edit().putString("nickname", result.result.nickName)
                    .apply()

                if (result == null) {

                } else {
                    with(binding) {

                        Log.d("myImage",result.result.profileUrl+"h")
                        activitySomeoneProfileTvUsername.text = result.result.nickName
                        activitySomeoneProfileTvEmail.text = result.result.profileEmail
                        activitySomeoneProfileTvSchool.text = result.result.belong
                        activitySomeoneProfileTvIntro.text = result.result.content

                        if(result.result.profileUrl != null){
                            activity?.let { it1 ->
                                Glide.with(it1).load(result.result.profileUrl)
                                    .into(activitySomeoneProfileIvProfile)
                            }
                        }

                        activitySomeoneProfileIvProfile.clipToOutline = true

                        if (result.result.content == null) {
                            activitySomeoneProfileTvIntro.visibility = View.GONE
                        }else{
                            activitySomeoneProfileTvIntro.visibility = VISIBLE
                        }
                    }

                }
            })


            var dividerItemDecoration =
                context?.let {
                    ContextCompat.getDrawable(it, R.drawable.divider)
                        ?.let { DividerItemDecoratorForLastItem(it) }
                };
            if (dividerItemDecoration != null) {
                binding.activitySomeoneProfileRVSns.addItemDecoration(dividerItemDecoration)
            }
            //SNS 정보 어댑터 연결
            getSNSInfo(memberIdx)
            snsInfoArray.observe(viewLifecycleOwner, Observer { it ->
                val snsAdapter = activity?.let { it1 -> SnsSomeoneRVAdapter(it, it1.applicationContext) }
                binding.activitySomeoneProfileRVSns.apply {
                    adapter = snsAdapter

                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
                snsAdapter?.setOnItemClickListener(object : SnsSomeoneRVAdapter.OnItemClickListener {
                    override fun onClick(position: Int) {
                    }
                })
            })
            //경력 정보 어댑터 연결
            getCareerInfo(memberIdx)
            careerInfoArray.observe(viewLifecycleOwner, Observer { it ->
                val careerAdapter = activity?.let { it1 ->
                    CareerSomeoneRVAdapter(it,
                        it1.applicationContext)
                }
                dividerItemDecoration =
                    context?.let { it ->
                        ContextCompat.getDrawable(it, R.drawable.divider)
                            ?.let { DividerItemDecoratorForLastItem(it) }
                    };
                if (dividerItemDecoration != null) {
                    binding.activitySomeoneProfileRVCareer.addItemDecoration(dividerItemDecoration!!)
                }
                binding.activitySomeoneProfileRVCareer.apply {
                    adapter = careerAdapter
                    Log.d("career_adapter_list_size", it.size.toString())
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
                careerAdapter?.setOnItemClickListener(object : CareerSomeoneRVAdapter.OnItemClickListener {
                    override fun onClick(position: Int) {
                    }
                })
            })
            //교육 정보 어댑터 연결
            getEducationInfo(memberIdx)
            educationInfoArray.observe(viewLifecycleOwner, Observer { it ->
                val eduAdapter = activity?.let { it1 -> EduSomeoneRVAdapter(it, it1.applicationContext) }
                dividerItemDecoration =
                    context?.let { it ->
                        ContextCompat.getDrawable(it, R.drawable.divider)
                            ?.let { DividerItemDecoratorForLastItem(it) }
                    };
                if (dividerItemDecoration != null) {
                    binding.activitySomeoneProfileRVEdu.addItemDecoration(dividerItemDecoration!!)
                }
                binding.activitySomeoneProfileRVEdu.apply {
                    adapter = eduAdapter
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                }
                eduAdapter?.setOnItemClickListener(object : EduSomeoneRVAdapter.OnItemClickListener {
                    override fun onClick(position: Int) {
                    }
                })
            })
        }
    }

}

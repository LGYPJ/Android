package com.garamgaebi.garamgaebi.src.main.profile

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.*
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.*
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.FragmentSomeoneprofileBinding
import com.garamgaebi.garamgaebi.model.CareerData
import com.garamgaebi.garamgaebi.model.ProfileDataResponse
import com.garamgaebi.garamgaebi.viewModel.ProfileViewModel
import java.util.Comparator


class SomeoneProfileFragment :
BaseFragment<FragmentSomeoneprofileBinding>(FragmentSomeoneprofileBinding::bind, R.layout.fragment_someoneprofile) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var memberIdx = -1
        memberIdx = GaramgaebiApplication.sSharedPreferences.getInt("userMemberIdx",-1)
        Log.d("멤버idx",memberIdx.toString())
        var viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
//        viewModel.getProfileInfo(memberIdx)

        with(viewModel) {
            profileInfo.observe(viewLifecycleOwner, Observer {
                val result = it as ProfileDataResponse


                with(binding) {

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

                    if (result.result.belong == null) {
                        activitySomeoneProfileTvSchool.visibility = View.GONE
                    }else{
                        activitySomeoneProfileTvSchool.visibility = VISIBLE
                    }

                    if (result.result.content == null) {
                        activitySomeoneProfileTvIntro.visibility = View.GONE
                    }else{
                        activitySomeoneProfileTvIntro.visibility = VISIBLE
                    }
                }
            })

            binding.activitySomeoneProfileTvEmail.setOnClickListener {
                val clipboard = context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                // 새로운 ClipData 객체로 데이터 복사하기
                val clip: ClipData =
                    ClipData.newPlainText("sns_address", this.email.value)

                // 새로운 클립 객체를 클립보드에 배치합니다.
                clipboard.setPrimaryClip(clip)
                Toast.makeText(binding.root.context, "복사 완료", Toast.LENGTH_SHORT).show()

            }

            var dividerItemDecoration =
                context?.let {
                    ContextCompat.getDrawable(it, R.drawable.divider)
                        ?.let { DividerItemDecoratorForLastItem(it) }
                };
            if (dividerItemDecoration != null) {
                binding.activitySomeoneProfileRVSns.addItemDecoration(dividerItemDecoration)
            }
            //SNS 정보 어댑터 연결
            //getSNSInfo(memberIdx)
            snsInfoArray.observe(viewLifecycleOwner, Observer { it ->

                if(it == null || it.size < 1){
                    binding.activitySomeoneProfileClSnsTitle.visibility = GONE
                    binding.activitySomeoneProfileClSnsDesc.visibility = GONE
                }else{
                    binding.activitySomeoneProfileClSnsTitle.visibility = VISIBLE
                    binding.activitySomeoneProfileClSnsDesc.visibility = VISIBLE
                }

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
            //getCareerInfo(memberIdx)
            careerInfoArray.observe(viewLifecycleOwner, Observer { it ->


                if(it == null || it.size < 1){
                    binding.activitySomeoneProfileClCareerTitle.visibility = GONE
                    binding.activitySomeoneProfileClCareerDesc.visibility = GONE
                }else{
                    binding.activitySomeoneProfileClCareerTitle.visibility = VISIBLE
                    binding.activitySomeoneProfileClCareerDesc.visibility = VISIBLE
                }
//                var byEndDate = Comparator.comparing { obj: CareerData -> obj.endDate}
//
//                it.sortWith(byEndDate)

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
//            getEducationInfo(memberIdx)
            educationInfoArray.observe(viewLifecycleOwner, Observer { it ->
                if(it == null || it.size < 1){
                    binding.activitySomeoneProfileClEduTitle.visibility = GONE
                    binding.activitySomeoneProfileClEduDesc.visibility = GONE
                }else{
                    binding.activitySomeoneProfileClEduTitle.visibility = VISIBLE
                    binding.activitySomeoneProfileClEduDesc.visibility = VISIBLE
                }
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

            getProfileInfo(memberIdx)
            getEducationInfo(memberIdx)
            getCareerInfo(memberIdx)
            getSNSInfo(memberIdx)
        }


        super.onViewCreated(view, savedInstanceState)

    }

}

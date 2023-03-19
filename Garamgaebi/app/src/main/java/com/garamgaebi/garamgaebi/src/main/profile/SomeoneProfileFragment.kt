package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.drawable.Drawable
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
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.*
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.databinding.FragmentSomeoneprofileBinding
import com.garamgaebi.garamgaebi.model.CareerData
import com.garamgaebi.garamgaebi.model.ProfileDataResponse
import com.garamgaebi.garamgaebi.viewModel.ProfileViewModel
import com.google.android.datatransport.runtime.dagger.multibindings.StringKey
import kotlinx.coroutines.*
import java.util.*


class SomeoneProfileFragment :
BaseFragment<FragmentSomeoneprofileBinding>(FragmentSomeoneprofileBinding::bind, R.layout.fragment_someoneprofile) {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)

    }
    override fun onStart() {
        super.onStart()
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            var memberIdx = -1
            val getIdx = runBlocking {
                memberIdx = GaramgaebiApplication().loadIntData("userMemberIdx")!!
                Log.d("resultssee",memberIdx.toString())
            }
            // memberIdx = GaramgaebiApplication.sSharedPreferences.getInt("userMemberIdx",-1)
            Log.d("멤버idx", memberIdx.toString())
            var viewModel =
                ViewModelProvider(this@SomeoneProfileFragment)[ProfileViewModel::class.java]
            with(viewModel) {
                profileInfo.observe(viewLifecycleOwner, Observer {
                    val result = it as ProfileDataResponse
                    Log.d("resultss",result.result.toString())

                    with(binding) {
                        if (result.result.profileUrl != null) {
//                        CoroutineScope(Dispatchers.Main).launch {
//                            val bitmap = withContext(Dispatchers.IO){
//                                GaramgaebiFunction.ImageLoader.loadImage(result.result.profileUrl)
//                            }
//                            binding.fragmentSomeoneProfileIvProfile.setImageBitmap(bitmap)
//                        }
                            //Glide.get()

                                Glide.with(requireActivity())
                                    .load(result.result.profileUrl)
                                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                                    .skipMemoryCache(true)
                                    .into(binding.fragmentSomeoneProfileIvProfile)
                            Log.d("resultssdd",result.result.toString())

                        }
                        fragmentSomeoneProfileTvUsername.text = result.result.nickName
                        fragmentSomeoneProfileTvEmail.text = result.result.profileEmail
                        fragmentSomeoneProfileTvSchool.text = result.result.belong
                        fragmentSomeoneProfileTvIntro.text = result.result.content

                        if (result.result.belong == null || result.result.belong.trim()
                                .equals("")
                        ) {
                            fragmentSomeoneProfileTvSchool.visibility = View.GONE
                        } else {
                            fragmentSomeoneProfileTvSchool.visibility = VISIBLE
                        }

                        if (result.result.content == null || result.result.content.trim()
                                .equals("")
                        ) {
                            fragmentSomeoneProfileTvIntro.visibility = View.GONE
                        } else {
                            fragmentSomeoneProfileTvIntro.visibility = VISIBLE
                        }
                    }
                })


                binding.fragmentSomeoneProfileTvEmail.setOnClickListener {
                    val clipboard =
                        context?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

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
                    binding.fragmentSomeoneProfileRVSns.addItemDecoration(dividerItemDecoration)
                }
                //SNS 정보 어댑터 연결
                //getSNSInfo(memberIdx)
                snsInfoArray.observe(viewLifecycleOwner, Observer { it ->

                    if (it == null || it.size < 1) {
                        binding.fragmentSomeoneProfileContainerSns.visibility = GONE
                    } else {
                        binding.fragmentSomeoneProfileContainerSns.visibility = VISIBLE
                    }

                    val snsAdapter =
                        activity?.let { it1 -> SnsSomeoneRVAdapter(it, it1.applicationContext) }
                    binding.fragmentSomeoneProfileRVSns.apply {
                        adapter = snsAdapter
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }
                    snsAdapter?.setOnItemClickListener(object :
                        SnsSomeoneRVAdapter.OnItemClickListener {
                        override fun onClick(position: Int) {
                        }
                    })
                })
                //경력 정보 어댑터 연결
                //getCareerInfo(memberIdx)
                careerInfoArray.observe(viewLifecycleOwner, Observer { it ->


                    if (it == null || it.size < 1) {
                        binding.fragmentSomeoneProfileContainerCareer.visibility = GONE
                    } else {
                        binding.fragmentSomeoneProfileContainerCareer.visibility = VISIBLE
                    }
//                var byEndDate = Comparator.comparing { obj: CareerData -> obj.endDate}
//
//                it.sortWith(byEndDate)

                    val careerAdapter = activity?.let { it1 ->
                        CareerSomeoneRVAdapter(
                            it,
                            it1.applicationContext
                        )
                    }
                    dividerItemDecoration =
                        context?.let { it ->
                            ContextCompat.getDrawable(it, R.drawable.divider)
                                ?.let { DividerItemDecoratorForLastItem(it) }
                        };
                    if (dividerItemDecoration != null) {
                        binding.fragmentSomeoneProfileRVCareer.addItemDecoration(
                            dividerItemDecoration!!
                        )
                    }
                    binding.fragmentSomeoneProfileRVCareer.apply {
                        adapter = careerAdapter
                        Log.d("career_adapter_list_size", it.size.toString())
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }
                    careerAdapter?.setOnItemClickListener(object :
                        CareerSomeoneRVAdapter.OnItemClickListener {
                        override fun onClick(position: Int) {
                        }
                    })
                })
                //교육 정보 어댑터 연결
//            getEducationInfo(memberIdx)
                educationInfoArray.observe(viewLifecycleOwner, Observer { it ->
                    if (it == null || it.size < 1) {
                        binding.fragmentSomeoneProfileContainerEdu.visibility = GONE
                    } else {
                        binding.fragmentSomeoneProfileContainerEdu.visibility = VISIBLE
                    }
                    val eduAdapter =
                        activity?.let { it1 -> EduSomeoneRVAdapter(it, it1.applicationContext) }
                    dividerItemDecoration =
                        context?.let { it ->
                            ContextCompat.getDrawable(it, R.drawable.divider)
                                ?.let { DividerItemDecoratorForLastItem(it) }
                        };
                    if (dividerItemDecoration != null) {
                        binding.fragmentSomeoneProfileRVEdu.addItemDecoration(dividerItemDecoration!!)
                    }
                    binding.fragmentSomeoneProfileRVEdu.apply {
                        adapter = eduAdapter
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    }
                    eduAdapter?.setOnItemClickListener(object :
                        EduSomeoneRVAdapter.OnItemClickListener {
                        override fun onClick(position: Int) {
                        }
                    })
                })
                binding.fragmentSomeoneProfileTvEmail.setOnClickListener {
                    val clipboard =
                        requireActivity()?.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

                    // 새로운 ClipData 객체로 데이터 복사하기
                    val clip: ClipData =
                        ClipData.newPlainText(
                            "email_address",
                            binding.fragmentSomeoneProfileTvEmail.text
                        )

                    // 새로운 클립 객체를 클립보드에 배치합니다.
                    clipboard.setPrimaryClip(clip)

                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.S_V2)
                        Toast.makeText(binding.root.context, "복사 완료", Toast.LENGTH_SHORT).show()
                }
                getProfileInfo(memberIdx)
                getEducationInfo(memberIdx)
                getCareerInfo(memberIdx)
                getSNSInfo(memberIdx)
            }



        super.onViewCreated(view, savedInstanceState)

    }

}

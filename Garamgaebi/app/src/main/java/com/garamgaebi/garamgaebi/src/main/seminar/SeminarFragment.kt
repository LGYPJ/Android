package com.garamgaebi.garamgaebi.src.main.seminar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.lifecycle.*
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.SeminarPresentAdapter
import com.garamgaebi.garamgaebi.adapter.SeminarProfileAdapter
import com.garamgaebi.garamgaebi.common.BaseBindingFragment

import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.networkValid

import com.garamgaebi.garamgaebi.databinding.FragmentSeminarBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.model.PresentationResult
import com.garamgaebi.garamgaebi.model.SeminarResult
import com.garamgaebi.garamgaebi.viewModel.ProfileViewModel
import com.garamgaebi.garamgaebi.viewModel.SeminarViewModel
import kotlinx.coroutines.*

class SeminarFragment: BaseBindingFragment<FragmentSeminarBinding>(R.layout.fragment_seminar) {

    //화면전환
    var containerActivity: ContainerActivity? = null
    private val viewModel: SeminarViewModel by lazy {
        ViewModelProvider(this)[SeminarViewModel::class.java]
    }
    @SuppressLint("SuspiciousIndentation")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lifecycleOwner = this
            setDataView()



        with(binding) {
            activitySeminarFreeProfileRv.addItemDecoration(SeminarHorizontalItemDecoration())
            activitySeminarFreePresentRv.addItemDecoration(SeminarVerticalItemDecoration())
        }
        //무료이면 무료신청 페이지로 유료이면 유료 신청 페이지로 ==> 프래그먼트 전환으로 바꾸기
        //binding.activitySeminarFreeApplyBtn.visibility = VISIBLE
        binding.activitySeminarFreeApplyBtn.setOnClickListener {
            val pay = binding.activitySeminarFreePayDetailTv.text
            if(pay == getString(R.string.origin_pay)) {
                containerActivity!!.openFragmentOnFrameLayout(2)
            }
            else {
                containerActivity!!.openFragmentOnFrameLayout(3)
            }
        }

    }
    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

    override fun onResume() {
        super.onResume()
        CoroutineScope(Dispatchers.IO).launch {
            if (networkValid.value == true) {
                updateData()
            } else {
                networkValid.postValue(false)
            }
        }
    }

    private fun setDataView() {
        with(viewModel) {
            //프로필 어댑터 연결
            seminarParticipants.observe(viewLifecycleOwner, Observer {
                val seminarProfile = SeminarProfileAdapter(it as ArrayList<SeminarResult>)
                //참석자가 없을 경우 다른 뷰 노출
                if (it.isEmpty()) {
                    binding.activitySeminarFreeNoParticipants.visibility = VISIBLE
                    binding.activitySeminarFreeProfileRv.visibility = GONE
                    //참석자 수 표시
                    binding.activitySeminarFreeParticipantsNumber.text =
                        getString(R.string.main_participants, "0")
                } else {
                    binding.activitySeminarFreeNoParticipants.visibility = GONE
                    binding.activitySeminarFreeProfileRv.visibility = VISIBLE
                    //참석자 수 표시
                    binding.activitySeminarFreeParticipantsNumber.text =
                        getString(R.string.main_participants, it.size.toString())
                    binding.activitySeminarFreeProfileRv.apply {
                        adapter = seminarProfile
                        layoutManager =
                            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        //addItemDecoration(SeminarHorizontalItemDecoration())
                    }
                    //리사이클러뷰 클릭 이벤트
                    seminarProfile.setOnItemClickListener(object :
                        SeminarProfileAdapter.OnItemClickListener {
                        override fun onClick(position: Int) {
                            var memberIdxCheck = 0
                            val putData = runBlocking {
                                memberIdxCheck = GaramgaebiApplication().loadIntData("memberIdx")!!
                            }
                            if (position == 0 && it[0].memberIdx == memberIdxCheck) {
                                //이동 x
                            } else {
                                val putData = runBlocking {
                                    GaramgaebiApplication().saveIntToDataStore(
                                        "userMemberIdx",
                                        it[position].memberIdx
                                    )!!
                                    containerActivity!!.openFragmentOnFrameLayout(13)
                                    containerActivity!!.goUser()
                                }
                            }
                        }
                    })
                }
            })

            //발표 어댑터 연결
            presentation.observe(viewLifecycleOwner, Observer { it ->
                val presentAdapter =
                    SeminarPresentAdapter(it.result as ArrayList<PresentationResult>)
                binding.activitySeminarFreePresentRv.apply {
                    adapter = presentAdapter
                    layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    //addItemDecoration(SeminarVerticalItemDecoration())
                }
                //발표 리사이클러뷰 클릭하면 팝업다이얼로그 나타남!
                presentAdapter.setOnItemClickListener(object :
                    SeminarPresentAdapter.OnItemClickListener {
                    override fun onClick(position: Int) {
                        val bundle = Bundle()
                        bundle.putInt("presentationDialog", position)
                        val seminarPreviewDialog = SeminarPreviewDialog(it.result)
                        seminarPreviewDialog.arguments = bundle
                        /*activity?.let {
                                seminarPreviewDialog.show(
                                    it.supportFragmentManager, "SeminarPreviewDialog"
                                )
                            }*/
                        seminarPreviewDialog.show(parentFragmentManager, "SeminarPreviewDialog")
                        SeminarPreviewDialog(it.result).dialog?.window?.setBackgroundDrawable(
                            ColorDrawable(Color.TRANSPARENT)
                        )
                    }
                })
            })


            //세미나 상세 정보
            info.observe(viewLifecycleOwner, Observer {
                val item = it.result
                with(binding) {
                    activitySeminarFreeTitleTv.text = item.title
                    activitySeminarFreeDateDetailTv.text = item.date
                    activitySeminarFreePlaceDetailTv.text = item.location
                }
                if (item.fee.toString() == "0") {
                    binding.activitySeminarFreePayDetailTv.text = getString(R.string.origin_pay)
                } else {
                    binding.activitySeminarFreePayDetailTv.text =
                        getString(R.string.main_fee, item.fee.toString())
                }
                binding.activitySeminarFreeDeadlineDetailTv.text = item.endDate

                //무료
                if (it.result.fee == 0) {
                    // 버튼 상태
                    if (it.result.userButtonStatus == getString(R.string.origin_apply_complete)) {
                        //신청완료, 비활성화
                        with(binding.activitySeminarFreeApplyBtn) {
                            text = getString(R.string.apply_complete_btn)
                            setTextColor(resources.getColor(R.color.seminar_blue))
                            setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
                            isEnabled = false
                        }
                    }
                    if (it.result.userButtonStatus == getString(R.string.origin_apply_closed)) {
                        //마감, 비활성화
                        with(binding.activitySeminarFreeApplyBtn) {
                            text = getString(R.string.apply_end_btn)
                            setTextColor(resources.getColor(R.color.gray8a))
                            setBackgroundResource(R.drawable.activity_userbutton_closed_gray)
                            isEnabled = false
                        }
                    }
                    if (it.result.userButtonStatus == getString(R.string.origin_apply_apply)) {
                        // 신청하기 활성화
                        with(binding.activitySeminarFreeApplyBtn) {
                            text = getString(R.string.origin_apply)
                            setTextColor(resources.getColor(R.color.white))
                            setBackgroundResource(R.drawable.btn_seminar_apply)
                            isEnabled = true
                        }
                    }
                    binding.activitySeminarFreeApplyBtn.visibility = VISIBLE
                }
                //유료
                else {
                    // 버튼 상태
                    if (it.result.userButtonStatus == getString(R.string.origin_before_apply_confirm)) {
                        //신청확인중, 비활성화
                        with(binding.activitySeminarFreeApplyBtn) {
                            text = getString(R.string.apply_check_btn)
                            setTextColor(resources.getColor(R.color.seminar_blue))
                            setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
                        }
                    }
                    if (it.result.userButtonStatus == getString(R.string.origin_apply_complete)) {
                        //신청완료, 비활성화
                        with(binding.activitySeminarFreeApplyBtn) {
                            text = getString(R.string.apply_complete_btn)
                            setTextColor(resources.getColor(R.color.seminar_blue))
                            setBackgroundResource(R.drawable.activity_seminar_apply_done_btn_border)
                            isEnabled = false
                        }
                    }
                    if (it.result.userButtonStatus == getString(R.string.origin_apply_closed)) {
                        //마감, 비활성화
                        with(binding.activitySeminarFreeApplyBtn) {
                            text = getString(R.string.apply_end_btn)
                            setTextColor(resources.getColor(R.color.gray8a))
                            setBackgroundResource(R.drawable.activity_userbutton_closed_gray)
                            isEnabled = false
                        }
                    }
                    if (it.result.userButtonStatus == getString(R.string.origin_apply_apply)) {
                        // 신청하기 활성화
                        with(binding.activitySeminarFreeApplyBtn) {
                            text = getString(R.string.origin_apply)
                            setTextColor(resources.getColor(R.color.white))
                            setBackgroundResource(R.drawable.btn_seminar_apply)
                            isEnabled = true
                        }
                    }
                    binding.activitySeminarFreeApplyBtn.visibility = VISIBLE
                }

            })
        }

    }

    private suspend fun updateData():Int {
        val value: Int = withContext(Dispatchers.IO) {
            val total = 1
            with(viewModel) {
                getSeminarParticipants()
                getSeminarsInfo()
                getSeminarDetail()
            }
            total
        }
        return value
    }



}
package com.garamgaebi.garamgaebi.src.main.gathering

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.GatheringSeminarDeadlineRVAdapter
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiFunction
import com.garamgaebi.garamgaebi.common.NetworkErrorDialog
import com.garamgaebi.garamgaebi.databinding.FragmentGatheringSeminarBinding
import com.garamgaebi.garamgaebi.model.GatheringSeminarClosedResult
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.GatheringViewModel
import kotlinx.coroutines.*

/**
 * 세미나 모아보기 프래그먼트
 *
 */
class GatheringSeminarFragment : BaseFragment<FragmentGatheringSeminarBinding>(FragmentGatheringSeminarBinding::bind,R.layout.fragment_gathering_seminar){
    val viewModel by viewModels<GatheringViewModel>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 서버 꺼졌을 때 예외처리 하기 위해 시작할 때 뷰
        /*binding.fragmentGatheringSeminarThisMonthClBlank.visibility = View.VISIBLE
        binding.fragmentGatheringSeminarClThisMonth.visibility = View.GONE
        binding.fragmentGatheringSeminarScheduledClBlank.visibility = View.VISIBLE
        binding.fragmentGatheringSeminarClScheduled.visibility = View.GONE
        binding.fragmentGatheringSeminarClosedClBlank.visibility = View.VISIBLE
        binding.fragmentGatheringSeminarRvClosed.visibility = View.GONE*/
        binding.fragmentGatheringSeminarRvClosed.addItemDecoration(GatheringItemDecoration())
        showLoadingDialog(requireContext())
        CoroutineScope(Dispatchers.IO).launch {
            setView()
            dismissLoadingDialog()
        }
    }
    private suspend fun setView() {
        viewModel.getGatheringSeminarThisMonth()
        viewModel.getGatheringSeminarNextMonth()
        viewModel.getGatheringSeminarClosed()

        withContext(Dispatchers.Main) {
            // 이번달
            viewModel.seminarThisMonth.observe(viewLifecycleOwner, Observer {
                val result = it.result
                if(result == null) {
                    binding.fragmentGatheringSeminarThisMonthClBlank.visibility = View.VISIBLE
                    binding.fragmentGatheringSeminarClThisMonth.visibility = View.GONE
                } else {
                    binding.fragmentGatheringSeminarThisMonthClBlank.visibility = View.GONE
                    binding.fragmentGatheringSeminarClThisMonth.visibility = View.VISIBLE

                    binding.fragmentGatheringSeminarThisMonthTvName.text = result.title
                    binding.fragmentGatheringSeminarThisMonthTvDateData.text = GaramgaebiFunction().getDateYMD(result.date)
                    binding.fragmentGatheringSeminarThisMonthTvPlaceData.text = result.location
                    binding.fragmentGatheringSeminarThisMonthTvDDay.text = GaramgaebiFunction().getDDay(result.date)

                    val program = it.result.programIdx
                    if(it.result.isOpen == "BEFORE_OPEN"){
                        binding.fragmentGatheringSeminarClThisMonth.isEnabled = false
                    }
                    binding.fragmentGatheringSeminarClThisMonth.setOnClickListener {
                        //세미나 메인 화면으로
//                        GaramgaebiApplication.sSharedPreferences
//                            .edit().putInt("programIdx", program)
//                            .apply()
                        val putData = runBlocking {
                            GaramgaebiApplication().saveIntToDataStore("programIdx",program)
                        }
                        //세미나 메인 프래그먼트로!
                        startActivity(Intent(context, ContainerActivity::class.java)
                            .putExtra("seminar", true)
                            .putExtra("goseminar", "goseminar"))
                    }
                }

            })

            // 예정된
            viewModel.seminarNextMonth.observe(viewLifecycleOwner, Observer {
                val result = it.result
                if(result == null) {
                    binding.fragmentGatheringSeminarScheduledClBlank.visibility = View.VISIBLE
                    binding.fragmentGatheringSeminarClScheduled.visibility = View.GONE
                } else {
                    binding.fragmentGatheringSeminarScheduledClBlank.visibility = View.GONE
                    binding.fragmentGatheringSeminarClScheduled.visibility = View.VISIBLE

                    binding.fragmentGatheringSeminarScheduledTvName.text = result.title
                    binding.fragmentGatheringSeminarScheduledTvDateData.text = GaramgaebiFunction().getDateYMD(result.date)
                    binding.fragmentGatheringSeminarScheduledTvPlaceData.text = result.location
                }
                binding.fragmentGatheringSeminarClScheduled.isEnabled = false

            })

            // 마감된
            viewModel.seminarClosed.observe(viewLifecycleOwner, Observer {
                val result = it.result as ArrayList<GatheringSeminarClosedResult>
                val seminarDeadlineAdapter : GatheringSeminarDeadlineRVAdapter
                if(result == null || result.isEmpty()) {
                    binding.fragmentGatheringSeminarClosedClBlank.visibility = View.VISIBLE
                    binding.fragmentGatheringSeminarRvClosed.visibility = View.GONE
                } else {
                    seminarDeadlineAdapter = GatheringSeminarDeadlineRVAdapter(result)
                    binding.fragmentGatheringSeminarClosedClBlank.visibility = View.GONE
                    binding.fragmentGatheringSeminarRvClosed.visibility = View.VISIBLE
                    binding.fragmentGatheringSeminarRvClosed.apply {
                        adapter = seminarDeadlineAdapter
                        layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

                    }
                    seminarDeadlineAdapter.setOnItemClickListener(object :
                        GatheringSeminarDeadlineRVAdapter.OnItemClickListener{
                        override fun onClick(position: Int) {
//                            GaramgaebiApplication.sSharedPreferences
//                                .edit().putInt("programIdx", it.result[position].programIdx)
//                                .apply()
                            val putData = runBlocking {
                                GaramgaebiApplication().saveIntToDataStore("programIdx",it.result[position].programIdx)
                            }
                            //세미나 메인 프래그먼트로!
                            startActivity(Intent(context, ContainerActivity::class.java)
                                .putExtra("seminar", true)
                                .putExtra("goseminar", "goseminar"))
                        }
                    })
                }
            })
        }
    }


}
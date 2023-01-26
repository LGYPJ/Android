package com.softsquared.template.Garamgaebi.src.main.cancel

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.databinding.FragmentCancelBankBottomDialogBinding
import com.softsquared.template.Garamgaebi.databinding.FragmentServicecenterOrderBottomDialogBinding

class CancelBankBottomDialogFragment(val itemClick: (String) -> Unit): BottomSheetDialogFragment(){

    lateinit var binding: FragmentCancelBankBottomDialogBinding

    private var cancelList: ArrayList<Cancel> = arrayListOf(
        Cancel(R.drawable.bankicon, "NH농협"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_2, "카카오뱅크"),
        Cancel(R.drawable.bankicon_21, "신한"),
        Cancel(R.drawable.bankicon_4, "우리"),
        Cancel(R.drawable.bankicon_5, "IBK 기업"),
        Cancel(R.drawable.bankicon_6, "하나"),
        Cancel(R.drawable.bankicon_7, "토스뱅크"),
        Cancel(R.drawable.bankicon_7, "새마을"),
        Cancel(R.drawable.bankicon_9, "부산"),
        Cancel(R.drawable.bankicon_10, "대구"),
        Cancel(R.drawable.bankicon_11, "케이뱅크"),
        Cancel(R.drawable.bankicon_12, "신협"),
        Cancel(R.drawable.bankicon_13, "우체국"),
        Cancel(R.drawable.bankicon_14, "SC제일"),
        Cancel(R.drawable.bankicon_15, "경남"),
        Cancel(R.drawable.bankicon_16, "수협"),
        Cancel(R.drawable.bankicon_17, "광주"),
        Cancel(R.drawable.bankicon_18, "전북"),
        Cancel(R.drawable.bankicon_19, "저축은행"),
        Cancel(R.drawable.bankicon_20, "시티"),
        Cancel(R.drawable.bankicon_21, "제주"),
        Cancel(R.drawable.bankicon_21, "KDB산업"),
        Cancel(R.drawable.bankicon_23, "SBI저축은행"),
        Cancel(R.drawable.bankicon_24, "산림조합"),
        Cancel(R.drawable.bankicon_24, "BOA"),
        Cancel(R.drawable.bankicon_24, "HSBC"),
        Cancel(R.drawable.bankicon_24, "중국"),
        Cancel(R.drawable.bankicon_24, "도이치"),
        Cancel(R.drawable.bankicon_24, "중국공상"),
        Cancel(R.drawable.bankicon_24, "JP모건"),
        Cancel(R.drawable.bankicon_24, "BNP파리바"),
        Cancel(R.drawable.bankicon_24, "중국건설"),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCancelBankBottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cancelAdapter = CancelAdapter(cancelList)
        binding.fragmentCancelRv.apply {
            adapter = cancelAdapter
            layoutManager = GridLayoutManager(context, 3)
            addItemDecoration(CancelHorizontalItemDecoration())
        }



        cancelAdapter.setOnItemClickListener(object : CancelAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                // 클릭하면 캔슬액티비티에 은행 글자 데이터 보내는
                val intent = Intent(context, CancelActivity::class.java)
                val temp = cancelList[position].bank
                intent.putExtra("bank", temp)


                itemClick(temp)
                dialog?.dismiss()
            }
        })
    }
}
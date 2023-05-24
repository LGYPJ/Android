package com.garamgaebi.garamgaebi.src.main.cancel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.adapter.CancelAdapter
import com.garamgaebi.garamgaebi.databinding.FragmentCancelBankBottomDialogBinding

class CancelBankBottomDialogFragment(val itemClick: (String) -> Unit): BottomSheetDialogFragment(){

    lateinit var binding: FragmentCancelBankBottomDialogBinding

    private var cancelList: ArrayList<Cancel> = arrayListOf(
        Cancel(R.drawable.bankicon, getString(R.string.apply_bank_name)),
        Cancel(R.drawable.bankicon_1, getString(R.string.apply_bank_name1)),
        Cancel(R.drawable.bankicon_2, getString(R.string.apply_bank_name2)),
        Cancel(R.drawable.bankicon_21, getString(R.string.apply_bank_name3)),
        Cancel(R.drawable.bankicon_4, getString(R.string.apply_bank_name4)),
        Cancel(R.drawable.bankicon_5, getString(R.string.apply_bank_name5)),
        Cancel(R.drawable.bankicon_6, getString(R.string.apply_bank_name6)),
        Cancel(R.drawable.bankicon_7, getString(R.string.apply_bank_name7)),
        Cancel(R.drawable.bankicon_8, getString(R.string.apply_bank_name8)),
        Cancel(R.drawable.bankicon_9, getString(R.string.apply_bank_name9)),
        Cancel(R.drawable.bankicon_10, getString(R.string.apply_bank_name10)),
        Cancel(R.drawable.bankicon_11, getString(R.string.apply_bank_name11)),
        Cancel(R.drawable.bankicon_12, getString(R.string.apply_bank_name12)),
        Cancel(R.drawable.bankicon_13, getString(R.string.apply_bank_name13)),
        Cancel(R.drawable.bankicon_14, getString(R.string.apply_bank_name14)),
        Cancel(R.drawable.bankicon_15, getString(R.string.apply_bank_name15)),
        Cancel(R.drawable.bankicon_16, getString(R.string.apply_bank_name16)),
        Cancel(R.drawable.bankicon_17, getString(R.string.apply_bank_name17)),
        Cancel(R.drawable.bankicon_18, getString(R.string.apply_bank_name18)),
        Cancel(R.drawable.bankicon_19, getString(R.string.apply_bank_name19)),
        Cancel(R.drawable.bankicon_20, getString(R.string.apply_bank_name20)),
        Cancel(R.drawable.bankicon_21, getString(R.string.apply_bank_name21)),
        Cancel(R.drawable.bankicon_22, getString(R.string.apply_bank_name22)),
        Cancel(R.drawable.bankicon_23, getString(R.string.apply_bank_name23)),
        Cancel(R.drawable.bankicon_25, getString(R.string.apply_bank_name24)),
        Cancel(R.drawable.bankicon_26, getString(R.string.apply_bank_name25)),
        Cancel(R.drawable.bankicon_27, getString(R.string.apply_bank_name26)),
        Cancel(R.drawable.bankicon_28, getString(R.string.apply_bank_name27)),
        Cancel(R.drawable.bankicon_29, getString(R.string.apply_bank_name28)),
        Cancel(R.drawable.bankicon_30, getString(R.string.apply_bank_name29)),
        Cancel(R.drawable.bankicon_31, getString(R.string.apply_bank_name30)),
        Cancel(R.drawable.bankicon_32, getString(R.string.apply_bank_name31)),
        Cancel(R.drawable.bankicon_33, getString(R.string.apply_bank_name32)),
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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

                val temp = cancelList[position].bank

                itemClick(temp)
                dialog?.dismiss()
            }
        })
    }
}
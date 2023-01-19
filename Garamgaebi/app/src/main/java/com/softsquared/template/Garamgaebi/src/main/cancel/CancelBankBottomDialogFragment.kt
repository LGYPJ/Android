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

class CancelBankBottomDialogFragment(): BottomSheetDialogFragment(){

    lateinit var binding: FragmentCancelBankBottomDialogBinding

    private var cancelList: ArrayList<Cancel> = arrayListOf(
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
        Cancel(R.drawable.bankicon_1, "KB국민"),
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
        }

        cancelAdapter.setOnItemClickListener(object : CancelAdapter.OnItemClickListener{
            override fun onClick(position: Int) {
                // 클릭하면 캔슬액티비티에 은행 글자 데이터 보내는
                val intent = Intent(context, CancelActivity::class.java)
                val temp = cancelList[position].bank
                intent.putExtra("bank", temp)
                startActivity(intent)
            }
        })
    }
}
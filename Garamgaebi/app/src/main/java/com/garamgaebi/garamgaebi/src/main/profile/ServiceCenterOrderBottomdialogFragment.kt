package com.garamgaebi.garamgaebi.src.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.garamgaebi.garamgaebi.databinding.FragmentServicecenterOrderBottomDialogBinding

class ServiceCenterOrderBottomdialogFragment (val itemClick: (Int) -> Unit) :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentServicecenterOrderBottomDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServicecenterOrderBottomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentDialogTvQuestion.setOnClickListener {
            itemClick(0)
            dialog?.dismiss()
        }
        binding.fragmentDialogTvReport.setOnClickListener {
            itemClick(1)
            dialog?.dismiss()
        }
        binding.fragmentDialogTvService.setOnClickListener {
            itemClick(2)
            dialog?.dismiss()
        }
        binding.fragmentDialogTvEtc.setOnClickListener {
            itemClick(3)
            dialog?.dismiss()
        }
    }
}
package com.garamgaebi.garamgaebi.src.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentDatepickerDialogBinding

class DatePickerDialogFragment (val itemClick: (String) -> Unit) :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentDatepickerDialogBinding
    private lateinit var date:String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDatepickerDialogBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.datePickerBtn.setOnClickListener {
            var dp = binding.datePicker
            date = String.format("%4d.%02d.%2d",dp.year,dp.month+1,dp.dayOfMonth)

            itemClick(date)
            dialog?.dismiss()
        }
    }
}
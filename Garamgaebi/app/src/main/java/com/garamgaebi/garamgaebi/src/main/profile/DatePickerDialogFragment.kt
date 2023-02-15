package com.garamgaebi.garamgaebi.src.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.garamgaebi.garamgaebi.databinding.FragmentDatepickerDialogBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DatePickerDialogFragment (var date:String, val itemClick: (String) -> Unit) :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentDatepickerDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDatepickerDialogBinding.inflate(inflater, container, false)
        return binding.root

    }
    fun setYearMonth(year:String,month:String){
        binding.pickerYear.value = year.toInt()
        binding.pickerMonth.value = month.toInt()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            pickerYear.maxValue = 2100
            pickerYear.minValue = 1900
            pickerMonth.maxValue = 12
            pickerMonth.minValue = 1

            datePickerBtn.setOnClickListener {
                date = String.format("%4d.%02d", pickerYear.value, pickerMonth.value)

                itemClick(date)
                dialog?.dismiss()
            }
        }

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy.MM")
        var formatted = current.format(formatter)
        var arr : List<String>
        if(date.isNotEmpty() && !(date.equals("Error") || date.equals("현재"))) {
            arr = date.split(".")
            setYearMonth(arr[0], arr[1])
        }else{
            arr = formatted.split(".")
            setYearMonth(arr[0], arr[1])
        }
        Log.d("date뭐냐",arr.toString())


    }
}
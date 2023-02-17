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
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy.MM")
            val formatterForYear = DateTimeFormatter.ofPattern("yyyy")

            var formatted = current.format(formatter)


            pickerYear.maxValue = current.format(formatterForYear).toInt()
            pickerYear.minValue = 1950
            pickerMonth.maxValue = 12
            pickerMonth.minValue = 1

            datePickerBtn.setOnClickListener {
                date = String.format("%4d.%02d", pickerYear.value, pickerMonth.value)

                itemClick(date)
                dialog?.dismiss()
            }
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //false로 설정해 주면 화면밖 혹은 뒤로가기 버튼시 다이얼로그라 dismiss 되지 않는다.
        isCancelable = false
    }}
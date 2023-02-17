package com.garamgaebi.garamgaebi.src.main.profile

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.garamgaebi.garamgaebi.databinding.FragmentDatepickerDialogBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.math.log

class DatePickerDialogFragment (var date:String, val itemClick: (String) -> Unit) :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentDatepickerDialogBinding
    var dateYear:Int = 0
    var dateMonth:Int = 0
    val current = LocalDateTime.now()
    val formatter = DateTimeFormatter.ofPattern("yyyy.MM")
    val formatterForYear = DateTimeFormatter.ofPattern("yyyy")
    val formatterForMonth = DateTimeFormatter.ofPattern("MM")
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


            var formatted = current.format(formatter)
            pickerYear.value = current.format(formatterForYear).toInt()
            pickerMonth.value = current.format(formatterForMonth).toInt()

            dateYear = pickerYear.value
            dateMonth = pickerMonth.value

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
        isCancelable = true
    }

    override fun dismiss() {
        var formatted = current.format(formatter)
        dateYear = current.format(formatterForYear).toInt()
        dateMonth = current.format(formatterForMonth).toInt()

        date = String.format("%4d.%02d", dateYear, dateMonth)
        itemClick(date)
        Log.d("data_what",date.toString())

        super.dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        dateYear = current.format(formatterForYear).toInt()
        dateMonth = current.format(formatterForMonth).toInt()

        date = String.format("%4d.%02d", dateYear, dateMonth)
        itemClick(date)
        Log.d("data_what",date.toString())
        super.onCancel(dialog)
    }

    }
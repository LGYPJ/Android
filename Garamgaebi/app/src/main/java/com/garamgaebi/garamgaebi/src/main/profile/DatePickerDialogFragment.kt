package com.garamgaebi.garamgaebi.src.main.profile

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.garamgaebi.garamgaebi.common.MINIMUM_YEAR
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.garamgaebi.garamgaebi.databinding.FragmentDatepickerDialogBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class DatePickerDialogFragment (var date:String, val itemClick: (String) -> Unit) :
    BottomSheetDialogFragment() {
    lateinit var binding: FragmentDatepickerDialogBinding
    private var dateYear:Int = 0
    private var dateMonth:Int = 0
    private val current: LocalDateTime = LocalDateTime.now()
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM")
    private val formatterForYear: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy")
    private val formatterForMonth: DateTimeFormatter = DateTimeFormatter.ofPattern("MM")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDatepickerDialogBinding.inflate(inflater, container, false)
        return binding.root

    }
    private fun setYearMonth(year:String, month:String){
        year.toInt().also { binding.pickerYear.value = it }
        binding.pickerMonth.value = month.toInt()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {


            val formatted = current.format(formatter)
            pickerYear.value = current.format(formatterForYear).toInt()
            pickerMonth.value = current.format(formatterForMonth).toInt()

            dateYear = pickerYear.value
            dateMonth = pickerMonth.value

            pickerYear.maxValue = current.format(formatterForYear).toInt()
            pickerYear.minValue = MINIMUM_YEAR
            pickerMonth.maxValue = 12
            pickerMonth.minValue = 1

            datePickerBtn.setOnClickListener {
                date = String.format("%4d/%02d", pickerYear.value, pickerMonth.value)
                itemClick(date)
                dialog?.dismiss()
            }
            val arr : List<String>
            if(date.isNotEmpty() && !(date == "Error" || date == "현재")) {
                arr = date.split("/")
                setYearMonth(arr[0], arr[1])
            }else{
                arr = formatted.split("/")
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
        dateYear = current.format(formatterForYear).toInt()
        dateMonth = current.format(formatterForMonth).toInt()

        date = String.format("%4d/%02d", dateYear, dateMonth)
        itemClick(date)
        Log.d("data_what",date)

        super.dismiss()
    }

    override fun onCancel(dialog: DialogInterface) {
        dateYear = current.format(formatterForYear).toInt()
        dateMonth = current.format(formatterForMonth).toInt()

        date = String.format("%4d/%02d", dateYear, dateMonth)
        itemClick(date)
        Log.d("data_what",date)
        super.onCancel(dialog)
    }

    }
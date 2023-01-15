package com.Garamgaebi.src.profile

import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import com.softsquared.template.Garamgaebi.R
import com.softsquared.template.Garamgaebi.config.BaseActivity
import com.softsquared.template.Garamgaebi.databinding.ActivityServicecenterBinding

class ServiceCenterActivity : BaseActivity<ActivityServicecenterBinding>(ActivityServicecenterBinding::inflate) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //뒤로가기
        binding.activityServicecenterBackBtn.setOnClickListener {
            onBackPressed()
        }

        val spinner = binding.activityServicecenterSpinner

        val items = resources.getStringArray(R.array.question_option)

        val myAapter = object : ArrayAdapter<String>(this, R.layout.activity_servicecenter_item_spinner) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

                val v = super.getView(position, convertView, parent)

                if (position == count) {
                    //마지막 포지션의 textView 를 힌트 용으로 사용합니다.
                    (v.findViewById<View>(R.id.tvItemSpinner) as TextView).text = ""
                    //아이템의 마지막 값을 불러와 hint로 추가해 줍니다.
                    (v.findViewById<View>(R.id.tvItemSpinner) as TextView).hint = getItem(count)
                }

                return v
            }

            override fun getCount(): Int {
                //마지막 아이템은 힌트용으로만 사용하기 때문에 getCount에 1을 빼줍니다.
                return super.getCount() - 1
            }

        }


//아이템을 추가해 줍니다.
        myAapter.addAll(items.toMutableList())

//힌트로 사용할 문구를 마지막 아이템에 추가해 줍니다.
        myAapter.add("질문 유형을 선택해주세요.")

//어댑터를 연결해줍니다.
        spinner.adapter = myAapter

//스피너 초기값을 마지막 아이템으로 설정해 줍니다. (마지막 아이템이 힌트 이기 때문이죠)
        spinner.setSelection(myAapter.count)

//droplist를 spinner와 간격을 두고 나오게 해줍니다.)
//아이템 크기가 45dp 이므로 45dp 간격을 주었습니다.
//이때 dp 를 px 로 변환해 주는 작업이 필요합니다.
        spinner.dropDownVerticalOffset = dipToPixels(45f).toInt()

//스피너 선택시 나오는 화면 입니다.
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {

                //아이템이 클릭 되면 맨 위부터 position 0번부터 순서대로 동작하게 됩니다.
                when (position) {
                    0 -> {

                    }
                    1 -> {

                    }
                    2 -> {

                    }
                    3 -> {

                    }
                    else -> {

                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                Log.d("MyTag", "onNothingSelected")
            }
        }


    }
    private fun dipToPixels(dipValue: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dipValue,
            resources.displayMetrics
        )
    }
    //뒤로가기 버튼 눌렀을 때
    override fun onBackPressed() {
        super.onBackPressed()
//        stopPlay() //이 액티비티에서 종료되어야 하는 활동 종료시켜주는 함수
//        Toast.makeText(this@WebViewPlayer, "방송 시청이 종료되었습니다.", Toast.LENGTH_SHORT).show() //토스트 메시지
//        val intent =
//            Intent(this@WebViewPlayer, MainActivity::class.java) //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
//        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP //인텐트 플래그 설정
//        startActivity(intent) //인텐트 이동
        finish() //현재 액티비티 종료
    }
}


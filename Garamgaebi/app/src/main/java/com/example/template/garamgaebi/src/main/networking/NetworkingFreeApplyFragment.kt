package com.example.template.garamgaebi.src.main.networking

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.template.garamgaebi.R
import com.example.template.garamgaebi.common.BaseFragment
import com.example.template.garamgaebi.databinding.FragmentNetworkingFreeApplyBinding
import com.example.template.garamgaebi.src.main.ContainerActivity
import com.example.template.garamgaebi.viewModel.ApplyViewModel
import java.util.regex.Pattern

class NetworkingFreeApplyFragment: BaseFragment<FragmentNetworkingFreeApplyBinding>(FragmentNetworkingFreeApplyBinding::bind, R.layout.fragment_networking_free_apply) {


    //화면전환
    var containerActivity: ContainerActivity? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.activityNetworkFreeApplyBtn.isEnabled = false

        //신청 등록 뷰모델
        val viewModel = ViewModelProvider(this)[ApplyViewModel::class.java]

        // et selected 여부에 따라 drawable 결정
        binding.activityNetworkFreeApplyNameTv.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
            } else {
                view.setBackgroundResource(R.drawable.et_seminat_apply)
            }
        }
        binding.activityNetworkFreeApplyNicknameTv.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
            } else {
                view.setBackgroundResource(R.drawable.et_seminat_apply)

            }
        }
        binding.activityNetworkFreeApplyPhoneTv.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
            } else {
                view.setBackgroundResource(R.drawable.et_seminat_apply)
            }
        }

        // et에 따라 tv 변경 & drawable 변경 & 신청하기버튼 활성화
        binding.activityNetworkFreeApplyNicknameTv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(!isNickName()){
                    binding.activityNetworkFreeApplyNotCorrectNicknameTv.visibility = View.VISIBLE
                    binding.activityNetworkFreeApplyNicknameTv.setBackgroundResource(R.drawable.activity_seminar_apply_red_border)
                }
                else{
                    binding.activityNetworkFreeApplyNotCorrectNicknameTv.visibility = View.GONE
                    binding.activityNetworkFreeApplyNicknameTv.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
                }
                if(isButton()){

                    binding.activityNetworkFreeApplyBtn.isEnabled = true
                    binding.activityNetworkFreeApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                }
                else {

                    binding.activityNetworkFreeApplyBtn.isEnabled = false
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        binding.activityNetworkFreeApplyPhoneTv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(!isPhoneNumberCheck()){
                    binding.activityNetworkFreeApplyNotCorrectPhoneTv.visibility = View.VISIBLE
                    binding.activityNetworkFreeApplyPhoneTv.setBackgroundResource(R.drawable.activity_seminar_apply_red_border)
                }
                else{
                    binding.activityNetworkFreeApplyNotCorrectPhoneTv.visibility = View.GONE
                    binding.activityNetworkFreeApplyPhoneTv.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
                }
                if(isButton()){

                    binding.activityNetworkFreeApplyBtn.isEnabled = true
                    binding.activityNetworkFreeApplyBtn.setBackgroundResource(R.drawable.btn_seminar_apply)
                }
                else {

                    binding.activityNetworkFreeApplyBtn.isEnabled = false
                }
            }
            override fun afterTextChanged(p0: Editable?) {
            }

        })

        //신청하기 버튼 누르면 버튼 바뀌는 값 전달
        binding.activityNetworkFreeApplyBtn.setOnClickListener {
            /*val bundle = Bundle()
            bundle.putBoolean("networking",true)
            val networkingFragment = NetworkingFragment()
            networkingFragment.arguments = bundle
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.activity_seminar_frame, networkingFragment).commit()*/
            /*val name = binding.activityNetworkFreeApplyNameTv.text.toString()
            val nickname = binding.activityNetworkFreeApplyNicknameTv.text.toString()
            val phone = binding.activityNetworkFreeApplyPhoneTv.text.toString()*/
            //신청 등록 api
            //viewModel.postEnroll(EnrollRequest(0,0,name,nickname,phone))
            viewModel.enroll.observe(viewLifecycleOwner, Observer {
                if(it.isSuccess){
                    //네트워킹 메인 화면으로
                    requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
                    requireActivity().supportFragmentManager.popBackStack()
                }
            })
        }


    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

    //전화번호 형식 맞나
    fun isPhoneNumberCheck() : Boolean {
        var returnValue = false
        val phone = binding.activityNetworkFreeApplyPhoneTv.text.toString()
        val regex = "^\\s*(010|011|012|013|014|015|016|017|018|019)(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$";
        val p = Pattern.compile(regex);
        val m = p.matcher(phone);
        if (m.matches()) {
            returnValue = true;
        }
        return returnValue;
    }
    //닉네임 맞나
    fun isNickName(): Boolean{
        var returnValue = false
        val nickname = binding.activityNetworkFreeApplyNicknameTv.text.toString()
        val regex = "cindy"
        val p = regex.matches(nickname.toRegex())
        if(p){
            returnValue = true;
        }
        return returnValue;
    }
    //신청하기 버튼 클릭하면 네트워킹 메인 신청하기 버튼 바뀌게
    fun isButton():Boolean {
        var returnValue = false
        if(isNickName()&&isPhoneNumberCheck()){
            returnValue = true;
        }
        return returnValue;
    }
}
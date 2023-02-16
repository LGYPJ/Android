package com.garamgaebi.garamgaebi.src.main.networking

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.FragmentNetworkingFreeApplyBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.ApplyViewModel
import java.util.regex.Pattern

class NetworkingFreeApplyFragment: BaseFragment<FragmentNetworkingFreeApplyBinding>(FragmentNetworkingFreeApplyBinding::bind, R.layout.fragment_networking_free_apply) {


    //화면전환
    var containerActivity: ContainerActivity? = null
    private val viewModel by viewModels<ApplyViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //데이터바인딩
        binding.setVariable(BR.item, viewModel)
        binding.activityNetworkFreeApplyBtn.isEnabled = false

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

        binding.activityNetworkFreeApplyNameTv.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                if(!isNickName()){
                    binding.activityNetworkFreeApplyNotCorrectNameTv.visibility = View.VISIBLE
                    binding.activityNetworkFreeApplyNameTv.setBackgroundResource(R.drawable.activity_seminar_apply_red_border)
                }
                else{
                    binding.activityNetworkFreeApplyNotCorrectNameTv.visibility = View.GONE
                    binding.activityNetworkFreeApplyNameTv.setBackgroundResource(R.drawable.activity_seminar_et_border_gray)
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
            viewModel.postEnroll()
            viewModel.enroll.observe(viewLifecycleOwner, Observer {
                binding.item = viewModel
                if(it.isSuccess){
                    //네트워킹 메인 화면으로
                    requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
                    requireActivity().supportFragmentManager.popBackStack()
                }
            })
        }

        viewModel.getNetworking()
        viewModel.networkingInfo.observe(viewLifecycleOwner, Observer{
            binding.item = viewModel
        })


    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

    // 이름 형식 맞나
    fun isName() : Boolean {
        var returnValue = false
        val name = binding.activityNetworkFreeApplyNameTv.text.toString()
        val regex = "^[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z]{1,20}$"
        val p = Pattern.compile(regex)
        val m = p.matcher(name)
        if (m.matches()) {
            returnValue = true
        }
        return returnValue;
    }

    //전화번호 형식 맞나
    fun isPhoneNumberCheck() : Boolean {
        var returnValue = false
        val phone = binding.activityNetworkFreeApplyPhoneTv.text.toString()
        val regex = "^[0-9]{11}$"
        val p = Pattern.compile(regex)
        val m = p.matcher(phone)
        if (m.matches()) {
            returnValue = true
        }
        return returnValue
    }
    //닉네임 맞나
    fun isNickName(): Boolean{
        var returnValue = false
        val nickname = binding.activityNetworkFreeApplyNicknameTv.text.toString()
        //나중에 회원가입할 때 닉네임 로컬에 저장해서 regax에 선언하기
        val regex = GaramgaebiApplication.sSharedPreferences.getString("nickname", null)
        val p = regex?.matches(nickname.toRegex())
        if(p == true){
            returnValue = true
        }
        return returnValue
    }

    //버튼 활성화
    fun isButton():Boolean {
        var returnValue = false
        if(isNickName()&&isPhoneNumberCheck()&&isName()){
            returnValue = true
        }
        return returnValue

    }
}
package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.databinding.FragmentWithdrawalBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.src.main.register.LoginActivity
import com.garamgaebi.garamgaebi.viewModel.WithdrawalViewModel
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

class WithdrawalFragment :
    BaseBindingFragment<FragmentWithdrawalBinding>(R.layout.fragment_withdrawal),
    ConfirmDialogInterface {
    var containerActivity: ContainerActivity? = null

    @SuppressLint("SuspiciousIndentation", "ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var myEmail = GaramgaebiApplication.sSharedPreferences.getString("mySchoolEmail","not@gachon.ac.kr")

        val viewModel = ViewModelProvider(this)[WithdrawalViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this


        if (myEmail != null) {
            viewModel.email.value = myEmail
        }

        viewModel.category.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            if(it.isNotEmpty() && (it.toCharArray()[0] != ' '))
                viewModel.categoryIsValid.value = true

            if(it.equals("ETC")){
                binding.activityContentEtNameLength.visibility = View.VISIBLE
                viewModel.content.value=""
            }else{
                binding.activityContentEtNameLength.visibility = View.GONE
                viewModel.contentIsValid.value=true
                viewModel.contentFirst.value=false

            }
        })
        viewModel.content.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            viewModel.contentIsValid.value = it.length < INPUT_TEXT_LENGTH_100 && it.isNotEmpty()
            GaramgaebiFunction().checkFirstChar(viewModel.contentIsValid, it)
        })
        viewModel.agree.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.agreeIsValid.value = binding.activityWithdrawalCheckbox.isChecked
        })





        //동의 체크박스 클릭 이벤트
        disposables
            .add(
                binding
                    .activityWithdrawalTvCheckboxDesc
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        var preCheck = binding.activityWithdrawalCheckbox.isChecked
                        viewModel.agree.value = !preCheck
                    }, { it.printStackTrace() })
            )
        disposables
            .add(
                binding
                    .activityWithdrawalEtOption
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        viewModel.categoryFocusing.value = true
                        viewModel.categoryFirst.value = false

                        val orderBottomDialogFragment: WithdrawalOrderBottomDialogFragment = WithdrawalOrderBottomDialogFragment {
                            when (it) {
                                0 -> {
                                    Toast.makeText(activity, "이용이 불편해서", Toast.LENGTH_SHORT).show()
                                    binding.activityWithdrawalEtOption.setText("이용이 불편해서")
                                    viewModel.categoryFocusing.value = false
                                    viewModel.category.value = "UNCOMFORTABLE"
                                }
                                1 -> {
                                    Toast.makeText(activity, "사용 빈도가 낮아서", Toast.LENGTH_SHORT).show()
                                    binding.activityWithdrawalEtOption.setText("사용 빈도가 낮아서")
                                    viewModel.categoryFocusing.value = false
                                    viewModel.category.value = "UNUSED"
                                }
                                2 -> {
                                    Toast.makeText(activity, "콘텐츠 내용이 부족해서", Toast.LENGTH_SHORT).show()
                                    binding.activityWithdrawalEtOption.setText("콘텐츠 내용이 부족해서")
                                    viewModel.categoryFocusing.value = false
                                    viewModel.category.value = "CONTENT_LACK"

                                }
                                3 -> {
                                    Toast.makeText(activity, "기타", Toast.LENGTH_SHORT).show()
                                    binding.activityWithdrawalEtOption.setText("기타")
                                    viewModel.categoryFocusing.value = false
                                    viewModel.category.value = "ETC"

                                }

                            }
                        }
                        orderBottomDialogFragment.show(parentFragmentManager, orderBottomDialogFragment.tag)
                    }, { it.printStackTrace() })
            )
        disposables
            .add(
                binding
                    .activityWithdrawalSendBtn
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        val dialog: DialogFragment? = ConfirmDialog(this,"탈퇴하시겠습니까?", 1) { it ->
                            when (it) {
                                -1 -> {

                                    Log.d("withdrawal_button","close")

                                }
                                1 -> {
                                    //탈퇴
                                    viewModel.postWithdrawal()
                                    activity?.startActivity(
                                        Intent(activity,
                                            LoginActivity::class.java)
                                    )
                                    val dialog = ConfirmDialog(this, "탈퇴가 완료되었습니다", -1){it2 ->
                                        when(it2){
                                            1 -> {

                                                Log.d("withdrawal_button","close")
                                                (activity as ContainerActivity).onBackPressed()

                                            }
                                            2->{
                                                (activity as ContainerActivity).onBackPressed()
                                            }
                                        }
                                    }
                                    // 알림창이 띄워져있는 동안 배경 클릭 막기
                                    dialog.show(activity?.supportFragmentManager!!, "com.example.garamgaebi.common.ConfirmDialog")


                                }
                            }
                        }
                        // 알림창이 띄워져있는 동안 배경 클릭 막기
                        dialog?.show(activity?.supportFragmentManager!!, "com.example.garamgaebi.common.ConfirmDialog")
                        Log.d("career_remove_button","success")
                    }, { it.printStackTrace() })
            )
        binding.containerLayout.setOnTouchListener(View.OnTouchListener { v, event ->
            hideKeyboard()
            false
        })

    }
    private fun hideKeyboard() {
        if (activity != null && requireActivity().currentFocus != null) {
            // 프래그먼트기 때문에 getActivity() 사용
            val inputManager: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                requireActivity().currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }

    override fun onYesButtonClick(id: Int) {
        TODO("Not yet implemented")
    }
}


package com.garamgaebi.garamgaebi.src.main.seminar

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.networkValid
import com.garamgaebi.garamgaebi.common.KeyboardVisibilityUtils
import com.garamgaebi.garamgaebi.databinding.FragmentSeminarFreeApplyBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.ApplyViewModel
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.coroutines.runBlocking
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

/**
<SeminarFreeApplyFragment>
화면 기능 : 세미나 일시, 장소, 참가비, 신청마감 상세정보 보여줌 & 이름, 닉네임, 전화번호 기입하여 세미나 신청하는 기능

세미나 상세 정보 -> 데이터바인딩 이용

이름, 닉네임, 전화번호 유효성 검사
viewModel.nameIsValid.value -> isNotEmpty 이름란이 비어 있지 않을때  && isName() 한글,영문 가능, 특수문자 불가능 , 1-20자  --> true (그 외 false)
viewModel.nicknameIsValid.value -> isNotEmpty 닉네임란이 비어 있지 않을때 && isNickName() 가입시 닉네임(datastore의 myNickName)과 입력 닉네임이 같을때 --> true (그 외 false)
viewModel.phoneIsValid.value -> isNotEmpty 전화번호란이 비어 있지 않을때 && isPhoneNumberCheck() 숫자만 입력 11자 --> true (그 외 false)

유효성 false -> 유효성 문구 나타나게 & EditText 테두리 빨간색으로 ( layout 파일에있음)
유효성 모두 true -> 신청하기 버튼 활성화

신청하기 버튼 클릭 -> SeminarFragment 화면으로

 */

class SeminarFreeApplyFragment: BaseBindingFragment<FragmentSeminarFreeApplyBinding>(R.layout.fragment_seminar_free_apply) {

    //화면전환
    var containerActivity: ContainerActivity? = null
    private val viewModel by viewModels<ApplyViewModel>()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //데이터바인딩
        binding.setVariable(BR.item, viewModel)
        binding.item = viewModel
        binding.lifecycleOwner = this

        viewModel.inputName.observe(viewLifecycleOwner, Observer{
            binding.item = viewModel
            viewModel.nameIsValid.value = it.isNotEmpty() && isName(it)
        })

        viewModel.inputNickName.observe(viewLifecycleOwner, Observer{
            binding.item = viewModel
            viewModel.nicknameIsValid.value = it.isNotEmpty() && isNickName(it)
        })

        viewModel.inputPhone.observe(viewLifecycleOwner, Observer{
            binding.item = viewModel
            viewModel.phoneIsValid.value = it.isNotEmpty() && isPhoneNumberCheck(it)
        })

        with(viewModel){
            nameState.value = getString(R.string.apply_not_name)
            nicknameState.value = getString(R.string.apply_not_nickname)
            phoneState.value = getString(R.string.apply_not_phone)
        }

        //신청하기 버튼 누르면 버튼 바뀌는 값 전달
        disposables
            .add(
                binding
                    .activitySeminarFreeApplyBtn
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        if(networkValid.value == true){
                        //신청 등록 api
                        viewModel.postEnroll()
                        viewModel.enroll.observe(viewLifecycleOwner, Observer {
                            binding.item = viewModel
                            if(it.isSuccess){
                                //세미나 메인 화면으로
                                requireActivity().supportFragmentManager.beginTransaction().remove(this).commit()
                                requireActivity().supportFragmentManager.popBackStack()

                            }
                        })}else{
                            (requireActivity() as ContainerActivity).networkAlertDialog()
                        }
                    }, { it.printStackTrace() })
            )

        viewModel.getSeminar()
        viewModel.seminarInfo.observe(viewLifecycleOwner, Observer{
            binding.item = viewModel
        })


        binding.containerLayout.setOnTouchListener(View.OnTouchListener { v, event ->
            hideKeyboard()
            false
        })

        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
            onShowKeyboard = { keyboardHeight ->
                binding.svRoot.run {
                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
                }
                binding.activitySeminarFreeApplyBtn.visibility = View.GONE
            },
            onHideKeyboard = { ->
                //binding.fragmentCareerSaveBtn.visibility = View.VISIBLE
            }
        )
        view.viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                val rect = Rect()
                view.getWindowVisibleDisplayFrame(rect)

                val screenHeight = view.rootView.height
                val keypadHeight = screenHeight - rect.bottom

                if (keypadHeight < screenHeight * 0.15) {
                    binding.activitySeminarFreeApplyBtn.postDelayed({
                        binding.activitySeminarFreeApplyBtn.visibility = View.VISIBLE
                    },0)

                }
            }
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

    // 이름 형식 맞나
    fun isName(name : String) : Boolean {
        var returnValue = false
        val regex = "^[가-힣a-zA-Z]{1,20}$"
        val p = Pattern.compile(regex)
        val m = p.matcher(name)
        if (m.matches()) {
            returnValue = true
        }
        return returnValue;
    }

    //전화번호 형식 맞나
    fun isPhoneNumberCheck( phone : String) : Boolean {
        var returnValue = false
        val regex = "^[0-9]{11}$"
        val p = Pattern.compile(regex)
        val m = p.matcher(phone)
        if (m.matches()) {
            returnValue = true
        }
        return returnValue
    }
    //닉네임 맞나
    fun isNickName(nickname : String): Boolean{
        var returnValue = false
        var regex = ""
        val putData = runBlocking {
            regex = GaramgaebiApplication().loadStringData("myNickName").toString()
        }
        val p = regex?.matches(nickname.toRegex())
        if(p == true){
            returnValue = true
        }
        return returnValue
    }

    //화면전환
    override fun onAttach(context: Context) {
        super.onAttach(context)
        containerActivity = context as ContainerActivity
    }

    override fun onDestroy() {
        keyboardVisibilityUtils.detachKeyboardListeners()
        super.onDestroy()
    }
}
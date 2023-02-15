package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.util.Patterns
import android.view.View
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.garamgaebi.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.garamgaebi.databinding.FragmentProfileEditBinding
import com.garamgaebi.garamgaebi.viewModel.ProfileViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class ProfileEditFragment :
    BaseBindingFragment<FragmentProfileEditBinding>(R.layout.fragment_profile_edit) {
    private lateinit var callback: OnBackPressedCallback

    var nickState:Int = 0
    var emailState:Int = 0
    var teamState:Int = 0



    @RequiresApi(Build.VERSION_CODES.P)
    private val imageResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        if(result.resultCode == Activity.RESULT_OK){
            val imageUri = result.data?.data ?: return@registerForActivityResult

            val file = File(activity?.let { absolutelyPath(imageUri, it) })
            val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
            val body = MultipartBody.Part.createFormData("profile", file.name, requestFile)


            val bitmap = context?.let { ImageDecoder.createSource(it.contentResolver,imageUri) }
                ?.let { ImageDecoder.decodeBitmap(it) }
            binding.activityEditProfileIvProfile.setImageBitmap(bitmap)
            binding.activityEditProfileIvProfile.setImageResource(R.drawable.basic_gray_border_layout)
            Log.d("testt",file.name)
            Log.d("testt2",imageUri.toString())
            Log.d("testt3",bitmap.toString())

            activity?.let { Glide.with(it).load(imageUri).fitCenter().apply(RequestOptions().override(80,80)).into(binding.activityEditProfileIvProfile) }


            //sendImage(body)

            //binding.activityEditProfileIvProfile.setImageURI(file)

//                        activity?.let { it1 ->
//                            Glide.with(it1)
//                                .load(imageUri.toString())
//                                .into(binding.activityEditProfileIvProfile)
//                        }
            binding.activityEditProfileIvProfile.clipToOutline = true


            Log.d("testt_",binding.activityEditProfileIvProfile.resources.toString())

        }
    }
    companion object{
        const val REQ_GALLERY = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    @RequiresApi(Build.VERSION_CODES.P)
    private fun selectGallery(){
        val writePermission = activity?.let {
            ContextCompat.checkSelfPermission(
                it,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        }
        val readPermission = activity?.let {
            ContextCompat.checkSelfPermission(
                it,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            )
        }

        if(writePermission == PackageManager.PERMISSION_DENIED ||
            readPermission == PackageManager.PERMISSION_DENIED){
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQ_GALLERY
                )
            }
        }else{
            val intent = Intent(Intent.ACTION_PICK)
            intent.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            Log.d("왜안돼","어")
            imageResult.launch(intent)
        }



    }
    // 절대경로 변환

    fun absolutelyPath(path: Uri?, context : Context): String {
        var proj: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
        var c: Cursor? = context.contentResolver.query(path!!, proj, null, null, null)
        var index = c?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        c?.moveToFirst()

        var result = c?.getString(index!!)

        return result!!
    }

//    fun sendImage(body: MultipartBody.Part){
//        retrofit.sendImage(body).enqueue(object: Callback<String>{
//            override fun onResponse(call: Call<String>, response: Response<String>) {
//                if(response.isSuccessful){
//                    Toast.makeText(this@MainActivity, "이미지 전송 성공", Toast.LENGTH_SHORT).show()
//                }else{
//                    Toast.makeText(this@MainActivity, "이미지 전송 실패", Toast.LENGTH_SHORT).show()
//                }
//            }
//
//            override fun onFailure(call: Call<String>, t: Throwable) {
//                Log.d("testt", t.message.toString())
//            }
//
//        })
//    }
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("viewcreated","yes")
        val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        binding.setVariable(BR.profileViewModel,viewModel)
        //val viewModels by viewModels<ProfileViewModel>()

    binding.activityEditProfileIvProfile.setOnClickListener {
       selectGallery()
        Log.d("test---","아얏")
    }
        with(binding) {
            viewModel.nickName.value = GaramgaebiApplication.sSharedPreferences.getString(
                "myNickName",
                "Error"
            )
            viewModel.belong.value = GaramgaebiApplication.sSharedPreferences.getString(
                "myBelong",
                "Error"
            )
            viewModel.email.value = GaramgaebiApplication.sSharedPreferences.getString(
                "myEmail",
                "Error"
            )
            viewModel.intro.value = GaramgaebiApplication.sSharedPreferences.getString(
                "myIntro",
                ""
            )
            viewModel.image.value = GaramgaebiApplication.sSharedPreferences.getString(
                "myImage",
                "Error"
            )

            activityEditProfileEtNick.setText(
                GaramgaebiApplication.sSharedPreferences.getString(
                    "myNickName",
                    "Error"
                )
            )
            activityEditProfileEtTeam.setText(
                GaramgaebiApplication.sSharedPreferences.getString(
                    "myBelong",
                    "Error"
                )
            )
            activityEditProfileEtEmail.setText(
                GaramgaebiApplication.sSharedPreferences.getString(
                    "myEmail",
                    "Error"
                )
            )
            activityEditProfileEtIntro.setText(
                GaramgaebiApplication.sSharedPreferences.getString(
                    "myIntro",
                    "Error"
                )
            )
//            activity?.let { it1 ->
//                Glide.with(it1)
//                    .load(GaramgaebiApplication.sSharedPreferences.getString("myImage", ""))
//                    .into(activityEditProfileIvProfile)
//            }
            //activityEditProfileIvProfile.clipToOutline = true


        }

        //편집 정보 저장하기 버튼 클릭이벤트
        binding.activityEducationSaveBtn.setOnClickListener {
            if (checkInfo() == true){
                //회원정보 편집 저장 기능 추가
                viewModel.getCheckEditProfileInfo(myMemberIdx)
            }else{
                //저장 불가 및 이유
            }
        }

        // 유효성 확인
        viewModel.nickName.observe(viewLifecycleOwner, Observer {
            binding.profileViewModel = viewModel
            if(it.length < 22 && it.isNotEmpty())
                viewModel.nickNameIsValid.value = true

            Log.d("profile_nickName_true",viewModel.nickNameIsValid.value.toString())
        })
        viewModel.belong.observe(viewLifecycleOwner, Observer {
            binding.profileViewModel = viewModel
            if(it.length < 22 && it.isNotEmpty())
                viewModel.belongIsValid.value = true

            Log.d("profile_belong_true",viewModel.belongIsValid.value.toString())
        })
        viewModel.email.observe(viewLifecycleOwner, Observer {
            binding.profileViewModel = viewModel

            viewModel.emailIsValid.value = it.isNotEmpty()

            Log.d("profile_email_true",viewModel.emailIsValid.value.toString())
        })
        viewModel.intro.observe(viewLifecycleOwner, Observer {
            binding.profileViewModel = viewModel

            viewModel.introIsValid.value = it.isNotEmpty()

            Log.d("profile_intro_true",viewModel.introIsValid.value.toString())
        })

        binding.activityEducationSaveBtn.setOnClickListener {
            //편집하기 버튼
           // viewModel.postEducationInfo()
            Log.d("profile_edit_button","success")
        }

        //닉네임 입력 시 레이아웃 테두리 변경
        checkNickname(binding.activityEditProfileEtNick)

        //소속 입력 시 레이아웃 테두리 변경
        checkTeam(binding.activityEditProfileEtTeam)

        //이메일 입력 시 레이아웃 테두리 변경
        checkEmail(binding.activityEditProfileEtEmail)

        //자기소개 입력 시 레이아웃 테두리 변경
        checkEtInput(binding.activityEditProfileEtIntro)



    }
    fun checkEtInput(view: EditText){
        var focusing : Boolean = false

        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            focusing = hasFocus
            if (hasFocus) {
                binding.activityEditProfileEtIntro.setHint("")
                view.setBackgroundResource(R.drawable.basic_black_border_layout)
            } else {
                binding.activityEditProfileEtIntro.setHint("100자 이내로 작성해주세요")
                view.setBackgroundResource(R.drawable.basic_gray_border_layout)
            }
        }
    }

    fun checkNickname(view: EditText){
        var focusing : Boolean = false

        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            focusing = hasFocus
            if (hasFocus) {
                binding.activityEditProfileEtNick.setHint("")
                view.setBackgroundResource(R.drawable.basic_black_border_layout)
            } else {
                binding.activityEditProfileEtNick.setHint("닉네임을 입력해주세요 (최대 8글자")
                view.setBackgroundResource(R.drawable.basic_gray_border_layout)
            }
            if(nickState == -1){
                view.setBackgroundResource(R.drawable.basic_red_border_layout)
            }
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var nick = binding.activityEditProfileEtNick.text.toString()

                //닉네임이 너무 길 때
                if(nick.length > 8) {
                    binding.activityNicknameState.apply {
                        visibility = View.VISIBLE
                        text = "사용 불가능한 닉네임입니다"
                        setTextColor(getColor(requireActivity().applicationContext,R.color.redForText))
                    }
                    binding.activityEditProfileEtNick.setBackgroundResource(R.drawable.basic_red_border_layout)
                    nickState = -1

                    //나머지 경우
                } else {
                    if (focusing){
                        binding.activityEditProfileEtNick.setBackgroundResource(R.drawable.basic_black_border_layout)
                        binding.activityNicknameState.visibility = View.VISIBLE
                    }else {
                        binding.activityEditProfileEtNick.setBackgroundResource(R.drawable.basic_gray_border_layout)
                        binding.activityNicknameState.visibility = View.GONE
                    }
                    //유효한 경우
                    if(nick.isNotEmpty()) {
                        binding.activityNicknameState.apply {
                            visibility = View.VISIBLE
                            text = "사용 가능한 닉네임입니다"
                            setTextColor(getColor(requireActivity().applicationContext,R.color.blueForBtn))
                        }
                        nickState = 1
                    } else{
                        binding.activityNicknameState.visibility = View.GONE
                        nickState = 0
                    }
                }
                if (checkInfo()){
                    binding.activityEducationSaveBtn.isClickable = true
                    binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
                }else{
                    binding.activityEducationSaveBtn.isClickable = false
                    binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
                }

            }
        })
    }

    fun checkTeam(view: EditText){
        var focusing : Boolean = false

        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            focusing = hasFocus
            if (hasFocus) {
                binding.activityEditProfileEtTeam.setHint("")
                view.setBackgroundResource(R.drawable.basic_black_border_layout)
            } else {
                binding.activityEditProfileEtTeam.setHint("소속을 입력해주세요")
                view.setBackgroundResource(R.drawable.basic_gray_border_layout)
            }
            if(teamState == -1){
                view.setBackgroundResource(R.drawable.basic_red_border_layout)
            }
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var team = binding.activityEditProfileEtTeam.text.toString()

                //소속이 너무 길 때
                if(team.length > 18) {
                    binding.activityTeamState.apply {
                        visibility = View.VISIBLE
                        text = "사용 불가능한 소속입니다"
                        setTextColor(getColor(requireActivity().applicationContext,R.color.redForText))
                    }
                    binding.activityEditProfileEtTeam.setBackgroundResource(R.drawable.basic_red_border_layout)
                    teamState = -1

                    //나머지 경우
                } else {

                    if (focusing){
                        binding.activityEditProfileEtTeam.setBackgroundResource(R.drawable.basic_black_border_layout)
                        binding.activityTeamState.visibility = View.VISIBLE
                    }else {
                        binding.activityEditProfileEtTeam.setBackgroundResource(R.drawable.basic_gray_border_layout)
                        binding.activityTeamState.visibility = View.GONE
                    }
                    //유효한 경우
                    if(team.isNotEmpty()) {
                        binding.activityTeamState.apply {
                            visibility = View.VISIBLE
                            text = "사용 가능한 소속입니다"
                            setTextColor(getColor(requireActivity().applicationContext,R.color.blueForBtn))
                        }
                        teamState = 1
                    } else{
                        binding.activityTeamState.visibility = View.GONE
                        teamState = 0
                    }
                }
                if (checkInfo()){
                    binding.activityEducationSaveBtn.isClickable = true
                    binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
                }else{
                    binding.activityEducationSaveBtn.isClickable = false
                    binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
                }
            }
        })
    }
    fun checkEmail(view: EditText){
        var focusing : Boolean = false

        view.onFocusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            focusing = hasFocus
            if (hasFocus) {
                binding.activityEditProfileEtEmail.setHint("")
                view.setBackgroundResource(R.drawable.basic_black_border_layout)
            } else {
                binding.activityEditProfileEtEmail.setHint("이메일을 입력해주세요")
                view.setBackgroundResource(R.drawable.basic_gray_border_layout)
            }
            if(emailState == -1){
                view.setBackgroundResource(R.drawable.basic_red_border_layout)
            }
        }
        view.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {}
            @SuppressLint("SuspiciousIndentation")
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val email = binding.activityEditProfileEtEmail.text.toString()

                //유효할 때
                    if(Patterns.EMAIL_ADDRESS.matcher(email).matches() && email.isNotEmpty()){
                        binding.activityEmailState.apply {
                        visibility = View.VISIBLE
                        text = "사용 가능한 이메일입니다"
                        setTextColor(getColor(requireActivity().applicationContext,R.color.blueForBtn))
                    }
                    emailState = 1
                        if (focusing){
                            binding.activityEditProfileEtEmail.setBackgroundResource(R.drawable.basic_black_border_layout)
                            binding.activityEmailState.visibility = View.VISIBLE
                        }else {
                            binding.activityEditProfileEtEmail.setBackgroundResource(R.drawable.basic_gray_border_layout)
                            binding.activityEmailState.visibility = View.GONE
                        }

                    //유효하지 않을 경우
                } else {
                    if(email.isNotEmpty()) {
                        binding.activityEmailState.apply {
                            visibility = View.VISIBLE
                            text = "이메일 형식이 올바르지 않습니다"
                            setTextColor(getColor(requireActivity().applicationContext,R.color.redForText))
                        }
                        binding.activityEditProfileEtEmail.setBackgroundResource(R.drawable.basic_red_border_layout)
                        emailState = -1
                    } else{
                        binding.activityEmailState.visibility = View.GONE
                        emailState = 0
                    }
                }
                if (checkInfo()){
                    binding.activityEducationSaveBtn.isClickable = true
                    binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_blue_btn_layout)
                }else{
                    binding.activityEducationSaveBtn.isClickable = false
                    binding.activityEducationSaveBtn.setBackgroundResource(R.drawable.basic_gray_btn_layout)
                }
            }
        })
    }

    fun checkInfo() : Boolean{
        return nickState == 1 && teamState == 1 && emailState == 1
    }
}




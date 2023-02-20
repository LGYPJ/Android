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
import android.view.inputmethod.InputMethodManager
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
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.BaseBindingFragment
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.common.INPUT_TEXT_LENGTH_100
import com.garamgaebi.garamgaebi.databinding.FragmentProfileEditBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.ProfileViewModel
import com.jakewharton.rxbinding4.view.clicks
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.concurrent.TimeUnit

class ProfileEditFragment :
    BaseBindingFragment<FragmentProfileEditBinding>(R.layout.fragment_profile_edit) {
    private lateinit var callback: OnBackPressedCallback
    private var bodyPart : MultipartBody.Part? = null

    private val imageResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){ result ->
        Log.d("image_result_code",result.resultCode.toString())

        if(result.resultCode == Activity.RESULT_OK){
            val imageUri = result.data?.data ?: return@registerForActivityResult

            val file = activity?.let { absolutelyPath(imageUri, it) }?.let { File(it) }
            Log.d("image_result_file",file.toString())

            val requestFile = file?.let { RequestBody.create("image/*".toMediaTypeOrNull(), it) }
            Log.d("image_result_request",requestFile.toString())

            bodyPart = requestFile?.let {
                MultipartBody.Part.createFormData("profile", file.name,
                    it
                )
            }
            Log.d("image_result_multipart",bodyPart.toString())

//            val bitmap = context?.let { ImageDecoder.createSource(it.contentResolver,imageUri) }
//                ?.let { ImageDecoder.decodeBitmap(it) }
            //binding.activityEditProfileIvProfile.setImageBitmap(bitmap)
            binding.activityEditProfileIvProfile.setImageResource(R.drawable.basic_gray_border_layout)
            if (file != null) {
                Log.d("image_1_file_name",file.name)
            }
            Log.d("image_2_image_uri",imageUri.toString())
            //Log.d("image_3_image_bitmap",bitmap.toString())

            activity?.let { Glide.with(it).load(imageUri).fitCenter().apply(RequestOptions().override(80,80)).into(binding.activityEditProfileIvProfile) }


            //sendImage(body)

            binding.activityEditProfileIvProfile.setImageURI(imageUri)
            //binding.activityEditProfileIvProfile.setBackgroundResource(imageUri)

            activity?.let { it1 ->
                Glide.with(it1)
                    .load(imageUri.toString())
                    .into(binding.activityEditProfileIvProfile)
            }
            binding.activityEditProfileIvProfile.clipToOutline = true


            Log.d("image_4_resource",binding.activityEditProfileIvProfile.resources.toString())

        }else{
            Log.d("image_4_resourcedd",binding.activityEditProfileIvProfile.resources.toString())

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
    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            var imageUrl = it.data?.data
            Log.d("img","성공")
        }else{
            Log.d("img","실패")
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        Log.d("viewcreated","yes")
        val viewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        binding.setVariable(BR.viewModel,viewModel)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        //val viewModels by viewModels<ProfileViewModel>()


        with(binding) {
            viewModel.nickName.value = GaramgaebiApplication.sSharedPreferences.getString(
                "myNickName",
                "Error"
            )
            viewModel.belong.value = GaramgaebiApplication.sSharedPreferences.getString(
                "myBelong",
                ""
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
                    ""
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
                    ""
                )
            )

            var myProfileImage = GaramgaebiApplication.sSharedPreferences.getString("myImage", "")
            if (myProfileImage != null) {
                if(myProfileImage.isNotEmpty()) {
                    activity?.let { it1 ->
                        Glide.with(it1)
                            .load(myProfileImage)
                            .into(activityEditProfileIvProfile)
                    }
                }
            }
            activityEditProfileIvProfile.clipToOutline = true


        }


        // 유효성 확인
        viewModel.nickName.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel


            if(it.length > 8){
                viewModel.nameState.value = getString(R.string.edit_profile_hint)
                viewModel.nickNameIsValid.value = false
            }else if(it.isNotEmpty()){
                if(it!=null  && it.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝| ]*".toRegex())) {
                    System.out.println("특수 문자가 없습니다.");
                    viewModel.nickNameIsValid.value = true
                }else {
                    System.out.println("특수문자가 있습니다.");
                    viewModel.nameState.value = getString(R.string.wrong_nick_name)
                    viewModel.nickNameIsValid.value = false
                }

            }else if(it.isEmpty()){
                viewModel.nameState.value = getString(R.string.edit_profile_hint)
                viewModel.nickNameIsValid.value = false
            }

            Log.d("profile_nickName_true",viewModel.nickNameIsValid.value.toString())
        })
        viewModel.belong.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel
            viewModel.belongIsValid.value = it.length < 18 && it.isNotEmpty()
            Log.d("profile_belong_true",viewModel.belongIsValid.value.toString())
        })
        viewModel.email.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            //email 유효성 검사 부분
            viewModel.emailIsValid.value = Patterns.EMAIL_ADDRESS.matcher(it).matches()
            Log.d("profile_email_true",viewModel.emailIsValid.value.toString())
        })

        viewModel.intro.observe(viewLifecycleOwner, Observer {
            binding.viewModel = viewModel

            viewModel.introIsValid.value = it.length < INPUT_TEXT_LENGTH_100

            Log.d("profile_intro_true",viewModel.introIsValid.value.toString())
        })
//프로필 사진 변경을 위한,,,
        disposables
            .add(
                binding
                    .activityEditProfileIvProfile
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        selectGallery()
                        Log.d("test---","아얏")
                    }, { it.printStackTrace() })
            )

        //편집 정보 저장하기 버튼 클릭이벤트
        disposables
            .add(
                binding
                    .activityEducationSaveBtn
                    .clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        //회원정보 편집 저장 기능 추가
                        Log.d("image_edit_ss",bodyPart.toString())
                        //viewModel.img = null
                        Log.d("image_edit_ss","??")
                        viewModel.getCheckEditProfileInfo(myMemberIdx, null)
                        (activity as ContainerActivity).onBackPressed()
                    }, { it.printStackTrace() })
            )


        binding.containerLayout.setOnTouchListener(View.OnTouchListener { v, event ->
            hideKeyboard()
            false
        })

    }
    private fun hideKeyboard() {
        Log.d("image_edit_ss","??ㅈㅈ")

        if (activity != null && requireActivity().currentFocus != null) {
            Log.d("image_edit_ss","??22")

            // 프래그먼트기 때문에 getActivity() 사용
            val inputManager: InputMethodManager =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputManager.hideSoftInputFromWindow(
                requireActivity().currentFocus!!.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS
            )
        }
    }
}




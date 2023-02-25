package com.garamgaebi.garamgaebi.src.main.profile

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ContentUris
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.database.Cursor
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import com.garamgaebi.garamgaebi.src.main.home.FileUpLoad
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.databinding.FragmentProfileEditBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.viewModel.ProfileViewModel
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.coroutines.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.TimeUnit

class ProfileEditFragment :
    BaseBindingFragment<FragmentProfileEditBinding>(R.layout.fragment_profile_edit) {

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    private val TAG = "TAG-EDIT-CP"

    override fun onPause() {
        GaramgaebiApplication.sSharedPreferences
            .edit().putString("myNickName", viewModel.nickName.value)
            .putString("myBelong", viewModel.belong.value)
            .putString("myEmail", viewModel.email.value)
            .putString("myIntro", viewModel.intro.value)
            .apply()

        //사진 편집 후 중단된 경우
        if(!GaramgaebiApplication.sSharedPreferences.getBoolean("EditImage",false)){
            GaramgaebiApplication.sSharedPreferences
                .edit().putString("myImage", viewModel.image.value)
                .apply()
        }
        Log.e(TAG, "onpause")

        super.onPause()
    }
    @SuppressLint("ClickableViewAccessibility", "UseRequireInsteadOfGet", "WrongThread",
        "SuspiciousIndentation"
    )
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(TAG, "viewcreated")

        binding.setVariable(BR.viewModel, viewModel)
        binding.lifecycleOwner = this

        with(binding) {
            viewModel!!.nickName.value = GaramgaebiApplication.sSharedPreferences.getString(
                "myNickName",
                "Error"
            )
            viewModel!!.belong.value = GaramgaebiApplication.sSharedPreferences.getString(
                "myBelong",
                ""
            )
            viewModel!!.email.value = GaramgaebiApplication.sSharedPreferences.getString(
                "myEmail",
                "Error"
            )
            viewModel!!.intro.value = GaramgaebiApplication.sSharedPreferences.getString(
                "myIntro",
                ""
            )
            viewModel!!.image.value = GaramgaebiApplication.sSharedPreferences.getString(
                "myImage",
                "Error"
            )
            Log.d("image_viewModel",viewModel!!.image.value.toString())

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
            var myProfileImage = GaramgaebiApplication.sSharedPreferences.getString("myImage", "error")
            var editImage = GaramgaebiApplication.sSharedPreferences.getBoolean("EditImage", false)

            if (myProfileImage !="error" && myProfileImage != null && !editImage) {
                    CoroutineScope(Dispatchers.Main).launch {
                        val bitmap = withContext(Dispatchers.IO) {
                            GaramgaebiFunction.ImageLoader.loadImage(myProfileImage)
                        }
                        binding.activityEditProfileIvProfile.setImageBitmap(bitmap)
                    }
           }else if(editImage){
                if (FileUpLoad.getFileToUpLoad().isNotEmpty()) {
                    Log.e(TAG, FileUpLoad.getFileToUpLoad())
                    binding.activityEditProfileIvProfile.setImageURI(Uri.fromFile(File(FileUpLoad.getFileToUpLoad())))
                } else {
                    Log.e(TAG, "image Empty")
                }
            } else {

            }
        }

        with(viewModel) {

            // 유효성 확인
            nickName.observe(viewLifecycleOwner, Observer {
                binding.viewModel = viewModel

                if (it.length > 8) {
                    nameState.value = getString(R.string.edit_profile_hint)
                    nickNameIsValid.value = false
                } else if (it.isNotEmpty()) {
                    if (it != null && it.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝| ]*".toRegex())) {
                        System.out.println("특수문자가 없습니다.");
                        nickNameIsValid.value = true
                    } else {
                        System.out.println("특수문자가 있습니다.");
                        nameState.value = getString(R.string.wrong_nick_name)
                        nickNameIsValid.value = false
                    }
                    if (it.toCharArray()[0] == ' ') {
                        nickNameIsValid.value = false
                    }

                } else if (it.isEmpty()) {
                    nameState.value = getString(R.string.edit_profile_hint)
                    nickNameIsValid.value = false
                }

                Log.d("profile_nickName_true", nickNameIsValid.value.toString())
            })
                belong.observe(viewLifecycleOwner, Observer {
                binding.viewModel = viewModel
                belongIsValid.value = it.length < 19
                GaramgaebiFunction().checkFirstChar(belongIsValid, it)
                Log.d("profile_belong_true", belongIsValid.value.toString())
            })
            email.observe(viewLifecycleOwner, Observer {
                binding.viewModel = viewModel

                //email 유효성 검사 부분
                viewModel.emailIsValid.value = Patterns.EMAIL_ADDRESS.matcher(it).matches()
                Log.d("profile_email_true", emailIsValid.value.toString())
            })

            intro.observe(viewLifecycleOwner, Observer {
                binding.viewModel = viewModel

                introIsValid.value = (it.length < INPUT_TEXT_LENGTH_100)
                GaramgaebiFunction().checkFirstChar(introIsValid, it)

                Log.d("profile_intro_true", introIsValid.value.toString())
            })

            profileEdit.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel

                if (profileEdit.value?.result?.memberIdx == myMemberIdx){
                    (activity as ContainerActivity).onBackPressed()
                }

            }
        }

        //프로필 사진 변경을 위한 클릭리스너
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
                        var editImage = GaramgaebiApplication.sSharedPreferences.getBoolean("EditImage", false)

                        CoroutineScope(Dispatchers.Main).launch {
                            val job1 = async(Dispatchers.IO) {

                                if (editImage) {

                                    val requestFile = FileUpLoad.getFileToUpLoad()
                                        .toRequestBody("multipart/form-data".toMediaTypeOrNull())
                                    val body = MultipartBody.Part.createFormData(
                                        "image",
                                        FileUpLoad.getFileToUpLoad() + ".png",
                                        requestFile
                                    )
                                    Log.d("image_edit_ss", body.toString())


                                    var inputStream: InputStream? = null
                                    try {
                                        inputStream =
                                            context?.contentResolver?.openInputStream(
                                                Uri.fromFile(
                                                    File(
                                                        FileUpLoad.getFileToUpLoad()
                                                    )
                                                )
                                            )!!
                                    } catch (e: IOException) {
                                        e.printStackTrace();
                                    }
                                    var bitmap = BitmapFactory.decodeStream(inputStream);
                                    var byteArrayOutputStream: ByteArrayOutputStream? =
                                        ByteArrayOutputStream()
                                    bitmap.compress(
                                        Bitmap.CompressFormat.JPEG,
                                        20,
                                        byteArrayOutputStream
                                    )

                                    var requestBody = byteArrayOutputStream?.let {
                                        RequestBody.create(
                                            "multipart/form-data".toMediaTypeOrNull(),
                                            it.toByteArray()
                                        )
                                    };
                                    var uploadFile = requestBody?.let {
                                        MultipartBody.Part.createFormData(
                                            "image", FileUpLoad.getFileToUpLoad() + ".png",
                                            it
                                        )
                                    }
                                        viewModel.getCheckEditProfileInfo(myMemberIdx, uploadFile)
                                        1
                                } else {
                                        viewModel.getCheckEditProfileInfo(myMemberIdx, null)
                                        1
                                }
                            }

                            val result1 = job1.await()
                            Toast.makeText(binding.root.context, "저장 완료", Toast.LENGTH_SHORT).show()
                            // (activity as ContainerActivity).onBackPressed()
                        }

                    }, { it.printStackTrace() })
            )



        binding.containerLayout.setOnTouchListener(View.OnTouchListener { v, event ->
            hideKeyboard()
            Log.d("image_source",binding.activityEditProfileIvProfile.resources.toString())
            false
        })
//        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
//            onShowKeyboard = { keyboardHeight ->
//                binding.svRoot.run {
//                    smoothScrollTo(scrollX, scrollY + keyboardHeight)
//                }
//            })

    }
    override fun onDestroy() {
        super.onDestroy()
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private val imageResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val imageUri = result.data?.data ?: return@registerForActivityResult
            imageUri.scheme?.let {
                /**
                 * imageUri 가 content:// 스킴으로 로 시작하는 경우(대부분 삼성폰, 경우에 따라서는 "file://" 넘어오는 단말기들이 존재
                 */
                findImageFileNameFromUri(imageUri)

                /**
                 * 사용자가 갤러리에서 이미지를 선택했다면
                 */
                GaramgaebiApplication.sSharedPreferences
                    .edit().putBoolean("EditImage", true)
                    .apply()

            }
        }
    }

    /**
     * 갤러리에서 사용자가 선택한 이미지의 절대경로를 얻어오는 함수
     */
    @SuppressLint("Range", "Recycle")
    private fun findImageFileNameFromUri(tempUri: Uri): Boolean {
        var flag = false
        //실제 Image 파일이 위치하는 곳(절대디렉토리)
        val imageDBColumn = arrayOf("_data")
        val cursor: Cursor?
        // try {
        //Primary Key 값을 추출
        val imagePK = ContentUris.parseId(tempUri).toString()
        //Image DB에 쿼리를 날린다.
        cursor = requireActivity().contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            imageDBColumn,
            MediaStore.Images.Media._ID + "=?", arrayOf(imagePK),
            null, null
        )
        cursor?.let {
            it.moveToFirst()
            FileUpLoad.setFileToUpLoad(it.getString(it.getColumnIndex("_data")))
            flag = true
        } ?: run {
            flag = false
        }
        return flag
    }

    companion object {
        const val REQ_GALLERY = 1
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private fun selectGallery() {
        val writePermission = requireActivity().let {
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

        if (writePermission == PackageManager.PERMISSION_DENIED ||
            readPermission == PackageManager.PERMISSION_DENIED
        ) {
            activity?.let {
                ActivityCompat.requestPermissions(
                    it,
                    arrayOf(
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        android.Manifest.permission.READ_EXTERNAL_STORAGE
                    ),
                    REQ_GALLERY
                )
            }
        } else {

            val target = Intent(Intent.ACTION_PICK)
            target.setDataAndType(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                "image/*"
            )
            imageResult.launch(target)
        }


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
}




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
import android.graphics.Matrix
import android.graphics.Rect
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.exifinterface.media.ExifInterface
import androidx.lifecycle.ViewModelProvider
import com.garamgaebi.garamgaebi.BR
import com.garamgaebi.garamgaebi.R
import com.garamgaebi.garamgaebi.common.*
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.myMemberIdx
import com.garamgaebi.garamgaebi.databinding.FragmentProfileEditBinding
import com.garamgaebi.garamgaebi.src.main.ContainerActivity
import com.garamgaebi.garamgaebi.src.main.home.FileUpLoad
import com.garamgaebi.garamgaebi.util.LoadingDialog
import com.garamgaebi.garamgaebi.viewModel.ProfileViewModel
import com.jakewharton.rxbinding4.view.clicks
import kotlinx.coroutines.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.util.concurrent.TimeUnit
/*
프로필 편집 Fragment - ContainerActivity

프로필 편집
사진 편집

 */
class ProfileEditFragment :
    BaseBindingFragment<FragmentProfileEditBinding>(R.layout.fragment_profile_edit) {

    private val viewModel: ProfileViewModel by lazy {
        ViewModelProvider(this)[ProfileViewModel::class.java]
    }

    private val TAG = "TAG-EDIT-CP"
    lateinit var mLoadingDialog: LoadingDialog

    override fun onPause() {
        CoroutineScope(Dispatchers.Main).launch {
            with(viewModel){
                nickName.value?.let {
                    GaramgaebiApplication().saveStringToDataStore("myNickName",
                        it
                    )
                }
                belong.value?.let { GaramgaebiApplication().saveStringToDataStore("myBelong", it) }
                email.value?.let { GaramgaebiApplication().saveStringToDataStore("myEmail", it) }
                intro.value?.let {
                    GaramgaebiApplication().saveStringToDataStore("profileEmail",
                        it
                    )
                }
            }
        }

        //사진 편집 후 중단된 경우
        var editImageCheck = false
        runBlocking {
            editImageCheck = GaramgaebiApplication().loadBooleanData("EditImage") == true
            }
        if(!editImageCheck){
            runBlocking {
                viewModel.image.value?.let {
                    Log.d("짱구","사진받")
                    GaramgaebiApplication().saveStringToDataStore("myImage",
                        it
                    )
                }
            }
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

        binding.svRoot.isSmoothScrollingEnabled = false
        with(binding) {
            runBlocking {
                viewModel!!.nickName.value = GaramgaebiApplication().loadStringData("myNickName").toString()
                viewModel!!.email.value = GaramgaebiApplication().loadStringData("myEmail").toString()
                viewModel!!.image.value = GaramgaebiApplication().loadStringData("myImage").toString()
                Log.d("사진 널값", GaramgaebiApplication().loadStringData("myImage").toString())

                //viewModel!!.intro.value?.let { Log.e(TAG, it) }
                if (GaramgaebiApplication().loadBooleanData("myIntroNull") == true) {
                    viewModel?.intro?.value = ""
                }else{
                    viewModel!!.intro.value = GaramgaebiApplication().loadStringData("myIntro")

                }
                if (GaramgaebiApplication().loadBooleanData("myBelongNull") == true) {
                    viewModel?.belong?.value = ""
                }else{
                    viewModel!!.belong.value = GaramgaebiApplication().loadStringData("myBelong")
                }
            }



            Log.d("image_viewModel",viewModel!!.image.value.toString())

            fragmentEditProfileEtNick.setText(
                viewModel!!.nickName.value
            )
            fragmentEditProfileEtTeam.setText(
                viewModel!!.belong.value
            )
            fragmentEditProfileEtEmail.setText(
                viewModel!!.email.value
            )
            fragmentEditProfileEtIntro.setText(
                viewModel!!.intro.value
            )

            var myProfileImage: String?
            var editImage: Boolean

            runBlocking {
                myProfileImage = GaramgaebiApplication().loadStringData("myImage")
                editImage = GaramgaebiApplication().loadBooleanData("EditImage") == true
            }

            if (myProfileImage != null && !editImage) {
                    CoroutineScope(Dispatchers.Main).launch {
                        val bitmap = withContext(Dispatchers.IO) {
                            myProfileImage?.let { GaramgaebiFunction.ImageLoader.loadImage(it) }
                        }
                        binding.fragmentEditProfileIvProfile.setImageBitmap(bitmap)
                    }
           }else if(editImage){
                if (FileUpLoad.getFileToUpLoad().isNotEmpty()) {
                    Log.e(TAG, FileUpLoad.getFileToUpLoad())
                    binding.fragmentEditProfileIvProfile.setImageURI(Uri.fromFile(File(FileUpLoad.getFileToUpLoad())))
                } else {
                    Log.e(TAG, "image Empty")
                }
            }else{

            }
        }

        with(viewModel) {

            // 유효성 확인

            //닉네임 유효성확인
            nickName.observe(viewLifecycleOwner) {

                if (it.length > 8) {
                    nameState.value = getString(R.string.edit_profile_hint)
                    nickNameIsValid.value = false
                } else if (it.isNotEmpty()) {
                    if (it != null && it.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝| ]*".toRegex())) {
                        println("특수문자가 없습니다.");
                        nickNameIsValid.value = true
                    } else {
                        println("특수문자가 있습니다.");
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
            }
            //소속 유효성확인
                belong.observe(viewLifecycleOwner) {

                    if (it != null) {
                        belongIsValid.value = it.length < 19
                        GaramgaebiFunction().checkFirstChar(belongIsValid, it)
                        Log.d("profile_belong_true", "not null")
                    } else {
                        belongIsValid.value = true
                    }
                    Log.d("profile_belong_true", belongIsValid.value.toString())
                }

            //이메일 유효성확인
            email.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel

                //email 유효성 검사 부분
                viewModel.emailIsValid.value = Patterns.EMAIL_ADDRESS.matcher(it).matches()
                Log.d("profile_email_true", emailIsValid.value.toString())
            }

            //소개 유효성확인
            intro.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                if (it != null) {
                    introIsValid.value = (it.length < INPUT_TEXT_LENGTH_100)
                    GaramgaebiFunction().checkFirstChar(introIsValid, it)
                    Log.d("profile_intro_true", "not null")
                } else {
                    introIsValid.value = true
                }

                Log.d("profile_intro_true", introIsValid.value.toString())
            }

            //편집버튼 클릭
            profileEdit.observe(viewLifecycleOwner) {
                binding.viewModel = viewModel
                if (profileEdit.value?.result?.memberIdx == myMemberIdx){
                    GaramgaebiApplication.getProfile = true
                    if (mLoadingDialog.isShowing) {
                        mLoadingDialog.dismiss()
                    }
                    (activity as ContainerActivity).onBackPressed()
                }else{
                    (requireActivity() as ContainerActivity).networkAlertDialog()
                }
            }
        }

        //프로필 사진 변경을 위한 클릭리스너
        disposables
            .add(
                binding
                    .fragmentEditProfileIvProfile
                    .clicks()
                    .throttleFirst(300, TimeUnit.MILLISECONDS)
                    .subscribe({
                        selectGallery()
                        Log.d("짱구","편집 버튼 클릭")
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

                        if((requireActivity() as ContainerActivity).networkValid.value == true) {
                            var editImage = false

                            runBlocking {
                                editImage =
                                    GaramgaebiApplication().loadBooleanData("EditImage") == true
                            }
                            CoroutineScope(Dispatchers.Main).launch {
                                mLoadingDialog = LoadingDialog(requireActivity())
                                mLoadingDialog.show()
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
                                            e.printStackTrace()
                                        }
                                        val bitmap = BitmapFactory.decodeStream(inputStream)
                                        val bmRotated = rotateBitmap(bitmap, IMAGE_ORIENTATION)
                                        val byteArrayOutputStream =
                                            ByteArrayOutputStream()
                                        bmRotated?.compress(
                                            Bitmap.CompressFormat.JPEG,
                                            20,
                                            byteArrayOutputStream
                                        )

                                        val requestBody = byteArrayOutputStream.let {
                                            RequestBody.create(
                                                "multipart/form-data".toMediaTypeOrNull(),
                                                it.toByteArray()
                                            )
                                        };
                                        val uploadFile = requestBody.let {
                                            MultipartBody.Part.createFormData(
                                                "image", FileUpLoad.getFileToUpLoad() + ".jpeg",
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

                                job1.await()
                                Toast.makeText(binding.root.context, "저장 완료", Toast.LENGTH_SHORT)
                                    .show()
                                // (activity as ContainerActivity).onBackPressed()
                            }
                    }else {
                            (requireActivity() as ContainerActivity).networkAlertDialog()
                    }

                    }, { it.printStackTrace() })
            )



        binding.containerLayout.setOnTouchListener { _, _ ->
            hideKeyboard()
            Log.d("image_source", binding.fragmentEditProfileIvProfile.resources.toString())
            false
        }
        keyboardVisibilityUtils = KeyboardVisibilityUtils(requireActivity().window,
            onShowKeyboard = { keyboardHeight ->
                binding.svRoot.run {
                    smoothScrollTo(scrollX, scrollY + keyboardHeight)

                }
                  binding.activityEducationSaveBtn.visibility = View.GONE
            },
            onHideKeyboard = {
                //  binding.fragmentEducationSaveBtn.visibility = View.VISIBLE
            }
        )
        view.viewTreeObserver.addOnGlobalLayoutListener {
            val rect = Rect()
            view.getWindowVisibleDisplayFrame(rect)

            val screenHeight = view.rootView.height
            val keypadHeight = screenHeight - rect.bottom

            if (keypadHeight < screenHeight * 0.15) {
                // 키보드가 완전히 내려갔음을 나타내는 동작을 구현합니다.
                binding.activityEducationSaveBtn.postDelayed({
                    binding.activityEducationSaveBtn.visibility = View.VISIBLE
                }, 0)

            }
        }

    }

    private fun rotateBitmap(bitmap: Bitmap, orientation: Int): Bitmap? {
        val matrix = Matrix()
        Log.d("orientation 4",orientation.toString())
        when (orientation) {
            ExifInterface.ORIENTATION_NORMAL -> return bitmap
            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> matrix.setScale(-1F, 1F)
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.setRotate(180F)
            ExifInterface.ORIENTATION_FLIP_VERTICAL -> {
                matrix.setRotate(180F)
                matrix.postScale(-1F, 1F)
            }
            ExifInterface.ORIENTATION_TRANSPOSE -> {
                matrix.setRotate(90F)
                matrix.postScale(-1F, 1F)
            }
            ExifInterface.ORIENTATION_ROTATE_90 -> {
                Log.d("orientation 5","hh")
                matrix.setRotate(90F)
            }
            ExifInterface.ORIENTATION_TRANSVERSE -> {
                matrix.setRotate(-90F)
                matrix.postScale(-1F, 1F)
            }
            ExifInterface.ORIENTATION_ROTATE_270 -> matrix.setRotate(-90F)
            else -> return bitmap
        }
        Log.d("orientation 3","gg")
        return try {
            val bmRotated =
                Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
            bitmap.recycle()
            Log.d("orientation 2","gg")
            bmRotated
        } catch (e: OutOfMemoryError) {
            e.printStackTrace()
            null
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    private val imageResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        Log.d("짱구", "진짜 result1"+result.resultCode.toString())
        when (result.resultCode) {
            Activity.RESULT_OK -> {
                Log.d("짱구", "진짜 result2"+result.resultCode.toString())

                val imageUri = result.data?.data ?: return@registerForActivityResult
                imageUri.scheme?.let {
                    /**
                     * imageUri 가 content:// 스킴으로 로 시작하는 경우(대부분 삼성폰, 경우에 따라서는 "file://" 넘어오는 단말기들이 존재
                     */
                    findImageFileNameFromUri(imageUri)

                    /**
                     * 사용자가 갤러리에서 이미지를 선택했다면
                     */
                    // GaramgaebiApplication.sSharedPreferences.edit().putBoolean("EditImage", true).apply()
                    runBlocking {
                        Log.d("짱구","editImage")
                        GaramgaebiApplication().saveBooleanToDataStore("EditImage",true)
                    }
                }
            }
            Activity.RESULT_CANCELED -> {
                Log.d("짱구", "사용자가 이미지 선택을 취소했습니다.")
            }
            else -> {
                Log.d("짱구", "알 수 없는 오류가 발생했습니다.")
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
            var exif: ExifInterface? = null
            try {
                exif = ExifInterface(it.getString(it.getColumnIndex("_data")))
            } catch (e: IOException) {
                e.printStackTrace()
            }
            if (exif != null) {
                IMAGE_ORIENTATION = exif.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED
                )
            }
            flag = true
            Log.d("짱구","선택했다짜")
        } ?: run {
            flag = false
        }
        return flag
    }

    // 권한 요청 결과 처리
    @RequiresApi(Build.VERSION_CODES.P)
    private  val requestPermission = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()) {
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

            Toast.makeText(
                context,
                "권한이 거부되었습니다",
                Toast.LENGTH_SHORT
            ).show()
        }else{
            val target = Intent(Intent.ACTION_PICK)
            target.setDataAndType(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
                "image/*"
            )
            imageResult.launch(target)
        }
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
            requestPermission.launch(arrayOf
                (
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                android.Manifest.permission.READ_EXTERNAL_STORAGE))

        } else {

            val target = Intent(Intent.ACTION_PICK)
            target.setDataAndType(
                MediaStore.Images.Media.INTERNAL_CONTENT_URI,
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




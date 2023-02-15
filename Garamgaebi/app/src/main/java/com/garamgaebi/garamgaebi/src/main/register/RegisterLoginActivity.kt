package com.garamgaebi.garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.garamgaebi.garamgaebi.BuildConfig
import com.garamgaebi.garamgaebi.common.BaseActivity
import com.garamgaebi.garamgaebi.databinding.ActivityRegisterLoginBinding
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.model.AuthErrorCause
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient


class RegisterLoginActivity : BaseActivity<ActivityRegisterLoginBinding>(
    ActivityRegisterLoginBinding::inflate) {
    var socialEmail : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val keyHash = Utility.getKeyHash(this)
        //Log.d("Hash", keyHash)
        /*UserApiClient.instance.logout { error ->
            if (error != null) {
                Toast.makeText(this, "로그아웃 실패 $error", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "로그아웃 성공", Toast.LENGTH_SHORT).show()
            }
            Intent(this, RegisterActivity::class.java).run {
                startActivity(addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            }
            finish()
        }*/
        /*UserApiClient.instance.unlink { error ->
            if (error != null) {
                Toast.makeText(this, "회원 탈퇴 실패 $error", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "회원 탈퇴 성공", Toast.LENGTH_SHORT).show()
                Intent(this, RegisterActivity::class.java).run {
                    startActivity(addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                }
                finish()
            }
        }*/
        binding.fragmentLoginKakao.setOnClickListener {
            kakaoLogin()
        }
    }
    private fun kakaoLogin() {

        // 로그인 정보 확인
        UserApiClient.instance.accessTokenInfo { tokenInfo, error ->
            if (error != null) {
                Log.d( "kakao", "토큰 정보 보기 실패$error")
            }
            else if (tokenInfo != null) {
                Log.d("kakao", "토큰 정보 보기 성공")
                getUserInfo()
            }
        }

        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            Log.e("Callback", "$token")
            if (error != null) {
                when {
                    error.toString() == AuthErrorCause.AccessDenied.toString() -> {
                        Log.d("kakao", "접근이 거부 됨(동의 취소)")
                    }
                    error.toString() == AuthErrorCause.InvalidClient.toString() -> {
                        Log.d( "kakao", "유효하지 않은 앱")
                    }
                    error.toString() == AuthErrorCause.InvalidGrant.toString() -> {
                        Log.d( "kakao", "인증 수단이 유효하지 않아 인증할 수 없는 상태")
                    }
                    error.toString() == AuthErrorCause.InvalidRequest.toString() -> {
                        Log.d( "kakao", "요청 파라미터 오류")
                    }
                    error.toString() == AuthErrorCause.InvalidScope.toString() -> {
                        Log.d( "kakao", "유효하지 않은 scope ID")
                    }
                    error.toString() == AuthErrorCause.Misconfigured.toString() -> {
                        Log.d( "kakao", "설정이 올바르지 않음(android key hash)")
                    }
                    error.toString() == AuthErrorCause.ServerError.toString() -> {
                        Log.d( "kakao", "서버 내부 에러")
                    }
                    error.toString() == AuthErrorCause.Unauthorized.toString() -> {
                        Log.d( "kakao", "앱이 요청 권한이 없음")
                    }
                    else -> { // Unknown
                        Log.d( "kakao", "기타 에러")
                    }
                }
            }
            else if (token != null) {
                Log.d( "kakao", "로그인 성공")
                getUserInfo()
            }
        }

        if(UserApiClient.instance.isKakaoTalkLoginAvailable(this)){
            UserApiClient.instance.loginWithKakaoTalk(this, callback = callback)

        }else{
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }
    private fun getUserInfo() {
        UserApiClient.instance.me { userInfo, error ->
            if (error != null) {
                Log.e("TAG", "사용자 정보 요청 실패", error)
            } else if (userInfo != null) {
                Intent(this, RegisterActivity::class.java).run{
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    putExtra("login",true)
                    putExtra("email", userInfo.kakaoAccount?.email)
                    startActivity(this)
                }
                finish()
            }
        }
    }
}
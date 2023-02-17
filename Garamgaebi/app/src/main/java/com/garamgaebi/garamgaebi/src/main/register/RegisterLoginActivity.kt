package com.garamgaebi.garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
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
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.d("kakao", "회원 탈퇴 실패 $error")
            } else {
                Log.d("kakao", "회원 탈퇴 성공")
                Intent(this, RegisterActivity::class.java).run {
                    startActivity(addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
                }
                finish()
            }
        }
        binding.fragmentLoginKakao.setOnClickListener {
            kakaoLogin()
        }
    }
    private fun kakaoLogin() {

        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit ={token, error->
            if (error != null) {
                Log.e("kakao", "카카오계정으로 로그인 실패 ${error}")
            } else if (token != null) {
                Log.i("kakao", "카카오계정으로 로그인 성공 ${token.accessToken}")
                getUserInfo()
            }
        }
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this){token, error->
                if (error != null) {
                    Log.e("kakao", "카카오톡으로 로그인 실패 ${error}")

                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }

                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this , callback = callback)
                } else if (token != null) {
                    Log.i("kakao", "카카오톡으로 로그인 성공 ${token.accessToken}")
                    getUserInfo()
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }
    private fun getUserInfo() {
        UserApiClient.instance.me { userInfo, error ->
            if (error != null) {
                Log.e("TAG", "사용자 정보 요청 실패 ${error.cause}")
            } else if (userInfo != null) {
                Intent(this, RegisterActivity::class.java).run{
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    putExtra("login",true)
                    putExtra("email", userInfo.kakaoAccount?.email)
                    startActivity(this)
                    finish()
                }
            }
        }
    }
}
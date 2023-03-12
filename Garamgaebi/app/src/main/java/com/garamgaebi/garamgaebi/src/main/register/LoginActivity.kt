package com.garamgaebi.garamgaebi.src.main.register

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.garamgaebi.garamgaebi.common.BaseActivity
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication
import com.garamgaebi.garamgaebi.databinding.ActivityLoginBinding
import com.garamgaebi.garamgaebi.model.LoginRequest
import com.garamgaebi.garamgaebi.src.main.MainActivity
import com.garamgaebi.garamgaebi.viewModel.HomeViewModel
import com.jakewharton.rxbinding4.view.clicks
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.common.util.Utility
import com.kakao.sdk.user.UserApiClient
import io.reactivex.rxjava3.disposables.CompositeDisposable
import java.util.concurrent.TimeUnit


class LoginActivity : BaseActivity<ActivityLoginBinding>(
    ActivityLoginBinding::inflate
) {
    val viewModel by viewModels<HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        GaramgaebiApplication.sSharedPreferences.edit().putString("kakaoToken", "").apply()
        //val keyHash = Utility.getKeyHash(this)
        //Log.d("kakao", "hash $keyHash")
        /*UserApiClient.instance.logout { error ->
            if (error != null) {
                Log.d("kakao", "로그아웃 실패 $error")
            } else {
                Log.d("kakao", "로그아웃 성공")
            }
        }*/
        UserApiClient.instance.unlink { error ->
            if (error != null) {
                Log.d("kakao", "회원 탈퇴 실패 $error")
            } else {
                Log.d("kakao", "회원 탈퇴 성공")
            }
        }
        CompositeDisposable()
            .add(
                binding.fragmentLoginKakao.clicks()
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe({
                        kakaoLogin()
                    }, { it.printStackTrace() })
            )
    }

    private fun kakaoLogin() {
        // 카카오계정으로 로그인 공통 callback 구성
        // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
        val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
            if (error != null) {
                Log.d("kakao", "카카오계정으로 로그인 실패 ${error}")
            } else if (token != null) {
                Log.d("kakao", "카카오계정으로 로그인 성공 ${token.accessToken}")
                postLogin(token.accessToken)
            }
        }
        // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(this)) {
            UserApiClient.instance.loginWithKakaoTalk(this) { token, error ->
                if (error != null) {
                    Log.d("kakao", "카카오톡으로 로그인 실패 ${error}")
                    // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                    // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                    if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                        return@loginWithKakaoTalk
                    }
                    // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                    UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
                } else if (token != null) {
                    Log.d("kakao", "카카오톡으로 로그인 성공 ${token.accessToken}")
                    postLogin(token.accessToken)
                }
            }
        } else {
            UserApiClient.instance.loginWithKakaoAccount(this, callback = callback)
        }
    }

    private fun postLogin(token : String) {
        viewModel.postLogin(
            LoginRequest(
                token,
                GaramgaebiApplication.sSharedPreferences.getString("pushToken", "")!!
            )
        )
        Log.d("pushToken", GaramgaebiApplication.sSharedPreferences.getString("pushToken", "")!!)
        viewModel.login.observe(this, Observer {
            if (it.isSuccess) {
                GaramgaebiApplication.sSharedPreferences.edit()
                    .putString("kakaoToken", token)
                    .putString(GaramgaebiApplication.X_ACCESS_TOKEN, it.result.tokenInfo.accessToken)
                    .putString(GaramgaebiApplication.X_REFRESH_TOKEN, it.result.tokenInfo.refreshToken)
                    .putBoolean("fromLoginActivity", true)
                    .putInt("memberIdx", it.result.tokenInfo.memberIdx)
                    .apply()
                GaramgaebiApplication.myMemberIdx = it.result.tokenInfo.memberIdx
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                startActivity(
                    Intent(this, RegisterActivity::class.java)
                        .putExtra("login", true)
                        .putExtra("kakaoToken", token)
                )
                finish()
            }
        })
    }
}
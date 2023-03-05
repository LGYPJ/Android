package com.garamgaebi.garamgaebi.common

import android.util.Log
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.X_ACCESS_TOKEN
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.X_REFRESH_TOKEN
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.sSharedPreferences
import com.garamgaebi.garamgaebi.model.LoginRequest
import com.garamgaebi.garamgaebi.repository.HomeRepository
import okhttp3.*
import okhttp3.Response
import java.io.IOException
import retrofit2.*

class XAccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = "Bearer " + sSharedPreferences.getString(X_ACCESS_TOKEN, null)
        if (jwtToken != null) {
            builder.addHeader("Authorization", jwtToken)
        }
        val response = chain.proceed(builder.build())
        // access token이 만료된 경우
        if (response.code == 401) {
            val newJwtToken =  refreshToken()
            if (newJwtToken != null) {
                // 갱신된 access token으로 다시 요청을 보냄
                val newBuilder = chain.request().newBuilder()
                    .removeHeader("Authorization")
                    .addHeader("Authorization", newJwtToken)
                return chain.proceed(newBuilder.build())
            }
        }
        return response
    }

    private fun refreshToken(): String? {
        val refreshToken = sSharedPreferences.getString(X_REFRESH_TOKEN, null) ?: return null
        val loginRequest = LoginRequest(sSharedPreferences.getString("socialEmail", "")!!,"")

        // refresh token이 없는 경우 갱신 실패로 처리
        val homeRepository = HomeRepository()
        val call = homeRepository.postLoginForRefresh(loginRequest)

        try {
            val response = call.execute()

            if (response.isSuccessful) {
                val loginResponse = response.body()

                // 새로운 access token 추출
                val newAccessToken = loginResponse?.result!!.accessToken

                // 추출된 access token이 null이 아니면 반환
                if (!newAccessToken.isNullOrEmpty()) {
                    return "Bearer $newAccessToken"
                }
            } else {
                // API 호출이 실패한 경우 로그를 출력
                Log.e("XAccessTokenInterceptor", "Failed to refresh access token. Response code: ${response.code()}")
            }
        } catch (e: IOException) {
            // IOException이 발생한 경우 로그를 출력
            Log.e("XAccessTokenInterceptor", "Failed to refresh access token", e)
        }

        return null
    }

}
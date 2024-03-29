package com.garamgaebi.garamgaebi.common

import android.util.Log
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.X_ACCESS_TOKEN
import com.garamgaebi.garamgaebi.common.GaramgaebiApplication.Companion.X_REFRESH_TOKEN
import com.garamgaebi.garamgaebi.model.AutoLoginRequest
import com.garamgaebi.garamgaebi.repository.HomeRepository
import kotlinx.coroutines.*
import okhttp3.*
import okhttp3.Response
import retrofit2.*
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        var jwtToken = ""

        val getToken = runBlocking {
            jwtToken = "Bearer " + GaramgaebiApplication().loadStringData(X_ACCESS_TOKEN).toString()
        }

        val request = chain.request()

        if (jwtToken != "Bearer ") {
            builder.addHeader("Authorization", jwtToken)
        }

        val response = chain.proceed(builder.build())

        // access token이 만료된 경우
        if (response.code == 401) {
            val newJwtToken = refreshToken()
            if (newJwtToken != null) {
                // 갱신된 access token으로 다시 요청을 보냄
                chain.request().newBuilder()
                    .removeHeader("Authorization")
                    .addHeader("Authorization", newJwtToken)
                response.close()
                return chain.proceed(newRequestWithAccessToken(newJwtToken, request))
            }
        } else {
            // 401 이외의 상태코드의 경우 바로 반환
        }
        return response
    }

    private fun newRequestWithAccessToken(accessToken: String?, request: Request): Request =
        request.newBuilder()
            .header("Authorization", "Bearer $accessToken")
            .build()


    private fun refreshToken(): String? {
        var refreshToken = ""
        runBlocking {
            refreshToken = GaramgaebiApplication().loadStringData(X_REFRESH_TOKEN).toString()
        }
        val autoLoginRequest = AutoLoginRequest(refreshToken)

        // refresh token이 없는 경우 갱신 실패로 처리
        val call = HomeRepository().postLoginForRefresh(autoLoginRequest)

        try {
            val response = call.execute()

            if (response.isSuccessful) {
                val loginResponse = response.body()
                // 새로운 access token 추출
                var newAccessToken = ""
                newAccessToken = loginResponse?.result?.tokenInfo?.accessToken ?: ""


                if (loginResponse != null) {
                    CoroutineScope(Dispatchers.Main).launch {
                        withContext(Dispatchers.IO) { // 비동기 작업 시작
                            GaramgaebiApplication().saveStringToDataStore(
                                X_ACCESS_TOKEN,
                                loginResponse.result.tokenInfo.accessToken
                            )
                            GaramgaebiApplication().saveStringToDataStore(
                                X_REFRESH_TOKEN,
                                loginResponse.result.tokenInfo.refreshToken
                            )
                        } // 결과 대기
                    }
                }

                // 추출된 access token이 null이 아니면 반환
                if (!newAccessToken.isNullOrEmpty()) {


                    return "Bearer $newAccessToken"
                }
            } else {
                // API 호출이 실패한 경우 로그를 출력
               // Log.e("XAccessTokenInterceptor11", "Failed to refresh access token. Response code: ${response.code()}")
            }
        } catch (e: IOException) {
            // IOException이 발생한 경우 로그를 출력
            //Log.e("XAccessTokenInterceptor1122", "Failed to refresh access token", e)
        }

        return null
    }

}
package com.example.template.garamgaebi.common

import com.example.template.garamgaebi.common.GaramgaebiApplication.Companion.X_ACCESS_TOKEN
import com.example.template.garamgaebi.common.GaramgaebiApplication.Companion.sSharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = "Bearer " + sSharedPreferences.getString(X_ACCESS_TOKEN, null)
        if (jwtToken != null) {
            builder.addHeader("Authorization", jwtToken)
        }
        return chain.proceed(builder.build())

        /*val jwtToken = "Bearer " + sSharedPreferences.getString(X_ACCESS_TOKEN, null)
        val request = chain.request().newBuilder()
                .addHeader("Authorization", jwtToken)
                .build()
        //return chain.proceed(builder.build())
        val response = chain.proceed(request)
        if (response.code == 2007) {
            val refreshToken = "Bearer " + sSharedPreferences.getString(X_REFRESH_TOKEN, null)
            val refreshRequest = chain.request().newBuilder()
                .addHeader("Authorization", refreshToken)
                .build()
            return chain.proceed(refreshRequest)
        }
        return response*/
    }
}
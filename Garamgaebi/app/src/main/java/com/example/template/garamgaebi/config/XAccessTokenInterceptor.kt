package com.example.template.garamgaebi.config

import androidx.appcompat.app.AppCompatActivity
import com.example.template.garamgaebi.config.GaramgaebiApplication.Companion.X_ACCESS_TOKEN
import com.example.template.garamgaebi.config.GaramgaebiApplication.Companion.sSharedPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.io.IOException

class XAccessTokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder: Request.Builder = chain.request().newBuilder()
        val jwtToken: String? = "Bearer " + sSharedPreferences.getString("login",null)
        if (jwtToken != null) {
            builder.addHeader("Authorization", jwtToken)
        }
        /*if (jwtToken != null) {
            builder.addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlaGR3bHNkbHdrZDIyQGdhY2hvbi5hYy5rciIsImF1dGgiOiJST0xFX1VTRVIiLCJleHAiOjE2NzU0OTczNTB9.D9swvgBggUYfDdkD6XFLy8GzsonF4NpqIxUViBLvJ68")
        }*/
        return chain.proceed(builder.build())
    }
}
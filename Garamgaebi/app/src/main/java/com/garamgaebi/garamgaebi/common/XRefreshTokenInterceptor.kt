package com.garamgaebi.garamgaebi.common

import okhttp3.Interceptor
import okhttp3.Response

class XRefreshTokenInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request();
        val response = chain.proceed(request);

        when (response.code) {
            400 -> {
                //Show Bad Request Error Message
            }
            401 -> {
                //Show UnauthorizedError Message
            }

            403 -> {
                //Show Forbidden Message
            }

            404 -> {
                //Show NotFound Message
            }

            // ... and so on

        }
        return response    }
}
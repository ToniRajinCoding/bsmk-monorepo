package com.example.besokmasak.core.data.source

import okhttp3.Interceptor
import okhttp3.Response

class BearerAuthInterceptor(private val apikey:String) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $apikey")
            .addHeader("Content-Type", "application/json")
            .build()
        return chain.proceed(request)
    }

}
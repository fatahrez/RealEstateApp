package com.example.guryihii.core.shared.remote

import android.content.SharedPreferences
import com.example.guryihii.core.util.Constants.TOKEN_KEY
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sharedPreferences: SharedPreferences
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString(TOKEN_KEY, null)
        val request = chain.request()
        return if (token.isNullOrEmpty()) {
            chain.proceed(request)
        } else {
            val newRequest = request.newBuilder().header(
                "Authorization", "Bearer $token"
            ).build()
            chain.proceed(request)
        }
    }
}
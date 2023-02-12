package com.example.guryihii.core.shared.remote

import android.content.SharedPreferences
import com.example.guryihii.feature_auth.data.remote.AuthAPI
import com.example.guryihii.feature_auth.data.remote.TokenAuthenticator
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object HttpClient {
    fun setupOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        authInterceptor: AuthInterceptor,
//        authenticator: TokenAuthenticator
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
//            .authenticator(authenticator)
            .build()
    }
}
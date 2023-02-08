package com.example.guryihii.feature_properties.data.remote

import okhttp3.logging.HttpLoggingInterceptor

object HttpLogger {
    fun create(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
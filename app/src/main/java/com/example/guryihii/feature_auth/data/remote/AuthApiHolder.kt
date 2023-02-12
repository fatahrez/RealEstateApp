package com.example.guryihii.feature_auth.data.remote

class AuthApiHolder() {
    companion object {

    }
    private lateinit var authAPI: AuthAPI
    fun apiService(): AuthAPI {
        return authAPI
    }
}
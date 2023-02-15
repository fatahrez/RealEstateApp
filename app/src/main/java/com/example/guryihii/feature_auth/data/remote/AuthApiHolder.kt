package com.example.guryihii.feature_auth.data.remote

import javax.inject.Inject

class AuthApiHolder() {
    companion object {

    }
    @Inject
    lateinit var authAPI: AuthAPI
    fun apiService(): AuthAPI {
        return authAPI
    }
}
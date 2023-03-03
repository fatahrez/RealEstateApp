package com.example.guryihii.core.util

import android.util.Patterns

fun String.split(): String {
    return try {
        val index = this.lastIndexOf(":")
        this.substring(index + 1, this.length)
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}

fun String.isValidEmail(): Boolean {
    return Patterns.EMAIL_ADDRESS.matcher(this).matches()
}
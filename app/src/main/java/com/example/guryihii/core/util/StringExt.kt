package com.example.guryihii.core.util

fun String.split(): String {
    return try {
        val index = this.lastIndexOf(":")
        this.substring(index + 1, this.length)
    } catch (e: Exception) {
        e.printStackTrace()
        this
    }
}
package com.example.guryihii.core.util

import android.content.Context
import android.net.Uri
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.FileNotFoundException

object MultiPartUtil {
    fun loadFileFromContentResolver(
        context: Context,
        fileUri: Uri
    ): MultipartBody.Part {
        val contentResolver = context.contentResolver
        val fileInputStream = contentResolver.openInputStream(fileUri) ?: throw
        FileNotFoundException("File not found: $fileUri")
        val requestBody = RequestBody.create(
            "image/*".toMediaTypeOrNull(),
            fileInputStream.readBytes()
        )
        return MultipartBody.Part.createFormData("file", "filename", requestBody)
    }
}
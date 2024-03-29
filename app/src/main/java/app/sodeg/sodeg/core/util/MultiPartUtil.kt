package app.sodeg.sodeg.core.util

import android.content.Context
import android.net.Uri
import android.util.Log
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.FileNotFoundException

object MultiPartUtil {
    fun loadFileFromContentResolver(
        context: Context,
        fileUri: Uri,
        fileName: String
    ): MultipartBody.Part {
        val contentResolver = context.contentResolver
        val fileInputStream = contentResolver.openInputStream(fileUri) ?: throw
        FileNotFoundException("File not found: $fileUri")
        val requestBody = RequestBody.create(
            "image/*".toMediaTypeOrNull(),
            fileInputStream.readBytes()
        )
        val mimeType = contentResolver.getType(fileUri)
        val parts = mimeType?.split("/")
        val fileExtension = fileUri.lastPathSegment + "." + (parts?.get(1) ?: "jpeg")
        Log.i("TAG", "loadFileFromContentResolver: $fileExtension")
        return MultipartBody.Part.createFormData(fileName, fileExtension, requestBody)
    }
}
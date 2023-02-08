package com.example.guryihii.core.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.IOException
import org.json.JSONObject
import retrofit2.HttpException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): ResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when(throwable) {
                is IOException -> ResultWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val errorResponse = convertErrorBody(throwable)
                    ResultWrapper.GenericError(code, errorResponse)
                }
                else -> {
                    val errorResponse = ErrorResponse(
                        "Couldn't reach server, check your internet connection.",
                        null
                    )
                    ResultWrapper.GenericError(null, errorResponse)
                }
            }
        }
    }
}
fun convertErrorBody(throwable: HttpException): ErrorResponse? = try {
    val nn = throwable.response()?.errorBody()?.string()

    val message = StringBuilder()
    val jsonObj = JSONObject(nn)
    val messageToAppend = if (jsonObj.has("errors")) {
        val cleanJson = jsonObj.getString("errors")
            .replace("null", "\"\"")
        var err: String = cleanJson.replace("[", "")
        err = err.replace("]", "")
        err = err.replace("\"", "")
        err = err.replace(",", "\n")
        err = err.replace("{", "")
        err = err.replace("}", "")
        err.split()
    } else {
        jsonObj.getString("message")
    }

    message.append(messageToAppend)

    ErrorResponse(message.toString(), null)
} catch (exception: java.lang.Exception) {
    exception.printStackTrace()
    null
}
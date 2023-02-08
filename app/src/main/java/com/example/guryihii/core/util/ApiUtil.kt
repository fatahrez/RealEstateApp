package com.example.guryihii.core.util

import android.util.Log
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okio.IOException
import org.json.JSONObject
import retrofit2.HttpException

suspend fun <T> safeApiCall(
    dispatcher: CoroutineDispatcher,
    apiCall: suspend () -> T
): Flow<ResultWrapper<T>> {
    return withContext(dispatcher) {
        flow {
            emit(ResultWrapper.Loading)
            try {
                emit(ResultWrapper.Success(apiCall.invoke()))
            } catch (throwable: Throwable) {
                when(throwable) {
                    is IOException -> emit(ResultWrapper.NetworkError)
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        emit(ResultWrapper.GenericError(code, errorResponse))
                    }
                    else -> {
                        val errorResponse = ErrorResponse(
                            "Couldn't reach server, check your internet connection.",
                            null
                        )
                        emit(ResultWrapper.GenericError(null, errorResponse))
                    }
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
package app.sodeg.sodeg.core.util

sealed class WrapperResource<out T>{
    data class Success<T>(val data: T): WrapperResource<T>()
    data class Error<T>(val errorMessage: String?, val exception: Exception?): WrapperResource<T>()
    object Loading: WrapperResource<Nothing>()
}

sealed class ResultWrapper<out T> {
    data class Success<out T>(val value: T): ResultWrapper<T>()
    data class GenericError(
        val code: Int? = null,
        val error: ErrorResponse? = null
    ): ResultWrapper<Nothing>()
    object NetworkError: ResultWrapper<Nothing>()
    object Loading: ResultWrapper<Nothing>()
}

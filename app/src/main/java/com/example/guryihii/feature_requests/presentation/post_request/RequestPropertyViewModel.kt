package com.example.guryihii.feature_requests.presentation.post_request

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_requests.domain.usecases.PostRequest
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RequestPropertyViewModel @Inject constructor(
    private val postRequest: PostRequest
): ViewModel() {

    private val _state = MutableStateFlow(RequestPropertyState())
    val state: StateFlow<RequestPropertyState> get() = _state

    fun postRequestProperty(
        name: String,
        email: String,
        phoneNumber: String,
        subject: String,
        message: String
    ) {
        viewModelScope.launch {
            postRequest(
                name,
                email,
                phoneNumber,
                subject,
                message
            ).collect { result ->
                when(result) {
                    is ResultWrapper.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            requestProperty = result.value
                        )
                    }
                    is ResultWrapper.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true,
                            requestProperty = null
                        )
                    }
                    is ResultWrapper.NetworkError -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            requestProperty = null,
                            error = "Network Error. Please Check Internet Connection"
                        )
                    }
                    is ResultWrapper.GenericError -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            requestProperty = null,
                            error = result.error?.message
                        )
                    }
                }
            }
        }
    }

}
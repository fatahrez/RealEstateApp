package app.sodeg.sodeg.feature_requests.presentation.post_request

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_requests.domain.usecases.PostRequest
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
                            requestPropertyResponse = result.value,
                            error = null
                        )
                    }
                    is ResultWrapper.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true,
                            requestPropertyResponse = null,
                            error = null
                        )
                    }
                    is ResultWrapper.NetworkError -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            requestPropertyResponse = null,
                            error = "Network Error. Please Check Internet Connection"
                        )
                    }
                    is ResultWrapper.GenericError -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            requestPropertyResponse = null,
                            error = result.error?.message
                        )
                    }
                }
            }
        }
    }

}
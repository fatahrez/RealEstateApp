package com.example.guryihii.feature_requests.presentation.request_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_requests.domain.usecases.GetAllRequests
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class PropertyRequestsViewModel @Inject constructor(
    private val getAllRequests: GetAllRequests
): ViewModel() {

    private val _state = MutableStateFlow(PropertyRequestsState())
    private val state: StateFlow<PropertyRequestsState> get() = _state

    init {
        showAllPropertyRequests()
    }

    private fun showAllPropertyRequests() {
        viewModelScope.launch {
            getAllRequests()
                .onEach { result ->
                    when(result) {
                        is ResultWrapper.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                requestProperties = result.value ?: emptyList()
                            )
                        }
                        is ResultWrapper.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true,
                                requestProperties = emptyList()
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            Log.e("TAG", "showAllPropertyRequests: network error")
                            _state.value = state.value.copy(
                                isLoading = false,
                                requestProperties = emptyList()
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            Log.e("TAG", "showAllPropertyRequests: ${result.error}")
                            _state.value = state.value.copy(
                                isLoading = false,
                                requestProperties = emptyList()
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}
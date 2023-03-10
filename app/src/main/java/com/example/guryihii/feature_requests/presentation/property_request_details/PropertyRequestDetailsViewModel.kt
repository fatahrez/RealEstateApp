package com.example.guryihii.feature_requests.presentation.property_request_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_requests.domain.usecases.GetRequestPropertyDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyRequestDetailsViewModel @Inject constructor(
    private val getRequestPropertyDetails: GetRequestPropertyDetails
): ViewModel() {

    private val _state = MutableStateFlow(PropertyRequestDetailsState())
    val state: StateFlow<PropertyRequestDetailsState> get() = _state

    fun showRequestPropertyDetails(id: Int) {
        viewModelScope.launch {
            getRequestPropertyDetails(id)
                .collect { result ->
                    when(result) {
                        is ResultWrapper.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                requestProperty = result.value ?: null
                            )
                        }
                        is ResultWrapper.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true,
                                requestProperty = null
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                requestProperty = null
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                requestProperty = null
                            )
                        }
                    }
                }
        }
    }

}
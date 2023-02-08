package com.example.guryihii.feature_properties.presentation.property_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.usecases.GetPropertyDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyDetailViewModel @Inject constructor(
    private val getPropertyDetails: GetPropertyDetails
): ViewModel() {

    private var _state = MutableStateFlow(PropertyDetailState())
    val state: StateFlow<PropertyDetailState> get() = _state

    fun showPropertyDetail(slug: String) {
        viewModelScope.launch {
            getPropertyDetails(slug)
                .onEach {result ->
                    when(result) {
                        is ResultWrapper.Success -> {
                            _state.value = state.value.copy(
                                property = result.value ?: null,
                                isLoading = false
                            )
                        }
                        is ResultWrapper.Loading -> {
                            _state.value = state.value.copy(
                                property = null,
                                isLoading = true
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            Log.e("TAG", "Network error: $result")
                            _state.value = state.value.copy(
                                property = null,
                                isLoading = false
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            Log.e("TAG", "error: ${result.error})")
                            _state.value = state.value.copy(
                                property = null,
                                isLoading = false
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }

    }

}
package com.example.guryihii.feature_properties.presentation.property_listing_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.usecases.GetPropertyListingDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyListingDetailsViewModel @Inject constructor(
    private val getPropertyListingDetails: GetPropertyListingDetails
): ViewModel() {

    private val _state = MutableStateFlow(PropertyListingDetailsState())
    val state: StateFlow<PropertyListingDetailsState> get() = _state

    fun showPropertyListingDetails(id: Int) {
        viewModelScope.launch {
            getPropertyListingDetails(id)
                .collect { result ->
                    when(result) {
                        is ResultWrapper.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                propertyListing = result.value ?: null
                            )
                        }
                        is ResultWrapper.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true,
                                propertyListing = null
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            Log.e("TAG", "showPropertyListingDetails: network error")
                            _state.value = state.value.copy(
                                isLoading = false,
                                propertyListing = null
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            Log.e("TAG", "showPropertyListingDetails: ${result.error}")
                            _state.value = state.value.copy(
                                isLoading = false,
                                propertyListing = null
                            )
                        }
                    }
                }
        }
    }
}
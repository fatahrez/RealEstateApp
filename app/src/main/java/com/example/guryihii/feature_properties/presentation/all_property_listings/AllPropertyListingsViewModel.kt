package com.example.guryihii.feature_properties.presentation.all_property_listings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.usecases.GetAllPropertyListing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllPropertyListingsViewModel @Inject constructor(
    private val getAllPropertyListing: GetAllPropertyListing
): ViewModel() {

    private val _state = MutableStateFlow(AllPropertyListingsState())
    val state: StateFlow<AllPropertyListingsState> get() = _state

    init {
        showAllPropertyListings()
    }

    private fun showAllPropertyListings() {
        viewModelScope.launch {
            getAllPropertyListing()
                .onEach { result ->
                    when(result) {
                        is ResultWrapper.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                propertyListings = result.value ?: emptyList()
                            )
                        }
                        is ResultWrapper.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true,
                                propertyListings = emptyList()
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            Log.e("TAG", "showAllPropertyListings: network error")
                            _state.value = state.value.copy(
                                isLoading = false,
                                propertyListings = emptyList()
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            Log.e("TAG", "showAllPropertyListings: ${result.error}")
                            _state.value = state.value.copy(
                                isLoading = false,
                                propertyListings = emptyList()
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}
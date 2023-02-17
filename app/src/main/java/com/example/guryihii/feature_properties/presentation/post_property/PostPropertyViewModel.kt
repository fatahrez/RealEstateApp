package com.example.guryihii.feature_properties.presentation.post_property

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.model.Property
import com.example.guryihii.feature_properties.domain.usecases.PostProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostPropertyViewModel @Inject constructor(
    private val postProperty: PostProperty
): ViewModel() {

    private val _state = MutableStateFlow(PostPropertyState())
    val state: StateFlow<PostPropertyState> get() = _state

    fun postSellerProperty(property: Property) {
        viewModelScope.launch {
            postProperty(property)
                .collect { result ->
                    when(result) {
                        is ResultWrapper.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true
                            )
                        }
                        is ResultWrapper.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                property = property ?: null
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            Log.i("TAG", "postSellerProperty: network error")
                            _state.value = state.value.copy(
                                isLoading = false
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            Log.i("TAG", "postSellerProperty: ${result.error}")
                            _state.value = state.value.copy(
                                isLoading = false
                            )
                        }
                    }
                }
        }
    }

}
package com.example.guryihii.feature_properties.presentation.property_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.usecases.GetAllProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PropertyListViewModel @Inject constructor(
    private val getAllProperties: GetAllProperties
): ViewModel() {

    private val _state = MutableStateFlow<PropertyListState>(PropertyListState())
    val state: StateFlow<PropertyListState> get() = _state

    init {
        showAllProperties()
    }

    fun showAllProperties() {
        viewModelScope.launch {
            getAllProperties()
                .onEach { result ->
                    when(result) {
                        is ResultWrapper.Success -> {
                            _state.value = state.value.copy(
                                properties = result.value ?: emptyList()
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            _state.value = state.value.copy(
                                properties = emptyList()
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            _state.value = state.value.copy(
                                properties = emptyList()
                            )
                        }
                        is ResultWrapper.Loading -> state.value.copy(
                            isLoading = true
                        )
                    }
                }
        }
    }
}
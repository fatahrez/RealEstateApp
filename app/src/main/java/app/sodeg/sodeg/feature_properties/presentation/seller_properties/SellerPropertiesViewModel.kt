package app.sodeg.sodeg.feature_properties.presentation.seller_properties

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_properties.domain.usecases.GetSellerProperties
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellerPropertiesViewModel @Inject constructor(
    private val getSellerProperties: GetSellerProperties
): ViewModel() {

    private val _state = MutableStateFlow(SellerPropertiesState())
    val state: StateFlow<SellerPropertiesState> get() = _state

    init {
        showSellerProperties()
    }

    private fun showSellerProperties() {
        viewModelScope.launch {
            getSellerProperties()
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
                                properties = result.value ?: emptyList()
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            Log.i("TAG", "showSellerProperties: network error")
                            _state.value = state.value.copy(
                                isLoading = false,
                                properties = emptyList()
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            Log.i("TAG", "showSellerProperties: ${result.error}")

                            _state.value = state.value.copy(
                                isLoading = false,
                                properties = emptyList()
                            )
                        }
                    }
                }
        }
    }
}
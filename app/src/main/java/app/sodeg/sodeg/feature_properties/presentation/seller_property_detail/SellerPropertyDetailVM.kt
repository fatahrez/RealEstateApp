package app.sodeg.sodeg.feature_properties.presentation.seller_property_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_properties.domain.usecases.DeleteProperty
import app.sodeg.sodeg.feature_properties.domain.usecases.GetPropertyDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SellerPropertyDetailVM @Inject constructor(
    private val getPropertyDetails: GetPropertyDetails,
    private val deleteProperty: DeleteProperty
): ViewModel() {

    private val _state = MutableStateFlow(SellerPropertyDetailState())
    val state: StateFlow<SellerPropertyDetailState> get() = _state

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

    fun deleteSellerProperty(slug: String) {
        viewModelScope.launch {
            deleteProperty(slug)
                .collect { result ->
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
                }
        }
    }
}
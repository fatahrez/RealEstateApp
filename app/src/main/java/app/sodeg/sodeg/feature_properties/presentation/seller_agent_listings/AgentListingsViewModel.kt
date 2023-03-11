package app.sodeg.sodeg.feature_properties.presentation.seller_agent_listings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_properties.domain.usecases.GetSellerPropertyListing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentListingsViewModel @Inject constructor(
    private val getSellerPropertyListing: GetSellerPropertyListing
): ViewModel() {

    private val _state = MutableStateFlow(AgentListingsState())
    val state: StateFlow<AgentListingsState> get() = _state

    init {
        showAgentPropertyListing()
    }

    private fun showAgentPropertyListing() {
        viewModelScope.launch {
            getSellerPropertyListing().onEach { result ->
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
                        Log.e("TAG", "showAgentPropertyListing: network error")
                        _state.value = state.value.copy(
                            isLoading = false,
                            propertyListings = emptyList()
                        )
                    }
                    is ResultWrapper.GenericError -> {
                        Log.e("TAG", "showAgentPropertyListing: ${result.error}")
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
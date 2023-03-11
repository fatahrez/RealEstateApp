package app.sodeg.sodeg.feature_properties.presentation.agent_listings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_properties.domain.usecases.GetAgentPropertyListing
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListingsViewModel @Inject constructor(
    private val agentPropertyListing: GetAgentPropertyListing
): ViewModel() {

    private val _state = MutableStateFlow(MyListingsState())
    val state: StateFlow<MyListingsState> get() = _state

    init {
        showMyListings()
    }

    private fun showMyListings() {
        viewModelScope.launch {
            agentPropertyListing().onEach { result ->
                when(result) {
                    is ResultWrapper.Success -> {
                        _state.value = state.value.copy(
                            propertyListings = result.value ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is ResultWrapper.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultWrapper.NetworkError -> {
                        Log.e("TAG", "showMyListings: network error")
                        _state.value = state.value.copy(
                            isLoading = false,
                            propertyListings = emptyList()
                        )
                    }
                    is ResultWrapper.GenericError -> {
                        Log.e("TAG", "showMyListings: ${result.error}")
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
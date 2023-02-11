package com.example.guryihii.feature_agents.presentation.agent_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_agents.domain.usecases.GetAgentDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentDetailViewModel @Inject constructor(
    private val getAgentDetails: GetAgentDetails
): ViewModel() {

    private val _state = MutableStateFlow(AgentDetailState())
    val state: StateFlow<AgentDetailState> get() = _state

    fun showAgentDetails(id: Int) {
        viewModelScope.launch {
            getAgentDetails(id)
                .onEach { result ->
                    when(result) {
                        is ResultWrapper.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true
                            )
                        }
                        is ResultWrapper.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                agent = result.value ?: null
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            Log.e("TAG", "showAgentDetails: network error")
                            _state.value = state.value.copy(
                                isLoading = false,
                                agent = null
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            Log.e("TAG", "showAgentDetails: ${result.error}")
                            _state.value = state.value.copy(
                                isLoading = false,
                                agent = null
                            )
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }
}
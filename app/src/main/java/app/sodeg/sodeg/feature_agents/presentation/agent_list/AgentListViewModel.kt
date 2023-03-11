package app.sodeg.sodeg.feature_agents.presentation.agent_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_agents.domain.usecases.GetAllAgents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgentListViewModel @Inject constructor(
    private val getAllAgents: GetAllAgents
): ViewModel() {

    private val _state = MutableStateFlow(AgentListState())
    val state: StateFlow<AgentListState> get() = _state

    init {
        showAllAgents()
    }

    fun showAllAgents() {
        viewModelScope.launch {
            getAllAgents()
                .onEach {result ->
                    when(result) {
                        is ResultWrapper.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true
                            )
                        }
                        is ResultWrapper.Success -> {
                            Log.i("TAG", "showAllAgents: $result")
                            _state.value = state.value.copy(
                                agents = result.value ?: emptyList(),
                                isLoading = false
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            Log.i("TAG", "showAllAgents: ${result.error}")
                            _state.value = state.value.copy(
                                agents = emptyList(),
                                isLoading = false
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            Log.i("TAG", "showAllAgents: network error")
                            _state.value = state.value.copy(
                                agents = emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
}
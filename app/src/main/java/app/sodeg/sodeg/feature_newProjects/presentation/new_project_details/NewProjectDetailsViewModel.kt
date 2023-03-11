package app.sodeg.sodeg.feature_newProjects.presentation.new_project_details

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_newProjects.domain.usecases.GetNewProjectDetails
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewProjectDetailsViewModel @Inject constructor(
    private val getNewProjectDetails: GetNewProjectDetails
): ViewModel() {

    private val _state = MutableStateFlow(NewProjectDetailsState())
    val state: StateFlow<NewProjectDetailsState> get() = _state

    fun showNewProjectDetails(slug: String) {
        viewModelScope.launch {
            getNewProjectDetails(slug)
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
                                newProject = result.value ?: null
                            )
                        }

                        is ResultWrapper.NetworkError -> {
                            Log.i("TAG", "showNewProjectDetails: network error")
                            _state.value = state.value.copy(
                                isLoading = false,
                                newProject = null
                            )
                        }

                        is ResultWrapper.GenericError -> {
                            Log.i("TAG", "showNewProjectDetails: ${result.error}")
                            _state.value = state.value.copy(
                                isLoading = false,
                                newProject = null
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
}
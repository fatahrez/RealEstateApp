package com.example.guryihii.feature_newProjects.presentation.new_project_list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_newProjects.domain.usecases.GetAllNewProjects
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewProjectListViewModel @Inject constructor(
    private val getAllNewProjects: GetAllNewProjects
): ViewModel() {

    private val _state = MutableStateFlow(NewProjectListState())
    val state: StateFlow<NewProjectListState> get() = _state

    init {
        showAllNewProjectList()
    }

    fun showAllNewProjectList() {
        viewModelScope.launch {
            getAllNewProjects()
                .onEach {result ->
                    when(result) {
                        is ResultWrapper.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true
                            )
                        }
                        is ResultWrapper.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                newProjects = result.value
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            Log.e("TAG", "showAllNewProjectList: network error")
                            _state.value = state.value.copy(
                                isLoading = false,
                                newProjects = emptyList()
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            Log.e("TAG", "showAllNewProjectList: error ${result.error}")
                            _state.value = state.value.copy(
                                isLoading = false,
                                newProjects = emptyList()
                            )
                        }
                    }
                }
                .launchIn(viewModelScope)
        }
    }
}
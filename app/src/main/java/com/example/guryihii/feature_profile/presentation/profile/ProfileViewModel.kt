package com.example.guryihii.feature_profile.presentation.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_profile.domain.usecases.GetProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfile: GetProfile
): ViewModel() {

    private val _state = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> get() = _state

    fun showProfile(){
        viewModelScope.launch {
            getProfile().collect { result ->
                when(result) {
                    is ResultWrapper.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultWrapper.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            profile = result.value
                        )
                    }
                    is ResultWrapper.NetworkError -> {
                        Log.e("TAG", "showProfile: Network error")
                        _state.value = state.value.copy(
                            isLoading = false,
                            profile = null
                        )
                    }
                    is ResultWrapper.GenericError -> {
                        Log.e("TAG", "showProfile: ${result.error}")
                        _state.value = state.value.copy(
                            isLoading = false,
                            profile = null
                        )
                    }
                }
            }
        }
    }

}
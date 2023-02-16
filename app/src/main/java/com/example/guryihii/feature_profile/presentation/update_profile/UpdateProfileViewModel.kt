package com.example.guryihii.feature_profile.presentation.update_profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_profile.domain.model.Profile
import com.example.guryihii.feature_profile.domain.usecases.UpdateProfile
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class UpdateProfileViewModel(
    private val updateProfile: UpdateProfile
): ViewModel() {

    private val _state = MutableStateFlow(UpdateProfileState())
    val state: StateFlow<UpdateProfileState> get() = _state

    fun updateUserProfile(username: String, profile: Profile) {
        viewModelScope.launch {
            updateProfile(username, profile).collect { result ->
                when(result) {
                    is ResultWrapper.Success -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            profileResponse = result.value ?: ""
                        )
                    }
                    is ResultWrapper.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true
                        )
                    }
                    is ResultWrapper.NetworkError -> {
                        Log.e("TAG", "updateUserProfile: network error")
                        _state.value = state.value.copy(
                            isLoading = false
                        )
                    }
                    is ResultWrapper.GenericError -> {
                        Log.e("TAG", "updateUserProfile: ${result.error}")
                        _state.value = state.value.copy(
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

}
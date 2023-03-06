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
import javax.inject.Inject

@HiltViewModel
class UpdateProfileViewModel @Inject constructor(
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
                            profileResponse = result.value ?: null,
                            error = null
                        )
                    }
                    is ResultWrapper.Loading -> {
                        _state.value = state.value.copy(
                            isLoading = true,
                            error = null
                        )
                    }
                    is ResultWrapper.NetworkError -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = "Network error, Please check your internet."
                        )
                    }
                    is ResultWrapper.GenericError -> {
                        _state.value = state.value.copy(
                            isLoading = false,
                            error = result.error?.message
                        )
                    }
                }
            }
        }
    }

}
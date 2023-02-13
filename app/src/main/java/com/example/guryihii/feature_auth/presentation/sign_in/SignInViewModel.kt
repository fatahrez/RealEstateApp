package com.example.guryihii.feature_auth.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_auth.domain.model.User
import com.example.guryihii.feature_auth.domain.usecases.PostSignInUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val postSignInUser: PostSignInUser
): ViewModel() {

    private val _state = MutableStateFlow(SignInState())
    val state: StateFlow<SignInState> get() = _state

    fun signIn(user: User) {
        viewModelScope.launch {
            postSignInUser(user)
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
                                user = result.value ?: null
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                user = null
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                user = null
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
}
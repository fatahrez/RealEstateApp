package app.sodeg.sodeg.feature_auth.presentation.sign_up

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_auth.domain.model.User
import app.sodeg.sodeg.feature_auth.domain.usecases.PostSignUpUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val postSignUpUser: PostSignUpUser
): ViewModel() {

    private val _state = MutableStateFlow(SignUpState())
    val state: StateFlow<SignUpState> get() = _state

    fun signUpUser(user: User) {
        viewModelScope.launch {
            postSignUpUser(user)
                .onEach { result ->
                    when(result) {
                        is ResultWrapper.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true,
                                error = null,
                                user = null
                            )
                        }
                        is ResultWrapper.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                user = result.value,
                                error = null
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            Log.e("TAG", "signUpUser: network error")
                            _state.value = state.value.copy(
                                isLoading = false,
                                user = null,
                                error = "Network Error, Check your internet"
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            Log.i("TAG", "signUpUser: ${result.error}")
                            _state.value = state.value.copy(
                                isLoading = false,
                                user = null,
                                error = result.error?.message
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }
}
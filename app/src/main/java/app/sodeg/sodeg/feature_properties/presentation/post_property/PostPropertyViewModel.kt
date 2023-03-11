package app.sodeg.sodeg.feature_properties.presentation.post_property

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_properties.domain.usecases.PostProperty
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class PostPropertyViewModel @Inject constructor(
    private val postProperty: PostProperty
): ViewModel() {

    private val _state = MutableStateFlow(PostPropertyState())
    val state: StateFlow<PostPropertyState> get() = _state

    fun postSellerProperty(
        advertType: MultipartBody.Part,
        bathrooms: Int,
        bedrooms: Int,
        city: MultipartBody.Part,
        country: MultipartBody.Part,
        coverPhoto: MultipartBody.Part,
        description: MultipartBody.Part,
        photo1: MultipartBody.Part,
        photo2: MultipartBody.Part,
        photo3: MultipartBody.Part,
        photo4: MultipartBody.Part,
        plotArea: Int,
        postalCode: MultipartBody.Part,
        price: Int,
        propertyNumber: Int,
        propertyType: MultipartBody.Part,
        streetAddress: MultipartBody.Part,
        title: MultipartBody.Part,
        totalFloors: Int
    ) {
        viewModelScope.launch {
            postProperty(
                advertType,
                bathrooms,
                bedrooms,
                city,
                country,
                coverPhoto,
                description,
                photo1,
                photo2,
                photo3,
                photo4,
                plotArea,
                postalCode,
                price,
                propertyNumber,
                propertyType,
                streetAddress,
                title,
                totalFloors
            )
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
                                property = result.value ?: null
                            )
                        }
                        is ResultWrapper.NetworkError -> {
                            Log.i("TAG", "postSellerProperty: network error")
                            _state.value = state.value.copy(
                                isLoading = false
                            )
                        }
                        is ResultWrapper.GenericError -> {
                            Log.i("TAG", "postSellerProperty: ${result.error}")
                            _state.value = state.value.copy(
                                isLoading = false
                            )
                        }
                    }
                }.launchIn(viewModelScope)
        }
    }

}
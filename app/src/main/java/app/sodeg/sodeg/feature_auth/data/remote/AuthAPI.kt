package app.sodeg.sodeg.feature_auth.data.remote

import app.sodeg.sodeg.feature_auth.data.remote.dto.UserDTO
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.POST

interface AuthAPI {

    @POST("users/login/")
    suspend fun postUserSignIn(@Body user: UserDTO): UserDTO

    @POST("users/")
    suspend fun postUserSignUp(@Body user: UserDTO): UserDTO

}
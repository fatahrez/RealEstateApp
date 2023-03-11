package app.sodeg.sodeg.feature_profile.domain.usecases

import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_profile.domain.model.Profile
import app.sodeg.sodeg.feature_profile.domain.repository.ProfileRepository
import kotlinx.coroutines.flow.Flow

class GetProfile(
    private val profileRepository: ProfileRepository
) {
    suspend operator fun invoke(): Flow<ResultWrapper<Profile>> {
        return profileRepository.getProfile()
    }
}
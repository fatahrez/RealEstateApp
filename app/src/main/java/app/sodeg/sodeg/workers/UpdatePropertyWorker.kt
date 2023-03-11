package app.sodeg.sodeg.workers

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_properties.domain.usecases.UpdateProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

@HiltWorker
class UpdatePropertyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val updateProperty: UpdateProperty
): CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result {
        val inputData = inputData

        val slug = inputData.getString("slug")
        val title = inputData.getString("title")
        val country = inputData.getString("country")
        val user = inputData.getInt("user", 0)

        val countryRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), country!!)
        val countryPart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "country",
            null,
            countryRequestBody
        )

        updateProperty(
            slug = slug!!,
            title = title,
            country = countryPart,
            user = user
        ).collect { result ->
            when(result) {
                is ResultWrapper.Loading -> {
                    Log.i("TAG", "doWork: loading...")
                }
                is ResultWrapper.Success -> {
                    Log.i("TAG", "doWork: success ${result.value}")
                }
                is ResultWrapper.NetworkError -> {
                    Log.e("TAG", "doWork: network error")
                }
                is ResultWrapper.GenericError -> {
                    Log.i("TAG", "doWork: ${result.error}")
                }
            }
        }

        return Result.success()
    }
}
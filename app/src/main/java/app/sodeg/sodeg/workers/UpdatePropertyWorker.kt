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

//            .putString("description", description)
//            .putString("city", city)
//            .putString("postalCode", postalCode)
//            .putString("streetAddress", streetAddress)
//            .putString("propertyNumber", propertyNumber)
//            .putString("price", price)
//            .putString("plotArea", plotArea)
//            .putString("totalFloors", totalFloors)
//            .putString("bedrooms", bedrooms)

        val slug = inputData.getString("slug")
        val title = inputData.getString("title")
        val country = inputData.getString("country")
        val user = inputData.getInt("user", 0)
        val description = inputData.getString("description")
        val city = inputData.getString("city")
        val postalCode = inputData.getString("postalCode")
        val streetAddress = inputData.getString("streetAddress")
        val propertyNumber = inputData.getInt("propertyNumber", 0)
        val price = inputData.getInt("price", 0)
        val plotArea = inputData.getInt("plotArea", 0)
        val totalFloors = inputData.getInt("totalFloors", 0)
        val bedrooms = inputData.getInt("bedrooms", 0)
        val bathrooms = inputData.getInt("bathrooms", 0)

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
            user = user,
            description = description,
            city = city,
            postalCode = postalCode,
            streetAddress = streetAddress,
            propertyNumber = propertyNumber,
            price = price,
            plotArea = plotArea,
            totalFloors = totalFloors,
            bedrooms = bedrooms,
            bathrooms = bathrooms
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
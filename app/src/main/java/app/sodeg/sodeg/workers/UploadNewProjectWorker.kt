package app.sodeg.sodeg.workers

import android.content.Context
import android.net.Uri
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import app.sodeg.sodeg.core.util.MultiPartUtil
import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_newProjects.domain.usecases.PostNewProject
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

@HiltWorker
class UploadNewProjectWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val postNewProject: PostNewProject
): CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val inputData = inputData

        val name = inputData.getString("name")
        val location = inputData.getString("location")
        val description = inputData.getString("description")
        val price = inputData.getString("price")
        val bedrooms = inputData.getInt("bedrooms", -1)
        val bathrooms = inputData.getInt("bathrooms", -1)
        val squareFeet = inputData.getInt("squareFeet", -1)
        val country = inputData.getString("country")
        val city = inputData.getString("city")
        val constructionStatus = inputData.getString("constructionStatus")
        val completionDate = inputData.getString("completionDate")
        val propertyType = inputData.getString("propertyType")
        val coverPhotoUri = inputData.getString("coverPhoto")
        val photo1Uri = inputData.getString("photo1")
        val photo2Uri = inputData.getString("photo2")


        val coverPhoto = MultiPartUtil.loadFileFromContentResolver(
            applicationContext,
            Uri.parse(coverPhotoUri),
            "cover_photo"
        )

        val photo1 = MultiPartUtil.loadFileFromContentResolver(
            applicationContext,
            Uri.parse(photo1Uri),
            "photo1"
        )

        val photo2 = MultiPartUtil.loadFileFromContentResolver(
            applicationContext,
            Uri.parse(photo2Uri),
            "photo2"
        )

        val nameRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), name!!)
        val namePart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "name",
            null,
            nameRequestBody
        )

        val locationRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), location!!)
        val locationPart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "location",
            null,
            locationRequestBody
        )

        val descriptionRequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            description!!
        )
        val descriptionPart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "description",
            null,
            descriptionRequestBody
        )

        val priceRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), price!!)
        val pricePart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "price",
            null,
            priceRequestBody
        )

        val countryRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), country!!)
        val countryPart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "country",
            null,
            countryRequestBody
        )

        val cityRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), city!!)
        val cityPart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "city",
            null,
            cityRequestBody
        )

        val constructionStatusRequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            constructionStatus!!
        )
        val constructionStatusPart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "construction_status",
            null,
            constructionStatusRequestBody
        )

        val completionDateRequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            completionDate!!
        )
        val completionDatePart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "completion_date",
            null,
            completionDateRequestBody
        )

        val propertyTypeRequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            propertyType!!
        )
        val propertyTypePart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "property_type",
            null,
            propertyTypeRequestBody
        )

        postNewProject(
            namePart,
            locationPart,
            descriptionPart,
            pricePart,
            bedrooms,
            bathrooms,
            squareFeet,
            countryPart,
            cityPart,
            constructionStatusPart,
            completionDatePart,
            propertyTypePart,
            coverPhoto,
            photo1,
            photo2
        ).collect { result ->
            when(result) {
                is ResultWrapper.Success -> {

                }
                is ResultWrapper.Loading -> {

                }
                is ResultWrapper.NetworkError -> {

                }
                is ResultWrapper.GenericError -> {

                }
            }
        }

        return Result.success()
    }

}
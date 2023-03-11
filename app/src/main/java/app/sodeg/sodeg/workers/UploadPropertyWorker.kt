package app.sodeg.sodeg.workers

import android.content.Context
import android.net.Uri
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import app.sodeg.sodeg.core.util.MultiPartUtil
import app.sodeg.sodeg.core.util.ResultWrapper
import app.sodeg.sodeg.feature_properties.domain.usecases.PostProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody

@HiltWorker
class UploadPropertyWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val postProperty: PostProperty
): CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val inputData = inputData

        val advertType = inputData.getString("advertType")
        val bathrooms = inputData.getString("bathrooms")!!.trim().toInt()
        val bedrooms = inputData.getInt("bedrooms", 0)
        val city = inputData.getString("city")
        val country = inputData.getString("country")
        val coverPhotoUri = inputData.getString("coverPhoto")
        val description = inputData.getString("description")
        val photo1Uri = inputData.getString("photo1")
        val photo2Uri = inputData.getString("photo2")
        val photo3Uri = inputData.getString("photo3")
        val photo4Uri = inputData.getString("photo4")
        val plotArea = inputData.getString("plotArea")!!.trim().toInt()
        val postalCode = inputData.getString("postalCode")
        val price = inputData.getString("price")!!.trim().toInt()
        val propertyNumber = inputData.getInt("propertyNumber", 0)
        val propertyType = inputData.getString("propertyType")
        val streetAddress = inputData.getString("streetAddress")
        val title = inputData.getString("title")
        val totalFloors = inputData.getInt("totalFloors", 0)

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

        val photo3 = MultiPartUtil.loadFileFromContentResolver(
            applicationContext,
            Uri.parse(photo3Uri),
            "photo3"
        )

        val photo4 = MultiPartUtil.loadFileFromContentResolver(
            applicationContext,
            Uri.parse(photo4Uri),
            "photo4"
        )

        val countryRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), country!!)
        val countryPart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "country",
            null,
            countryRequestBody
        )

        val advertTypeRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), advertType!!)
        val advertTypePart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "advert_type",
            null,
            advertTypeRequestBody
        )

        val propertyTypeRequestBody = RequestBody.create("text/plain".toMediaTypeOrNull(), propertyType!!)
        val propertyTypePart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "property_type",
            null,
            propertyTypeRequestBody
        )

        val propertyTitleRequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            title!!
        )
        val propertyTitlePart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "title",
            null,
            propertyTitleRequestBody
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

        val cityRequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            city!!
        )
        val cityPart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "city",
            null,
            cityRequestBody
        )

        val postalCodeRequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            postalCode!!
        )
        val postalCodePart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "postal_code",
            null,
            postalCodeRequestBody
        )

        val streetAddressRequestBody = RequestBody.create(
            "text/plain".toMediaTypeOrNull(),
            streetAddress!!
        )
        val streetAddressPart: MultipartBody.Part = MultipartBody.Part.createFormData(
            "street_address",
            null,
            streetAddressRequestBody
        )

        postProperty(
            advertTypePart,
            bathrooms,
            bedrooms,
            cityPart,
            countryPart,
            coverPhoto,
            descriptionPart,
            photo1,
            photo2,
            photo3,
            photo4,
            plotArea,
            postalCodePart,
            price,
            propertyNumber,
            propertyTypePart,
            streetAddressPart,
            propertyTitlePart,
            totalFloors
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
package com.example.guryihii.workers

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.guryihii.core.util.MultiPartUtil
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.usecases.PostProperty
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import okhttp3.MediaType.Companion.parse
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.FileNotFoundException
import javax.inject.Inject

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


        postProperty(
            advertTypePart,
            bathrooms,
            bedrooms,
            city!!,
            countryPart,
            coverPhoto,
            description!!,
            photo1,
            photo2,
            photo3,
            photo4,
            plotArea,
            postalCode!!,
            price,
            propertyNumber,
            propertyTypePart,
            streetAddress!!,
            title!!,
            totalFloors
        ).collect { result ->
            Log.i("TAG", "result $result")
            when(result) {
                is ResultWrapper.Success -> {
                    Log.i("TAG", "doWork: upload ${result.value}")
                }
                is ResultWrapper.Loading -> {
                    Log.i("TAG", "doWork: loading ...")
                }
                is ResultWrapper.NetworkError -> {
                    Log.e("TAG", "doWork: network error")
                }
                is ResultWrapper.GenericError -> {
                    Log.e("TAG", "doWork: ${result.error}", )
                }
            }
        }
        return Result.success()
    }

}
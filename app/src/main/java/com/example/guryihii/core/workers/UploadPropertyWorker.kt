package com.example.guryihii.core.workers

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.guryihii.core.util.MultiPartUtil
import com.example.guryihii.core.util.ResultWrapper
import com.example.guryihii.feature_properties.domain.usecases.PostProperty
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.FileNotFoundException
import javax.inject.Inject

class UploadPropertyWorker(
    private val context: Context,
    workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {

    @Inject
    lateinit var postProperty: PostProperty

    override suspend fun doWork(): Result {
        val inputData = inputData

        val advertType = inputData.getString("advertType")
        val bathrooms = inputData.getString("bathrooms")
        val bedrooms = inputData.getInt("bedrooms", 0)
        val city = inputData.getString("city")
        val country = inputData.getString("country")
        val coverPhotoUri = inputData.getString("coverPhoto")
        val description = inputData.getString("description")
        val photo1Uri = inputData.getString("photo1")
        val photo2Uri = inputData.getString("photo2")
        val photo3Uri = inputData.getString("photo3")
        val photo4Uri = inputData.getString("photo4")
        val plotArea = inputData.getString("plotArea")
        val postalCode = inputData.getString("postalCode")
        val price = inputData.getString("price")
        val propertyNumber = inputData.getString("propertyNumber")
        val propertyType = inputData.getString("propertyType")
        val streetAddress = inputData.getString("streetAddress")
        val title = inputData.getString("title")
        val totalFloors = inputData.getInt("totalFloors", 0)

        val coverPhoto = MultiPartUtil.loadFileFromContentResolver(
            applicationContext,
            Uri.parse(coverPhotoUri)
        )

        val photo1 = MultiPartUtil.loadFileFromContentResolver(
            applicationContext,
            Uri.parse(photo1Uri)
        )

        val photo2 = MultiPartUtil.loadFileFromContentResolver(
            applicationContext,
            Uri.parse(photo2Uri)
        )

        val photo3 = MultiPartUtil.loadFileFromContentResolver(
            applicationContext,
            Uri.parse(photo3Uri)
        )

        val photo4 = MultiPartUtil.loadFileFromContentResolver(
            applicationContext,
            Uri.parse(photo4Uri)
        )

        postProperty(
            advertType!!,
            bathrooms!!,
            bedrooms,
            city!!,
            country!!,
            coverPhoto,
            description!!,
            photo1,
            photo2,
            photo3,
            photo4,
            plotArea!!,
            postalCode!!,
            price!!,
            propertyNumber!!,
            propertyType!!,
            streetAddress!!,
            title!!,
            totalFloors
        ).collect { result ->
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
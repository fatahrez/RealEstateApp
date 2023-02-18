package com.example.guryihii.core.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.guryihii.feature_properties.domain.usecases.PostProperty
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.FileNotFoundException
import javax.inject.Inject

class UploadPropertyWorker(
    private val context: Context,
    workerParams: WorkerParameters
): Worker(context, workerParams) {

    @Inject
    lateinit var postProperty: PostProperty

    override fun doWork(): Result {
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

        val contentResolver = applicationContext.contentResolver

        val coverPhotoInputStream = contentResolver.openInputStream(selectedUris[0]) ?: throw FileNotFoundException("File not found: $fileUri")
        val coverPhotoBytes = coverPhotoInputStream?.readBytes()
        val coverPhotoRequestFile = coverPhotoBytes?.let { it1 ->
            RequestBody
                .create("multipart/form-data".toMediaTypeOrNull(), it1)
        }
        val coverPhoto = coverPhotoRequestFile?.let { it1 ->
            MultipartBody.Part.createFormData("file", "filename",
                it1
            )
        }

        val photo1InputStream = contentResolver.openInputStream(selectedUris[1])
        val photo1Bytes = photo1InputStream?.readBytes()
        val photo1RequestFile = photo1Bytes?.let { it1 ->
            RequestBody
                .create("multipart/form-data".toMediaTypeOrNull(), it1)
        }
        val photo1 = photo1RequestFile?.let { it1 ->
            MultipartBody.Part.createFormData("file", "filename",
                it1
            )
        }

        val photo2InputStream = contentResolver.openInputStream(selectedUris[1])
        val photo2Bytes = photo2InputStream?.readBytes()
        val photo2RequestFile = photo2Bytes?.let { it1 ->
            RequestBody
                .create("multipart/form-data".toMediaTypeOrNull(), it1)
        }
        val photo2 = photo2RequestFile?.let { it1 ->
            MultipartBody.Part.createFormData("file", "filename",
                it1
            )
        }

        val photo3InputStream = contentResolver.openInputStream(selectedUris[1])
        val photo3Bytes = photo3InputStream?.readBytes()
        val photo3RequestFile = photo3Bytes?.let { it1 ->
            RequestBody
                .create("multipart/form-data".toMediaTypeOrNull(), it1)
        }
        val photo3 = photo3RequestFile?.let { it1 ->
            MultipartBody.Part.createFormData("file", "filename",
                it1
            )
        }

        val photo4InputStream = contentResolver.openInputStream(selectedUris[1])
        val photo4Bytes = photo4InputStream?.readBytes()
        val photo4RequestFile = photo4Bytes?.let { it1 ->
            RequestBody
                .create("multipart/form-data".toMediaTypeOrNull(), it1)
        }
        val photo4 = photo4RequestFile?.let { it1 ->
            MultipartBody.Part.createFormData("file", "filename",
                it1
            )
        }

        val uploadData = postProperty()
    }

}
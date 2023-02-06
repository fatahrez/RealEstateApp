package com.example.guryihii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

   @Inject lateinit var propertiesService:PropertiesService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launchWhenStarted {
            val response = propertiesService.getAllProperties()
            Log.i("DATA", response.body()!!.toString())
        }
    }
    interface PropertiesService {
        @GET("http://24.199.124.221/api/properties/all/")
        suspend fun getAllProperties(): Response<NetworkProperties>
    }
}


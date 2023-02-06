package com.example.guryihii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.guryihii.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

   @Inject lateinit var propertiesService:PropertiesService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        refreshData()
        setupListeners()

    }

    private fun refreshData() {
        lifecycleScope.launchWhenStarted {
            val response = propertiesService.getAllProperties()
            binding.propertyImageView.load(
                data = "https://plus.unsplash.com/premium_photo-1661883982941-50af7720a6ff?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80"
            ){
                listener{request, result ->
                    binding.productImageViewLoadingProgressBar.isGone = true
                }
            }
            Log.i("DATA", response.body()!!.toString())
        }
    }

    private fun setupListeners() {
        var publishedStatus = false
        binding.favImageView.setOnClickListener {
            val imageRes = if (publishedStatus){
                R.drawable.favourite
            } else{
                R.drawable.fav
            }
            binding.favImageView.setIconResource(imageRes)
            publishedStatus = !publishedStatus
        }
    }

}


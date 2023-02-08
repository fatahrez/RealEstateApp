package com.example.guryihii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isGone
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.guryihii.Epoxy.PropertyEpoxyController
import com.example.guryihii.databinding.ActivityMainBinding
import com.example.guryihii.model.domain.Property
import com.example.guryihii.model.mapper.PropertyMapper
import com.example.guryihii.model.network.NetworkProperties
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import retrofit2.http.GET
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

   @Inject lateinit var propertiesService:PropertiesService

   @Inject lateinit var propertyMapper: PropertyMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupUI()

        val controller = PropertyEpoxyController()
        binding.epoxyRecyclerView.setController(controller)

        lifecycleScope.launchWhenStarted {
            val response : Response<NetworkProperties> = propertiesService.getAllProperties()
            val domainProperty: List<Property> = response.body()!!.results.map{
                propertyMapper.buildFrom(it)
            }
            controller.setData(domainProperty)

            if(domainProperty.isEmpty()){
                Snackbar.make(binding.root, "Failed to fetch", Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun setupUI() {

    }



}


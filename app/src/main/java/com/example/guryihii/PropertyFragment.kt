package com.example.guryihii

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.guryihii.Epoxy.PropertyEpoxyController
import com.example.guryihii.databinding.FragmentPropertyBinding
import com.example.guryihii.model.domain.Property
import com.example.guryihii.model.mapper.PropertyMapper
import com.example.guryihii.model.network.NetworkProperties
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

@AndroidEntryPoint
class PropertyFragment : Fragment() {

    private var _binding: FragmentPropertyBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var propertiesService:PropertiesService

    @Inject
    lateinit var propertyMapper: PropertyMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPropertyBinding.inflate(inflater, container, false)
        val view = binding.root

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

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
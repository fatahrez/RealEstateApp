package app.sodeg.sodeg.feature_properties.presentation.update_property

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.gone
import app.sodeg.sodeg.core.util.visible
import app.sodeg.sodeg.databinding.FragmentSellerPropertyUpdateBinding
import app.sodeg.sodeg.feature_properties.domain.model.Property
import app.sodeg.sodeg.feature_properties.presentation.property_detail.PropertyDetailViewModel
import app.sodeg.sodeg.workers.UpdatePropertyWorker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class SellerPropertyUpdateFragment : Fragment() {

    private var _binding: FragmentSellerPropertyUpdateBinding? = null
    private val binding: FragmentSellerPropertyUpdateBinding get() = _binding!!

    private val viewModel: PropertyDetailViewModel by viewModels()
    private var slug = ""
    private var user = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSellerPropertyUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        fetchData()
        initViews()
        initListeners()
        observeViewState()
    }

    private fun initViews() {
        with(binding) {
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.propertyType,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                propertyTypeSpinner.adapter = adapter
            }

            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.advertType,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                advertTypeSpinner.adapter = adapter
            }

        }
    }

    private fun observeViewState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    showPropertyDetails(state.property)
                }
            }
        }
    }

    private fun showPropertyDetails(property: Property?) {
        if (property != null) {
            with(binding) {
                titleEditText.setText(property.title)
                descriptionEditText.setText(property.description)
                cityEditText.setText(property.city)
                postalCodeEditText.setText(property.postalCode)
                streetAddressEditText.setText(property.streetAddress)
                propertyNumberEditText.setText(property.propertyNumber.toString())
                priceEditText.setText(property.price)
                plotAreaEditText.setText(property.plotArea)
                totalFloorsEditText.setText(property.totalFloors.toString())
                bedroomsEditText.setText(property.bedrooms.toString())
                bathroomsEditText.setText(property.bathrooms)
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            updatePropertyButton.setOnClickListener {
                val propertyTitle = titleEditText.text.toString()
                val description = descriptionEditText.text.toString()
                val country = countryEditText.textView_selectedCountry.text.toString()
                val city = cityEditText.text.toString()
                val postalCode = postalCodeEditText.text.toString()
                val streetAddress = streetAddressEditText.text.toString()
                val propertyNumber = propertyNumberEditText.text.toString().toInt()
                val price = priceEditText.text.toString().toDouble().toInt()
                val plotArea = plotAreaEditText.text.toString().toDouble().toInt()
                val totalFloors = totalFloorsEditText.text.toString().toInt()
                val bedrooms = bedroomsEditText.text.toString().toInt()
                val bathrooms = bathroomsEditText.text.toString().toDouble().toInt()

                val inputData = Data.Builder()
                    .putString("slug", slug)
                    .putInt("user", user)
                    .putString("title", propertyTitle)
                    .putString("country", country)
                    .putString("description", description)
                    .putString("city", city)
                    .putString("postalCode", postalCode)
                    .putString("streetAddress", streetAddress)
                    .putInt("propertyNumber", propertyNumber)
                    .putInt("price", price)
                    .putInt("plotArea", plotArea)
                    .putInt("totalFloors", totalFloors)
                    .putInt("bedrooms", bedrooms)
                    .putInt("bathrooms", bathrooms)
                    .build()

                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()

                val updatePropertyRequest = OneTimeWorkRequestBuilder<UpdatePropertyWorker>()
                    .setConstraints(constraints)
                    .setInputData(inputData)
                    .addTag("update_property")
                    .build()

                WorkManager.getInstance(requireContext()).enqueue(updatePropertyRequest)
            }
        }
    }

    private fun fetchData() {
        slug = arguments?.getString("slug").toString()
        user = arguments?.getInt("user") ?: 0

        viewModel.showPropertyDetail(slug)
    }

    companion object {

    }
}
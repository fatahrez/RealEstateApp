package com.example.guryihii.feature_properties.presentation.property_listing_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentPropertyListDetailsBinding
import com.example.guryihii.feature_properties.domain.model.PropertyListing
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PropertyListingDetailsFragment : Fragment() {

    private var _binding: FragmentPropertyListDetailsBinding? = null
    private val binding: FragmentPropertyListDetailsBinding get() = _binding!!

    private val viewModel: PropertyListingDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPropertyListDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        fetchData()
        observeViewState()
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    showPropertyDetails(state.propertyListing)
                }
            }
        }
    }

    private fun showPropertyDetails(propertyListing: PropertyListing?) {
        if (propertyListing != null) {
            with(binding) {
                propertyImageView.load(Constants.BASE_URL_IMAGE+propertyListing.property.coverPhoto)
            }
        }
    }

    private fun fetchData() {
        val id = arguments?.getInt("id")
        if (id != null) {
            viewModel.showPropertyListingDetails(id)
        }
    }

    companion object {

    }
}
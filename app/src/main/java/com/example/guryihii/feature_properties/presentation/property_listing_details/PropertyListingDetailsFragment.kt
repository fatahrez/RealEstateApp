package com.example.guryihii.feature_properties.presentation.property_listing_details

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.jwt.Jwt
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentPropertyListDetailsBinding
import com.example.guryihii.feature_properties.domain.model.PropertyListing
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PropertyListingDetailsFragment : Fragment() {

    private var _binding: FragmentPropertyListDetailsBinding? = null
    private val binding: FragmentPropertyListDetailsBinding get() = _binding!!

    private val viewModel: PropertyListingDetailsViewModel by viewModels()
    @Inject lateinit var sharedPreferences: SharedPreferences

    private var id: Int = 0
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
        initViews()
        initListeners()
        observeViewState()
    }

    private fun initListeners() {
        with(binding) {
            deletePropertyListingButton.setOnClickListener {
                viewModel.deleteAgentPropertyListing(id)
            }
        }
    }

    private fun initViews() {
        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
        if (token != null) {
            val user = Jwt(token).getUserData().role
            if (user == Constants.AGENT_SIGN_UP) {
                binding.deletePropertyListingButton.visible()
            }
        }
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

        lifecycleScope.launchWhenCreated {
            viewModel.deleteState.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    Log.i("TAG", "observeViewState: ${state.propertyListing}")
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
        id = arguments?.getInt("id") ?: 0
        if (id != 0) {
            viewModel.showPropertyListingDetails(id)
        }
    }

    companion object {

    }
}
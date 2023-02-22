package com.example.guryihii.feature_properties.presentation.property_detail

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
import com.example.guryihii.databinding.FragmentPropertyDetailBinding
import com.example.guryihii.feature_properties.domain.model.Property
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PropertyDetailFragment : Fragment() {

    private var _binding: FragmentPropertyDetailBinding? = null
    private val binding: FragmentPropertyDetailBinding get() = _binding!!

    @Inject lateinit var sharedPreferences: SharedPreferences

    private val viewModel: PropertyDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPropertyDetailBinding.inflate(inflater, container, false)
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
        binding.createPropertyListingBtn.setOnClickListener {
            val property = viewModel.state.value.property
            if (property != null) {
                viewModel.postAgentPropertyListing(property.slug)
            }
        }
    }

    private fun initViews() {
        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
        if (token != null) {
            val user = Jwt(token).getUserData().role
            if (user == Constants.AGENT_SIGN_UP) {
                binding.createPropertyListingBtn.visible()
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
                    showPropertyDetails(state.property)
                }
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.propertyListingState.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    Log.i("TAG", "observeViewState: ${state.propertyListing}")
                }
            }
        }
    }

    private fun showPropertyDetails(property: Property?) {
        if (property != null) {
            with(binding) {
                propertyImageView.load(Constants.BASE_URL_IMAGE+property.coverPhoto)
            }
        }
    }

    private fun fetchData() {
        val slug = arguments?.getString("slug")
        if (slug != null) {
            viewModel.showPropertyDetail(slug)
        }
    }

    companion object {

    }
}
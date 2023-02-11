package com.example.guryihii.feature_properties.presentation.property_detail

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
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentPropertyDetailBinding
import com.example.guryihii.feature_properties.domain.model.Property
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PropertyDetailFragment : Fragment() {

    private var _binding: FragmentPropertyDetailBinding? = null
    private val binding: FragmentPropertyDetailBinding get() = _binding!!

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
        observeViewState()
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
    }

    private fun showPropertyDetails(property: Property?) {
        if (property != null) {
            with(binding) {
                binding.propertyImageView.load(Constants.BASE_URL_IMAGE+property.coverPhoto)
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
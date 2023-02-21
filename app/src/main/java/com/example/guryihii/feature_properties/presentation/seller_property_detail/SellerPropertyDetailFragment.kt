package com.example.guryihii.feature_properties.presentation.seller_property_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.guryihii.R
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentSellerPropertyDetailBinding
import com.example.guryihii.feature_properties.domain.model.Property
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellerPropertyDetailFragment : Fragment() {

    private var _binding: FragmentSellerPropertyDetailBinding? = null
    private val binding: FragmentSellerPropertyDetailBinding get() = _binding!!

    private val viewModel: SellerPropertyDetailVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSellerPropertyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        fetchData()
        initListeners()
        observeViewState()
    }

    private fun initListeners() {
        with(binding) {
            updatePropertyButton.setOnClickListener {
                val bundle = Bundle()
                val property = viewModel.state.value.property
                if (property != null) {
                    bundle.putString("slug", property.slug)
                    bundle.putInt("user", property.user)
                    findNavController().navigate(
                        R.id.action_sellerPropertyDetailFragment_to_sellerPropertyUpdateFragment,
                        bundle
                    )
                }
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
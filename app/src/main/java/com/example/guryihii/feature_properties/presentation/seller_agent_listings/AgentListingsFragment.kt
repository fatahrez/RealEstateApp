package com.example.guryihii.feature_properties.presentation.seller_agent_listings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.guryihii.R
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentAgentListingsBinding
import com.example.guryihii.feature_properties.domain.model.PropertyListing
import com.example.guryihii.feature_properties.presentation.all_property_listings.AllPropertyListingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentListingsFragment : Fragment() {

    private var _binding: FragmentAgentListingsBinding? = null
    private val binding: FragmentAgentListingsBinding get() = _binding!!

    private val viewModel: AgentListingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAgentListingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        observeViewState(adapter)
    }

    private fun setupRecyclerView(allPropertyListingAdapter: AllPropertyListingAdapter) {
        binding.recyclerView.apply {
            adapter = allPropertyListingAdapter
            setHasFixedSize(true)
        }
    }

    private fun createAdapter(): AllPropertyListingAdapter {
        return AllPropertyListingAdapter(
            clickListener = {
                navToPropertyListingDetails(it)
            },
            requireContext()
        )
    }

    private fun navToPropertyListingDetails(propertyListing: PropertyListing) {
        val bundle = Bundle()
        bundle.putInt("id", propertyListing.id)
        findNavController().navigate(
            R.id.action_agentListingsFragment_to_propertyListingDetailsFragment2,
            bundle
        )
    }

    private fun observeViewState(adapter: AllPropertyListingAdapter) {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    binding.noData.run {
                        if (state.propertyListings.isEmpty()) visible() else gone()
                    }
                    adapter.submitList(state.propertyListings)
                }
            }
        }
    }

    companion object {

    }
}
package app.sodeg.sodeg.feature_properties.presentation.all_property_listings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.gone
import app.sodeg.sodeg.core.util.visible
import app.sodeg.sodeg.databinding.FragmentAllPropertyListingsBinding
import app.sodeg.sodeg.feature_properties.domain.model.PropertyListing
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllPropertyListingsFragment : Fragment() {

    private var _binding: FragmentAllPropertyListingsBinding? = null
    private val binding: FragmentAllPropertyListingsBinding get() = _binding!!

    private val viewModel: AllPropertyListingsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllPropertyListingsBinding.inflate(
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
        return AllPropertyListingAdapter(clickListener = {
            navToPropertyListingDetails(it)
        },context = requireContext())
    }

    private fun navToPropertyListingDetails(propertyListing: PropertyListing) {
        val bundle = Bundle()
        bundle.putInt("id", propertyListing.id)
        findNavController().navigate(
            R.id.action_allPropertyListingsFragment_to_propertyListDetailsFragment,
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
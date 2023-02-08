package com.example.guryihii.feature_properties.presentation.property_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.guryihii.R
import com.example.guryihii.databinding.FragmentPropertyBinding
import com.example.guryihii.feature_properties.domain.model.Property
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.count


@AndroidEntryPoint
class PropertyFragment : Fragment() {
    private var _binding: FragmentPropertyBinding? = null
    private val binding: FragmentPropertyBinding get() = _binding!!

    private val viewModel: PropertyListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPropertyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        initViews()
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        initListeners()
        observeViewState(adapter)
    }

    private fun initViews() {

    }

    private fun initListeners() {
    }

    private fun observeViewState(adapter: PropertiesAdapter) {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visibility = View.VISIBLE
                } else {
                    binding.progressBar.visibility = View.GONE
                    binding.noData.run {
                        if (state.properties.isEmpty()) visibility = View.GONE else View.VISIBLE
                    }
                    adapter.submitList(state.properties)
                }
            }
        }
    }

    private fun setupRecyclerView(propertiesAdapter: PropertiesAdapter) {
        binding.recyclerView.apply {
            adapter = propertiesAdapter
            setHasFixedSize(true)
        }
    }

    private fun  createAdapter(): PropertiesAdapter {
        return PropertiesAdapter {
            navToPropertyDetail(it)
        }
    }

    private fun navToPropertyDetail(property: Property) {
        val args = Bundle().apply {
            putString("slug", property.slug)
        }
        findNavController().navigate(R.id.action_propertyFragment_to_propertyDetailFragment, args)
    }
    companion object {

    }
}
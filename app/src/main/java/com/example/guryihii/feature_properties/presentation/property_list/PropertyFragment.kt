package com.example.guryihii.feature_properties.presentation.property_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.guryihii.R
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.visible
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
    ): View {
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
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    binding.noData.run {
                        if (state.properties.isEmpty()) visible() else gone()
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
        Log.i("TAG", "navToPropertyDetail: ${property.slug}")
        val bundle = Bundle()
        bundle.putString("slug", property.slug)
        view?.let {
            Navigation
                .findNavController(it)
                .navigate(R.id.action_propertyFragment_to_propertyDetailFragment, bundle)
        }
    }
    companion object {

    }
}
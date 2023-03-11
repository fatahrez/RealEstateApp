package app.sodeg.sodeg.feature_requests.presentation.request_list

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
import app.sodeg.sodeg.databinding.FragmentPropertyRequestsBinding
import app.sodeg.sodeg.feature_requests.domain.model.RequestProperty
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PropertyRequestsFragment : Fragment() {
    private var _binding: FragmentPropertyRequestsBinding? = null
    private val binding: FragmentPropertyRequestsBinding get() = _binding!!

    private val viewModel: PropertyRequestsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPropertyRequestsBinding.inflate(inflater, container, false)
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

    private fun setupRecyclerView(requestAdapter: PropertyRequestAdapter) {
        binding.recyclerView.apply {
            adapter = requestAdapter
            setHasFixedSize(true)
        }
    }

    private fun createAdapter(): PropertyRequestAdapter {
        return PropertyRequestAdapter {
            navToDetails(it)
        }
    }

    private fun navToDetails(requestProperty: RequestProperty) {
        val bundle = Bundle()
        bundle.putInt("id", requestProperty.id)
        findNavController().navigate(
            R.id.action_propertyRequestsFragment_to_propertyRequestDetailsFragment,
            bundle
        )
    }

    private fun observeViewState(adapter: PropertyRequestAdapter) {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    binding.noData.run {
                        if (state.requestProperties.isEmpty()) visible() else gone()
                    }
                    adapter.submitList(state.requestProperties)
                }
            }
        }
    }

    companion object {

    }
}
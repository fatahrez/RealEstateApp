package app.sodeg.sodeg.feature_requests.presentation.property_request_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.sodeg.sodeg.core.util.gone
import app.sodeg.sodeg.core.util.visible
import app.sodeg.sodeg.databinding.FragmentPropertyRequestDetailsBinding
import app.sodeg.sodeg.feature_requests.domain.model.RequestProperty
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PropertyRequestDetailsFragment : Fragment() {

    private var _binding: FragmentPropertyRequestDetailsBinding? = null
    private val binding: FragmentPropertyRequestDetailsBinding get() = _binding!!

    private val viewModel: PropertyRequestDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPropertyRequestDetailsBinding
            .inflate(inflater, container, false)
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
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    showRequestPropertyDetails(state.requestProperty)
                }
            }
        }
    }

    private fun showRequestPropertyDetails(requestProperty: RequestProperty?) {
        with(binding) {
            if (requestProperty != null) {
                nameTextView.text = requestProperty.name
                emailTextView.text = requestProperty.email
                phoneNumberTextView.text = requestProperty.phoneNumber
                subjectTextView.text = requestProperty.subject
                messageTextView.text = requestProperty.message
            }
        }
    }

    private fun fetchData() {
        val id = arguments?.getInt("id", 0)
        if (id != null) {
            viewModel.showRequestPropertyDetails(id)
        }
    }

    companion object {
    }
}
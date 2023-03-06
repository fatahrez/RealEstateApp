package com.example.guryihii.feature_requests.presentation.post_request

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
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentRequestPropertyBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestPropertyFragment : Fragment() {

    private var _binding: FragmentRequestPropertyBinding? = null
    private val binding: FragmentRequestPropertyBinding get() = _binding!!

    private val viewModel: RequestPropertyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRequestPropertyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        initListeners()
        observeViewState()
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else if (!state.error.isNullOrEmpty()) {
                    Snackbar.make(
                        requireView(),
                        state.error,
                        Snackbar.LENGTH_SHORT
                    ).show()
                }else {
                    binding.progressBar.gone()
                    Snackbar.make(
                        requireView(),
                        "Property Request successfully Posted",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(
                        R.id.action_requestPropertyFragment_to_allPropertyListingsFragment,
                        null
                    )
                }
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            makeRequestButton.setOnClickListener {
                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val phoneNumber = phoneNumberEditText.text.toString()
                val subject = subjectEditText.text.toString()
                val message = messageEditText.text.toString()

                viewModel.postRequestProperty(
                    name, email, phoneNumber, subject, message
                )
            }
        }
    }

    companion object {
    }
}
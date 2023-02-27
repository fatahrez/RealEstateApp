package com.example.guryihii.feature_properties.presentation.all_property_listings

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.guryihii.R
import com.example.guryihii.databinding.FragmentAllPropertyListingsBinding
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
        observeViewState()
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    Log.i("TAG", "observeViewState: loading...")
                } else {
                    Log.i("TAG", "observeViewState: ${state.propertyListings}")
                }
            }
        }
    }

    companion object {

    }
}
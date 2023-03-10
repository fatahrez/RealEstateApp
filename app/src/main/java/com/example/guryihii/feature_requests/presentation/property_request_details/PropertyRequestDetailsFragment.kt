package com.example.guryihii.feature_requests.presentation.property_request_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.guryihii.R
import com.example.guryihii.databinding.FragmentPropertyRequestDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PropertyRequestDetailsFragment : Fragment() {

    private var _binding: FragmentPropertyRequestDetailsBinding? = null
    private val binding: FragmentPropertyRequestDetailsBinding get() = _binding!!

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

    }

    companion object {
    }
}
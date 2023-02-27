package com.example.guryihii.feature_requests.presentation.request_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.guryihii.R
import com.example.guryihii.databinding.FragmentPropertyRequestsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PropertyRequestsFragment : Fragment() {
    private var _binding: FragmentPropertyRequestsBinding? = null
    private val binding: FragmentPropertyRequestsBinding get() = _binding!!

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

    }

    companion object {

    }
}
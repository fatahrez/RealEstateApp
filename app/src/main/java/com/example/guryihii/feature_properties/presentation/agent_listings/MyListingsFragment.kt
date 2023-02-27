package com.example.guryihii.feature_properties.presentation.agent_listings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.guryihii.R
import com.example.guryihii.databinding.FragmentMyListingsBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyListingsFragment : Fragment() {

    private var _binding: FragmentMyListingsBinding? = null
    private val binding: FragmentMyListingsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyListingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        TODO("Not yet implemented")
    }

    companion object {

    }
}
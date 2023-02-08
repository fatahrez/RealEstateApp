package com.example.guryihii.feature_properties.presentation.property_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.guryihii.R
import com.example.guryihii.databinding.FragmentPropertyDetailBinding

class PropertyDetailFragment : Fragment() {

    private var _binding: FragmentPropertyDetailBinding? = null
    private val binding: FragmentPropertyDetailBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPropertyDetailBinding.inflate(inflater, container, false)
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
package com.example.guryihii.feature_properties.presentation.update_property

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.guryihii.R
import com.example.guryihii.databinding.FragmentSellerPropertyUpdateBinding


class SellerPropertyUpdateFragment : Fragment() {

    private var _binding: FragmentSellerPropertyUpdateBinding? = null
    private val binding: FragmentSellerPropertyUpdateBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSellerPropertyUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

    }
}
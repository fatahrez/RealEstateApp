package com.example.guryihii.feature_requests.presentation.post_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.guryihii.R
import com.example.guryihii.databinding.FragmentRequestPropertyBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestPropertyFragment : Fragment() {

    private var _binding: FragmentRequestPropertyBinding? = null
    private val binding: FragmentRequestPropertyBinding get() = _binding!!

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
        TODO("Not yet implemented")
    }

    companion object {
    }
}
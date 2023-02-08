package com.example.guryihii.feature_properties.presentation.property_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.guryihii.databinding.FragmentPropertyBinding


class PropertyFragment : Fragment() {
    private var _binding: FragmentPropertyBinding? = null
    private val binding: FragmentPropertyBinding get() = _binding!!

    private val viewModel: PropertyListViewModel by viewModels()

    val state = viewModel.state.value

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPropertyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        initViews()
        initListeners()
        observeViewState()
    }

    private fun initViews() {
        TODO("Not yet implemented")
    }

    private fun initListeners() {
        TODO("Not yet implemented")
    }

    private fun observeViewState() {
        if(state.isLoading) {
            Log.i("TAG", "observeViewState: Loading")
        }

        Log.i("TAG", "observeViewState: ${state.properties}")
    }

    companion object {

    }
}
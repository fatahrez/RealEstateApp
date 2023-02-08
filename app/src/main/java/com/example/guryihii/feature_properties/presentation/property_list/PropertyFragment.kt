package com.example.guryihii.feature_properties.presentation.property_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.guryihii.databinding.FragmentPropertyBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.count


@AndroidEntryPoint
class PropertyFragment : Fragment() {
    private var _binding: FragmentPropertyBinding? = null
    private val binding: FragmentPropertyBinding get() = _binding!!

    private val viewModel: PropertyListViewModel by viewModels()

//    private val state = viewModel.state.value

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

    }

    private fun initListeners() {
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    Log.i("TAG", "observeViewState: loading")
                } else {
                    Log.i("TAG", "observeViewState: ${state.properties}")
                }
            }
        }
    }

    companion object {

    }
}
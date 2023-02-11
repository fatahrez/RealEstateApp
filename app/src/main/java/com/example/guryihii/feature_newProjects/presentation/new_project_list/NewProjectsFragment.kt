package com.example.guryihii.feature_newProjects.presentation.new_project_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.guryihii.R
import com.example.guryihii.databinding.FragmentNewProjectsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class NewProjectsFragment : Fragment() {
    private var _binding: FragmentNewProjectsBinding? = null
    private val binding: FragmentNewProjectsBinding get() = _binding!!

    private val viewModel: NewProjectListViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNewProjectsBinding.inflate(inflater, container, false)
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
                if(state.isLoading) {
                    Log.i("TAG", "observeViewState: loading...")
                } else {
                    Log.i("TAG", "observeViewState: ${state.newProjects}")
                }
            }
        }
    }

    companion object {

    }
}
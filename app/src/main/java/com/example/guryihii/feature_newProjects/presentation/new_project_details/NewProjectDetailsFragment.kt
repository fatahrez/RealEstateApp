package com.example.guryihii.feature_newProjects.presentation.new_project_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.guryihii.R
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentNewProjectDetailsBinding
import com.example.guryihii.feature_newProjects.domain.model.NewProject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class NewProjectDetailsFragment : Fragment() {

    private var _binding: FragmentNewProjectDetailsBinding? = null
    private val binding: FragmentNewProjectDetailsBinding get() = _binding!!

    private val viewModel: NewProjectDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewProjectDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        fetchData()
        observeViewState()
    }

    private fun fetchData() {
        val slug = arguments?.getString("slug")
        if (slug != null) {
            viewModel.showNewProjectDetails(slug)
        }
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    showNewProjectDetails(state.newProject)
                }
            }
        }
    }

    private fun showNewProjectDetails(newProject: NewProject?) {
        with(binding) {
            if (newProject != null) {
                newProjectNameTextView.text = newProject.name
            }
        }
    }

    companion object {

    }
}
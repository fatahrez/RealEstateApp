package com.example.guryihii.feature_newProjects.presentation.new_project_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.guryihii.R
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentNewProjectsBinding
import com.example.guryihii.feature_newProjects.domain.model.NewProject
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
        _binding = FragmentNewProjectsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        observeViewState(adapter)
    }

    private fun observeViewState(adapter: NewProjectListAdapter) {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if(state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    binding.noData.run {
                        if (state.newProjects.isEmpty()) visible() else gone()
                    }
                    adapter.submitList(state.newProjects)
                }
            }
        }
    }

    private fun createAdapter(): NewProjectListAdapter {
        return NewProjectListAdapter {
            navToNewProjectDetails(it)
        }
    }

    private fun navToNewProjectDetails(newProject: NewProject) {
        val bundle = Bundle()
        bundle.putString("slug", newProject.slug)
        view?.let {
            Navigation.findNavController(it)
                .navigate(R.id.action_newProjectsFragment2_to_newProjectDetailsFragment, bundle)
        }
    }

    private fun setupRecyclerView(newProjectListAdapter: NewProjectListAdapter) {
        binding.recyclerView.apply {
            adapter = newProjectListAdapter
            setHasFixedSize(true)
        }
    }

    companion object {

    }
}
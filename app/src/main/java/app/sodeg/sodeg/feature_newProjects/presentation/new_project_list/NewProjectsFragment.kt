package app.sodeg.sodeg.feature_newProjects.presentation.new_project_list

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.core.util.gone
import app.sodeg.sodeg.core.util.jwt.Jwt
import app.sodeg.sodeg.core.util.visible
import app.sodeg.sodeg.databinding.FragmentNewProjectsBinding
import app.sodeg.sodeg.feature_newProjects.domain.model.NewProject
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewProjectsFragment : Fragment() {
    private var _binding: FragmentNewProjectsBinding? = null
    private val binding: FragmentNewProjectsBinding get() = _binding!!

    private val viewModel: NewProjectListViewModel by viewModels()
    @Inject lateinit var sharedPreferences: SharedPreferences

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
        initViews()
        initListeners()
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        observeViewState(adapter)
    }

    private fun initListeners() {
        with(binding) {
            postNewProjectFloatingButton.setOnClickListener {
                findNavController()
                    .navigate(R.id.action_newProjectsFragment2_to_postNewProjectFragment)
            }
        }
    }

    private fun initViews() {
        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
        if (token != null) {
            val user = Jwt(token).getUserData().role
            if (user == Constants.PROJECTBUILDER_SIGN_UP) {
                binding.postNewProjectFloatingButton.visible()
            }
        }
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
        return NewProjectListAdapter (
            clickListener = {
                navToNewProjectDetails(it)
            },
            requireContext()
        )
    }

    private fun navToNewProjectDetails(newProject: NewProject) {
        val bundle = Bundle()
        bundle.putString("slug", newProject.slug)
        bundle.putInt("user", newProject.user)
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
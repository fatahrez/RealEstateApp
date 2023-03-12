package app.sodeg.sodeg.feature_newProjects.presentation.post_new_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.sodeg.sodeg.databinding.FragmentNewProjectsBinding


class PostNewProjectFragment : Fragment() {

    private var _binding: FragmentNewProjectsBinding? = null
    private val binding: FragmentNewProjectsBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentNewProjectsBinding.inflate(inflater, container, false)
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
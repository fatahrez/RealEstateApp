package app.sodeg.sodeg.feature_newProjects.presentation.update_new_project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.sodeg.sodeg.R
import app.sodeg.sodeg.databinding.FragmentUpdateNewProjectBinding


class UpdateNewProjectFragment : Fragment() {

    private var _binding: FragmentUpdateNewProjectBinding? = null
    private val binding: FragmentUpdateNewProjectBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateNewProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

    }
}
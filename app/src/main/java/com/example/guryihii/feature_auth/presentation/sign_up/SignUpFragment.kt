package com.example.guryihii.feature_auth.presentation.sign_up

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentSignUpBinding
import com.example.guryihii.feature_auth.domain.model.User
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        initListeners()
        observeViewState()
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if(state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    Log.i("TAG", "observeViewState: ${state.user}")
                }
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            signUpButton.setOnClickListener {
                val firstName = firstNameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                val user = User(
                    firstName = firstName,
                    email = email,
                    password = password,
                    type = "INDIVIDUAL"
                )
                viewModel.signUpUser(user)
            }
        }
    }

    companion object {

    }
}
package com.example.guryihii.feature_auth.presentation.sign_in

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.guryihii.MainActivity
import com.example.guryihii.R
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.isValidEmail
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentSignInBinding
import com.example.guryihii.feature_auth.domain.model.User
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class SignInFragment() : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding get() = _binding!!

    private val viewModel: SignInViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        observeViewState()
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            signInButton.setOnClickListener {
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()
                if(validateInputs(email, password)) {
                    val user = User(email, password)
                    viewModel.signIn(user)
                }
            }
        }
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else if (!state.errors.isNullOrEmpty()) {
                    binding.progressBar.gone()
                    Snackbar.make(
                        requireView(),
                        state.errors.toString(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }else {
                    binding.progressBar.gone()
                    if (state.user?.detail.isNullOrEmpty() || state.user?.refreshToken.isNullOrEmpty()) {
                        sharedPreferences.edit {
                            putString(Constants.ACCESS_TOKEN, state.user?.accessToken)
                            putString(Constants.REFRESH_TOKEN, state.user?.refreshToken)
                        }
                        if (sharedPreferences.getString(Constants.ACCESS_TOKEN, null) != null) {
                            val mainActivity = requireActivity() as MainActivity
                            mainActivity.updateNavigation()
                        }
                    }
                }
            }
        }
    }

    private fun validateInputs(email: String, password: String): Boolean {
        with(binding) {
            return when {
                email.isEmpty() -> {
                    emailEditText.error = getString(R.string.emptyEmail)
                    false
                }
                password.isEmpty() -> {
                    passwordEditText.error = getString(R.string.emptyPassword)
                    false
                }
                password.length < 6 -> {
                    passwordEditText.error = getString(R.string.passwordInvalidLength)
                    false
                }
                !email.contains("@") -> {
                    emailEditText.error = getString(R.string.invalid_email_error)
                    false
                }
                email.contains("@") -> {
                    if (!email.isValidEmail()) {
                        emailEditText.error = getString(R.string.invalid_email_error)
                        false
                    } else true
                }

                else -> true
            }
        }
    }

    companion object {

    }
}
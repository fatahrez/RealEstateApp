package com.example.guryihii.feature_auth.presentation.sign_up

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
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.guryihii.MainActivity
import com.example.guryihii.R
import com.example.guryihii.core.util.*
import com.example.guryihii.databinding.FragmentSignUpBinding
import com.example.guryihii.feature_auth.domain.model.User
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private var _binding: FragmentSignUpBinding? = null
    private val binding: FragmentSignUpBinding get() = _binding!!

    private val viewModel: SignUpViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

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
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                if(state.isLoading) {
                    binding.progressBar.visible()
                } else if(!state.error.isNullOrEmpty()) {
                    hideKeyboard()
                    binding.progressBar.gone()
                    Snackbar.make(
                        requireView(),
                        state.error.toString(),
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    hideKeyboard()
                    binding.progressBar.gone()
                    if (state.user != null) {
                        sharedPreferences.edit {
                            putString(Constants.ACCESS_TOKEN, state.user.accessToken)
                            putString(Constants.REFRESH_TOKEN, state.user.refreshToken)
                        }
                        val mainActivity = requireActivity() as MainActivity
                        mainActivity.updateNavigation()
//                        findNavController().navigate(
//                            R.id.action_signUpFragment_to_allPropertyListingsFragment,
//                            null
//                        )
                    }
                }
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            otherUserSignUpButton.setOnClickListener {
                findNavController().navigate(R.id.otherUserSignUpFragment)
            }
            signUpButton.setOnClickListener {
                val firstName = firstNameEditText.text.toString()
                val email = emailEditText.text.toString()
                val password = passwordEditText.text.toString()

                if (validateInputs(firstName, email, password)) {
                    val user = User(
                        firstName = firstName,
                        email = email,
                        password = password,
                        type = Constants.INDIVIDUAL_SIGN_UP
                    )
                    viewModel.signUpUser(user)
                }
            }
        }
    }

    private fun validateInputs(firstName: String, email: String, password: String): Boolean {
        with(binding) {
            return when {
                firstName.isEmpty() -> {
                    firstNameEditText.error = getString(R.string.empty_first_name)
                    return false
                }
                email.isEmpty() -> {
                    emailEditText.error = getString(R.string.emptyEmail)
                    false
                }
                password.isEmpty() -> {
                    passwordEditText.error = getString(R.string.emptyPassword)
                    false
                }
                !email.isValidEmail() -> {
                    emailEditText.error = getString(R.string.invalid_email_error)
                    false
                }
                password.length < 6 -> {
                    passwordEditText.error = getString(R.string.passwordInvalidLength)
                    false
                }
                else -> true
            }
        }
    }
    companion object {

    }
}
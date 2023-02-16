package com.example.guryihii.feature_auth.presentation.sign_up.other_users

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.guryihii.R
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentOtherUserSignUpBinding
import com.example.guryihii.feature_auth.domain.model.User
import com.example.guryihii.feature_auth.presentation.sign_up.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class OtherUserSignUpFragment : Fragment() {

    private var _binding: FragmentOtherUserSignUpBinding? = null
    private val binding: FragmentOtherUserSignUpBinding get() = _binding!!

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val viewModel: SignUpViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOtherUserSignUpBinding.inflate(inflater, container, false)
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

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if(state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    if (state.user != null) {
                        sharedPreferences.edit {
                            putString(Constants.ACCESS_TOKEN, state.user.accessToken)
                            putString(Constants.REFRESH_TOKEN, state.user.refreshToken)
                        }
                        findNavController().navigate(R.id.updateProfileFragment, null)
                    }
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
                val userType = userTypeSpinners.selectedItem.toString()

                Log.i("TAG", "initListeners: $userType")
                val user = User(
                    firstName = firstName,
                    email = email,
                    password = password,
                    type = userType
                )
                viewModel.signUpUser(user)
            }
        }
    }

    private fun initViews() {
        with(binding) {
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.userTypes,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                userTypeSpinners.adapter = adapter
            }
        }

    }

    companion object {
    }
}
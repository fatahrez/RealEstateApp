package com.example.guryihii.feature_profile.presentation.update_profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.guryihii.R
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.jwt.Jwt
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentUpdateProfileBinding
import com.example.guryihii.feature_profile.domain.model.Profile
import com.example.guryihii.feature_profile.presentation.profile.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject


@AndroidEntryPoint
class UpdateProfileFragment : Fragment() {

    private var _binding: FragmentUpdateProfileBinding? = null
    private val binding: FragmentUpdateProfileBinding get() = _binding!!

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private var gender: String? = "Male"

    private val viewModel: UpdateProfileViewModel by viewModels()
    private val getProfileViewModel: ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        fetchData()
        initListeners()
        observeViewState()
    }

    private fun fetchData() {
        getProfileViewModel.showProfile()
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            getProfileViewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    if (state.profile != null) {
                        updateProfile(state.profile.username)
                    }
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    findNavController().navigate(R.id.propertyFragment, null)
                }
            }
        }
    }

    private fun updateProfile(username: String?) {
        with(binding) {
            updateProfileButton.setOnClickListener {
                val phoneNumber = phoneNumberEditText.text.toString()
                val aboutMe = aboutMeEditText.text.toString()
                val license = realEstateLicenseEditText.text.toString()
                maleRadioButton.isChecked = true
                maleRadioButton.setOnCheckedChangeListener { _, isChecked ->
                    gender = if (isChecked) {
                        "Male"
                    } else {
                        "Female"
                    }
                }
                val userGender = if (gender != null) {
                    gender as String
                } else {
                    ""
                }
                val country = countryEditText.text.toString()
                val city = cityEditText.text.toString()
                val profile = Profile(
                    phoneNumber = phoneNumber,
                    aboutMe = aboutMe,
                    license = license,
                    gender = userGender,
                    country = country.lowercase(),
                    city = city
                )
                if (username != null) {
                    viewModel.updateUserProfile(username, profile)
                }

            }
        }
    }

    private fun initListeners() {

    }

    companion object {
    }
}
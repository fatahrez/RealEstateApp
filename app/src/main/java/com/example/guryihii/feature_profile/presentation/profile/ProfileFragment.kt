package com.example.guryihii.feature_profile.presentation.profile

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.guryihii.R
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentProfileBinding
import com.example.guryihii.feature_profile.domain.model.Profile
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding: FragmentProfileBinding get() = _binding!!

    private val viewModel: ProfileViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
        if (token != null) {
            binding.fragmentLoggedIn.visible()
            binding.fragmentNotSignedIn.gone()
            fetchData()
            observeViewState()
        } else {
            binding.fragmentNotSignedIn.visible()
            binding.fragmentLoggedIn.gone()
        }
        initViews()
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            buttonSignIn.setOnClickListener {
                findNavController().navigate(R.id.action_profileFragment2_to_signInFragment, null)
            }
        }
    }

    private fun fetchData() {
        viewModel.showProfile()
    }

    private fun initViews() {

    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar2.visible()
                } else {
                    binding.progressBar2.gone()
                    showProfile(state.profile)
                }
            }
        }
    }

    private fun showProfile(profile: Profile?) {
        with(binding) {
            if (profile != null) {
                fragmentNotSignedIn.gone()
                fragmentLoggedIn.visible()
                profilePhoto.load(profile.profilePhoto)
                username.text = profile.username
                name.text = profile.firstName
                email.text = profile.email
                phone.text = profile.phoneNumber
                aboutMe.text = profile.aboutMe
                gender.text = profile.gender
                country.text = profile.country
                city.text = profile.city
            } else {
                fragmentNotSignedIn.visible()
                fragmentLoggedIn.gone()
            }
        }
    }

    companion object {

    }
}
package app.sodeg.sodeg.feature_auth.presentation.sign_in

import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import app.sodeg.sodeg.MainActivity
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.*
import app.sodeg.sodeg.databinding.FragmentSignInBinding
import app.sodeg.sodeg.feature_auth.domain.model.User
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
                    hideKeyboard()
                    binding.progressBar.gone()
                    Snackbar.make(
                        requireView(),
                        state.errors.toString(),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }else {
                    hideKeyboard()
                    binding.progressBar.gone()
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
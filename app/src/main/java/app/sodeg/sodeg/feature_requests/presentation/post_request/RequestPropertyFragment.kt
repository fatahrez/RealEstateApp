package app.sodeg.sodeg.feature_requests.presentation.post_request

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.gone
import app.sodeg.sodeg.core.util.isValidEmail
import app.sodeg.sodeg.core.util.visible
import app.sodeg.sodeg.databinding.FragmentRequestPropertyBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RequestPropertyFragment : Fragment() {

    private var _binding: FragmentRequestPropertyBinding? = null
    private val binding: FragmentRequestPropertyBinding get() = _binding!!

    private val viewModel: RequestPropertyViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRequestPropertyBinding.inflate(inflater, container, false)
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
                if (state.requestPropertyResponse != null) {
                    if (state.isLoading) {
                        binding.progressBar.visible()
                    } else if (!state.error.isNullOrEmpty()) {
                        Snackbar.make(
                            requireView(),
                            state.error,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }else {
                        binding.progressBar.gone()
                        Snackbar.make(
                            requireView(),
                            "Property Request successfully Posted",
                            Snackbar.LENGTH_SHORT
                        ).show()
                        findNavController().navigate(
                            R.id.action_requestPropertyFragment_to_allPropertyListingsFragment,
                            null
                        )
                    }
                }
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            makeRequestButton.setOnClickListener {
                countryCodePicker.registerCarrierNumberEditText(phoneNumberEditText)
                val name = nameEditText.text.toString()
                val email = emailEditText.text.toString()
                val phoneNumber = countryCodePicker.fullNumberWithPlus
                val subject = subjectEditText.text.toString()
                val message = messageEditText.text.toString()

                if (
                    validInputs(
                        name,
                        email,
                        phoneNumberEditText.text.toString(),
                        subject,
                        message
                    )
                ) {
                    viewModel.postRequestProperty(
                        name, email, phoneNumber, subject, message
                    )
                }
            }
        }
    }

    private fun validInputs(
        name: String,
        email: String,
        phoneNumber: String,
        subject: String,
        message: String
    ): Boolean {
        with(binding) {
            return when {
                name.isEmpty() -> {
                    nameEditText.error = getString(R.string.empty_name)
                    false
                }
                email.isEmpty() -> {
                    emailEditText.error = getString(R.string.emptyEmail)
                    false
                }
                !email.isValidEmail() -> {
                    emailEditText.error = getString(R.string.invalid_email_error)
                    false
                }
                phoneNumber.isEmpty() -> {
                    phoneNumberEditText.error = getString(R.string.empty_phone_number)
                    false
                }
                phoneNumber.length < 9 -> {
                    phoneNumberEditText.error = getString(R.string.invalid_phone_length)
                    false
                }
                subject.isEmpty() -> {
                    subjectEditText.error = getString(R.string.empty_subject)
                    false
                }
                message.isEmpty() -> {
                    messageEditText.error = getString(R.string.empty_message)
                    false
                }
                else -> true
            }
        }
    }

    companion object {
    }
}
package app.sodeg.sodeg.feature_profile.presentation.update_profile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.sodeg.sodeg.MainActivity
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.core.util.MultiPartUtil
import app.sodeg.sodeg.core.util.gone
import app.sodeg.sodeg.core.util.jwt.Jwt
import app.sodeg.sodeg.core.util.visible
import app.sodeg.sodeg.databinding.FragmentUpdateProfileBinding
import app.sodeg.sodeg.feature_properties.presentation.post_property.PostPropertyFragment.Companion.PICK_IMAGE_REQUEST_CODE
import app.sodeg.sodeg.feature_properties.presentation.post_property.PostPropertyFragment.Companion.REQUEST_CODE_READ_EXTERNAL_STORAGE
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@AndroidEntryPoint
class UpdateProfileFragment : Fragment() {

    private var _binding: FragmentUpdateProfileBinding? = null
    private val binding: FragmentUpdateProfileBinding get() = _binding!!

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private var gender: String? = "Male"

    private var imageUri: Uri? = null

    private val viewModel: UpdateProfileViewModel by viewModels()
    var username = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (ContextCompat.checkSelfPermission(requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                REQUEST_CODE_READ_EXTERNAL_STORAGE)
        }
    }
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
        initViews()
        fetchData()
        initListeners()
        observeViewState()
    }

    private fun initViews() {
        with(binding) {
        }
    }

    private fun fetchData() {
        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
        if (token != null) {
            username = Jwt(token).getUserData().username
        }
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else if (!state.error.isNullOrEmpty()) {
                    Snackbar.make(
                        requireView(),
                        state.error,
                        Snackbar.LENGTH_LONG
                    ).show()
                }else {
                    binding.progressBar.gone()
                    if (state.profileResponse != null) {
                        val mainActivity = requireActivity() as MainActivity
                        mainActivity.updateNavigation()
                        findNavController().navigate(R.id.propertyFragment, null)
                    }
                }
            }
        }
    }

    private fun updateProfile(username: String?) {
        with(binding) {
            updateProfileButton.setOnClickListener {
                countryCodePicker.registerCarrierNumberEditText(phoneNumberEditText)
                val phoneNumber = countryCodePicker.fullNumberWithPlus
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
                val country = countryEditText.textView_selectedCountry.text.toString()
                val city = cityEditText.text.toString()
                if (validateInputs(phoneNumber, aboutMe, license, country, city)) {
//                    val profile = Profile(
//                        phoneNumber = phoneNumber.toString(),
//                        aboutMe = aboutMe,
//                        license = license,
//                        gender = userGender,
//                        country = country.lowercase(),
//                        city = city
//                    )
                    val phoneRequestBody = RequestBody
                        .create("text/plain".toMediaTypeOrNull(), phoneNumber)
                    val phoneNumberPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                        "phone_number",
                        null,
                        phoneRequestBody
                    )

                    val aboutMeRequestBody = RequestBody
                        .create("text/plain".toMediaTypeOrNull(), aboutMe)
                    val aboutMePart: MultipartBody.Part = MultipartBody.Part.createFormData(
                        "about_me",
                        null,
                        aboutMeRequestBody
                    )

                    val licenseRequestBody = RequestBody
                        .create("text/plain".toMediaTypeOrNull(), license)
                    val licensePart: MultipartBody.Part = MultipartBody.Part.createFormData(
                        "license",
                        null,
                        licenseRequestBody
                    )

                    val genderRequestBody = RequestBody
                        .create("text/plain".toMediaTypeOrNull(), gender.toString())
                    val genderPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                        "gender",
                        null,
                        genderRequestBody
                    )

                    val countryRequestBody = RequestBody
                        .create("text/plain".toMediaTypeOrNull(), country)
                    val countryPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                        "country",
                        null,
                        countryRequestBody
                    )

                    val cityRequestBody = RequestBody
                        .create("text/plain".toMediaTypeOrNull(), city)
                    val cityPart: MultipartBody.Part = MultipartBody.Part.createFormData(
                        "city",
                        null,
                        cityRequestBody
                    )

                    val profilePhoto = imageUri?.let { it1 ->
                        MultiPartUtil.loadFileFromContentResolver(
                            requireContext(),
                            it1,
                            "profile_photo"
                        )
                    }
                    if (username != null && profilePhoto != null) {
                        viewModel.updateUserProfile(
                            username,
                            phoneNumberPart,
                            aboutMePart,
                            licensePart,
                            genderPart,
                            countryPart,
                            cityPart,
                            profilePhoto
                        )
                    } else {
                       Snackbar.make(
                           requireView(),
                           "Pick Profile Image",
                           Snackbar.LENGTH_SHORT
                       ).show()
                    }
                }
            }
        }
    }

    private fun validateInputs(
        phoneNumber: String,
        aboutMe: String,
        license: String,
        country: String,
        city: String
    ): Boolean {
        with(binding) {
            return when {
                phoneNumber.isEmpty() -> {
                    phoneNumberEditText.error = getString(R.string.empty_phone_number)
                    false
                }
                aboutMe.isEmpty() -> {
                    aboutMeEditText.error = getString(R.string.empty_about_me)
                    false
                }
                license.isEmpty() -> {
                    realEstateLicenseEditText.error = getString(R.string.empty_realestate_license)
                    false
                }
                license.length > 20 -> {
                    realEstateLicenseEditText.error = getString(R.string.invalid_license_length)
                    false
                }
                country.isEmpty() -> {
                    Snackbar.make(
                        requireView(),
                        getString(R.string.empty_country),
                        Snackbar.LENGTH_SHORT
                    ).show()
                    false
                }
                city.isEmpty() -> {
                    cityEditText.error = getString(R.string.empty_city)
                    false
                }
                phoneNumber.length < 9 -> {
                    phoneNumberEditText.error = getString(R.string.invalid_phone_length)
                    false
                }
                else -> true
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            profilePhotoImageView.setOnClickListener {
                pickPhoto()
            }
            updateProfileButton.setOnClickListener {
                updateProfile(username)
            }
        }
    }

    private fun pickPhoto() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(
            Intent.createChooser(intent, "Select Picture"),
            PICK_IMAGE_REQUEST_CODE
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            if (uri != null) {
                imageUri = uri

                binding.profilePhotoImageView.setImageURI(uri)
            }
        }
    }

    companion object {
    }
}
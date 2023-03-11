package com.example.guryihii.feature_properties.presentation.property_listing_details

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager.NameNotFoundException
import android.net.Uri
import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import com.example.guryihii.R
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.util.gone
import com.example.guryihii.core.util.jwt.Jwt
import com.example.guryihii.core.util.setResizableText
import com.example.guryihii.core.util.visible
import com.example.guryihii.databinding.FragmentPropertyListDetailsBinding
import com.example.guryihii.feature_properties.domain.model.PropertyListing
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class PropertyListingDetailsFragment : Fragment() {

    private var _binding: FragmentPropertyListDetailsBinding? = null
    private val binding: FragmentPropertyListDetailsBinding get() = _binding!!

    private val viewModel: PropertyListingDetailsViewModel by viewModels()
    @Inject lateinit var sharedPreferences: SharedPreferences

    private var id: Int = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPropertyListDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        fetchData()
        initViews()
        initListeners()
        observeViewState()
    }

    private fun initListeners() {
        with(binding) {
            deletePropertyListingButton.setOnClickListener {
                viewModel.deleteAgentPropertyListing(id)
            }
        }
    }

    private fun initViews() {
        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
        if (token != null) {
            val user = Jwt(token).getUserData().role
            if (user == Constants.AGENT_SIGN_UP) {
                binding.deletePropertyListingButton.visible()
                binding.agentInfoView.gone()
            }
        }
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    showPropertyDetails(state.propertyListing)
                }
            }
        }

        lifecycleScope.launchWhenCreated {
            viewModel.deleteState.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    Log.i("TAG", "observeViewState: ${state.propertyListing}")
                }
            }
        }
    }

    private fun showPropertyDetails(propertyListing: PropertyListing?) {
        if (propertyListing != null) {
            with(binding) {
                val property = propertyListing.property
                val agent = propertyListing.agent

                val displayMetrics = DisplayMetrics()
                requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

                val width = displayMetrics.widthPixels
                val height = displayMetrics.heightPixels

                imageCarouselScrollView.layoutParams.height = height/3

                coverPhotoImageView.layoutParams.width = (width/1.2).toInt()
                coverPhotoImageView.load(Constants.BASE_URL_IMAGE+property.coverPhoto)

                photo1ImageView.layoutParams.width = (width/1.2).toInt()
                photo1ImageView.load(Constants.BASE_URL_IMAGE+property.photo1)

                photo2ImageView.layoutParams.width = (width/1.2).toInt()
                photo2ImageView.load(Constants.BASE_URL_IMAGE+property.photo2)

                photo3ImageView.layoutParams.width = (width/1.2).toInt()
                photo3ImageView.load(Constants.BASE_URL_IMAGE+property.photo3)

                photo4ImageView.layoutParams.width = (width/1.2).toInt()
                photo4ImageView.load(Constants.BASE_URL_IMAGE+property.photo4)

                tvApartmentTitle.text = property.title
                tvStreetAddress.text = property.streetAddress
                tvPropertyPrice.text = "$ " + property.price
                tvCountry.text = property.city + ", " + property.country
//                tvPropertyDescription.text = property.description

                tvPropertyDescription.setResizableText(property.description, 4, true)

                val plotAreaDouble = property.plotArea.toDouble()
                val bathroomsDouble = property.bathrooms.toDouble()
                bedroomsTextView.text = property.bedrooms.toString() + " beds"
                bathroomTextView.text = String.format("%.1f", bathroomsDouble) + " bath"
                squareFeetTextView.text = String.format("%.1f", plotAreaDouble) + " m"
                floorsTextView.text = property.totalFloors.toString() + " floor"

                agentProfileImageView.load(agent.profilePhoto)
                agentNameTextView.text = agent.firstName

                emailImageView.setOnClickListener {
                    try {
                        val intent = Intent(Intent.ACTION_SEND)
                        val text = getString(R.string.enquiry_whatsapp) + property.title + " in " +
                                property.streetAddress + "referred by " +
                                getString(R.string.app_name) + " App"
                        intent.putExtra(Intent.EXTRA_TEXT, text)

                        intent.putExtra(
                            "jid",
                            PhoneNumberUtils.stripSeparators(agent.phoneNumber.replaceFirstChar { "" }) +
                                    "@s.whatsapp.net"
                        )

                        intent.type = "text/plain"
                        intent.`package` = "com.whatsapp"
                        startActivity(Intent.createChooser(intent, "Share with"))
                    } catch (e: NameNotFoundException) {
                        Toast.makeText(
                            requireContext(),
                            "WhatsApp not Installed",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                }

                phoneCallImageView.setOnClickListener {

                    val callIntent = Intent(Intent.ACTION_DIAL)
                    callIntent.data = Uri.parse("tel:${agent.phoneNumber}")
                    startActivity(callIntent)
                }

            }
        }
    }

    private fun fetchData() {
        id = arguments?.getInt("id") ?: 0
        if (id != 0) {
            viewModel.showPropertyListingDetails(id)
        }
    }

    companion object {
        const val REQUEST_CODE = 1
    }
}
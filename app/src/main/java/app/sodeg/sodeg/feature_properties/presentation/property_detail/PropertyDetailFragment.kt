package app.sodeg.sodeg.feature_properties.presentation.property_detail

import android.content.SharedPreferences
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import coil.load
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.core.util.gone
import app.sodeg.sodeg.core.util.jwt.Jwt
import app.sodeg.sodeg.core.util.setResizableText
import app.sodeg.sodeg.core.util.visible
import app.sodeg.sodeg.databinding.FragmentPropertyDetailBinding
import app.sodeg.sodeg.feature_properties.domain.model.Property
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PropertyDetailFragment : Fragment() {

    private var _binding: FragmentPropertyDetailBinding? = null
    private val binding: FragmentPropertyDetailBinding get() = _binding!!

    @Inject lateinit var sharedPreferences: SharedPreferences

    private val viewModel: PropertyDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPropertyDetailBinding.inflate(inflater, container, false)
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
        binding.createPropertyListingBtn.setOnClickListener {
            val property = viewModel.state.value.property
            if (property != null) {
                viewModel.postAgentPropertyListing(property.slug)
            }
        }
    }

    private fun initViews() {
        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
        if (token != null) {
            val user = Jwt(token).getUserData().role
            if (user == Constants.AGENT_SIGN_UP) {
                binding.createPropertyListingBtn.visible()
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
                    showPropertyDetails(state.property)
                }
            }
        }
        lifecycleScope.launchWhenCreated {
            viewModel.propertyListingState.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    Log.i("TAG", "observeViewState: ${state.propertyListing}")
                }
            }
        }
    }

    private fun showPropertyDetails(property: Property?) {
        if (property != null) {
            with(binding) {
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

                // Inside showPropertyDetails()
                featuredBadgeImageView.visibility = View.VISIBLE
                verifiedBadgeImageView.visibility = View.VISIBLE

//                tvPropertyDescription.text = property.description

                tvPropertyDescription.setResizableText(property.description, 4, true)

                val plotAreaDouble = property.plotArea.toDouble()
                val bathroomsDouble = property.bathrooms.toDouble()
                bedroomsTextView.text = property.bedrooms.toString() + " beds"
                bathroomTextView.text = String.format("%.1f", bathroomsDouble) + " bath"
                squareFeetTextView.text = String.format("%.1f", plotAreaDouble) + " m"
                floorsTextView.text = property.totalFloors.toString() + " floor"

            }
        }
    }

    private fun fetchData() {
        val slug = arguments?.getString("slug")
        if (slug != null) {
            viewModel.showPropertyDetail(slug)
        }
    }

    companion object {

    }
}
package app.sodeg.sodeg.feature_properties.presentation.seller_property_detail

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.core.util.gone
import app.sodeg.sodeg.core.util.setResizableText
import app.sodeg.sodeg.core.util.visible
import app.sodeg.sodeg.databinding.FragmentSellerPropertyDetailBinding
import app.sodeg.sodeg.feature_properties.domain.model.Property
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SellerPropertyDetailFragment : Fragment() {

    private var _binding: FragmentSellerPropertyDetailBinding? = null
    private val binding: FragmentSellerPropertyDetailBinding get() = _binding!!

    private val viewModel: SellerPropertyDetailVM by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSellerPropertyDetailBinding.inflate(inflater, container, false)
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

    private fun initListeners() {
        with(binding) {

            updatePropertyButton.setOnClickListener {
                val bundle = Bundle()
                val property = viewModel.state.value.property
                if (property != null) {
                    bundle.putString("slug", property.slug)
                    bundle.putInt("user", property.user)
                    findNavController().navigate(
                        R.id.action_sellerPropertyDetailFragment_to_sellerPropertyUpdateFragment,
                        bundle
                    )
                }
            }

            deletePropertyButton.setOnClickListener {
                val property = viewModel.state.value.property
                if (property != null) {
                    viewModel.deleteSellerProperty(property.slug)
                    findNavController().navigate(
                        R.id.action_sellerPropertyDetailFragment_to_sellerPropertiesFragment
                    )
                }
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
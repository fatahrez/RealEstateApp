package app.sodeg.sodeg.feature_newProjects.presentation.new_project_details

import android.content.SharedPreferences
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.core.util.gone
import app.sodeg.sodeg.core.util.jwt.Jwt
import app.sodeg.sodeg.core.util.setResizableText
import app.sodeg.sodeg.core.util.visible
import app.sodeg.sodeg.databinding.FragmentNewProjectDetailsBinding
import app.sodeg.sodeg.feature_newProjects.domain.model.NewProject
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewProjectDetailsFragment : Fragment() {

    private var _binding: FragmentNewProjectDetailsBinding? = null
    private val binding: FragmentNewProjectDetailsBinding get() = _binding!!

    @Inject lateinit var sharedPreferences: SharedPreferences
    private val viewModel: NewProjectDetailsViewModel by viewModels()


    var slug = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewProjectDetailsBinding.inflate(inflater, container, false)
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
            val bundle = Bundle()
            bundle.putString("slug", slug)
            updateNewProjectBtn.setOnClickListener {
                findNavController().navigate(
                    R.id.action_newProjectDetailsFragment_to_updateNewProjectFragment,
                    bundle
                )
            }
        }
    }

    private fun initViews() {
        with(binding) {
            val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
            if (token != null) {
                val role = Jwt(token).getUserData().role
                if (role == Constants.PROJECTBUILDER_SIGN_UP) {
                    updateNewProjectBtn.visible()
                }
            }
        }
    }

    private fun fetchData() {
        slug = arguments?.getString("slug") ?: ""
        if (slug.isNotEmpty()) {
            viewModel.showNewProjectDetails(slug)
        }
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    binding.progressBar.visible()
                } else {
                    binding.progressBar.gone()
                    showNewProjectDetails(state.newProject)
                }
            }
        }
    }

    private fun showNewProjectDetails(newProject: NewProject?) {
        with(binding) {
            if (newProject != null) {
                with(binding) {
                    val displayMetrics = DisplayMetrics()
                    requireActivity().windowManager.defaultDisplay.getMetrics(displayMetrics)

                    val width = displayMetrics.widthPixels
                    val height = displayMetrics.heightPixels

                    imageCarouselScrollView.layoutParams.height = height/3

                    coverPhotoImageView.layoutParams.width = (width/1.2).toInt()
                    coverPhotoImageView.load(Constants.BASE_URL_IMAGE+newProject.coverPhoto)

                    photo1ImageView.layoutParams.width = (width/1.2).toInt()
                    photo1ImageView.load(Constants.BASE_URL_IMAGE+newProject.photo1)

                    photo2ImageView.layoutParams.width = (width/1.2).toInt()
                    photo2ImageView.load(Constants.BASE_URL_IMAGE+newProject.photo2)

//                    photo3ImageView.layoutParams.width = (width/1.2).toInt()
//                    photo3ImageView.load(Constants.BASE_URL_IMAGE+newProject.photo3)
//
//                    photo4ImageView.layoutParams.width = (width/1.2).toInt()
//                    photo4ImageView.load(Constants.BASE_URL_IMAGE+newProject.photo4)

                    tvApartmentTitle.text = newProject.name
                    tvStreetAddress.text = newProject.location
                    tvPropertyPrice.text = "$ " + newProject.price
                    tvCountry.text = newProject.city + ", " + newProject.country
//                tvPropertyDescription.text = property.description

                    tvPropertyDescription.setResizableText(newProject.description, 4, true)

                    val plotAreaDouble = newProject.squareFeet.toDouble()
                    val bathroomsDouble = newProject.bathrooms.toDouble()
                    bedroomsTextView.text = newProject.bedrooms.toString() + " beds"
                    bathroomTextView.text = String.format("%.1f", bathroomsDouble) + " bath"
                    squareFeetTextView.text = String.format("%.1f", plotAreaDouble) + " m"

                }
            }
        }
    }

    companion object {

    }
}
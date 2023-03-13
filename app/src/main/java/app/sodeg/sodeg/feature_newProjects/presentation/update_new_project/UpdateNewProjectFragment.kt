package app.sodeg.sodeg.feature_newProjects.presentation.update_new_project

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.core.util.gone
import app.sodeg.sodeg.core.util.visible
import app.sodeg.sodeg.databinding.FragmentUpdateNewProjectBinding
import app.sodeg.sodeg.feature_newProjects.domain.model.NewProject
import app.sodeg.sodeg.feature_newProjects.presentation.new_project_details.NewProjectDetailsViewModel
import app.sodeg.sodeg.feature_properties.presentation.post_property.PostPropertyFragment.Companion.PICK_IMAGE_REQUEST_CODE
import app.sodeg.sodeg.feature_properties.presentation.post_property.PostPropertyFragment.Companion.REQUEST_CODE_READ_EXTERNAL_STORAGE
import app.sodeg.sodeg.workers.UpdateNewProjectWorker
import app.sodeg.sodeg.workers.UploadNewProjectWorker
import coil.load
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpdateNewProjectFragment : Fragment() {

    private var _binding: FragmentUpdateNewProjectBinding? = null
    private val binding: FragmentUpdateNewProjectBinding get() = _binding!!

    private val viewModel: NewProjectDetailsViewModel by viewModels()
    var slug = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUpdateNewProjectBinding.inflate(inflater, container, false)
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

    private fun observeViewState() {
        lifecycleScope.launch {
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
                nameEditText.setText(newProject.name)
                locationEditText.setText(newProject.location)
                descriptionEditText.setText(newProject.description)
                priceEditText.setText(newProject.price)
                bedroomsEditText.setText(newProject.bedrooms.toString())
                bathroomsEditText.setText(newProject.bathrooms.toString())
                squareFeetEditText.setText(newProject.squareFeet.toString())
                cityEditText.setText(newProject.city)
                val constructionArray = arrayOf(R.array.constructionStatus)
                constructionStatusSpinner.setSelection(
                    constructionArray.find { it.equals(newProject.constructionStatus) } ?: 0
                )
                completionDateEditText.setText(newProject.completionDate)
                val propertyTypeArray = arrayOf(R.array.propertyType)
                propertyTypeSpinner.setSelection(
                    propertyTypeArray.find { it.equals(newProject.propertyType) } ?: 0
                )
            }
        }
    }

    private fun fetchData() {
        slug = arguments?.getString("slug") ?: ""
        if (slug.isNotEmpty()) {
            viewModel.showNewProjectDetails(slug)
        }
    }

    private fun initListeners() {
        with(binding) {
            postNewProjectButton.setOnClickListener {
                val name = nameEditText.text.toString()
                val location = locationEditText.text.toString()
                val description = descriptionEditText.text.toString()
                val price = priceEditText.text.toString()
                val bedrooms = bedroomsEditText.text.toString().toInt()
                val bathrooms = bathroomsEditText.text.toString().toInt()
                val squareFeet = squareFeetEditText.text.toString().toInt()
                val country = countryEditText.textView_selectedCountry.text.toString()
                val city = cityEditText.text.toString()
                val constructionStatus = constructionStatusSpinner.selectedItem.toString()
                val completionDate = completionDateEditText.text.toString()
                val propertyType = propertyTypeSpinner.selectedItem.toString()

                val inputData = Data.Builder()
                    .putString("slug", slug)
                    .putString("name", name)
                    .putString("location", location)
                    .putString("description", description)
                    .putString("price", price)
                    .putInt("bedrooms", bedrooms)
                    .putInt("bathrooms", bathrooms)
                    .putInt("squareFeet", squareFeet)
                    .putString("country", country)
                    .putString("city", city)
                    .putString("constructionStatus", constructionStatus)
                    .putString("completionDate", completionDate)
                    .putString("propertyType", propertyType)
                    .putInt("user", arguments?.
                        getInt("user", 0) ?: 0)
                    .build()

                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()

                val uploadRequest = OneTimeWorkRequestBuilder<UpdateNewProjectWorker>()
                    .setConstraints(constraints)
                    .setInputData(inputData)
                    .addTag("update_new_project")
                    .build()

                WorkManager.getInstance(requireContext()).enqueue(uploadRequest)

            }
        }
    }

    private fun initViews() {
        with(binding) {
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.propertyType,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                propertyTypeSpinner.adapter = adapter
            }

            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.constructionStatus,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                constructionStatusSpinner.adapter = adapter
            }
        }
    }


    companion object {

    }
}
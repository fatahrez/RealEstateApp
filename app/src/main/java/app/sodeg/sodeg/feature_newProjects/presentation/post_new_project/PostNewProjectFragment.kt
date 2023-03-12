package app.sodeg.sodeg.feature_newProjects.presentation.post_new_project

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
import androidx.work.*
import app.sodeg.sodeg.R
import app.sodeg.sodeg.databinding.FragmentPostNewProjectBinding
import app.sodeg.sodeg.feature_properties.presentation.post_property.PostPropertyFragment.Companion.PICK_IMAGE_REQUEST_CODE
import app.sodeg.sodeg.feature_properties.presentation.post_property.PostPropertyFragment.Companion.REQUEST_CODE_READ_EXTERNAL_STORAGE
import app.sodeg.sodeg.workers.UploadNewProjectWorker


class PostNewProjectFragment : Fragment() {

    private var _binding: FragmentPostNewProjectBinding? = null
    private val binding: FragmentPostNewProjectBinding get() = _binding!!

    private val selectedUris = mutableListOf<Uri>()

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
        _binding = FragmentPostNewProjectBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        initViews()
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            coverPhotoImageView.setOnClickListener {
                pickPhoto()
            }

            photo1ImageView.setOnClickListener {
                pickPhoto()
            }

            photo2ImageView.setOnClickListener {
                pickPhoto()
            }

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
                    .putString("coverPhoto", selectedUris[0].toString())
                    .putString("photo1", selectedUris[1].toString())
                    .putString("photo2", selectedUris[2].toString())
                    .build()

                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()

                val uploadRequest = OneTimeWorkRequestBuilder<UploadNewProjectWorker>()
                    .setConstraints(constraints)
                    .setInputData(inputData)
                    .addTag("upload_new_project")
                    .build()

                WorkManager.getInstance(requireContext()).enqueue(uploadRequest)

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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val uri = data?.data
            if (uri != null) {
                selectedUris.add(uri)

                when(selectedUris.size) {
                    1 -> binding.coverPhotoImageView.setImageURI(uri)
                    2 -> binding.photo1ImageView.setImageURI(uri)
                    3 -> binding.photo2ImageView.setImageURI(uri)
                }
            }
        }
    }

    companion object {

    }
}
package com.example.guryihii.feature_properties.presentation.post_property

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.work.*
import com.example.guryihii.R
import com.example.guryihii.core.workers.UploadPropertyWorker
import com.example.guryihii.databinding.FragmentPostPropertyBinding
import com.example.guryihii.feature_properties.domain.model.Property
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


@AndroidEntryPoint
class PostPropertyFragment : Fragment() {
    private var _binding: FragmentPostPropertyBinding? = null
    private val binding: FragmentPostPropertyBinding get() = _binding!!

    private val viewModel: PostPropertyViewModel by viewModels()

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
        // Inflate the layout for this fragment
        _binding = FragmentPostPropertyBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        initViews()
        initListeners()
        observeViewState()
    }

    private fun initViews() {
        with(binding) {
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.advertType,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                advertTypeSpinner.adapter = adapter
            }

            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.propertyType,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                propertyTypeSpinner.adapter = adapter
            }
        }
    }

    private fun observeViewState() {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    Log.i("TAG", "observeViewState: loading ...")
                } else {
                    Log.i("TAG", "observeViewState: ${state.property}")
                }
            }
        }
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

            photo3ImageView.setOnClickListener {
                pickPhoto()
            }

            photo4ImageView.setOnClickListener {
                pickPhoto()
            }

            postPropertyButton.setOnClickListener {
                val advertType = advertTypeSpinner.selectedItem.toString()
                val bathrooms = bathroomsEditText.text.toString()
                val bedrooms = bedroomsEditText.text.toString().toInt()
                val city = cityEditText.text.toString()
                val country = countryEditText.text.toString().lowercase()
                val description = descriptionEditText.text.toString()
                val plotArea = plotAreaEditText.text.toString()
                val postalCode = postalCodeEditText.text.toString()
                val price = priceEditText.text.toString()
                val propertyNumber = propertyNumberEditText.text.toString()
                val propertyType = propertyTypeSpinner.selectedItem.toString()
                val streetAddress = streetAddressEditText.text.toString()
                val title = titleEditText.text.toString()
                val totalFloors = totalFloorsEditText.text.toString().toInt()

                val inputData = Data.Builder()
                    .putString("advertType", advertType)
                    .putString("bathrooms", bathrooms)
                    .putInt("bedrooms", bedrooms)
                    .putString("city", city)
                    .putString("country", country)
                    .putString("coverPhoto", selectedUris[0].toString())
                    .putString("description", description)
                    .putString("photo1", selectedUris[0].toString())
                    .putString("photo2", selectedUris[1].toString())
                    .putString("photo3", selectedUris[2].toString())
                    .putString("photo4", selectedUris[3].toString())
                    .putString("plotArea", plotArea)
                    .putString("postalCode", postalCode)
                    .putString("price", price)
                    .putString("propertyNumber", propertyNumber)
                    .putString("propertyType", propertyType)
                    .putString("streetAddress", streetAddress)
                    .putString("title", title)
                    .putInt("totalFloors", totalFloors)
                    .build()

                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()

                val uploadRequest = OneTimeWorkRequestBuilder<UploadPropertyWorker>()
                    .setConstraints(constraints)
                    .setInputData(inputData)
                    .addTag("upload_property")
                    .build()

                WorkManager.getInstance(requireContext()).enqueue(uploadRequest)
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
                    4 -> binding.photo3ImageView.setImageURI(uri)
                    5 -> binding.photo4ImageView.setImageURI(uri)
                }
            }
        }
    }

    private fun pickPhoto() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST_CODE)
    }

    companion object {
        const val REQUEST_CODE_READ_EXTERNAL_STORAGE = 1
        const val PICK_IMAGE_REQUEST_CODE = 1
    }
}
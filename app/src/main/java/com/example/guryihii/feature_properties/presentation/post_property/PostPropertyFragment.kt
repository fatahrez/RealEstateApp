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
import com.example.guryihii.R
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
                val contentResolver = requireContext().contentResolver

                val advertType = advertTypeSpinner.selectedItem.toString()
                val bathrooms = bathroomsEditText.text.toString()
                val bedrooms = bedroomsEditText.text.toString().toInt()
                val city = cityEditText.text.toString()
                val country = countryEditText.text.toString().lowercase()

                val coverPhotoInputStream = contentResolver.openInputStream(selectedUris[0])
                val coverPhotoBytes = coverPhotoInputStream?.readBytes()
                val coverPhotoRequestFile = coverPhotoBytes?.let { it1 ->
                    RequestBody
                        .create("multipart/form-data".toMediaTypeOrNull(), it1)
                }
                val coverPhoto = coverPhotoRequestFile?.let { it1 ->
                    MultipartBody.Part.createFormData("file", "filename",
                        it1
                    )
                }


                val description = descriptionEditText.text.toString()

                val photo1InputStream = contentResolver.openInputStream(selectedUris[1])
                val photo1Bytes = photo1InputStream?.readBytes()
                val photo1RequestFile = photo1Bytes?.let { it1 ->
                    RequestBody
                        .create("multipart/form-data".toMediaTypeOrNull(), it1)
                }
                val photo1 = photo1RequestFile?.let { it1 ->
                    MultipartBody.Part.createFormData("file", "filename",
                        it1
                    )
                }

                val photo2InputStream = contentResolver.openInputStream(selectedUris[1])
                val photo2Bytes = photo2InputStream?.readBytes()
                val photo2RequestFile = photo2Bytes?.let { it1 ->
                    RequestBody
                        .create("multipart/form-data".toMediaTypeOrNull(), it1)
                }
                val photo2 = photo2RequestFile?.let { it1 ->
                    MultipartBody.Part.createFormData("file", "filename",
                        it1
                    )
                }

                val photo3InputStream = contentResolver.openInputStream(selectedUris[1])
                val photo3Bytes = photo3InputStream?.readBytes()
                val photo3RequestFile = photo3Bytes?.let { it1 ->
                    RequestBody
                        .create("multipart/form-data".toMediaTypeOrNull(), it1)
                }
                val photo3 = photo3RequestFile?.let { it1 ->
                    MultipartBody.Part.createFormData("file", "filename",
                        it1
                    )
                }

                val photo4InputStream = contentResolver.openInputStream(selectedUris[1])
                val photo4Bytes = photo4InputStream?.readBytes()
                val photo4RequestFile = photo4Bytes?.let { it1 ->
                    RequestBody
                        .create("multipart/form-data".toMediaTypeOrNull(), it1)
                }
                val photo4 = photo4RequestFile?.let { it1 ->
                    MultipartBody.Part.createFormData("file", "filename",
                        it1
                    )
                }

                val plotArea = plotAreaEditText.text.toString()
                val postalCode = postalCodeEditText.text.toString()
                val price = priceEditText.text.toString()
                val propertyNumber = propertyNumberEditText.text.toString()
                val propertyType = propertyTypeSpinner.selectedItem.toString()
                val streetAddress = streetAddressEditText.text.toString()
                val title = titleEditText.text.toString()
                val totalFloors = totalFloorsEditText.text.toString().toInt()

                if (coverPhoto != null && photo1 != null && photo2 != null
                    && photo3 != null && photo4 != null) {
                    viewModel.postSellerProperty(
                        advertType,
                        bathrooms,
                        bedrooms,
                        city,
                        country,
                        coverPhoto,
                        description,
                        photo1,
                        photo2,
                        photo3,
                        photo4,
                        plotArea,
                        postalCode,
                        price,
                        propertyNumber,
                        propertyType,
                        streetAddress,
                        title,
                        totalFloors
                    )
                }
            }

        }
//        val property = Property(
//            "For Sale",
//            "3.5",
//            6,
//            "Nairobi",
//            "kenya",
//            "/mediafiles/bc3cbfda-4f47-4fb6-86b9-2da5996ae483-0.jpg.webp",
//            "The villa is an incredible opportunity to own a stunning home within the prestigious vipingo ridge.\n" +
//                    "\n" +
//                    "the design of the villa is influenced by arabic and swahili contemporary themes that combines a sociable neighbourhood atmosphere with superior lifestyle facilities. it features a fully furnished large open plan living area and lounge which looks out to the beautiful scenery of the garden. adjoining the living room is dining room which is accompanied by an open-plan kitchen with built-in modern appliances. also on the ground floor is a 2 large ensuite bedrooms with balconies. ",
//            1,
//            "/mediafiles/bc3cbfda-4f47-4fb6-86b9-2da5996ae483-0.jpg.webp",
//            "/mediafiles/bc3cbfda-4f47-4fb6-86b9-2da5996ae483-0.jpg.webp",
//            "/mediafiles/bc3cbfda-4f47-4fb6-86b9-2da5996ae483-0.jpg.webp",
//            "/mediafiles/bc3cbfda-4f47-4fb6-86b9-2da5996ae483-0.jpg.webp",
//            "100.00",
//            "1220-00100",
//            "10000",
//            "/mediafiles/bc3cbfda-4f47-4fb6-86b9-2da5996ae483-0.jpg.webp",
//            12,
//            "House",
//            true,
//            "hsdshfjkd",
//            "skjgsg",
//            "ngara",
//            "4 bedroom ngara",
//            4,
//            1,
//            2
//        )
//        viewModel.postSellerProperty(property)
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
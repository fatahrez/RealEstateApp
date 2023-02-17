package com.example.guryihii.feature_properties.presentation.post_property

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
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


@AndroidEntryPoint
class PostPropertyFragment : Fragment() {
    private var _binding: FragmentPostPropertyBinding? = null
    private val binding: FragmentPostPropertyBinding get() = _binding!!

    private val viewModel: PostPropertyViewModel by viewModels()

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
            postPropertyButton.setOnClickListener {
                val advertType = advertTypeSpinner.selectedItem.toString()
                val bathrooms = bathroomsEditText.text.toString()
                val bedrooms = bedroomsEditText.text
                val city = cityEditText.text.toString()
                val country = countryEditText.text.toString().lowercase()
//                val coverPhoto
                val description = descriptionEditText.text.toString()
//                val photo1 =
//                val photo2
//                val photo3
//                val photo4
                val plotArea = plotAreaEditText.text.toString()
                val postalCode = postalCodeEditText.text.toString()
                val price = priceEditText.text.toString()
                val propertyNumber = propertyNumberEditText.text.toString()
                val propertyType = propertyTypeSpinner.selectedItem.toString()
                val streetAddress = streetAddressEditText.text.toString()
                val title = titleEditText.text.toString()
                val totalFloors = totalFloorsEditText.text
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_READ_EXTERNAL_STORAGE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {

            }
        }

    }

    companion object {
        const val REQUEST_CODE_READ_EXTERNAL_STORAGE = 1
    }
}
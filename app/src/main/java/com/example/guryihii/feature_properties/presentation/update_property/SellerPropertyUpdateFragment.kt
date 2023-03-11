package com.example.guryihii.feature_properties.presentation.update_property

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.work.*
import com.example.guryihii.databinding.FragmentSellerPropertyUpdateBinding
import com.example.guryihii.workers.UpdatePropertyWorker
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SellerPropertyUpdateFragment : Fragment() {

    private var _binding: FragmentSellerPropertyUpdateBinding? = null
    private val binding: FragmentSellerPropertyUpdateBinding get() = _binding!!

    private var slug = ""
    private var user = 0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSellerPropertyUpdateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        fetchData()
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            updatePropertyButton.setOnClickListener {
                val propertyTitle = titleEditText.text.toString()
                val country = countryEditText.text.toString()
                val inputData = Data.Builder()
                    .putString("slug", slug)
                    .putInt("user", user)
                    .putString("title", propertyTitle)
                    .putString("country", country)
                    .build()

                val constraints = Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()

                val updatePropertyRequest = OneTimeWorkRequestBuilder<UpdatePropertyWorker>()
                    .setConstraints(constraints)
                    .setInputData(inputData)
                    .addTag("update_property")
                    .build()

                WorkManager.getInstance(requireContext()).enqueue(updatePropertyRequest)
            }
        }
    }

    private fun fetchData() {
        slug = arguments?.getString("slug").toString()
        user = arguments?.getInt("user") ?: 0
    }

    companion object {

    }
}
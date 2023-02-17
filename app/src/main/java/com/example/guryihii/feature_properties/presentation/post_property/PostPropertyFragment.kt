package com.example.guryihii.feature_properties.presentation.post_property

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        initListeners()
        observeViewState()
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
        val property = Property(
            "FOR_SALE",
            "3.5",
            6,
            "Nairobi",
            "kenya",
            "/mediafiles/bc3cbfda-4f47-4fb6-86b9-2da5996ae483-0.jpg.webp",
            "The villa is an incredible opportunity to own a stunning home within the prestigious vipingo ridge.\n" +
                    "\n" +
                    "the design of the villa is influenced by arabic and swahili contemporary themes that combines a sociable neighbourhood atmosphere with superior lifestyle facilities. it features a fully furnished large open plan living area and lounge which looks out to the beautiful scenery of the garden. adjoining the living room is dining room which is accompanied by an open-plan kitchen with built-in modern appliances. also on the ground floor is a 2 large ensuite bedrooms with balconies. ",
            1,
            "/mediafiles/bc3cbfda-4f47-4fb6-86b9-2da5996ae483-0.jpg.webp",
            "/mediafiles/bc3cbfda-4f47-4fb6-86b9-2da5996ae483-0.jpg.webp",
            "/mediafiles/bc3cbfda-4f47-4fb6-86b9-2da5996ae483-0.jpg.webp",
            "/mediafiles/bc3cbfda-4f47-4fb6-86b9-2da5996ae483-0.jpg.webp",
            "100.00",
            "1220-00100",
            "10000",
            "/mediafiles/bc3cbfda-4f47-4fb6-86b9-2da5996ae483-0.jpg.webp",
            12,
            "HOUSE",
            true,
            "hsdshfjkd",
            "skjgsg",
            "ngara",
            "4 bedroom ngara",
            4,
            1,
            2
        )
        viewModel.postSellerProperty(property)
    }

    companion object {

    }
}
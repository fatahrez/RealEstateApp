package app.sodeg.sodeg.feature_properties.presentation.seller_properties

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import app.sodeg.sodeg.R
import app.sodeg.sodeg.core.util.Constants
import app.sodeg.sodeg.core.util.jwt.Jwt
import app.sodeg.sodeg.core.util.visible
import app.sodeg.sodeg.databinding.FragmentSellerPropertiesBinding
import app.sodeg.sodeg.feature_properties.domain.model.Property
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SellerPropertiesFragment : Fragment() {

    private var _binding: FragmentSellerPropertiesBinding? = null
    private val binding: FragmentSellerPropertiesBinding get() = _binding!!

    @Inject
    lateinit var sharedPreferences: SharedPreferences
    private val viewModel: SellerPropertiesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSellerPropertiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        initViews()
        val adapter = createAdapter()
        setupRecyclerView(adapter)
        initListeners()
        observeViewState(adapter)
    }

    private fun initListeners() {
        with(binding) {
            floatingActionButton.setOnClickListener {
                findNavController().navigate(
                    R.id.action_propertyFragment_to_postPropertyFragment,
                    null
                )
            }
        }
    }

    private fun setupRecyclerView(sellerPropertiesAdapter: SellerPropertiesAdapter) {
        binding.recyclerView.apply {
            adapter = sellerPropertiesAdapter
            setHasFixedSize(true)
        }
    }

    private fun createAdapter(): SellerPropertiesAdapter {
        return SellerPropertiesAdapter (
            clickListener = {
                navToSellerPropertiesAdapter(it)
            },
            requireContext()
        )
    }

    private fun navToSellerPropertiesAdapter(property: Property) {
        val bundle = Bundle()
        bundle.putString("slug", property.slug)
        findNavController().navigate(
            R.id.action_sellerPropertiesFragment_to_sellerPropertyDetailFragment,
            bundle
        )
    }

    private fun initViews() {
        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
        if (token != null) {
            val loggedInUser = Jwt(token).getUserData().role
            if (loggedInUser == Constants.SELLER_SIGN_UP) {
                binding.floatingActionButton.visible()
            }
        }
    }

    private fun observeViewState(adapter: SellerPropertiesAdapter) {
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect { state ->
                if (state.isLoading) {
                    Log.i("TAG", "observeViewState: loading...")
                } else {
                    adapter.submitList(state.properties)
                }
            }
        }
    }

    companion object {

    }
}
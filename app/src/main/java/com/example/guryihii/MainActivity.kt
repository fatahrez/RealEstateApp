package com.example.guryihii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.guryihii.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

//   @Inject lateinit var propertiesService: PropertiesService

   private val navController: NavController by lazy {
       val navHostFragment = supportFragmentManager
           .findFragmentById(R.id.nav_graph) as NavHostFragment
       navHostFragment.navController
   }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setupUI()

//        refreshData()
//        setupListeners()

        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId) {
                R.id.properties -> {
                    navController.navigate(R.id.propertyFragment, null)
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    private fun setupUI() {

    }

//    private fun refreshData() {
//        lifecycleScope.launchWhenStarted {
//            val response = propertiesService.getAllProperties()
//            binding.propertyImageView.load(
//                data = "https://plus.unsplash.com/premium_photo-1661883982941-50af7720a6ff?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80"
//            ){
//                listener{request, result ->
//                    binding.productImageViewLoadingProgressBar.isGone = true
//                }
//            }
//            Log.i("DATA", response.body()!!.toString())
//        }
//    }

//    private fun setupListeners() {
//        var publishedStatus = false
//        binding.favImageView.setOnClickListener {
//            val imageRes = if (publishedStatus){
//                R.drawable.favourite
//            } else{
//                R.drawable.fav
//            }
//            binding.favImageView.setIconResource(imageRes)
//            publishedStatus = !publishedStatus
//        }
//    }

}


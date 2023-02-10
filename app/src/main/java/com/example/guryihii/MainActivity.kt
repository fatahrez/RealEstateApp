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
                R.id.agents -> {
                    navController.navigate(R.id.agentListFragment, null)
                    true
                }
                R.id.newProjects -> {
                    navController.navigate(R.id.newProjectsFragment2, null)
                    true
                }
                R.id.profile -> {
                    navController.navigate(R.id.profileFragment2, null)
                    true
                }
                R.id.menu -> {
                    navController.navigate(R.id.menuFragment2, null)
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

}


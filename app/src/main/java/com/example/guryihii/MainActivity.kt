package com.example.guryihii

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.content.edit
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.util.jwt.Jwt
import com.example.guryihii.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

   private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        updateNavigation()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.END)

        when(item.itemId) {
            R.id.sign_in -> {
                navController.navigate(R.id.signInFragment, null)
            }
            R.id.sign_up -> {
                navController.navigate(R.id.signUpFragment, null)
            }
            R.id.request_property -> {
                navController.navigate(R.id.requestPropertyFragment, null)
            }
            R.id.logout -> {
                logout()
            }
        }
        return true
    }

    fun updateNavigation() {
        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
        if (token != null) {
            val tokenDecoder = Jwt(token)
            val userRole = tokenDecoder.getUserData().role
            val navGraphId = when(userRole) {
                Constants.INDIVIDUAL_SIGN_UP -> R.navigation.nav_graph
                Constants.AGENT_SIGN_UP -> R.navigation.agent_nav_graph
                Constants.SELLER_SIGN_UP -> R.navigation.seller_nav_graph
                Constants.PROJECTBUILDER_SIGN_UP -> R.navigation.project_builder_nav_graph
                else -> R.navigation.nav_graph
            }

            val menuResId = when(userRole) {
                Constants.INDIVIDUAL_SIGN_UP -> R.menu.bottom_navigation_menu
                Constants.AGENT_SIGN_UP -> R.menu.agent_bottom_navigation_menu
                Constants.SELLER_SIGN_UP -> R.menu.seller_bottom_navigation_menu
                Constants.PROJECTBUILDER_SIGN_UP -> R.menu.project_builder_navigation_menu
                else -> R.menu.bottom_navigation_menu
            }
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph) as NavHostFragment
            navController = navHostFragment.navController
            navController.setGraph(navGraphId)

            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNavigationView.menu.clear()
            bottomNavigationView.inflateMenu(menuResId)
            bottomNavigationView.setupWithNavController(navController)

            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
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
                        binding.drawerLayout.openDrawer(GravityCompat.END)
                        true
                    }
                    R.id.seller_posts -> {
                        navController.navigate(R.id.propertyFragment, null)
                        true
                    }
                    R.id.my_properties -> {
                        navController.navigate(R.id.sellerPropertiesFragment, null)
                        true
                    }
                    R.id.my_listings -> {
                        navController.navigate(R.id.myListingsFragment, null)
                        true
                    }
                    R.id.agent_listings -> {
                        navController.navigate(R.id.agentListingsFragment, null)
                        true
                    }
                    R.id.all_properties -> {
                        navController.navigate(R.id.allPropertyListingsFragment, null)
                        true
                    }
                    R.id.buyer_request -> {
                        navController.navigate(R.id.propertyRequestsFragment)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
            binding.navView.setNavigationItemSelectedListener(this)
            binding.navView.menu.findItem(R.id.logout).isVisible = true
            binding.navView.menu.findItem(R.id.sign_in).isVisible = false
            binding.navView.menu.findItem(R.id.sign_up).isVisible = false
            binding.navView.menu.findItem(R.id.request_property).isVisible =
                !(userRole == Constants.AGENT_SIGN_UP ||
                    userRole == Constants.SELLER_SIGN_UP ||
                    userRole == Constants.PROJECTBUILDER_SIGN_UP)
        } else {
            val navGraphId = R.navigation.nav_graph
            val menuResId = R.menu.bottom_navigation_menu
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph) as NavHostFragment
            navController = navHostFragment.navController
            navController.setGraph(navGraphId)

            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
            bottomNavigationView.menu.clear()
            bottomNavigationView.inflateMenu(menuResId)
            bottomNavigationView.setupWithNavController(navController)

            bottomNavigationView.setOnNavigationItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.all_properties -> {
                        navController.navigate(R.id.allPropertyListingsFragment, null)
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
                        binding.drawerLayout.openDrawer(GravityCompat.END)
                        true
                    }
                    else -> {
                        false
                    }
                }
            }
            binding.navView.setNavigationItemSelectedListener(this)
            binding.navView.menu.findItem(R.id.logout).isVisible = false
            binding.navView.menu.findItem(R.id.sign_in).isVisible = true
            binding.navView.menu.findItem(R.id.sign_up).isVisible = true
            binding.navView.menu.findItem(R.id.request_property).isVisible = true
        }
    }

    private fun logout() {
        sharedPreferences.edit {
            putString(Constants.ACCESS_TOKEN, null)
            putString(Constants.REFRESH_TOKEN, null)
        }
        updateNavigation()
        binding.drawerLayout.closeDrawer(GravityCompat.END)
    }
}


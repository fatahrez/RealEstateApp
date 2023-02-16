package com.example.guryihii

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
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

        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
        if (token != null) {
            val tokenDecoder = Jwt(token)
            val navGraphId = when(tokenDecoder.getUserData().role) {
                Constants.INDIVIDUAL_SIGN_UP -> R.navigation.nav_graph
                Constants.AGENT_SIGN_UP -> R.navigation.agent_nav_graph
                else -> R.navigation.nav_graph
            }

            val menuResId = when(tokenDecoder.getUserData().role) {
                Constants.INDIVIDUAL_SIGN_UP -> R.menu.bottom_navigation_menu
                Constants.AGENT_SIGN_UP -> R.menu.agent_bottom_navigation_menu
                else -> R.menu.bottom_navigation_menu
            }

            setContentView(view)
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph) as NavHostFragment
            navController = navHostFragment.navController
            navController.setGraph(navGraphId)

            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
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
                    else -> {
                        false
                    }
                }
            }
            binding.navView.setNavigationItemSelectedListener(this)
        } else {
            val navGraphId = R.navigation.nav_graph
            val menuResId = R.menu.bottom_navigation_menu
            setContentView(view)
            val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_graph) as NavHostFragment
            navController = navHostFragment.navController
            navController.setGraph(navGraphId)

            val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
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
                    else -> {
                        false
                    }
                }
            }
            binding.navView.setNavigationItemSelectedListener(this)
        }
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
            R.id.logout -> {
                logout()
            }
        }
        return true
    }

    private fun logout() {
        sharedPreferences.edit {
            putString(Constants.ACCESS_TOKEN, null)
            putString(Constants.REFRESH_TOKEN, null)
        }
        binding.drawerLayout.closeDrawer(GravityCompat.END)
    }
}


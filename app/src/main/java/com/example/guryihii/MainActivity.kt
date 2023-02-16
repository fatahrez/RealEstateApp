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
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.guryihii.core.util.Constants
import com.example.guryihii.core.util.jwt.Jwt
import com.example.guryihii.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var sharedPreferences: SharedPreferences

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

        val token = sharedPreferences.getString(Constants.ACCESS_TOKEN, null)
        if (token != null) {
            val tokenDecoder = Jwt(token)
            binding.navView.menu.findItem(R.id.logout).isVisible = true
            binding.navView.menu.findItem(R.id.sign_in).isVisible = false
            binding.navView.menu.findItem(R.id.sign_up).isVisible = false
        } else {
            binding.navView.menu.findItem(R.id.logout).isVisible = false
            binding.navView.menu.findItem(R.id.sign_in).isVisible = true
            binding.navView.menu.findItem(R.id.sign_up).isVisible = true
        }
        setupUI()

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

    private fun setupUI() {

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
//        navController.navigate(R.id.)
        binding.drawerLayout.closeDrawer(GravityCompat.END)
    }

}


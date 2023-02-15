package com.example.guryihii

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.guryihii.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
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
        Log.i("TAG", "onNavigationItemSelected: click 1")
        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
        drawer.closeDrawer(GravityCompat.END)
        when(item.itemId) {
            R.id.sign_in -> {
                Log.i("TAG", "onNavigationItemSelected: click 2")
                navController.navigate(R.id.signInFragment, null)
            }
//            R.id.sign_up -> {
//
//            }
        }
        return true
    }

}


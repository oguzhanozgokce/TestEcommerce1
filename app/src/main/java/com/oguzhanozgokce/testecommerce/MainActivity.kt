package com.oguzhanozgokce.testecommerce

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.oguzhanozgokce.testecommerce.databinding.ActivityMainBinding
import com.oguzhanozgokce.testecommerce.ui.login.util.UserSessionManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Setup view binding

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    @Inject
    lateinit var userSessionManager: UserSessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Navigation and menu bar setup
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment)!! as NavHostFragment
        navController = navHostFragment.findNavController()
        val bottomNavigation = binding.bottomNavigation
        bottomNavigation.setupWithNavController(navController)
        //click listener for menu items
        bottomNavigation.setOnItemSelectedListener { item ->
            if (userSessionManager.isUserLoggedIn()) {
                NavigationUI.onNavDestinationSelected(item, navController)
            } else {
                // Prevent navigation and inform the user.
                Toast.makeText(this, "You must log in first!", Toast.LENGTH_SHORT).show()
                false
                // Return false to prevent navigation.
            }
        }

        checkIfUserLoggedIn()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun checkIfUserLoggedIn() {
        val isLoggedIn = userSessionManager.isUserLoggedIn()
        Log.e("MainActivity", "User logged in: $isLoggedIn")
        //binding.bottomNavigation.visibility = if (isLoggedIn) View.VISIBLE else View.GONE

        if (isLoggedIn) {
            // Log and navigate to the home screen
            Log.e("MainActivity", "Navigating to HomeFragment")
            navController.navigate(R.id.homeFragment)
        } else {
            // Log and navigate to the login screen
            Log.e("MainActivity", "Navigating to LoginFragment")
            navController.navigate(R.id.loginFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        val isLoggedIn = userSessionManager.isUserLoggedIn()
        binding.bottomNavigation.visibility = if (isLoggedIn) View.VISIBLE else View.GONE
        updateBottomNavigationVisibility()
    }
    fun updateBottomNavigationVisibility() {
        val isLoggedIn = userSessionManager.isUserLoggedIn()
        binding.bottomNavigation.visibility = if (isLoggedIn) View.VISIBLE else View.GONE
    }



}


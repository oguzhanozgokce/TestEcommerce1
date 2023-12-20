package com.oguzhanozgokce.testecommerce

import  androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
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
        bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.homeFragment -> {
                    navController.navigate(R.id.homeFragment)
                    true
                }

                R.id.favoritesFragment -> {
                    navController.navigate(R.id.favoritesFragment)
                    true
                }

                R.id.cartFragment -> {
                    navController.navigate(R.id.cartFragment)
                    true
                }

                R.id.profileFragment -> {
                    navController.navigate(R.id.profileFragment)
                    true

                }

                else -> false
            }
        }

        checkIfUserLoggedIn()
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun checkIfUserLoggedIn() {
        if (userSessionManager.isUserLoggedIn()) {
            // Kullanıcı giriş yapmış, ana ekrana yönlendir
            navController.navigate(R.id.homeFragment)
        } else {
            // Kullanıcı giriş yapmamış, giriş ekranına yönlendir
            navController.navigate(R.id.loginFragment)
        }
    }
}

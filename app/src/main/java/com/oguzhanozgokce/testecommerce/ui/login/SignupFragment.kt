package com.oguzhanozgokce.testecommerce.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.FragmentSignupBinding
import com.oguzhanozgokce.testecommerce.ui.login.RegistrationStatus.*
import com.oguzhanozgokce.testecommerce.ui.login.util.loginPage
import com.oguzhanozgokce.testecommerce.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {
    private lateinit var binding : FragmentSignupBinding
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentSignupBinding.inflate(inflater,container,false)
        observeViewModel()
        binding.sigUpButtonID.setOnClickListener {
            val username = binding.sigUpUsernameID.text.toString().trim()
            val password = binding.sigUpPasswordID.text.toString().trim()
            val name = binding.sigUpNameID.text.toString().trim()
            val surname = binding.sigUpSurNameID.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && surname.isNotEmpty()) {
                // Call the registerUser method only once here
                authViewModel.registerUser(name, surname, username, password)
                it.isEnabled = false  // Disable the button
            } else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }


        return binding.root
    }
    private fun observeViewModel() {
        authViewModel.registrationStatus.observe(viewLifecycleOwner) { status ->
            when(status) {
                SUCCESS -> {
                    // Hide loading indicator
                    // Continue with navigation
                    val currentDestination = findNavController().currentDestination?.id
                    if (currentDestination == R.id.signupFragment) {
                        findNavController().navigate(R.id.action_signupFragment_to_homeFragment)
                    }
                }
                FAILURE -> {
                    // Hide loading indicator and show a failure message
                    Toast.makeText(requireContext(), "Registration failed", Toast.LENGTH_SHORT).show()
                    // Re-enable the button for another attempt
                    binding.sigUpButtonID.isEnabled = true
                }
                IN_PROGRESS -> {
                    binding.sigUpButtonID.isEnabled = false
                }
            }
        }
    }



}
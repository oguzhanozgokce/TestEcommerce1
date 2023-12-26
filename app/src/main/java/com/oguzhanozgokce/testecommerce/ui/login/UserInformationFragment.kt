package com.oguzhanozgokce.testecommerce.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oguzhanozgokce.testecommerce.MainActivity
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.FragmentUserInformationBinding
import com.oguzhanozgokce.testecommerce.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserInformationFragment : Fragment() {
    private lateinit var binding: FragmentUserInformationBinding
    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUserInformationBinding.inflate(inflater, container, false)

        binding.informationButtonSaveID.setOnClickListener {
            saveUserInfo()
        }
        observeViewModel()

        return binding.root
    }

    private fun saveUserInfo() {
        val email = binding.informationMailID.text.toString().trim()
        val phone = binding.informationPhoneID.text.toString().trim()
        val age = binding.informationAgeID.text.toString().toIntOrNull()
        val address = binding.informationAddressID.text.toString().trim()

        if (email.isNotEmpty() && phone.isNotEmpty() && age != null && address.isNotEmpty()) {
            // Assuming you have a method in your ViewModel to update user info
            Log.e("UserInformation", "Email: $email, Phone: $phone, Age: $age, Address: $address")
            authViewModel.updateUserInfo(email, phone, age, address)
            Toast.makeText(context, "User info updated", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_userInformationFragment_to_homeFragment)
        } else {
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeViewModel() {
        authViewModel.navigateToHome.observe(viewLifecycleOwner) { shouldNavigate ->
            Log.e("UserInformationFragment", "Should navigate: $shouldNavigate")
            if (shouldNavigate == true) {
                Log.e("UserInformationFragment", "Navigating to HomeFragment")
                findNavController().navigate(R.id.action_userInformationFragment_to_homeFragment)
                (activity as? MainActivity)?.updateBottomNavigationVisibility()
                authViewModel.doneNavigating()  // Reset navigation state
            } else if (shouldNavigate == false) {
                Toast.makeText(context, "Failed to update user information.", Toast.LENGTH_SHORT).show()
            }
        }
    }

}

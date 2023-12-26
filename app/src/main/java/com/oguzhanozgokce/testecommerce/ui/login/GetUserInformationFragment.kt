package com.oguzhanozgokce.testecommerce.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.oguzhanozgokce.testecommerce.databinding.FragmentGetUserInformationBinding
import com.oguzhanozgokce.testecommerce.ui.login.util.UserSessionManager
import com.oguzhanozgokce.testecommerce.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetUserInformationFragment : Fragment() {
    private lateinit var binding: FragmentGetUserInformationBinding
    private val authViewModel: AuthViewModel by viewModels()
    private val userSessionManager: UserSessionManager by lazy { UserSessionManager(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGetUserInformationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeUserInfo()
        loadUserInfo()
        binding.getInformationButtonSaveID.setOnClickListener {
            saveUserInfo()
        }
    }

    private fun saveUserInfo() {
        val newName = binding.getInformationNameID.text.toString().trim()
        val newUsername = binding.getInformationUserNameID.text.toString().trim()
        val newPassword = binding.getInformationPasswordID.text.toString().trim()
        val newEmail = binding.getInformationMailID.text.toString().trim()
        val newPhone = binding.getInformationPhoneID.text.toString().trim()
        val newAge = binding.getInformationAgeID.text.toString().toIntOrNull() ?: 0 // Providing a default value if null
        val newAddress = binding.getInformationAddressID.text.toString().trim()

        if (newName.isNotEmpty() && newUsername.isNotEmpty() && newPassword.isNotEmpty() && newEmail.isNotEmpty() && newPhone.isNotEmpty() && newAge != null && newAddress.isNotEmpty()) {
            authViewModel.updateUserDetails(newName, newUsername, newPassword, newEmail, newPhone, newAge, newAddress)
        } else {
            Toast.makeText(context, "All fields are required.", Toast.LENGTH_SHORT).show()
        }
    }


    private fun loadUserInfo() {
        val userId = userSessionManager.getCurrentUserId()
        if (userId != -1) {
            authViewModel.loadUserInfo(userId)
        } else {
            Toast.makeText(context, "User not found.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeUserInfo() {
        authViewModel.user.observe(viewLifecycleOwner) { user ->
            user?.let {
                binding.getInformationNameID.setText(it.name)
                binding.getInformationUserNameID.setText(it.username)
                binding.getInformationPasswordID.setText(it.password)
                binding.getInformationMailID.setText(it.email)
                binding.getInformationPhoneID.setText(it.phoneNumber)
                binding.getInformationAgeID.setText(it.age?.toString())
                binding.getInformationAddressID.setText(it.address)
            }
        }
    }
}

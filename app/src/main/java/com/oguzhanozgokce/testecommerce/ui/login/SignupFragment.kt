package com.oguzhanozgokce.testecommerce.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.FragmentSignupBinding
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
            val username = binding.sigUpUsernameID.text.toString()
            val password = binding.sigUpPasswordID.text.toString()
            authViewModel.registerUser(username, password)
        }
        return binding.root
    }
    private fun observeViewModel() {
        authViewModel.registrationStatus.observe(viewLifecycleOwner) { status ->
            if (status == RegistrationStatus.SUCCESS) {
                findNavController().navigate(R.id.action_signupFragment_to_homeFragment)
            }
            // Hata durumları için ek işlemler...
        }
    }

}
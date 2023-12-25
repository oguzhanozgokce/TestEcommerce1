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
import com.oguzhanozgokce.testecommerce.databinding.FragmentLoginBinding
import com.oguzhanozgokce.testecommerce.ui.login.util.loginPage
import com.oguzhanozgokce.testecommerce.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    private val authViewModel: AuthViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        //When the button is pressed, it switches to the home fragment.
        binding.loginButtonID.setOnClickListener {
            val username = binding.loginUsernameID.text.toString().trim()
            val password = binding.loginPasswordID.text.toString().trim()

            if (username.isNotEmpty() && password.isNotEmpty()) {
                authViewModel.loginUser(username, password)
            } else {
                Toast.makeText(requireContext(), "Please enter username and password", Toast.LENGTH_SHORT).show()
            }
        }
        observeViewModel()
        binding.loginSignupTextID.setOnClickListener {
            Navigation.loginPage(it, R.id.action_loginFragment_to_signupFragment)
        }
        return binding.root
    }

    private fun observeViewModel() {
        authViewModel.loginStatus.observe(viewLifecycleOwner) { isSuccess ->
            if (isSuccess) {
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(requireContext(), "Incorrect login credentials", Toast.LENGTH_SHORT).show()
            }
        }
    }

}
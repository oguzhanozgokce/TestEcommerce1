package com.oguzhanozgokce.testecommerce.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.FragmentLoginBinding
import com.oguzhanozgokce.testecommerce.login.util.loginPage

class LoginFragment : Fragment() {
    private lateinit var binding : FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        //When the button is pressed, it switches to the home fragment.
        binding.loginButtonID.setOnClickListener {
            Navigation.loginPage(it,R.id.action_loginFragment_to_homeFragment)
        }
        binding.loginSignupTextID.setOnClickListener {
            Navigation.loginPage(it, R.id.action_loginFragment_to_signupFragment)
        }
        return binding.root
    }
}
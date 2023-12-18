package com.oguzhanozgokce.testecommerce.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.FragmentSignupBinding
import com.oguzhanozgokce.testecommerce.ui.login.util.loginPage

class SignupFragment : Fragment() {
    private lateinit var binding : FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentSignupBinding.inflate(inflater,container,false)
        binding.sigUpButtonID.setOnClickListener {
            Navigation.loginPage(it,R.id.action_signupFragment_to_homeFragment)
        }
        return binding.root
    }

}
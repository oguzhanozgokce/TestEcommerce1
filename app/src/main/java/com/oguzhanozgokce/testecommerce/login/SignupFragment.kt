package com.oguzhanozgokce.testecommerce.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.FragmentSignupBinding

class SignupFragment : Fragment() {
    private lateinit var binding : FragmentSignupBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentSignupBinding.inflate(inflater,container,false)
        binding.sigUpButtonID.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_signupFragment_to_homeFragment)
        }
        return binding.root
    }

}
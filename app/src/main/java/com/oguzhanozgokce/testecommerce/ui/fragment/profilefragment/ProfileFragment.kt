package com.oguzhanozgokce.testecommerce.ui.fragment.profilefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }


}
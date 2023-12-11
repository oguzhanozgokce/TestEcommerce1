package com.oguzhanozgokce.testecommerce.ui.fragment.favoritesfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.FragmentFavoritesBinding

class FavoritesFragment : Fragment() {


    private lateinit var binding : FragmentFavoritesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding.root
    }


}
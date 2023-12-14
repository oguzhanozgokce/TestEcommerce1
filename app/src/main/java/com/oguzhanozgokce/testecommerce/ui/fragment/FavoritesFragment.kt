package com.oguzhanozgokce.testecommerce.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.oguzhanozgokce.testecommerce.R
import com.oguzhanozgokce.testecommerce.databinding.FragmentFavoritesBinding
import com.oguzhanozgokce.testecommerce.ui.viewmodel.FavoritesViewModel
import com.oguzhanozgokce.testecommerce.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    /**
     * val temp : FavoritesViewModel by this.viewModels()
     *         viewModel = temp
     *         viewModel = FavoritesViewModel()  iki kull
     */


    private lateinit var binding : FragmentFavoritesBinding
    lateinit var viewModel : FavoritesViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding  = FragmentFavoritesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : FavoritesViewModel by this.viewModels()
        viewModel = tempViewModel

    }


}
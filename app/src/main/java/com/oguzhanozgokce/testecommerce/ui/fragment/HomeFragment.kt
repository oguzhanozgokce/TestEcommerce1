package com.oguzhanozgokce.testecommerce.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oguzhanozgokce.testecommerce.databinding.FragmentHomeBinding
import com.oguzhanozgokce.testecommerce.ui.adapter.ProductAdapter
import com.oguzhanozgokce.testecommerce.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        val productAdapter = ProductAdapter(requireContext(), emptyList(), viewModel)
        binding.recyclerViewProductID.adapter = productAdapter
        binding.recyclerViewProductID.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)

        viewModel.productList.observe(viewLifecycleOwner) { products ->
            productAdapter.updateData(products)
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllProducts()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllProducts()
    }

}

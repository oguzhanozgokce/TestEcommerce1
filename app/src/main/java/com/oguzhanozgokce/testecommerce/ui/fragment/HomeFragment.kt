package com.oguzhanozgokce.testecommerce.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oguzhanozgokce.testecommerce.databinding.FragmentHomeBinding
import com.oguzhanozgokce.testecommerce.ui.adapter.ProductAdapter
import com.oguzhanozgokce.testecommerce.ui.viewmodel.HomeViewModel


class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        viewModel.productList.observe(viewLifecycleOwner) {
            val productAdapter = ProductAdapter(requireContext(), it, viewModel)
            binding.recyclerViewProductID.apply {
                layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
                setHasFixedSize(true)
                adapter = productAdapter
            }
        }
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = HomeViewModel()
    }

}
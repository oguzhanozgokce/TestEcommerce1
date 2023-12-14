package com.oguzhanozgokce.testecommerce.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oguzhanozgokce.testecommerce.databinding.FragmentHomeBinding
import com.oguzhanozgokce.testecommerce.ui.adapter.ProductAdapter
import com.oguzhanozgokce.testecommerce.ui.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
        
        //searchView
        binding.editTextText.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.productSearch(query!!)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.productSearch(newText!!)
                return true
            }

        })

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : HomeViewModel by this.viewModels()
        viewModel = tempViewModel
    }

    override fun onResume() {
        super.onResume()
        viewModel.productAdd()
    }

}

package com.oguzhanozgokce.testecommerce.ui.fragment.homefragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.oguzhanozgokce.testecommerce.databinding.FragmentHomeBinding
import com.oguzhanozgokce.testecommerce.domain.Produckt
import com.oguzhanozgokce.testecommerce.ui.adapter.ProductAdapter



class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.recyclerViewProductID.layoutManager =
            StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.HORIZONTAL)
        val productList = ArrayList<Produckt>()
        val product1 = Produckt(
            1,
            "Macbook",
            "pic1",
            100.0,
            9.8,
            18,
            false,
            20,
            "Apple",
            "Laptop",
        )
        val product2 = Produckt(
            2,
            "Iphone",
            "login_image",
            100.0,
            9.8,
            18,
            false,
            20,
            "Iphone",
            "Laptop",
        )
        productList.add(product1)
        productList.add(product2)


        val productAdapter = ProductAdapter(requireContext(), productList)
        binding.recyclerViewProductID.adapter = productAdapter
        return binding.root
    }

}
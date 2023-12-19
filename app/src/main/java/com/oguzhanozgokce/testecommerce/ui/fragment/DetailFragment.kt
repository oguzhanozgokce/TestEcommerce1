package com.oguzhanozgokce.testecommerce.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.oguzhanozgokce.testecommerce.databinding.FragmentDetailBinding
import com.oguzhanozgokce.testecommerce.ui.viewmodel.CartViewModel
import com.oguzhanozgokce.testecommerce.ui.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)

        val productId = DetailFragmentArgs.fromBundle(requireArguments()).productId
        viewModel.fetchProductDetails(productId)

        viewModel.productDetails.observe(viewLifecycleOwner, Observer { product ->
            Glide.with(requireContext())
                .load(product.image)
                .into(binding.detailImageID)
            binding.detailDescriptionID.text = product.description
            binding.detailPriceID.text = product.price.toString()
            binding.detailTitleID.text = product.title
            binding.detailRatingID.text = product.rating.toString()
            binding.detailSalesPersonID.text = product.salesPerson
        })

        binding.imageViewBack.setOnClickListener {
            requireActivity().onBackPressed()
        }



        return binding.root
    }
}
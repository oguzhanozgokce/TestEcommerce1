package com.oguzhanozgokce.testecommerce.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanozgokce.testecommerce.databinding.FragmentCartBinding
import com.oguzhanozgokce.testecommerce.ui.adapter.CartAdapter
import com.oguzhanozgokce.testecommerce.ui.viewmodel.CartViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val cartViewModel: CartViewModel by viewModels()

    /**
     * BAKKK BURAYA
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeCartItems()
    }


    private fun setupRecyclerView() {
        binding.cartRecyclerViewID.layoutManager = LinearLayoutManager(context)
        binding.cartRecyclerViewID.adapter = CartAdapter(emptyList()) { cartItem ->
           cartViewModel.removeCartItem(cartItem)
        }
    }

    private fun observeCartItems() {
        // Sepet öğelerini ViewModel'den alın ve RecyclerView'da gösterin
        cartViewModel.cartItems.observe(viewLifecycleOwner, Observer { cartItems ->
            (binding.cartRecyclerViewID.adapter as? CartAdapter)?.updateData(cartItems)
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
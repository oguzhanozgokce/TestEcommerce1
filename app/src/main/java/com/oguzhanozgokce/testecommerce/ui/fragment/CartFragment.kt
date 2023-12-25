package com.oguzhanozgokce.testecommerce.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.oguzhanozgokce.testecommerce.databinding.FragmentCartBinding
import com.oguzhanozgokce.testecommerce.ui.adapter.CartAdapter
import com.oguzhanozgokce.testecommerce.ui.login.util.UserSessionManager
import com.oguzhanozgokce.testecommerce.ui.viewmodel.CartViewModel
import com.oguzhanozgokce.testecommerce.ui.viewmodel.OrderViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment() {
    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val cartViewModel: CartViewModel by viewModels()
    private val viewModel: OrderViewModel by viewModels()
    private val userSessionManager: UserSessionManager by lazy { UserSessionManager(requireContext()) }


    /**
     * BAKKK BURAYA
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCartBinding.inflate(inflater, container, false)

        binding.cartButtonPlaceOrderID.setOnClickListener {
            val userId = userSessionManager.getCurrentUserId()
            val productId = userSessionManager.getSelectedProductId()
            Log.e("CartFragment", "UserID: $userId, ProductID: $productId")

            if (userId > 0 && productId > 0) {
                viewModel.placeOrder(userId, productId)
            } else {
                Toast.makeText(context, "Error: Unable to place order. Invalid user or product.", Toast.LENGTH_SHORT).show()
                Log.e("CartFragment", "Invalid userId or productId: userId = $userId, productId = $productId")
            }
        }

        viewModel.orderStatus.observe(viewLifecycleOwner) { isSuccessful ->
            if (isSuccessful) {
                Toast.makeText(context, "Order placed successfully!", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to place order. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
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
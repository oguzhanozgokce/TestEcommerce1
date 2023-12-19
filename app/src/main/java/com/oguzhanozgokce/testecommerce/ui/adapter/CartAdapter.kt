package com.oguzhanozgokce.testecommerce.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.oguzhanozgokce.testecommerce.databinding.OrderCartDesingBinding
import com.oguzhanozgokce.testecommerce.entitiy.CartItem

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------	 |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
class CartAdapter(
    private var cartItems: List<CartItem>,
    private val onDeleteClick: (CartItem) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    class CartViewHolder(var binding: OrderCartDesingBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = OrderCartDesingBinding.inflate(layoutInflater, parent, false)
        return CartViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartItems[position]
        holder.binding.apply {
            Glide.with(orderCartImageID.context).load(cartItem.image).into(orderCartImageID)
            orderCartProductPriceID.text = cartItem.price.toString()
            orderCartProductTitleID.text = cartItem.title

            orderCartDeleteButtonID.setOnClickListener {
                onDeleteClick(cartItem)
            }
        }
    }
    override fun getItemCount(): Int {
        return cartItems.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newCartItems: List<CartItem>) {
        cartItems = newCartItems
        notifyDataSetChanged()
    }
}

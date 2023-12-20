package com.oguzhanozgokce.testecommerce.data.repo

import androidx.lifecycle.LiveData
import com.oguzhanozgokce.testecommerce.data.datasource.CartDataSource
import com.oguzhanozgokce.testecommerce.entitiy.CartItem

class CartRepository (val cartDataSource: CartDataSource) {
    fun getAllCartItems(): LiveData<List<CartItem>> = cartDataSource.getAllCartItems()

    suspend fun insertCartItem(cartItem: CartItem) {
        cartDataSource.insertCartItem(cartItem)
    }
    suspend fun removeCartItem(cartItem: CartItem) {
        cartDataSource.removeCartItem(cartItem)
    }
}
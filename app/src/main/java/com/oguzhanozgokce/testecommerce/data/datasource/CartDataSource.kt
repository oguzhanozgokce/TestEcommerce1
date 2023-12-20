package com.oguzhanozgokce.testecommerce.data.datasource

import androidx.lifecycle.LiveData
import com.oguzhanozgokce.testecommerce.data.room.CartDao
import com.oguzhanozgokce.testecommerce.entitiy.CartItem

class CartDataSource(val cartDao: CartDao) {
    fun getAllCartItems(): LiveData<List<CartItem>> = cartDao.getAllCartItems()
    suspend fun insertCartItem(cartItem: CartItem) {
        cartDao.insertCartItem(cartItem)
    }
    suspend fun removeCartItem(cartItem: CartItem) {
        cartDao.deleteCartItem(cartItem)
    }
}
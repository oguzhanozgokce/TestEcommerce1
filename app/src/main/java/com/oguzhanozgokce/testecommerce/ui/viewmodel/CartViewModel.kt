package com.oguzhanozgokce.testecommerce.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.testecommerce.data.repo.CartRepository
import com.oguzhanozgokce.testecommerce.entitiy.CartItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CartViewModel @Inject constructor(private val cartRepository: CartRepository) : ViewModel() {

    val cartItems: LiveData<List<CartItem>> = cartRepository.getAllCartItems()

    private val _addItemStatus = MutableLiveData<Boolean>()
    val addItemStatus: LiveData<Boolean> = _addItemStatus


    fun addToCart(productId: Int, title: String, price: Int, image: String) {
        viewModelScope.launch {
            val cartItem = CartItem(productId, title, price, image)
            cartRepository.insertCartItem(cartItem)
            _addItemStatus.value = true
        }
    }
    fun removeCartItem(cartItem: CartItem) {
        viewModelScope.launch {
            cartRepository.removeCartItem(cartItem)
            // Sepet listesini güncelleme (gerekiyorsa)
        }
    }
    fun resetAddItemStatus() {
        _addItemStatus.value = false
    }
}

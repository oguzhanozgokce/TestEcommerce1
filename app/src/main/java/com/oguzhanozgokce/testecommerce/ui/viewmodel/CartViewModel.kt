package com.oguzhanozgokce.testecommerce.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.testecommerce.data.repo.ProductRepository
import com.oguzhanozgokce.testecommerce.entitiy.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
@HiltViewModel
class CartViewModel @Inject constructor (
    private val productRepository: ProductRepository
): ViewModel() {
    private val _cartItems = MutableLiveData<List<Product>>(emptyList())
    val cartItems: LiveData<List<Product>> = _cartItems

    fun addToCart(productId: Int) {
        viewModelScope.launch {
            val product = productRepository.getProductById(productId)
            val currentCartItems = _cartItems.value.orEmpty().toMutableList()
            currentCartItems.add(product)
            _cartItems.value = currentCartItems
        }
    }
}

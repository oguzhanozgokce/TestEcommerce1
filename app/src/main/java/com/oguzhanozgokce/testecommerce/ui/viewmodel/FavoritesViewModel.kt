package com.oguzhanozgokce.testecommerce.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.oguzhanozgokce.testecommerce.data.repo.ProductRepository
import com.oguzhanozgokce.testecommerce.entitiy.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(var productRepository: ProductRepository) : ViewModel(){

    val favoriteProducts = MutableLiveData<List<Product>>()

    fun fetchFavoriteProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            val products = productRepository.getFavoriteProducts()
            favoriteProducts.value = products
        }
    }
    fun updateFavoriteStatus(productId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            productRepository.updateFavoriteStatus(productId, isFavorite)
        }
    }
}

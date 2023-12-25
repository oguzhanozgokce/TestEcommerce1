package com.oguzhanozgokce.testecommerce.ui.viewmodel

import androidx.lifecycle.LiveData
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


    private val _favoriteProducts = MutableLiveData<List<Product>>()
    val favoriteProducts: LiveData<List<Product>> = _favoriteProducts

    init {
        fetchFavoriteProducts()
    }

    fun fetchFavoriteProducts() {
        viewModelScope.launch {
            _favoriteProducts.value = productRepository.getFavoriteProducts()
        }
    }

    fun updateFavoriteStatus(productId: Int, isFavorite: Boolean) {
        viewModelScope.launch {
            productRepository.updateFavoriteStatus(productId, isFavorite)
        }
    }

    fun searchProductsByName(query: String) {
        viewModelScope.launch {
            if (query.isEmpty()) {
                fetchFavoriteProducts() // If query is empty, show all favorites again
            } else {
                val searchResult = productRepository.searchProductsByName("%$query%")
                _favoriteProducts.postValue(searchResult.filter { it.isFavorite }) // Filter by favorites
            }
        }
    }
}

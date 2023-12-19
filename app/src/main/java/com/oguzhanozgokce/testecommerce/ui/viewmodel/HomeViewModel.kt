package com.oguzhanozgokce.testecommerce.ui.viewmodel
// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------	 |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oguzhanozgokce.testecommerce.data.repo.ProductRepository
import com.oguzhanozgokce.testecommerce.entitiy.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(var productRepository: ProductRepository) : ViewModel() {

    private val _productList = MutableLiveData<List<Product>>()
    val productList: LiveData<List<Product>> = _productList

    private val _highRatedProducts = MutableLiveData<List<Product>>()
    val highRatedProducts: LiveData<List<Product>> = _highRatedProducts

    init {
        getAllProducts()
    }

    fun getAllProducts() {
        CoroutineScope(Dispatchers.Main).launch {
            val products = productRepository.getAllProducts()
            Log.e("HomeViewModel", "Fetched products size: ${products.size}") // Log ekle
            _productList.value = products

            _highRatedProducts.value = products.filter { it.rating in 4..5 }
        }
    }

    fun updateFavoriteStatus(productId: Int, isFavorite: Boolean) {
        CoroutineScope(Dispatchers.Main).launch {
            productRepository.updateFavoriteStatus(productId, isFavorite)
        }
    }

}
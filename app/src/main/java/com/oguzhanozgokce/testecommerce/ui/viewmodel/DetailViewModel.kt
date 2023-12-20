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

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _productDetails = MutableLiveData<Product>()
    val productDetails: LiveData<Product> = _productDetails

    fun fetchProductDetails(productId: Int) {
        viewModelScope.launch {
            val product = productRepository.getProductById(productId)
            _productDetails.value = product
        }
    }
}
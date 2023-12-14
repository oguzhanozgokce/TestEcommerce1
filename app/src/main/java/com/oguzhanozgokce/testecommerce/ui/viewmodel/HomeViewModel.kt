package com.oguzhanozgokce.testecommerce.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.oguzhanozgokce.testecommerce.data.repo.ProductRepository
import com.oguzhanozgokce.testecommerce.domain.Product
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
/**
 * val productList = MutableLiveData<List<Product>>() // veri tabanından gelen ürünlerin tutulduğu liste
 * val productRepository = ProductRepository() // veri tabanı işlemlerinin yapıldığı class
 * fun getAllProducts() // veri tabanından tüm ürünleri çekmek için kullanılır.
 */
@HiltViewModel
class HomeViewModel  @Inject constructor(var productRepository: ProductRepository) : ViewModel() {
   
    var productList = MutableLiveData<List<Product>>()

    init {
        productAdd()
    }
     fun getAllProducts() {
         CoroutineScope(Dispatchers.Main).launch {
             productRepository.getAllProducts()
         }
     }

    fun productAdd() {
        CoroutineScope(Dispatchers.Main).launch {
            productList.value = productRepository.productAdd()
        }
    }

    fun productSearch(productSearch: String) {
        CoroutineScope(Dispatchers.Main).launch {
            productList.value = productRepository.productSearch(productSearch)
        }
    }

}
package com.oguzhanozgokce.testecommerce.data.repo

import com.oguzhanozgokce.testecommerce.data.datasource.ProductDataSource
import com.oguzhanozgokce.testecommerce.domain.Product

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
/**
 * Repository : Veri tabanı işlemleri için gerekli olan fonksiyonlar burada tanımlanır.
 * buradaki fonksiyonlar ProductDataSource classından gelir.
 * asıl amacımız veri tabanı işlemlerini viewmodelden ayırmak.
 * viewmodelde sadece veri tabanı işlemlerini çağırmak yeterli olacak.
 * bu sayede viewmodel daha az kod içerecek ve daha anlaşılır olacak.
 * bu sayede veri tabanı işlemlerini daha kolay yapabileceğiz.
 */
class ProductRepository (var productDataSource : ProductDataSource){

    suspend fun getAllProducts() = productDataSource.getAllProducts()
    suspend fun productRemove() = productDataSource.productRemove()

    suspend fun productAdd() : MutableList<Product> = productDataSource.productAdd()

    suspend fun productSearch(productSearch: String) : MutableList<Product> =
        productDataSource.productSearch(productSearch)
}
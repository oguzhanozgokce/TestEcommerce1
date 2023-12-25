package com.oguzhanozgokce.testecommerce.data.repo

import com.oguzhanozgokce.testecommerce.data.datasource.ProductDataSource
import com.oguzhanozgokce.testecommerce.entitiy.Product


/**
 * Repository : Veri tabanı işlemleri için gerekli olan fonksiyonlar burada tanımlanır.
 * buradaki fonksiyonlar ProductDataSource classından gelir.
 * viewmodelde sadece veri tabanı işlemlerini çağırmak yeterli olacak.
 */
class ProductRepository (var productDataSource : ProductDataSource){
    suspend fun getAllProducts() : List<Product> = productDataSource.getAllProducts()
    suspend fun getProductsByCategory(categoryId: Int): List<Product> =
        productDataSource.getProductsByCategory(categoryId)

    suspend fun updateFavoriteStatus(productId: Int, isFavorite: Boolean) {
        productDataSource.updateFavoriteStatus(productId, isFavorite)
    }
    suspend fun getFavoriteProducts() : List<Product> = productDataSource.getFavoriteProducts()

    suspend fun getProductById(productId: Int) : Product = productDataSource.getProductById(productId)

    suspend fun searchProductsByName(query: String): List<Product> {
        return productDataSource.searchProductsByName(query)
    }

}
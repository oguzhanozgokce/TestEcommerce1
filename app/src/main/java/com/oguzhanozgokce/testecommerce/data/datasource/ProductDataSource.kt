package com.oguzhanozgokce.testecommerce.data.datasource

import com.oguzhanozgokce.testecommerce.data.room.ProductDao
import com.oguzhanozgokce.testecommerce.entitiy.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
/**
 * Veri tabanı işlemleri için gerekli olan fonksiyonlar burada tanımlanır.
 * buradaki fonksiyonlar ProductDao interfaceinden gelir.
 */
class ProductDataSource (var productDao: ProductDao){

    suspend fun getAllProducts() : List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext productDao.getAllProducts()
        }

    suspend fun updateFavoriteStatus(productId: Int, isFavorite: Boolean) {
        productDao.updateFavoriteStatus(productId, isFavorite)
    }

    suspend fun getFavoriteProducts() : List<Product> =
        withContext(Dispatchers.IO) {
            return@withContext productDao.getFavoriteProducts()
        }

    suspend fun getProductById(productId: Int) : Product =
        withContext(Dispatchers.IO) {
            return@withContext productDao.getProductById(productId)
        }
}
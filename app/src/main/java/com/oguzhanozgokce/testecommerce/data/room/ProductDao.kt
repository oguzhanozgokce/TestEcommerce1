package com.oguzhanozgokce.testecommerce.data.room

import androidx.room.Dao
import androidx.room.Query
import com.oguzhanozgokce.testecommerce.entitiy.Product

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|

/**
 *  dao : data access object (veri erişim nesnesi) veri tabanı işlemlerini yapmak için kullanılır.
 *  Veri tabanı işlemleri için gerekli olan fonksiyonlar burada tanımlanır.
 *
 */
@Dao
interface ProductDao {
    @Query ("SELECT * FROM product")
    suspend fun getAllProducts(): List<Product>

    @Query("UPDATE product SET isFavorite = :isFavorite WHERE ProductID = :productId")
    suspend fun updateFavoriteStatus(productId: Int, isFavorite: Boolean)

    @Query("SELECT * FROM product WHERE isFavorite = 1")
    suspend fun getFavoriteProducts(): List<Product>
}
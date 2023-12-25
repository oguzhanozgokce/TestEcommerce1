package com.oguzhanozgokce.testecommerce.data.room

import androidx.room.Dao
import androidx.room.Query
import com.oguzhanozgokce.testecommerce.entitiy.Product
/**
 *  dao : data access object (veri erişim nesnesi) veri tabanı işlemlerini yapmak için kullanılır.
 *  Veri tabanı işlemleri için gerekli olan fonksiyonlar burada tanımlanır.
 */

@Dao
interface ProductDao {
    @Query ("SELECT * FROM product")
    suspend fun getAllProducts(): List<Product>

    @Query("SELECT * FROM product WHERE categoryID = :categoryId")
    fun getProductsByCategory(categoryId: Int): List<Product>

    @Query("UPDATE product SET isFavorite = :isFavorite WHERE ProductID = :productId")
    suspend fun updateFavoriteStatus(productId: Int, isFavorite: Boolean)

    @Query("SELECT * FROM product WHERE isFavorite = 1")
    suspend fun getFavoriteProducts(): List<Product>

    @Query("SELECT * FROM product WHERE ProductID = :productId")
    suspend fun getProductById(productId: Int): Product

    //search
    @Query("SELECT * FROM product WHERE title LIKE :query")
    suspend fun searchProductsByName(query: String): List<Product>
}
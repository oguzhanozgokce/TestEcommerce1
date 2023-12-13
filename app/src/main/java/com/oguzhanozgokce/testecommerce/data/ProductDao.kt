package com.oguzhanozgokce.testecommerce.data

import androidx.room.Dao
import androidx.room.Query
import com.oguzhanozgokce.testecommerce.domain.Product

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
    @Query ("SELECT * FROM Product")
    suspend fun getAllProducts(): MutableList<Product>
}
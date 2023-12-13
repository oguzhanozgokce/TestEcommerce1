package com.oguzhanozgokce.testecommerce.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oguzhanozgokce.testecommerce.domain.Product

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------	 |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|

/**
 * abstract classı RoomDatabase den türetiyoruz.
 * entities : veri tabanında oluşturulacak tabloları belirtir.
 * neden abstract class kullandık? : çünkü RoomDatabase abstract classıdır.
 * neden abstract fun productDao(): ProductDao kullandık? : çünkü veri tabanı işlemleri için gerekli olan fonksiyonlar burada tanımlanır.
 */
@Database (entities = [Product::class],version = 1)
abstract class DataBase : RoomDatabase(){
    abstract fun productDao(): ProductDao
}
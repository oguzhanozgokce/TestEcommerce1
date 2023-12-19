package com.oguzhanozgokce.testecommerce.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oguzhanozgokce.testecommerce.entitiy.Product
import com.oguzhanozgokce.testecommerce.entitiy.User

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

@Database (entities = [Product::class, User::class],version = 1, exportSchema=false)
abstract class DataBase : RoomDatabase(){
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
}
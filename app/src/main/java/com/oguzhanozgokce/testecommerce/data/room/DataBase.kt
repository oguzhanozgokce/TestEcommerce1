package com.oguzhanozgokce.testecommerce.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.oguzhanozgokce.testecommerce.entitiy.CartItem
import com.oguzhanozgokce.testecommerce.entitiy.Category
import com.oguzhanozgokce.testecommerce.entitiy.Order
import com.oguzhanozgokce.testecommerce.entitiy.Product
import com.oguzhanozgokce.testecommerce.entitiy.User

@Database (entities = [Product::class, User::class, CartItem::class, Category::class, Order::class],version =3, exportSchema=false)
abstract class DataBase : RoomDatabase(){
    abstract fun productDao(): ProductDao
    abstract fun userDao(): UserDao
    abstract fun cartDao(): CartDao
    abstract fun categoryDao(): CategoryDao
    abstract fun orderDao(): OrderDao

}
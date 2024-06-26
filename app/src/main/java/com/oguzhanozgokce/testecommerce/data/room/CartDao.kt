package com.oguzhanozgokce.testecommerce.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oguzhanozgokce.testecommerce.entitiy.CartItem

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItem)

    @Query("SELECT * FROM Cart")
    fun getAllCartItems(): LiveData<List<CartItem>>

    @Delete
    suspend fun deleteCartItem(cartItem: CartItem)
}
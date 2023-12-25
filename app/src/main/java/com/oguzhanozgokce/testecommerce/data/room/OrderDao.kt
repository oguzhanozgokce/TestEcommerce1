package com.oguzhanozgokce.testecommerce.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.oguzhanozgokce.testecommerce.entitiy.Order

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
@Dao
interface OrderDao {
    @Insert
    suspend fun insertOrder(order: Order)

    @Query("SELECT * FROM orders WHERE userId = :userId")
    suspend fun getOrdersByUserId(userId: Int): List<Order>

    // Add more queries as needed, for example, to retrieve all orders, delete an order, etc.
}
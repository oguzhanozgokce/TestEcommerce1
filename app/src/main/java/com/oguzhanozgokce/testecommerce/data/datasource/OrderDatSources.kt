package com.oguzhanozgokce.testecommerce.data.datasource

import android.util.Log
import com.oguzhanozgokce.testecommerce.data.room.OrderDao
import com.oguzhanozgokce.testecommerce.entitiy.Order
import javax.inject.Inject

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
class OrderDatSources(private val orderDao: OrderDao) {
    suspend fun getUserOrders(userId: Int): List<Order> {
        return orderDao.getOrdersByUserId(userId)

    }
    suspend fun placeOrder(userId: Int, ProductID: Int) {
        if(userId > 0 && ProductID > 0) {  // Check for valid IDs
            val order = Order(userId = userId, productId = ProductID)
            orderDao.insertOrder(order)
        } else {
            Log.e("OrderDatSources", "Invalid userId or ProductID: userId = $userId, ProductID = $ProductID")
        }
    }

}
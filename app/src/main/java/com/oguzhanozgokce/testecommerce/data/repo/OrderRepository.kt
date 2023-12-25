package com.oguzhanozgokce.testecommerce.data.repo

import com.oguzhanozgokce.testecommerce.data.datasource.OrderDatSources
import com.oguzhanozgokce.testecommerce.entitiy.Order
import javax.inject.Inject
import javax.inject.Singleton

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|


class OrderRepository (private val orderDataSource: OrderDatSources) {
    suspend fun placeOrder(userId: Int, productId: Int) {
        orderDataSource.placeOrder(userId, productId)
    }
    suspend fun getUserOrders(userId: Int): List<Order> {
        return orderDataSource.getUserOrders(userId)
    }
}
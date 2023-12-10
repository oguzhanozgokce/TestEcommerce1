package com.oguzhanozgokce.testecommerce.domain

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|

data class Produckt(
    val productId: Int,
    val name: String,
    val image: String,
    val price: Double,
    val rating: Double,
    val salePrice: Int,
    val isFavorite: Boolean,
    val count: Int,
    val description: String,
    val category: String
){
    fun getRatingString(): String {
        return rating.toString()
    }
    fun getPriceString(): String {
        return price.toString()
    }
    fun getSalePriceString(): String {
        return salePrice.toString()
    }
    fun getCountString(): String {
        return count.toString()
    }
}

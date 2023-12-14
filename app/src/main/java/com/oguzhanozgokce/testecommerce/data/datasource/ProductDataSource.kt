package com.oguzhanozgokce.testecommerce.data.datasource

import com.oguzhanozgokce.testecommerce.domain.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
/**
 * Veri tabanı işlemleri için gerekli olan fonksiyonlar burada tanımlanır.
 * buradaki fonksiyonlar ProductDao interfaceinden gelir.
 */
class ProductDataSource {
    suspend fun getAllProducts() {

    }

    suspend fun productRemove() {
        //
    }
    suspend fun productAdd(): MutableList<Product> =
        withContext(Dispatchers.IO) {
            val productList = ArrayList<Product>()
            val product1 = Product(
                1,
                "Macbook",
                "pic1",
                100.0,
                9,
                18.0,
                false,
                20,
                "Apple",
                "Laptop",
            )
            val product2 = Product(
                2,
                "Iphone",
                "login_image",
                100.0,
                9,
                18.0,
                false,
                20,
                "Iphone",
                "Laptop",
            )
            productList.add(product1)
            productList.add(product2)
            return@withContext productList
        }

    suspend fun productSearch(productSearch: String) : MutableList<Product> =
        withContext(Dispatchers.IO) {
            val productList = ArrayList<Product>()
            val product1 = Product(
                1,
                "Macbook",
                "pic1",
                100.0,
                9,
                18.0,
                false,
                20,
                "Apple",
                "Laptop",
            )
            val product2 = Product(
                2,
                "Iphone",
                "login_image",
                100.0,
                9,
                18.0,
                false,
                20,
                "Iphone",
                "Laptop",
            )
            productList.add(product1)
            productList.add(product2)
            return@withContext productList
        }


}
package com.oguzhanozgokce.testecommerce.data.datasource

import com.oguzhanozgokce.testecommerce.data.room.CategoryDao

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
class CategoryDataSource (val categoryDao: CategoryDao) {
    fun getAllCategories() = categoryDao.getAllCategories()
}
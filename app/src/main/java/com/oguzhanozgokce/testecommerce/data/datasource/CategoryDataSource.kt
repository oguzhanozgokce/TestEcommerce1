package com.oguzhanozgokce.testecommerce.data.datasource

import com.oguzhanozgokce.testecommerce.data.room.CategoryDao

class CategoryDataSource (val categoryDao: CategoryDao) {
    fun getAllCategories() = categoryDao.getAllCategories()
}
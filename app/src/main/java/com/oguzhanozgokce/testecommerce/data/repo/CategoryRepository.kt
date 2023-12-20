package com.oguzhanozgokce.testecommerce.data.repo

import com.oguzhanozgokce.testecommerce.data.datasource.CategoryDataSource


class CategoryRepository (val categoryDataSource: CategoryDataSource) {
     fun getAllCategories() = categoryDataSource.getAllCategories()
}
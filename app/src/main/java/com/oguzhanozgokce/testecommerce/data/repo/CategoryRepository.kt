package com.oguzhanozgokce.testecommerce.data.repo

import com.oguzhanozgokce.testecommerce.data.datasource.CategoryDataSource

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------	 |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
class CategoryRepository (val categoryDataSource: CategoryDataSource) {
     fun getAllCategories() = categoryDataSource.getAllCategories()
}
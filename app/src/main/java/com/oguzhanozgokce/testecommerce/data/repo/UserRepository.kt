package com.oguzhanozgokce.testecommerce.data.repo

import com.oguzhanozgokce.testecommerce.data.datasource.ProductDataSource
import com.oguzhanozgokce.testecommerce.data.datasource.UserDataSource

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
class UserRepository(var userDataSource: UserDataSource) {

        suspend fun registerUser(username: String, password: String) {
            userDataSource.registerUser(username, password)
        }

        suspend fun loginUser(username: String, password: String): Boolean {
            return userDataSource.loginUser(username, password)
        }
}
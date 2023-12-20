package com.oguzhanozgokce.testecommerce.data.repo

import com.oguzhanozgokce.testecommerce.data.datasource.UserDataSource
class UserRepository(var userDataSource: UserDataSource) {

        suspend fun registerUser(username: String, password: String) {
            userDataSource.registerUser(username, password)
        }

        suspend fun loginUser(username: String, password: String): Boolean {
            return userDataSource.loginUser(username, password)
        }
}
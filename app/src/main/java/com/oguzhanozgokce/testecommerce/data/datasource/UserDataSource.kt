package com.oguzhanozgokce.testecommerce.data.datasource

import com.oguzhanozgokce.testecommerce.data.room.UserDao
import com.oguzhanozgokce.testecommerce.entitiy.User

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
class UserDataSource (private val userDao: UserDao) {
    suspend fun registerUser(username: String, password: String) {
        val user = User(username, password)
        userDao.insertUser(user)
    }

    suspend fun loginUser(username: String, password: String): Boolean {
        val user = userDao.getUserByUsername(username)
        return user != null && user.password == password
    }
}
package com.oguzhanozgokce.testecommerce.data.datasource

import com.oguzhanozgokce.testecommerce.data.room.UserDao
import com.oguzhanozgokce.testecommerce.entitiy.User

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
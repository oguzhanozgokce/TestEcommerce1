package com.oguzhanozgokce.testecommerce.data.repo

import android.util.Log
import com.oguzhanozgokce.testecommerce.data.datasource.UserDataSource
import com.oguzhanozgokce.testecommerce.entitiy.User

class UserRepository(var userDataSource: UserDataSource) {
    suspend fun registerUser(name: String, surname: String, username: String, password: String): Long {
        return try {
            userDataSource.registerUser(name, surname, username, password)
        } catch (e: Exception) {
            Log.e("UserRepository", "Error registering user: ${e.localizedMessage}")
            -1 // Indicate failure with a special value
        }
    }
    suspend fun getUserByUsername(username: String): User? {
            return userDataSource.getUserByUsername(username)
    }

    suspend fun loginUser(username: String, password: String): User? {
        return userDataSource.loginUser(username, password)
    }
    suspend fun getUserById(userId: Long): User? {
        return userDataSource.getUserById(userId)  // Implement getUserById in UserDataSource
    }


}
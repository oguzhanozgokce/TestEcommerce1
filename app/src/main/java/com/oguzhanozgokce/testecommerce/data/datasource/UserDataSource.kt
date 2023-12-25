package com.oguzhanozgokce.testecommerce.data.datasource

import android.util.Log
import com.oguzhanozgokce.testecommerce.data.room.UserDao
import com.oguzhanozgokce.testecommerce.entitiy.User

class UserDataSource (private val userDao: UserDao) {
    suspend fun registerUser(name: String, surname: String, username: String, password: String): Long {
        return try {
            if (isUserExists(username)) {
                // Username already exists
                -1L // Return -1 to indicate the username is taken
            } else {
                // Create a new user object
                val newUser = User(username = username, password = password, name = name, surname = surname)
                // Insert the user into the database and return the userId
                insertNewUser(newUser)
            }
        } catch (e: Exception) {
            Log.e("UserDataSource", "Error registering user: ${e.localizedMessage}")
            -1L // Return -1 to indicate a failure
        }
    }
    suspend fun isUserExists(username: String): Boolean {
        return userDao.countUserByUsername(username) > 0
    }
    suspend fun insertNewUser(user: User): Long {
        return userDao.insertUser(user)
    }

    suspend fun getUserByUsername(username: String): User? {
        return userDao.getUserByUsername(username)
    }

    suspend fun loginUser(username: String, password: String): User? {
        val user = userDao.getUserByUsername(username)
        if (user != null && user.password == password) {
            return user
        }
        return null
    }

    suspend fun getUserById(userId: Long): User? {
        return userDao.getUserById(userId)  // Ensure this method is implemented in UserDao
    }
    suspend fun updateUserInfo(userId: Long, email: String, phone: String, age: Int, address: String): Boolean {
        return try {
            val user = userDao.getUserById(userId)
            user?.let {
                val updatedUser = it.copy(email = email, phoneNumber = phone, age = age, address = address)
                userDao.updateUser(updatedUser)
                true
            } ?: false
        } catch (e: Exception) {
            Log.e("UserRepository", "Error updating user info: ${e.localizedMessage}")
            false
        }
    }
    suspend fun updateUser(user: User): Boolean {
        return try {
            userDao.updateUser(user)
            true // return true on success
        } catch (e: Exception) {
            Log.e("UserRepository", "Error updating user: ${e.localizedMessage}")
            false // return false on failure
        }
    }

}
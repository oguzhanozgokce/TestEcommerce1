package com.oguzhanozgokce.testecommerce.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.oguzhanozgokce.testecommerce.entitiy.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User): Long

    @Query("SELECT * FROM User WHERE username = :username")
    suspend fun getUserByUsername(username: String): User?

    @Query("SELECT COUNT(username) FROM User WHERE username = :username")
    suspend fun countUserByUsername(username: String): Int

    @Query("SELECT * FROM User WHERE userId = :userId")
    suspend fun getUserById(userId: Long): User?

    @Update
    suspend fun updateUser(user: User)



}
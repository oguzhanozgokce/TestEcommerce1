package com.oguzhanozgokce.testecommerce.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "User")
data class User (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userId") @NotNull
    val userId: Int = 0,

    @ColumnInfo(name = "username") @NotNull
    val username: String,

    @ColumnInfo(name = "password") @NotNull
    val password: String,

    // Add new fields
    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name = "surname")
    val surname: String?,

    @ColumnInfo(name = "email")
    val email: String? = null,

    @ColumnInfo(name = "phoneNumber")
    val phoneNumber: String? = null,

    @ColumnInfo(name = "age")
    val age: Int? = null,

    @ColumnInfo(name = "address")
    val address: String? = null
)
package com.oguzhanozgokce.testecommerce.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "User")
data class User (
    @PrimaryKey
    @ColumnInfo(name = "username") @NotNull
    val username: String,

    @ColumnInfo(name = "password") @NotNull
    val password: String
)
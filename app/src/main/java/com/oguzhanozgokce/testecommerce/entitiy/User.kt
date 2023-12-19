package com.oguzhanozgokce.testecommerce.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------	 |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
@Entity(tableName = "User")
data class User (
    @PrimaryKey
    @ColumnInfo(name = "username") @NotNull
    val username: String,

    @ColumnInfo(name = "password") @NotNull
    val password: String
)
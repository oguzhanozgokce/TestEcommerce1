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
@Entity(tableName = "categories")
data class Category (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "CategoryID") @NotNull
    val categoryID: Int = 0,

    @ColumnInfo(name = "Name") @NotNull
    val name: String,
)
package com.oguzhanozgokce.testecommerce.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "Cart")
data class CartItem(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ProductID") @NotNull
    val productID: Int = 0,

    @ColumnInfo(name = "Title") @NotNull
    val title: String,

    @ColumnInfo(name = "Price") @NotNull
    val price: Int,

    @ColumnInfo(name = "Image") @NotNull
    val image: String,
)
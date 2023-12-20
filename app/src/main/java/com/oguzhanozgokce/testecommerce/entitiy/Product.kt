package com.oguzhanozgokce.testecommerce.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable


@Entity(tableName = "product",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["CategoryID"],
            childColumns = ["CategoryID"],
            onDelete = ForeignKey.NO_ACTION
        )
    ])
data class Product(
    @PrimaryKey
    @ColumnInfo(name = "ProductID") @NotNull
    val productID: Int = 0,

    @ColumnInfo(name = "Title") @NotNull
    val title: String,

    @ColumnInfo(name = "Price") @NotNull
    val price: Int,

    @ColumnInfo(name = "Image") @NotNull
    val image: String,

    @ColumnInfo(name = "isFavorite") @NotNull
    var isFavorite: Boolean = false,

    @ColumnInfo(name = "Description") @NotNull
    val description: String,

    @ColumnInfo(name = "SalesPerson") @NotNull
    val salesPerson: String,

    @ColumnInfo(name = "Rating") @NotNull
    val rating: Int,

    @ColumnInfo(name = "CategoryID") @NotNull
    val categoryID: Int,

): Serializable




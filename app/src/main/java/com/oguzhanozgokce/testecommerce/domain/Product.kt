package com.oguzhanozgokce.testecommerce.domain

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull


// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
// Parcelable: Bir sınıfın nesnesini başka bir sınıfa göndermek için kullanılır. Serializable'ın daha hızlı çalışan versiyonudur.


@Entity(tableName = "Product")
@Parcelize
data class Product(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ProductID")  val id: Int,
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Image") val image: String,
    @ColumnInfo(name = "Price") val price: Double,
    @ColumnInfo(name = "Rating") val rating: Int,
    @ColumnInfo(name = "SalePrice") val salePrice: Double,
    @ColumnInfo(name = "IsFavorite") val isFavorite: Boolean,
    @ColumnInfo(name = "Count") val count: Int,
    @ColumnInfo(name = "Description") val description: String,
    @ColumnInfo(name = "CategoryID") val category: String
) : Parcelable





package com.oguzhanozgokce.testecommerce.entitiy

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import org.jetbrains.annotations.NotNull
import java.io.Serializable


// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
// Parcelable: Bir sınıfın nesnesini başka bir sınıfa göndermek için kullanılır. Serializable'ın daha hızlı çalışan versiyonudur.


@Entity(tableName = "product")
data class Product(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ProductID") @NotNull
    val productID: Int = 0,

    @ColumnInfo(name = "Title") @NotNull
    val title: String,

    @ColumnInfo(name = "Price") @NotNull
    val price: Int,

    @ColumnInfo(name = "Image") @NotNull
    val image: String,

    @ColumnInfo(name = "isFavorite") @NotNull
    var isFavorite: Boolean = false

): Serializable




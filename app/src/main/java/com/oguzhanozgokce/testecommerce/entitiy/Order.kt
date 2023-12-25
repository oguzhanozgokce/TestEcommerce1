package com.oguzhanozgokce.testecommerce.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(
    tableName = "orders",
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.NO_ACTION
        ),
        ForeignKey(
            entity = Product::class,
            parentColumns = ["ProductID"],
            childColumns = ["ProductID"],
            onDelete = ForeignKey.NO_ACTION
        )
    ]
)
data class Order(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "orderId") @NotNull
    val orderId: Int = 0,

    @ColumnInfo(name = "userId") @NotNull
    val userId: Int = 0,

    @ColumnInfo(name = "ProductID") @NotNull
    val productId: Int = 0
)
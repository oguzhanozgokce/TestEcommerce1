package com.oguzhanozgokce.testecommerce.hilt

import android.content.Context
import androidx.room.Room
import com.oguzhanozgokce.testecommerce.data.datasource.ProductDataSource
import com.oguzhanozgokce.testecommerce.data.repo.ProductRepository
import com.oguzhanozgokce.testecommerce.data.room.DataBase
import com.oguzhanozgokce.testecommerce.data.room.ProductDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------	 |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun providesProductDataSource(productDao: ProductDao): ProductDataSource {
        return ProductDataSource(productDao)

    }
    @Provides
    @Singleton
    fun providesProductRepository(productDataSource: ProductDataSource): ProductRepository {
        return ProductRepository(productDataSource)
    }
    @Provides
    @Singleton
    fun providesProductDao(@ApplicationContext context: Context): ProductDao {
        val db = Room.databaseBuilder(context, DataBase::class.java, "testdb.db")
            .createFromAsset("testdb.db").build()
        return db.productDao()

    }
}
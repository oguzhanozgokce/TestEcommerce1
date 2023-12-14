package com.oguzhanozgokce.testecommerce.hilt

import com.oguzhanozgokce.testecommerce.data.datasource.ProductDataSource
import com.oguzhanozgokce.testecommerce.data.repo.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Code with ♥️
// _______________________________
// |					         |
// |  Created by Oguzhan OZGOKCE |
// |	--------------------				         |
// |  oguzhanozgokce34@Android.  |
// |_____________________________|
@Module
@InstallIn(SingletonComponent::class )
class AppModule {
    @Provides
    @Singleton
    fun providesProductDataSource(): ProductDataSource {
        return ProductDataSource()

    }

    @Provides
    @Singleton
    fun providesProductRepository( productDataSource: ProductDataSource): ProductRepository {
        return ProductRepository(productDataSource)
    }
}
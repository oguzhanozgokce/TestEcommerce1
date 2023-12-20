package com.oguzhanozgokce.testecommerce.hilt

import android.content.Context
import androidx.room.Room
import com.oguzhanozgokce.testecommerce.data.datasource.CartDataSource
import com.oguzhanozgokce.testecommerce.data.datasource.CategoryDataSource
import com.oguzhanozgokce.testecommerce.data.datasource.ProductDataSource
import com.oguzhanozgokce.testecommerce.data.datasource.UserDataSource
import com.oguzhanozgokce.testecommerce.data.repo.CartRepository
import com.oguzhanozgokce.testecommerce.data.repo.CategoryRepository
import com.oguzhanozgokce.testecommerce.data.repo.ProductRepository
import com.oguzhanozgokce.testecommerce.data.repo.UserRepository
import com.oguzhanozgokce.testecommerce.data.room.CartDao
import com.oguzhanozgokce.testecommerce.data.room.CategoryDao
import com.oguzhanozgokce.testecommerce.data.room.DataBase
import com.oguzhanozgokce.testecommerce.data.room.ProductDao
import com.oguzhanozgokce.testecommerce.data.room.UserDao
import com.oguzhanozgokce.testecommerce.ui.login.util.UserSessionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideUserSessionManager(@ApplicationContext context: Context): UserSessionManager {
        return UserSessionManager(context)
    }
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
    fun providesProductDao(database: DataBase): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): DataBase {
        return Room.databaseBuilder(context, DataBase::class.java, "testdb.db")
            .fallbackToDestructiveMigration()
            .createFromAsset("testdb.db").build()
    }

    //UserDataSource
    @Provides
    @Singleton
    fun providesUserDataSource(userDao: UserDao): UserDataSource {
        return UserDataSource(userDao)

    }

    //UserRepository
    @Provides
    @Singleton
    fun providesUserRepository(userDataSource: UserDataSource): UserRepository {
        return UserRepository(userDataSource)
    }

    //UserDao
    @Provides
    @Singleton
    fun providesUserDao(database: DataBase): UserDao {
        return database.userDao()
    }

    //cart
    @Provides
    @Singleton
    fun providesCartDao(database: DataBase): CartDao = database.cartDao()

    @Provides
    @Singleton
    fun providesCartDataSource(cartDao: CartDao): CartDataSource = CartDataSource(cartDao)

    @Provides
    @Singleton
    fun providesCartRepository(cartDataSource: CartDataSource): CartRepository =
        CartRepository(cartDataSource)

    //category
    @Provides
    @Singleton
    fun providesCategoryDao(database: DataBase): CategoryDao = database.categoryDao()

    @Provides
    @Singleton
    fun providesCategoryDataSource(categoryDao: CategoryDao): CategoryDataSource =
        CategoryDataSource(categoryDao)

    @Provides
    @Singleton
    fun providesCategoryRepository(categoryDataSource: CategoryDataSource): CategoryRepository =
        CategoryRepository(categoryDataSource)


}
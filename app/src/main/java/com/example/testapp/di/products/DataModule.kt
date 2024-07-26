package com.example.testapp.di.products

import android.content.Context
import androidx.room.Room
import com.example.testapp.data.product.cache.ProductsCacheDataSource
import com.example.testapp.data.product.cloud.ProductsCloudDataSource
import com.example.testapp.data.product.mappers.*
import com.example.testapp.core.retrofit.RetrofitService
import com.example.testapp.core.room.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    fun provideProductCloudDataSource(retrofitService: RetrofitService): ProductsCloudDataSource{
        return ProductsCloudDataSource.BaseProductsCloudDataSource(retrofitService)
    }

    @Provides
    fun provideProductsCacheDataSource(database: Database): ProductsCacheDataSource{
        return ProductsCacheDataSource.BaseProductsCacheDataSource(database)
    }

    @Provides
    fun provideRetrofitService(): RetrofitService {
        return RetrofitService.BaseRetrofitService("https://fakestoreapi.com")
    }

    @Provides
    fun provideProductDtoToDomainMapper(): ProductDtoToDomainMapper {
        return ProductDtoToDomainMapper.BaseProductDtoToDomainMapper()
    }

    @Provides
    fun provideProductDtoToCacheMapper(): ProductDtoToCacheMapper{
        return ProductDtoToCacheMapper.BaseProductDtoToCacheMapper()
    }

    @Provides
    fun provideProductCacheToDomainMapper(): ProductCacheToDomainMapper{
        return ProductCacheToDomainMapper.BaseProductCacheToDomainMapper()
    }

    @Provides
    fun provideRoom(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, "database").build()
    }
}
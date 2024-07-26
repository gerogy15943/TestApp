package com.example.testapp.di.products

import com.example.testapp.data.product.BaseProductsRepository
import com.example.testapp.data.product.cache.ProductsCacheDataSource
import com.example.testapp.data.product.cloud.ProductsCloudDataSource
import com.example.testapp.data.product.mappers.ProductCacheToDomainMapper
import com.example.testapp.data.product.mappers.ProductDtoToCacheMapper
import com.example.testapp.domain.core.ExceptionHandler
import com.example.testapp.domain.product.ProductInteractor
import com.example.testapp.domain.product.ProductsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideProductsRepository(productsCacheDataSource: ProductsCacheDataSource, productProductsCloudDataSource: ProductsCloudDataSource,
                                  mapperToCache: ProductDtoToCacheMapper, mappetoDomainMapper: ProductCacheToDomainMapper): ProductsRepository{
        return BaseProductsRepository(productsCacheDataSource, productProductsCloudDataSource, mapperToCache, mappetoDomainMapper)
    }

    @Provides
    fun provideProductInteractor(productsRepository: ProductsRepository, exceptionHandler: ExceptionHandler): ProductInteractor{
        return ProductInteractor.BaseProductInteractor(productsRepository, exceptionHandler)
    }

    @Provides
    fun provideExceptionHandler(): ExceptionHandler{
        return ExceptionHandler.BaseExceptionHandler()
    }
}
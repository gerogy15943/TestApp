package com.example.testapp.di.products

import android.content.Context
import com.example.testapp.presentation.core.DispatchersList
import com.example.testapp.presentation.core.LiveDataWrapper
import com.example.testapp.presentation.core.ResourceManager
import com.example.testapp.presentation.product.productDetail.ProductLiveDataWrapper
import com.example.testapp.presentation.product.productsList.BaseProductToUiMapper
import com.example.testapp.presentation.product.productsList.ProductToUiMapper
import com.example.testapp.presentation.product.productsList.ProductUi
import com.example.testapp.presentation.product.productsList.ProductsLiveDataWrapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UiModule {

    @Provides
    fun provideResourceManager(@ApplicationContext context: Context): ResourceManager{
        return ResourceManager.Base(context)
    }

    @Provides
    fun provideProductToUiMapper(resourceManager: ResourceManager): ProductToUiMapper {
        return BaseProductToUiMapper(resourceManager)
    }

    @Provides
    fun provideProductLiveData(): LiveDataWrapper<List<ProductUi>>{
        return ProductsLiveDataWrapper()
    }

    @Provides
    fun provideProducLiveData(): LiveDataWrapper<ProductUi>{
        return ProductLiveDataWrapper()
    }

    @Provides
    fun provideDispatchers(): DispatchersList{
        return DispatchersList.Base()
    }
}
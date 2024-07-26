package com.example.testapp.presentation.productDetail

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.testapp.data.product.cache.ProductsCacheDataSource
import com.example.testapp.data.product.mappers.ProductCacheToDomainMapper
import com.example.testapp.data.products.cache.TestProductsCacheDataSource
import com.example.testapp.domain.ExceptionType
import com.example.testapp.domain.TestProductsInteractor
import com.example.testapp.domain.core.ExceptionHandler
import com.example.testapp.domain.product.ProductDomain
import com.example.testapp.domain.product.ProductsRepository
import com.example.testapp.presentation.FakeResourceManager
import com.example.testapp.presentation.core.DispatchersList
import com.example.testapp.presentation.core.LiveDataWrapper
import com.example.testapp.presentation.product.productDetail.ProductDetailViewModel
import com.example.testapp.presentation.product.productsList.BaseProductToUiMapper
import com.example.testapp.presentation.product.productsList.ProductUi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ProductDetailViewModelTest {

    lateinit var context: Context

    @Before
    fun setup(){
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun failProduct(){
        val expected = ProductUi.Error("Не удалось загрузить продукт")
        val resourceManager = FakeResourceManager(ExceptionType.NOTFOUND)
        val mapper = BaseProductToUiMapper(resourceManager)
        val productLiveData = ProductLiveDataTest()
        val viewModel = ProductDetailViewModel(productLiveData,
            TestProductsInteractor(
                TestProductRepository(
                    ProductCacheToDomainMapper.BaseProductCacheToDomainMapper(),
                    TestProductsCacheDataSource()
                ), ExceptionHandler.BaseExceptionHandler(), ExceptionType.UNKNOWNHOST),
            mapper, TestDispatchers()
        )
        viewModel.fetchProduct(-1)
        Assert.assertEquals(expected, productLiveData.getState())
    }

    @Test
    fun successProduct(){
        val expected = ProductUi.Base(2, "Продукт 2", 2.00, "Описание 2", "Категория 1", "http://localhost/2", 1.0)
        val resourceManager = FakeResourceManager(ExceptionType.NONE)
        val mapper = BaseProductToUiMapper(resourceManager)
        val productLiveData = ProductLiveDataTest()
        val viewModel = ProductDetailViewModel(productLiveData,
            TestProductsInteractor(
                TestProductRepository(
                    ProductCacheToDomainMapper.BaseProductCacheToDomainMapper(),
                    TestProductsCacheDataSource()
                ), ExceptionHandler.BaseExceptionHandler(), ExceptionType.UNKNOWNHOST),
            mapper, TestDispatchers()
        )
        viewModel.fetchProduct(2)
        Assert.assertEquals(expected, productLiveData.getState())
    }


    private class TestDispatchers: DispatchersList {
        override fun io(): CoroutineDispatcher = TestCoroutineDispatcher()
        override fun main(): CoroutineDispatcher = TestCoroutineDispatcher()
    }

    private interface FakeProductLiveData{
        fun getState(): ProductUi?
    }

    class ProductLiveDataTest: LiveDataWrapper<ProductUi>, FakeProductLiveData {
        private var state: ProductUi? = null

        override fun map(data: ProductUi) {
            state = data
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<ProductUi>) = Unit

        override fun getState(): ProductUi? = state
    }

    private inner class TestProductRepository(private val mapperToDomain: ProductCacheToDomainMapper,
                                              private val cacheDatasSource: ProductsCacheDataSource
    ): ProductsRepository {
        override suspend fun fetchProducts(): List<ProductDomain> {
            return cacheDatasSource.fetchProducts().map { it.map(mapperToDomain) }

        }

        override suspend fun fetchProduct(id: Int): ProductDomain {
            return cacheDatasSource.fetchProduct(id).map(mapperToDomain)
        }
    }
}
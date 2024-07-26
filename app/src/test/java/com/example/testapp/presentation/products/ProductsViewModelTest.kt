package com.example.testapp.presentation.products

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
import com.example.testapp.presentation.core.ResourceManager
import com.example.testapp.presentation.product.productsList.BaseProductToUiMapper
import com.example.testapp.presentation.product.productsList.ProductUi
import com.example.testapp.presentation.product.productsList.ProductsViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ProductsViewModelTest {

    lateinit var context: Context

    @Before
    fun setup(){
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun fail_throwable_unknown_host(){
        val expected = listOf(ProductUi.Error("Не удалось подключиться к серверу"))
        val resourceManager = FakeResourceManager(ExceptionType.UNKNOWNHOST)
        val mapper = BaseProductToUiMapper(resourceManager)
        val productLiveData = ProductLiveDataTest()
        val viewModel = ProductsViewModel(productLiveData,
            TestProductsInteractor(
                TestProductRepository(ProductCacheToDomainMapper.BaseProductCacheToDomainMapper(),
                    TestProductsCacheDataSource()), ExceptionHandler.BaseExceptionHandler(), ExceptionType.UNKNOWNHOST),
            mapper, TestDispatchers()
        )
        viewModel.fetchProducts()
        assertEquals(expected, productLiveData.getState())
    }

    @Test
    fun fail_throwable_generic(){
        val expected = listOf(ProductUi.Error("Что-то пошло не так"))
        val resourceManager = FakeResourceManager(ExceptionType.GENERIC)
        val mapper = BaseProductToUiMapper(resourceManager)
        val productLiveData = ProductLiveDataTest()
        val viewModel = ProductsViewModel(productLiveData,
            TestProductsInteractor(
                TestProductRepository(ProductCacheToDomainMapper.BaseProductCacheToDomainMapper(),
                    TestProductsCacheDataSource()), ExceptionHandler.BaseExceptionHandler(), ExceptionType.GENERIC),
            mapper, TestDispatchers()
        )
        viewModel.fetchProducts()
        assertEquals(expected, productLiveData.getState())
    }

    @Test
    fun success_products_list(){
            val expected = listOf(
                ProductUi.Base(
                    1,
                    "Продукт 1",
                    1.00,
                    "Описание 1",
                    "Категория 1",
                    "http://localhost/1",
                    1.0
                ),
                ProductUi.Base(
                    2,
                    "Продукт 2",
                    2.00,
                    "Описание 2",
                    "Категория 1",
                    "http://localhost/2",
                    1.0
                ),
                ProductUi.Base(
                    3,
                    "Продукт 3",
                    3.00,
                    "Описание 3",
                    "Категория 1",
                    "http://localhost/3",
                    1.0
                )
            )
            val resourceManager = ResourceManager.Base(context)
            val mapper = BaseProductToUiMapper(resourceManager)
            val productLiveData = ProductLiveDataTest()
            val viewModel = ProductsViewModel(productLiveData,
                TestProductsInteractor(
                    TestProductRepository(ProductCacheToDomainMapper.BaseProductCacheToDomainMapper(),
                        TestProductsCacheDataSource()), ExceptionHandler.BaseExceptionHandler(), ExceptionType.NONE),
                mapper, TestDispatchers()
            )
            viewModel.fetchProducts()
            assertEquals(expected, productLiveData.getState())

    }

    private class TestDispatchers: DispatchersList{
        override fun io(): CoroutineDispatcher = TestCoroutineDispatcher()
        override fun main(): CoroutineDispatcher = TestCoroutineDispatcher()
    }

    private interface FakeProductLiveData{
        fun getState(): List<ProductUi>
    }

    class ProductLiveDataTest: LiveDataWrapper<List<ProductUi>>, FakeProductLiveData {
        private val state = mutableListOf<ProductUi>()

        override fun map(data: List<ProductUi>) {
            state.clear()
            state.addAll(data)
        }

        override fun observe(owner: LifecycleOwner, observer: Observer<List<ProductUi>>) = Unit

        override fun getState(): List<ProductUi> = state
    }

    private inner class TestProductRepository(private val mapperToDomain: ProductCacheToDomainMapper,
                                              private val cacheDatasSource: ProductsCacheDataSource): ProductsRepository{
        override suspend fun fetchProducts(): List<ProductDomain> {
            return cacheDatasSource.fetchProducts().map { it.map(mapperToDomain) }

        }

        override suspend fun fetchProduct(id: Int): ProductDomain {
            return cacheDatasSource.fetchProduct(id).map(mapperToDomain)
        }
    }
}
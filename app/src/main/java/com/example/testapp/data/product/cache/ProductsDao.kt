package com.example.testapp.data.product.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProductsDao {
    @Query("SELECT * FROM productCache")
    fun fetchProducts(): List<ProductCache>

    @Query("SELECT * FROM productCache WHERE id= :productId")
    fun fetchProduct(productId: Int): ProductCache?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg product: ProductCache)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProducts(products: List<ProductCache>)

}
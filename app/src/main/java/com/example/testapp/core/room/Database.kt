package com.example.testapp.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testapp.data.product.cache.ProductCache
import com.example.testapp.data.product.cache.ProductsDao

@Database(entities = [ProductCache::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun productsDao(): ProductsDao
}
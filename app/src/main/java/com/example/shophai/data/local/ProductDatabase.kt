package com.example.shophai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.shophai.data.model.ProductsItem

@Database(
    entities = [ProductsItem::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ProductDatabase: RoomDatabase() {
    abstract fun getProductDao(): ProductDao
}
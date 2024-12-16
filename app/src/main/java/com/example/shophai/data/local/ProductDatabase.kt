package com.example.shophai.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.shophai.data.model.ProductsItem

@Database(entities = [ProductsIte::class], version = 1, exportSchema = false)
abstract class ProductDatabase: RoomDatabase() {

}
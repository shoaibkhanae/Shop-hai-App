package com.example.shophai.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.shophai.data.model.ProductsItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(products: List<ProductsItem>)

    @Query("SELECT * FROM products")
    fun getAllProducuts(): Flow<List<ProductsItem>>
}
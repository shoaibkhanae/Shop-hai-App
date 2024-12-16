package com.example.shophai.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.shophai.data.api.response.products.Rating
import com.google.gson.annotations.SerializedName


@Entity(tableName = "products")
data class ProductsItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("rating")
    val rating: Rating,
    @SerializedName("title")
    val title: String
)

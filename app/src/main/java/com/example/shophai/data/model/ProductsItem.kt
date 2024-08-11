package com.example.shophai.data.model

import com.google.gson.annotations.SerializedName

data class ProductsItem(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("Ratting")
    val rating: Rating,
    @SerializedName("title")
    val title: String
)

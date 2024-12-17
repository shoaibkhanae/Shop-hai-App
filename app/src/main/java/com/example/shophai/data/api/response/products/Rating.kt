package com.example.shophai.data.api.response.products

import com.google.gson.annotations.SerializedName

data class Rating(
    @SerializedName("rate")
    val rate: Double,
    @SerializedName("count")
    val count: Int,
)
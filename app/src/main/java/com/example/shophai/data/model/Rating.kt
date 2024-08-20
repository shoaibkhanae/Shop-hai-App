package com.example.shophai.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class Rating(
    @SerializedName("count")
    val count: Int,
    @SerializedName("rate")
    val rate: Double
)
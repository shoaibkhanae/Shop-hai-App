package com.example.shophai.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class Rating(
    @field:SerializedName("count")
    val count: Int,
    @field:SerializedName("rate")
    val rate: Double
)
package com.example.shophai.data.local

import androidx.room.TypeConverter
import com.example.shophai.data.api.response.products.Rating

class Converters {
    @TypeConverter
    fun RattingToString(rating: Rating): String {
        return "${rating.rate}" +"::"+ "${rating.count}"
    }

    @TypeConverter
    fun fromStringToRating(stringFromDb: String): Rating {
        val split = stringFromDb.split("::")
        val count = split[0].toInt()
        val rate = split[1].toDouble()
        return Rating(count, rate)
    }
}
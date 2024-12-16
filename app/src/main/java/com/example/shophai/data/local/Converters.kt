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
        return Rating(split[0], split[1])
    }
}
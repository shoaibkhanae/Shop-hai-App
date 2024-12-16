package com.example.shophai.di

import android.content.Context
import androidx.room.Room
import com.example.shophai.data.api.ShopApiService
import com.example.shophai.data.local.ProductDao
import com.example.shophai.data.local.ProductDatabase
import com.example.shophai.utils.AppConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    fun providesProductDao(database: ProductDatabase): ProductDao {
        return database.getProductDao()
    }

    @Provides
    fun providesDatabase(@ApplicationContext context: Context): ProductDatabase {
        return Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "products_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun providesBaseUrl(): String = AppConstants.BASE_URL

    @Provides
    @Singleton
    fun providesRetrofit(baseUrl: String): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): ShopApiService = retrofit.create(ShopApiService::class.java)
}
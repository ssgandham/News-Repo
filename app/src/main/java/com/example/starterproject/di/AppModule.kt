package com.example.starterproject.di

import com.example.starterproject.data.crypto.CoinAPI
import com.example.starterproject.data.crypto.CoinRepository
import com.example.starterproject.data.news.NewsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://newsapi.org/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideCoinAPI(retrofit: Retrofit): CoinAPI {
        return retrofit.create(CoinAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsAPI(retrofit: Retrofit): NewsAPI {
        return retrofit.create(NewsAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesCoinRepository(api: CoinAPI): CoinRepository {
        return CoinRepository(api)
    }



}
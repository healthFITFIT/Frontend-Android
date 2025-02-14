package com.example.core.data.remote_db

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
abstract class DbRemoteModule {
    @Binds
    internal abstract fun bindDbRemoteDataSource(
        retrofitApi: RetrofitApi
    ): DbRemoteDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {

    private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

    @Provides
    @Singleton
    fun provideRetrofit(

    ): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): RetrofitApiService {
        return retrofit.create(RetrofitApiService::class.java)
    }
}
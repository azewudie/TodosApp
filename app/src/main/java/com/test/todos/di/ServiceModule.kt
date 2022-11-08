package com.test.todos.di

import com.test.todos.models.remote.BAS_URL
import com.test.todos.models.remote.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Provides
    fun provideRetrofitClient() = OkHttpClient.Builder().build()

    @Provides
    fun provideConverterGson():Converter.Factory = GsonConverterFactory.create()

    @Provides

    fun provideService(client: OkHttpClient,
    gsonFactory: Converter.Factory) = Retrofit.Builder()
        .baseUrl(BAS_URL)
        .addConverterFactory(gsonFactory)
        .client(client)
        .build()
        .create(Service::class.java)


}
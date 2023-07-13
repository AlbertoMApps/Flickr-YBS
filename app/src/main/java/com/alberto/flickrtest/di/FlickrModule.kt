package com.alberto.flickrtest.di

import android.util.Log
import com.alberto.flickrtest.business.IFlickrRepository
import com.alberto.flickrtest.data.api.FlickrApi
import com.alberto.flickrtest.data.common.Constants.FLICKR_BASE_URL
import com.alberto.flickrtest.data.repository.FlickrRepository
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FlickrModule {

    @Provides
    @Singleton
    fun provideFlickrApi(
        client: OkHttpClient,
        gson: Gson
    ): FlickrApi =
        Retrofit.Builder()
            .baseUrl(FLICKR_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(FlickrApi::class.java)

    @Provides
    @Singleton
    fun getGson(): Gson =
        GsonBuilder().serializeNulls().setLenient().create()

    @Provides
    @Singleton
    fun getLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor { message -> Log.d("Logger", message) }
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun getOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(50, TimeUnit.SECONDS)

        return httpBuilder
            .protocols(mutableListOf(Protocol.HTTP_1_1))
            .build()
    }

    @Provides
    @Singleton
    fun provideFlickrRepository(
        api: FlickrApi
    ): IFlickrRepository =
        FlickrRepository(
            api
        )


}
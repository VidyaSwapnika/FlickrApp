package com.example.flickrapp.data.di

import com.example.flickrapp.data.repository.FlickrAppRepository
import com.example.flickrapp.data.repository.FlickrRepositoryImpl
import com.example.flickrapp.data.service.FlickrApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class FlickrNetworkModule {

    @Provides
    @Singleton
    fun providerRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.flickr.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providerApiService(retrofit: Retrofit) : FlickrApiService {
        return retrofit.create(FlickrApiService::class.java)
    }

    @Provides
    @Singleton
    fun providerRepositoryImpl(flickrApiService: FlickrApiService) : FlickrAppRepository {
        return FlickrRepositoryImpl(flickrApiService)
    }

}
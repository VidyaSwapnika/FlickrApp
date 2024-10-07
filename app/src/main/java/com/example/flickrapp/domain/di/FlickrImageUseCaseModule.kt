package com.example.flickrapp.domain.di

import com.example.flickrapp.domain.usecase.GetFlickrSearchImageListUseCase
import com.example.flickrapp.data.repository.FlickrRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FlickrImageUseCaseModule {

    @Provides
    @Singleton
    fun FlickrSearchImageListUseCaseProvider(repositoryImpl: FlickrRepositoryImpl) : GetFlickrSearchImageListUseCase{
        return GetFlickrSearchImageListUseCase(repositoryImpl)
    }

}
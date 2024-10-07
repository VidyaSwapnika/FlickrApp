package com.example.flickrapp.data.repository

import com.example.flickrapp.data.model.FlickrResponse
import com.example.flickrapp.data.service.FlickrApiService
import javax.inject.Inject
import javax.inject.Singleton


interface FlickrAppRepository {
    suspend fun searchImages(tags: String): FlickrResponse
}

@Singleton
class FlickrRepositoryImpl @Inject constructor(
    private val flickrApiService: FlickrApiService
) : FlickrAppRepository {
    override suspend fun searchImages(tags: String): FlickrResponse {
        return flickrApiService.searchImages(tags)
    }
}
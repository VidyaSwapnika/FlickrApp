package com.example.flickrapp.domain.usecase

import com.example.flickrapp.domain.UiState
import com.example.flickrapp.data.model.FlickrResponse
import com.example.flickrapp.data.repository.FlickrRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.lang.Exception
import javax.inject.Inject

class GetFlickrSearchImageListUseCase @Inject constructor(private val flickrRepositoryImpl : FlickrRepositoryImpl)  {

    operator fun invoke(tags : String) : Flow<UiState<FlickrResponse>> = flow {
        emit(UiState.Loading())
        try {
            emit(UiState.Success(data = flickrRepositoryImpl.searchImages(tags)))
        }catch (e : Exception){
            emit(UiState.Error(message = e.message.toString()))
        }
    }.flowOn(Dispatchers.IO)

}
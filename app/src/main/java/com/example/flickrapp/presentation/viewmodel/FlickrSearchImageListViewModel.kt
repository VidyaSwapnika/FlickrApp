package com.example.flickrapp.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flickrapp.data.model.FlickrImage
import com.example.flickrapp.domain.UiState
import com.example.flickrapp.domain.usecase.GetFlickrSearchImageListUseCase
import com.example.flickrapp.presentation.state.FlickrSearchImageListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FlickrSearchImageListViewModel @Inject constructor(
    private val getFlickrSearchImageListUseCase: GetFlickrSearchImageListUseCase
) : ViewModel() {

    private val _flickrSearchImageList = mutableStateOf(FlickrSearchImageListState())
    val flickrSearchImageList: State<FlickrSearchImageListState> get() = _flickrSearchImageList

    private val _flickImageData = MutableLiveData<FlickrImage?>(null)
    val flickImageData: LiveData<FlickrImage?> = _flickImageData

    fun updateSelectedImageDetailScreen(flickImageData: FlickrImage) {
        _flickImageData.value = flickImageData
    }

    fun getFlickrSearchImageListApi(tags: String) {

        getFlickrSearchImageListUseCase.invoke(tags).onEach {
            when (it) {
                is UiState.Loading -> {
                    _flickrSearchImageList.value = FlickrSearchImageListState(isLoading = true)
                }

                is UiState.Success -> {
                    _flickrSearchImageList.value = FlickrSearchImageListState(data = it.data?.items)
                }

                is UiState.Error -> {
                    _flickrSearchImageList.value =
                        FlickrSearchImageListState(error = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}
package com.example.flickrapp.presentation.state

import com.example.flickrapp.data.model.FlickrImage

data class FlickrSearchImageListState(
    val isLoading: Boolean = false,
    val data: List<FlickrImage>? = null,
    var error: String = ""
)
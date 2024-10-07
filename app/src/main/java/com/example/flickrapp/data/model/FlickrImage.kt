package com.example.flickrapp.data.model

data class FlickrImage(
    val title: String,
    val link: String,
    val media: Media,
    val author: String,
    val description: String,
    val published: String
)

data class Media(
    val m: String
)

data class FlickrResponse(
    val items: List<FlickrImage>
)
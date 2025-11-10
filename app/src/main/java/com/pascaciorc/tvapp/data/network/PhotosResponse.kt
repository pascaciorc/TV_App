package com.pascaciorc.tvapp.data.network

data class PhotosResponse(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

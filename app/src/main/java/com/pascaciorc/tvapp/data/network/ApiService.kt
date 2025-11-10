package com.pascaciorc.tvapp.data.network

import retrofit2.http.GET

interface ApiService {

    @GET("albums")
    suspend fun getAlbums(): List<AlbumsResponse>

    @GET("photos")
    suspend fun getPhotos(): List<PhotosResponse>
}
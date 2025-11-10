package com.pascaciorc.tvapp.data.repository

import com.pascaciorc.tvapp.data.network.ApiService
import javax.inject.Inject

class AlbumsRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getAlbums() = apiService.getAlbums().take(5)
    suspend fun getPhotos() = apiService.getPhotos()
}
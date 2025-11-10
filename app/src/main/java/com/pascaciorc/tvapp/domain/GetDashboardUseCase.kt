package com.pascaciorc.tvapp.domain

import com.pascaciorc.tvapp.data.repository.AlbumsRepository
import com.pascaciorc.tvapp.presentation.dashboard.Category
import com.pascaciorc.tvapp.presentation.dashboard.MediaItem
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class GetDashboardDataUseCase(
    private val repository: AlbumsRepository
) {
    suspend fun invoke(): List<Category> = coroutineScope {
        val albumsDeferred = async { repository.getAlbums() }
        val photosDeferred = async { repository.getPhotos() }

        val albums = albumsDeferred.await()
        val photos = photosDeferred.await()

        val photosMap = photos.groupBy { it.albumId }
        val result = albums.map { album ->
            val photos = photosMap[album.id]?.take(10)
            photos?.let {
                Category(album.id.toString(), album.title, it.mapIndexed { index, photo ->
                    MediaItem(
                        photo.id.toString(),
                        photo.title,
                        "https://picsum.photos/400/600?random=${index}"
                    )
                })
            }
        }

        return@coroutineScope result as List<Category>
    }
}
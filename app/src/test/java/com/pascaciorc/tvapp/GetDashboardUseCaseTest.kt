package com.pascaciorc.tvapp

import com.pascaciorc.tvapp.data.network.AlbumsResponse
import com.pascaciorc.tvapp.data.network.PhotosResponse
import com.pascaciorc.tvapp.data.repository.AlbumsRepository
import com.pascaciorc.tvapp.domain.GetDashboardDataUseCase
import com.pascaciorc.tvapp.presentation.dashboard.Category
import com.pascaciorc.tvapp.presentation.dashboard.MediaItem
import kotlinx.coroutines.test.runTest
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.Test
import kotlin.test.assertEquals

class GetDashboardUseCaseTest {
    private val repository: AlbumsRepository = mock()
    private val useCase = GetDashboardDataUseCase(repository)

    @Test
    fun `returns user from repository`() = runTest {
        val expected = listOf(Category("1", "category", listOf(MediaItem("1", "photo", "https://picsum.photos/400/600?random=0"))))
        whenever(repository.getAlbums()).thenReturn(listOf(AlbumsResponse(1, 1, "category")))
        whenever(repository.getPhotos()).thenReturn(listOf(PhotosResponse(1, 1, "photo", "", "")))
        val result = useCase.invoke()

        assertEquals(expected, result)
    }
}
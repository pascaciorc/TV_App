package com.pascaciorc.tvapp.presentation.di

import com.pascaciorc.tvapp.data.network.ApiService
import com.pascaciorc.tvapp.data.repository.AlbumsRepository
import com.pascaciorc.tvapp.domain.GetDashboardDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRepository(
        apiService: ApiService
    ): AlbumsRepository {
        return AlbumsRepository(apiService)
    }

    @Provides
    fun provideGetEntriesUseCase(repository: AlbumsRepository) = GetDashboardDataUseCase(repository)
}
package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.domain.repository.AlbumRepository
import com.example.androidapplicationtemplate.domain.repository.ArtistRepository
import com.example.androidapplicationtemplate.domain.repository.TagRepository
import com.example.androidapplicationtemplate.domain.repository.TrackRepository
import com.example.androidapplicationtemplate.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetAllTagsUseCase(
        tagRepository: TagRepository,
    ) = GetAllTagsUseCase(tagRepository)

    @Provides
    fun provideGetTopAlbumsByTagUseCase(
		albumRepository: AlbumRepository,
	) = GetTopAlbumsByTagUseCase(albumRepository)

    @Provides
    fun provideGetTopArtistsByTagUseCase(
        artistRepository: ArtistRepository,
    ) = GetTopArtistsByTagUseCase(artistRepository)

    @Provides
    fun provideGetTopTracksByTagUseCase(
        trackRepository: TrackRepository,
    ) = GetTopTracksByTagUseCase(trackRepository)


    @Provides
    fun provideTagInfoUseCase(
        tagRepository: TagRepository,
    ) = GetTagInfoUseCase(tagRepository)

}
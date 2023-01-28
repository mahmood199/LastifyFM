package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.domain.repository.AlbumRepository
import com.example.androidapplicationtemplate.domain.repository.TagRepository
import com.example.androidapplicationtemplate.domain.usecase.GetAllTagsUseCase
import com.example.androidapplicationtemplate.domain.usecase.GetTopAlbumsByTagUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideSomeUseCase(
        tagRepository: TagRepository,
    ) = GetAllTagsUseCase(tagRepository)

    @Provides
    fun provideAlbumsUseCase(
		albumRepository: AlbumRepository,
	) = GetTopAlbumsByTagUseCase(albumRepository)

}
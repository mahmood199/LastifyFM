package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.data.remote.remoteDataSource.AlbumRemoteDataSource
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.TagRemoteDataSource
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.TrackRemoteDataSource
import com.example.androidapplicationtemplate.data.remote.remoteServices.AlbumServices
import com.example.androidapplicationtemplate.data.remote.remoteServices.TagServices
import com.example.androidapplicationtemplate.data.remote.remoteServices.TrackServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    fun provideTagRemoteDataSource(
        tagServices: TagServices,
    ) = TagRemoteDataSource(tagServices)


    @Provides
    fun provideAlbumRemoteDataSource(
        albumServices: AlbumServices,
    ) = AlbumRemoteDataSource(albumServices)

    @Provides
    fun provideTrackRemoteDataSource(
        albumServices: TrackServices,
    ) = TrackRemoteDataSource(albumServices)


}
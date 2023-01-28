package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.data.remote.remoteDataSource.TagRemoteDataSource
import com.example.androidapplicationtemplate.data.remote.remoteServices.TagServices
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
	): TagRemoteDataSource {
		return TagRemoteDataSource(tagServices)
	}

}
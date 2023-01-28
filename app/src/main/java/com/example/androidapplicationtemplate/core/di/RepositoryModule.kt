package com.example.androidapplicationtemplate.core.di

import com.example.androidapplicationtemplate.data.local.localDataSource.SomeLocalDataSource
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.AlbumRemoteDataSource
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.TagRemoteDataSource
import com.example.androidapplicationtemplate.data.repositoryImpl.AlbumRepositoryImpl
import com.example.androidapplicationtemplate.domain.repository.TagRepository
import com.example.androidapplicationtemplate.data.repositoryImpl.TagRepositoryImpl
import com.example.androidapplicationtemplate.domain.repository.AlbumRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

	@Provides
	fun provideSomeRepository(
        someLocalDataSource: SomeLocalDataSource,
        tagRemoteDataSource: TagRemoteDataSource,
	): TagRepository {
		return TagRepositoryImpl(someLocalDataSource, tagRemoteDataSource)
	}

	@Provides
	fun provideSomeRepository(
		remoteDataSource: AlbumRemoteDataSource,
	): AlbumRepository {
		return AlbumRepositoryImpl(remoteDataSource)
	}


}
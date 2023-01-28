package com.example.androidapplicationtemplate.data.repositoryImpl

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.AlbumRemoteDataSource
import com.example.androidapplicationtemplate.domain.repository.AlbumRepository
import javax.inject.Inject

class AlbumRepositoryImpl @Inject constructor(
    private val remoteDataSource: AlbumRemoteDataSource,
) : AlbumRepository {

    override suspend fun getAlbumsByTag(tag: Tag): Resource<AlbumListResponse> {
        return remoteDataSource.getAlbumsByTag(tag)
    }

}
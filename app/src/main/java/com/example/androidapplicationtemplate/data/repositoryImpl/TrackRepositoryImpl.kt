package com.example.androidapplicationtemplate.data.repositoryImpl

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.models.response.TrackListResponse
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.AlbumRemoteDataSource
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.TrackRemoteDataSource
import com.example.androidapplicationtemplate.domain.repository.AlbumRepository
import com.example.androidapplicationtemplate.domain.repository.TrackRepository
import javax.inject.Inject

class TrackRepositoryImpl @Inject constructor(
    private val remoteDataSource: TrackRemoteDataSource,
) : TrackRepository {

    override suspend fun getTrackByTag(tag: Tag): Resource<TrackListResponse> {
        return remoteDataSource.getTracksByTag(tag)
    }

}
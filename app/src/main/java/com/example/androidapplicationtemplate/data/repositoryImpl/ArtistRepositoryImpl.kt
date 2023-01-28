package com.example.androidapplicationtemplate.data.repositoryImpl

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.AlbumRemoteDataSource
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.ArtistRemoteDataSource
import com.example.androidapplicationtemplate.domain.repository.AlbumRepository
import com.example.androidapplicationtemplate.domain.repository.ArtistRepository
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val remoteDataSource: ArtistRemoteDataSource,
) : ArtistRepository {

    override suspend fun getArtistByTag(tag: Tag): Resource<AlbumListResponse> {
        return remoteDataSource.getArtistsByTag(tag)
    }

}
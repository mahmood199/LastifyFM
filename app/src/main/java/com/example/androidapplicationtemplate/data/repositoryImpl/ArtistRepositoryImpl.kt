package com.example.androidapplicationtemplate.data.repositoryImpl

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Artist
import com.example.androidapplicationtemplate.data.models.response.ArtistDetailResponse
import com.example.androidapplicationtemplate.data.models.response.ArtistListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.ArtistRemoteDataSource
import com.example.androidapplicationtemplate.domain.repository.ArtistRepository
import javax.inject.Inject

class ArtistRepositoryImpl @Inject constructor(
    private val remoteDataSource: ArtistRemoteDataSource,
) : ArtistRepository {

    override suspend fun getArtistByTag(tag: Tag): Resource<ArtistListResponse> {
        return remoteDataSource.getArtistsByTag(tag)
    }

    override suspend fun getArtistDetails(artist: String): Resource<ArtistDetailResponse> {
        return remoteDataSource.getArtistDetails(artist)
    }

}
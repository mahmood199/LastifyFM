package com.example.androidapplicationtemplate.data.remote.remoteDataSource

import com.example.androidapplicationtemplate.core.network.FailureStatus
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.remote.remoteServices.AlbumServices
import com.example.androidapplicationtemplate.data.remote.remoteServices.ArtistServices
import javax.inject.Inject

class ArtistRemoteDataSource @Inject constructor(
    private val albumServices: ArtistServices,
) {

    suspend fun getArtistsByTag(tag: Tag): Resource<AlbumListResponse> {
        return try {
            val result = albumServices.getArtistsByTag(tag.name)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Failure(FailureStatus.API_FAIL)
        }
    }

}
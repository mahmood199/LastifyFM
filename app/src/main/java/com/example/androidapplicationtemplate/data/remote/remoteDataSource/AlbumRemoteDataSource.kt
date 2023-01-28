package com.example.androidapplicationtemplate.data.remote.remoteDataSource

import com.example.androidapplicationtemplate.core.network.FailureStatus
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.remote.remoteServices.AlbumServices
import javax.inject.Inject

class AlbumRemoteDataSource @Inject constructor(
    private val albumServices: AlbumServices,
) {

    suspend fun getAlbumsByTag(tag: Tag): Resource<AlbumListResponse> {
        return try {
            val result = albumServices.getAlbumsByTag(tag.name)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Failure(FailureStatus.API_FAIL)
        }
    }

}
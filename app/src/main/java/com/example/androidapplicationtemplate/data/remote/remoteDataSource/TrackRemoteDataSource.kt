package com.example.androidapplicationtemplate.data.remote.remoteDataSource

import com.example.androidapplicationtemplate.core.network.FailureStatus
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.models.response.TrackListResponse
import com.example.androidapplicationtemplate.data.remote.remoteServices.TrackServices
import javax.inject.Inject

class TrackRemoteDataSource @Inject constructor(
    private val trackServices: TrackServices,
) {

    suspend fun getTracksByTag(tag: Tag): Resource<TrackListResponse> {
        return try {
            val result = trackServices.getTracksByTag(tag.name)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Failure(FailureStatus.API_FAIL)
        }
    }

}
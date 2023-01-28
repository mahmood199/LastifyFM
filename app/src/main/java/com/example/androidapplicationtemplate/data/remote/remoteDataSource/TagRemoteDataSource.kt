package com.example.androidapplicationtemplate.data.remote.remoteDataSource

import com.example.androidapplicationtemplate.data.models.response.TagListResponse
import com.example.androidapplicationtemplate.data.remote.remoteServices.TagServices
import javax.inject.Inject

class TagRemoteDataSource @Inject constructor(
    private val tagServices: TagServices,
) {

    suspend fun getTags(): TagListResponse {
        return tagServices.getTags()
    }

}
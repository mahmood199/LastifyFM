package com.example.androidapplicationtemplate.data.remote.remoteDataSource

import com.example.androidapplicationtemplate.core.network.FailureStatus
import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.models.response.TagListResponse
import com.example.androidapplicationtemplate.data.models.response.TagResponse
import com.example.androidapplicationtemplate.data.remote.remoteServices.TagServices
import javax.inject.Inject

class TagRemoteDataSource @Inject constructor(
    private val tagServices: TagServices,
) {

    suspend fun getAllTags(): Resource<TagListResponse> {
        return try {
            val result = tagServices.getAllTags()
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Failure(FailureStatus.API_FAIL)
        }
    }

    suspend fun getTagInfo(tag: String): Resource<TagResponse> {
        return try {
            val result = tagServices.getTagInfo(tag)
            Resource.Success(result)
        } catch (e: Exception) {
            Resource.Failure(FailureStatus.API_FAIL)
        }
    }
}
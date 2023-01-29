package com.example.androidapplicationtemplate.data.repositoryImpl

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.local.localDataSource.SomeLocalDataSource
import com.example.androidapplicationtemplate.data.models.response.TagListResponse
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.TagRemoteDataSource
import com.example.androidapplicationtemplate.domain.repository.TagRepository
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val localDataSource: SomeLocalDataSource,
    private val remoteDataSource: TagRemoteDataSource,
) : TagRepository {

    override suspend fun getAllTags(): Resource<TagListResponse> {
        return Resource.Success(remoteDataSource.getAllTags())
    }

}
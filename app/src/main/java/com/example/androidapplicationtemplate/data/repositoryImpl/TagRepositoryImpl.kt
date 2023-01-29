package com.example.androidapplicationtemplate.data.repositoryImpl

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.local.localDataSource.SomeLocalDataSource
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.models.response.TagListResponse
import com.example.androidapplicationtemplate.data.models.response.TagResponse
import com.example.androidapplicationtemplate.data.remote.remoteDataSource.TagRemoteDataSource
import com.example.androidapplicationtemplate.domain.repository.TagRepository
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val localDataSource: SomeLocalDataSource,
    private val remoteDataSource: TagRemoteDataSource,
) : TagRepository {

    override suspend fun getAllTags(): Resource<TagListResponse> {
        return remoteDataSource.getAllTags()
    }

    override suspend fun getTagInfo(tag: String): Resource<TagResponse> {
        return remoteDataSource.getTagInfo(tag)
    }

}
package com.example.androidapplicationtemplate.domain.repository

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.models.response.TagListResponse
import com.example.androidapplicationtemplate.data.models.response.TagResponse

interface TagRepository {
    suspend fun getAllTags(): Resource<TagListResponse>
    suspend fun getTagInfo(tag: String): Resource<TagResponse>
}
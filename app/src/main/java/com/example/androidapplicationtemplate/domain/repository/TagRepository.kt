package com.example.androidapplicationtemplate.domain.repository

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.Response
import com.example.androidapplicationtemplate.data.models.response.TagListResponse

interface TagRepository {
    suspend fun someCrudOperation(): Resource<TagListResponse>
}
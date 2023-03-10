package com.example.androidapplicationtemplate.domain.repository

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag
import com.example.androidapplicationtemplate.data.models.response.TagListResponse

interface AlbumRepository {
    suspend fun getAlbumsByTag(tag: Tag): Resource<AlbumListResponse>
}
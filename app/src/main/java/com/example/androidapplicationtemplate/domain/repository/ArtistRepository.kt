package com.example.androidapplicationtemplate.domain.repository

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.ArtistListResponse
import com.example.androidapplicationtemplate.data.models.response.Tag

interface ArtistRepository {
    suspend fun getArtistByTag(tag: Tag): Resource<ArtistListResponse>
}
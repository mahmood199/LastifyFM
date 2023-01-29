package com.example.androidapplicationtemplate.domain.repository

import com.example.androidapplicationtemplate.core.network.Resource
import com.example.androidapplicationtemplate.data.models.response.*

interface ArtistRepository {
    suspend fun getArtistByTag(tag: Tag): Resource<ArtistListResponse>
    suspend fun getArtistDetails(artist: String): Resource<ArtistDetailResponse>
}
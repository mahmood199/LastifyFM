package com.example.androidapplicationtemplate.data.remote.remoteServices

import com.example.androidapplicationtemplate.core.credentials.API_KEY
import com.example.androidapplicationtemplate.data.models.response.AlbumListResponse
import com.example.androidapplicationtemplate.data.models.response.ArtistDetailResponse
import com.example.androidapplicationtemplate.data.models.response.ArtistListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtistServices {

    @GET("/2.0/?method=tag.gettopartists&api_key=${API_KEY}&format=json")
    suspend fun getArtistsByTag(@Query("tag") tag: String): ArtistListResponse

    @GET("/2.0/?method=artist.getinfo&api_key=${API_KEY}&format=json")
    suspend fun getArtistDetails(@Query("artist") artistName: String): ArtistDetailResponse


}